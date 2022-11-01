/*  1:   */ package cappo.game.rollers;
/*  2:   */ 
/*  3:   */ import cappo.game.roomengine.entity.live.LiveEntity;
/*  4:   */ 
/*  5:   */ public class RollerMoveDataEntity
/*  6:   */   extends RollerMoveData
/*  7:   */ {
/*  8:   */   public LiveEntity entity;
/*  9:   */   public int entityMoveType;
/* 10:   */   
/* 11:   */   public RollerMoveDataEntity(LiveEntity stacked, int movetype)
/* 12:   */   {
/* 13:10 */     this.entity = stacked;
/* 14:11 */     this.fromZ = stacked.z;
/* 15:12 */     this.entityMoveType = movetype;
/* 16:   */   }
/* 17:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.rollers.RollerMoveDataEntity
 * JD-Core Version:    0.7.0.1
 */