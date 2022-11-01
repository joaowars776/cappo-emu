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
/* 13:   */ public class BanUserParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   private static final String BAN_USER_HOUR = "RWUAM_BAN_USER_HOUR";
/* 17:   */   private static final String BAN_USER_DAY = "RWUAM_BAN_USER_DAY";
/* 18:   */   private static final String BAN_USER_PERM = "RWUAM_BAN_USER_PERM";
/* 19:   */   
/* 20:   */   public void messageReceived(Connection cn)
/* 21:   */   {
/* 22:26 */     Avatar avatar = cn.avatar;
/* 23:27 */     if (avatar == null) {
/* 24:28 */       return;
/* 25:   */     }
/* 26:31 */     RoomTask room = avatar.room;
/* 27:32 */     RoomData roomData = room.roomData;
/* 28:34 */     if (roomData.modPermissions.permissionsBan == 1)
/* 29:   */     {
/* 30:35 */       if ((avatar.controllerLevel == 1) || 
/* 31:36 */         (avatar.controllerLevel >= 3)) {}
/* 32:   */     }
/* 33:41 */     else if (avatar.controllerLevel < 4) {
/* 34:42 */       return;
/* 35:   */     }
/* 36:46 */     PlayerData client = Clients.getPlayerData(cn.currentPacket.readInt());
/* 37:47 */     if ((client == null) || (client.connection.avatar == null)) {
/* 38:48 */       return;
/* 39:   */     }
/* 40:51 */     if ((client.staffLevel > 1) && (client.staffLevel >= cn.getPlayerData().staffLevel)) {
/* 41:52 */       return;
/* 42:   */     }
/* 43:55 */     cn.currentPacket.readInt();
/* 44:   */     
/* 45:57 */     String type = cn.currentPacket.readString();
/* 46:58 */     if (type.equals("RWUAM_BAN_USER_HOUR")) {
/* 47:59 */       room.addBan(client, 3600);
/* 48:60 */     } else if (type.equals("RWUAM_BAN_USER_DAY")) {
/* 49:61 */       room.addBan(client, 86400);
/* 50:62 */     } else if (type.equals("RWUAM_BAN_USER_PERM")) {
/* 51:63 */       room.addBan(client, 30000000);
/* 52:   */     }
/* 53:66 */     if (client.connection.avatar.room == room) {
/* 54:67 */       room.removeUserFromRoom(client.connection, true, true);
/* 55:   */     }
/* 56:   */   }
/* 57:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.action.BanUserParser
 * JD-Core Version:    0.7.0.1
 */