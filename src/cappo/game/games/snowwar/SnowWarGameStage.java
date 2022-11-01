/*   1:    */ package cappo.game.games.snowwar;
/*   2:    */ 
/*   3:    */ import cappo.game.collections.BaseItem;
/*   4:    */ import cappo.game.games.snowwar.gameobjects.GameItemObject;
/*   5:    */ import java.util.List;
/*   6:    */ 
/*   7:    */ public class SnowWarGameStage
/*   8:    */ {
/*   9: 14 */   public static int _43q = 25;
/*  10: 15 */   public static int _3hO = 25;
/*  11: 16 */   private static int _0Fs = 100000;
/*  12:    */   private Tile[][] tileMap;
/*  13:    */   
/*  14:    */   public void initialize(SnowWarArenaBase arena)
/*  15:    */   {
/*  16: 21 */     buildMap(arena);
/*  17: 22 */     addObjects(arena.fuseObjects);
/*  18:    */   }
/*  19:    */   
/*  20:    */   public static Direction8 _4Ce(Tile _arg1)
/*  21:    */   {
/*  22: 27 */     return Direction360.direction360ValueToDirection8(Direction360.getRot(_43q - _arg1._4gH[0], _3hO - _arg1._4gH[1]));
/*  23:    */   }
/*  24:    */   
/*  25:    */   public void _2Av(GameItemObject _arg1)
/*  26:    */   {
/*  27: 31 */     PlayerTile local1 = _arg1.location3D();
/*  28: 32 */     Tile local2 = getTile(Tile._4mC(local1.x()), Tile._3FS(local1.y()));
/*  29: 33 */     if (local2 != null) {
/*  30: 34 */       local2._1tH(_arg1);
/*  31:    */     }
/*  32:    */   }
/*  33:    */   
/*  34:    */   public boolean _18P(int _arg1, int _arg2)
/*  35:    */   {
/*  36: 40 */     int local1 = Tile._4mC(_arg1);
/*  37: 41 */     int local2 = Tile._3FS(_arg2);
/*  38: 42 */     Tile local3 = getTile(local1, local2);
/*  39: 43 */     if (local3 != null) {
/*  40: 44 */       return local3.isOpen(null);
/*  41:    */     }
/*  42: 46 */     return false;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public boolean checkFloorCollision(GameItemObject _arg1)
/*  46:    */   {
/*  47: 50 */     if (_arg1.location3D().z() < 1) {
/*  48: 51 */       return true;
/*  49:    */     }
/*  50: 53 */     int local1 = Tile._4mC(_arg1.location3D().x());
/*  51: 54 */     int local2 = Tile._3FS(_arg1.location3D().y());
/*  52: 55 */     Tile local3 = getTile(local1, local2);
/*  53: 56 */     if (local3 != null) {
/*  54: 57 */       return _arg1.location3D().z() < local3.height();
/*  55:    */     }
/*  56: 59 */     return false;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public Tile getTile(int _arg1, int _arg2)
/*  60:    */   {
/*  61: 63 */     if ((_arg1 < 0) || (_arg1 >= this.tileMap[0].length) || 
/*  62: 64 */       (_arg2 < 0) || (_arg2 >= this.tileMap.length)) {
/*  63: 65 */       return null;
/*  64:    */     }
/*  65: 67 */     return this.tileMap[_arg2][_arg1];
/*  66:    */   }
/*  67:    */   
/*  68:    */   private void addObjects(List<GamefuseObject> objects)
/*  69:    */   {
/*  70: 71 */     for (GamefuseObject object : objects)
/*  71:    */     {
/*  72: 72 */       Tile _arg3 = getTile(object.X, object.Y);
/*  73: 73 */       if (_arg3 != null)
/*  74:    */       {
/*  75: 74 */         _arg3._lR(object);
/*  76: 75 */         setupTile(object);
/*  77:    */       }
/*  78:    */     }
/*  79:    */   }
/*  80:    */   
/*  81:    */   private void setupTile(GamefuseObject object)
/*  82:    */   {
/*  83: 84 */     int local2 = object.Rot;
/*  84: 85 */     int local3 = object.baseItem.xDim;
/*  85: 86 */     int local4 = object.baseItem.yDim;
/*  86: 87 */     if ((local2 == Direction8.E.getRot()) || (local2 == Direction8.W.getRot()))
/*  87:    */     {
/*  88: 88 */       int local6 = local3;
/*  89: 89 */       local3 = local4;
/*  90: 90 */       local4 = local6;
/*  91:    */     }
/*  92: 92 */     int local5 = 1;
/*  93: 93 */     while (local5 < local3)
/*  94:    */     {
/*  95: 94 */       Tile local1 = getTile(object.X + local5, object.Y);
/*  96: 95 */       if (local1 != null)
/*  97:    */       {
/*  98: 96 */         local1._4AO((int)(object.baseItem.Height * Tile.TILE_SIZE));
/*  99: 97 */         if (!object.baseItem.allowWalk) {
/* 100: 98 */           local1.setBlocked(true);
/* 101:    */         }
/* 102:    */       }
/* 103:101 */       local5++;
/* 104:    */     }
/* 105:103 */     local5 = 1;
/* 106:104 */     while (local5 < local4)
/* 107:    */     {
/* 108:105 */       Tile local1 = getTile(object.X, object.Y + local5);
/* 109:106 */       if (local1 != null)
/* 110:    */       {
/* 111:107 */         local1._4AO((int)(object.baseItem.Height * Tile.TILE_SIZE));
/* 112:108 */         if (!object.baseItem.allowWalk) {
/* 113:109 */           local1.setBlocked(true);
/* 114:    */         }
/* 115:    */       }
/* 116:112 */       local5++;
/* 117:    */     }
/* 118:    */   }
/* 119:    */   
/* 120:    */   private void buildMap(SnowWarArenaBase _arg1)
/* 121:    */   {
/* 122:123 */     int[][] local1 = parseHeightMap(_arg1.HeightMap, _arg1.ArenaWidth, _arg1.ArenaHeight);
/* 123:124 */     int local2 = _arg1.ArenaHeight;
/* 124:125 */     int local3 = _arg1.ArenaWidth;
/* 125:126 */     this.tileMap = new Tile[_arg1.ArenaHeight][];
/* 126:127 */     int local5 = 0;
/* 127:128 */     while (local5 < local2)
/* 128:    */     {
/* 129:129 */       this.tileMap[local5] = new Tile[_arg1.ArenaWidth];
/* 130:130 */       int local6 = 0;
/* 131:131 */       while (local6 < local3)
/* 132:    */       {
/* 133:132 */         this.tileMap[local5][local6] = null;
/* 134:133 */         if (local1[local5][local6] != _0Fs)
/* 135:    */         {
/* 136:134 */           Tile local4 = new Tile(local6, local5);
/* 137:135 */           this.tileMap[local5][local6] = local4;
/* 138:136 */           Tile local7 = getTile(local6 + 1, local5 - 1);
/* 139:137 */           if (local7 != null) {
/* 140:138 */             local4._3iT(local7, Direction8.NE);
/* 141:    */           }
/* 142:140 */           Tile local8 = getTile(local6, local5 - 1);
/* 143:141 */           if (local8 != null) {
/* 144:142 */             local4._3iT(local8, Direction8.N);
/* 145:    */           }
/* 146:144 */           Tile local9 = getTile(local6 - 1, local5 - 1);
/* 147:145 */           if (local9 != null) {
/* 148:146 */             local4._3iT(local9, Direction8.NW);
/* 149:    */           }
/* 150:148 */           Tile local10 = getTile(local6 - 1, local5);
/* 151:149 */           if (local10 != null) {
/* 152:150 */             local4._3iT(local10, Direction8.W);
/* 153:    */           }
/* 154:    */         }
/* 155:153 */         local6++;
/* 156:    */       }
/* 157:155 */       local5++;
/* 158:    */     }
/* 159:    */   }
/* 160:    */   
/* 161:    */   private int[][] parseHeightMap(String _arg1, int _arg2, int _arg3)
/* 162:    */   {
/* 163:163 */     int local3 = 0;
/* 164:164 */     String[] local4 = _arg1.split("\r");
/* 165:165 */     int[][] local5 = new int[local4.length][];
/* 166:166 */     int local6 = 0;
/* 167:167 */     while (local6 < local4.length)
/* 168:    */     {
/* 169:168 */       String local7 = local4[local6];
/* 170:169 */       local5[local6] = new int[local7.length()];
/* 171:170 */       int local8 = local7.length() - 1;
/* 172:171 */       while (local8 >= 0)
/* 173:    */       {
/* 174:172 */         String local9 = local7.substring(local8, local8 + 1);
/* 175:173 */         if (local9.equals("x")) {
/* 176:174 */           local5[local6][local8] = _0Fs;
/* 177:    */         } else {
/* 178:    */           try
/* 179:    */           {
/* 180:177 */             local5[local6][local8] = Integer.parseInt(local9);
/* 181:    */           }
/* 182:    */           catch (Exception ex)
/* 183:    */           {
/* 184:180 */             local5[local6][local8] = (10 + (local9.charAt(0) - 'a'));
/* 185:    */           }
/* 186:    */         }
/* 187:183 */         if ((local5[local6][local8] > local3) && (local5[local6][local8] != _0Fs)) {
/* 188:184 */           local3 = local5[local6][local8];
/* 189:    */         }
/* 190:186 */         local8--;
/* 191:    */       }
/* 192:188 */       local6++;
/* 193:    */     }
/* 194:190 */     return local5;
/* 195:    */   }
/* 196:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.SnowWarGameStage
 * JD-Core Version:    0.7.0.1
 */