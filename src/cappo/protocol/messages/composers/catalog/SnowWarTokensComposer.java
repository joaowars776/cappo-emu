/*  1:   */ package cappo.protocol.messages.composers.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class SnowWarTokensComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   private static MessageWriter ClientMessage;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose()
/* 12:   */   {
/* 13:17 */     if (ClientMessage == null)
/* 14:   */     {
/* 15:18 */       ClientMessage = new MessageWriter();
/* 16:19 */       Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */       Composer.add(Integer.valueOf(-1), ClientMessage);
/* 18:21 */       Composer.add("GET_SNOWWAR_TOKENS", ClientMessage);
/* 19:22 */       Composer.add(Integer.valueOf(1), ClientMessage);
/* 20:23 */       Composer.endPacket(ClientMessage);
/* 21:   */     }
/* 22:25 */     return ClientMessage;
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.catalog.SnowWarTokensComposer
 * JD-Core Version:    0.7.0.1
 */