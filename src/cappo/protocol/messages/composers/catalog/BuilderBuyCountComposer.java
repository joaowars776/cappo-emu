/*  1:   */ package cappo.protocol.messages.composers.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class BuilderBuyCountComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose()
/* 11:   */   {
/* 12:18 */     MessageWriter message = new MessageWriter();
/* 13:19 */     Composer.initPacket(HEADER, message);
/* 14:20 */     Composer.writeInt32(0, message);
/* 15:21 */     Composer.endPacket(message);
/* 16:22 */     return message;
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.catalog.BuilderBuyCountComposer
 * JD-Core Version:    0.7.0.1
 */