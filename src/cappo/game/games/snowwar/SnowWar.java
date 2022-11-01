/*  1:   */ package cappo.game.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.game.games.GameBase;
/*  4:   */ 
/*  5:   */ public class SnowWar
/*  6:   */   extends GameBase
/*  7:   */ {
/*  8:12 */   public static SnowWarArenaBase[] ArenaTypes = {
/*  9:13 */     new SnowWarArena8(), 
/* 10:14 */     new SnowWarArena9(), 
/* 11:15 */     new SnowWarArena11() };
/* 12:   */   public static final int GAMESECONDS = 120;
/* 13:   */   public static final int GAMETURNMILLIS = 150;
/* 14:   */   public static final int GAMETURNS = 800;
/* 15:   */   public static final int MINPLAYERS = 4;
/* 16:   */   public static final int MAXPLAYERS = 10;
/* 17:   */   public static final int INLOBBY = 0;
/* 18:   */   public static final int INARENA = 1;
/* 19:   */   public static final int CLOSE = 0;
/* 20:   */   public static final int TIMER_TOLOBBY = 1;
/* 21:   */   public static final int STAGE_LOADING = 2;
/* 22:   */   public static final int STAGE_STARTING = 3;
/* 23:   */   public static final int STAGE_RUNNING = 4;
/* 24:   */   public static final int ARENA = 5;
/* 25:   */   public static final int ARENA_END = 6;
/* 26:   */   public static final int TEAM_BLUE = 1;
/* 27:   */   public static final int TEAM_RED = 2;
/* 28:43 */   public static final int[] TEAMS = {
/* 29:44 */     1, 
/* 30:45 */     2 };
/* 31:   */   
/* 32:   */   public SnowWar()
/* 33:   */   {
/* 34:49 */     super(0, "snowwar", "93d4f3", "", "http://dcr.lavvos.pl/lavvos/c_images/gamecenter_snowwar/");
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.SnowWar
 * JD-Core Version:    0.7.0.1
 */