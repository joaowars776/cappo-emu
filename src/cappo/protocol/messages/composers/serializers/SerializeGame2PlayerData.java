/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class SerializeGame2PlayerData
/*  8:   */ {
/*  9:   */   public static void parse(MessageWriter ClientMessage, HumanGameObject Player)
/* 10:   */   {
/* 11:15 */     Composer.add(Integer.valueOf(Player.userId), ClientMessage);
/* 12:16 */     Composer.add(Player.userName, ClientMessage);
/* 13:17 */     Composer.add(Player.look, ClientMessage);
/* 14:18 */     Composer.add(Player.sex == 1 ? "M" : "F", ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(Player.team), ClientMessage);
/* 16:   */   }
/* 17:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2PlayerData
 * JD-Core Version:    0.7.0.1
 */