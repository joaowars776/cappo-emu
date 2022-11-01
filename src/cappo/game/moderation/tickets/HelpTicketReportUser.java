/* 1:  */ package cappo.game.moderation.tickets;
/* 2:  */ 
/* 3:  */ public class HelpTicketReportUser
/* 4:  */   extends HelpTicket
/* 5:  */ {
/* 6:  */   public HelpTicketReportUser(boolean inRoom)
/* 7:  */   {
/* 8:5 */     if (inRoom) {
/* 9:6 */       this.type = 2;
/* ::  */     } else {
/* ;:8 */       this.type = 1;
/* <:  */     }
/* =:  */   }
/* >:  */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.moderation.tickets.HelpTicketReportUser
 * JD-Core Version:    0.7.0.1
 */