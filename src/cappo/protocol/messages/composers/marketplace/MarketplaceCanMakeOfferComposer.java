/*  1:   */ package cappo.protocol.messages.composers.marketplace;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class MarketplaceCanMakeOfferComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(int Result, int ErrorCode)
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(Integer.valueOf(Result), ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(ErrorCode), ClientMessage);
/* 16:20 */     Composer.endPacket(ClientMessage);
/* 17:21 */     return ClientMessage;
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.marketplace.MarketplaceCanMakeOfferComposer
 * JD-Core Version:    0.7.0.1
 */