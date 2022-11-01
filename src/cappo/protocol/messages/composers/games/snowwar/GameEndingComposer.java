/*  1:   */ package cappo.protocol.messages.composers.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.SnowWar;
/*  5:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import cappo.protocol.messages.composers.serializers.SerializeGame2GameResult;
/*  8:   */ import cappo.protocol.messages.composers.serializers.SerializeGame2SnowWarGameStats;
/*  9:   */ import cappo.protocol.messages.composers.serializers.SerializeGame2TeamScoreData;
/* 10:   */ import java.util.Map;
/* 11:   */ 
/* 12:   */ public class GameEndingComposer
/* 13:   */ {
/* 14:   */   public static int HEADER;
/* 15:   */   
/* 16:   */   public static final MessageWriter compose(SnowWarRoom arena)
/* 17:   */   {
/* 18:21 */     MessageWriter ClientMessage = new MessageWriter(10000);
/* 19:22 */     Composer.initPacket(HEADER, ClientMessage);
/* 20:23 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 21:24 */     SerializeGame2GameResult.parse(ClientMessage, arena);
/* 22:25 */     Composer.add(Integer.valueOf(SnowWar.TEAMS.length), ClientMessage);
/* 23:26 */     for (int teamId : SnowWar.TEAMS) {
/* 24:27 */       SerializeGame2TeamScoreData.parse(ClientMessage, teamId, arena.TeamScore[(teamId - 1)], ((Map)arena.TeamPlayers.get(Integer.valueOf(teamId))).values());
/* 25:   */     }
/* 26:29 */     SerializeGame2SnowWarGameStats.parse(ClientMessage, arena);
/* 27:30 */     Composer.endPacket(ClientMessage);
/* 28:31 */     return ClientMessage;
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.snowwar.GameEndingComposer
 * JD-Core Version:    0.7.0.1
 */