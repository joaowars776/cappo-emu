/*  1:   */ package cappo.game.rollers;
/*  2:   */ 
/*  3:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  4:   */ 
/*  5:   */ public class RollerMoveDataObject
/*  6:   */   extends RollerMoveData
/*  7:   */ {
/*  8:   */   public FloorItem item;
/*  9:   */   
/* 10:   */   public RollerMoveDataObject(FloorItem stacked)
/* 11:   */   {
/* 12:15 */     this.item = stacked;
/* 13:16 */     this.fromZ = stacked.getZ();
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.rollers.RollerMoveDataObject
 * JD-Core Version:    0.7.0.1
 */