/*  1:   */ package cappo.protocol.messages.events.moderator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.moderation.StaffManager;
/*  6:   */ import cappo.game.moderation.tickets.HelpTicket;
/*  7:   */ import cappo.game.moderation.tickets.HelpTicketsManager;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.moderation.IssueInfoComposer;
/* 11:   */ import java.util.Map;
/* 12:   */ 
/* 13:   */ public class ReleaseIssuesParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection cn)
/* 17:   */   {
/* 18:19 */     if (!cn.playerData.allowModTools()) {
/* 19:20 */       return;
/* 20:   */     }
/* 21:23 */     int count = cn.currentPacket.readInt();
/* 22:24 */     if ((count < 1) || (count > 20)) {
/* 23:25 */       return;
/* 24:   */     }
/* 25:28 */     for (int i = 0; i < count; i++)
/* 26:   */     {
/* 27:29 */       int ticketId = cn.currentPacket.readInt();
/* 28:30 */       HelpTicket ticket = (HelpTicket)HelpTicketsManager.tickets.get(Integer.valueOf(ticketId));
/* 29:32 */       if ((ticket != null) && (ticket.status == 2) && (ticket.handlerId == cn.playerData.userId))
/* 30:   */       {
/* 31:36 */         ticket.status = 1;
/* 32:37 */         ticket.handlerId = 0;
/* 33:38 */         ticket.handlerName = "";
/* 34:   */         
/* 35:40 */         StaffManager.broadcast(IssueInfoComposer.compose(ticket));
/* 36:   */       }
/* 37:   */     }
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.moderator.ReleaseIssuesParser
 * JD-Core Version:    0.7.0.1
 */