/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.player.AvatarLook;
/*  5:   */ import cappo.game.player.PlayerData;
/*  6:   */ import cappo.game.player.SnowWarPlayerData;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ import java.util.List;
/*  9:   */ 
/* 10:   */ public class SerializeGame2Leaderboard
/* 11:   */ {
/* 12:   */   public static void parse(MessageWriter clientMessage, List<SnowWarPlayerData> rankedList, int gameId)
/* 13:   */   {
/* 14:17 */     Composer.add(Integer.valueOf(rankedList.size()), clientMessage);
/* 15:18 */     for (SnowWarPlayerData player : rankedList)
/* 16:   */     {
/* 17:19 */       Composer.add(Integer.valueOf(player.player.userId), clientMessage);
/* 18:20 */       Composer.add(Integer.valueOf(player.score), clientMessage);
/* 19:21 */       Composer.add(Integer.valueOf(player.rank), clientMessage);
/* 20:22 */       Composer.add(player.player.userName, clientMessage);
/* 21:23 */       Composer.add(player.player.avatarLook.toString(), clientMessage);
/* 22:24 */       Composer.add(player.player.sex == 1 ? "M" : "F", clientMessage);
/* 23:   */     }
/* 24:26 */     Composer.add(Integer.valueOf(0), clientMessage);
/* 25:27 */     Composer.add(Integer.valueOf(gameId), clientMessage);
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2Leaderboard
 * JD-Core Version:    0.7.0.1
 */