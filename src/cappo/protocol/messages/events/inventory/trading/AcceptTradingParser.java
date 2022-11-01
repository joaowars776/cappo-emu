/*  1:   */ package cappo.protocol.messages.events.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.game.inventory.trading.Trade;
/*  5:   */ import cappo.game.inventory.trading.TradeUser;
/*  6:   */ import cappo.game.player.PlayerData;
/*  7:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.inventory.trading.TradingAcceptComposer;
/* 10:   */ import cappo.protocol.messages.composers.inventory.trading.TradingConfirmationComposer;
/* 11:   */ import java.util.Map;
/* 12:   */ 
/* 13:   */ public class AcceptTradingParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection Main)
/* 17:   */   {
/* 18:21 */     Avatar avatar = Main.avatar;
/* 19:22 */     if (avatar == null) {
/* 20:23 */       return;
/* 21:   */     }
/* 22:26 */     PlayerData playerData = Main.getPlayerData();
/* 23:   */     
/* 24:28 */     Trade trade = (Trade)Trade.tradeMap.get(Integer.valueOf(playerData.userId));
/* 25:29 */     if (trade == null) {
/* 26:30 */       return;
/* 27:   */     }
/* 28:33 */     TradeUser user = trade.guestUser;
/* 29:34 */     if (trade.ownerUser.userId == playerData.userId) {
/* 30:35 */       user = trade.ownerUser;
/* 31:   */     }
/* 32:38 */     if (user.status != 0) {
/* 33:39 */       return;
/* 34:   */     }
/* 35:42 */     user.status = 1;
/* 36:   */     
/* 37:44 */     trade.broadcast(TradingAcceptComposer.compose(playerData.userId, user.status));
/* 38:45 */     if (trade.guestUser.status == trade.ownerUser.status) {
/* 39:46 */       trade.broadcast(TradingConfirmationComposer.compose());
/* 40:   */     }
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.trading.AcceptTradingParser
 * JD-Core Version:    0.7.0.1
 */