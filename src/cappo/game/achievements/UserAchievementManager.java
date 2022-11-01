/*  1:   */ package cappo.game.achievements;
/*  2:   */ 
/*  3:   */ import java.util.Map;
/*  4:   */ import java.util.concurrent.ConcurrentHashMap;
/*  5:   */ 
/*  6:   */ public class UserAchievementManager
/*  7:   */ {
/*  8:13 */   public Map<Integer, UserAchievement> achievements = new ConcurrentHashMap();
/*  9:   */   
/* 10:   */   public void fillAchievements()
/* 11:   */   {
/* 12:16 */     for (AchievementBase ach : AchievementManager.achievements.values()) {
/* 13:17 */       if (!this.achievements.containsKey(Integer.valueOf(ach.achId))) {
/* 14:21 */         this.achievements.put(Integer.valueOf(ach.achId), new UserAchievement(ach, 0));
/* 15:   */       }
/* 16:   */     }
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.achievements.UserAchievementManager
 * JD-Core Version:    0.7.0.1
 */