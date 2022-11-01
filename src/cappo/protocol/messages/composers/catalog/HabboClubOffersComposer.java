/*  1:   */ package cappo.protocol.messages.composers.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class HabboClubOffersComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose()
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 16:20 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 17:   */     
/* 18:   */ 
/* 19:   */ 
/* 20:24 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 21:   */     
/* 22:   */ 
/* 23:   */ 
/* 24:   */ 
/* 25:   */ 
/* 26:   */ 
/* 27:   */ 
/* 28:   */ 
/* 29:33 */     Composer.endPacket(ClientMessage);
/* 30:34 */     return ClientMessage;
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.catalog.HabboClubOffersComposer
 * JD-Core Version:    0.7.0.1
 */