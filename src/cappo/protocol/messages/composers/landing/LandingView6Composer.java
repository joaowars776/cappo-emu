/*  1:   */ package cappo.protocol.messages.composers.landing;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class LandingView6Composer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose()
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add("africaJungle", ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(2), ClientMessage);
/* 16:   */     
/* 17:   */ 
/* 18:22 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 19:23 */     Composer.add("capostrike93", ClientMessage);
/* 20:24 */     Composer.add("ch-3111-63-62.hd-3103-1.hr-3163-39.lg-285-77.sh-305-78", ClientMessage);
/* 21:25 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 22:26 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 23:   */     
/* 24:   */ 
/* 25:29 */     Composer.add(Integer.valueOf(2), ClientMessage);
/* 26:30 */     Composer.add("Nick", ClientMessage);
/* 27:31 */     Composer.add("ch-3111-63-62.hd-3103-1.hr-3163-39.lg-285-77.sh-305-78", ClientMessage);
/* 28:32 */     Composer.add(Integer.valueOf(2), ClientMessage);
/* 29:33 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 30:   */     
/* 31:   */ 
/* 32:36 */     Composer.endPacket(ClientMessage);
/* 33:37 */     return ClientMessage;
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.landing.LandingView6Composer
 * JD-Core Version:    0.7.0.1
 */