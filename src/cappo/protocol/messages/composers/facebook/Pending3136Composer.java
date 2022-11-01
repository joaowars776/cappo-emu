/*  1:   */ package cappo.protocol.messages.composers.facebook;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class Pending3136Composer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose()
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add("", ClientMessage);
/* 15:19 */     Composer.add("", ClientMessage);
/* 16:20 */     Composer.add("", ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 18:22 */     Composer.endPacket(ClientMessage);
/* 19:23 */     return ClientMessage;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.facebook.Pending3136Composer
 * JD-Core Version:    0.7.0.1
 */