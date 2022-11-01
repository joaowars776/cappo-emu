/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ 
/*  8:   */ public class SerializeGame2SnowWarGameStats
/*  9:   */ {
/* 10:   */   public static void parse(MessageWriter ClientMessage, SnowWarRoom arena)
/* 11:   */   {
/* 12:15 */     Composer.add(Integer.valueOf(arena.MostKills.userId), ClientMessage);
/* 13:16 */     Composer.add(Integer.valueOf(arena.MostHits.userId), ClientMessage);
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2SnowWarGameStats
 * JD-Core Version:    0.7.0.1
 */