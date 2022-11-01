/*   1:    */ package cappo.game.player;
/*   2:    */ 
/*   3:    */ import cappo.game.games.snowwar.PlayerTile;
/*   4:    */ import cappo.game.games.snowwar.SnowPlayerQueue;
/*   5:    */ import cappo.game.games.snowwar.SnowWarRoom;
/*   6:    */ import cappo.game.games.snowwar.SynchronizedGameStage;
/*   7:    */ import cappo.game.games.snowwar.Tile;
/*   8:    */ import cappo.game.games.snowwar.gameevents.BallThrowToHuman;
/*   9:    */ import cappo.game.games.snowwar.gameevents.BallThrowToPosition;
/*  10:    */ import cappo.game.games.snowwar.gameevents.CreateSnowBall;
/*  11:    */ import cappo.game.games.snowwar.gameevents.MakeSnowBall;
/*  12:    */ import cappo.game.games.snowwar.gameevents.UserMove;
/*  13:    */ import cappo.game.games.snowwar.gameobjects.GameItemObject;
/*  14:    */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  15:    */ import cappo.game.games.snowwar.gameobjects.SnowBallGameObject;
/*  16:    */ import java.util.List;
/*  17:    */ import java.util.Map;
/*  18:    */ 
/*  19:    */ public class SnowWarPlayerData
/*  20:    */   extends GamePlayerData
/*  21:    */ {
/*  22:    */   public PlayerData player;
/*  23:    */   public SnowWarRoom currentSnowWar;
/*  24:    */   public HumanGameObject humanObject;
/*  25:    */   public int snowLevel;
/*  26:    */   public int PointsNeed;
/*  27:    */   
/*  28:    */   public SnowWarPlayerData(PlayerData playerData)
/*  29:    */   {
/*  30: 24 */     this.player = playerData;
/*  31: 25 */     this.snowLevel = 1;
/*  32: 26 */     this.rank = 1;
/*  33: 27 */     this.score = 10;
/*  34: 28 */     this.PointsNeed = 100;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void setHumanObject(HumanGameObject humanGameObject)
/*  38:    */   {
/*  39: 32 */     if (humanGameObject == null)
/*  40:    */     {
/*  41: 33 */       this.humanObject.snowWarPlayer = null;
/*  42: 34 */       this.humanObject.cn = null;
/*  43: 35 */       this.humanObject.userId = 0;
/*  44: 36 */       this.humanObject = null;
/*  45:    */     }
/*  46:    */     else
/*  47:    */     {
/*  48: 38 */       this.humanObject = humanGameObject;
/*  49: 39 */       this.humanObject.snowWarPlayer = this;
/*  50:    */       
/*  51: 41 */       this.humanObject.cn = this.player.connection;
/*  52: 42 */       this.humanObject.userId = this.player.userId;
/*  53: 43 */       this.humanObject.userName = this.player.userName;
/*  54: 44 */       this.humanObject.look = this.player.avatarLook.toString();
/*  55: 45 */       this.humanObject.motto = this.player.motto;
/*  56: 46 */       this.humanObject.sex = this.player.sex;
/*  57:    */     }
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void setRoom(SnowWarRoom room)
/*  61:    */   {
/*  62: 51 */     this.currentSnowWar = room;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void userLeft()
/*  66:    */   {
/*  67: 56 */     if ((this.humanObject != null) && (this.currentSnowWar != null)) {
/*  68: 57 */       SnowPlayerQueue.playerExit(this.currentSnowWar, this.humanObject);
/*  69:    */     }
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void makeSnowBall()
/*  73:    */   {
/*  74: 62 */     synchronized (this.currentSnowWar.gameEvents)
/*  75:    */     {
/*  76: 63 */       this.currentSnowWar.gameEvents.add(new MakeSnowBall(this.humanObject));
/*  77:    */     }
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void playerMove(int x, int y)
/*  81:    */   {
/*  82: 68 */     synchronized (this.currentSnowWar.gameEvents)
/*  83:    */     {
/*  84: 69 */       this.currentSnowWar.gameEvents.add(new UserMove(this.humanObject, x, y));
/*  85:    */     }
/*  86:    */   }
/*  87:    */   
/*  88:    */   public void throwSnowballFlood(int destX, int destY)
/*  89:    */   {
/*  90: 74 */     SnowBallGameObject ball = new SnowBallGameObject(this.currentSnowWar); SnowWarRoom 
/*  91: 75 */       tmp17_14 = this.currentSnowWar; int tmp21_18 = tmp17_14.objectIdCounter;tmp17_14.objectIdCounter = (tmp21_18 + 1);ball.objectId = tmp21_18;
/*  92: 76 */     SnowBallGameObject ball1 = new SnowBallGameObject(this.currentSnowWar); SnowWarRoom 
/*  93: 77 */       tmp49_46 = this.currentSnowWar; int tmp53_50 = tmp49_46.objectIdCounter;tmp49_46.objectIdCounter = (tmp53_50 + 1);ball1.objectId = tmp53_50;
/*  94: 78 */     SnowBallGameObject ball2 = new SnowBallGameObject(this.currentSnowWar); SnowWarRoom 
/*  95: 79 */       tmp81_78 = this.currentSnowWar; int tmp85_82 = tmp81_78.objectIdCounter;tmp81_78.objectIdCounter = (tmp85_82 + 1);ball2.objectId = tmp85_82;
/*  96: 80 */     SnowBallGameObject ball3 = new SnowBallGameObject(this.currentSnowWar); SnowWarRoom 
/*  97: 81 */       tmp113_110 = this.currentSnowWar; int tmp117_114 = tmp113_110.objectIdCounter;tmp113_110.objectIdCounter = (tmp117_114 + 1);ball3.objectId = tmp117_114;
/*  98: 82 */     SnowBallGameObject ball4 = new SnowBallGameObject(this.currentSnowWar); SnowWarRoom 
/*  99: 83 */       tmp145_142 = this.currentSnowWar; int tmp149_146 = tmp145_142.objectIdCounter;tmp145_142.objectIdCounter = (tmp149_146 + 1);ball4.objectId = tmp149_146;
/* 100: 84 */     SnowBallGameObject ball5 = new SnowBallGameObject(this.currentSnowWar); SnowWarRoom 
/* 101: 85 */       tmp177_174 = this.currentSnowWar; int tmp181_178 = tmp177_174.objectIdCounter;tmp177_174.objectIdCounter = (tmp181_178 + 1);ball5.objectId = tmp181_178;
/* 102: 86 */     SnowBallGameObject ball6 = new SnowBallGameObject(this.currentSnowWar); SnowWarRoom 
/* 103: 87 */       tmp209_206 = this.currentSnowWar; int tmp213_210 = tmp209_206.objectIdCounter;tmp209_206.objectIdCounter = (tmp213_210 + 1);ball6.objectId = tmp213_210;
/* 104: 88 */     synchronized (this.currentSnowWar.gameEvents)
/* 105:    */     {
/* 106: 89 */       this.currentSnowWar.gameEvents.add(new BallThrowToPosition(this.humanObject, destX * Tile.TILE_SIZE, destY * Tile.TILE_SIZE, 3));
/* 107: 90 */       this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball, this.humanObject, destX * Tile.TILE_SIZE, destY * Tile.TILE_SIZE, 3));
/* 108: 91 */       this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball4, this.humanObject, destX * Tile.TILE_SIZE, (destY + 1) * Tile.TILE_SIZE, 3));
/* 109: 92 */       this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball1, this.humanObject, (destX + 1) * Tile.TILE_SIZE, destY * Tile.TILE_SIZE, 3));
/* 110: 93 */       this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball6, this.humanObject, (destX - 1) * Tile.TILE_SIZE, (destY + 1) * Tile.TILE_SIZE, 3));
/* 111: 94 */       this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball2, this.humanObject, (destX - 1) * Tile.TILE_SIZE, (destY - 1) * Tile.TILE_SIZE, 3));
/* 112: 95 */       this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball3, this.humanObject, (destX + 1) * Tile.TILE_SIZE, (destY - 1) * Tile.TILE_SIZE, 3));
/* 113: 96 */       this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball5, this.humanObject, (destX + 1) * Tile.TILE_SIZE, (destY + 1) * Tile.TILE_SIZE, 3));
/* 114:    */     }
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void throwSnowballAtHuman(int victim, int type)
/* 118:    */   {
/* 119:101 */     if (!this.humanObject.canThrowSnowBall()) {
/* 120:102 */       return;
/* 121:    */     }
/* 122:105 */     GameItemObject vict = (GameItemObject)this.currentSnowWar.gameObjects.get(Integer.valueOf(victim));
/* 123:106 */     if (vict == null) {
/* 124:107 */       return;
/* 125:    */     }
/* 126:110 */     SnowBallGameObject ball = new SnowBallGameObject(this.currentSnowWar); SnowWarRoom 
/* 127:111 */       tmp55_52 = this.currentSnowWar; int tmp59_56 = tmp55_52.objectIdCounter;tmp55_52.objectIdCounter = (tmp59_56 + 1);ball.objectId = tmp59_56;
/* 128:112 */     synchronized (this.currentSnowWar.gameEvents)
/* 129:    */     {
/* 130:113 */       this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball, this.humanObject, vict.location3D().x(), vict.location3D().y(), type));
/* 131:114 */       this.currentSnowWar.gameEvents.add(new BallThrowToHuman(this.humanObject, (HumanGameObject)vict, 0));
/* 132:    */     }
/* 133:    */   }
/* 134:    */   
/* 135:    */   public void throwSnowballAtPosition(int destX, int destY, int type)
/* 136:    */   {
/* 137:119 */     if (!this.humanObject.canThrowSnowBall()) {
/* 138:120 */       return;
/* 139:    */     }
/* 140:123 */     SnowBallGameObject ball = new SnowBallGameObject(this.currentSnowWar); SnowWarRoom 
/* 141:124 */       tmp30_27 = this.currentSnowWar; int tmp34_31 = tmp30_27.objectIdCounter;tmp30_27.objectIdCounter = (tmp34_31 + 1);ball.objectId = tmp34_31;
/* 142:125 */     synchronized (this.currentSnowWar.gameEvents)
/* 143:    */     {
/* 144:126 */       this.currentSnowWar.gameEvents.add(new CreateSnowBall(ball, this.humanObject, destX, destY, type));
/* 145:127 */       this.currentSnowWar.gameEvents.add(new BallThrowToPosition(this.humanObject, destX, destY, 0));
/* 146:    */     }
/* 147:    */   }
/* 148:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.SnowWarPlayerData
 * JD-Core Version:    0.7.0.1
 */