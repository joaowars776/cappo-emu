/*  1:   */ package cappo.protocol.messages.events.help;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.moderation.UserMuted;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.help.CallForHelpMutedComposer;
/*  8:   */ import cappo.protocol.messages.composers.help.CallForHelpOpenComposer;
/*  9:   */ 
/* 10:   */ public class CallForHelpOpenParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection cn)
/* 14:   */   {
/* 15:18 */     if (cn.userMuted != null)
/* 16:   */     {
/* 17:19 */       if (cn.userMuted.isMuted())
/* 18:   */       {
/* 19:20 */         QueueWriter.write(cn.socket, CallForHelpMutedComposer.compose(cn.userMuted.reason));
/* 20:21 */         return;
/* 21:   */       }
/* 22:23 */       cn.userMuted = null;
/* 23:   */     }
/* 24:25 */     QueueWriter.write(cn.socket, CallForHelpOpenComposer.compose());
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.help.CallForHelpOpenParser
 * JD-Core Version:    0.7.0.1
 */