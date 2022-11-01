/*  1:   */ package cappo.game.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.gameobjects.GameItemObject;
/*  4:   */ import java.util.ArrayList;
/*  5:   */ import java.util.List;
/*  6:   */ import java.util.Map;
/*  7:   */ 
/*  8:   */ public abstract class SnowWarArenaBase
/*  9:   */ {
/* 10:   */   public int ArenaHeight;
/* 11:   */   public int ArenaType;
/* 12:   */   public int ArenaWidth;
/* 13:   */   public String HeightMap;
/* 14:20 */   public List<GamefuseObject> fuseObjects = new ArrayList(200);
/* 15:21 */   public List<SpawnPoint> spawnsBLUE = new ArrayList(5);
/* 16:22 */   public List<SpawnPoint> spawnsRED = new ArrayList(5);
/* 17:   */   
/* 18:   */   public abstract void gameObjects(Map<Integer, GameItemObject> paramMap, SnowWarRoom paramSnowWarRoom);
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.SnowWarArenaBase
 * JD-Core Version:    0.7.0.1
 */