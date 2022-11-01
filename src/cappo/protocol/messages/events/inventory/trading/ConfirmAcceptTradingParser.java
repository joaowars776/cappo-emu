/*  1:   */ package cappo.protocol.messages.events.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.Database;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.inventory.trading.Trade;
/*  6:   */ import cappo.game.inventory.trading.TradeUser;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.roomengine.entity.item.Item;
/*  9:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/* 10:   */ import cappo.game.roomengine.entity.item.wall.WallItem;
/* 11:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ import cappo.protocol.messages.composers.inventory.trading.TradingCompletedComposer;
/* 14:   */ import java.util.Map;
/* 15:   */ 
/* 16:   */ public class ConfirmAcceptTradingParser
/* 17:   */   extends IncomingMessageEvent
/* 18:   */ {
/* 19:   */   public void messageReceived(Connection Main)
/* 20:   */   {
/* 21:24 */     Avatar avatar = Main.avatar;
/* 22:25 */     if (avatar == null) {
/* 23:26 */       return;
/* 24:   */     }
/* 25:29 */     PlayerData playerData = Main.getPlayerData();
/* 26:   */     
/* 27:31 */     Trade trade = (Trade)Trade.tradeMap.get(Integer.valueOf(playerData.userId));
/* 28:32 */     if (trade == null) {
/* 29:33 */       return;
/* 30:   */     }
/* 31:36 */     TradeUser user = trade.guestUser;
/* 32:37 */     if (trade.ownerUser.userId == playerData.userId) {
/* 33:38 */       user = trade.ownerUser;
/* 34:   */     }
/* 35:41 */     if (user.status != 1) {
/* 36:42 */       return;
/* 37:   */     }
/* 38:45 */     user.status = 2;
/* 39:47 */     if (trade.guestUser.status == trade.ownerUser.status)
/* 40:   */     {
/* 41:48 */       if (!trade.ownerUser.furnis.isEmpty())
/* 42:   */       {
/* 43:49 */         PlayerData newPlayerData = trade.guestUser.connection.getPlayerData();
/* 44:50 */         for (Item furni : trade.ownerUser.furnis.values())
/* 45:   */         {
/* 46:51 */           Database.exec("UPDATE furnis SET userid=" + trade.guestUser.userId + " WHERE id=" + furni.itemId + ";", new Object[0]);
/* 47:53 */           if ((furni instanceof FloorItem))
/* 48:   */           {
/* 49:54 */             trade.ownerUser.connection.inventoryRemoveItem(furni.itemId, false);
/* 50:   */             
/* 51:56 */             FloorItem floorItem = (FloorItem)furni;
/* 52:57 */             floorItem.owner = newPlayerData;
/* 53:58 */             floorItem.setMysqlState(1);
/* 54:59 */             trade.guestUser.connection.inventoryAddFloorItem(floorItem);
/* 55:   */           }
/* 56:   */           else
/* 57:   */           {
/* 58:61 */             trade.ownerUser.connection.inventoryRemoveItem(furni.itemId, true);
/* 59:   */             
/* 60:63 */             WallItem wallItem = (WallItem)furni;
/* 61:64 */             wallItem.owner = newPlayerData;
/* 62:65 */             wallItem.setMysqlState(1);
/* 63:66 */             trade.guestUser.connection.inventoryAddWallItem(wallItem);
/* 64:   */           }
/* 65:   */         }
/* 66:69 */         trade.ownerUser.furnis.clear();
/* 67:   */       }
/* 68:72 */       if (!trade.guestUser.furnis.isEmpty())
/* 69:   */       {
/* 70:73 */         PlayerData newPlayerData = trade.ownerUser.connection.getPlayerData();
/* 71:74 */         for (Item furni : trade.guestUser.furnis.values())
/* 72:   */         {
/* 73:75 */           Database.exec("UPDATE furnis SET userid=" + trade.ownerUser.userId + " WHERE id=" + furni.itemId + ";", new Object[0]);
/* 74:77 */           if ((furni instanceof FloorItem))
/* 75:   */           {
/* 76:78 */             trade.guestUser.connection.inventoryRemoveItem(furni.itemId, false);
/* 77:   */             
/* 78:80 */             FloorItem floorItem = (FloorItem)furni;
/* 79:81 */             floorItem.owner = newPlayerData;
/* 80:82 */             floorItem.setMysqlState(1);
/* 81:83 */             trade.ownerUser.connection.inventoryAddFloorItem(floorItem);
/* 82:   */           }
/* 83:   */           else
/* 84:   */           {
/* 85:85 */             trade.guestUser.connection.inventoryRemoveItem(furni.itemId, true);
/* 86:   */             
/* 87:87 */             WallItem wallItem = (WallItem)furni;
/* 88:88 */             wallItem.owner = newPlayerData;
/* 89:89 */             wallItem.setMysqlState(1);
/* 90:90 */             trade.ownerUser.connection.inventoryAddWallItem(wallItem);
/* 91:   */           }
/* 92:   */         }
/* 93:93 */         trade.guestUser.furnis.clear();
/* 94:   */       }
/* 95:96 */       trade.clean();
/* 96:97 */       trade.broadcast(TradingCompletedComposer.compose());
/* 97:   */     }
/* 98:   */   }
/* 99:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.trading.ConfirmAcceptTradingParser
 * JD-Core Version:    0.7.0.1
 */