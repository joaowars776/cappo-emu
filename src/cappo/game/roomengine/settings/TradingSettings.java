/*  1:   */ package cappo.game.roomengine.settings;
/*  2:   */ 
/*  3:   */ public class TradingSettings
/*  4:   */ {
/*  5:   */   public static final int NONE = 0;
/*  6:   */   public static final int WITH_RIGHTS = 1;
/*  7:   */   public static final int ALL = 2;
/*  8:   */   public int permissions;
/*  9:   */   
/* 10:   */   public TradingSettings(int data)
/* 11:   */   {
/* 12:11 */     this.permissions = data;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public int getIntValue()
/* 16:   */   {
/* 17:15 */     return this.permissions;
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.settings.TradingSettings
 * JD-Core Version:    0.7.0.1
 */