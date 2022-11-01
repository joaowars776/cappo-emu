/*  1:   */ package cappo.protocol.messages.events.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.catalog.Catalog.CatalogProduct;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.roomengine.entity.item.floor.PresentItem;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ import cappo.protocol.messages.events.catalog.PurchaseFromCatalogParser;
/* 12:   */ 
/* 13:   */ public class PresentOpenParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection Main)
/* 17:   */   {
/* 18:20 */     Avatar avatar = Main.avatar;
/* 19:21 */     if (avatar == null) {
/* 20:22 */       return;
/* 21:   */     }
/* 22:25 */     PresentItem gift = (PresentItem)avatar.room.getFloorItem(Main.currentPacket.readInt());
/* 23:26 */     if (gift == null) {
/* 24:27 */       return;
/* 25:   */     }
/* 26:30 */     avatar.room.removeFloorItem(gift, Main.playerData.userId);
/* 27:31 */     gift.setMysqlState(4);
/* 28:   */     
/* 29:33 */     Catalog.CatalogProduct giveItem = gift.getProduct();
/* 30:34 */     if (giveItem == null) {
/* 31:35 */       return;
/* 32:   */     }
/* 33:38 */     PurchaseFromCatalogParser.buyProduct(giveItem, gift.getProductParam(), 1, Main);
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.furniture.PresentOpenParser
 * JD-Core Version:    0.7.0.1
 */