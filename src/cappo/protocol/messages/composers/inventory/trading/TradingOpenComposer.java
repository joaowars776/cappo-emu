/*  1:   */ package cappo.protocol.messages.composers.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class TradingOpenComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(int inviter, int receiver)
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(Integer.valueOf(inviter), ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 16:20 */     Composer.add(Integer.valueOf(receiver), ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 18:22 */     Composer.endPacket(ClientMessage);
/* 19:23 */     return ClientMessage;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.trading.TradingOpenComposer
 * JD-Core Version:    0.7.0.1
 */