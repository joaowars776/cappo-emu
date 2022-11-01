/*  1:   */ package cappo.protocol.messages.composers.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class TradingCloseComposer
/*  7:   */ {
/*  8:   */   public static final int CLOSE = 0;
/*  9:   */   public static final int COMIT_ERROR = 1;
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(int ownerId, int reason)
/* 13:   */   {
/* 14:19 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:21 */     Composer.add(Integer.valueOf(ownerId), ClientMessage);
/* 17:22 */     Composer.add(Integer.valueOf(reason), ClientMessage);
/* 18:23 */     Composer.endPacket(ClientMessage);
/* 19:24 */     return ClientMessage;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.trading.TradingCloseComposer
 * JD-Core Version:    0.7.0.1
 */