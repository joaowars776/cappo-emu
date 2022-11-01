/*  1:   */ package cappo.protocol.messages.composers.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class TradingAlreadyOpenComposer
/*  7:   */ {
/*  8:   */   public static final int TRD_HOTEL_DISABLED = 1;
/*  9:   */   public static final int TRD_ACCOUNT_DISABLED = 2;
/* 10:   */   public static final int TRD_PENDING_ASD = 4;
/* 11:   */   public static final int TRD_ROOM_DISABLED = 6;
/* 12:   */   public static final int TRD_SELF_TRADING = 7;
/* 13:   */   public static final int TRD_OTHER_TRADING = 8;
/* 14:   */   public static int HEADER;
/* 15:   */   
/* 16:   */   public static final MessageWriter compose(int errorCode, String otherUser)
/* 17:   */   {
/* 18:24 */     MessageWriter ClientMessage = new MessageWriter();
/* 19:25 */     Composer.initPacket(HEADER, ClientMessage);
/* 20:26 */     Composer.add(Integer.valueOf(errorCode), ClientMessage);
/* 21:27 */     Composer.add(otherUser, ClientMessage);
/* 22:28 */     Composer.endPacket(ClientMessage);
/* 23:29 */     return ClientMessage;
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.trading.TradingAlreadyOpenComposer
 * JD-Core Version:    0.7.0.1
 */