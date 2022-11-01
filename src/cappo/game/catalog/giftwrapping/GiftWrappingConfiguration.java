/*  1:   */ package cappo.game.catalog.giftwrapping;
/*  2:   */ 
/*  3:   */ import cappo.game.collections.BaseItem;
/*  4:   */ import java.util.HashMap;
/*  5:   */ import java.util.Map;
/*  6:   */ 
/*  7:   */ public class GiftWrappingConfiguration
/*  8:   */ {
/*  9:   */   public static final boolean WRAPPING_ENABLED = true;
/* 10:   */   public static final int WRAPPING_COST = 1;
/* 11:   */   public static final int BOX_COUNT = 7;
/* 12:   */   public static final int RIBBON_COUNT = 11;
/* 13:16 */   public static Map<Integer, BaseItem> baseGiftItems = new HashMap(20);
/* 14:17 */   public static Map<Integer, BaseItem> baseGiftFreeItems = new HashMap(10);
/* 15:   */   public static boolean needUpdate;
/* 16:   */   
/* 17:   */   public static void addGift(BaseItem item)
/* 18:   */   {
/* 19:21 */     int id = item.SpriteId;
/* 20:22 */     if (id < 1000) {
/* 21:23 */       baseGiftFreeItems.put(Integer.valueOf(id), item);
/* 22:   */     } else {
/* 23:25 */       baseGiftItems.put(Integer.valueOf(id), item);
/* 24:   */     }
/* 25:27 */     needUpdate = true;
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.catalog.giftwrapping.GiftWrappingConfiguration
 * JD-Core Version:    0.7.0.1
 */