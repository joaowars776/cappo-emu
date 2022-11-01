/*  1:   */ package cappo.engine.threadpools;
/*  2:   */ 
/*  3:   */ import cappo.engine.logging.Log;
/*  4:   */ import cappo.game.games.snowwar.SnowPlayerQueue;
/*  5:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  6:   */ import cappo.game.games.snowwar.tasks.SnowArenaEnd;
/*  7:   */ import cappo.game.games.snowwar.tasks.SnowArenaRun;
/*  8:   */ import cappo.game.games.snowwar.tasks.SnowStageLoading;
/*  9:   */ import cappo.game.games.snowwar.tasks.SnowStageRun;
/* 10:   */ import cappo.game.games.snowwar.tasks.SnowStageStarting;
/* 11:   */ import java.util.concurrent.ScheduledFuture;
/* 12:   */ 
/* 13:   */ public class SnowWarTask
/* 14:   */   extends GameTask
/* 15:   */ {
/* 16:   */   public SnowWarRoom room;
/* 17:   */   
/* 18:   */   public static void addTask(GameTask task, int initDelay, int repeatDelay)
/* 19:   */   {
/* 20:21 */     WorkerTasks.addTask(task, initDelay, repeatDelay, WorkerTasks.SnowWarTasks);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public SnowWarTask(SnowWarRoom snowRoom)
/* 24:   */   {
/* 25:27 */     this.room = snowRoom;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void run()
/* 29:   */   {
/* 30:   */     try
/* 31:   */     {
/* 32:33 */       if (this.room.STATUS == 6)
/* 33:   */       {
/* 34:34 */         this.future.cancel(false);
/* 35:35 */         SnowArenaEnd.exec(this.room);
/* 36:36 */         return;
/* 37:   */       }
/* 38:39 */       if (this.room.STATUS == 5)
/* 39:   */       {
/* 40:40 */         SnowArenaRun.exec(this.room);
/* 41:41 */         return;
/* 42:   */       }
/* 43:44 */       if (this.room.STATUS == 4)
/* 44:   */       {
/* 45:45 */         SnowStageRun.exec(this.room);
/* 46:46 */         this.room.STATUS = 5;
/* 47:47 */         return;
/* 48:   */       }
/* 49:50 */       if (this.room.STATUS == 3)
/* 50:   */       {
/* 51:51 */         SnowStageStarting.exec(this.room);
/* 52:52 */         this.room.STATUS = 4;
/* 53:53 */         addTask(this, 6000, 150);
/* 54:54 */         return;
/* 55:   */       }
/* 56:57 */       if (this.room.STATUS == 2)
/* 57:   */       {
/* 58:58 */         SnowStageLoading.exec(this.room);
/* 59:60 */         if (this.room.STATUS == 3)
/* 60:   */         {
/* 61:61 */           this.future.cancel(false);
/* 62:62 */           addTask(this, 6000, 0);
/* 63:   */         }
/* 64:64 */         return;
/* 65:   */       }
/* 66:67 */       if (this.room.STATUS == 1)
/* 67:   */       {
/* 68:68 */         if (this.room.TimeToStart-- == 0)
/* 69:   */         {
/* 70:69 */           this.future.cancel(false);
/* 71:   */           
/* 72:71 */           SnowPlayerQueue.roomLoaded(this.room);
/* 73:72 */           this.room.STATUS = 2;
/* 74:73 */           addTask(this, 100, 200);
/* 75:   */         }
/* 76:75 */         return;
/* 77:   */       }
/* 78:   */     }
/* 79:   */     catch (Exception ex)
/* 80:   */     {
/* 81:79 */       this.future.cancel(false);
/* 82:80 */       Log.printException("SnowEngine", ex);
/* 83:   */     }
/* 84:   */   }
/* 85:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.threadpools.SnowWarTask
 * JD-Core Version:    0.7.0.1
 */