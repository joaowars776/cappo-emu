/*  1:   */ package cappo.protocol.messages.composers.landing;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.catalog.Catalog.CatalogProduct;
/*  5:   */ import cappo.game.catalog.LimitedItems;
/*  6:   */ import cappo.game.collections.Utils;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ 
/*  9:   */ public class NextLimitedAvailableComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose()
/* 14:   */   {
/* 15:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 16:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */     if (LimitedItems.nextLtd == null)
/* 18:   */     {
/* 19:21 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 20:22 */       Composer.add(Integer.valueOf(-1), ClientMessage);
/* 21:23 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 22:24 */       Composer.add("", ClientMessage);
/* 23:   */     }
/* 24:   */     else
/* 25:   */     {
/* 26:26 */       long now = Utils.getTimestamp();
/* 27:27 */       if (LimitedItems.nextLtd.startTime > now)
/* 28:   */       {
/* 29:28 */         Composer.add(Long.valueOf(LimitedItems.nextLtd.startTime - now), ClientMessage);
/* 30:29 */         Composer.add(Integer.valueOf(-1), ClientMessage);
/* 31:   */       }
/* 32:   */       else
/* 33:   */       {
/* 34:31 */         Composer.add(Integer.valueOf(0), ClientMessage);
/* 35:32 */         Composer.add(Integer.valueOf(LimitedItems.nextLtd.product.pageId), ClientMessage);
/* 36:   */       }
/* 37:34 */       Composer.add(Integer.valueOf(LimitedItems.nextLtd.product.productId), ClientMessage);
/* 38:35 */       Composer.add(LimitedItems.nextLtd.product.itemName, ClientMessage);
/* 39:   */     }
/* 40:37 */     Composer.endPacket(ClientMessage);
/* 41:38 */     return ClientMessage;
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.landing.NextLimitedAvailableComposer
 * JD-Core Version:    0.7.0.1
 */