/*   1:    */ package cappo.game.roomengine.entity.item.floor;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.Database;
/*   4:    */ import cappo.engine.player.Connection;
/*   5:    */ import cappo.engine.threadpools.RoomTask;
/*   6:    */ import cappo.game.collections.BaseItem;
/*   7:    */ import cappo.game.collections.BflyData;
/*   8:    */ import cappo.game.games.snowwar.Direction8;
/*   9:    */ import cappo.game.roomengine.entity.item.RoomItemData;
/*  10:    */ import cappo.game.roomengine.gamemap.GameMapBase;
/*  11:    */ import cappo.game.roomengine.itemInteractor.Interactor;
/*  12:    */ import java.util.ArrayList;
/*  13:    */ import java.util.List;
/*  14:    */ import java.util.Map;
/*  15:    */ 
/*  16:    */ public class RoomFloorItemData
/*  17:    */   extends RoomItemData
/*  18:    */ {
/*  19:    */   private final FloorItem floorItem;
/*  20:    */   private int x;
/*  21:    */   private int y;
/*  22:    */   private int xy;
/*  23: 19 */   private Direction8 dir = Direction8.N;
/*  24:    */   private float z;
/*  25:    */   
/*  26:    */   public RoomFloorItemData(RoomTask room, FloorItem item)
/*  27:    */   {
/*  28: 23 */     super(item.baseItem, room);
/*  29: 24 */     this.floorItem = item;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void setPosition()
/*  33:    */   {
/*  34: 28 */     setXy(this.x + this.y * this.currentRoom.model.widthX);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void setPosition(int argX, int argY, int argXY)
/*  38:    */   {
/*  39: 32 */     setX(argX);
/*  40: 33 */     setY(argY);
/*  41: 34 */     setXy(argXY);
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void setPosition(int x, int y)
/*  45:    */   {
/*  46: 38 */     setXy(x + y * this.currentRoom.model.widthX);
/*  47:    */     
/*  48:    */ 
/*  49: 41 */     setX(x);
/*  50: 42 */     setY(y);
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void setPosition(int xy)
/*  54:    */   {
/*  55: 46 */     setXy(xy);
/*  56:    */     
/*  57:    */ 
/*  58: 49 */     setY(xy / this.currentRoom.model.widthX);
/*  59: 50 */     setX(xy - this.y * this.currentRoom.model.widthX);
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void setPosition(float val)
/*  63:    */   {
/*  64: 54 */     setZ(val);
/*  65:    */   }
/*  66:    */   
/*  67:    */   public List<AffectedTile> getOutSideTiles()
/*  68:    */   {
/*  69: 58 */     int mapSizeX = this.currentRoom.model.widthX;
/*  70: 59 */     int mapSizeY = this.currentRoom.model.heightY;
/*  71:    */     
/*  72: 61 */     boolean swap = Direction8.haveDirection(this.dir, new Direction8[] { Direction8.N, Direction8.S });
/*  73: 62 */     int xDim = swap ? this.baseItem.xDim : this.baseItem.yDim;
/*  74: 63 */     int yDim = swap ? this.baseItem.yDim : this.baseItem.xDim;
/*  75:    */     
/*  76: 65 */     List<AffectedTile> PointList = new ArrayList();
/*  77: 67 */     if ((this.x > 0) && (this.y > 0))
/*  78:    */     {
/*  79: 69 */       int newXY2 = this.xy - 1 - mapSizeX;
/*  80: 70 */       if (this.currentRoom.validTile(newXY2)) {
/*  81: 71 */         PointList.add(new AffectedTile(newXY2, mapSizeX));
/*  82:    */       }
/*  83:    */     }
/*  84: 75 */     boolean yFree = getY() + yDim < mapSizeY;
/*  85: 76 */     for (int i = 0; i <= xDim; i++)
/*  86:    */     {
/*  87: 77 */       if (yFree)
/*  88:    */       {
/*  89: 79 */         int newXY2 = this.xy + i + yDim * mapSizeX;
/*  90: 80 */         if (this.currentRoom.validTile(newXY2)) {
/*  91: 81 */           PointList.add(new AffectedTile(newXY2, mapSizeX));
/*  92:    */         }
/*  93:    */       }
/*  94: 85 */       if (getY() > 0)
/*  95:    */       {
/*  96: 87 */         int newXY2 = this.xy + i - mapSizeX;
/*  97: 88 */         if (this.currentRoom.validTile(newXY2)) {
/*  98: 89 */           PointList.add(new AffectedTile(newXY2, mapSizeX));
/*  99:    */         }
/* 100:    */       }
/* 101:    */     }
/* 102: 94 */     boolean xFree = getX() + xDim < mapSizeX;
/* 103: 95 */     for (int i = 0; i <= yDim; i++)
/* 104:    */     {
/* 105: 96 */       if (xFree)
/* 106:    */       {
/* 107: 98 */         int newXY2 = this.xy + xDim + i * mapSizeX;
/* 108: 99 */         if (this.currentRoom.validTile(newXY2)) {
/* 109:100 */           PointList.add(new AffectedTile(newXY2, mapSizeX));
/* 110:    */         }
/* 111:    */       }
/* 112:104 */       if (getX() > 0)
/* 113:    */       {
/* 114:106 */         int newXY2 = this.xy - 1 + i * mapSizeX;
/* 115:107 */         if (this.currentRoom.validTile(newXY2)) {
/* 116:108 */           PointList.add(new AffectedTile(newXY2, mapSizeX));
/* 117:    */         }
/* 118:    */       }
/* 119:    */     }
/* 120:112 */     return PointList;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public boolean itemsOnTop()
/* 124:    */   {
/* 125:116 */     List<AffectedTile> PointList = getAffectedTiles();
/* 126:117 */     for (AffectedTile Tile : PointList)
/* 127:    */     {
/* 128:118 */       float tmp = getZ() + this.baseItem.Height;
/* 129:119 */       Float f = (Float)this.currentRoom.squareAbsoluteHeight.get(Integer.valueOf(Tile.xy));
/* 130:120 */       if ((f != null) && (tmp < f.floatValue())) {
/* 131:121 */         return true;
/* 132:    */       }
/* 133:    */     }
/* 134:124 */     return false;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public List<AffectedTile> getAffectedTiles()
/* 138:    */   {
/* 139:128 */     return getAffectedTiles(this.xy, this.dir);
/* 140:    */   }
/* 141:    */   
/* 142:    */   public List<AffectedTile> getAffectedTiles(boolean asd)
/* 143:    */   {
/* 144:132 */     return getAffectedTiles(this.x, this.y, this.dir);
/* 145:    */   }
/* 146:    */   
/* 147:    */   public List<AffectedTile> getAffectedTiles(int xy, Direction8 dir)
/* 148:    */   {
/* 149:136 */     int mapSizeX = this.currentRoom.model.widthX;
/* 150:    */     
/* 151:138 */     boolean swap = Direction8.haveDirection(dir, new Direction8[] { Direction8.N, Direction8.S });
/* 152:139 */     int xDim = swap ? this.baseItem.xDim : this.baseItem.yDim;
/* 153:140 */     int yDim = swap ? this.baseItem.yDim : this.baseItem.xDim;
/* 154:    */     
/* 155:142 */     List<AffectedTile> PointList = new ArrayList();
/* 156:143 */     for (int i = 0; i < xDim; i++) {
/* 157:144 */       for (int j = 0; j < yDim; j++)
/* 158:    */       {
/* 159:146 */         int newXY2 = xy + i + j * mapSizeX;
/* 160:147 */         PointList.add(new AffectedTile(newXY2, mapSizeX));
/* 161:    */       }
/* 162:    */     }
/* 163:150 */     return PointList;
/* 164:    */   }
/* 165:    */   
/* 166:    */   public List<AffectedTile> getAffectedTiles(int x, int y, Direction8 dir)
/* 167:    */   {
/* 168:154 */     boolean swap = Direction8.haveDirection(dir, new Direction8[] { Direction8.N, Direction8.S });
/* 169:155 */     int xDim = swap ? this.baseItem.xDim : this.baseItem.yDim;
/* 170:156 */     int yDim = swap ? this.baseItem.yDim : this.baseItem.xDim;
/* 171:    */     
/* 172:158 */     List<AffectedTile> PointList = new ArrayList();
/* 173:159 */     for (int i = 0; i < xDim; i++) {
/* 174:160 */       for (int j = 0; j < yDim; j++)
/* 175:    */       {
/* 176:161 */         int vX = x + i;
/* 177:162 */         int vY = y + j;
/* 178:163 */         PointList.add(new AffectedTile(vX, vY, true));
/* 179:    */       }
/* 180:    */     }
/* 181:166 */     return PointList;
/* 182:    */   }
/* 183:    */   
/* 184:    */   public byte[] SquareInFront()
/* 185:    */   {
/* 186:170 */     return new byte[] { (byte)(this.x + this.dir.getDiffX()), (byte)(this.y + this.dir.getDiffY()) };
/* 187:    */   }
/* 188:    */   
/* 189:    */   public byte[] SquareBehind()
/* 190:    */   {
/* 191:174 */     Direction8 tmp = this.dir.rotateDirection180Degrees();
/* 192:175 */     return new byte[] { (byte)(this.x + tmp.getDiffX()), (byte)(this.y + tmp.getDiffY()) };
/* 193:    */   }
/* 194:    */   
/* 195:    */   public void finishPlace(List<AffectedTile> Points)
/* 196:    */   {
/* 197:180 */     for (AffectedTile Tile : Points)
/* 198:    */     {
/* 199:181 */       int xy = Tile.x + Tile.y * this.currentRoom.model.widthX;
/* 200:182 */       this.currentRoom.generateSquare(xy, this.floorItem, true, true);
/* 201:    */     }
/* 202:185 */     if ((this.floorItem instanceof GenericFloorItem)) {
/* 203:186 */       this.baseItem.interactor.OnPlace(this.currentRoom, null, (GenericFloorItem)this.floorItem);
/* 204:    */     }
/* 205:189 */     this.currentRoom.FloorItems.put(Integer.valueOf(this.floorItem.itemId), this.floorItem);
/* 206:    */   }
/* 207:    */   
/* 208:    */   public void finishPlace(Connection user, List<AffectedTile> Points, boolean add)
/* 209:    */   {
/* 210:193 */     for (AffectedTile Tile : Points) {
/* 211:194 */       this.currentRoom.generateSquare(Tile.xy, this.floorItem, true, false);
/* 212:    */     }
/* 213:197 */     if (add)
/* 214:    */     {
/* 215:198 */       if ((this.floorItem instanceof GenericFloorItem)) {
/* 216:199 */         this.baseItem.interactor.OnPlace(this.currentRoom, user, (GenericFloorItem)this.floorItem);
/* 217:    */       }
/* 218:202 */       this.currentRoom.FloorItems.put(Integer.valueOf(this.floorItem.itemId), this.floorItem);
/* 219:    */     }
/* 220:    */   }
/* 221:    */   
/* 222:    */   public static class AffectedTile
/* 223:    */   {
/* 224:    */     public int x;
/* 225:    */     public int y;
/* 226:    */     public int xy;
/* 227:    */     
/* 228:    */     public AffectedTile(int XY, int mapSizeX)
/* 229:    */     {
/* 230:213 */       this.y = (XY / mapSizeX);
/* 231:214 */       this.x = (XY - this.y * mapSizeX);
/* 232:215 */       this.xy = XY;
/* 233:    */     }
/* 234:    */     
/* 235:    */     public AffectedTile(int X, int Y, boolean n)
/* 236:    */     {
/* 237:219 */       this.x = X;
/* 238:220 */       this.y = Y;
/* 239:    */     }
/* 240:    */   }
/* 241:    */   
/* 242:    */   public void save()
/* 243:    */     throws Exception
/* 244:    */   {
/* 245:225 */     Database.exec("UPDATE furnis_roomdata SET a='" + BflyData.Combine(this.x, this.y) + "',b='" + this.z + "',r=" + this.dir.getRot() + " WHERE id=" + this.floorItem.itemId + ";", new Object[0]);
/* 246:    */   }
/* 247:    */   
/* 248:    */   public int getX()
/* 249:    */   {
/* 250:229 */     return this.x;
/* 251:    */   }
/* 252:    */   
/* 253:    */   public void setX(int x)
/* 254:    */   {
/* 255:233 */     this.x = x;
/* 256:    */   }
/* 257:    */   
/* 258:    */   public int getY()
/* 259:    */   {
/* 260:237 */     return this.y;
/* 261:    */   }
/* 262:    */   
/* 263:    */   public void setY(int y)
/* 264:    */   {
/* 265:241 */     this.y = y;
/* 266:    */   }
/* 267:    */   
/* 268:    */   public int getXy()
/* 269:    */   {
/* 270:245 */     return this.xy;
/* 271:    */   }
/* 272:    */   
/* 273:    */   public void setXy(int xy)
/* 274:    */   {
/* 275:249 */     this.xy = xy;
/* 276:    */   }
/* 277:    */   
/* 278:    */   public Direction8 getDir()
/* 279:    */   {
/* 280:253 */     return this.dir;
/* 281:    */   }
/* 282:    */   
/* 283:    */   public void setDir(Direction8 dir)
/* 284:    */   {
/* 285:257 */     this.dir = dir;
/* 286:    */   }
/* 287:    */   
/* 288:    */   public float getZ()
/* 289:    */   {
/* 290:261 */     return this.z;
/* 291:    */   }
/* 292:    */   
/* 293:    */   public void setZ(float z)
/* 294:    */   {
/* 295:265 */     this.z = z;
/* 296:    */   }
/* 297:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.RoomFloorItemData
 * JD-Core Version:    0.7.0.1
 */