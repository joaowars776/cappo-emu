/*  1:   */ package cappo.game.roomengine.roomevents;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  5:   */ 
/*  6:   */ public class VengingMachineClose
/*  7:   */   extends Event
/*  8:   */ {
/*  9:   */   GenericFloorItem Item;
/* 10:   */   
/* 11:   */   public VengingMachineClose(GenericFloorItem item)
/* 12:   */   {
/* 13:16 */     this.Item = item;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public void run(RoomTask room)
/* 17:   */   {
/* 18:21 */     this.Item.setIntData(0);
/* 19:22 */     room.floorItemUpdateNeeded(this.Item);
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomevents.VengingMachineClose
 * JD-Core Version:    0.7.0.1
 */