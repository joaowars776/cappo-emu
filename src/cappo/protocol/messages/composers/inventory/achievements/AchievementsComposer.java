/*  1:   */ package cappo.protocol.messages.composers.inventory.achievements;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.achievements.AchievementBase;
/*  5:   */ import cappo.game.achievements.UserAchievement;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import java.util.Collection;
/*  8:   */ import java.util.List;
/*  9:   */ 
/* 10:   */ public class AchievementsComposer
/* 11:   */ {
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static MessageWriter compose(Collection<UserAchievement> values, String openCategory)
/* 15:   */   {
/* 16:19 */     MessageWriter ClientMessage = new MessageWriter();
/* 17:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 18:21 */     Composer.writeInt32(values.size(), ClientMessage);
/* 19:22 */     for (UserAchievement ach : values)
/* 20:   */     {
/* 21:23 */       int nextLevel = ach.getNextNivel();
/* 22:24 */       Composer.writeInt32(ach.achievement.achId, ClientMessage);
/* 23:25 */       Composer.writeInt32(nextLevel, ClientMessage);
/* 24:26 */       Composer.add(ach.achievement.badgeId + nextLevel, ClientMessage);
/* 25:27 */       Composer.writeInt32(ach.getPrevGoal(), ClientMessage);
/* 26:28 */       Composer.writeInt32(ach.getNextGoal(), ClientMessage);
/* 27:29 */       Composer.writeInt32(0, ClientMessage);
/* 28:30 */       Composer.writeInt32(0, ClientMessage);
/* 29:31 */       Composer.writeInt32(ach.progress, ClientMessage);
/* 30:32 */       Composer.add(Boolean.valueOf(ach.achieved), ClientMessage);
/* 31:33 */       Composer.add(ach.achievement.categoryName, ClientMessage);
/* 32:34 */       Composer.add("", ClientMessage);
/* 33:35 */       Composer.writeInt32(ach.achievement.levels.size(), ClientMessage);
/* 34:36 */       Composer.writeInt32(0, ClientMessage);
/* 35:   */     }
/* 36:38 */     Composer.add(openCategory, ClientMessage);
/* 37:39 */     Composer.endPacket(ClientMessage);
/* 38:40 */     return ClientMessage;
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.achievements.AchievementsComposer
 * JD-Core Version:    0.7.0.1
 */