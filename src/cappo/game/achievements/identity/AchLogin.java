/*  1:   */ package cappo.game.achievements.identity;
/*  2:   */ 
/*  3:   */ import cappo.game.achievements.AchievementLevel;
/*  4:   */ 
/*  5:   */ public class AchLogin
/*  6:   */   extends AchievementBaseIdentity
/*  7:   */ {
/*  8:   */   public AchLogin(int id)
/*  9:   */   {
/* 10:13 */     super(id, "ACH_Login");
/* 11:   */     
/* 12:   */ 
/* 13:16 */     addLevel(new AchievementLevel(5));
/* 14:   */     
/* 15:18 */     addLevel(new AchievementLevel(8));
/* 16:   */   }
/* 17:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.achievements.identity.AchLogin
 * JD-Core Version:    0.7.0.1
 */