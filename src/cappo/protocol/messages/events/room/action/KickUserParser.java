/*  1:   */ package cappo.protocol.messages.events.room.action;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Clients;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.roomengine.RoomData;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.game.roomengine.settings.ModerationPermissions;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ 
/* 13:   */ public class KickUserParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection cn)
/* 17:   */   {
/* 18:22 */     Avatar avatar = cn.avatar;
/* 19:23 */     if (avatar == null) {
/* 20:24 */       return;
/* 21:   */     }
/* 22:27 */     RoomTask room = avatar.room;
/* 23:28 */     RoomData roomData = room.roomData;
/* 24:30 */     if (roomData.modPermissions.permissionsKick != 2) {
/* 25:31 */       if (roomData.modPermissions.permissionsKick == 1)
/* 26:   */       {
/* 27:32 */         if ((avatar.controllerLevel == 1) || 
/* 28:33 */           (avatar.controllerLevel >= 3)) {}
/* 29:   */       }
/* 30:38 */       else if (avatar.controllerLevel < 4) {
/* 31:39 */         return;
/* 32:   */       }
/* 33:   */     }
/* 34:44 */     PlayerData client = Clients.getPlayerData(cn.currentPacket.readInt());
/* 35:45 */     if ((client == null) || (client.connection == null)) {
/* 36:46 */       return;
/* 37:   */     }
/* 38:49 */     if ((client.staffLevel > 1) && (client.staffLevel >= cn.playerData.staffLevel)) {
/* 39:50 */       return;
/* 40:   */     }
/* 41:53 */     Avatar clientAvatar = client.connection.avatar;
/* 42:54 */     if (clientAvatar.room == room) {
/* 43:55 */       room.removeUserFromRoom(client.connection, true, true);
/* 44:   */     }
/* 45:   */   }
/* 46:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.action.KickUserParser
 * JD-Core Version:    0.7.0.1
 */