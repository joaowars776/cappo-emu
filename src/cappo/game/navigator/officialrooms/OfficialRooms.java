/*  1:   */ package cappo.game.navigator.officialrooms;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import java.sql.ResultSet;
/*  6:   */ import java.util.ArrayList;
/*  7:   */ import java.util.HashMap;
/*  8:   */ import java.util.List;
/*  9:   */ import java.util.Map;
/* 10:   */ 
/* 11:   */ public class OfficialRooms
/* 12:   */ {
/* 13:   */   public static final int TAG_SEARCH = 1;
/* 14:   */   public static final int ROOM = 2;
/* 15:   */   public static final int TAB = 4;
/* 16:23 */   public static Map<Integer, List<Official>> items = new HashMap();
/* 17:   */   public static int SIZE;
/* 18:   */   
/* 19:   */   public static void init(DBResult result)
/* 20:   */     throws Exception
/* 21:   */   {
/* 22:27 */     items.clear();
/* 23:28 */     SIZE = 0;
/* 24:   */     
/* 25:30 */     Database.query(result, "SELECT * FROM navigator_official WHERE enabled='1' ORDER BY order_id ASC;", new Object[0]);
/* 26:31 */     while (result.data.next())
/* 27:   */     {
/* 28:32 */       int type = result.data.getInt("type");
/* 29:33 */       Official item = null;
/* 30:34 */       if (type == 2)
/* 31:   */       {
/* 32:35 */         item = new OfficialRoom(result.data);
/* 33:   */       }
/* 34:36 */       else if (type == 4)
/* 35:   */       {
/* 36:37 */         item = new OfficialRoomTab(result.data);
/* 37:   */       }
/* 38:   */       else
/* 39:   */       {
/* 40:38 */         if (type != 1) {
/* 41:   */           continue;
/* 42:   */         }
/* 43:39 */         item = new OfficialRoomTagSearch(result.data);
/* 44:   */       }
/* 45:44 */       List<Official> a = (List)items.get(Integer.valueOf(item.parentId));
/* 46:45 */       if (a == null)
/* 47:   */       {
/* 48:46 */         a = new ArrayList();
/* 49:47 */         items.put(Integer.valueOf(item.parentId), a);
/* 50:   */       }
/* 51:49 */       a.add(item);
/* 52:   */       
/* 53:51 */       SIZE += 1;
/* 54:   */     }
/* 55:   */   }
/* 56:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.navigator.officialrooms.OfficialRooms
 * JD-Core Version:    0.7.0.1
 */