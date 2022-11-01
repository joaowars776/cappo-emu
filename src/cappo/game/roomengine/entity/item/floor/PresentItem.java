/*  1:   */ package cappo.game.roomengine.entity.item.floor;
/*  2:   */ 
/*  3:   */ import cappo.game.catalog.Catalog;
/*  4:   */ import cappo.game.catalog.Catalog.CatalogProduct;
/*  5:   */ import cappo.game.roomengine.entity.item.extradata.MapStuffData;
/*  6:   */ import java.util.Map;
/*  7:   */ 
/*  8:   */ public class PresentItem
/*  9:   */   extends FloorItem
/* 10:   */ {
/* 11:   */   public static final String MESSAGE = "MESSAGE";
/* 12:   */   public static final String PRODUCT_CODE = "PRODUCT_CODE";
/* 13:   */   public static final String EXTRA_PARAM = "EXTRA_PARAM";
/* 14:   */   public static final String PURCHASER_NAME = "PURCHASER_NAME";
/* 15:   */   public static final String PURCHASER_FIGURE = "PURCHASER_FIGURE";
/* 16:   */   
/* 17:   */   public Catalog.CatalogProduct getProduct()
/* 18:   */   {
/* 19:21 */     MapStuffData data = (MapStuffData)this.extraData;
/* 20:22 */     return (Catalog.CatalogProduct)Catalog.Items.get(Integer.valueOf(Integer.parseInt((String)data.extraData.get("PRODUCT_CODE"))));
/* 21:   */   }
/* 22:   */   
/* 23:   */   public String getProductParam()
/* 24:   */   {
/* 25:26 */     MapStuffData data = (MapStuffData)this.extraData;
/* 26:27 */     return (String)data.extraData.get("EXTRA_PARAM");
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.PresentItem
 * JD-Core Version:    0.7.0.1
 */