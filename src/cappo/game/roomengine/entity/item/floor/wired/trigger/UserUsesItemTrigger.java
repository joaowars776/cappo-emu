/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired.trigger;
/*  2:   */ 
/*  3:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  4:   */ import cappo.game.roomengine.wired.WiredManager;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import java.util.List;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class UserUsesItemTrigger
/* 10:   */   extends WiredTriggerBase
/* 11:   */ {
/* 12:   */   public int getCode()
/* 13:   */   {
/* 14:11 */     return 1;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public boolean addItem(FloorItem item)
/* 18:   */   {
/* 19:16 */     if (super.addItem(item))
/* 20:   */     {
/* 21:17 */       List<WiredTriggerBase> triggerList = (List)this.wiredManager.triggersSateChanged.get(Integer.valueOf(item.itemId));
/* 22:18 */       if (triggerList == null)
/* 23:   */       {
/* 24:19 */         triggerList = new ArrayList();
/* 25:20 */         this.wiredManager.triggersSateChanged.put(Integer.valueOf(item.itemId), triggerList);
/* 26:   */       }
/* 27:22 */       triggerList.add(this);
/* 28:23 */       return true;
/* 29:   */     }
/* 30:25 */     return false;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public boolean removeAllItems()
/* 34:   */   {
/* 35:30 */     for (FloorItem item : this.items.values()) {
/* 36:31 */       removeItem(item);
/* 37:   */     }
/* 38:33 */     super.removeAllItems();
/* 39:34 */     return true;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void cleanDeletedItems()
/* 43:   */   {
/* 44:39 */     for (FloorItem item : this.deletedItems.values()) {
/* 45:40 */       removeItem(item);
/* 46:   */     }
/* 47:42 */     super.cleanDeletedItems();
/* 48:   */   }
/* 49:   */   
/* 50:   */   private void removeItem(FloorItem item)
/* 51:   */   {
/* 52:46 */     List<WiredTriggerBase> triggerList = (List)this.wiredManager.triggersSateChanged.get(Integer.valueOf(item.itemId));
/* 53:47 */     if (triggerList != null)
/* 54:   */     {
/* 55:48 */       triggerList.remove(this);
/* 56:49 */       if (triggerList.isEmpty()) {
/* 57:50 */         this.wiredManager.triggersSateChanged.remove(Integer.valueOf(item.itemId));
/* 58:   */       }
/* 59:   */     }
/* 60:   */   }
/* 61:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.trigger.UserUsesItemTrigger
 * JD-Core Version:    0.7.0.1
 */