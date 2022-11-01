/*   1:    */ package cappo.game.roomengine.wired;
/*   2:    */ 
/*   3:    */ import cappo.engine.player.Connection;
/*   4:    */ import cappo.game.collections.BaseItem.ItemType;
/*   5:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*   6:    */ import cappo.game.roomengine.entity.item.floor.wired.WiredItemBase;
/*   7:    */ import cappo.game.roomengine.entity.item.floor.wired.condition.WiredConditionBase;
/*   8:    */ import cappo.game.roomengine.entity.item.floor.wired.effect.WiredEffectBase;
/*   9:    */ import cappo.game.roomengine.entity.item.floor.wired.trigger.WiredTriggerBase;
/*  10:    */ import java.util.ArrayList;
/*  11:    */ import java.util.List;
/*  12:    */ import java.util.Map;
/*  13:    */ import java.util.concurrent.ConcurrentHashMap;
/*  14:    */ 
/*  15:    */ public class WiredManager
/*  16:    */ {
/*  17:    */   public static final int MAX_CHILDS_PER_WIRED = 5;
/*  18:    */   private static final int MAX_WIREDS_PER_ROOM = 100;
/*  19:    */   private int count;
/*  20: 29 */   public final List<WiredTriggerBase> triggersEntersRoom = new ArrayList();
/*  21: 30 */   public final List<WiredTriggerBase> triggersTimers = new ArrayList();
/*  22: 31 */   public final List<WiredTriggerBase> triggersGameEnds = new ArrayList();
/*  23: 32 */   public final List<WiredTriggerBase> triggersGameStarts = new ArrayList();
/*  24: 33 */   public final List<WiredTriggerBase> triggersUserSays = new ArrayList();
/*  25: 34 */   public final Map<Integer, List<WiredTriggerBase>> triggersSateChanged = new ConcurrentHashMap();
/*  26: 35 */   public final Map<Integer, List<WiredTriggerBase>> triggersWalksOffFurni = new ConcurrentHashMap();
/*  27: 36 */   public final Map<Integer, List<WiredTriggerBase>> triggersWalksOnFurni = new ConcurrentHashMap();
/*  28: 39 */   public final Map<Integer, Map<Integer, WiredConditionBase>> wiredConditionsMap = new ConcurrentHashMap();
/*  29: 40 */   public final Map<Integer, Map<Integer, WiredEffectBase>> wiredEffectsMap = new ConcurrentHashMap();
/*  30:    */   
/*  31:    */   public void parseWiredMutacion(Connection User, FloorItem item)
/*  32:    */   {
/*  33: 43 */     List<WiredTriggerBase> triggerList = (List)this.triggersSateChanged.get(Integer.valueOf(item.itemId));
/*  34: 44 */     if (triggerList != null) {
/*  35: 45 */       WiredTriggerBase.launchTriggers(triggerList, User, null);
/*  36:    */     }
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void parseWiredWalksOffFurni(Connection User, int xy)
/*  40:    */   {
/*  41: 50 */     List<WiredTriggerBase> triggerList = (List)this.triggersWalksOffFurni.get(Integer.valueOf(xy));
/*  42: 51 */     if (triggerList != null) {
/*  43: 52 */       WiredTriggerBase.launchTriggers(triggerList, User, null);
/*  44:    */     }
/*  45:    */   }
/*  46:    */   
/*  47:    */   public void parseWiredWalksOnFurni(Connection User, int xy)
/*  48:    */   {
/*  49: 57 */     List<WiredTriggerBase> triggerList = (List)this.triggersWalksOnFurni.get(Integer.valueOf(xy));
/*  50: 58 */     if (triggerList != null) {
/*  51: 59 */       WiredTriggerBase.launchTriggers(triggerList, User, null);
/*  52:    */     }
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void registerWired(WiredItemBase item, BaseItem.ItemType type)
/*  56:    */   {
/*  57: 64 */     if (this.count >= 100) {
/*  58: 65 */       return;
/*  59:    */     }
/*  60: 68 */     if (type == BaseItem.ItemType.WIRED_EFFECT)
/*  61:    */     {
/*  62: 69 */       Map<Integer, WiredEffectBase> effects = (Map)this.wiredEffectsMap.get(Integer.valueOf(item.getXy()));
/*  63: 70 */       if (effects == null)
/*  64:    */       {
/*  65: 71 */         effects = new ConcurrentHashMap();
/*  66: 72 */         this.wiredEffectsMap.put(Integer.valueOf(item.getXy()), effects);
/*  67:    */       }
/*  68: 75 */       effects.put(Integer.valueOf(item.itemId), (WiredEffectBase)item);
/*  69:    */     }
/*  70: 76 */     else if (type == BaseItem.ItemType.WIRED_CONDITION)
/*  71:    */     {
/*  72: 77 */       Map<Integer, WiredConditionBase> conditions = (Map)this.wiredConditionsMap.get(Integer.valueOf(item.getXy()));
/*  73: 78 */       if (conditions == null)
/*  74:    */       {
/*  75: 79 */         conditions = new ConcurrentHashMap();
/*  76: 80 */         this.wiredConditionsMap.put(Integer.valueOf(item.getXy()), conditions);
/*  77:    */       }
/*  78: 83 */       conditions.put(Integer.valueOf(item.itemId), (WiredConditionBase)item);
/*  79:    */     }
/*  80: 86 */     item.setManager(this);
/*  81:    */     
/*  82: 88 */     this.count += 1;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void removeWired(WiredItemBase item, BaseItem.ItemType type, int prevXY)
/*  86:    */   {
/*  87: 92 */     item.removeAllItems();
/*  88: 94 */     if (type == BaseItem.ItemType.WIRED_EFFECT)
/*  89:    */     {
/*  90: 95 */       Map<Integer, WiredEffectBase> effects = (Map)this.wiredEffectsMap.get(Integer.valueOf(prevXY));
/*  91: 96 */       effects.remove(Integer.valueOf(item.itemId));
/*  92: 97 */       if (effects.isEmpty()) {
/*  93: 98 */         this.wiredEffectsMap.remove(Integer.valueOf(item.getXy()));
/*  94:    */       }
/*  95:    */     }
/*  96:100 */     else if (type == BaseItem.ItemType.WIRED_CONDITION)
/*  97:    */     {
/*  98:101 */       Map<Integer, WiredConditionBase> conditions = (Map)this.wiredConditionsMap.get(Integer.valueOf(prevXY));
/*  99:102 */       conditions.remove(Integer.valueOf(item.itemId));
/* 100:103 */       if (conditions.isEmpty()) {
/* 101:104 */         this.wiredConditionsMap.remove(Integer.valueOf(item.getXy()));
/* 102:    */       }
/* 103:    */     }
/* 104:108 */     if (item.wiredManager != null) {
/* 105:109 */       item.removeManager();
/* 106:    */     }
/* 107:112 */     this.count -= 1;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void moveWired(WiredItemBase item, BaseItem.ItemType type, int prevXY)
/* 111:    */   {
/* 112:118 */     if (type == BaseItem.ItemType.WIRED_EFFECT)
/* 113:    */     {
/* 114:119 */       Map<Integer, WiredEffectBase> effects = (Map)this.wiredEffectsMap.get(Integer.valueOf(prevXY));
/* 115:120 */       effects.remove(Integer.valueOf(item.itemId));
/* 116:121 */       if (effects.isEmpty()) {
/* 117:122 */         this.wiredEffectsMap.remove(Integer.valueOf(prevXY));
/* 118:    */       }
/* 119:125 */       effects = (Map)this.wiredEffectsMap.get(Integer.valueOf(item.getXy()));
/* 120:126 */       if (effects == null)
/* 121:    */       {
/* 122:127 */         effects = new ConcurrentHashMap();
/* 123:128 */         this.wiredEffectsMap.put(Integer.valueOf(item.getXy()), effects);
/* 124:    */       }
/* 125:131 */       effects.put(Integer.valueOf(item.itemId), (WiredEffectBase)item);
/* 126:    */     }
/* 127:132 */     else if (type == BaseItem.ItemType.WIRED_CONDITION)
/* 128:    */     {
/* 129:133 */       Map<Integer, WiredConditionBase> conditions = (Map)this.wiredConditionsMap.get(Integer.valueOf(prevXY));
/* 130:134 */       conditions.remove(Integer.valueOf(item.itemId));
/* 131:135 */       if (conditions.isEmpty()) {
/* 132:136 */         this.wiredConditionsMap.remove(Integer.valueOf(prevXY));
/* 133:    */       }
/* 134:139 */       conditions = (Map)this.wiredConditionsMap.get(Integer.valueOf(item.getXy()));
/* 135:140 */       if (conditions == null)
/* 136:    */       {
/* 137:141 */         conditions = new ConcurrentHashMap();
/* 138:142 */         this.wiredConditionsMap.put(Integer.valueOf(item.getXy()), conditions);
/* 139:    */       }
/* 140:145 */       conditions.put(Integer.valueOf(item.itemId), (WiredConditionBase)item);
/* 141:    */     }
/* 142:    */   }
/* 143:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.wired.WiredManager
 * JD-Core Version:    0.7.0.1
 */