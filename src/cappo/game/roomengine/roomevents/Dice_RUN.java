/*  1:   */ package cappo.game.roomengine.roomevents;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ import cappo.game.collections.Utils;
/*  5:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  6:   */ 
/*  7:   */ public class Dice_RUN
/*  8:   */   extends Event
/*  9:   */ {
/* 10:   */   GenericFloorItem Item;
/* 11:   */   
/* 12:   */   public Dice_RUN(GenericFloorItem item)
/* 13:   */   {
/* 14:17 */     this.Item = item;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void run(RoomTask room)
/* 18:   */   {
/* 19:22 */     this.Item.setIntData(Utils.GetRandomNumber(1, 6));
/* 20:23 */     room.floorItemUpdateNeeded(this.Item);
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomevents.Dice_RUN
 * JD-Core Version:    0.7.0.1
 */