/*  1:   */ package cappo.protocol.messages.composers.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class ScrUserInfoComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(Connection Main, int Type)
/* 12:   */   {
/* 13:17 */     int TotalDaysLeft = 1;
/* 14:   */     
/* 15:19 */     MessageWriter ClientMessage = new MessageWriter();
/* 16:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:21 */     Composer.add("habbo_club", ClientMessage);
/* 18:22 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 19:23 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 20:24 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 21:25 */     Composer.add(Integer.valueOf(Type), ClientMessage);
/* 22:26 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 23:27 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 24:28 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 25:29 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 26:30 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 27:31 */     Composer.endPacket(ClientMessage);
/* 28:32 */     return ClientMessage;
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.users.ScrUserInfoComposer
 * JD-Core Version:    0.7.0.1
 */