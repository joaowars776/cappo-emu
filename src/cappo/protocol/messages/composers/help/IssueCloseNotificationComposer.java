/*  1:   */ package cappo.protocol.messages.composers.help;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class IssueCloseNotificationComposer
/*  7:   */ {
/*  8:   */   public static final int RESOLVED = 0;
/*  9:   */   public static final int USELESS = 1;
/* 10:   */   public static final int ABUSIVE = 2;
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(int reason)
/* 14:   */   {
/* 15:20 */     MessageWriter ClientMessage = new MessageWriter();
/* 16:21 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:22 */     Composer.add(Integer.valueOf(reason), ClientMessage);
/* 18:23 */     Composer.endPacket(ClientMessage);
/* 19:24 */     return ClientMessage;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.help.IssueCloseNotificationComposer
 * JD-Core Version:    0.7.0.1
 */