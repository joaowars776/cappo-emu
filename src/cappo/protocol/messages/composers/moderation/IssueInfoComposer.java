/*  1:   */ package cappo.protocol.messages.composers.moderation;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.moderation.tickets.HelpTicket;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class IssueInfoComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(HelpTicket ticket)
/* 12:   */   {
/* 13:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 14:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 15:19 */     serializeIssue(ticket, ClientMessage);
/* 16:20 */     Composer.endPacket(ClientMessage);
/* 17:21 */     return ClientMessage;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public static final void serializeIssue(HelpTicket ticket, MessageWriter ClientMessage)
/* 21:   */   {
/* 22:26 */     Composer.add(Integer.valueOf(ticket.id), ClientMessage);
/* 23:27 */     Composer.add(Short.valueOf(ticket.status), ClientMessage);
/* 24:28 */     Composer.add(Short.valueOf(ticket.type), ClientMessage);
/* 25:29 */     Composer.add(Short.valueOf(ticket.category), ClientMessage);
/* 26:30 */     Composer.add(Long.valueOf(System.currentTimeMillis() - ticket.timeStamp), ClientMessage);
/* 27:31 */     Composer.add(Short.valueOf(ticket.priority), ClientMessage);
/* 28:32 */     Composer.add(Integer.valueOf(ticket.reporterId), ClientMessage);
/* 29:33 */     Composer.add(ticket.reporterName, ClientMessage);
/* 30:34 */     Composer.add(Integer.valueOf(ticket.reportedId), ClientMessage);
/* 31:35 */     Composer.add(ticket.reportedName, ClientMessage);
/* 32:36 */     Composer.add(Integer.valueOf(ticket.handlerId), ClientMessage);
/* 33:37 */     Composer.add(ticket.handlerName, ClientMessage);
/* 34:38 */     Composer.add(ticket.text, ClientMessage);
/* 35:39 */     Composer.add(Integer.valueOf(ticket.chatLogId), ClientMessage);
/* 36:40 */     Composer.add(ticket.roomName, ClientMessage);
/* 37:41 */     Composer.add(Integer.valueOf(ticket.roomType), ClientMessage);
/* 38:42 */     if (ticket.roomType == 1)
/* 39:   */     {
/* 40:43 */       Composer.add("", ClientMessage);
/* 41:44 */       Composer.add(Integer.valueOf(ticket.roomId), ClientMessage);
/* 42:45 */       Composer.add("", ClientMessage);
/* 43:   */     }
/* 44:   */     else
/* 45:   */     {
/* 46:47 */       Composer.add("", ClientMessage);
/* 47:48 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 48:49 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 49:   */     }
/* 50:51 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.moderation.IssueInfoComposer
 * JD-Core Version:    0.7.0.1
 */