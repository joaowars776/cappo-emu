/*  1:   */ package cappo.protocol.messages.events.moderator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.MessageWriter;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Clients;
/*  7:   */ import cappo.engine.player.Connection;
/*  8:   */ import cappo.game.moderation.StaffManager;
/*  9:   */ import cappo.game.moderation.tickets.HelpTicket;
/* 10:   */ import cappo.game.moderation.tickets.HelpTicketsManager;
/* 11:   */ import cappo.game.player.PlayerData;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ import cappo.protocol.messages.composers.help.IssueCloseNotificationComposer;
/* 14:   */ import cappo.protocol.messages.composers.moderation.IssueInfoComposer;
/* 15:   */ import java.util.Map;
/* 16:   */ 
/* 17:   */ public class CloseIssuesParser
/* 18:   */   extends IncomingMessageEvent
/* 19:   */ {
/* 20:   */   public static final int CLOSE_USELESS = 1;
/* 21:   */   public static final int CLOSE_ABUSIVE = 2;
/* 22:   */   public static final int CLOSE_RESOLVED = 3;
/* 23:   */   
/* 24:   */   public void messageReceived(Connection cn)
/* 25:   */   {
/* 26:28 */     if (!cn.playerData.allowModTools()) {
/* 27:29 */       return;
/* 28:   */     }
/* 29:32 */     int action = cn.currentPacket.readInt();
/* 30:   */     
/* 31:34 */     int count = cn.currentPacket.readInt();
/* 32:35 */     if ((count < 1) || (count > 20)) {
/* 33:   */       return;
/* 34:   */     }
/* 35:   */     MessageWriter packet;
/* 36:   */     MessageWriter packet;
/* 37:40 */     if (action == 1)
/* 38:   */     {
/* 39:41 */       packet = IssueCloseNotificationComposer.compose(1);
/* 40:   */     }
/* 41:   */     else
/* 42:   */     {
/* 43:   */       MessageWriter packet;
/* 44:42 */       if (action == 2) {
/* 45:43 */         packet = IssueCloseNotificationComposer.compose(2);
/* 46:   */       } else {
/* 47:45 */         packet = IssueCloseNotificationComposer.compose(0);
/* 48:   */       }
/* 49:   */     }
/* 50:48 */     for (int i = 0; i < count; i++)
/* 51:   */     {
/* 52:49 */       int ticketId = cn.currentPacket.readInt();
/* 53:50 */       HelpTicket ticket = (HelpTicket)HelpTicketsManager.tickets.get(Integer.valueOf(ticketId));
/* 54:52 */       if ((ticket != null) && (ticket.status == 2) && (ticket.handlerId == cn.playerData.userId))
/* 55:   */       {
/* 56:56 */         ticket.status = 3;
/* 57:57 */         StaffManager.broadcast(IssueInfoComposer.compose(ticket));
/* 58:   */         
/* 59:59 */         HelpTicketsManager.tickets.remove(Integer.valueOf(ticketId));
/* 60:   */         
/* 61:61 */         PlayerData client = Clients.getPlayerData(ticket.reporterId);
/* 62:62 */         if ((client != null) && (client.connection != null)) {
/* 63:63 */           QueueWriter.writeAndFlush(client.connection.socket, packet);
/* 64:   */         }
/* 65:   */       }
/* 66:   */     }
/* 67:   */   }
/* 68:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.moderator.CloseIssuesParser
 * JD-Core Version:    0.7.0.1
 */