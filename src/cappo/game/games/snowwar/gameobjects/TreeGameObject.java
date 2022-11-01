/*   1:    */ package cappo.game.games.snowwar.gameobjects;
/*   2:    */ 
/*   3:    */ import cappo.game.games.snowwar.Direction360;
/*   4:    */ import cappo.game.games.snowwar.Direction8;
/*   5:    */ import cappo.game.games.snowwar.PlayerTile;
/*   6:    */ import cappo.game.games.snowwar.SnowWarGameStage;
/*   7:    */ import cappo.game.games.snowwar.SnowWarRoom;
/*   8:    */ import cappo.game.games.snowwar.Tile;
/*   9:    */ 
/*  10:    */ public class TreeGameObject
/*  11:    */   extends GameItemObject
/*  12:    */ {
/*  13: 18 */   private static int[] _jU = new int[1];
/*  14: 19 */   private static int[] _2Kl = { Tile.TILE_SIZE - SnowBallGameObject.boundingData[0] - 1 };
/*  15:    */   private final int parentFuseId;
/*  16:    */   private final Tile _0QF;
/*  17:    */   private final Direction8 _direction8;
/*  18:    */   private final Direction360 _direction360;
/*  19:    */   private final int _height;
/*  20:    */   private final int _ws;
/*  21:    */   private int currentDamage;
/*  22:    */   public SnowWarRoom currentSnowWar;
/*  23:    */   
/*  24:    */   public TreeGameObject(int x, int y, int rot, int height, int a, int b, int c, SnowWarGameStage _arg2, SnowWarRoom room)
/*  25:    */   {
/*  26: 32 */     super(9);
/*  27:    */     
/*  28: 34 */     this.currentSnowWar = room;
/*  29:    */     
/*  30: 36 */     this._0QF = _arg2.getTile(x, y);
/*  31: 37 */     this._direction8 = Direction8.getDirection(rot);
/*  32: 38 */     this._direction360 = new Direction360(Direction360.direction8ToDirection360Value(this._direction8));
/*  33: 39 */     this.parentFuseId = a;
/*  34: 40 */     this._height = (height * Tile.TILE_SIZE);
/*  35: 41 */     this.currentDamage = c;
/*  36: 42 */     this._ws = b;
/*  37:    */     
/*  38: 44 */     _arg2._2Av(this);
/*  39:    */     
/*  40: 46 */     this._0QF._4AO(-this._height);
/*  41: 47 */     this._0QF.setBlocked(true);
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void setDamage(int val)
/*  45:    */   {
/*  46: 51 */     this.currentSnowWar.checksum += val * 9 - getVariable(8) * 9;
/*  47: 52 */     this.currentDamage = val;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public int getVariable(int val)
/*  51:    */   {
/*  52: 57 */     if (val == 0) {
/*  53: 58 */       return 2;
/*  54:    */     }
/*  55: 60 */     if (val == 1) {
/*  56: 61 */       return this.objectId;
/*  57:    */     }
/*  58: 63 */     if (val == 2) {
/*  59: 64 */       return this._0QF.location().x();
/*  60:    */     }
/*  61: 66 */     if (val == 3) {
/*  62: 67 */       return this._0QF.location().y();
/*  63:    */     }
/*  64: 69 */     if (val == 4) {
/*  65: 70 */       return this._direction8.getRot();
/*  66:    */     }
/*  67: 72 */     if (val == 5) {
/*  68: 73 */       return this._height;
/*  69:    */     }
/*  70: 75 */     if (val == 6) {
/*  71: 76 */       return this.parentFuseId;
/*  72:    */     }
/*  73: 78 */     if (val == 7) {
/*  74: 79 */       return this._ws;
/*  75:    */     }
/*  76: 82 */     return this.currentDamage;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public int[] boundingData()
/*  80:    */   {
/*  81: 88 */     if (this.currentDamage < this._ws) {
/*  82: 89 */       return _2Kl;
/*  83:    */     }
/*  84: 91 */     return _jU;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public PlayerTile location3D()
/*  88:    */   {
/*  89: 96 */     return this._0QF.location();
/*  90:    */   }
/*  91:    */   
/*  92:    */   public Direction360 direction360()
/*  93:    */   {
/*  94:101 */     return this._direction360;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void onSnowBallHit(SnowBallGameObject _arg2)
/*  98:    */   {
/*  99:106 */     if (this.currentDamage < this._ws) {
/* 100:107 */       setDamage(this.currentDamage + 1);
/* 101:    */     } else {
/* 102:109 */       this._0QF.removeGameObject();
/* 103:    */     }
/* 104:    */   }
/* 105:    */   
/* 106:    */   public int _4ZU()
/* 107:    */   {
/* 108:114 */     return this._ws;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public int _2Ti()
/* 112:    */   {
/* 113:118 */     return this.currentDamage;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public int _4rk()
/* 117:    */   {
/* 118:122 */     return this.parentFuseId;
/* 119:    */   }
/* 120:    */   
/* 121:    */   public int collisionHeight()
/* 122:    */   {
/* 123:127 */     return this._height;
/* 124:    */   }
/* 125:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameobjects.TreeGameObject
 * JD-Core Version:    0.7.0.1
 */