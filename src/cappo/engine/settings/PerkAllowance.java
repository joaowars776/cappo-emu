/*  1:   */ package cappo.engine.settings;
/*  2:   */ 
/*  3:   */ public class PerkAllowance
/*  4:   */ {
/*  5:   */   public String codeName;
/*  6:   */   public boolean active;
/*  7:   */   public String errorText;
/*  8:   */   
/*  9:   */   public PerkAllowance(String code, boolean enabled, String error)
/* 10:   */   {
/* 11:15 */     this.codeName = code;
/* 12:16 */     this.active = enabled;
/* 13:17 */     this.errorText = error;
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.settings.PerkAllowance
 * JD-Core Version:    0.7.0.1
 */