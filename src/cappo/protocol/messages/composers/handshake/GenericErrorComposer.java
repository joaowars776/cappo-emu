/*  1:   */ package cappo.protocol.messages.composers.handshake;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class GenericErrorComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   public static final int ROOM_CANT_SET_NOT_OWNER = -32000;
/* 10:   */   public static final int ROOM_KICKED = 4008;
/* 11:   */   public static final int ROOM_UNUSED_YET = -13001;
/* 12:   */   public static final int NAVIGATOR_WRONG_PASSWORD = -100002;
/* 13:   */   public static final int NAVIGATOR_NEED_TO_BE_VIP = 4009;
/* 14:   */   public static final int NAVIGATOR_INVALID_ROOM_NAME = 4010;
/* 15:   */   public static final int LOGIN_AUTH_FAILED = -3;
/* 16:   */   public static final int LOGIN_CONNECT_FAILED = -400;
/* 17:   */   
/* 18:   */   public static final MessageWriter compose(int ErrorCode)
/* 19:   */   {
/* 20:27 */     MessageWriter ClientMessage = new MessageWriter();
/* 21:28 */     Composer.initPacket(HEADER, ClientMessage);
/* 22:29 */     Composer.add(Integer.valueOf(ErrorCode), ClientMessage);
/* 23:30 */     Composer.endPacket(ClientMessage);
/* 24:31 */     return ClientMessage;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.handshake.GenericErrorComposer
 * JD-Core Version:    0.7.0.1
 */