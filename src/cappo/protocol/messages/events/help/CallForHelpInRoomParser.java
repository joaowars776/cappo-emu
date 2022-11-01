/*  1:   */ package cappo.protocol.messages.events.help;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.engine.threadpools.RoomTask;
/*  8:   */ import cappo.game.moderation.UserMuted;
/*  9:   */ import cappo.game.moderation.tickets.HelpTicketReportUser;
/* 10:   */ import cappo.game.moderation.tickets.HelpTicketsManager;
/* 11:   */ import cappo.game.player.PlayerData;
/* 12:   */ import cappo.game.roomengine.RoomData;
/* 13:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 14:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 15:   */ import cappo.protocol.messages.composers.help.CallForHelpMutedComposer;
/* 16:   */ import cappo.protocol.messages.composers.help.CallForHelpResultComposer;
/* 17:   */ 
/* 18:   */ public class CallForHelpInRoomParser
/* 19:   */   extends IncomingMessageEvent
/* 20:   */ {
/* 21:   */   public void messageReceived(Connection cn)
/* 22:   */   {
/* 23:23 */     if (cn.userMuted != null)
/* 24:   */     {
/* 25:24 */       if (cn.userMuted.isMuted())
/* 26:   */       {
/* 27:25 */         QueueWriter.write(cn.socket, CallForHelpMutedComposer.compose(cn.userMuted.reason));
/* 28:26 */         return;
/* 29:   */       }
/* 30:28 */       cn.userMuted = null;
/* 31:   */     }
/* 32:31 */     RoomTask room = cn.avatar.room;
/* 33:32 */     if (room == null) {
/* 34:33 */       return;
/* 35:   */     }
/* 36:36 */     HelpTicketReportUser ticket = new HelpTicketReportUser(true);
/* 37:37 */     ticket.text = cn.currentPacket.readString();
/* 38:38 */     cn.currentPacket.readInt();
/* 39:39 */     ticket.category = ((short)cn.currentPacket.readInt());
/* 40:40 */     ticket.reporterId = cn.playerData.userId;
/* 41:41 */     ticket.reporterName = cn.playerData.userName;
/* 42:42 */     ticket.roomId = room.roomId;
/* 43:43 */     ticket.roomName = room.roomData.name;
/* 44:   */     
/* 45:45 */     int userId = cn.currentPacket.readInt();
/* 46:46 */     if (userId == 0)
/* 47:   */     {
/* 48:47 */       ticket.reportedId = 0;
/* 49:48 */       ticket.reportedName = "";
/* 50:   */     }
/* 51:   */     else
/* 52:   */     {
/* 53:50 */       PlayerData client = Clients.getPlayerData(userId);
/* 54:51 */       if (client == null) {
/* 55:52 */         return;
/* 56:   */       }
/* 57:54 */       ticket.reportedId = client.userId;
/* 58:55 */       ticket.reportedName = client.userName;
/* 59:   */     }
/* 60:58 */     HelpTicketsManager.addTicket(ticket);
/* 61:   */     
/* 62:60 */     QueueWriter.write(cn.socket, CallForHelpResultComposer.compose(0));
/* 63:   */   }
/* 64:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.help.CallForHelpInRoomParser
 * JD-Core Version:    0.7.0.1
 */