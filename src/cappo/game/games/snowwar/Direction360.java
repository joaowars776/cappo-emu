/*   1:    */ package cappo.game.games.snowwar;
/*   2:    */ 
/*   3:    */ public class Direction360
/*   4:    */ {
/*   5: 10 */   public static int N = 0;
/*   6: 11 */   public static int NE = 45;
/*   7: 12 */   public static int E = 90;
/*   8: 13 */   public static int SE = 135;
/*   9: 14 */   public static int S = 180;
/*  10: 15 */   public static int SW = 225;
/*  11: 16 */   public static int W = 270;
/*  12: 17 */   public static int NW = 315;
/*  13: 19 */   private static int[][] _4G = { { 0, 4, 8, 13, 17, 22, 26, 31, 35, 40, 44, 48, 53, 57, 61, 66, 70, 74, 79, 83, 87, 91, 95, 100, 104, 108, 112, 116, 120, 124, 127, 131, 135, 139, 143, 146, 150, 154, 157, 161, 164, 167, 171, 174, 177, 181, 184, 187, 190, 193, 196, 198, 201, 204, 207, 209, 212, 214, 217, 219, 221, 223, 226, 228, 230, 232, 233, 235, 237, 238, 240, 242, 243, 244, 246, 247, 248, 249, 250, 251, 252, 252, 253, 254, 254, 255, 255, 255, 255, 255, 256, 255, 255, 255, 255, 255, 254, 254, 253, 252, 252, 251, 250, 249, 248, 247, 246, 244, 243, 242, 240, 238, 237, 235, 233, 232, 230, 228, 226, 223, 221, 219, 217, 214, 212, 209, 207, 204, 201, 198, 196, 193, 190, 187, 184, 181, 177, 174, 171, 167, 164, 161, 157, 154, 150, 146, 143, 139, 135, 131, 127, 124, 120, 116, 112, 108, 104, 100, 95, 91, 87, 83, 79, 74, 70, 66, 61, 57, 53, 48, 44, 40, 35, 31, 26, 22, 17, 13, 8, 4, 0, -4, -8, -13, -17, -22, -26, -31, -35, -40, -44, -48, -53, -57, -61, -66, -70, -74, -79, -83, -87, -91, -95, -100, -104, -108, -112, -116, -120, -124, -128, -131, -135, -139, -143, -146, -150, -154, -157, -161, -164, -167, -171, -174, -177, -181, -184, -187, -190, -193, -196, -198, -201, -204, -207, -209, -212, -214, -217, -219, -221, -223, -226, -228, -230, -232, -233, -235, -237, -238, -240, -242, -243, -244, -246, -247, -248, -249, -250, -251, -252, -252, -253, -254, -254, -255, -255, -255, -255, -255, -256, -255, -255, -255, -255, -255, -254, -254, -253, -252, -252, -251, -250, -249, -248, -247, -246, -244, -243, -242, -240, -238, -237, -235, -233, -232, -230, -228, -226, -223, -221, -219, -217, -214, -212, -209, -207, -204, -201, -198, -196, -193, -190, -187, -184, -181, -177, -174, -171, -167, -164, -161, -157, -154, -150, -146, -143, -139, -135, -131, -128, -124, -120, -116, -112, -108, -104, -100, -95, -91, -87, -83, -79, -74, -70, -66, -61, -57, -53, -48, -44, -40, -35, -31, -26, -22, -17, -13, -8, -4 }, { -256, -255, -255, -255, -255, -255, -254, -254, -253, -252, -252, -251, -250, -249, -248, -247, -246, -244, -243, -242, -240, -238, -237, -235, -233, -232, -230, -228, -226, -223, -221, -219, -217, -214, -212, -209, -207, -204, -201, -198, -196, -193, -190, -187, -184, -181, -177, -174, -171, -167, -164, -161, -157, -154, -150, -146, -143, -139, -135, -131, -128, -124, -120, -116, -112, -108, -104, -100, -95, -91, -87, -83, -79, -74, -70, -66, -61, -57, -53, -48, -44, -40, -35, -31, -26, -22, -17, -13, -8, -4, 0, 4, 8, 13, 17, 22, 26, 31, 35, 40, 44, 48, 53, 57, 61, 66, 70, 74, 79, 83, 87, 91, 95, 100, 104, 108, 112, 116, 120, 124, 127, 131, 135, 139, 143, 146, 150, 154, 157, 161, 164, 167, 171, 174, 177, 181, 184, 187, 190, 193, 196, 198, 201, 204, 207, 209, 212, 214, 217, 219, 221, 223, 226, 228, 230, 232, 233, 235, 237, 238, 240, 242, 243, 244, 246, 247, 248, 249, 250, 251, 252, 252, 253, 254, 254, 255, 255, 255, 255, 255, 256, 255, 255, 255, 255, 255, 254, 254, 253, 252, 252, 251, 250, 249, 248, 247, 246, 244, 243, 242, 240, 238, 237, 235, 233, 232, 230, 228, 226, 223, 221, 219, 217, 214, 212, 209, 207, 204, 201, 198, 196, 193, 190, 187, 184, 181, 177, 174, 171, 167, 164, 161, 157, 154, 150, 146, 143, 139, 135, 131, 128, 124, 120, 116, 112, 108, 104, 100, 95, 91, 87, 83, 79, 74, 70, 66, 61, 57, 53, 48, 44, 40, 35, 31, 26, 22, 17, 13, 8, 4, 0, -4, -8, -13, -17, -22, -26, -31, -35, -40, -44, -48, -53, -57, -61, -66, -70, -74, -79, -83, -87, -91, -95, -100, -104, -108, -112, -116, -120, -124, -128, -131, -135, -139, -143, -146, -150, -154, -157, -161, -164, -167, -171, -174, -177, -181, -184, -187, -190, -193, -196, -198, -201, -204, -207, -209, -212, -214, -217, -219, -221, -223, -226, -228, -230, -232, -233, -235, -237, -238, -240, -242, -243, -244, -246, -247, -248, -249, -250, -251, -252, -252, -253, -254, -254, -255, -255, -255, -255, -255 } };
/*  14: 20 */   private static int[] _1MD = { 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 10, 11, 11, 11, 11, 12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 14, 14, 14, 14, 15, 15, 15, 15, 15, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19, 20, 20, 20, 20, 20, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 24, 24, 24, 24, 24, 24, 25, 25, 25, 25, 25, 26, 26, 26, 26, 26, 26, 27, 27, 27, 27, 27, 28, 28, 28, 28, 28, 28, 29, 29, 29, 29, 29, 29, 30, 30, 30, 30, 30, 30, 31, 31, 31, 31, 31, 31, 32, 32, 32, 32, 32, 32, 33, 33, 33, 33, 33, 33, 34, 34, 34, 34, 34, 34, 34, 35, 35, 35, 35, 35, 35, 36, 36, 36, 36, 36, 36, 36, 37, 37, 37, 37, 37, 37, 37, 38, 38, 38, 38, 38, 38, 38, 39, 39, 39, 39, 39, 39, 39, 39, 40, 40, 40, 40, 40, 40, 40, 41, 41, 41, 41, 41, 41, 41, 41, 42, 42, 42, 42, 42, 42, 42, 42, 43, 43, 43, 43, 43, 43, 43, 43, 44, 44, 44, 44, 44, 44, 44, 44, 44, 45, 45, 45, 45, 45 };
/*  15: 22 */   private int _4XG = 0;
/*  16: 23 */   private boolean _3zG = false;
/*  17:    */   
/*  18:    */   public Direction360(int _arg1)
/*  19:    */   {
/*  20: 27 */     this._4XG = _arg1;
/*  21:    */   }
/*  22:    */   
/*  23:    */   public static int validateDirection360Value(int _arg1)
/*  24:    */   {
/*  25: 30 */     if (_arg1 > 359) {
/*  26: 31 */       _arg1 %= 360;
/*  27: 33 */     } else if (_arg1 < 0) {
/*  28: 34 */       _arg1 = 360 + _arg1 % 360;
/*  29:    */     }
/*  30: 37 */     return _arg1;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public static Direction8 direction360ValueToDirection8(int _arg1)
/*  34:    */   {
/*  35: 40 */     return Direction8.getDirection(Direction8.validateDirection8Value(MathUtil._43Z(validateDirection360Value(_arg1 - 22) / 45) + 1));
/*  36:    */   }
/*  37:    */   
/*  38:    */   public static int direction8ToDirection360Value(Direction8 _arg1)
/*  39:    */   {
/*  40: 43 */     switch (_arg1.getRot())
/*  41:    */     {
/*  42:    */     case 0: 
/*  43: 45 */       return N;
/*  44:    */     case 1: 
/*  45: 47 */       return NE;
/*  46:    */     case 2: 
/*  47: 49 */       return E;
/*  48:    */     case 3: 
/*  49: 51 */       return SE;
/*  50:    */     case 4: 
/*  51: 53 */       return S;
/*  52:    */     case 5: 
/*  53: 55 */       return SW;
/*  54:    */     case 6: 
/*  55: 57 */       return W;
/*  56:    */     case 7: 
/*  57: 59 */       return NW;
/*  58:    */     }
/*  59: 61 */     return -1;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public static int _0uc(int _arg1)
/*  63:    */   {
/*  64: 64 */     _arg1 = validateDirection360Value(_arg1);
/*  65: 65 */     return _4G[0][_arg1];
/*  66:    */   }
/*  67:    */   
/*  68:    */   public static int _17x(int _arg1)
/*  69:    */   {
/*  70: 68 */     _arg1 = validateDirection360Value(_arg1);
/*  71: 69 */     return _4G[1][_arg1];
/*  72:    */   }
/*  73:    */   
/*  74:    */   public static int getRot(int deltaX, int deltaY)
/*  75:    */   {
/*  76: 73 */     if (_1xd(deltaX) <= _1xd(deltaY))
/*  77:    */     {
/*  78: 74 */       if (deltaY == 0) {
/*  79: 75 */         deltaY = 1;
/*  80:    */       }
/*  81: 77 */       deltaX *= 256;
/*  82: 78 */       int local2 = MathUtil._43Z(deltaX / deltaY);
/*  83: 79 */       if (local2 < 0) {
/*  84: 80 */         local2 = -local2;
/*  85:    */       }
/*  86: 82 */       if (local2 > 255) {
/*  87: 83 */         local2 = 255;
/*  88:    */       }
/*  89: 85 */       if (deltaY < 0)
/*  90:    */       {
/*  91: 86 */         if (deltaX > 0) {
/*  92: 87 */           return _1MD[local2];
/*  93:    */         }
/*  94: 89 */         return 360 - _1MD[local2];
/*  95:    */       }
/*  96: 91 */       if (deltaX > 0) {
/*  97: 92 */         return 180 - _1MD[local2];
/*  98:    */       }
/*  99: 94 */       return 180 + _1MD[local2];
/* 100:    */     }
/* 101: 96 */     if (deltaX == 0) {
/* 102: 97 */       deltaX = 1;
/* 103:    */     }
/* 104: 99 */     deltaY *= 256;
/* 105:100 */     int local2 = MathUtil._43Z(deltaY / deltaX);
/* 106:101 */     if (local2 < 0) {
/* 107:102 */       local2 = -local2;
/* 108:    */     }
/* 109:104 */     if (local2 > 255) {
/* 110:105 */       local2 = 255;
/* 111:    */     }
/* 112:107 */     if (deltaY < 0)
/* 113:    */     {
/* 114:108 */       if (deltaX > 0) {
/* 115:109 */         return 90 - _1MD[local2];
/* 116:    */       }
/* 117:111 */       return 270 + _1MD[local2];
/* 118:    */     }
/* 119:113 */     if (deltaX > 0) {
/* 120:114 */       return 90 + _1MD[local2];
/* 121:    */     }
/* 122:116 */     return 270 - _1MD[local2];
/* 123:    */   }
/* 124:    */   
/* 125:    */   public static int _1xd(int _arg1)
/* 126:    */   {
/* 127:119 */     if (_arg1 < 0) {
/* 128:120 */       return -_arg1;
/* 129:    */     }
/* 130:122 */     return _arg1;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public void dispose()
/* 134:    */   {
/* 135:126 */     this._3zG = true;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public boolean disposed()
/* 139:    */   {
/* 140:129 */     return this._3zG;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public int _2Hq()
/* 144:    */   {
/* 145:132 */     return this._4XG;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public void _1ji(int _arg1)
/* 149:    */   {
/* 150:135 */     this._4XG = validateDirection360Value(_arg1);
/* 151:    */   }
/* 152:    */   
/* 153:    */   public void _0JV(int _arg1)
/* 154:    */   {
/* 155:138 */     this._4XG += _arg1;
/* 156:139 */     this._4XG = validateDirection360Value(this._4XG);
/* 157:    */   }
/* 158:    */   
/* 159:    */   public String toString()
/* 160:    */   {
/* 161:143 */     return "[" + this._4XG + "]";
/* 162:    */   }
/* 163:    */   
/* 164:    */   public Direction8 direction8Value()
/* 165:    */   {
/* 166:146 */     return direction360ValueToDirection8(this._4XG);
/* 167:    */   }
/* 168:    */   
/* 169:    */   public int _0uc()
/* 170:    */   {
/* 171:149 */     return _4G[0][this._4XG];
/* 172:    */   }
/* 173:    */   
/* 174:    */   public int _17x()
/* 175:    */   {
/* 176:152 */     return _4G[1][this._4XG];
/* 177:    */   }
/* 178:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.Direction360
 * JD-Core Version:    0.7.0.1
 */