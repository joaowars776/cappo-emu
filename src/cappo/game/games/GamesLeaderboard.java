/*  1:   */ package cappo.game.games;
/*  2:   */ 
/*  3:   */ import cappo.game.player.SnowWarPlayerData;
/*  4:   */ import java.util.List;
/*  5:   */ import java.util.Map;
/*  6:   */ import java.util.concurrent.ConcurrentHashMap;
/*  7:   */ 
/*  8:   */ public class GamesLeaderboard
/*  9:   */ {
/* 10:16 */   public static final Map<Integer, GamesLeaderboard> leaderboards = new ConcurrentHashMap();
/* 11:   */   public final int gameId;
/* 12:   */   public List<SnowWarPlayerData> rankedList;
/* 13:   */   
/* 14:   */   public GamesLeaderboard(int id)
/* 15:   */   {
/* 16:23 */     this.gameId = id;
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.GamesLeaderboard
 * JD-Core Version:    0.7.0.1
 */