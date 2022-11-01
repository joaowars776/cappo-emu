/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.gameevents.AddBallToMachine;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.MachineGameObject;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ 
/*  8:   */ public class SerializeGame2EventAddBallToMachine
/*  9:   */ {
/* 10:   */   public static void parse(MessageWriter ClientMessage, AddBallToMachine evt)
/* 11:   */   {
/* 12:15 */     Composer.add(Integer.valueOf(evt.gameItem.objectId), ClientMessage);
/* 13:   */   }
/* 14:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2EventAddBallToMachine
 * JD-Core Version:    0.7.0.1
 */