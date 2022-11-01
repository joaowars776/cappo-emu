/*  1:   */ package cappo.game.player.data;
/*  2:   */ 
/*  3:   */ import cappo.engine.settings.PerkAllowance;
/*  4:   */ import cappo.game.achievements.UserAchievementManager;
/*  5:   */ import cappo.game.collections.UnseenItems;
/*  6:   */ import java.util.ArrayList;
/*  7:   */ import java.util.List;
/*  8:   */ import java.util.Map;
/*  9:   */ import java.util.concurrent.ConcurrentHashMap;
/* 10:   */ 
/* 11:   */ public class AvatarData
/* 12:   */ {
/* 13:   */   public List<PerkAllowance> perksAllowances;
/* 14:   */   public UserAchievementManager achievementManager;
/* 15:   */   public UnseenItems UnseenItems;
/* 16:   */   public Map<Integer, Integer> Achievements;
/* 17:   */   public List<String> friendCategories;
/* 18:   */   public List<Integer> ratedRooms;
/* 19:   */   public long EcotronNextTime;
/* 20:   */   public long lastChangeNameTry;
/* 21:   */   public long lastCheckNameTry;
/* 22:   */   public int AccessCount;
/* 23:   */   public int LoadingRoom;
/* 24:   */   public int TotalLengthHC;
/* 25:   */   public int TotalLengthVIP;
/* 26:33 */   public int volume1 = 80;
/* 27:34 */   public int volume2 = 80;
/* 28:35 */   public int volume3 = 80;
/* 29:   */   public boolean oldChatStyle;
/* 30:   */   
/* 31:   */   public AvatarData()
/* 32:   */   {
/* 33:40 */     this.perksAllowances = new ArrayList();
/* 34:41 */     this.perksAllowances.add(new PerkAllowance("USE_GUIDE_TOOL", false, ""));
/* 35:42 */     this.perksAllowances.add(new PerkAllowance("GIVE_GUIDE_TOURS", false, ""));
/* 36:43 */     this.perksAllowances.add(new PerkAllowance("JUDGE_CHAT_REVIEWS", false, ""));
/* 37:44 */     this.perksAllowances.add(new PerkAllowance("VOTE_IN_COMPETITIONS", false, ""));
/* 38:45 */     this.perksAllowances.add(new PerkAllowance("CALL_ON_HELPERS", false, ""));
/* 39:   */     
/* 40:47 */     this.perksAllowances.add(new PerkAllowance("CITIZEN", false, ""));
/* 41:   */     
/* 42:49 */     this.perksAllowances.add(new PerkAllowance("TRADE", true, ""));
/* 43:50 */     this.perksAllowances.add(new PerkAllowance("HEIGHTMAP_EDITOR_BETA", true, ""));
/* 44:51 */     this.perksAllowances.add(new PerkAllowance("EXPERIMENTAL_CHAT_BETA", true, ""));
/* 45:52 */     this.perksAllowances.add(new PerkAllowance("EXPERIMENTAL_TOOLBAR", true, ""));
/* 46:53 */     this.perksAllowances.add(new PerkAllowance("NEW_UI", true, ""));
/* 47:   */     
/* 48:55 */     this.achievementManager = new UserAchievementManager();
/* 49:56 */     this.UnseenItems = new UnseenItems();
/* 50:57 */     this.Achievements = new ConcurrentHashMap();
/* 51:58 */     this.friendCategories = new ArrayList();
/* 52:59 */     this.ratedRooms = new ArrayList();
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.data.AvatarData
 * JD-Core Version:    0.7.0.1
 */