/*  1:   */ package cappo.game.roomengine.roomevents.wired;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.player.PlayerData;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.wired.effect.MoveRotateItemAction;
/*  7:   */ import cappo.game.roomengine.roomevents.Event;
/*  8:   */ 
/*  9:   */ public class MoveRotateEvent
/* 10:   */   extends Event
/* 11:   */ {
/* 12:   */   private final MoveRotateItemAction wired;
/* 13:   */   private final Connection invoker;
/* 14:   */   
/* 15:   */   public MoveRotateEvent(MoveRotateItemAction item, Connection ivk)
/* 16:   */   {
/* 17:13 */     this.wired = item;
/* 18:14 */     this.invoker = ivk;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public boolean equals(Object arg0)
/* 22:   */   {
/* 23:19 */     if (super.equals(arg0))
/* 24:   */     {
/* 25:20 */       MoveRotateEvent comp = (MoveRotateEvent)arg0;
/* 26:21 */       if (this.invoker == null) {
/* 27:22 */         return comp.invoker == null;
/* 28:   */       }
/* 29:24 */       return (comp.invoker != null) && (comp.invoker.playerData.userId == this.invoker.playerData.userId);
/* 30:   */     }
/* 31:28 */     return false;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void run(RoomTask room)
/* 35:   */   {
/* 36:33 */     MoveRotateItemAction.doEffect(this.wired);
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomevents.wired.MoveRotateEvent
 * JD-Core Version:    0.7.0.1
 */