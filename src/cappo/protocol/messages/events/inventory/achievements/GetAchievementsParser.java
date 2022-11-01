/*  1:   */ package cappo.protocol.messages.events.inventory.achievements;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.achievements.UserAchievementManager;
/*  6:   */ import cappo.game.player.data.AvatarData;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.inventory.achievements.AchievementsComposer;
/*  9:   */ import cappo.protocol.messages.composers.inventory.achievements.AchievementsScoreComposer;
/* 10:   */ import java.util.Map;
/* 11:   */ 
/* 12:   */ public class GetAchievementsParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection cn)
/* 16:   */   {
/* 17:18 */     QueueWriter.write(cn.socket, AchievementsComposer.compose(cn.avatarData.achievementManager.achievements.values(), ""));
/* 18:19 */     QueueWriter.write(cn.socket, AchievementsScoreComposer.compose(0));
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.achievements.GetAchievementsParser
 * JD-Core Version:    0.7.0.1
 */