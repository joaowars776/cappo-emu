/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import java.util.Collection;
/*  7:   */ 
/*  8:   */ public class SerializeGame2TeamScoreData
/*  9:   */ {
/* 10:   */   public static void parse(MessageWriter ClientMessage, int TeamId, int TeamScore, Collection<HumanGameObject> Players)
/* 11:   */   {
/* 12:17 */     Composer.add(Integer.valueOf(TeamId), ClientMessage);
/* 13:18 */     Composer.add(Integer.valueOf(TeamScore), ClientMessage);
/* 14:19 */     Composer.add(Integer.valueOf(Players.size()), ClientMessage);
/* 15:20 */     for (HumanGameObject Player : Players) {
/* 16:21 */       SerializeGame2TeamPlayerData.parse(ClientMessage, Player);
/* 17:   */     }
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2TeamScoreData
 * JD-Core Version:    0.7.0.1
 */