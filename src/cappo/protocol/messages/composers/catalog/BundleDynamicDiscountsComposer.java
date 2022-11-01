/*  1:   */ package cappo.protocol.messages.composers.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class BundleDynamicDiscountsComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose()
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(Integer.valueOf(50), ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(5), ClientMessage);
/* 16:20 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 18:22 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 19:   */     
/* 20:   */ 
/* 21:   */ 
/* 22:26 */     Composer.endPacket(ClientMessage);
/* 23:27 */     return ClientMessage;
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.catalog.BundleDynamicDiscountsComposer
 * JD-Core Version:    0.7.0.1
 */