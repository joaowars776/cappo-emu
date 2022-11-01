/*  1:   */ package cappo.protocol.messages.composers.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.catalog.Catalog.CatalogProduct;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.CatalogPageMessageOfferData;
/*  7:   */ 
/*  8:   */ public class UniqueLimitedItemComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(Catalog.CatalogProduct item)
/* 13:   */   {
/* 14:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:20 */     CatalogPageMessageOfferData.parse(ClientMessage, item);
/* 17:21 */     Composer.endPacket(ClientMessage);
/* 18:22 */     return ClientMessage;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.catalog.UniqueLimitedItemComposer
 * JD-Core Version:    0.7.0.1
 */