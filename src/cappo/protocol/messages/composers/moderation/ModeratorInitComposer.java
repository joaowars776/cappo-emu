/*  1:   */ package cappo.protocol.messages.composers.moderation;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.moderation.tickets.HelpTicket;
/*  5:   */ import cappo.game.moderation.tickets.HelpTicketsManager;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class ModeratorInitComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose()
/* 14:   */   {
/* 15:18 */     MessageWriter ClientMessage = new MessageWriter(1000 + 300 * HelpTicketsManager.tickets.size());
/* 16:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */     Composer.add(Integer.valueOf(HelpTicketsManager.tickets.size()), ClientMessage);
/* 18:21 */     for (HelpTicket ticket : HelpTicketsManager.tickets.values()) {
/* 19:22 */       IssueInfoComposer.serializeIssue(ticket, ClientMessage);
/* 20:   */     }
/* 21:25 */     Composer.add(Integer.valueOf(3), ClientMessage);
/* 22:   */     
/* 23:27 */     Composer.add("mensaje predeterminado 1", ClientMessage);
/* 24:28 */     Composer.add("mensaje predeterminado 2", ClientMessage);
/* 25:29 */     Composer.add("mensaje predeterminado 3", ClientMessage);
/* 26:   */     
/* 27:   */ 
/* 28:32 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 29:   */     
/* 30:34 */     Composer.add("Acoso Sexual", ClientMessage);
/* 31:   */     
/* 32:36 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 33:37 */     Composer.add(Integer.valueOf(2), ClientMessage);
/* 34:   */     
/* 35:39 */     Composer.add("Me habbo violo", ClientMessage);
/* 36:40 */     Composer.add("Tonterias que se mandaran...", ClientMessage);
/* 37:41 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 38:42 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 39:43 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 40:44 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 41:45 */     Composer.add("", ClientMessage);
/* 42:   */     
/* 43:   */ 
/* 44:48 */     Composer.add("Pidio Sexo", ClientMessage);
/* 45:49 */     Composer.add("Tonterias que se mandaran...", ClientMessage);
/* 46:50 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 47:51 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 48:52 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 49:53 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 50:54 */     Composer.add("", ClientMessage);
/* 51:   */     
/* 52:   */ 
/* 53:   */ 
/* 54:58 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 55:59 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 56:60 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 57:61 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 58:62 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 59:63 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 60:64 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 61:   */     
/* 62:66 */     Composer.add(Integer.valueOf(4), ClientMessage);
/* 63:   */     
/* 64:68 */     Composer.add("Test template 1", ClientMessage);
/* 65:69 */     Composer.add("Test template 2", ClientMessage);
/* 66:70 */     Composer.add("Test template 3", ClientMessage);
/* 67:71 */     Composer.add("Test template 4", ClientMessage);
/* 68:   */     
/* 69:   */ 
/* 70:74 */     Composer.endPacket(ClientMessage);
/* 71:75 */     return ClientMessage;
/* 72:   */   }
/* 73:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.moderation.ModeratorInitComposer
 * JD-Core Version:    0.7.0.1
 */