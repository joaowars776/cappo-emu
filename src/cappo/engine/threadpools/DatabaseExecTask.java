/*  1:   */ package cappo.engine.threadpools;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.Database;
/*  4:   */ import cappo.engine.logging.Log;
/*  5:   */ import java.sql.Connection;
/*  6:   */ import java.sql.PreparedStatement;
/*  7:   */ 
/*  8:   */ public class DatabaseExecTask
/*  9:   */   extends GameTask
/* 10:   */ {
/* 11:   */   public final Object[] values;
/* 12:   */   public final String query;
/* 13:   */   
/* 14:   */   public static void addTask(GameTask task, int initDelay, int repeatRate)
/* 15:   */   {
/* 16:17 */     WorkerTasks.addTask(task, initDelay, repeatRate, WorkerTasks.DatabaseExecTasks);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public DatabaseExecTask(String q, Object... v)
/* 20:   */   {
/* 21:24 */     this.query = q;
/* 22:25 */     this.values = v;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void run()
/* 26:   */   {
/* 27:30 */     PreparedStatement pst = null;
/* 28:31 */     Connection cn = null;
/* 29:   */     try
/* 30:   */     {
/* 31:33 */       cn = Database.getNew();
/* 32:34 */       pst = cn.prepareStatement(this.query);
/* 33:35 */       Database.parseValues(pst, this.values);
/* 34:36 */       pst.execute();
/* 35:   */     }
/* 36:   */     catch (Exception ex)
/* 37:   */     {
/* 38:39 */       Log.printException("QueryExec (" + this.query + ")", ex);
/* 39:   */     }
/* 40:42 */     if (pst != null) {
/* 41:   */       try
/* 42:   */       {
/* 43:44 */         pst.close();
/* 44:   */       }
/* 45:   */       catch (Exception localException1) {}
/* 46:   */     }
/* 47:48 */     if (cn != null) {
/* 48:   */       try
/* 49:   */       {
/* 50:50 */         cn.close();
/* 51:   */       }
/* 52:   */       catch (Exception localException2) {}
/* 53:   */     }
/* 54:   */   }
/* 55:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.threadpools.DatabaseExecTask
 * JD-Core Version:    0.7.0.1
 */