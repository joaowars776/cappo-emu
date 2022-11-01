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
/* 15:   */ public class CallForHelpParser
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
/* 31:32 */     cn.currentPacket.readInt();
/* 32:33 */     ticket.category = ((short)cn.currentPacket.readInt());
/* 33:34 */     ticket.reporterId = cn.playerData.userId;
/* 34:35 */     ticket.reporterName = cn.playerData.userName;
/* 35:   */     
/* 36:37 */     int userId = cn.currentPacket.readInt();
/* 37:38 */     if (userId == 0)
/* 38:   */     {
/* 39:39 */       ticket.reportedId = 0;
/* 40:40 */       ticket.reportedName = "";
/* 41:   */     }
/* 42:   */     else
/* 43:   */     {
/* 44:42 */       PlayerData client = Clients.getPlayerData(userId);
/* 45:43 */       if (client == null) {
/* 46:44 */         return;
/* 47:   */       }
/* 48:46 */       ticket.reportedId = client.userId;
/* 49:47 */       ticket.reportedName = client.userName;
/* 50:   */     }
/* 51:49 */     ticket.roomName = "";
/* 52:   */     
/* 53:51 */     HelpTicketsManager.addTicket(ticket);
/* 54:   */     
/* 55:53 */     QueueWriter.write(cn.socket, CallForHelpResultComposer.compose(0));
/* 56:   */   }
/* 57:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.help.CallForHelpParser
 * JD-Core Version:    0.7.0.1
 */