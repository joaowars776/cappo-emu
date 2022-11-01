/*  1:   */ package cappo.protocol.messages.composers.notifications;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class ActivityPointsComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(int pixels, int diamonds)
/* 11:   */   {
/* 12:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:19 */     Composer.add(Integer.valueOf(2), ClientMessage);
/* 15:20 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 16:21 */     Composer.add(Integer.valueOf(pixels), ClientMessage);
/* 17:22 */     Composer.add(Integer.valueOf(105), ClientMessage);
/* 18:23 */     Composer.add(Integer.valueOf(diamonds), ClientMessage);
/* 19:24 */     Composer.endPacket(ClientMessage);
/* 20:25 */     return ClientMessage;
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.notifications.ActivityPointsComposer
 * JD-Core Version:    0.7.0.1
 */