/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired.trigger;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.wired.WiredItemBase;
/*  5:   */ import cappo.game.roomengine.entity.item.floor.wired.condition.WiredConditionBase;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.wired.effect.WiredEffectBase;
/*  7:   */ import cappo.game.roomengine.wired.WiredManager;
/*  8:   */ import java.util.List;
/*  9:   */ import java.util.Map;
/* 10:   */ 
/* 11:   */ public abstract class WiredTriggerBase
/* 12:   */   extends WiredItemBase
/* 13:   */ {
/* 14:   */   public boolean needUser()
/* 15:   */   {
/* 16:15 */     return false;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public static boolean launchTriggers(List<WiredTriggerBase> triggers, Connection cn, Object extra)
/* 20:   */   {
/* 21:19 */     boolean launched = false;
/* 22:20 */     for (WiredTriggerBase trigger : triggers) {
/* 23:21 */       launched = (trigger.launch(cn, extra)) || (launched);
/* 24:   */     }
/* 25:23 */     return launched;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public boolean launch(Connection cn, Object extra)
/* 29:   */   {
/* 30:27 */     if ((this.wiredManager == null) || (getRoom() == null)) {
/* 31:28 */       return true;
/* 32:   */     }
/* 33:31 */     Map<Integer, WiredEffectBase> effects = (Map)this.wiredManager.wiredEffectsMap.get(Integer.valueOf(getXy()));
/* 34:32 */     if (effects == null) {
/* 35:33 */       return true;
/* 36:   */     }
/* 37:36 */     Map<Integer, WiredConditionBase> conditions = (Map)this.wiredManager.wiredConditionsMap.get(Integer.valueOf(getXy()));
/* 38:37 */     if (conditions != null) {
/* 39:38 */       for (WiredConditionBase condition : conditions.values()) {
/* 40:39 */         if (((cn != null) || (!condition.needUser())) && 
/* 41:40 */           (!condition.checkCondition(cn))) {
/* 42:41 */           return true;
/* 43:   */         }
/* 44:   */       }
/* 45:   */     }
/* 46:47 */     for (WiredEffectBase effect : effects.values()) {
/* 47:48 */       if ((cn != null) || (!effect.needUser())) {
/* 48:49 */         effect.invoke(cn);
/* 49:   */       }
/* 50:   */     }
/* 51:53 */     return true;
/* 52:   */   }
/* 53:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.trigger.WiredTriggerBase
 * JD-Core Version:    0.7.0.1
 */