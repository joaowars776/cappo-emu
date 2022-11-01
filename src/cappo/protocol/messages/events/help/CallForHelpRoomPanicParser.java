/*  1:   */ package cappo.protocol.messages.events.help;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.moderation.UserMuted;
/*  7:   */ import cappo.game.moderation.tickets.HelpTicketReportRoom;
/*  8:   */ import cappo.game.moderation.tickets.HelpTicketsManager;
/*  9:   */ import cappo.game.player.PlayerData;
/* 10:   */ import cappo.game.roomengine.RoomData;
/* 11:   */ import cappo.game.roomengine.RoomManager;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ import cappo.protocol.messages.composers.help.CallForHelpMutedComposer;
/* 14:   */ import cappo.protocol.messages.composers.help.CallForHelpResultComposer;
/* 15:   */ 
/* 16:   */ public class CallForHelpRoomPanicParser
/* 17:   */   extends IncomingMessageEvent
/* 18:   */ {
/* 19:   */   public void messageReceived(Connection cn)
/* 20:   */   {
/* 21:22 */     if (cn.userMuted != null)
/* 22:   */     {
/* 23:23 */       if (cn.userMuted.isMuted())
/* 24:   */       {
/* 25:24 */         QueueWriter.write(cn.socket, CallForHelpMutedComposer.compose(cn.userMuted.reason));
/* 26:25 */         return;
/* 27:   */       }
/* 28:27 */       cn.userMuted = null;
/* 29:   */     }
/* 30:30 */     HelpTicketReportRoom ticket = new HelpTicketReportRoom(true);
/* 31:31 */     ticket.text = cn.currentPacket.readString();
/* 32:32 */     ticket.category = ((short)cn.currentPacket.readInt());
/* 33:33 */     ticket.reporterId = cn.playerData.userId;
/* 34:34 */     ticket.reporterName = cn.playerData.userName;
/* 35:35 */     ticket.roomId = cn.currentPacket.readInt();
/* 36:36 */     ticket.reportedName = "";
/* 37:   */     
/* 38:38 */     RoomData room = RoomManager.getRoom(ticket.roomId);
/* 39:39 */     if (room == null) {
/* 40:40 */       return;
/* 41:   */     }
/* 42:43 */     ticket.roomId = room.roomId;
/* 43:44 */     ticket.roomName = room.name;
/* 44:   */     
/* 45:46 */     HelpTicketsManager.addTicket(ticket);
/* 46:   */     
/* 47:48 */     QueueWriter.write(cn.socket, CallForHelpResultComposer.compose(0));
/* 48:   */   }
/* 49:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.help.CallForHelpRoomPanicParser
 * JD-Core Version:    0.7.0.1
 */