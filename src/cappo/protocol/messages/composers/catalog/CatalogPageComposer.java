/*  1:   */ package cappo.protocol.messages.composers.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.catalog.Catalog.CatalogPage;
/*  5:   */ import cappo.game.catalog.Catalog.CatalogProduct;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import cappo.protocol.messages.composers.serializers.CatalogPageMessageOfferData;
/*  8:   */ import cappo.protocol.messages.composers.serializers.SerializeCatalogPageData;
/*  9:   */ import java.util.List;
/* 10:   */ 
/* 11:   */ public class CatalogPageComposer
/* 12:   */ {
/* 13:   */   public static int HEADER;
/* 14:   */   
/* 15:   */   public static final MessageWriter compose(Catalog.CatalogPage Page, int offerId, String catalogType)
/* 16:   */   {
/* 17:20 */     MessageWriter ClientMessage = new MessageWriter(50000);
/* 18:21 */     Composer.initPacket(HEADER, ClientMessage);
/* 19:22 */     Composer.add(Integer.valueOf(Page.pageId), ClientMessage);
/* 20:23 */     Composer.add(catalogType, ClientMessage);
/* 21:24 */     Composer.add(Page.Layout, ClientMessage);
/* 22:25 */     SerializeCatalogPageData.parse(ClientMessage, Page.PageData);
/* 23:26 */     Composer.add(Integer.valueOf(Page.items.size()), ClientMessage);
/* 24:27 */     for (Catalog.CatalogProduct item : Page.items) {
/* 25:28 */       CatalogPageMessageOfferData.parse(ClientMessage, item);
/* 26:   */     }
/* 27:30 */     Composer.add(Integer.valueOf(offerId), ClientMessage);
/* 28:31 */     Composer.add(Boolean.valueOf(Page.acceptSeasonCurrencyAsCredits), ClientMessage);
/* 29:32 */     Composer.endPacket(ClientMessage);
/* 30:33 */     return ClientMessage;
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.catalog.CatalogPageComposer
 * JD-Core Version:    0.7.0.1
 */