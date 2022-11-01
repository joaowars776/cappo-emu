/*  1:   */ package cappo.game.achievements.identity;
/*  2:   */ 
/*  3:   */ import cappo.game.achievements.AchievementLevel;
/*  4:   */ 
/*  5:   */ public class AchEmailVerification
/*  6:   */   extends AchievementBaseIdentity
/*  7:   */ {
/*  8:   */   public AchEmailVerification(int id)
/*  9:   */   {
/* 10:13 */     super(id, "ACH_EmailVerification");
/* 11:   */     
/* 12:   */ 
/* 13:16 */     addLevel(new AchievementLevel(1));
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.achievements.identity.AchEmailVerification
 * JD-Core Version:    0.7.0.1
 */