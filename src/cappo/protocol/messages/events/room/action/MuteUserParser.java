/*  1:   */ package cappo.protocol.messages.events.room.action;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Clients;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.collections.Utils;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.game.roomengine.RoomData;
/* 10:   */ import cappo.game.roomengine.chat.UserRoomMuted;
/* 11:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 12:   */ import cappo.game.roomengine.settings.ModerationPermissions;
/* 13:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 14:   */ 
/* 15:   */ public class MuteUserParser
/* 16:   */   extends IncomingMessageEvent
/* 17:   */ {
/* 18:   */   public void messageReceived(Connection cn)
/* 19:   */   {
/* 20:24 */     Avatar avatar = cn.avatar;
/* 21:25 */     if (avatar == null) {
/* 22:26 */       return;
/* 23:   */     }
/* 24:29 */     RoomTask room = avatar.room;
/* 25:30 */     RoomData roomData = room.roomData;
/* 26:32 */     if (roomData.modPermissions.permissionsMute == 1)
/* 27:   */     {
/* 28:33 */       if ((avatar.controllerLevel == 1) || 
/* 29:34 */         (avatar.controllerLevel >= 3)) {}
/* 30:   */     }
/* 31:39 */     else if (avatar.controllerLevel < 4) {
/* 32:40 */       return;
/* 33:   */     }
/* 34:44 */     PlayerData client = Clients.getPlayerData(cn.currentPacket.readInt());
/* 35:45 */     if ((client == null) || (client.connection == null)) {
/* 36:46 */       return;
/* 37:   */     }
/* 38:49 */     if ((client.staffLevel > 1) && (client.staffLevel >= cn.playerData.staffLevel)) {
/* 39:50 */       return;
/* 40:   */     }
/* 41:53 */     cn.currentPacket.readInt();
/* 42:   */     
/* 43:55 */     Avatar clientAvatar = client.connection.avatar;
/* 44:56 */     if (clientAvatar.room == room)
/* 45:   */     {
/* 46:57 */       clientAvatar.userRoomMuted = new UserRoomMuted();
/* 47:58 */       clientAvatar.userRoomMuted.unMuteTimeStamp = (Utils.getTimestamp() + cn.currentPacket.readInt() * 60);
/* 48:   */     }
/* 49:   */   }
/* 50:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.action.MuteUserParser
 * JD-Core Version:    0.7.0.1
 */