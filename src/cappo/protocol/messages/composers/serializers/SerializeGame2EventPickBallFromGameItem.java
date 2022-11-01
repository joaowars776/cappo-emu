/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.gameevents.PickBallFromGameItem;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  6:   */ import cappo.game.games.snowwar.gameobjects.PickBallsGameItemObject;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ 
/*  9:   */ public class SerializeGame2EventPickBallFromGameItem
/* 10:   */ {
/* 11:   */   public static void parse(MessageWriter ClientMessage, PickBallFromGameItem evt)
/* 12:   */   {
/* 13:15 */     Composer.add(Integer.valueOf(evt.player.objectId), ClientMessage);
/* 14:16 */     Composer.add(Integer.valueOf(evt.gameItem.objectId), ClientMessage);
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2EventPickBallFromGameItem
 * JD-Core Version:    0.7.0.1
 */