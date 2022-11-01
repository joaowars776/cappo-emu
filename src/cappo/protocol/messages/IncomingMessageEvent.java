/*  1:   */ package cappo.protocol.messages;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ 
/*  5:   */ public abstract class IncomingMessageEvent
/*  6:   */ {
/*  7:   */   public int HEADER;
/*  8:14 */   public static final IncomingMessageEvent[] callBacks = new IncomingMessageEvent[5000];
/*  9:   */   
/* 10:   */   public abstract void messageReceived(Connection paramConnection)
/* 11:   */     throws Exception;
/* 12:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.IncomingMessageEvent
 * JD-Core Version:    0.7.0.1
 */