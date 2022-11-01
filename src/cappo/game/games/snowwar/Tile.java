/*   1:    */ package cappo.game.games.snowwar;
/*   2:    */ 
/*   3:    */ import cappo.game.collections.BaseItem;
/*   4:    */ import cappo.game.games.snowwar.gameobjects.GameItemObject;
/*   5:    */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*   6:    */ import cappo.game.games.snowwar.gameobjects.PickBallsGameItemObject;
/*   7:    */ import java.util.ArrayList;
/*   8:    */ import java.util.List;
/*   9:    */ 
/*  10:    */ public class Tile
/*  11:    */ {
/*  12: 17 */   public static int TILE_SIZE = 3200;
/*  13: 18 */   public static int _37G = MathUtil._43Z(TILE_SIZE / 2);
/*  14: 19 */   public static int _03y = TILE_SIZE + _37G;
/*  15: 20 */   public static int _1L = (int)Math.sqrt(TILE_SIZE * TILE_SIZE + TILE_SIZE * TILE_SIZE);
/*  16:    */   private final PlayerTile _location;
/*  17:    */   private final Tile[] _3ob;
/*  18:    */   private GameItemObject _0Zr;
/*  19:    */   public int[] _4gH;
/*  20:    */   private boolean blocked;
/*  21:    */   private int _height;
/*  22:    */   public PickBallsGameItemObject pickBallsItem;
/*  23:    */   private final List<GamefuseObject> _0E8;
/*  24:    */   
/*  25:    */   public Tile(int _arg1, int _arg2)
/*  26:    */   {
/*  27: 34 */     this._3ob = new Tile[8];
/*  28: 35 */     this._4gH = new int[] { _arg1, _arg2 };
/*  29: 36 */     this._location = new PlayerTile(_arg1 * TILE_SIZE, _arg2 * TILE_SIZE, 0);
/*  30: 37 */     this._0E8 = new ArrayList();
/*  31:    */   }
/*  32:    */   
/*  33:    */   public static int _4mC(int _arg1)
/*  34:    */   {
/*  35: 41 */     return MathUtil._43Z((_arg1 + _37G) / TILE_SIZE);
/*  36:    */   }
/*  37:    */   
/*  38:    */   public static int _3FS(int _arg1)
/*  39:    */   {
/*  40: 45 */     return MathUtil._43Z((_arg1 + _37G) / TILE_SIZE);
/*  41:    */   }
/*  42:    */   
/*  43:    */   public static int _3b(int _arg1)
/*  44:    */   {
/*  45: 49 */     return _arg1 * TILE_SIZE;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public static int _3Qo(int _arg1)
/*  49:    */   {
/*  50: 53 */     return _arg1 * TILE_SIZE;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public List<GamefuseObject> fuseObjects()
/*  54:    */   {
/*  55: 58 */     return this._0E8;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void _lR(GamefuseObject fuseItem)
/*  59:    */   {
/*  60: 63 */     this._0E8.add(fuseItem);
/*  61: 64 */     _4AO((int)(fuseItem.baseItem.Height * TILE_SIZE));
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void _4AO(int _arg1)
/*  65:    */   {
/*  66: 68 */     this._height += _arg1;
/*  67: 69 */     if (this._height < 0) {
/*  68: 70 */       this._height = 0;
/*  69:    */     }
/*  70:    */   }
/*  71:    */   
/*  72:    */   public int[] _4iL()
/*  73:    */   {
/*  74: 76 */     return this._4gH;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public PlayerTile location()
/*  78:    */   {
/*  79: 80 */     return this._location;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public boolean isTooAway(PlayerTile _arg1)
/*  83:    */   {
/*  84: 84 */     int local1 = this._location.x() - _arg1.x();
/*  85: 85 */     if (local1 < 0) {
/*  86: 86 */       local1 = -local1;
/*  87:    */     }
/*  88: 88 */     int local2 = this._location.y() - _arg1.y();
/*  89: 89 */     if (local2 < 0) {
/*  90: 90 */       local2 = -local2;
/*  91:    */     }
/*  92: 92 */     return (local1 < _37G) && (local2 < _37G);
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void _3iT(Tile _arg1, Direction8 _arg2)
/*  96:    */   {
/*  97: 96 */     _2UR(_arg1, _arg2);
/*  98: 97 */     _arg1._2UR(this, _arg2.rotateDirection180Degrees());
/*  99:    */   }
/* 100:    */   
/* 101:    */   private void _2UR(Tile _arg1, Direction8 _arg2)
/* 102:    */   {
/* 103:101 */     this._3ob[_arg2.getRot()] = _arg1;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public Tile getNextTileAtRot(Direction8 _arg1)
/* 107:    */   {
/* 108:105 */     return this._3ob[_arg1.getRot()];
/* 109:    */   }
/* 110:    */   
/* 111:    */   public boolean isOpen(GameItemObject _arg1)
/* 112:    */   {
/* 113:110 */     boolean isBlocked = false;
/* 114:111 */     if (this._0E8.size() == 1) {
/* 115:112 */       isBlocked = !((GamefuseObject)this._0E8.get(0)).baseItem.allowWalk;
/* 116:114 */     } else if (!this._0E8.isEmpty()) {
/* 117:115 */       isBlocked = true;
/* 118:    */     }
/* 119:119 */     return (!isBlocked) && (this._0Zr == null) && (!this.blocked);
/* 120:    */   }
/* 121:    */   
/* 122:    */   public boolean _1tH(GameItemObject _arg1)
/* 123:    */   {
/* 124:124 */     boolean _arg2 = false;
/* 125:125 */     if (this._0Zr == null)
/* 126:    */     {
/* 127:126 */       this._0Zr = _arg1;
/* 128:127 */       _arg2 = true;
/* 129:    */     }
/* 130:129 */     return _arg2;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public GameItemObject removeGameObject()
/* 134:    */   {
/* 135:134 */     GameItemObject local0 = null;
/* 136:135 */     if (this._0Zr != null)
/* 137:    */     {
/* 138:136 */       local0 = this._0Zr;
/* 139:137 */       this._0Zr = null;
/* 140:    */     }
/* 141:139 */     return local0;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public GameItemObject _4fe()
/* 145:    */   {
/* 146:143 */     return this._0Zr;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public HumanGameObject _05Z()
/* 150:    */   {
/* 151:147 */     if ((this._0Zr != null) && ((this._0Zr instanceof HumanGameObject))) {
/* 152:148 */       return (HumanGameObject)this._0Zr;
/* 153:    */     }
/* 154:150 */     return null;
/* 155:    */   }
/* 156:    */   
/* 157:    */   public HumanGameObject _40T()
/* 158:    */   {
/* 159:154 */     HumanGameObject local0 = _05Z();
/* 160:155 */     if (local0 != null) {
/* 161:156 */       this._0Zr = null;
/* 162:    */     }
/* 163:158 */     return local0;
/* 164:    */   }
/* 165:    */   
/* 166:    */   public int distanceTo(Tile _arg1)
/* 167:    */   {
/* 168:163 */     return this._location.distanceTo(_arg1.location());
/* 169:    */   }
/* 170:    */   
/* 171:    */   public Direction8 directionTo(Tile _arg1)
/* 172:    */   {
/* 173:168 */     return this._location.directionTo(_arg1.location());
/* 174:    */   }
/* 175:    */   
/* 176:    */   public Tile getNodeAt(Direction8 _arg1)
/* 177:    */   {
/* 178:173 */     return this._3ob[_arg1.getRot()];
/* 179:    */   }
/* 180:    */   
/* 181:    */   public boolean directionIsBlocked(Direction8 _arg1, GameItemObject _arg2)
/* 182:    */   {
/* 183:178 */     return isOpen(_arg2);
/* 184:    */   }
/* 185:    */   
/* 186:    */   public int getPathCost(Direction8 _arg1, GameItemObject _arg2)
/* 187:    */   {
/* 188:183 */     if (_arg1._AC()) {
/* 189:184 */       return TILE_SIZE;
/* 190:    */     }
/* 191:187 */     return _1L;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public int height()
/* 195:    */   {
/* 196:191 */     return this._height;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public String toString()
/* 200:    */   {
/* 201:196 */     return " X:" + this._location.x() + " Y:" + this._location.y() + " Z:" + this._location.z();
/* 202:    */   }
/* 203:    */   
/* 204:    */   public void setBlocked(boolean block)
/* 205:    */   {
/* 206:200 */     this.blocked = block;
/* 207:    */   }
/* 208:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.Tile
 * JD-Core Version:    0.7.0.1
 */