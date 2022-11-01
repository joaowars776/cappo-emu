/*  1:   */ package cappo.engine.logging;
/*  2:   */ 
/*  3:   */ import cappo.engine.Server;
/*  4:   */ import java.io.FileOutputStream;
/*  5:   */ import java.io.OutputStream;
/*  6:   */ import java.io.PrintStream;
/*  7:   */ 
/*  8:   */ public class Log
/*  9:   */   extends PrintStream
/* 10:   */ {
/* 11:   */   private static PrintStream out;
/* 12:   */   private static PrintStream outExceptions;
/* 13:   */   private static String lastLog;
/* 14:   */   
/* 15:   */   public Log(OutputStream f)
/* 16:   */   {
/* 17:17 */     super(f);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public static void Init(boolean dev, String Date)
/* 21:   */   {
/* 22:27 */     if (!dev) {
/* 23:   */       try
/* 24:   */       {
/* 25:29 */         System.setOut(new Log(new FileOutputStream("./" + Server.serverId + "/log-" + Date.replace(":", "-") + ".txt", true)));
/* 26:30 */         System.setErr(new Log(new FileOutputStream("./" + Server.serverId + "/log-" + Date.replace(":", "-") + "-Exceptions.txt", true)));
/* 27:   */       }
/* 28:   */       catch (Exception ex)
/* 29:   */       {
/* 30:32 */         ex.printStackTrace(System.err);
/* 31:   */       }
/* 32:   */     }
/* 33:36 */     outExceptions = System.err;
/* 34:37 */     out = System.out;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public static void printException(String Caller, Exception ex)
/* 38:   */   {
/* 39:41 */     outExceptions.print(System.currentTimeMillis() + ": ");
/* 40:42 */     outExceptions.println("New Exception in " + Caller);
/* 41:43 */     ex.printStackTrace(outExceptions);
/* 42:44 */     outExceptions.flush();
/* 43:   */   }
/* 44:   */   
/* 45:   */   public static void printThrowable(String Caller, Throwable ex)
/* 46:   */   {
/* 47:48 */     outExceptions.print(System.currentTimeMillis() + ": ");
/* 48:49 */     outExceptions.println("New Exception in " + Caller);
/* 49:50 */     ex.printStackTrace(outExceptions);
/* 50:51 */     outExceptions.flush();
/* 51:   */   }
/* 52:   */   
/* 53:   */   public static void printLog(String ex)
/* 54:   */   {
/* 55:57 */     if ((lastLog != null) && (lastLog.equals(ex))) {
/* 56:58 */       return;
/* 57:   */     }
/* 58:60 */     lastLog = ex;
/* 59:   */     
/* 60:62 */     out.print(System.currentTimeMillis() + ": ");
/* 61:63 */     out.println(ex);
/* 62:64 */     out.flush();
/* 63:   */   }
/* 64:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.logging.Log
 * JD-Core Version:    0.7.0.1
 */