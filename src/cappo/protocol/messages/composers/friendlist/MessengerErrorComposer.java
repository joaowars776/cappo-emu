/*  1:   */ package cappo.protocol.messages.composers.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class MessengerErrorComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(int MsgId, int ErrorCode)
/* 11:   */   {
/* 12:25 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:26 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:27 */     Composer.add(Integer.valueOf(MsgId), ClientMessage);
/* 15:28 */     Composer.add(Integer.valueOf(ErrorCode), ClientMessage);
/* 16:29 */     Composer.endPacket(ClientMessage);
/* 17:30 */     return ClientMessage;
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.friendlist.MessengerErrorComposer
 * JD-Core Version:    0.7.0.1
 */