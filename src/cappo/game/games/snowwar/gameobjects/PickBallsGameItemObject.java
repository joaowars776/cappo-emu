/*  1:   */ package cappo.game.games.snowwar.gameobjects;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.Direction360;
/*  4:   */ import cappo.game.games.snowwar.PlayerTile;
/*  5:   */ import cappo.game.games.snowwar.Tile;
/*  6:   */ 
/*  7:   */ public abstract class PickBallsGameItemObject
/*  8:   */   extends GameItemObject
/*  9:   */ {
/* 10:   */   protected int parentFuseId;
/* 11:   */   protected int snowBalls;
/* 12:   */   protected Tile location;
/* 13:   */   public int concurrentUses;
/* 14:   */   
/* 15:   */   public PickBallsGameItemObject(int _arg1, Tile _arg2, int _arg3, int _arg4)
/* 16:   */   {
/* 17:21 */     super(_arg1);
/* 18:22 */     this.location = _arg2;
/* 19:23 */     this.snowBalls = _arg3;
/* 20:24 */     this.parentFuseId = _arg4;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public Direction360 direction360()
/* 24:   */   {
/* 25:29 */     return null;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public PlayerTile location3D()
/* 29:   */   {
/* 30:34 */     return this.location.location();
/* 31:   */   }
/* 32:   */   
/* 33:   */   public int _4rk()
/* 34:   */   {
/* 35:38 */     return this.parentFuseId;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public boolean canPickUpFromHere()
/* 39:   */   {
/* 40:42 */     return this.snowBalls > this.concurrentUses;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public int pickUp(int ammount)
/* 44:   */   {
/* 45:46 */     if (this.snowBalls < ammount) {
/* 46:47 */       ammount = this.snowBalls;
/* 47:   */     }
/* 48:49 */     onSnowballPickup(ammount);
/* 49:50 */     return ammount;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public abstract void onSnowballPickup(int paramInt);
/* 53:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameobjects.PickBallsGameItemObject
 * JD-Core Version:    0.7.0.1
 */