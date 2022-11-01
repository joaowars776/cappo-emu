/*  1:   */ package cappo.game.achievements;
/*  2:   */ 
/*  3:   */ import cappo.game.achievements.identity.AchAvatarLooks;
/*  4:   */ import cappo.game.achievements.identity.AchEmailVerification;
/*  5:   */ import cappo.game.achievements.identity.AchLogin;
/*  6:   */ import java.util.Map;
/*  7:   */ import java.util.concurrent.ConcurrentHashMap;
/*  8:   */ 
/*  9:   */ public class AchievementManager
/* 10:   */ {
/* 11:   */   public static final int ACH_EmailVerification = 3;
/* 12:   */   public static final int ACH_Login = 4;
/* 13:   */   public static final int ACH_AvatarLooks = 6;
/* 14:21 */   public static final Map<Integer, AchievementBase> achievements = new ConcurrentHashMap();
/* 15:   */   
/* 16:   */   static
/* 17:   */   {
/* 18:24 */     achievements.put(Integer.valueOf(3), new AchEmailVerification(3));
/* 19:25 */     achievements.put(Integer.valueOf(4), new AchLogin(4));
/* 20:26 */     achievements.put(Integer.valueOf(6), new AchAvatarLooks(6));
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.achievements.AchievementManager
 * JD-Core Version:    0.7.0.1
 */