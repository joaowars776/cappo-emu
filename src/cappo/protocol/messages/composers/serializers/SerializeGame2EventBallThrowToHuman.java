/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.gameevents.BallThrowToHuman;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ 
/*  8:   */ public class SerializeGame2EventBallThrowToHuman
/*  9:   */ {
/* 10:   */   public static void parse(MessageWriter ClientMessage, BallThrowToHuman evt)
/* 11:   */   {
/* 12:15 */     Composer.add(Integer.valueOf(evt.attacker.objectId), ClientMessage);
/* 13:16 */     Composer.add(Integer.valueOf(evt.victim.objectId), ClientMessage);
/* 14:17 */     Composer.add(Integer.valueOf(evt.type), ClientMessage);
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2EventBallThrowToHuman
 * JD-Core Version:    0.7.0.1
 */