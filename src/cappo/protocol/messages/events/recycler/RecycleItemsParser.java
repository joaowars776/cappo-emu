/*  1:   */ package cappo.protocol.messages.events.recycler;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.MessageWriter;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.collections.BaseItem;
/*  8:   */ import cappo.game.collections.Utils;
/*  9:   */ import cappo.game.player.data.AvatarData;
/* 10:   */ import cappo.game.player.inventory.PlayerInventory;
/* 11:   */ import cappo.game.roomengine.entity.item.Item;
/* 12:   */ import cappo.protocol.messages.Composer;
/* 13:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 14:   */ 
/* 15:   */ public class RecycleItemsParser
/* 16:   */   extends IncomingMessageEvent
/* 17:   */ {
/* 18:   */   public void messageReceived(Connection Main)
/* 19:   */   {
/* 20:20 */     if (Utils.getTimestamp() < Main.avatarData.EcotronNextTime) {
/* 21:21 */       return;
/* 22:   */     }
/* 23:24 */     int count = Main.currentPacket.readInt();
/* 24:26 */     if (count != 5) {
/* 25:28 */       return;
/* 26:   */     }
/* 27:31 */     for (int i = 0; i < count; i++)
/* 28:   */     {
/* 29:32 */       Item Item = Main.inventory.getFurni(Main.currentPacket.readInt());
/* 30:34 */       if ((Item == null) || (!Item.baseItem.AllowRecycle)) {
/* 31:36 */         return;
/* 32:   */       }
/* 33:39 */       Main.inventoryRemoveItem(Item.itemId, Item.baseItem.Type.equals("i"));
/* 34:   */     }
/* 35:44 */     MessageWriter ClientMessage = new MessageWriter();
/* 36:45 */     Composer.initPacket(508, ClientMessage);
/* 37:46 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 38:47 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 39:48 */     QueueWriter.write(Main.socket, ClientMessage);
/* 40:   */     
/* 41:50 */     Main.avatarData.EcotronNextTime = (Utils.getTimestamp() + 300L);
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.recycler.RecycleItemsParser
 * JD-Core Version:    0.7.0.1
 */