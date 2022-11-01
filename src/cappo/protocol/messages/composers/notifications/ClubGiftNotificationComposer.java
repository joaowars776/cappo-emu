/*  1:   */ package cappo.protocol.messages.composers.notifications;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class ClubGiftNotificationComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(int Total, int Ammount, int Type)
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add("", ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 16:   */     
/* 17:   */ 
/* 18:   */ 
/* 19:23 */     Composer.endPacket(ClientMessage);
/* 20:24 */     return ClientMessage;
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.notifications.ClubGiftNotificationComposer
 * JD-Core Version:    0.7.0.1
 */