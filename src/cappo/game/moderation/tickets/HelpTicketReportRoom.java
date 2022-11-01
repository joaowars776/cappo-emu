/* 1:  */ package cappo.game.moderation.tickets;
/* 2:  */ 
/* 3:  */ public class HelpTicketReportRoom
/* 4:  */   extends HelpTicket
/* 5:  */ {
/* 6:  */   public HelpTicketReportRoom(boolean panic)
/* 7:  */   {
/* 8:5 */     if (panic) {
/* 9:6 */       this.type = 8;
/* ::  */     } else {
/* ;:8 */       this.type = 7;
/* <:  */     }
/* =:  */   }
/* >:  */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.moderation.tickets.HelpTicketReportRoom
 * JD-Core Version:    0.7.0.1
 */