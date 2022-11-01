/*  1:   */ package cappo.game.roomengine.settings;
/*  2:   */ 
/*  3:   */ public class ModerationPermissions
/*  4:   */ {
/*  5:   */   public static final int ONLY_OWNER = 0;
/*  6:   */   public static final int WITH_RIGHTS = 1;
/*  7:   */   public static final int ALL_USERS = 2;
/*  8:   */   public int permissionsMute;
/*  9:   */   public int permissionsKick;
/* 10:   */   public int permissionsBan;
/* 11:   */   private static final int PACK_NUMBITS = 3;
/* 12:   */   private static final int PACK_BITS = 7;
/* 13:   */   
/* 14:   */   public ModerationPermissions(int data)
/* 15:   */   {
/* 16:23 */     this.permissionsMute = getIntShiftBits(data, 0);
/* 17:24 */     this.permissionsKick = getIntShiftBits(data, 3);
/* 18:25 */     this.permissionsBan = getIntShiftBits(data, 6);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public int getIntValue()
/* 22:   */   {
/* 23:29 */     return this.permissionsMute | this.permissionsKick << 3 | this.permissionsBan << 6;
/* 24:   */   }
/* 25:   */   
/* 26:   */   private int getIntBits(long num)
/* 27:   */   {
/* 28:33 */     return (int)(num & 0x7);
/* 29:   */   }
/* 30:   */   
/* 31:   */   private int getIntShiftBits(int data, int shift)
/* 32:   */   {
/* 33:37 */     if (shift > 0) {
/* 34:38 */       return getIntBits(data >>> shift);
/* 35:   */     }
/* 36:40 */     return getIntBits(data);
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.settings.ModerationPermissions
 * JD-Core Version:    0.7.0.1
 */