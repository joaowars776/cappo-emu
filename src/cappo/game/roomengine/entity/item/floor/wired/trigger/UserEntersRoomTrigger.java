/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired.trigger;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import cappo.engine.logging.Log;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.roomengine.wired.WiredManager;
/*  9:   */ import java.sql.ResultSet;
/* 10:   */ import java.util.List;
/* 11:   */ 
/* 12:   */ public class UserEntersRoomTrigger
/* 13:   */   extends WiredTriggerBase
/* 14:   */ {
/* 15:   */   public String filterByName;
/* 16:   */   
/* 17:   */   public int getCode()
/* 18:   */   {
/* 19:14 */     return 7;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void setManager(WiredManager manager)
/* 23:   */   {
/* 24:19 */     super.setManager(manager);
/* 25:20 */     this.wiredManager.triggersEntersRoom.add(this);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void removeManager()
/* 29:   */   {
/* 30:25 */     this.wiredManager.triggersEntersRoom.remove(this);
/* 31:26 */     super.removeManager();
/* 32:   */   }
/* 33:   */   
/* 34:   */   public boolean launch(Connection cn, Object extra)
/* 35:   */   {
/* 36:31 */     if ((this.filterByName != null) && 
/* 37:32 */       (!this.filterByName.equals(cn.playerData.userName))) {
/* 38:33 */       return false;
/* 39:   */     }
/* 40:37 */     return super.launch(cn, null);
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void saveData()
/* 44:   */   {
/* 45:   */     try
/* 46:   */     {
/* 47:44 */       Database.exec("INSERT INTO trigger_item (trigger_id,trigger_data)VALUES(" + this.itemId + ",?) on DUPLICATE KEY UPDATE `trigger_data`=?;", new Object[] { getWiredData(), getWiredData() });
/* 48:45 */       super.saveData();
/* 49:   */     }
/* 50:   */     catch (Exception ex)
/* 51:   */     {
/* 52:47 */       Log.printException("ShowMessage-saveData", ex);
/* 53:   */     }
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void loadData(DBResult result)
/* 57:   */   {
/* 58:   */     try
/* 59:   */     {
/* 60:54 */       Database.query(result, "SELECT trigger_data FROM trigger_item WHERE trigger_id = " + this.itemId + ";", new Object[0]);
/* 61:55 */       if (result.data.next())
/* 62:   */       {
/* 63:56 */         this.filterByName = result.data.getString("trigger_data");
/* 64:57 */         if (this.filterByName.isEmpty()) {
/* 65:58 */           this.filterByName = null;
/* 66:   */         }
/* 67:   */       }
/* 68:61 */       super.loadData(result);
/* 69:   */     }
/* 70:   */     catch (Exception ex)
/* 71:   */     {
/* 72:63 */       Log.printException("ShowMessage-loadData", ex);
/* 73:   */     }
/* 74:   */   }
/* 75:   */   
/* 76:   */   public void setWiredData(String data)
/* 77:   */   {
/* 78:69 */     if (!data.isEmpty()) {
/* 79:70 */       this.filterByName = data;
/* 80:   */     } else {
/* 81:72 */       this.filterByName = null;
/* 82:   */     }
/* 83:   */   }
/* 84:   */   
/* 85:   */   public String getWiredData()
/* 86:   */   {
/* 87:78 */     return this.filterByName != null ? this.filterByName : "";
/* 88:   */   }
/* 89:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.trigger.UserEntersRoomTrigger
 * JD-Core Version:    0.7.0.1
 */