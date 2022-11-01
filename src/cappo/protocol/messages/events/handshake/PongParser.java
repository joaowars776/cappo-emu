/*  1:   */ package cappo.protocol.messages.events.handshake;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  5:   */ 
/*  6:   */ public class PongParser
/*  7:   */   extends IncomingMessageEvent
/*  8:   */ {
/*  9:   */   public void messageReceived(Connection Main)
/* 10:   */   {
/* 11:16 */     Main.setFlag(2, true);
/* 12:   */   }
/* 13:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.handshake.PongParser
 * JD-Core Version:    0.7.0.1
 */