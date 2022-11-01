/*  1:   */ package cappo.game.games;
/*  2:   */ 
/*  3:   */ import cappo.game.games.basejump.BaseJump;
/*  4:   */ import cappo.game.games.snowwar.SnowWar;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import java.util.List;
/*  7:   */ 
/*  8:   */ public class GamesManager
/*  9:   */ {
/* 10:16 */   public static List<GameBase> games = new ArrayList();
/* 11:   */   
/* 12:   */   public static void initManager()
/* 13:   */   {
/* 14:19 */     addGame(new SnowWar(), true);
/* 15:20 */     addGame(new BaseJump(), true);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public static void addGame(GameBase game, boolean enabled)
/* 19:   */   {
/* 20:24 */     games.add(game);
/* 21:25 */     game.isEnabled = enabled;
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.GamesManager
 * JD-Core Version:    0.7.0.1
 */