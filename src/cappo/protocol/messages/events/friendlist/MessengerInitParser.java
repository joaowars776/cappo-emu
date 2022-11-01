/*  1:   */ package cappo.protocol.messages.events.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.logging.Log;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.messenger.PlayerMessenger;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.friendlist.MessengerInitComposer;
/* 11:   */ 
/* 12:   */ public class MessengerInitParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection cn)
/* 16:   */   {
/* 17:19 */     if (cn.playerData == null) {
/* 18:20 */       return;
/* 19:   */     }
/* 20:23 */     DBResult result = new DBResult();
/* 21:   */     try
/* 22:   */     {
/* 23:25 */       cn.playerData.messenger.initMessenger(result);
/* 24:26 */       QueueWriter.write(cn.socket, MessengerInitComposer.compose(cn.playerData.messenger));
/* 25:   */     }
/* 26:   */     catch (Exception ex)
/* 27:   */     {
/* 28:28 */       Log.printException("MessengerInitParser-1", ex);
/* 29:   */     }
/* 30:30 */     result.close();
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.friendlist.MessengerInitParser
 * JD-Core Version:    0.7.0.1
 */