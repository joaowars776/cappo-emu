/*  1:   */ package cappo.protocol.messages.composers.guides;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class UpdateGuideToolComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(boolean onDuty, int helpers, int guardians)
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(Boolean.valueOf(onDuty), ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(helpers), ClientMessage);
/* 16:20 */     Composer.add(Integer.valueOf(guardians), ClientMessage);
/* 17:21 */     Composer.endPacket(ClientMessage);
/* 18:22 */     return ClientMessage;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.guides.UpdateGuideToolComposer
 * JD-Core Version:    0.7.0.1
 */