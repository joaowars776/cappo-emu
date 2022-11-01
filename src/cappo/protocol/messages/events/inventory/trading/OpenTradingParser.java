/*  1:   */ package cappo.protocol.messages.events.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.inventory.trading.Trade;
/*  8:   */ import cappo.game.inventory.trading.TradeUser;
/*  9:   */ import cappo.game.player.PlayerData;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.inventory.trading.TradingAlreadyOpenComposer;
/* 13:   */ import cappo.protocol.messages.composers.inventory.trading.TradingOpenComposer;
/* 14:   */ import java.util.Map;
/* 15:   */ 
/* 16:   */ public class OpenTradingParser
/* 17:   */   extends IncomingMessageEvent
/* 18:   */ {
/* 19:   */   public void messageReceived(Connection Main)
/* 20:   */   {
/* 21:21 */     Avatar avatar = Main.avatar;
/* 22:22 */     if (avatar == null) {
/* 23:23 */       return;
/* 24:   */     }
/* 25:26 */     if (Trade.tradeMap.containsKey(Integer.valueOf(Main.playerData.userId)))
/* 26:   */     {
/* 27:27 */       QueueWriter.write(Main.socket, TradingAlreadyOpenComposer.compose(7, ""));
/* 28:28 */       return;
/* 29:   */     }
/* 30:31 */     Avatar User = avatar.room.getRoomUserByVirtualId(Main.currentPacket.readInt());
/* 31:33 */     if (User == null) {
/* 32:34 */       return;
/* 33:   */     }
/* 34:37 */     if (Trade.tradeMap.containsKey(Integer.valueOf(User.cn.playerData.userId)))
/* 35:   */     {
/* 36:38 */       QueueWriter.write(Main.socket, TradingAlreadyOpenComposer.compose(8, User.cn.playerData.userName));
/* 37:39 */       return;
/* 38:   */     }
/* 39:42 */     if (User.cn.haveFlag(8))
/* 40:   */     {
/* 41:43 */       QueueWriter.write(Main.socket, TradingAlreadyOpenComposer.compose(2, User.cn.playerData.userName));
/* 42:44 */       return;
/* 43:   */     }
/* 44:47 */     avatar.setStatus("trd", "");
/* 45:48 */     User.setStatus("trd", "");
/* 46:   */     
/* 47:50 */     Trade trade = new Trade(Main, User.cn);
/* 48:51 */     trade.broadcast(TradingOpenComposer.compose(trade.ownerUser.userId, trade.guestUser.userId));
/* 49:   */   }
/* 50:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.trading.OpenTradingParser
 * JD-Core Version:    0.7.0.1
 */