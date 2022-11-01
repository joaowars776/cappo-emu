/*  1:   */ package cappo.protocol.messages.composers.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class StageRunningComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   private static MessageWriter ClientMessage;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(int seconds)
/* 12:   */   {
/* 13:17 */     if (ClientMessage == null)
/* 14:   */     {
/* 15:18 */       ClientMessage = new MessageWriter();
/* 16:19 */       Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */       Composer.add(Integer.valueOf(seconds), ClientMessage);
/* 18:21 */       Composer.endPacket(ClientMessage);
/* 19:   */     }
/* 20:23 */     return ClientMessage;
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.snowwar.StageRunningComposer
 * JD-Core Version:    0.7.0.1
 */