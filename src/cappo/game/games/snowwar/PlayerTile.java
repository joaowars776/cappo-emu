/*   1:    */ package cappo.game.games.snowwar;
/*   2:    */ 
/*   3:    */ public class PlayerTile
/*   4:    */ {
/*   5:    */   private int x;
/*   6:    */   private int y;
/*   7:    */   private int z;
/*   8:    */   
/*   9:    */   public PlayerTile(int _arg1, int _arg2, int _arg3)
/*  10:    */   {
/*  11: 15 */     this.x = _arg1;
/*  12: 16 */     this.y = _arg2;
/*  13: 17 */     this.z = _arg3;
/*  14:    */   }
/*  15:    */   
/*  16:    */   public int x()
/*  17:    */   {
/*  18: 21 */     return this.x;
/*  19:    */   }
/*  20:    */   
/*  21:    */   public int y()
/*  22:    */   {
/*  23: 25 */     return this.y;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public int z()
/*  27:    */   {
/*  28: 29 */     return this.z;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void setXYZ(int _arg1, int _arg2, int _arg3)
/*  32:    */   {
/*  33: 33 */     this.x = _arg1;
/*  34: 34 */     this.y = _arg2;
/*  35: 35 */     this.z = _arg3;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public void setXY(int x, int y)
/*  39:    */   {
/*  40: 39 */     this.x = x;
/*  41: 40 */     this.y = y;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void setXYZ(PlayerTile _arg1)
/*  45:    */   {
/*  46: 44 */     this.x = _arg1.x;
/*  47: 45 */     this.y = _arg1.y;
/*  48: 46 */     this.z = _arg1.z;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public int distanceTo(PlayerTile _arg1)
/*  52:    */   {
/*  53: 50 */     int local1 = _arg1.x - this.x;
/*  54: 51 */     int local2 = _arg1.y - this.y;
/*  55: 52 */     int local3 = _arg1.z - this.z;
/*  56: 53 */     int local4 = Math.abs(local1) + Math.abs(local2) + Math.abs(local3);
/*  57: 54 */     return local4;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public Direction8 directionTo(PlayerTile _arg1)
/*  61:    */   {
/*  62: 58 */     if ((_arg1.x == this.x) && (_arg1.y == this.y)) {
/*  63: 59 */       return null;
/*  64:    */     }
/*  65: 61 */     int local1 = _arg1.x - this.x;
/*  66: 62 */     int local2 = _arg1.y - this.y;
/*  67: 63 */     int local3 = Direction360.getRot(local1, local2);
/*  68: 64 */     return Direction360.direction360ValueToDirection8(local3);
/*  69:    */   }
/*  70:    */   
/*  71:    */   public boolean isSamePosition(Object _arg1)
/*  72:    */   {
/*  73: 68 */     if (this == _arg1) {
/*  74: 69 */       return true;
/*  75:    */     }
/*  76: 71 */     if (!(_arg1 instanceof PlayerTile)) {
/*  77: 72 */       return false;
/*  78:    */     }
/*  79: 74 */     PlayerTile local1 = (PlayerTile)_arg1;
/*  80: 75 */     if (this.x != local1.x) {
/*  81: 76 */       return false;
/*  82:    */     }
/*  83: 78 */     if (this.y != local1.y) {
/*  84: 79 */       return false;
/*  85:    */     }
/*  86: 81 */     if (this.z != local1.z) {
/*  87: 82 */       return false;
/*  88:    */     }
/*  89: 84 */     return true;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public String toString()
/*  93:    */   {
/*  94: 89 */     return "_x:" + this.x + "yy:" + this.y + "_zz:" + this.z;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public boolean _0Dk(PlayerTile _arg1, int _arg2)
/*  98:    */   {
/*  99: 93 */     return _4D8(this.x, this.y, _arg1.x, _arg1.y, _arg2);
/* 100:    */   }
/* 101:    */   
/* 102:    */   public static boolean _4D8(int _arg1, int _arg2, int _arg3, int _arg4, int _arg5)
/* 103:    */   {
/* 104: 97 */     int local5 = _arg3 - _arg1;
/* 105: 98 */     if (local5 < 0) {
/* 106: 99 */       local5 = -local5;
/* 107:    */     }
/* 108:101 */     int local6 = _arg4 - _arg2;
/* 109:102 */     if (local6 < 0) {
/* 110:103 */       local6 = -local6;
/* 111:    */     }
/* 112:105 */     if ((local6 > _arg5) || (local5 > _arg5)) {
/* 113:106 */       return false;
/* 114:    */     }
/* 115:108 */     if (local5 * local5 + local6 * local6 < _arg5 * _arg5) {
/* 116:109 */       return true;
/* 117:    */     }
/* 118:111 */     return false;
/* 119:    */   }
/* 120:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.PlayerTile
 * JD-Core Version:    0.7.0.1
 */