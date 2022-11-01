/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class SerializeGame2GameResult
/*  8:   */ {
/*  9:   */   public static void parse(MessageWriter ClientMessage, SnowWarRoom arena)
/* 10:   */   {
/* 11:15 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 12:16 */     Composer.add(Integer.valueOf(arena.Result), ClientMessage);
/* 13:17 */     Composer.add(Integer.valueOf(arena.Winner), ClientMessage);
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2GameResult
 * JD-Core Version:    0.7.0.1
 */