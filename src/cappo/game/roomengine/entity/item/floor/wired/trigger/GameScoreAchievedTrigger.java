/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired.trigger;
/*  2:   */ 
/*  3:   */ import cappo.game.roomengine.wired.WiredManager;
/*  4:   */ import java.util.List;
/*  5:   */ 
/*  6:   */ public class GameScoreAchievedTrigger
/*  7:   */   extends WiredTriggerBase
/*  8:   */ {
/*  9:   */   public int getCode()
/* 10:   */   {
/* 11: 8 */     return 10;
/* 12:   */   }
/* 13:   */   
/* 14:   */   public void setManager(WiredManager manager)
/* 15:   */   {
/* 16:13 */     super.setManager(manager);
/* 17:14 */     this.wiredManager.triggersGameStarts.add(this);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void removeManager()
/* 21:   */   {
/* 22:19 */     this.wiredManager.triggersGameStarts.remove(this);
/* 23:20 */     super.removeManager();
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.trigger.GameScoreAchievedTrigger
 * JD-Core Version:    0.7.0.1
 */