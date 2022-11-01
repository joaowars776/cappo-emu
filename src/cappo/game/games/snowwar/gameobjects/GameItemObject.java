/*  1:   */ package cappo.game.games.snowwar.gameobjects;
/*  2:   */ 
/*  3:   */ import cappo.game.collections.BaseItem;
/*  4:   */ import cappo.game.games.snowwar.CollisionDetection;
/*  5:   */ import cappo.game.games.snowwar.Direction360;
/*  6:   */ import cappo.game.games.snowwar.PlayerTile;
/*  7:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  8:   */ import cappo.game.games.snowwar.SynchronizedGameStage;
/*  9:   */ 
/* 10:   */ public abstract class GameItemObject
/* 11:   */ {
/* 12:   */   public static final int SNOWBALL = 1;
/* 13:   */   public static final int TREE = 2;
/* 14:   */   public static final int PILE = 3;
/* 15:   */   public static final int MACHINE = 4;
/* 16:   */   public static final int HUMAN = 5;
/* 17:   */   public BaseItem BaseItem;
/* 18:   */   public Object Data;
/* 19:   */   public int objectId;
/* 20:27 */   public boolean _active = false;
/* 21:   */   public final int variablesCount;
/* 22:   */   
/* 23:   */   public abstract PlayerTile location3D();
/* 24:   */   
/* 25:   */   public abstract Direction360 direction360();
/* 26:   */   
/* 27:   */   public abstract int getVariable(int paramInt);
/* 28:   */   
/* 29:   */   public abstract int[] boundingData();
/* 30:   */   
/* 31:   */   public void onRemove() {}
/* 32:   */   
/* 33:   */   public void subturn(SynchronizedGameStage _arg1) {}
/* 34:   */   
/* 35:   */   public void onSnowBallHit(SnowBallGameObject _arg2) {}
/* 36:   */   
/* 37:   */   protected GameItemObject(int varCount)
/* 38:   */   {
/* 39:42 */     this.variablesCount = varCount;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void GenerateCHECKSUM(SnowWarRoom arena, int multipler)
/* 43:   */   {
/* 44:46 */     for (int i = 0; i < this.variablesCount;) {
/* 45:47 */       arena.checksum += getVariable(i) * ++i * multipler;
/* 46:   */     }
/* 47:   */   }
/* 48:   */   
/* 49:   */   public int _3au()
/* 50:   */   {
/* 51:53 */     return -(this.objectId + 1);
/* 52:   */   }
/* 53:   */   
/* 54:   */   public int collisionHeight()
/* 55:   */   {
/* 56:57 */     return boundingData()[0];
/* 57:   */   }
/* 58:   */   
/* 59:   */   public boolean testSnowBallCollision(SnowBallGameObject _arg1)
/* 60:   */   {
/* 61:61 */     return (_arg1.location3D().z() < collisionHeight()) && (CollisionDetection._pU(this, _arg1));
/* 62:   */   }
/* 63:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameobjects.GameItemObject
 * JD-Core Version:    0.7.0.1
 */