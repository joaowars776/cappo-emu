/*  1:   */ package cappo.game.moderation;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import java.util.Map;
/*  7:   */ import java.util.concurrent.ConcurrentHashMap;
/*  8:   */ 
/*  9:   */ public class StaffManager
/* 10:   */ {
/* 11:11 */   public static final Map<Integer, Connection> staffs = new ConcurrentHashMap(100);
/* 12:   */   
/* 13:   */   public static void addStaff(int id, Connection cn)
/* 14:   */   {
/* 15:14 */     staffs.put(Integer.valueOf(id), cn);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public static void removeStaff(int id)
/* 19:   */   {
/* 20:18 */     staffs.remove(Integer.valueOf(id));
/* 21:   */   }
/* 22:   */   
/* 23:   */   public static void broadcast(MessageWriter packet)
/* 24:   */   {
/* 25:22 */     for (Connection cn : staffs.values()) {
/* 26:23 */       QueueWriter.writeAndFlush(cn.socket, packet);
/* 27:   */     }
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.moderation.StaffManager
 * JD-Core Version:    0.7.0.1
 */