/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.gameevents.BallThrowToPosition;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ 
/*  8:   */ public class SerializeGame2EventBallThrowToPosition
/*  9:   */ {
/* 10:   */   public static void parse(MessageWriter ClientMessage, BallThrowToPosition evt)
/* 11:   */   {
/* 12:15 */     Composer.add(Integer.valueOf(evt.attacker.objectId), ClientMessage);
/* 13:16 */     Composer.add(Integer.valueOf(evt.x), ClientMessage);
/* 14:17 */     Composer.add(Integer.valueOf(evt.y), ClientMessage);
/* 15:18 */     Composer.add(Integer.valueOf(evt.type), ClientMessage);
/* 16:   */   }
/* 17:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2EventBallThrowToPosition
 * JD-Core Version:    0.7.0.1
 */