/*  1:   */ package cappo.protocol.messages.events.moderator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Clients;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.collections.Utils;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ 
/* 12:   */ public class ModKickParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection cn)
/* 16:   */   {
/* 17:19 */     if (!cn.playerData.allowModTools()) {
/* 18:20 */       return;
/* 19:   */     }
/* 20:23 */     int UserId = cn.currentPacket.readInt();
/* 21:   */     
/* 22:25 */     PlayerData playerData = Clients.getPlayerData(UserId);
/* 23:26 */     if ((playerData == null) || (playerData.connection == null)) {
/* 24:27 */       return;
/* 25:   */     }
/* 26:30 */     Connection plrConnection = playerData.connection;
/* 27:31 */     if (plrConnection == null) {
/* 28:32 */       return;
/* 29:   */     }
/* 30:35 */     Avatar avatar = plrConnection.avatar;
/* 31:36 */     if (avatar == null) {
/* 32:37 */       return;
/* 33:   */     }
/* 34:40 */     avatar.room.removeUserFromRoom(plrConnection, true, false);
/* 35:   */     
/* 36:42 */     String Message = cn.currentPacket.readString();
/* 37:   */     
/* 38:44 */     Utils.AlertFromHotel(plrConnection.socket, Message);
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.moderator.ModKickParser
 * JD-Core Version:    0.7.0.1
 */