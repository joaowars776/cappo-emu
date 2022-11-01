/*  1:   */ package cappo.protocol.messages.composers.moderation;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.moderation.tickets.HelpTicket;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import java.util.List;
/*  7:   */ 
/*  8:   */ public class IssuePickFailedComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(List<HelpTicket> tickets)
/* 13:   */   {
/* 14:19 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:21 */     Composer.add(Integer.valueOf(tickets.size()), ClientMessage);
/* 17:22 */     for (HelpTicket ticket : tickets)
/* 18:   */     {
/* 19:23 */       Composer.add(Integer.valueOf(ticket.id), ClientMessage);
/* 20:24 */       Composer.add(Integer.valueOf(ticket.handlerId), ClientMessage);
/* 21:25 */       Composer.add(ticket.handlerName, ClientMessage);
/* 22:   */     }
/* 23:27 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 24:28 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 25:29 */     Composer.endPacket(ClientMessage);
/* 26:30 */     return ClientMessage;
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.moderation.IssuePickFailedComposer
 * JD-Core Version:    0.7.0.1
 */