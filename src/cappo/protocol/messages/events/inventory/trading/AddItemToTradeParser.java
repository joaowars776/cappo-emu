/*  1:   */ package cappo.protocol.messages.events.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.inventory.trading.Trade;
/*  6:   */ import cappo.game.inventory.trading.TradeUser;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.inventory.PlayerInventory;
/*  9:   */ import cappo.game.roomengine.entity.item.Item;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.inventory.trading.TradingAcceptComposer;
/* 13:   */ import cappo.protocol.messages.composers.inventory.trading.TradingItemListComposer;
/* 14:   */ import java.util.Map;
/* 15:   */ 
/* 16:   */ public class AddItemToTradeParser
/* 17:   */   extends IncomingMessageEvent
/* 18:   */ {
/* 19:   */   public void messageReceived(Connection Main)
/* 20:   */   {
/* 21:22 */     Avatar avatar = Main.avatar;
/* 22:23 */     if (avatar == null) {
/* 23:24 */       return;
/* 24:   */     }
/* 25:27 */     PlayerData playerData = Main.getPlayerData();
/* 26:   */     
/* 27:29 */     Trade trade = (Trade)Trade.tradeMap.get(Integer.valueOf(playerData.userId));
/* 28:30 */     if (trade == null) {
/* 29:31 */       return;
/* 30:   */     }
/* 31:34 */     TradeUser user = trade.guestUser;
/* 32:35 */     if (trade.ownerUser.userId == playerData.userId) {
/* 33:36 */       user = trade.ownerUser;
/* 34:   */     }
/* 35:39 */     if (user.status != 0) {
/* 36:40 */       return;
/* 37:   */     }
/* 38:43 */     Item item = Main.inventory.getFurni(Main.currentPacket.readInt());
/* 39:44 */     if (item == null) {
/* 40:45 */       return;
/* 41:   */     }
/* 42:50 */     if (user.furnis.containsKey(Integer.valueOf(item.itemId))) {
/* 43:51 */       return;
/* 44:   */     }
/* 45:54 */     user.furnis.put(Integer.valueOf(item.itemId), item);
/* 46:   */     
/* 47:56 */     trade.broadcast(TradingItemListComposer.compose(trade.ownerUser, trade.guestUser));
/* 48:58 */     if (trade.ownerUser.status != 0)
/* 49:   */     {
/* 50:59 */       trade.ownerUser.status = 0;
/* 51:60 */       trade.broadcast(TradingAcceptComposer.compose(trade.ownerUser.userId, 0));
/* 52:   */     }
/* 53:63 */     if (trade.guestUser.status != 0)
/* 54:   */     {
/* 55:64 */       trade.guestUser.status = 0;
/* 56:65 */       trade.broadcast(TradingAcceptComposer.compose(trade.guestUser.userId, 0));
/* 57:   */     }
/* 58:   */   }
/* 59:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.trading.AddItemToTradeParser
 * JD-Core Version:    0.7.0.1
 */