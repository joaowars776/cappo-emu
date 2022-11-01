/*  1:   */ package cappo.protocol.messages.composers.availability;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class Pending2850Composer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose()
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 16:20 */     Composer.endPacket(ClientMessage);
/* 17:21 */     return ClientMessage;
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.availability.Pending2850Composer
 * JD-Core Version:    0.7.0.1
 */