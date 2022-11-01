/*  1:   */ package cappo.protocol.messages.events.moderator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.moderation.StaffManager;
/*  7:   */ import cappo.game.moderation.tickets.HelpTicket;
/*  8:   */ import cappo.game.moderation.tickets.HelpTicketsManager;
/*  9:   */ import cappo.game.player.PlayerData;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ import cappo.protocol.messages.composers.moderation.IssueInfoComposer;
/* 12:   */ import cappo.protocol.messages.composers.moderation.IssuePickFailedComposer;
/* 13:   */ import java.util.ArrayList;
/* 14:   */ import java.util.List;
/* 15:   */ import java.util.Map;
/* 16:   */ 
/* 17:   */ public class PickIssuesParser
/* 18:   */   extends IncomingMessageEvent
/* 19:   */ {
/* 20:   */   public void messageReceived(Connection cn)
/* 21:   */   {
/* 22:24 */     if (!cn.playerData.allowModTools()) {
/* 23:25 */       return;
/* 24:   */     }
/* 25:28 */     int count = cn.currentPacket.readInt();
/* 26:29 */     if ((count < 1) || (count > 20)) {
/* 27:30 */       return;
/* 28:   */     }
/* 29:33 */     List<HelpTicket> failed = new ArrayList();
/* 30:35 */     for (int i = 0; i < count; i++)
/* 31:   */     {
/* 32:36 */       int ticketId = cn.currentPacket.readInt();
/* 33:37 */       HelpTicket ticket = (HelpTicket)HelpTicketsManager.tickets.get(Integer.valueOf(ticketId));
/* 34:39 */       if (ticket != null) {
/* 35:43 */         if (ticket.status != 1)
/* 36:   */         {
/* 37:44 */           failed.add(ticket);
/* 38:   */         }
/* 39:   */         else
/* 40:   */         {
/* 41:48 */           ticket.status = 2;
/* 42:49 */           ticket.handlerId = cn.playerData.userId;
/* 43:50 */           ticket.handlerName = cn.playerData.userName;
/* 44:   */           
/* 45:52 */           StaffManager.broadcast(IssueInfoComposer.compose(ticket));
/* 46:   */         }
/* 47:   */       }
/* 48:   */     }
/* 49:55 */     cn.currentPacket.readBoolean();
/* 50:56 */     cn.currentPacket.readInt();
/* 51:58 */     if (!failed.isEmpty()) {
/* 52:59 */       QueueWriter.write(cn.socket, IssuePickFailedComposer.compose(failed));
/* 53:   */     }
/* 54:   */   }
/* 55:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.moderator.PickIssuesParser
 * JD-Core Version:    0.7.0.1
 */