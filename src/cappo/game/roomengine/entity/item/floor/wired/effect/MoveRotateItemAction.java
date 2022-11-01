/*   1:    */ package cappo.game.roomengine.entity.item.floor.wired.effect;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.DBResult;
/*   4:    */ import cappo.engine.database.Database;
/*   5:    */ import cappo.engine.logging.Log;
/*   6:    */ import cappo.engine.player.Connection;
/*   7:    */ import cappo.engine.threadpools.RoomTask;
/*   8:    */ import cappo.game.collections.Utils;
/*   9:    */ import cappo.game.games.snowwar.Direction8;
/*  10:    */ import cappo.game.rollers.RollerMoveDataObject;
/*  11:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  12:    */ import cappo.game.roomengine.roomevents.wired.MoveRotateEvent;
/*  13:    */ import cappo.protocol.messages.composers.room.engine.SlideObjectBundleComposer;
/*  14:    */ import java.sql.ResultSet;
/*  15:    */ import java.util.Map;
/*  16:    */ 
/*  17:    */ public class MoveRotateItemAction
/*  18:    */   extends WiredEffectBase
/*  19:    */ {
/*  20:    */   private int optionMOVE;
/*  21:    */   private int optionROTATE;
/*  22:    */   
/*  23:    */   public int getCode()
/*  24:    */   {
/*  25: 20 */     return 4;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public boolean needUser()
/*  29:    */   {
/*  30: 25 */     return false;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void setWiredOption(int index, int option)
/*  34:    */   {
/*  35: 30 */     if (index == 0) {
/*  36: 31 */       this.optionMOVE = option;
/*  37: 32 */     } else if (index == 1) {
/*  38: 33 */       this.optionROTATE = option;
/*  39:    */     }
/*  40:    */   }
/*  41:    */   
/*  42:    */   public int[] getWiredOptions()
/*  43:    */   {
/*  44: 39 */     return new int[] { this.optionMOVE, this.optionROTATE };
/*  45:    */   }
/*  46:    */   
/*  47:    */   public void invoke(Connection invoker)
/*  48:    */   {
/*  49: 44 */     if (this.delayEffect > 0) {
/*  50: 45 */       getRoom().addItemEvent(new MoveRotateEvent(this, invoker), this.delayEffect);
/*  51:    */     } else {
/*  52: 47 */       doEffect(this);
/*  53:    */     }
/*  54:    */   }
/*  55:    */   
/*  56:    */   public static void doEffect(MoveRotateItemAction wired)
/*  57:    */   {
/*  58: 52 */     if (!wired.items.isEmpty()) {
/*  59: 53 */       for (FloorItem item : wired.items.values()) {
/*  60: 54 */         if (item.getRoomId() == wired.getRoomId())
/*  61:    */         {
/*  62: 58 */           int newX = item.getX();
/*  63: 59 */           int newY = item.getY();
/*  64: 61 */           if (wired.optionMOVE == 4) {
/*  65: 62 */             newY--;
/*  66: 63 */           } else if (wired.optionMOVE == 5) {
/*  67: 64 */             newX++;
/*  68: 65 */           } else if (wired.optionMOVE == 6) {
/*  69: 66 */             newY++;
/*  70: 67 */           } else if (wired.optionMOVE == 7) {
/*  71: 68 */             newX--;
/*  72: 69 */           } else if (wired.optionMOVE == 2)
/*  73:    */           {
/*  74: 70 */             if (Utils.GetRandomNumber(0, 2) == 0) {
/*  75: 71 */               newX++;
/*  76:    */             } else {
/*  77: 73 */               newX--;
/*  78:    */             }
/*  79:    */           }
/*  80: 75 */           else if (wired.optionMOVE == 3)
/*  81:    */           {
/*  82: 76 */             if (Utils.GetRandomNumber(0, 2) == 0) {
/*  83: 77 */               newY++;
/*  84:    */             } else {
/*  85: 79 */               newY--;
/*  86:    */             }
/*  87:    */           }
/*  88: 81 */           else if (wired.optionMOVE == 1) {
/*  89: 82 */             if (Utils.GetRandomNumber(0, 2) == 1)
/*  90:    */             {
/*  91: 83 */               if (Utils.GetRandomNumber(0, 2) == 0) {
/*  92: 84 */                 newX++;
/*  93:    */               } else {
/*  94: 86 */                 newX--;
/*  95:    */               }
/*  96:    */             }
/*  97: 89 */             else if (Utils.GetRandomNumber(0, 2) == 0) {
/*  98: 90 */               newY++;
/*  99:    */             } else {
/* 100: 92 */               newY--;
/* 101:    */             }
/* 102:    */           }
/* 103: 97 */           Direction8 newRot = item.getDir();
/* 104: 98 */           if (wired.optionROTATE == 1)
/* 105:    */           {
/* 106: 99 */             newRot = newRot.rotateDirection90Degrees(true);
/* 107:    */           }
/* 108:100 */           else if (wired.optionROTATE == 2)
/* 109:    */           {
/* 110:101 */             newRot = newRot.rotateDirection90Degrees(false);
/* 111:    */           }
/* 112:102 */           else if (wired.optionROTATE == 3)
/* 113:    */           {
/* 114:103 */             int rnd = Utils.GetRandomNumber(0, 3);
/* 115:104 */             if (rnd == 1) {
/* 116:105 */               newRot = newRot.rotateDirection90Degrees(true);
/* 117:106 */             } else if (rnd == 2) {
/* 118:107 */               newRot = newRot.rotateDirection90Degrees(false);
/* 119:    */             }
/* 120:    */           }
/* 121:111 */           int fromX = item.getX();int fromY = item.getY();
/* 122:112 */           RollerMoveDataObject moveData = new RollerMoveDataObject(item);
/* 123:    */           
/* 124:114 */           item.getRoom().setFloorItem(null, item, newX, newY, newRot, false);
/* 125:    */           
/* 126:116 */           item.getRoom().sendMessage(SlideObjectBundleComposer.compose(fromX, fromY, moveData));
/* 127:    */         }
/* 128:    */       }
/* 129:    */     }
/* 130:    */   }
/* 131:    */   
/* 132:    */   public void saveData()
/* 133:    */   {
/* 134:    */     try
/* 135:    */     {
/* 136:125 */       Database.exec("INSERT INTO trigger_rotation (item_id,movement_status,rotation_status)VALUES(" + this.itemId + "," + this.optionMOVE + "," + this.optionROTATE + ") on DUPLICATE KEY UPDATE `movement_status`=" + this.optionMOVE + ",`rotation_status`=" + this.optionROTATE + ";", new Object[0]);
/* 137:126 */       super.saveData();
/* 138:    */     }
/* 139:    */     catch (Exception ex)
/* 140:    */     {
/* 141:128 */       Log.printException("ShowMessage-saveData", ex);
/* 142:    */     }
/* 143:    */   }
/* 144:    */   
/* 145:    */   public void loadData(DBResult result)
/* 146:    */   {
/* 147:    */     try
/* 148:    */     {
/* 149:135 */       Database.query(result, "SELECT movement_status,rotation_status FROM trigger_rotation WHERE item_id = " + this.itemId + ";", new Object[0]);
/* 150:136 */       if (result.data.next())
/* 151:    */       {
/* 152:137 */         this.optionMOVE = result.data.getInt("movement_status");
/* 153:138 */         this.optionROTATE = result.data.getInt("rotation_status");
/* 154:    */       }
/* 155:140 */       super.loadData(result);
/* 156:    */     }
/* 157:    */     catch (Exception ex)
/* 158:    */     {
/* 159:142 */       Log.printException("ShowMessage-loadData", ex);
/* 160:    */     }
/* 161:    */   }
/* 162:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.effect.MoveRotateItemAction
 * JD-Core Version:    0.7.0.1
 */