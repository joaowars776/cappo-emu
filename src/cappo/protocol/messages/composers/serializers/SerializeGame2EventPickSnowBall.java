/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.gameevents.MakeSnowBall;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ 
/*  8:   */ public class SerializeGame2EventPickSnowBall
/*  9:   */ {
/* 10:   */   public static void parse(MessageWriter ClientMessage, MakeSnowBall evt)
/* 11:   */   {
/* 12:15 */     Composer.add(Integer.valueOf(evt.player.objectId), ClientMessage);
/* 13:   */   }
/* 14:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2EventPickSnowBall
 * JD-Core Version:    0.7.0.1
 */