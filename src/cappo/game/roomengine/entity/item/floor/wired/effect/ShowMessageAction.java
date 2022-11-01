/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired.effect;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import cappo.engine.logging.Log;
/*  6:   */ import cappo.engine.network.QueueWriter;
/*  7:   */ import cappo.engine.player.Connection;
/*  8:   */ import cappo.engine.threadpools.RoomTask;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.game.roomengine.roomevents.wired.ShowMessageEvent;
/* 11:   */ import cappo.protocol.messages.composers.room.chat.WhisperComposer;
/* 12:   */ import java.sql.ResultSet;
/* 13:   */ import java.util.ArrayList;
/* 14:   */ 
/* 15:   */ public class ShowMessageAction
/* 16:   */   extends WiredEffectBase
/* 17:   */ {
/* 18:   */   public String message;
/* 19:   */   
/* 20:   */   public int getCode()
/* 21:   */   {
/* 22:18 */     return 7;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public boolean needUser()
/* 26:   */   {
/* 27:23 */     return true;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void invoke(Connection invoker)
/* 31:   */   {
/* 32:28 */     if (this.delayEffect > 0) {
/* 33:29 */       getRoom().addItemEvent(new ShowMessageEvent(this, invoker), this.delayEffect);
/* 34:   */     } else {
/* 35:31 */       doEffect(this, invoker);
/* 36:   */     }
/* 37:   */   }
/* 38:   */   
/* 39:   */   public static void doEffect(ShowMessageAction wired, Connection invoker)
/* 40:   */   {
/* 41:36 */     if (wired.message != null) {
/* 42:37 */       QueueWriter.writeAndFlush(invoker.socket, WhisperComposer.compose(invoker.avatar.virtualId, wired.message, 0, 0, new ArrayList(), 0));
/* 43:   */     }
/* 44:   */   }
/* 45:   */   
/* 46:   */   public void saveData()
/* 47:   */   {
/* 48:   */     try
/* 49:   */     {
/* 50:44 */       Database.exec("INSERT INTO trigger_item (trigger_id,trigger_data)VALUES(" + this.itemId + ",?) on DUPLICATE KEY UPDATE `trigger_data`=?;", new Object[] { getWiredData(), getWiredData() });
/* 51:45 */       super.saveData();
/* 52:   */     }
/* 53:   */     catch (Exception ex)
/* 54:   */     {
/* 55:47 */       Log.printException("ShowMessage-saveData", ex);
/* 56:   */     }
/* 57:   */   }
/* 58:   */   
/* 59:   */   public void loadData(DBResult result)
/* 60:   */   {
/* 61:   */     try
/* 62:   */     {
/* 63:54 */       Database.query(result, "SELECT trigger_data FROM trigger_item WHERE trigger_id = " + this.itemId + ";", new Object[0]);
/* 64:55 */       if (result.data.next())
/* 65:   */       {
/* 66:56 */         this.message = result.data.getString("trigger_data");
/* 67:57 */         if (this.message.isEmpty()) {
/* 68:58 */           this.message = null;
/* 69:   */         }
/* 70:   */       }
/* 71:61 */       super.loadData(result);
/* 72:   */     }
/* 73:   */     catch (Exception ex)
/* 74:   */     {
/* 75:63 */       Log.printException("ShowMessage-loadData", ex);
/* 76:   */     }
/* 77:   */   }
/* 78:   */   
/* 79:   */   public void setWiredData(String data)
/* 80:   */   {
/* 81:69 */     this.message = data;
/* 82:   */   }
/* 83:   */   
/* 84:   */   public String getWiredData()
/* 85:   */   {
/* 86:74 */     return this.message != null ? this.message : "";
/* 87:   */   }
/* 88:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.effect.ShowMessageAction
 * JD-Core Version:    0.7.0.1
 */