/*  1:   */ package cappo.game.roomengine.roomevents.wired;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.player.PlayerData;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.wired.effect.ShowMessageAction;
/*  7:   */ import cappo.game.roomengine.roomevents.Event;
/*  8:   */ 
/*  9:   */ public class ShowMessageEvent
/* 10:   */   extends Event
/* 11:   */ {
/* 12:   */   private final ShowMessageAction wired;
/* 13:   */   private final Connection invoker;
/* 14:   */   
/* 15:   */   public ShowMessageEvent(ShowMessageAction item, Connection ivk)
/* 16:   */   {
/* 17:13 */     this.wired = item;
/* 18:14 */     this.invoker = ivk;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public boolean equals(Object arg0)
/* 22:   */   {
/* 23:19 */     if (super.equals(arg0))
/* 24:   */     {
/* 25:20 */       ShowMessageEvent comp = (ShowMessageEvent)arg0;
/* 26:21 */       if (comp.invoker.playerData.userId == this.invoker.playerData.userId) {
/* 27:22 */         return true;
/* 28:   */       }
/* 29:   */     }
/* 30:26 */     return false;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void run(RoomTask room)
/* 34:   */   {
/* 35:31 */     ShowMessageAction.doEffect(this.wired, this.invoker);
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomevents.wired.ShowMessageEvent
 * JD-Core Version:    0.7.0.1
 */