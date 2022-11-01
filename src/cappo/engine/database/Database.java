/*  1:   */ package cappo.engine.database;
/*  2:   */ 
/*  3:   */ import cappo.engine.Server;
/*  4:   */ import cappo.engine.logging.Log;
/*  5:   */ import cappo.engine.threadpools.DatabaseExecTask;
/*  6:   */ import java.sql.Connection;
/*  7:   */ import java.sql.PreparedStatement;
/*  8:   */ import java.sql.ResultSet;
/*  9:   */ import org.apache.tomcat.jdbc.pool.DataSource;
/* 10:   */ import org.apache.tomcat.jdbc.pool.PoolProperties;
/* 11:   */ 
/* 12:   */ public class Database
/* 13:   */ {
/* 14:   */   private static DataSource datasource;
/* 15:   */   
/* 16:   */   public static Connection getNew()
/* 17:   */   {
/* 18:   */     try
/* 19:   */     {
/* 20:24 */       return datasource.getConnection();
/* 21:   */     }
/* 22:   */     catch (Exception ex)
/* 23:   */     {
/* 24:26 */       Log.printException("Database-1", ex);
/* 25:   */     }
/* 26:28 */     return null;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public static void parseValues(PreparedStatement pst, Object... Values)
/* 30:   */     throws Exception
/* 31:   */   {
/* 32:32 */     int i = 0;
/* 33:33 */     for (Object val : Values) {
/* 34:34 */       if ((val instanceof String)) {
/* 35:35 */         pst.setString(++i, (String)val);
/* 36:36 */       } else if ((val instanceof byte[])) {
/* 37:37 */         pst.setBytes(++i, (byte[])val);
/* 38:   */       } else {
/* 39:39 */         throw new Exception("Unsoported value!" + val.getClass().getSimpleName());
/* 40:   */       }
/* 41:   */     }
/* 42:   */   }
/* 43:   */   
/* 44:   */   public static void query(DBResult Result, String query, Object... Values)
/* 45:   */     throws Exception
/* 46:   */   {
/* 47:45 */     if (Result.data != null)
/* 48:   */     {
/* 49:46 */       Result.data.close();
/* 50:47 */       Result.pst.close();
/* 51:   */     }
/* 52:49 */     Result.pst = Result.cn.prepareStatement(query);
/* 53:50 */     parseValues(Result.pst, Values);
/* 54:51 */     Result.data = Result.pst.executeQuery();
/* 55:   */   }
/* 56:   */   
/* 57:   */   public static void exec(String query, Object... params)
/* 58:   */   {
/* 59:55 */     if (Server.blockMysql) {
/* 60:56 */       return;
/* 61:   */     }
/* 62:59 */     DatabaseExecTask.addTask(new DatabaseExecTask(query, params), 0, 0);
/* 63:   */   }
/* 64:   */   
/* 65:   */   public static void Init(String host, String port, String db, String user, String pass)
/* 66:   */     throws Exception
/* 67:   */   {
/* 68:63 */     PoolProperties p = new PoolProperties();
/* 69:   */     
/* 70:65 */     p.setUrl("jdbc:mysql://" + host + ":" + port + "/" + db);
/* 71:66 */     p.setDriverClassName("com.mysql.jdbc.Driver");
/* 72:67 */     p.setUsername(user);
/* 73:68 */     p.setPassword(pass);
/* 74:   */     
/* 75:70 */     p.setInitSQL("SET SESSION interactive_timeout=360,wait_timeout=360,join_buffer_size=120000000,sort_buffer_size=20000000,read_rnd_buffer_size=20000000;");
/* 76:   */     
/* 77:72 */     p.setJmxEnabled(false);
/* 78:   */     
/* 79:74 */     p.setValidationQuery("SELECT 1");
/* 80:75 */     p.setValidationInterval(10000L);
/* 81:76 */     p.setTestWhileIdle(true);
/* 82:77 */     p.setTestOnBorrow(false);
/* 83:78 */     p.setTestOnReturn(false);
/* 84:   */     
/* 85:80 */     p.setTimeBetweenEvictionRunsMillis(5000);
/* 86:81 */     p.setMinEvictableIdleTimeMillis(5000);
/* 87:   */     
/* 88:83 */     p.setRemoveAbandoned(true);
/* 89:84 */     p.setRemoveAbandonedTimeout(5);
/* 90:85 */     p.setLogAbandoned(true);
/* 91:   */     
/* 92:87 */     p.setInitialSize(5);
/* 93:88 */     p.setMinIdle(3);
/* 94:89 */     p.setMaxIdle(6);
/* 95:90 */     p.setMaxActive(25);
/* 96:91 */     p.setMaxWait(2000);
/* 97:   */     
/* 98:93 */     datasource = new DataSource();
/* 99:94 */     datasource.setPoolProperties(p);
/* :0:   */   }
/* :1:   */   
/* :2:   */   public static void close()
/* :3:   */   {
/* :4:98 */     datasource.close();
/* :5:   */   }
/* :6:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.database.Database
 * JD-Core Version:    0.7.0.1
 */