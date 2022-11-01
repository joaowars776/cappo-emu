/*   1:    */ package cappo.game.games.snowwar.gameobjects;
/*   2:    */ 
/*   3:    */ import cappo.game.games.snowwar.Direction360;
/*   4:    */ import cappo.game.games.snowwar.Direction8;
/*   5:    */ import cappo.game.games.snowwar.MathUtil;
/*   6:    */ import cappo.game.games.snowwar.PlayerTile;
/*   7:    */ import cappo.game.games.snowwar.SnowWarGameStage;
/*   8:    */ import cappo.game.games.snowwar.SnowWarRoom;
/*   9:    */ import cappo.game.games.snowwar.SquareRoot;
/*  10:    */ import cappo.game.games.snowwar.SynchronizedGameStage;
/*  11:    */ import cappo.game.games.snowwar.Tile;
/*  12:    */ 
/*  13:    */ public class SnowBallGameObject
/*  14:    */   extends GameItemObject
/*  15:    */ {
/*  16: 19 */   public static int _4s = 0;
/*  17: 20 */   public static int _09I = 1;
/*  18: 21 */   public static int _x2 = 2;
/*  19: 22 */   public static int TOPLAYER = 3;
/*  20: 24 */   public static int _2pR = 2000;
/*  21: 25 */   public static int _2t9 = 3000;
/*  22: 26 */   public static double _1Zn = 0.000707213578500707D;
/*  23: 27 */   public static double _tO = 0.000559D;
/*  24: 28 */   public static int _0v2 = 20000;
/*  25: 29 */   public static int _3uf = 60000;
/*  26: 30 */   public static int _3Xd = 100000;
/*  27: 31 */   public static int _3uE = 42000;
/*  28: 32 */   public static int _2Sx = 10;
/*  29: 33 */   public static int _0zg = 25;
/*  30: 34 */   public static int _3kL = 50;
/*  31: 35 */   public static int _0Wg = 3;
/*  32: 36 */   public static int _0Po = 15;
/*  33: 38 */   public static int[] boundingData = { 400 };
/*  34:    */   public SnowWarRoom currentSnowWar;
/*  35:    */   public int originX;
/*  36:    */   public int originY;
/*  37:    */   private final PlayerTile currentLocation;
/*  38:    */   private final Direction360 direction;
/*  39:    */   private int launchType;
/*  40:    */   private int speed;
/*  41:    */   private int ttl;
/*  42:    */   private HumanGameObject ballOwner;
/*  43:    */   private int paraOffs;
/*  44:    */   
/*  45:    */   public SnowBallGameObject(SnowWarRoom room)
/*  46:    */   {
/*  47: 54 */     super(11);
/*  48: 55 */     this.currentSnowWar = room;
/*  49: 56 */     this.currentLocation = new PlayerTile(0, 0, 0);
/*  50: 57 */     this.direction = new Direction360(0);
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void setCurLocation(int x, int y, int z)
/*  54:    */   {
/*  55: 61 */     this.currentSnowWar.checksum += x * 3 - getVariable(2) * 3;
/*  56: 62 */     this.currentSnowWar.checksum += y * 4 - getVariable(3) * 4;
/*  57: 63 */     this.currentSnowWar.checksum += z * 5 - getVariable(4) * 5;
/*  58: 64 */     this.currentLocation.setXYZ(x, y, z);
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void setTtl(int val)
/*  62:    */   {
/*  63: 68 */     this.currentSnowWar.checksum += val * 8 - getVariable(7) * 8;
/*  64: 69 */     this.ttl = val;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void setOwner(HumanGameObject val)
/*  68:    */   {
/*  69: 73 */     if (val == null) {
/*  70: 74 */       this.currentSnowWar.checksum += -9 - getVariable(8) * 9;
/*  71:    */     } else {
/*  72: 76 */       this.currentSnowWar.checksum += val.objectId * 9 - getVariable(8) * 9;
/*  73:    */     }
/*  74: 78 */     this.ballOwner = val;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void initialize(int x, int y, int type, int destX, int destY, HumanGameObject owner)
/*  78:    */   {
/*  79: 82 */     this.originX = x;
/*  80: 83 */     this.originY = y;
/*  81: 84 */     this.currentLocation.setXYZ(x, y, _2t9);
/*  82:    */     
/*  83: 86 */     int deltaX = destX - x;
/*  84: 87 */     int deltaY = destY - y;
/*  85: 88 */     deltaX = MathUtil._43Z(deltaX / 200);
/*  86: 89 */     deltaY = MathUtil._43Z(deltaY / 200);
/*  87: 90 */     this.direction._1ji(Direction360.getRot(deltaX, deltaY));
/*  88:    */     
/*  89: 92 */     int local7 = SquareRoot.sqrt(deltaX * deltaX + deltaY * deltaY) * 200;
/*  90: 93 */     this.launchType = type;
/*  91: 94 */     getMoveType(type, local7);
/*  92: 96 */     if (this.launchType == _4s)
/*  93:    */     {
/*  94: 97 */       this.ttl = (_0v2 / _2pR);
/*  95: 98 */       this.speed = _2pR;
/*  96:    */     }
/*  97:100 */     else if (this.launchType == _09I)
/*  98:    */     {
/*  99:101 */       local7 = Math.min(local7, _3uf);
/* 100:102 */       this.ttl = ((int)(local7 * _tO));
/* 101:103 */       this.speed = (this.ttl == 0 ? 0 : MathUtil._43Z(local7 / this.ttl));
/* 102:    */     }
/* 103:105 */     else if (this.launchType == _x2)
/* 104:    */     {
/* 105:106 */       local7 = Math.min(local7, _3Xd);
/* 106:107 */       this.ttl = ((int)(local7 * _1Zn));
/* 107:108 */       this.speed = (this.ttl == 0 ? 0 : MathUtil._43Z(local7 / this.ttl));
/* 108:    */     }
/* 109:112 */     this.paraOffs = MathUtil._43Z(this.ttl / 2);
/* 110:113 */     this.ballOwner = owner;
/* 111:    */   }
/* 112:    */   
/* 113:    */   private void getMoveType(int _arg1, int _arg2)
/* 114:    */   {
/* 115:120 */     if (_arg1 == TOPLAYER)
/* 116:    */     {
/* 117:121 */       if (_arg2 <= _3uE) {
/* 118:122 */         this.launchType = _4s;
/* 119:124 */       } else if (_arg2 <= _3uf) {
/* 120:125 */         this.launchType = _09I;
/* 121:    */       } else {
/* 122:127 */         this.launchType = _x2;
/* 123:    */       }
/* 124:    */     }
/* 125:    */     else {
/* 126:131 */       this.launchType = _arg1;
/* 127:    */     }
/* 128:    */   }
/* 129:    */   
/* 130:    */   public int getVariable(int val)
/* 131:    */   {
/* 132:137 */     if (val == 0) {
/* 133:138 */       return 1;
/* 134:    */     }
/* 135:140 */     if (val == 1) {
/* 136:141 */       return this.objectId;
/* 137:    */     }
/* 138:143 */     if (val == 2) {
/* 139:144 */       return this.currentLocation.x();
/* 140:    */     }
/* 141:146 */     if (val == 3) {
/* 142:147 */       return this.currentLocation.y();
/* 143:    */     }
/* 144:149 */     if (val == 4) {
/* 145:150 */       return this.currentLocation.z();
/* 146:    */     }
/* 147:152 */     if (val == 5) {
/* 148:153 */       return this.direction._2Hq();
/* 149:    */     }
/* 150:155 */     if (val == 6) {
/* 151:156 */       return this.launchType;
/* 152:    */     }
/* 153:158 */     if (val == 7) {
/* 154:159 */       return this.ttl;
/* 155:    */     }
/* 156:161 */     if (val == 8) {
/* 157:162 */       return this.ballOwner == null ? -1 : this.ballOwner.objectId;
/* 158:    */     }
/* 159:164 */     if (val == 9) {
/* 160:165 */       return this.paraOffs;
/* 161:    */     }
/* 162:168 */     return this.speed;
/* 163:    */   }
/* 164:    */   
/* 165:    */   public Direction360 direction360()
/* 166:    */   {
/* 167:174 */     return this.direction;
/* 168:    */   }
/* 169:    */   
/* 170:    */   public int[] boundingData()
/* 171:    */   {
/* 172:179 */     return boundingData;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public PlayerTile location3D()
/* 176:    */   {
/* 177:184 */     return this.currentLocation;
/* 178:    */   }
/* 179:    */   
/* 180:    */   public void subturn(SynchronizedGameStage _arg1)
/* 181:    */   {
/* 182:189 */     SnowWarRoom local1 = (SnowWarRoom)_arg1;
/* 183:190 */     if (!this._active) {
/* 184:191 */       return;
/* 185:    */     }
/* 186:193 */     setTtl(this.ttl - 1);
/* 187:194 */     if (this.launchType == _4s) {
/* 188:195 */       _3rG(_2Sx, true);
/* 189:197 */     } else if (this.launchType == _09I) {
/* 190:198 */       _3rG(_0zg, false);
/* 191:    */     } else {
/* 192:200 */       _3rG(_3kL, false);
/* 193:    */     }
/* 194:203 */     int local2 = Tile._4mC(this.currentLocation.x());
/* 195:204 */     int local3 = Tile._3FS(this.currentLocation.y());
/* 196:205 */     Tile local4 = local1.map.getTile(local2, local3);
/* 197:206 */     boolean collision = checkCollision(local4);
/* 198:207 */     if (!collision) {
/* 199:208 */       collision = local1.map.checkFloorCollision(this);
/* 200:    */     }
/* 201:210 */     if (collision) {
/* 202:211 */       local1.queueDeleteObject(this);
/* 203:    */     }
/* 204:    */   }
/* 205:    */   
/* 206:    */   private boolean checkCollision(Tile _arg2)
/* 207:    */   {
/* 208:219 */     boolean local2 = false;
/* 209:220 */     if (_arg2 != null)
/* 210:    */     {
/* 211:221 */       local2 = testSnowBallCollision(_arg2);
/* 212:222 */       if (!local2)
/* 213:    */       {
/* 214:223 */         Direction8 local3 = this.direction.direction8Value();
/* 215:224 */         local2 = testSnowBallCollision(_arg2.getNextTileAtRot(local3));
/* 216:225 */         if (!local2)
/* 217:    */         {
/* 218:226 */           local2 = testSnowBallCollision(_arg2.getNextTileAtRot(local3.rotateDirection45Degrees(false)));
/* 219:227 */           if (!local2) {
/* 220:228 */             local2 = testSnowBallCollision(_arg2.getNextTileAtRot(local3.rotateDirection45Degrees(true)));
/* 221:    */           }
/* 222:    */         }
/* 223:    */       }
/* 224:    */     }
/* 225:233 */     return local2;
/* 226:    */   }
/* 227:    */   
/* 228:    */   private boolean testSnowBallCollision(Tile _arg2)
/* 229:    */   {
/* 230:237 */     if (_arg2 != null)
/* 231:    */     {
/* 232:238 */       GameItemObject item = _arg2._4fe();
/* 233:239 */       if (item != null) {
/* 234:243 */         if (item.testSnowBallCollision(this))
/* 235:    */         {
/* 236:244 */           item.onSnowBallHit(this);
/* 237:245 */           return true;
/* 238:    */         }
/* 239:    */       }
/* 240:    */     }
/* 241:249 */     return false;
/* 242:    */   }
/* 243:    */   
/* 244:    */   private void _3rG(int _arg1, boolean _arg2)
/* 245:    */   {
/* 246:253 */     int local2 = this.currentLocation.x() + MathUtil._43Z(this.direction._0uc() * this.speed / 255);
/* 247:254 */     int local3 = this.currentLocation.y() + MathUtil._43Z(this.direction._17x() * this.speed / 255);
/* 248:255 */     int local4 = this.ttl - this.paraOffs;
/* 249:256 */     int local5 = (this.paraOffs * this.paraOffs - local4 * local4) * _arg1 + _2t9;
/* 250:257 */     if (_arg2) {
/* 251:258 */       local5 = Math.min(local5, _2t9);
/* 252:    */     }
/* 253:260 */     setCurLocation(local2, local3, local5);
/* 254:    */   }
/* 255:    */   
/* 256:    */   public String toString()
/* 257:    */   {
/* 258:265 */     return " location=(" + this.currentLocation.x() + "," + this.currentLocation.y() + "," + this.currentLocation.z() + ")" + " dir=" + this.direction + " paraOffs=" + this.paraOffs + " ttl=" + this.ttl;
/* 259:    */   }
/* 260:    */   
/* 261:    */   public HumanGameObject getAttacker()
/* 262:    */   {
/* 263:269 */     return this.ballOwner;
/* 264:    */   }
/* 265:    */   
/* 266:    */   public void onRemove() {}
/* 267:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameobjects.SnowBallGameObject
 * JD-Core Version:    0.7.0.1
 */