/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.Direction8;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  5:   */ import java.util.Map;
/*  6:   */ import java.util.concurrent.ConcurrentHashMap;
/*  7:   */ 
/*  8:   */ public class AtachedWireds
/*  9:   */ {
/* 10:   */   private final Map<Integer, WiredItemBase> wireds;
/* 11:   */   
/* 12:   */   public AtachedWireds(WiredItemBase wired)
/* 13:   */   {
/* 14:13 */     this.wireds = new ConcurrentHashMap(3);
/* 15:14 */     addWired(wired);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void addWired(WiredItemBase wired)
/* 19:   */   {
/* 20:18 */     this.wireds.put(Integer.valueOf(wired.itemId), wired);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void removeWired(int itemId)
/* 24:   */   {
/* 25:22 */     this.wireds.remove(Integer.valueOf(itemId));
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void itemMoved(FloorItem item, int xy, Direction8 dir)
/* 29:   */   {
/* 30:26 */     for (WiredItemBase wired : this.wireds.values()) {
/* 31:27 */       wired.onChildMove(item, xy, dir);
/* 32:   */     }
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void itemPicked(FloorItem item, int xy, Direction8 dir)
/* 36:   */   {
/* 37:32 */     for (WiredItemBase wired : this.wireds.values()) {
/* 38:33 */       wired.onChildPicked(item, xy, dir);
/* 39:   */     }
/* 40:   */   }
/* 41:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.AtachedWireds
 * JD-Core Version:    0.7.0.1
 */