/*  1:   */ package cappo.game.games.snowwar.gameobjects;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.Direction8;
/*  4:   */ import cappo.game.games.snowwar.PlayerTile;
/*  5:   */ import cappo.game.games.snowwar.SnowWarGameStage;
/*  6:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  7:   */ import cappo.game.games.snowwar.Tile;
/*  8:   */ 
/*  9:   */ public class PileGameObject
/* 10:   */   extends PickBallsGameItemObject
/* 11:   */ {
/* 12:15 */   private static int BALLS_SIZE = 100;
/* 13:   */   public SnowWarRoom currentSnowWar;
/* 14:   */   private int[] boudngingData;
/* 15:   */   private final int snowBallsCapacity;
/* 16:   */   
/* 17:   */   public PileGameObject(int x, int y, int a, int snowBalls, int parentFuseId, SnowWarGameStage _arg2, SnowWarRoom room)
/* 18:   */   {
/* 19:23 */     super(7, _arg2.getTile(x, y), snowBalls, parentFuseId);
/* 20:24 */     this.snowBallsCapacity = a;
/* 21:25 */     if (snowBalls > 0) {
/* 22:26 */       _arg2._2Av(this);
/* 23:   */     }
/* 24:28 */     this.boudngingData = new int[] { snowBalls * BALLS_SIZE };
/* 25:29 */     this.currentSnowWar = room;
/* 26:   */     
/* 27:   */ 
/* 28:   */ 
/* 29:33 */     Tile pickUpZones = this.location.getNextTileAtRot(Direction8.N);
/* 30:34 */     if (pickUpZones != null) {
/* 31:35 */       pickUpZones.pickBallsItem = this;
/* 32:   */     }
/* 33:37 */     pickUpZones = this.location.getNextTileAtRot(Direction8.S);
/* 34:38 */     if (pickUpZones != null) {
/* 35:39 */       pickUpZones.pickBallsItem = this;
/* 36:   */     }
/* 37:41 */     pickUpZones = this.location.getNextTileAtRot(Direction8.E);
/* 38:42 */     if (pickUpZones != null) {
/* 39:43 */       pickUpZones.pickBallsItem = this;
/* 40:   */     }
/* 41:45 */     pickUpZones = this.location.getNextTileAtRot(Direction8.W);
/* 42:46 */     if (pickUpZones != null) {
/* 43:47 */       pickUpZones.pickBallsItem = this;
/* 44:   */     }
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void setSnowBalls(int val)
/* 48:   */   {
/* 49:53 */     this.currentSnowWar.checksum += val * 6 - getVariable(5) * 6;
/* 50:54 */     this.snowBalls = val;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public int getVariable(int val)
/* 54:   */   {
/* 55:59 */     if (val == 0) {
/* 56:60 */       return 3;
/* 57:   */     }
/* 58:62 */     if (val == 1) {
/* 59:63 */       return this.objectId;
/* 60:   */     }
/* 61:65 */     if (val == 2) {
/* 62:66 */       return this.location.location().x();
/* 63:   */     }
/* 64:68 */     if (val == 3) {
/* 65:69 */       return this.location.location().y();
/* 66:   */     }
/* 67:71 */     if (val == 4) {
/* 68:72 */       return this.snowBallsCapacity;
/* 69:   */     }
/* 70:74 */     if (val == 5) {
/* 71:75 */       return this.snowBalls;
/* 72:   */     }
/* 73:78 */     return this.parentFuseId;
/* 74:   */   }
/* 75:   */   
/* 76:   */   public int[] boundingData()
/* 77:   */   {
/* 78:84 */     return this.boudngingData;
/* 79:   */   }
/* 80:   */   
/* 81:   */   public int _4b8()
/* 82:   */   {
/* 83:88 */     return this.snowBallsCapacity;
/* 84:   */   }
/* 85:   */   
/* 86:   */   public void onSnowballPickup(int ammount)
/* 87:   */   {
/* 88:93 */     setSnowBalls(this.snowBalls - ammount);
/* 89:   */     
/* 90:95 */     this.boudngingData = new int[] { this.snowBalls * BALLS_SIZE };
/* 91:96 */     if (this.snowBalls <= 0) {
/* 92:97 */       this.location.removeGameObject();
/* 93:   */     }
/* 94:   */   }
/* 95:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameobjects.PileGameObject
 * JD-Core Version:    0.7.0.1
 */