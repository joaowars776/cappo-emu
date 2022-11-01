/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired.trigger;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import cappo.engine.logging.Log;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.roomengine.wired.WiredManager;
/*  8:   */ import java.sql.ResultSet;
/*  9:   */ import java.util.List;
/* 10:   */ 
/* 11:   */ public class UserSaysPhraseTrigger
/* 12:   */   extends WiredTriggerBase
/* 13:   */ {
/* 14:   */   public String keyword;
/* 15:   */   
/* 16:   */   public int getCode()
/* 17:   */   {
/* 18:14 */     return 0;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void setManager(WiredManager manager)
/* 22:   */   {
/* 23:19 */     super.setManager(manager);
/* 24:20 */     this.wiredManager.triggersUserSays.add(this);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void removeManager()
/* 28:   */   {
/* 29:25 */     this.wiredManager.triggersUserSays.remove(this);
/* 30:26 */     super.removeManager();
/* 31:   */   }
/* 32:   */   
/* 33:   */   public boolean launch(Connection playerData, Object extra)
/* 34:   */   {
/* 35:31 */     String sExtra = (String)extra;
/* 36:33 */     if ((this.keyword != null) && 
/* 37:34 */       (!sExtra.contains(this.keyword))) {
/* 38:35 */       return false;
/* 39:   */     }
/* 40:39 */     return super.launch(playerData, null);
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void saveData()
/* 44:   */   {
/* 45:   */     try
/* 46:   */     {
/* 47:45 */       Database.exec("INSERT INTO trigger_item (trigger_id,trigger_data)VALUES(" + this.itemId + ",?) on DUPLICATE KEY UPDATE `trigger_data`=?;", new Object[] { getWiredData(), getWiredData() });
/* 48:46 */       super.saveData();
/* 49:   */     }
/* 50:   */     catch (Exception ex)
/* 51:   */     {
/* 52:48 */       Log.printException("ShowMessage-saveData", ex);
/* 53:   */     }
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void loadData(DBResult result)
/* 57:   */   {
/* 58:   */     try
/* 59:   */     {
/* 60:55 */       Database.query(result, "SELECT trigger_data FROM trigger_item WHERE trigger_id = " + this.itemId + ";", new Object[0]);
/* 61:56 */       if (result.data.next())
/* 62:   */       {
/* 63:57 */         this.keyword = result.data.getString("trigger_data");
/* 64:58 */         if (this.keyword.isEmpty()) {
/* 65:59 */           this.keyword = null;
/* 66:   */         }
/* 67:   */       }
/* 68:62 */       super.loadData(result);
/* 69:   */     }
/* 70:   */     catch (Exception ex)
/* 71:   */     {
/* 72:64 */       Log.printException("ShowMessage-loadData", ex);
/* 73:   */     }
/* 74:   */   }
/* 75:   */   
/* 76:   */   public void setWiredData(String data)
/* 77:   */   {
/* 78:70 */     if (!data.isEmpty()) {
/* 79:71 */       this.keyword = data;
/* 80:   */     } else {
/* 81:73 */       this.keyword = null;
/* 82:   */     }
/* 83:   */   }
/* 84:   */   
/* 85:   */   public String getWiredData()
/* 86:   */   {
/* 87:79 */     return this.keyword != null ? this.keyword : "";
/* 88:   */   }
/* 89:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.trigger.UserSaysPhraseTrigger
 * JD-Core Version:    0.7.0.1
 */