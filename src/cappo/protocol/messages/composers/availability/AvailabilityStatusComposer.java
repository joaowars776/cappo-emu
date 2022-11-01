/*  1:   */ package cappo.protocol.messages.composers.availability;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class AvailabilityStatusComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   private static MessageWriter ClientMessage;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose()
/* 12:   */   {
/* 13:17 */     if (ClientMessage == null)
/* 14:   */     {
/* 15:18 */       ClientMessage = new MessageWriter(8);
/* 16:19 */       Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */       Composer.add(Boolean.valueOf(true), ClientMessage);
/* 18:21 */       Composer.add(Boolean.valueOf(false), ClientMessage);
/* 19:22 */       Composer.endPacket(ClientMessage);
/* 20:   */     }
/* 21:24 */     return ClientMessage;
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.availability.AvailabilityStatusComposer
 * JD-Core Version:    0.7.0.1
 */