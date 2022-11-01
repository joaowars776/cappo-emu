/*  1:   */ package cappo.game.achievements;
/*  2:   */ 
/*  3:   */ public class AchievementLevel
/*  4:   */ {
/*  5:   */   public static final int REWARD_PIXELS = 0;
/*  6:   */   public int levelGoal;
/*  7:   */   public int rewardPoints;
/*  8:   */   public int rewardType;
/*  9:   */   
/* 10:   */   public AchievementLevel(int goal)
/* 11:   */   {
/* 12:17 */     this.levelGoal = goal;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public AchievementLevel(int goal, int points, int type)
/* 16:   */   {
/* 17:21 */     this.levelGoal = goal;
/* 18:22 */     this.rewardPoints = points;
/* 19:23 */     this.rewardType = type;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.achievements.AchievementLevel
 * JD-Core Version:    0.7.0.1
 */