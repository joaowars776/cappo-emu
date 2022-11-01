/*  1:   */ package cappo.protocol.messages.composers.room.bots;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class BotErrorComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(int errorCode)
/* 11:   */   {
/* 12:16 */     MessageWriter clientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, clientMessage);
/* 14:18 */     Composer.add(Integer.valueOf(errorCode), clientMessage);
/* 15:19 */     Composer.endPacket(clientMessage);
/* 16:20 */     return clientMessage;
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.bots.BotErrorComposer
 * JD-Core Version:    0.7.0.1
 */