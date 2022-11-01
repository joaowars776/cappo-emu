/*  1:   */ package cappo.game.sound.trax;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import java.sql.ResultSet;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class Trax
/* 10:   */ {
/* 11:16 */   public static Map<Integer, TraxDisc> songDiscs = new HashMap();
/* 12:   */   
/* 13:   */   public static void Init(DBResult result)
/* 14:   */     throws Exception
/* 15:   */   {
/* 16:19 */     songDiscs.clear();
/* 17:   */     
/* 18:21 */     Database.query(result, "SELECT * FROM songs;", new Object[0]);
/* 19:22 */     while (result.data.next())
/* 20:   */     {
/* 21:23 */       TraxDisc Disc = new TraxDisc(result.data.getInt("id"), result.data.getString("name"), result.data.getString("song_data"), result.data.getInt("length"), result.data.getString("artist"));
/* 22:24 */       songDiscs.put(Integer.valueOf(Disc.Id), Disc);
/* 23:   */     }
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.sound.trax.Trax
 * JD-Core Version:    0.7.0.1
 */