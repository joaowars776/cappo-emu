/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class PlaceObjectErrorComposer
/*  7:   */ {
/*  8:   */   public static final int CANT_TRADE_STUFF = 1;
/*  9:   */   public static final int CANT_SET_ITEM = 11;
/* 10:   */   public static final int MAX_STICKIES = 12;
/* 11:   */   public static final int MAX_FURNITURE = 20;
/* 12:   */   public static final int MAX_PETS = 21;
/* 13:   */   public static final int MAX_QUEUETILES = 22;
/* 14:   */   public static final int MAX_SOUNDFURNI = 23;
/* 15:   */   public static int HEADER;
/* 16:   */   
/* 17:   */   public static final MessageWriter compose(int errorCode)
/* 18:   */   {
/* 19:24 */     MessageWriter ClientMessage = new MessageWriter();
/* 20:25 */     Composer.initPacket(HEADER, ClientMessage);
/* 21:26 */     Composer.add(Integer.valueOf(errorCode), ClientMessage);
/* 22:27 */     Composer.endPacket(ClientMessage);
/* 23:28 */     return ClientMessage;
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.PlaceObjectErrorComposer
 * JD-Core Version:    0.7.0.1
 */