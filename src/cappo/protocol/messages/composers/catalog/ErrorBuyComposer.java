/*  1:   */ package cappo.protocol.messages.composers.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class ErrorBuyComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(Boolean isCredits, Boolean isActivityPoint, int activityPointType)
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(isCredits, ClientMessage);
/* 15:19 */     Composer.add(isActivityPoint, ClientMessage);
/* 16:20 */     if (isActivityPoint.booleanValue()) {
/* 17:21 */       Composer.add(Integer.valueOf(activityPointType), ClientMessage);
/* 18:   */     }
/* 19:23 */     Composer.endPacket(ClientMessage);
/* 20:24 */     return ClientMessage;
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.catalog.ErrorBuyComposer
 * JD-Core Version:    0.7.0.1
 */