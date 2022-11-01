/*  1:   */ package cappo.protocol.messages.events.help;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.moderation.UserMuted;
/*  8:   */ import cappo.game.moderation.tickets.HelpTicketReportUser;
/*  9:   */ import cappo.game.moderation.tickets.HelpTicketsManager;
/* 10:   */ import cappo.game.player.PlayerData;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.help.CallForHelpMutedComposer;
/* 13:   */ import cappo.protocol.messages.composers.help.CallForHelpResultComposer;
/* 14:   */ 
/* 15:   */ public class CallForHelp2Parser
/* 16:   */   extends IncomingMessageEvent
/* 17:   */ {
/* 18:   */   public void messageReceived(Connection cn)
/* 19:   */   {
/* 20:22 */     if (cn.userMuted != null)
/* 21:   */     {
/* 22:23 */       if (cn.userMuted.isMuted())
/* 23:   */       {
/* 24:24 */         QueueWriter.write(cn.socket, CallForHelpMutedComposer.compose(cn.userMuted.reason));
/* 25:25 */         return;
/* 26:   */       }
/* 27:27 */       cn.userMuted = null;
/* 28:   */     }
/* 29:30 */     HelpTicketReportUser ticket = new HelpTicketReportUser(false);
/* 30:31 */     ticket.text = cn.currentPacket.readString();
/* 31:32 */     ticket.category = ((short)cn.currentPacket.readInt());
/* 32:33 */     ticket.reporterId = cn.playerData.userId;
/* 33:34 */     ticket.reporterName = cn.playerData.userName;
/* 34:   */     
/* 35:36 */     int userId = cn.currentPacket.readInt();
/* 36:37 */     if (userId == 0)
/* 37:   */     {
/* 38:38 */       ticket.reportedId = 0;
/* 39:39 */       ticket.reportedName = "";
/* 40:   */     }
/* 41:   */     else
/* 42:   */     {
/* 43:41 */       PlayerData client = Clients.getPlayerData(userId);
/* 44:42 */       if (client == null) {
/* 45:43 */         return;
/* 46:   */       }
/* 47:45 */       ticket.reportedId = client.userId;
/* 48:46 */       ticket.reportedName = client.userName;
/* 49:   */     }
/* 50:48 */     ticket.roomName = "";
/* 51:   */     
/* 52:50 */     HelpTicketsManager.addTicket(ticket);
/* 53:   */     
/* 54:52 */     QueueWriter.write(cn.socket, CallForHelpResultComposer.compose(0));
/* 55:   */   }
/* 56:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.help.CallForHelp2Parser
 * JD-Core Version:    0.7.0.1
 */