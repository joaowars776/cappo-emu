/*  1:   */ package cappo.protocol.messages.events.room.avatar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.player.AvatarLook;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.room.engine.UserChangeComposer;
/* 11:   */ 
/* 12:   */ public class ChangeMottoParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:18 */     Avatar avatar = Main.avatar;
/* 18:19 */     if (avatar == null) {
/* 19:20 */       return;
/* 20:   */     }
/* 21:23 */     String newMotto = Main.currentPacket.readString();
/* 22:24 */     if (newMotto.length() > 38) {
/* 23:25 */       newMotto = newMotto.substring(0, 38);
/* 24:   */     }
/* 25:28 */     PlayerData playerData = Main.getPlayerData();
/* 26:30 */     if (playerData.motto.equals(newMotto)) {
/* 27:31 */       return;
/* 28:   */     }
/* 29:34 */     playerData.motto = newMotto;
/* 30:   */     
/* 31:36 */     avatar.room.sendMessage(UserChangeComposer.compose(avatar.virtualId, playerData.avatarLook.toString(), playerData.sex, playerData.motto, playerData.AchievementsScore));
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.avatar.ChangeMottoParser
 * JD-Core Version:    0.7.0.1
 */