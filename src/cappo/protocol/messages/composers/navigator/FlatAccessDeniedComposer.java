/*  1:   */ package cappo.protocol.messages.composers.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class FlatAccessDeniedComposer
/*  7:   */ {
/*  8:   */   public static final int GUEST_ROOM_FULL = 1;
/*  9:   */   public static final int UNKNOWN = 2;
/* 10:   */   public static final int CUSTOM_ERROR = 3;
/* 11:   */   public static final int BANNED = 4;
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static final MessageWriter compose(int reason, String errorCode)
/* 15:   */   {
/* 16:21 */     MessageWriter ClientMessage = new MessageWriter();
/* 17:22 */     Composer.initPacket(HEADER, ClientMessage);
/* 18:23 */     Composer.add(Integer.valueOf(reason), ClientMessage);
/* 19:24 */     if (reason == 3) {
/* 20:25 */       Composer.add(errorCode, ClientMessage);
/* 21:   */     }
/* 22:27 */     Composer.endPacket(ClientMessage);
/* 23:28 */     return ClientMessage;
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.navigator.FlatAccessDeniedComposer
 * JD-Core Version:    0.7.0.1
 */