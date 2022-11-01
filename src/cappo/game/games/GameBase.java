/*  1:   */ package cappo.game.games;
/*  2:   */ 
/*  3:   */ import java.util.Map;
/*  4:   */ 
/*  5:   */ public class GameBase
/*  6:   */ {
/*  7:   */   public boolean isEnabled;
/*  8:   */   public final int gameId;
/*  9:   */   public final String gameName;
/* 10:   */   public final String bgColor;
/* 11:   */   public final String textColor;
/* 12:   */   public final String imagesPath;
/* 13:   */   
/* 14:   */   public GameBase(int id, String code, String bgcolor, String textcolor, String imagespath)
/* 15:   */   {
/* 16:19 */     this.gameId = id;
/* 17:20 */     this.gameName = code;
/* 18:21 */     this.bgColor = bgcolor;
/* 19:22 */     this.textColor = textcolor;
/* 20:23 */     this.imagesPath = imagespath;
/* 21:   */     
/* 22:25 */     GamesLeaderboard.leaderboards.put(Integer.valueOf(this.gameId), new GamesLeaderboard(this.gameId));
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.GameBase
 * JD-Core Version:    0.7.0.1
 */