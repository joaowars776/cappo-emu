/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.catalog.Catalog.CatalogProduct;
/*  5:   */ import cappo.game.catalog.Catalog.CatalogSubItem;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import java.util.List;
/*  8:   */ 
/*  9:   */ public class CatalogPageMessageOfferData
/* 10:   */ {
/* 11:   */   public static void parse(MessageWriter ClientMessage, Catalog.CatalogProduct item)
/* 12:   */   {
/* 13:16 */     Composer.add(Integer.valueOf(item.productId), ClientMessage);
/* 14:17 */     Composer.add(item.itemName, ClientMessage);
/* 15:18 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 16:19 */     Composer.add(Integer.valueOf(item.creditCost), ClientMessage);
/* 17:20 */     Composer.add(Integer.valueOf(item.activityPointCost), ClientMessage);
/* 18:21 */     Composer.add(Integer.valueOf(item.activityPointsType), ClientMessage);
/* 19:22 */     Composer.add(Boolean.valueOf(item.AllowGift), ClientMessage);
/* 20:23 */     Composer.add(Integer.valueOf(item.items.size()), ClientMessage);
/* 21:24 */     for (Catalog.CatalogSubItem subItem : item.items) {
/* 22:25 */       SerializeCatalogSubItem.parse(ClientMessage, subItem, item);
/* 23:   */     }
/* 24:27 */     Composer.add(Integer.valueOf(item.clubLevel), ClientMessage);
/* 25:28 */     Composer.add(Boolean.valueOf(item.allowBundleDiscounts), ClientMessage);
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.CatalogPageMessageOfferData
 * JD-Core Version:    0.7.0.1
 */