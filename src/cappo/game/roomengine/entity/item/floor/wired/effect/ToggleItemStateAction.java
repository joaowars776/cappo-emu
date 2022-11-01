/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired.effect;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.collections.BaseItem;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  7:   */ import cappo.game.roomengine.itemInteractor.Interactor;
/*  8:   */ import cappo.game.roomengine.roomevents.wired.ToggleItemStateEvent;
/*  9:   */ import java.util.Map;
/* 10:   */ 
/* 11:   */ public class ToggleItemStateAction
/* 12:   */   extends WiredEffectBase
/* 13:   */ {
/* 14:   */   public int getCode()
/* 15:   */   {
/* 16:10 */     return 8;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public boolean needUser()
/* 20:   */   {
/* 21:15 */     return false;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void invoke(Connection invoker)
/* 25:   */   {
/* 26:20 */     if (this.delayEffect > 0) {
/* 27:21 */       getRoom().addItemEvent(new ToggleItemStateEvent(this, invoker), this.delayEffect);
/* 28:   */     } else {
/* 29:23 */       doEffect(this, invoker);
/* 30:   */     }
/* 31:   */   }
/* 32:   */   
/* 33:   */   public static void doEffect(ToggleItemStateAction wired, Connection invoker)
/* 34:   */   {
/* 35:28 */     if (!wired.items.isEmpty()) {
/* 36:29 */       for (FloorItem item : wired.items.values()) {
/* 37:30 */         item.baseItem.interactor.OnTriggerFloor(item.getRoom(), invoker, item, 0, true);
/* 38:   */       }
/* 39:   */     }
/* 40:   */   }
/* 41:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.effect.ToggleItemStateAction
 * JD-Core Version:    0.7.0.1
 */