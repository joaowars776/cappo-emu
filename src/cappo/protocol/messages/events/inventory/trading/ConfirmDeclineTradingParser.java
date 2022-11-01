/*  1:   */ package cappo.protocol.messages.events.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.game.inventory.trading.Trade;
/*  5:   */ import cappo.game.inventory.trading.TradeUser;
/*  6:   */ import cappo.game.player.PlayerData;
/*  7:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.inventory.trading.TradingCloseComposer;
/* 10:   */ import java.util.Map;
/* 11:   */ 
/* 12:   */ public class ConfirmDeclineTradingParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:18 */     Avatar avatar = Main.avatar;
/* 18:19 */     if (avatar == null) {
/* 19:20 */       return;
/* 20:   */     }
/* 21:23 */     Trade trade = (Trade)Trade.tradeMap.get(Integer.valueOf(Main.playerData.userId));
/* 22:24 */     if (trade == null) {
/* 23:25 */       return;
/* 24:   */     }
/* 25:28 */     if ((trade.ownerUser.status == 0) || (trade.guestUser.status == 0)) {
/* 26:29 */       return;
/* 27:   */     }
/* 28:32 */     trade.clean();
/* 29:33 */     trade.broadcast(TradingCloseComposer.compose(Main.playerData.userId, 0));
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.trading.ConfirmDeclineTradingParser
 * JD-Core Version:    0.7.0.1
 */