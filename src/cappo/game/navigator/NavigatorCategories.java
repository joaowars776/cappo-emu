/*  1:   */ package cappo.game.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import java.sql.ResultSet;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class NavigatorCategories
/* 10:   */ {
/* 11:   */   public int id;
/* 12:   */   public String caption;
/* 13:   */   public int min_rank;
/* 14:20 */   public static Map<Integer, NavigatorCategories> roomCategories = new HashMap();
/* 15:   */   public static int MAX_ID;
/* 16:   */   
/* 17:   */   public NavigatorCategories(int ID, String name, int minrank)
/* 18:   */   {
/* 19:24 */     this.id = ID;
/* 20:25 */     this.caption = name;
/* 21:26 */     this.min_rank = minrank;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public static void Init(DBResult result)
/* 25:   */     throws Exception
/* 26:   */   {
/* 27:30 */     Database.query(result, "SELECT * FROM navigator_flatcats WHERE enabled='1';", new Object[0]);
/* 28:31 */     while (result.data.next())
/* 29:   */     {
/* 30:32 */       NavigatorCategories cat = new NavigatorCategories(result.data.getInt("id"), result.data.getString("caption"), result.data.getInt("min_rank"));
/* 31:33 */       roomCategories.put(Integer.valueOf(cat.id), cat);
/* 32:34 */       if (MAX_ID < cat.id) {
/* 33:35 */         MAX_ID = cat.id;
/* 34:   */       }
/* 35:   */     }
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.navigator.NavigatorCategories
 * JD-Core Version:    0.7.0.1
 */