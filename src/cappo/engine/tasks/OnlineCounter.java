/*  1:   */ package cappo.engine.tasks;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.Database;
/*  4:   */ import cappo.engine.logging.Log;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ 
/*  7:   */ public class OnlineCounter
/*  8:   */   implements Runnable
/*  9:   */ {
/* 10:   */   public void run()
/* 11:   */   {
/* 12:   */     try
/* 13:   */     {
/* 14:11 */       Database.exec("UPDATE `server_status` SET `users_online`='" + Clients.GetOnlineCount() + "';", new Object[0]);
/* 15:   */     }
/* 16:   */     catch (Exception ex)
/* 17:   */     {
/* 18:14 */       Log.printException("ServerTasks", ex);
/* 19:   */     }
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.tasks.OnlineCounter
 * JD-Core Version:    0.7.0.1
 */