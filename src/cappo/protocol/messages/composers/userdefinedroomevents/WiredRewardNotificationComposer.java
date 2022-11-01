/*  1:   */ package cappo.protocol.messages.composers.userdefinedroomevents;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class WiredRewardNotificationComposer
/*  7:   */ {
/*  8:   */   public static final int ERR1 = 0;
/*  9:   */   public static final int ERR2 = 1;
/* 10:   */   public static final int ERR3 = 2;
/* 11:   */   public static final int ERR4 = 3;
/* 12:   */   public static final int NON_LUCKY = 4;
/* 13:   */   public static final int ERR6 = 5;
/* 14:   */   public static final int REWARD_SUCCESS = 6;
/* 15:   */   public static final int BADGE_RECEIVED = 7;
/* 16:   */   public static int HEADER;
/* 17:   */   
/* 18:   */   public static final MessageWriter compose(int reason)
/* 19:   */   {
/* 20:33 */     MessageWriter ClientMessage = new MessageWriter();
/* 21:34 */     Composer.initPacket(HEADER, ClientMessage);
/* 22:35 */     Composer.add(Integer.valueOf(reason), ClientMessage);
/* 23:36 */     Composer.endPacket(ClientMessage);
/* 24:37 */     return ClientMessage;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.userdefinedroomevents.WiredRewardNotificationComposer
 * JD-Core Version:    0.7.0.1
 */