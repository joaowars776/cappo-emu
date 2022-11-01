/*  1:   */ package cappo.protocol.messages.events.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.game.inventory.trading.Trade;
/*  5:   */ import cappo.game.player.PlayerData;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.inventory.trading.TradingCloseComposer;
/*  8:   */ import java.util.Map;
/*  9:   */ 
/* 10:   */ public class CloseTradingParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection Main)
/* 14:   */   {
/* 15:17 */     Trade trade = (Trade)Trade.tradeMap.get(Integer.valueOf(Main.playerData.userId));
/* 16:18 */     if (trade == null) {
/* 17:19 */       return;
/* 18:   */     }
/* 19:22 */     trade.clean();
/* 20:23 */     trade.broadcast(TradingCloseComposer.compose(Main.playerData.userId, 0));
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.trading.CloseTradingParser
 * JD-Core Version:    0.7.0.1
 */