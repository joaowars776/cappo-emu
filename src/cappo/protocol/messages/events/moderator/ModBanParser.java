/*  1:   */ package cappo.protocol.messages.events.moderator;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.Database;
/*  4:   */ import cappo.engine.logging.Log;
/*  5:   */ import cappo.engine.network.MessageReader;
/*  6:   */ import cappo.engine.network.QueueWriter;
/*  7:   */ import cappo.engine.player.Clients;
/*  8:   */ import cappo.engine.player.Connection;
/*  9:   */ import cappo.game.collections.Utils;
/* 10:   */ import cappo.game.player.PlayerData;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.handshake.UserDisconnectComposer;
/* 13:   */ import cappo.protocol.messages.events.handshake.SSOTicketParser;
/* 14:   */ import java.util.Map;
/* 15:   */ 
/* 16:   */ public class ModBanParser
/* 17:   */   extends IncomingMessageEvent
/* 18:   */ {
/* 19:   */   public void messageReceived(Connection cn)
/* 20:   */   {
/* 21:23 */     PlayerData playerData = cn.getPlayerData();
/* 22:25 */     if (!playerData.allowBan()) {
/* 23:26 */       return;
/* 24:   */     }
/* 25:30 */     PlayerData client = Clients.getPlayerData(cn.currentPacket.readInt());
/* 26:31 */     if (client == null) {
/* 27:32 */       return;
/* 28:   */     }
/* 29:35 */     if ((client.staffLevel > 1) && (client.staffLevel >= playerData.staffLevel)) {
/* 30:36 */       return;
/* 31:   */     }
/* 32:39 */     String reason = cn.currentPacket.readString();
/* 33:40 */     int hours = cn.currentPacket.readInt();
/* 34:41 */     String ticketMessage = cn.currentPacket.readString();
/* 35:42 */     boolean isAvatarBan = cn.currentPacket.readBoolean();
/* 36:43 */     int issueId = -1;
/* 37:44 */     if (!ticketMessage.isEmpty()) {
/* 38:45 */       issueId = cn.currentPacket.readInt();
/* 39:   */     }
/* 40:48 */     long now = Utils.getTimestamp();
/* 41:50 */     if (hours == 100000) {
/* 42:51 */       SSOTicketParser.temporallyBans.put(Integer.valueOf(client.userId), Integer.valueOf(2147483646));
/* 43:   */     } else {
/* 44:53 */       SSOTicketParser.temporallyBans.put(Integer.valueOf(client.userId), Integer.valueOf((int)(now + hours * 3600)));
/* 45:   */     }
/* 46:   */     try
/* 47:   */     {
/* 48:57 */       Database.exec(
/* 49:   */       
/* 50:59 */         "INSERT INTO bans (type,reason,text,hours,created,mod_id,user_id,issue_id) VALUES('" + (isAvatarBan ? "avatar" : "account") + "',?,?," + hours + "," + now + "," + playerData.userId + "," + client.userId + "," + issueId + ")" + " ON DUPLICATE KEY UPDATE hours=" + hours + ",created=" + now + ";", new Object[] { reason, ticketMessage });
/* 51:   */     }
/* 52:   */     catch (Exception ex)
/* 53:   */     {
/* 54:62 */       Log.printException("Disconnect", ex);
/* 55:   */     }
/* 56:65 */     int Type = 1;
/* 57:66 */     if (hours == 100000) {
/* 58:67 */       Type = 10;
/* 59:   */     }
/* 60:70 */     if (client.connection != null) {
/* 61:71 */       QueueWriter.writeAndClose(client.connection.socket, UserDisconnectComposer.compose(Type));
/* 62:   */     }
/* 63:   */   }
/* 64:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.moderator.ModBanParser
 * JD-Core Version:    0.7.0.1
 */