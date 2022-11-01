/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.catalog.Catalog.CatalogProduct;
/*  5:   */ import cappo.game.catalog.Catalog.CatalogSubItem;
/*  6:   */ import cappo.game.collections.BaseItem;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ 
/*  9:   */ public class SerializeCatalogSubItem
/* 10:   */ {
/* 11:   */   public static void parse(MessageWriter ClientMessage, Catalog.CatalogSubItem subItem, Catalog.CatalogProduct item)
/* 12:   */   {
/* 13:16 */     Composer.add(subItem.baseItem.Type, ClientMessage);
/* 14:17 */     if (subItem.baseItem.Type.equals("b"))
/* 15:   */     {
/* 16:18 */       Composer.add(subItem.extraData, ClientMessage);
/* 17:   */     }
/* 18:   */     else
/* 19:   */     {
/* 20:20 */       Composer.add(Integer.valueOf(subItem.baseItem.SpriteId), ClientMessage);
/* 21:21 */       Composer.add(subItem.extraData, ClientMessage);
/* 22:22 */       Composer.add(subItem.amount, ClientMessage);
/* 23:24 */       if (item.uniqueLimitedItemsLaunched.intValue() < 1)
/* 24:   */       {
/* 25:25 */         Composer.add(Boolean.valueOf(false), ClientMessage);
/* 26:   */       }
/* 27:   */       else
/* 28:   */       {
/* 29:28 */         Composer.add(Boolean.valueOf(true), ClientMessage);
/* 30:29 */         Composer.add(item.uniqueLimitedItemsLaunched, ClientMessage);
/* 31:30 */         Composer.add(item.uniqueLimitedItemsLeft, ClientMessage);
/* 32:   */       }
/* 33:   */     }
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeCatalogSubItem
 * JD-Core Version:    0.7.0.1
 */