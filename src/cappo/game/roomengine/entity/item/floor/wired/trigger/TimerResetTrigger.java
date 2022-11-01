/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired.trigger;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import cappo.engine.logging.Log;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.engine.threadpools.RoomTask;
/*  8:   */ import cappo.game.roomengine.roomevents.wired.TimerSetTimeEvent;
/*  9:   */ import cappo.game.roomengine.wired.WiredManager;
/* 10:   */ import java.sql.ResultSet;
/* 11:   */ import java.util.List;
/* 12:   */ 
/* 13:   */ public class TimerResetTrigger
/* 14:   */   extends WiredTriggerBase
/* 15:   */ {
/* 16:   */   public TimerSetTimeEvent event;
/* 17:   */   public int delay;
/* 18:   */   
/* 19:   */   public int getCode()
/* 20:   */   {
/* 21:16 */     return 3;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void setManager(WiredManager manager)
/* 25:   */   {
/* 26:21 */     super.setManager(manager);
/* 27:22 */     this.wiredManager.triggersTimers.add(this);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void removeManager()
/* 31:   */   {
/* 32:27 */     this.wiredManager.triggersTimers.remove(this);
/* 33:28 */     super.removeManager();
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void setWiredOption(int index, int option)
/* 37:   */   {
/* 38:33 */     if (index == 0) {
/* 39:34 */       this.delay = option;
/* 40:   */     }
/* 41:   */   }
/* 42:   */   
/* 43:   */   public int[] getWiredOptions()
/* 44:   */   {
/* 45:40 */     return new int[] { this.delay };
/* 46:   */   }
/* 47:   */   
/* 48:   */   public static void doTrigger(TimerResetTrigger wired, Connection invoker)
/* 49:   */   {
/* 50:44 */     wired.launch(invoker, Boolean.valueOf(true));
/* 51:   */   }
/* 52:   */   
/* 53:   */   public boolean launch(Connection cn, Object extra)
/* 54:   */   {
/* 55:49 */     if (extra != null) {
/* 56:50 */       super.launch(cn, null);
/* 57:52 */     } else if ((this.delay > 0) && (this.event == null)) {
/* 58:53 */       getRoom().addItemEvent(this.event = new TimerSetTimeEvent(this, cn), this.delay);
/* 59:   */     }
/* 60:56 */     return true;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public void saveData()
/* 64:   */   {
/* 65:   */     try
/* 66:   */     {
/* 67:62 */       Database.exec("INSERT INTO trigger_item (trigger_id,trigger_data)VALUES(" + this.itemId + ",'" + this.delay + "') on DUPLICATE KEY UPDATE `trigger_data`='" + this.delay + "';", new Object[0]);
/* 68:63 */       super.saveData();
/* 69:   */     }
/* 70:   */     catch (Exception ex)
/* 71:   */     {
/* 72:65 */       Log.printException("ShowMessage-saveData", ex);
/* 73:   */     }
/* 74:   */   }
/* 75:   */   
/* 76:   */   public void loadData(DBResult result)
/* 77:   */   {
/* 78:   */     try
/* 79:   */     {
/* 80:72 */       Database.query(result, "SELECT trigger_data FROM trigger_item WHERE trigger_id = " + this.itemId + ";", new Object[0]);
/* 81:73 */       if (result.data.next()) {
/* 82:74 */         this.delay = result.data.getInt("trigger_data");
/* 83:   */       }
/* 84:76 */       super.loadData(result);
/* 85:   */     }
/* 86:   */     catch (Exception ex)
/* 87:   */     {
/* 88:78 */       Log.printException("ShowMessage-loadData", ex);
/* 89:   */     }
/* 90:   */   }
/* 91:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.trigger.TimerResetTrigger
 * JD-Core Version:    0.7.0.1
 */