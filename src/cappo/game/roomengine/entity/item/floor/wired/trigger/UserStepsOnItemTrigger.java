/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired.trigger;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.Direction8;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  5:   */ import cappo.game.roomengine.entity.item.floor.RoomFloorItemData.AffectedTile;
/*  6:   */ import cappo.game.roomengine.wired.WiredManager;
/*  7:   */ import java.util.ArrayList;
/*  8:   */ import java.util.List;
/*  9:   */ import java.util.Map;
/* 10:   */ 
/* 11:   */ public class UserStepsOnItemTrigger
/* 12:   */   extends WiredTriggerBase
/* 13:   */ {
/* 14:   */   public int getCode()
/* 15:   */   {
/* 16:14 */     return 1;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void onChildMove(FloorItem item, int xy, Direction8 dir)
/* 20:   */   {
/* 21:19 */     removeItem(item, xy, dir);
/* 22:20 */     setupItem(item);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void onChildPicked(FloorItem item, int xy, Direction8 dir)
/* 26:   */   {
/* 27:24 */     removeItem(item, xy, dir);
/* 28:25 */     super.onChildPicked(item, xy, dir);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public boolean addItem(FloorItem item)
/* 32:   */   {
/* 33:30 */     if (super.addItem(item))
/* 34:   */     {
/* 35:31 */       setupItem(item);
/* 36:32 */       item.addAttachedWired(this);
/* 37:   */     }
/* 38:34 */     return true;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void setupItem(FloorItem item)
/* 42:   */   {
/* 43:39 */     List<RoomFloorItemData.AffectedTile> PointList = item.getAffectedTiles();
/* 44:40 */     for (RoomFloorItemData.AffectedTile Tile : PointList)
/* 45:   */     {
/* 46:41 */       List<WiredTriggerBase> triggerList = (List)this.wiredManager.triggersWalksOnFurni.get(Integer.valueOf(Tile.xy));
/* 47:42 */       if (triggerList == null)
/* 48:   */       {
/* 49:43 */         triggerList = new ArrayList();
/* 50:44 */         this.wiredManager.triggersWalksOnFurni.put(Integer.valueOf(Tile.xy), triggerList);
/* 51:   */       }
/* 52:46 */       if (triggerList.isEmpty()) {
/* 53:47 */         item.eventSetFlag(Tile.xy, 256, true);
/* 54:   */       }
/* 55:49 */       triggerList.add(this);
/* 56:   */     }
/* 57:   */   }
/* 58:   */   
/* 59:   */   public boolean removeAllItems()
/* 60:   */   {
/* 61:55 */     for (FloorItem item : this.items.values()) {
/* 62:56 */       removeItem(item, item.getXy(), item.getDir());
/* 63:   */     }
/* 64:58 */     super.removeAllItems();
/* 65:59 */     return true;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public void cleanDeletedItems()
/* 69:   */   {
/* 70:64 */     for (FloorItem item : this.deletedItems.values()) {
/* 71:65 */       removeItem(item, item.getXy(), item.getDir());
/* 72:   */     }
/* 73:67 */     super.cleanDeletedItems();
/* 74:   */   }
/* 75:   */   
/* 76:   */   private void removeItem(FloorItem item, int xy, Direction8 dir)
/* 77:   */   {
/* 78:71 */     List<RoomFloorItemData.AffectedTile> PointList = item.getAffectedTiles(xy, dir);
/* 79:72 */     for (RoomFloorItemData.AffectedTile Tile : PointList)
/* 80:   */     {
/* 81:73 */       List<WiredTriggerBase> triggerList = (List)this.wiredManager.triggersWalksOnFurni.get(Integer.valueOf(Tile.xy));
/* 82:74 */       if (triggerList != null)
/* 83:   */       {
/* 84:75 */         triggerList.remove(this);
/* 85:76 */         if (triggerList.isEmpty())
/* 86:   */         {
/* 87:77 */           this.wiredManager.triggersWalksOnFurni.remove(Integer.valueOf(Tile.xy));
/* 88:78 */           item.eventSetFlag(Tile.xy, 256, false);
/* 89:   */         }
/* 90:   */       }
/* 91:   */     }
/* 92:   */   }
/* 93:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.trigger.UserStepsOnItemTrigger
 * JD-Core Version:    0.7.0.1
 */