/*  1:   */ package cappo.engine.tasks;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.Database;
/*  4:   */ import cappo.engine.logging.Log;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ import cappo.game.collections.Utils;
/*  7:   */ 
/*  8:   */ public class OnlineCounterGrapth
/*  9:   */   implements Runnable
/* 10:   */ {
/* 11:   */   private long unixMinutes;
/* 12:   */   
/* 13:   */   public void run()
/* 14:   */   {
/* 15:13 */     if (this.unixMinutes++ == 0L) {
/* 16:15 */       this.unixMinutes = (Utils.getTimestamp() / 60L);
/* 17:   */     }
/* 18:   */     try
/* 19:   */     {
/* 20:19 */       int online = Clients.GetOnlineCount();
/* 21:20 */       Database.exec("INSERT INTO stats_online (time,data)VALUES(" + this.unixMinutes + "," + online + ");", new Object[0]);
/* 22:   */     }
/* 23:   */     catch (Exception ex)
/* 24:   */     {
/* 25:23 */       Log.printException("ServerTasks", ex);
/* 26:   */     }
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.tasks.OnlineCounterGrapth
 * JD-Core Version:    0.7.0.1
 */