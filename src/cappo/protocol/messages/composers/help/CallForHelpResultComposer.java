/*  1:   */ package cappo.protocol.messages.composers.help;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class CallForHelpResultComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   public static final int SENT_OK = 0;
/* 10:   */   public static final int ERROR_TOO_MANY_PENDING = 1;
/* 11:   */   public static final int HAS_ABUSIVE_CALL = 2;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(int result)
/* 14:   */   {
/* 15:20 */     MessageWriter ClientMessage = new MessageWriter();
/* 16:21 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:22 */     Composer.add(Integer.valueOf(result), ClientMessage);
/* 18:23 */     Composer.endPacket(ClientMessage);
/* 19:24 */     return ClientMessage;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.help.CallForHelpResultComposer
 * JD-Core Version:    0.7.0.1
 */