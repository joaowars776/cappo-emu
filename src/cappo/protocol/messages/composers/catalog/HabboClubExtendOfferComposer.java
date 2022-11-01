/*  1:   */ package cappo.protocol.messages.composers.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import java.util.Calendar;
/*  6:   */ 
/*  7:   */ public class HabboClubExtendOfferComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose()
/* 12:   */   {
/* 13:18 */     Calendar calendar = Calendar.getInstance();
/* 14:   */     
/* 15:20 */     MessageWriter ClientMessage = new MessageWriter();
/* 16:21 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:22 */     Composer.add(Integer.valueOf(4), ClientMessage);
/* 18:   */     
/* 19:24 */     calendar.setTimeInMillis(System.currentTimeMillis());
/* 20:25 */     calendar.add(5, 31);
/* 21:26 */     Composer.add(Integer.valueOf(4896), ClientMessage);
/* 22:27 */     Composer.add("HABBO_CLUB_BASIC_1_MONTH", ClientMessage);
/* 23:28 */     Composer.add(Integer.valueOf(15), ClientMessage);
/* 24:29 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 25:30 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 26:31 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 27:32 */     Composer.add(Integer.valueOf(3), ClientMessage);
/* 28:33 */     Composer.add(Integer.valueOf(93), ClientMessage);
/* 29:34 */     Composer.add(Integer.valueOf(93), ClientMessage);
/* 30:35 */     Composer.add(Integer.valueOf(calendar.get(1)), ClientMessage);
/* 31:36 */     Composer.add(Integer.valueOf(calendar.get(2) + 1), ClientMessage);
/* 32:37 */     Composer.add(Integer.valueOf(calendar.get(5)), ClientMessage);
/* 33:   */     
/* 34:39 */     calendar.setTimeInMillis(System.currentTimeMillis());
/* 35:40 */     calendar.add(5, 93);
/* 36:41 */     Composer.add(Integer.valueOf(4897), ClientMessage);
/* 37:42 */     Composer.add("HABBO_CLUB_BASIC_3_MONTHS", ClientMessage);
/* 38:43 */     Composer.add(Integer.valueOf(25), ClientMessage);
/* 39:44 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 40:45 */     Composer.add(Integer.valueOf(3), ClientMessage);
/* 41:46 */     Composer.add(Integer.valueOf(93), ClientMessage);
/* 42:47 */     Composer.add(Integer.valueOf(93), ClientMessage);
/* 43:48 */     Composer.add(Integer.valueOf(calendar.get(1)), ClientMessage);
/* 44:49 */     Composer.add(Integer.valueOf(calendar.get(2) + 1), ClientMessage);
/* 45:50 */     Composer.add(Integer.valueOf(calendar.get(5)), ClientMessage);
/* 46:   */     
/* 47:52 */     calendar.setTimeInMillis(System.currentTimeMillis());
/* 48:53 */     calendar.add(5, 31);
/* 49:54 */     Composer.add(Integer.valueOf(4898), ClientMessage);
/* 50:55 */     Composer.add("HABBO_CLUB_VIP_1_MONTH", ClientMessage);
/* 51:56 */     Composer.add(Integer.valueOf(25), ClientMessage);
/* 52:57 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 53:58 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 54:59 */     Composer.add(Integer.valueOf(31), ClientMessage);
/* 55:60 */     Composer.add(Integer.valueOf(31), ClientMessage);
/* 56:61 */     Composer.add(Integer.valueOf(calendar.get(1)), ClientMessage);
/* 57:62 */     Composer.add(Integer.valueOf(calendar.get(2) + 1), ClientMessage);
/* 58:63 */     Composer.add(Integer.valueOf(calendar.get(5)), ClientMessage);
/* 59:   */     
/* 60:65 */     calendar.setTimeInMillis(System.currentTimeMillis());
/* 61:66 */     calendar.add(5, 93);
/* 62:67 */     Composer.add(Integer.valueOf(4899), ClientMessage);
/* 63:68 */     Composer.add("HABBO_CLUB_VIP_3_MONTHS", ClientMessage);
/* 64:69 */     Composer.add(Integer.valueOf(60), ClientMessage);
/* 65:70 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 66:71 */     Composer.add(Integer.valueOf(3), ClientMessage);
/* 67:72 */     Composer.add(Integer.valueOf(93), ClientMessage);
/* 68:73 */     Composer.add(Integer.valueOf(93), ClientMessage);
/* 69:74 */     Composer.add(Integer.valueOf(calendar.get(1)), ClientMessage);
/* 70:75 */     Composer.add(Integer.valueOf(calendar.get(2) + 1), ClientMessage);
/* 71:76 */     Composer.add(Integer.valueOf(calendar.get(5)), ClientMessage);
/* 72:   */     
/* 73:78 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 74:79 */     Composer.endPacket(ClientMessage);
/* 75:80 */     return ClientMessage;
/* 76:   */   }
/* 77:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.catalog.HabboClubExtendOfferComposer
 * JD-Core Version:    0.7.0.1
 */