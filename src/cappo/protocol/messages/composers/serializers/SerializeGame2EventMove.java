/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.gameevents.UserMove;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ 
/*  8:   */ public class SerializeGame2EventMove
/*  9:   */ {
/* 10:   */   public static void parse(MessageWriter ClientMessage, UserMove evt)
/* 11:   */   {
/* 12:15 */     Composer.add(Integer.valueOf(evt.player.objectId), ClientMessage);
/* 13:16 */     Composer.add(Integer.valueOf(evt.x), ClientMessage);
/* 14:17 */     Composer.add(Integer.valueOf(evt.y), ClientMessage);
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2EventMove
 * JD-Core Version:    0.7.0.1
 */