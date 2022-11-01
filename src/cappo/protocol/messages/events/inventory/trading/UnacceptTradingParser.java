/*  1:   */ package cappo.protocol.messages.events.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.inventory.trading.Trade;
/*  7:   */ import cappo.game.inventory.trading.TradeUser;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ import cappo.protocol.messages.composers.inventory.trading.TradingAcceptComposer;
/* 12:   */ import java.util.Map;
/* 13:   */ 
/* 14:   */ public class UnacceptTradingParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:21 */     Avatar avatar = Main.avatar;
/* 20:22 */     if (avatar == null) {
/* 21:23 */       return;
/* 22:   */     }
/* 23:26 */     Trade trade = (Trade)Trade.tradeMap.get(Integer.valueOf(Main.playerData.userId));
/* 24:27 */     if (trade == null) {
/* 25:28 */       return;
/* 26:   */     }
/* 27:31 */     TradeUser user = trade.guestUser;
/* 28:32 */     if (trade.ownerUser.userId == Main.playerData.userId) {
/* 29:33 */       user = trade.ownerUser;
/* 30:   */     }
/* 31:36 */     if (user.status != 1) {
/* 32:37 */       return;
/* 33:   */     }
/* 34:39 */     user.status = 0;
/* 35:   */     
/* 36:41 */     MessageWriter Message = TradingAcceptComposer.compose(Main.playerData.userId, 0);
/* 37:42 */     QueueWriter.writeAndFlush(trade.ownerUser.connection.socket, Message);
/* 38:43 */     QueueWriter.writeAndFlush(trade.guestUser.connection.socket, Message);
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.trading.UnacceptTradingParser
 * JD-Core Version:    0.7.0.1
 */