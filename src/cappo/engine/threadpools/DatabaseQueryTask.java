/*  1:   */ package cappo.engine.threadpools;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.Database;
/*  4:   */ import cappo.engine.logging.Log;
/*  5:   */ import java.lang.reflect.Method;
/*  6:   */ import java.sql.Connection;
/*  7:   */ import java.sql.PreparedStatement;
/*  8:   */ import java.sql.ResultSet;
/*  9:   */ import java.util.ArrayList;
/* 10:   */ import java.util.List;
/* 11:   */ 
/* 12:   */ public class DatabaseQueryTask
/* 13:   */   extends GameTask
/* 14:   */ {
/* 15:   */   private List<Query> queries;
/* 16:   */   
/* 17:   */   public static void addTask(DatabaseQueryTask task, int initDelay, int repeatRate)
/* 18:   */   {
/* 19:21 */     WorkerTasks.addTask(task, initDelay, repeatRate, WorkerTasks.DatabaseQueryTasks);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public DatabaseQueryTask(int initialQuerySize)
/* 23:   */   {
/* 24:26 */     this.queries = new ArrayList(initialQuerySize);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public DatabaseQueryTask(String query, Method callback, Object extra, Object... params)
/* 28:   */   {
/* 29:30 */     this(1);
/* 30:   */     
/* 31:32 */     addQuery(query, callback, extra, params);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void addQuery(String query, Method callback, Object extra, Object... params)
/* 35:   */   {
/* 36:36 */     this.queries.add(new Query(query, callback, extra, params));
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void run()
/* 40:   */   {
/* 41:41 */     Connection cn = Database.getNew();
/* 42:42 */     if (cn == null) {
/* 43:43 */       return;
/* 44:   */     }
/* 45:46 */     for (Query q : this.queries)
/* 46:   */     {
/* 47:47 */       PreparedStatement pst = null;
/* 48:48 */       boolean callBackOk = false;
/* 49:   */       try
/* 50:   */       {
/* 51:51 */         pst = cn.prepareStatement(q.query);
/* 52:52 */         Database.parseValues(pst, q.values);
/* 53:53 */         ResultSet result = pst.executeQuery();
/* 54:54 */         callBackOk = ((Boolean)q.callBack.invoke(null, new Object[] { result, q.extra })).booleanValue();
/* 55:   */       }
/* 56:   */       catch (Exception ex)
/* 57:   */       {
/* 58:57 */         Log.printException("QueryExec (" + q.query + ")", ex);
/* 59:   */       }
/* 60:60 */       if (pst != null) {
/* 61:   */         try
/* 62:   */         {
/* 63:62 */           pst.close();
/* 64:   */         }
/* 65:   */         catch (Exception localException1) {}
/* 66:   */       }
/* 67:66 */       if (!callBackOk) {
/* 68:   */         break;
/* 69:   */       }
/* 70:   */     }
/* 71:71 */     if (cn != null) {
/* 72:   */       try
/* 73:   */       {
/* 74:73 */         cn.close();
/* 75:   */       }
/* 76:   */       catch (Exception localException2) {}
/* 77:   */     }
/* 78:   */   }
/* 79:   */   
/* 80:   */   private class Query
/* 81:   */   {
/* 82:   */     public final Method callBack;
/* 83:   */     public final String query;
/* 84:   */     public final Object[] values;
/* 85:   */     public final Object extra;
/* 86:   */     
/* 87:   */     public Query(String q, Method call, Object e, Object... v)
/* 88:   */     {
/* 89:85 */       this.query = q;
/* 90:86 */       this.values = v;
/* 91:87 */       this.callBack = call;
/* 92:88 */       this.extra = e;
/* 93:   */     }
/* 94:   */   }
/* 95:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.threadpools.DatabaseQueryTask
 * JD-Core Version:    0.7.0.1
 */