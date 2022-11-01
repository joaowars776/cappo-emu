/*  1:   */ package cappo.engine.threadpools;
/*  2:   */ 
/*  3:   */ import java.util.concurrent.ScheduledThreadPoolExecutor;
/*  4:   */ import java.util.concurrent.TimeUnit;
/*  5:   */ 
/*  6:   */ public class WorkerTasks
/*  7:   */ {
/*  8:   */   public static int serverType;
/*  9:   */   public static final int SERVER_TINY = 0;
/* 10:   */   public static final int SERVER_SMALL = 1;
/* 11:   */   public static final int SERVER_NORMAL = 2;
/* 12:   */   public static final int SERVER_LARGE = 3;
/* 13:   */   public static final int SERVER_EXTRALARGE = 4;
/* 14:   */   public static final int SERVER_TURBO = 5;
/* 15:   */   public static ScheduledThreadPoolExecutor ItemsTasks;
/* 16:   */   public static ScheduledThreadPoolExecutor RoomsTasks;
/* 17:   */   public static ScheduledThreadPoolExecutor SnowWarTasks;
/* 18:   */   public static ScheduledThreadPoolExecutor DatabaseExecTasks;
/* 19:   */   public static ScheduledThreadPoolExecutor DatabaseQueryTasks;
/* 20:   */   
/* 21:   */   public static void initWorkers(int type)
/* 22:   */   {
/* 23:29 */     serverType = type;
/* 24:31 */     if (serverType == 0)
/* 25:   */     {
/* 26:34 */       RoomsTasks = new ScheduledThreadPoolExecutor(1);
/* 27:35 */       SnowWarTasks = new ScheduledThreadPoolExecutor(1);
/* 28:36 */       ItemsTasks = new ScheduledThreadPoolExecutor(1);
/* 29:37 */       DatabaseExecTasks = new ScheduledThreadPoolExecutor(1);
/* 30:38 */       DatabaseQueryTasks = new ScheduledThreadPoolExecutor(1);
/* 31:   */     }
/* 32:39 */     else if (serverType == 1)
/* 33:   */     {
/* 34:42 */       RoomsTasks = new ScheduledThreadPoolExecutor(2);
/* 35:43 */       SnowWarTasks = new ScheduledThreadPoolExecutor(1);
/* 36:44 */       ItemsTasks = new ScheduledThreadPoolExecutor(1);
/* 37:45 */       DatabaseExecTasks = new ScheduledThreadPoolExecutor(1);
/* 38:46 */       DatabaseQueryTasks = new ScheduledThreadPoolExecutor(1);
/* 39:   */     }
/* 40:47 */     else if (serverType == 2)
/* 41:   */     {
/* 42:50 */       RoomsTasks = new ScheduledThreadPoolExecutor(4);
/* 43:51 */       SnowWarTasks = new ScheduledThreadPoolExecutor(2);
/* 44:52 */       ItemsTasks = new ScheduledThreadPoolExecutor(1);
/* 45:53 */       DatabaseExecTasks = new ScheduledThreadPoolExecutor(2);
/* 46:54 */       DatabaseQueryTasks = new ScheduledThreadPoolExecutor(2);
/* 47:   */     }
/* 48:55 */     else if (serverType == 3)
/* 49:   */     {
/* 50:58 */       RoomsTasks = new ScheduledThreadPoolExecutor(8);
/* 51:59 */       SnowWarTasks = new ScheduledThreadPoolExecutor(3);
/* 52:60 */       ItemsTasks = new ScheduledThreadPoolExecutor(2);
/* 53:61 */       DatabaseExecTasks = new ScheduledThreadPoolExecutor(4);
/* 54:62 */       DatabaseQueryTasks = new ScheduledThreadPoolExecutor(3);
/* 55:   */     }
/* 56:   */     else
/* 57:   */     {
/* 58:66 */       RoomsTasks = new ScheduledThreadPoolExecutor(10);
/* 59:67 */       SnowWarTasks = new ScheduledThreadPoolExecutor(3);
/* 60:68 */       ItemsTasks = new ScheduledThreadPoolExecutor(2);
/* 61:69 */       DatabaseExecTasks = new ScheduledThreadPoolExecutor(6);
/* 62:70 */       DatabaseQueryTasks = new ScheduledThreadPoolExecutor(4);
/* 63:   */     }
/* 64:   */   }
/* 65:   */   
/* 66:   */   public static void addTask(GameTask task, int initDelay, int repeatRate, ScheduledThreadPoolExecutor worker)
/* 67:   */   {
/* 68:75 */     if (repeatRate > 0) {
/* 69:76 */       task.future = worker.scheduleAtFixedRate(task, initDelay, repeatRate, TimeUnit.MILLISECONDS);
/* 70:   */     } else {
/* 71:78 */       task.future = worker.schedule(task, initDelay, TimeUnit.MILLISECONDS);
/* 72:   */     }
/* 73:   */   }
/* 74:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.threadpools.WorkerTasks
 * JD-Core Version:    0.7.0.1
 */