/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class SerializeGame2PlayerStatsData
/*  8:   */ {
/*  9:   */   public static void parse(MessageWriter ClientMessage, HumanGameObject Player)
/* 10:   */   {
/* 11:15 */     Composer.add(Integer.valueOf(Player.score), ClientMessage);
/* 12:16 */     Composer.add(Integer.valueOf(Player.kills), ClientMessage);
/* 13:17 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 14:18 */     Composer.add(Integer.valueOf(Player.hits), ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 16:20 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 18:22 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 19:23 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 20:24 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2PlayerStatsData
 * JD-Core Version:    0.7.0.1
 */