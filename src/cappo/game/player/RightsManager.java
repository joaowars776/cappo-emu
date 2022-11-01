/*  1:   */ package cappo.game.player;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import java.lang.reflect.Field;
/*  6:   */ import java.sql.ResultSet;
/*  7:   */ 
/*  8:   */ public class RightsManager
/*  9:   */ {
/* 10:   */   public static void load(DBResult result)
/* 11:   */     throws Exception
/* 12:   */   {
/* 13: 8 */     Database.query(result, "SELECT * FROM rights_manager;", new Object[0]);
/* 14: 9 */     while (result.data.next())
/* 15:   */     {
/* 16:10 */       int rank = result.data.getInt("id");
/* 17:11 */       if ((rank >= 2) && (rank <= 9))
/* 18:   */       {
/* 19:15 */         Class<?> plrClass = PlayerData.securityLevelPlr[rank];
/* 20:   */         
/* 21:17 */         plrClass.getField("allowRoomAlert").setBoolean(null, 
/* 22:18 */           result.data.getInt("allow_roomalert") == 1);
/* 23:   */         
/* 24:   */ 
/* 25:21 */         plrClass.getField("allowPickFurni").setBoolean(null, 
/* 26:22 */           result.data.getInt("allow_pick") == 1);
/* 27:   */         
/* 28:   */ 
/* 29:25 */         plrClass.getField("allowEjectFurni").setBoolean(null, 
/* 30:26 */           result.data.getInt("allow_eject") == 1);
/* 31:   */         
/* 32:   */ 
/* 33:29 */         plrClass.getField("allowRoomControl").setBoolean(null, 
/* 34:30 */           result.data.getInt("allow_roomcontrol") == 1);
/* 35:   */         
/* 36:   */ 
/* 37:33 */         plrClass.getField("allowModTools").setBoolean(null, 
/* 38:34 */           result.data.getInt("allow_modtools") == 1);
/* 39:   */         
/* 40:   */ 
/* 41:37 */         plrClass.getField("allowBan").setBoolean(null, 
/* 42:38 */           result.data.getInt("allow_ban") == 1);
/* 43:   */         
/* 44:   */ 
/* 45:41 */         plrClass.getField("allowGiveBadge").setBoolean(null, 
/* 46:42 */           result.data.getInt("allow_givebadge") == 1);
/* 47:   */         
/* 48:   */ 
/* 49:45 */         plrClass.getField("allowHotelAlert").setBoolean(null, 
/* 50:46 */           result.data.getInt("allow_ha") == 1);
/* 51:   */         
/* 52:   */ 
/* 53:49 */         plrClass.getField("allowGiveMoney").setBoolean(null, 
/* 54:50 */           result.data.getInt("allow_givemoney") == 1);
/* 55:   */       }
/* 56:   */     }
/* 57:   */   }
/* 58:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.RightsManager
 * JD-Core Version:    0.7.0.1
 */