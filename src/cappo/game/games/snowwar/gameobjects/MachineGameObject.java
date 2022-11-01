/*  1:   */ package cappo.game.games.snowwar.gameobjects;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.Direction8;
/*  4:   */ import cappo.game.games.snowwar.PlayerTile;
/*  5:   */ import cappo.game.games.snowwar.SnowWarGameStage;
/*  6:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  7:   */ import cappo.game.games.snowwar.SynchronizedGameStage;
/*  8:   */ import cappo.game.games.snowwar.Tile;
/*  9:   */ import cappo.game.games.snowwar.gameevents.AddBallToMachine;
/* 10:   */ import java.util.List;
/* 11:   */ 
/* 12:   */ public class MachineGameObject
/* 13:   */   extends PickBallsGameItemObject
/* 14:   */ {
/* 15:   */   private static final int SNOWBALLGENERATOR_TIME = 100;
/* 16:19 */   public static int[] boundingData = { 1200 };
/* 17:   */   private final int snowBallsCapacity;
/* 18:   */   private final Direction8 direction;
/* 19:24 */   private int snowBallGeneratorTimer = 100;
/* 20:   */   public SnowWarRoom currentSnowWar;
/* 21:   */   
/* 22:   */   public MachineGameObject(int x, int y, int rot, int a, int b, int c, SnowWarGameStage _arg2, SnowWarRoom room)
/* 23:   */   {
/* 24:29 */     super(8, _arg2.getTile(x, y), b, c);
/* 25:30 */     this.snowBallsCapacity = a;
/* 26:31 */     this.direction = Direction8.getDirection(rot);
/* 27:32 */     _arg2._2Av(this);
/* 28:33 */     this.currentSnowWar = room;
/* 29:   */     
/* 30:35 */     Tile frontTile = this.location.getNextTileAtRot(Direction8.getDirection((rot + 4) % 8));
/* 31:36 */     if (frontTile != null) {
/* 32:37 */       frontTile.pickBallsItem = this;
/* 33:   */     }
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void setSnowBalls(int val)
/* 37:   */   {
/* 38:42 */     this.currentSnowWar.checksum += val * 7 - getVariable(6) * 7;
/* 39:43 */     this.snowBalls = val;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public int getVariable(int val)
/* 43:   */   {
/* 44:48 */     if (val == 0) {
/* 45:49 */       return 4;
/* 46:   */     }
/* 47:51 */     if (val == 1) {
/* 48:52 */       return this.objectId;
/* 49:   */     }
/* 50:54 */     if (val == 2) {
/* 51:55 */       return this.location.location().x();
/* 52:   */     }
/* 53:57 */     if (val == 3) {
/* 54:58 */       return this.location.location().y();
/* 55:   */     }
/* 56:60 */     if (val == 4) {
/* 57:61 */       return this.direction.getRot();
/* 58:   */     }
/* 59:63 */     if (val == 5) {
/* 60:64 */       return this.snowBallsCapacity;
/* 61:   */     }
/* 62:66 */     if (val == 6) {
/* 63:67 */       return this.snowBalls;
/* 64:   */     }
/* 65:70 */     return this.parentFuseId;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public void subturn(SynchronizedGameStage unused)
/* 69:   */   {
/* 70:76 */     if (this.snowBallGeneratorTimer > 0)
/* 71:   */     {
/* 72:77 */       this.snowBallGeneratorTimer -= 1;
/* 73:   */     }
/* 74:   */     else
/* 75:   */     {
/* 76:79 */       this.snowBallGeneratorTimer = 100;
/* 77:80 */       synchronized (this.currentSnowWar.gameEvents)
/* 78:   */       {
/* 79:81 */         this.currentSnowWar.gameEvents.add(new AddBallToMachine(this));
/* 80:   */       }
/* 81:   */     }
/* 82:   */   }
/* 83:   */   
/* 84:   */   public int[] boundingData()
/* 85:   */   {
/* 86:88 */     return boundingData;
/* 87:   */   }
/* 88:   */   
/* 89:   */   public void addSnowBall()
/* 90:   */   {
/* 91:92 */     if (this.snowBalls < this.snowBallsCapacity) {
/* 92:93 */       setSnowBalls(this.snowBalls + 1);
/* 93:   */     }
/* 94:   */   }
/* 95:   */   
/* 96:   */   public void onSnowballPickup(int ammount)
/* 97:   */   {
/* 98:99 */     setSnowBalls(this.snowBalls - ammount);
/* 99:   */   }
/* :0:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameobjects.MachineGameObject
 * JD-Core Version:    0.7.0.1
 */