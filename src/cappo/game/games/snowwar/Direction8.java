/*   1:    */ package cappo.game.games.snowwar;
/*   2:    */ 
/*   3:    */ import cappo.engine.logging.Log;
/*   4:    */ 
/*   5:    */ public class Direction8
/*   6:    */ {
/*   7: 12 */   public static Direction8[] DIRECTIONS = new Direction8[8];
/*   8: 13 */   public static Direction8 N = new Direction8(0, "N", 0, -1);
/*   9: 14 */   public static Direction8 NE = new Direction8(1, "NE", 1, -1);
/*  10: 15 */   public static Direction8 E = new Direction8(2, "E", 1, 0);
/*  11: 16 */   public static Direction8 SE = new Direction8(3, "SE", 1, 1);
/*  12: 17 */   public static Direction8 S = new Direction8(4, "S", 0, 1);
/*  13: 18 */   public static Direction8 SW = new Direction8(5, "SW", -1, 1);
/*  14: 19 */   public static Direction8 W = new Direction8(6, "W", -1, 0);
/*  15: 20 */   public static Direction8 NW = new Direction8(7, "NW", -1, -1);
/*  16:    */   private final int rot;
/*  17:    */   private final int xDiff;
/*  18:    */   private final int yDiff;
/*  19:    */   private final String rotName;
/*  20:    */   
/*  21:    */   public Direction8(int _arg1, String _arg2, int _arg3, int _arg4)
/*  22:    */   {
/*  23: 28 */     this.rot = _arg1;
/*  24: 29 */     this.rotName = _arg2;
/*  25: 30 */     this.xDiff = _arg3;
/*  26: 31 */     this.yDiff = _arg4;
/*  27: 32 */     DIRECTIONS[_arg1] = this;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public static Direction8 getDirection(int dir)
/*  31:    */   {
/*  32: 36 */     if ((dir < 0) || (dir > 7)) {
/*  33: 37 */       return N;
/*  34:    */     }
/*  35: 40 */     return DIRECTIONS[dir];
/*  36:    */   }
/*  37:    */   
/*  38:    */   public static int validateDirection8Value(int dir)
/*  39:    */   {
/*  40: 44 */     return dir & 0x7;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public static Direction8 getRot(int curX, int curY, int targetX, int targetY)
/*  44:    */   {
/*  45: 48 */     int deltaX = targetX - curX;
/*  46: 49 */     int deltaY = targetY - curY;
/*  47: 51 */     if (deltaX == 0)
/*  48:    */     {
/*  49: 52 */       if (deltaY < 0) {
/*  50: 53 */         return N;
/*  51:    */       }
/*  52: 56 */       if (deltaY > 0) {
/*  53: 57 */         return S;
/*  54:    */       }
/*  55:    */     }
/*  56: 61 */     if (deltaX > 0)
/*  57:    */     {
/*  58: 62 */       if (deltaY < 0) {
/*  59: 63 */         return NE;
/*  60:    */       }
/*  61: 66 */       if (deltaY == 0) {
/*  62: 67 */         return E;
/*  63:    */       }
/*  64: 70 */       if (deltaY > 0) {
/*  65: 71 */         return SE;
/*  66:    */       }
/*  67:    */     }
/*  68: 75 */     if (deltaX < 0)
/*  69:    */     {
/*  70: 76 */       if (deltaY < 0) {
/*  71: 77 */         return NW;
/*  72:    */       }
/*  73: 80 */       if (deltaY == 0) {
/*  74: 81 */         return W;
/*  75:    */       }
/*  76: 84 */       if (deltaY > 0) {
/*  77: 85 */         return SW;
/*  78:    */       }
/*  79:    */     }
/*  80: 89 */     Log.printLog("ERROR: Direction8.getRot == NULL");
/*  81:    */     
/*  82: 91 */     return null;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public int getRot()
/*  86:    */   {
/*  87: 95 */     return this.rot;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public Direction8 rotateDirection180Degrees()
/*  91:    */   {
/*  92: 99 */     return getDirectionAtRot(4);
/*  93:    */   }
/*  94:    */   
/*  95:    */   public Direction8 rotateDirection45Degrees(boolean _arg1)
/*  96:    */   {
/*  97:103 */     return getDirectionAtRot(_arg1 ? 1 : -1);
/*  98:    */   }
/*  99:    */   
/* 100:    */   public Direction8 rotateDirection90Degrees(boolean _arg1)
/* 101:    */   {
/* 102:107 */     return getDirectionAtRot(_arg1 ? 2 : -2);
/* 103:    */   }
/* 104:    */   
/* 105:    */   public boolean _AC()
/* 106:    */   {
/* 107:112 */     return this.rot % 2 == 0;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public int _3f4()
/* 111:    */   {
/* 112:117 */     return this.rot;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public Direction8 getDirectionAtRot(int diff)
/* 116:    */   {
/* 117:121 */     return DIRECTIONS[validateDirection8Value(this.rot + diff)];
/* 118:    */   }
/* 119:    */   
/* 120:    */   public String toString()
/* 121:    */   {
/* 122:126 */     return this.rotName + "(" + Integer.toString(this.rot) + ")";
/* 123:    */   }
/* 124:    */   
/* 125:    */   public String getRotName()
/* 126:    */   {
/* 127:130 */     return this.rotName;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public int getDiffX()
/* 131:    */   {
/* 132:134 */     return this.xDiff;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public int getDiffY()
/* 136:    */   {
/* 137:138 */     return this.yDiff;
/* 138:    */   }
/* 139:    */   
/* 140:    */   public static boolean haveDirection(Direction8 find, Direction8... directions)
/* 141:    */   {
/* 142:142 */     for (Direction8 val : directions) {
/* 143:143 */       if (find == val) {
/* 144:144 */         return true;
/* 145:    */       }
/* 146:    */     }
/* 147:148 */     return false;
/* 148:    */   }
/* 149:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.Direction8
 * JD-Core Version:    0.7.0.1
 */