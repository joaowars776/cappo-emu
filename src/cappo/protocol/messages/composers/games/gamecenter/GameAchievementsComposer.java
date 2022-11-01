/*  1:   */ package cappo.protocol.messages.composers.games.gamecenter;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class GameAchievementsComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose()
/* 11:   */   {
/* 12:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:19 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 15:20 */     Composer.endPacket(ClientMessage);
/* 16:21 */     return ClientMessage;
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.gamecenter.GameAchievementsComposer
 * JD-Core Version:    0.7.0.1
 */