/*  1:   */ package cappo.game.roomengine.roomevents;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ import cappo.game.roomengine.RoomData;
/*  5:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.wired.trigger.WiredTriggerBase;
/*  7:   */ import cappo.game.roomengine.wired.WiredManager;
/*  8:   */ 
/*  9:   */ public class Item_TIMER
/* 10:   */   extends Event
/* 11:   */ {
/* 12:   */   GenericFloorItem Item;
/* 13:   */   
/* 14:   */   public Item_TIMER(GenericFloorItem item)
/* 15:   */   {
/* 16:18 */     this.Item = item;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void run(RoomTask room)
/* 20:   */   {
/* 21:23 */     if (room.roomData.haveFlag(64)) {
/* 22:24 */       if (this.Item.decIntData(1) >= 0)
/* 23:   */       {
/* 24:25 */         room.floorItemUpdateNeeded(this.Item);
/* 25:26 */         this.Ticks += 2;
/* 26:   */       }
/* 27:   */       else
/* 28:   */       {
/* 29:28 */         room.roomData.setFlag(64, false);
/* 30:29 */         WiredTriggerBase.launchTriggers(room.wiredManager.triggersGameEnds, null, null);
/* 31:   */       }
/* 32:   */     }
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomevents.Item_TIMER
 * JD-Core Version:    0.7.0.1
 */