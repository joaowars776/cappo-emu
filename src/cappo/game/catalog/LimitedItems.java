/*  1:   */ package cappo.game.catalog;
/*  2:   */ 
/*  3:   */ import cappo.game.collections.Utils;
/*  4:   */ import java.util.Map;
/*  5:   */ import java.util.concurrent.ConcurrentHashMap;
/*  6:   */ 
/*  7:   */ public class LimitedItems
/*  8:   */ {
/*  9:   */   public Catalog.CatalogProduct product;
/* 10:   */   public long startTime;
/* 11:   */   public long endTime;
/* 12:   */   public static LimitedItems nextLtd;
/* 13:22 */   public static Map<Integer, LimitedItems> items = new ConcurrentHashMap();
/* 14:   */   
/* 15:   */   public LimitedItems(Catalog.CatalogProduct item, long start, long end)
/* 16:   */   {
/* 17:25 */     this.product = item;
/* 18:26 */     this.startTime = start;
/* 19:27 */     this.endTime = end;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public static void add(int productId, LimitedItems ltd)
/* 23:   */   {
/* 24:31 */     items.put(Integer.valueOf(productId), ltd);
/* 25:32 */     long now = Utils.getTimestamp();
/* 26:33 */     if ((ltd.endTime > now) && (
/* 27:34 */       (nextLtd == null) || (nextLtd.startTime < ltd.startTime))) {
/* 28:35 */       nextLtd = ltd;
/* 29:   */     }
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.catalog.LimitedItems
 * JD-Core Version:    0.7.0.1
 */