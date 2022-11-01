/*  1:   */ package cappo.game.moderation.tickets;
/*  2:   */ 
/*  3:   */ import cappo.engine.Server;
/*  4:   */ import cappo.game.moderation.StaffManager;
/*  5:   */ import cappo.protocol.messages.composers.moderation.IssueInfoComposer;
/*  6:   */ import java.util.Map;
/*  7:   */ import java.util.concurrent.ConcurrentHashMap;
/*  8:   */ 
/*  9:   */ public class HelpTicketsManager
/* 10:   */ {
/* 11:11 */   public static int ticketCount = 1;
/* 12:13 */   public static final Map<Integer, HelpTicket> tickets = new ConcurrentHashMap(100);
/* 13:   */   
/* 14:   */   public static void addTicket(HelpTicket ticket)
/* 15:   */   {
/* 16:16 */     if (Server.blockTickets) {
/* 17:17 */       return;
/* 18:   */     }
/* 19:20 */     ticket.status = 1;
/* 20:21 */     ticket.priority = 1;
/* 21:22 */     ticket.chatLogId = 1;
/* 22:23 */     ticket.timeStamp = System.currentTimeMillis();
/* 23:24 */     ticket.handlerName = "";
/* 24:   */     
/* 25:26 */     ticket.id = (ticketCount++);
/* 26:27 */     tickets.put(Integer.valueOf(ticket.id), ticket);
/* 27:   */     
/* 28:29 */     StaffManager.broadcast(IssueInfoComposer.compose(ticket));
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.moderation.tickets.HelpTicketsManager
 * JD-Core Version:    0.7.0.1
 */