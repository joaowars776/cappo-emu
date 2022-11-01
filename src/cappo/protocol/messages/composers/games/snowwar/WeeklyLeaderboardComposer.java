/*  1:   */ package cappo.protocol.messages.composers.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.GamesLeaderboard;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializeGame2Leaderboard;
/*  7:   */ 
/*  8:   */ public class WeeklyLeaderboardComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(GamesLeaderboard leaderboard)
/* 13:   */   {
/* 14:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:20 */     Composer.add(Integer.valueOf(2012), ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 18:22 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 19:23 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 20:24 */     Composer.add(Integer.valueOf(23), ClientMessage);
/* 21:25 */     SerializeGame2Leaderboard.parse(ClientMessage, leaderboard.rankedList, leaderboard.gameId);
/* 22:26 */     Composer.endPacket(ClientMessage);
/* 23:27 */     return ClientMessage;
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.snowwar.WeeklyLeaderboardComposer
 * JD-Core Version:    0.7.0.1
 */