/*  1:   */ package cappo.protocol.messages.events.register;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.CappoServer;
/*  4:   */ import cappo.engine.network.MessageReader;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.engine.threadpools.RoomTask;
/*  8:   */ import cappo.game.player.AvatarLook;
/*  9:   */ import cappo.game.player.PlayerData;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.room.engine.UserChangeComposer;
/* 13:   */ 
/* 14:   */ public class UpdateFigureDataParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:21 */     Avatar avatar = Main.avatar;
/* 20:22 */     if (avatar == null) {
/* 21:23 */       return;
/* 22:   */     }
/* 23:26 */     String Gender = Main.currentPacket.readString();
/* 24:27 */     String SelectedLook = Main.currentPacket.readString();
/* 25:29 */     if (!AvatarLook.validateLook(SelectedLook))
/* 26:   */     {
/* 27:30 */       CappoServer.close(Main.socket);
/* 28:31 */       return;
/* 29:   */     }
/* 30:34 */     PlayerData playerData = Main.getPlayerData();
/* 31:35 */     playerData.avatarLook = new AvatarLook(SelectedLook);
/* 32:36 */     playerData.sex = (Gender.equalsIgnoreCase("M") ? 1 : 0);
/* 33:   */     
/* 34:38 */     QueueWriter.write(Main.socket, UserChangeComposer.compose(-1, playerData.avatarLook.toString(), playerData.sex, playerData.motto, playerData.AchievementsScore));
/* 35:39 */     avatar.room.sendMessage(UserChangeComposer.compose(avatar.virtualId, playerData.avatarLook.toString(), playerData.sex, playerData.motto, playerData.AchievementsScore));
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.register.UpdateFigureDataParser
 * JD-Core Version:    0.7.0.1
 */