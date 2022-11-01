/*  1:   */ package cappo.protocol.messages.events.room.action;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.engine.threadpools.RoomTask;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.game.roomengine.settings.PlayerRight;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.room.permissions.YouAreControllerComposer;
/* 13:   */ import cappo.protocol.messages.composers.roomsettings.FlatControllerAddedComposer;
/* 14:   */ import java.util.Map;
/* 15:   */ 
/* 16:   */ public class AssignRightsParser
/* 17:   */   extends IncomingMessageEvent
/* 18:   */ {
/* 19:   */   public void messageReceived(Connection Main)
/* 20:   */   {
/* 21:23 */     Avatar avatar = Main.avatar;
/* 22:24 */     if (avatar == null) {
/* 23:25 */       return;
/* 24:   */     }
/* 25:28 */     if (avatar.controllerLevel < 4) {
/* 26:29 */       return;
/* 27:   */     }
/* 28:32 */     int userId = Main.currentPacket.readInt();
/* 29:33 */     if (avatar.room.usersWithRights.containsKey(Integer.valueOf(userId))) {
/* 30:34 */       return;
/* 31:   */     }
/* 32:37 */     PlayerData client = Clients.getPlayerData(userId);
/* 33:38 */     if (client == null) {
/* 34:39 */       return;
/* 35:   */     }
/* 36:42 */     avatar.room.usersWithRights.put(Integer.valueOf(client.userId), new PlayerRight(client).needInsert());
/* 37:43 */     QueueWriter.write(Main.socket, FlatControllerAddedComposer.compose(avatar.room.roomId, client.userId, client.userName));
/* 38:45 */     if (client.connection == null) {
/* 39:46 */       return;
/* 40:   */     }
/* 41:49 */     Avatar clientAvatar = client.connection.avatar;
/* 42:50 */     if ((clientAvatar == null) || (clientAvatar.room.roomId != avatar.room.roomId)) {
/* 43:51 */       return;
/* 44:   */     }
/* 45:54 */     clientAvatar.controllerLevel = 1;
/* 46:55 */     QueueWriter.writeAndFlush(client.connection.socket, YouAreControllerComposer.compose(1));
/* 47:   */   }
/* 48:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.action.AssignRightsParser
 * JD-Core Version:    0.7.0.1
 */