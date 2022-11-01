/*  1:   */ package cappo.game.collections;
/*  2:   */ 
/*  3:   */ import java.util.Map;
/*  4:   */ import java.util.concurrent.ConcurrentHashMap;
/*  5:   */ 
/*  6:   */ public class Teleports
/*  7:   */ {
/*  8:13 */   private static Map<Integer, Integer> ParentId = new ConcurrentHashMap();
/*  9:14 */   private static Map<Integer, Integer> RoomId = new ConcurrentHashMap();
/* 10:   */   
/* 11:   */   public static void delRoom(int Id)
/* 12:   */   {
/* 13:17 */     RoomId.remove(Integer.valueOf(Id));
/* 14:   */   }
/* 15:   */   
/* 16:   */   public static boolean teleLoaded(int Id)
/* 17:   */   {
/* 18:21 */     return RoomId.containsKey(Integer.valueOf(Id));
/* 19:   */   }
/* 20:   */   
/* 21:   */   public static int getRoom(int Id)
/* 22:   */   {
/* 23:25 */     if (RoomId.containsKey(Integer.valueOf(Id))) {
/* 24:26 */       return ((Integer)RoomId.get(Integer.valueOf(Id))).intValue();
/* 25:   */     }
/* 26:28 */     return -1;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public static int getTele(int Id)
/* 30:   */   {
/* 31:32 */     if (ParentId.containsKey(Integer.valueOf(Id))) {
/* 32:33 */       return ((Integer)ParentId.get(Integer.valueOf(Id))).intValue();
/* 33:   */     }
/* 34:35 */     return -1;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public static void setParents(int Id1, int Id2)
/* 38:   */   {
/* 39:39 */     ParentId.put(Integer.valueOf(Id1), Integer.valueOf(Id2));
/* 40:40 */     ParentId.put(Integer.valueOf(Id2), Integer.valueOf(Id1));
/* 41:   */   }
/* 42:   */   
/* 43:   */   public static void setRoom(int Id, int Room_Id)
/* 44:   */   {
/* 45:44 */     RoomId.put(Integer.valueOf(Id), Integer.valueOf(Room_Id));
/* 46:   */   }
/* 47:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.collections.Teleports
 * JD-Core Version:    0.7.0.1
 */