/*  1:   */ package cappo.protocol.messages.composers.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class InstantMessageErrorComposer
/*  7:   */ {
/*  8:   */   public static final int ERROR_RECEIVER_MUTED = 3;
/*  9:   */   public static final int ERROR_SENDER_MUTED = 4;
/* 10:   */   public static final int ERROR_SENDER_OFFLINE = 5;
/* 11:   */   public static final int ERROR_SENDER_NOTFRIEND = 6;
/* 12:   */   public static final int ERROR_SENDER_BUSY = 7;
/* 13:   */   public static final int ERROR_SENDER_OFFLINE_FAILED = 10;
/* 14:   */   public static int HEADER;
/* 15:   */   
/* 16:   */   public static final MessageWriter compose(int errorCode, int userId, String errorMessage)
/* 17:   */   {
/* 18:23 */     MessageWriter ClientMessage = new MessageWriter();
/* 19:24 */     Composer.initPacket(HEADER, ClientMessage);
/* 20:25 */     Composer.add(Integer.valueOf(errorCode), ClientMessage);
/* 21:26 */     Composer.add(Integer.valueOf(userId), ClientMessage);
/* 22:27 */     Composer.add(errorMessage, ClientMessage);
/* 23:28 */     Composer.endPacket(ClientMessage);
/* 24:29 */     return ClientMessage;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.friendlist.InstantMessageErrorComposer
 * JD-Core Version:    0.7.0.1
 */