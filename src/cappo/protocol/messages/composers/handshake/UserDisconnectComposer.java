/*  1:   */ package cappo.protocol.messages.composers.handshake;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class UserDisconnectComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(int ErrorCode)
/* 11:   */   {
/* 12:22 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:23 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:24 */     Composer.add(Integer.valueOf(ErrorCode), ClientMessage);
/* 15:25 */     Composer.endPacket(ClientMessage);
/* 16:26 */     return ClientMessage;
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.handshake.UserDisconnectComposer
 * JD-Core Version:    0.7.0.1
 */