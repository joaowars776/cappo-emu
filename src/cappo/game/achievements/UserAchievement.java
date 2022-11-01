/*  1:   */ package cappo.game.achievements;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ 
/*  5:   */ public class UserAchievement
/*  6:   */ {
/*  7:   */   public AchievementBase achievement;
/*  8:   */   public int progress;
/*  9:   */   public int level;
/* 10:   */   public boolean achieved;
/* 11:   */   
/* 12:   */   public UserAchievement(AchievementBase ach, int Progress)
/* 13:   */   {
/* 14:16 */     this.achievement = ach;
/* 15:17 */     this.progress = Progress;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public int getNextNivel()
/* 19:   */   {
/* 20:21 */     if (this.achievement.levels.size() > this.level) {
/* 21:22 */       return this.level + 1;
/* 22:   */     }
/* 23:24 */     return this.level;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public int getPrevGoal()
/* 27:   */   {
/* 28:28 */     if (this.level <= 1) {
/* 29:29 */       return 0;
/* 30:   */     }
/* 31:31 */     return ((AchievementLevel)this.achievement.levels.get(this.level - 2)).levelGoal;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public int getNextGoal()
/* 35:   */   {
/* 36:35 */     if (this.achievement.levels.isEmpty()) {
/* 37:36 */       return 0;
/* 38:   */     }
/* 39:39 */     if (this.achievement.levels.size() > this.level) {
/* 40:40 */       return ((AchievementLevel)this.achievement.levels.get(this.level)).levelGoal;
/* 41:   */     }
/* 42:43 */     return ((AchievementLevel)this.achievement.levels.get(this.level - 1)).levelGoal;
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.achievements.UserAchievement
 * JD-Core Version:    0.7.0.1
 */