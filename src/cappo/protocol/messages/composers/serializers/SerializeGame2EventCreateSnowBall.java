/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.gameevents.CreateSnowBall;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  6:   */ import cappo.game.games.snowwar.gameobjects.SnowBallGameObject;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ 
/*  9:   */ public class SerializeGame2EventCreateSnowBall
/* 10:   */ {
/* 11:   */   public static void parse(MessageWriter ClientMessage, CreateSnowBall evt)
/* 12:   */   {
/* 13:15 */     Composer.add(Integer.valueOf(evt.ball.objectId), ClientMessage);
/* 14:16 */     Composer.add(Integer.valueOf(evt.player.objectId), ClientMessage);
/* 15:17 */     Composer.add(Integer.valueOf(evt.x), ClientMessage);
/* 16:18 */     Composer.add(Integer.valueOf(evt.y), ClientMessage);
/* 17:19 */     Composer.add(Integer.valueOf(evt.type), ClientMessage);
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2EventCreateSnowBall
 * JD-Core Version:    0.7.0.1
 */