/*  1:   */ package cappo.engine.threadpools;
/*  2:   */ 
/*  3:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  4:   */ 
/*  5:   */ public abstract class ItemTask
/*  6:   */   extends GameTask
/*  7:   */ {
/*  8:   */   public GenericFloorItem item;
/*  9:   */   
/* 10:   */   public static void addTask(GameTask task, int initDelay, int repeatDelay)
/* 11:   */   {
/* 12:13 */     WorkerTasks.addTask(task, initDelay, repeatDelay, WorkerTasks.ItemsTasks);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public ItemTask(GenericFloorItem self)
/* 16:   */   {
/* 17:19 */     this.item = self;
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.threadpools.ItemTask
 * JD-Core Version:    0.7.0.1
 */