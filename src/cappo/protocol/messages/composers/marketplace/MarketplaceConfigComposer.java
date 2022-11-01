/*  1:   */ package cappo.protocol.messages.composers.marketplace;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class MarketplaceConfigComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   private static MessageWriter ClientMessage;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose()
/* 12:   */   {
/* 13:17 */     if (ClientMessage == null)
/* 14:   */     {
/* 15:18 */       ClientMessage = new MessageWriter(50);
/* 16:19 */       Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */       Composer.add(Boolean.valueOf(true), ClientMessage);
/* 18:21 */       Composer.add(Integer.valueOf(1), ClientMessage);
/* 19:22 */       Composer.add(Integer.valueOf(1), ClientMessage);
/* 20:23 */       Composer.add(Integer.valueOf(5), ClientMessage);
/* 21:24 */       Composer.add(Integer.valueOf(1), ClientMessage);
/* 22:25 */       Composer.add(Integer.valueOf(10000), ClientMessage);
/* 23:26 */       Composer.add(Integer.valueOf(48), ClientMessage);
/* 24:27 */       Composer.add(Integer.valueOf(7), ClientMessage);
/* 25:28 */       Composer.endPacket(ClientMessage);
/* 26:   */     }
/* 27:30 */     return ClientMessage;
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.marketplace.MarketplaceConfigComposer
 * JD-Core Version:    0.7.0.1
 */