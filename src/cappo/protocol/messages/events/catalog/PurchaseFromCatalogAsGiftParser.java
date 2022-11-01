/*   1:    */ package cappo.protocol.messages.events.catalog;
/*   2:    */ 
/*   3:    */ import cappo.engine.Server;
/*   4:    */ import cappo.engine.network.MessageReader;
/*   5:    */ import cappo.engine.network.QueueWriter;
/*   6:    */ import cappo.engine.player.Clients;
/*   7:    */ import cappo.engine.player.Connection;
/*   8:    */ import cappo.game.catalog.Catalog;
/*   9:    */ import cappo.game.catalog.Catalog.CatalogPage;
/*  10:    */ import cappo.game.catalog.Catalog.CatalogProduct;
/*  11:    */ import cappo.game.catalog.giftwrapping.GiftWrappingConfiguration;
/*  12:    */ import cappo.game.collections.BaseItem;
/*  13:    */ import cappo.game.collections.UnseenItems;
/*  14:    */ import cappo.game.player.AvatarLook;
/*  15:    */ import cappo.game.player.PlayerData;
/*  16:    */ import cappo.game.player.data.AvatarData;
/*  17:    */ import cappo.game.roomengine.entity.item.extradata.MapStuffData;
/*  18:    */ import cappo.game.roomengine.entity.item.extradata.StuffDataReader;
/*  19:    */ import cappo.game.roomengine.entity.item.extradata.StuffDataWriter;
/*  20:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  21:    */ import cappo.game.roomengine.entity.item.floor.PresentItem;
/*  22:    */ import cappo.protocol.messages.IncomingMessageEvent;
/*  23:    */ import cappo.protocol.messages.composers.catalog.ErrorBuyComposer;
/*  24:    */ import cappo.protocol.messages.composers.catalog.ErrorPurchaseFromCatalogComposer;
/*  25:    */ import cappo.protocol.messages.composers.inventory.furni.FurniListUpdateComposer;
/*  26:    */ import cappo.protocol.messages.composers.inventory.purse.CreditBalanceComposer;
/*  27:    */ import cappo.protocol.messages.composers.notifications.BuyNotificationComposer;
/*  28:    */ import cappo.protocol.messages.composers.notifications.HabboActivityPointNotificationComposer;
/*  29:    */ import cappo.protocol.messages.composers.notifications.UnseenItemsComposer;
/*  30:    */ import java.util.Map;
/*  31:    */ 
/*  32:    */ public class PurchaseFromCatalogAsGiftParser
/*  33:    */   extends IncomingMessageEvent
/*  34:    */ {
/*  35:    */   public void messageReceived(Connection Main)
/*  36:    */   {
/*  37: 39 */     int pageId = Main.currentPacket.readInt();
/*  38: 40 */     int itemId = Main.currentPacket.readInt();
/*  39: 41 */     String extraParam = Main.currentPacket.readString();
/*  40: 42 */     String sendTo = Main.currentPacket.readString();
/*  41: 43 */     String text = Main.currentPacket.readString();
/*  42: 44 */     int boxSpriteId = Main.currentPacket.readInt();
/*  43: 45 */     Main.currentPacket.readInt();
/*  44: 46 */     Main.currentPacket.readInt();
/*  45: 47 */     boolean showAvatar = Main.currentPacket.readBoolean();
/*  46:    */     
/*  47:    */ 
/*  48: 50 */     BaseItem boxBaseItem = (BaseItem)GiftWrappingConfiguration.baseGiftItems.get(Integer.valueOf(boxSpriteId));
/*  49:    */     int wrappingCost;
/*  50:    */     int wrappingCost;
/*  51: 51 */     if (boxBaseItem == null)
/*  52:    */     {
/*  53: 52 */       boxBaseItem = (BaseItem)GiftWrappingConfiguration.baseGiftFreeItems.get(Integer.valueOf(boxSpriteId));
/*  54: 53 */       if (boxBaseItem == null)
/*  55:    */       {
/*  56: 54 */         QueueWriter.write(Main.socket, ErrorPurchaseFromCatalogComposer.compose(0));
/*  57: 55 */         return;
/*  58:    */       }
/*  59: 57 */       wrappingCost = 0;
/*  60:    */     }
/*  61:    */     else
/*  62:    */     {
/*  63: 59 */       wrappingCost = 1;
/*  64:    */     }
/*  65: 62 */     PlayerData plr = Clients.getPlayerData(sendTo);
/*  66: 63 */     if (plr == null)
/*  67:    */     {
/*  68: 64 */       QueueWriter.write(Main.socket, ErrorPurchaseFromCatalogComposer.compose(0));
/*  69: 65 */       return;
/*  70:    */     }
/*  71: 68 */     Catalog.CatalogPage page = (Catalog.CatalogPage)Catalog.pages.get(Integer.valueOf(pageId));
/*  72: 69 */     if ((page == null) || (!page.isEnabled))
/*  73:    */     {
/*  74: 70 */       QueueWriter.write(Main.socket, ErrorPurchaseFromCatalogComposer.compose(0));
/*  75: 71 */       return;
/*  76:    */     }
/*  77: 74 */     if (page.minRank > Main.playerData.staffLevel)
/*  78:    */     {
/*  79: 75 */       QueueWriter.write(Main.socket, ErrorPurchaseFromCatalogComposer.compose(0));
/*  80: 76 */       return;
/*  81:    */     }
/*  82: 79 */     Catalog.CatalogProduct product = (Catalog.CatalogProduct)Catalog.Items.get(Integer.valueOf(itemId));
/*  83: 80 */     if ((product == null) || (product.pageId != page.pageId)) {
/*  84: 81 */       return;
/*  85:    */     }
/*  86: 84 */     if ((product.creditCost > 0) || (wrappingCost > 0))
/*  87:    */     {
/*  88: 85 */       int finalCost = product.creditCost + wrappingCost;
/*  89: 86 */       if (Main.credits < finalCost)
/*  90:    */       {
/*  91: 87 */         QueueWriter.write(Main.socket, ErrorBuyComposer.compose(Boolean.valueOf(true), Boolean.valueOf(false), 0));
/*  92: 88 */         return;
/*  93:    */       }
/*  94: 91 */       Main.credits -= finalCost;
/*  95: 92 */       QueueWriter.write(Main.socket, CreditBalanceComposer.compose(Main.credits));
/*  96:    */     }
/*  97: 95 */     if (product.activityPointCost > 0)
/*  98:    */     {
/*  99: 96 */       int finalCost = product.activityPointCost;
/* 100: 97 */       if (product.activityPointsType == 105)
/* 101:    */       {
/* 102: 98 */         if (Main.diamondAmmount < finalCost)
/* 103:    */         {
/* 104: 99 */           QueueWriter.write(Main.socket, ErrorBuyComposer.compose(Boolean.valueOf(false), Boolean.valueOf(true), 105));
/* 105:100 */           return;
/* 106:    */         }
/* 107:103 */         Main.diamondAmmount -= finalCost;
/* 108:104 */         QueueWriter.write(Main.socket, HabboActivityPointNotificationComposer.compose(Main.diamondAmmount, 0, 105));
/* 109:    */       }
/* 110:    */       else
/* 111:    */       {
/* 112:106 */         if (Main.pixelAmmount < finalCost)
/* 113:    */         {
/* 114:107 */           QueueWriter.write(Main.socket, ErrorBuyComposer.compose(Boolean.valueOf(false), Boolean.valueOf(true), 0));
/* 115:108 */           return;
/* 116:    */         }
/* 117:111 */         Main.pixelAmmount -= finalCost;
/* 118:112 */         QueueWriter.write(Main.socket, HabboActivityPointNotificationComposer.compose(Main.pixelAmmount, 0, 0));
/* 119:    */       }
/* 120:    */     }
/* 121:118 */     Connection clientCn = plr.connection;
/* 122:    */     
/* 123:    */ 
/* 124:121 */     StuffDataWriter data = new StuffDataWriter(1);
/* 125:122 */     data.writeInt8(showAvatar ? 5 : 3);
/* 126:123 */     data.writeString("MESSAGE");
/* 127:124 */     data.writeString(text);
/* 128:125 */     data.writeString("PRODUCT_CODE");
/* 129:126 */     data.writeString(Integer.toString(product.productId));
/* 130:127 */     data.writeString("EXTRA_PARAM");
/* 131:128 */     data.writeString(extraParam);
/* 132:129 */     if (showAvatar)
/* 133:    */     {
/* 134:130 */       data.writeString("PURCHASER_NAME");
/* 135:131 */       data.writeString(Main.playerData.userName);
/* 136:132 */       data.writeString("PURCHASER_FIGURE");
/* 137:133 */       data.writeString(Main.playerData.avatarLook.toString());
/* 138:    */     }
/* 139:136 */     int refId = Server.generateRefItemId();
/* 140:    */     
/* 141:138 */     FloorItem userItem = new PresentItem();
/* 142:139 */     userItem.refId = refId;
/* 143:140 */     userItem.itemId = Server.generateItemId();
/* 144:141 */     userItem.baseItem = boxBaseItem;
/* 145:142 */     userItem.owner = plr;
/* 146:143 */     userItem.extraData = new MapStuffData(new StuffDataReader(data.getData()));
/* 147:144 */     userItem.setMysqlState(3);
/* 148:146 */     if (clientCn != null)
/* 149:    */     {
/* 150:147 */       clientCn.avatarData.UnseenItems.AddItem(1, refId);
/* 151:148 */       clientCn.inventoryAddFloorItem(userItem);
/* 152:    */     }
/* 153:151 */     if (clientCn != null)
/* 154:    */     {
/* 155:152 */       QueueWriter.write(clientCn.socket, FurniListUpdateComposer.compose());
/* 156:153 */       QueueWriter.write(clientCn.socket, UnseenItemsComposer.compose(clientCn.avatarData.UnseenItems));
/* 157:    */     }
/* 158:156 */     QueueWriter.write(Main.socket, BuyNotificationComposer.compose(product));
/* 159:    */   }
/* 160:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.catalog.PurchaseFromCatalogAsGiftParser
 * JD-Core Version:    0.7.0.1
 */