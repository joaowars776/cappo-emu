/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired.condition;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  5:   */ import java.util.Map;
/*  6:   */ 
/*  7:   */ public class FurniHasItemsOnTop
/*  8:   */   extends WiredConditionBase
/*  9:   */ {
/* 10:   */   private int optionMODE;
/* 11:   */   
/* 12:   */   public int getCode()
/* 13:   */   {
/* 14:17 */     return 7;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public boolean needUser()
/* 18:   */   {
/* 19:22 */     return false;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void setWiredOption(int index, int option)
/* 23:   */   {
/* 24:27 */     if (index == 0) {
/* 25:28 */       this.optionMODE = option;
/* 26:   */     }
/* 27:   */   }
/* 28:   */   
/* 29:   */   public int[] getWiredOptions()
/* 30:   */   {
/* 31:34 */     return new int[] { this.optionMODE };
/* 32:   */   }
/* 33:   */   
/* 34:   */   public boolean checkCondition(Connection invoker)
/* 35:   */   {
/* 36:39 */     if (this.optionMODE == 0) {
/* 37:40 */       return checkFirst();
/* 38:   */     }
/* 39:42 */     return checkAll();
/* 40:   */   }
/* 41:   */   
/* 42:   */   private boolean checkFirst()
/* 43:   */   {
/* 44:46 */     for (FloorItem floorItem : this.items.values()) {
/* 45:47 */       if (floorItem.itemsOnTop()) {
/* 46:48 */         return true;
/* 47:   */       }
/* 48:   */     }
/* 49:52 */     return false;
/* 50:   */   }
/* 51:   */   
/* 52:   */   private boolean checkAll()
/* 53:   */   {
/* 54:56 */     for (FloorItem floorItem : this.items.values()) {
/* 55:57 */       if (!floorItem.itemsOnTop()) {
/* 56:58 */         return false;
/* 57:   */       }
/* 58:   */     }
/* 59:62 */     return true;
/* 60:   */   }
/* 61:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.condition.FurniHasItemsOnTop
 * JD-Core Version:    0.7.0.1
 */