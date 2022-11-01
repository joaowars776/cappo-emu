/*  1:   */ package cappo.game.achievements;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.List;
/*  5:   */ 
/*  6:   */ public class AchievementBase
/*  7:   */ {
/*  8:   */   public final int achId;
/*  9:   */   public final String badgeId;
/* 10:   */   public final String categoryName;
/* 11:   */   public final List<AchievementLevel> levels;
/* 12:   */   
/* 13:   */   public AchievementBase(int id, String badge, String category)
/* 14:   */   {
/* 15:19 */     this.achId = id;
/* 16:20 */     this.badgeId = badge;
/* 17:21 */     this.categoryName = category;
/* 18:22 */     this.levels = new ArrayList();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void addLevel(AchievementLevel level)
/* 22:   */   {
/* 23:26 */     this.levels.add(level);
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.achievements.AchievementBase
 * JD-Core Version:    0.7.0.1
 */