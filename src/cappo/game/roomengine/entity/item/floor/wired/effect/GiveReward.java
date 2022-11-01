/*   1:    */ package cappo.game.roomengine.entity.item.floor.wired.effect;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.DBResult;
/*   4:    */ import cappo.engine.database.Database;
/*   5:    */ import cappo.engine.logging.Log;
/*   6:    */ import cappo.engine.network.QueueWriter;
/*   7:    */ import cappo.engine.player.Connection;
/*   8:    */ import cappo.engine.threadpools.RoomTask;
/*   9:    */ import cappo.game.catalog.Catalog;
/*  10:    */ import cappo.game.catalog.Catalog.CatalogProduct;
/*  11:    */ import cappo.game.collections.Utils;
/*  12:    */ import cappo.game.roomengine.entity.live.Avatar;
/*  13:    */ import cappo.game.roomengine.roomevents.wired.GiveRewardEvent;
/*  14:    */ import cappo.protocol.messages.composers.userdefinedroomevents.WiredRewardNotificationComposer;
/*  15:    */ import cappo.protocol.messages.events.catalog.PurchaseFromCatalogParser;
/*  16:    */ import java.sql.ResultSet;
/*  17:    */ import java.util.ArrayList;
/*  18:    */ import java.util.List;
/*  19:    */ import java.util.Map;
/*  20:    */ 
/*  21:    */ public class GiveReward
/*  22:    */   extends WiredEffectBase
/*  23:    */ {
/*  24:    */   private int option1;
/*  25:    */   private int option2;
/*  26:    */   private int option3;
/*  27:    */   
/*  28:    */   public int getCode()
/*  29:    */   {
/*  30: 28 */     return 17;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public boolean needUser()
/*  34:    */   {
/*  35: 33 */     return false;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public void setWiredOption(int index, int option)
/*  39:    */   {
/*  40: 39 */     if (index == 0) {
/*  41: 40 */       this.option1 = option;
/*  42: 41 */     } else if (index == 1) {
/*  43: 42 */       this.option2 = option;
/*  44: 43 */     } else if (index == 2) {
/*  45: 44 */       this.option3 = option;
/*  46:    */     }
/*  47:    */   }
/*  48:    */   
/*  49:    */   public int[] getWiredOptions()
/*  50:    */   {
/*  51: 50 */     return new int[] { this.option1, this.option2, this.option3 };
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void setWiredData(String data)
/*  55:    */   {
/*  56: 55 */     this.rewards.clear();
/*  57:    */     
/*  58: 57 */     int probability = 0;
/*  59: 59 */     if (!data.isEmpty())
/*  60:    */     {
/*  61: 60 */       String[] rewardItems = data.split(";");
/*  62: 61 */       for (String rwd : rewardItems) {
/*  63: 62 */         if (!rwd.isEmpty())
/*  64:    */         {
/*  65: 66 */           String[] parts = rwd.split(",");
/*  66: 67 */           if ((parts.length == 3) && (!parts[1].isEmpty()))
/*  67:    */           {
/*  68: 71 */             int prob = Integer.parseInt(parts[2]);
/*  69: 72 */             if ((prob >= 0) && (prob <= 100))
/*  70:    */             {
/*  71: 76 */               Reward reward = new Reward(null);
/*  72: 77 */               reward.isBadge = (!parts[0].equals("1"));
/*  73: 79 */               if (!reward.isBadge)
/*  74:    */               {
/*  75: 80 */                 Catalog.CatalogProduct product = (Catalog.CatalogProduct)Catalog.Items.get(Integer.valueOf(Integer.parseInt(parts[1])));
/*  76: 81 */                 if (product == null) {}
/*  77:    */               }
/*  78:    */               else
/*  79:    */               {
/*  80: 86 */                 reward.product = parts[1];
/*  81:    */                 
/*  82: 88 */                 reward.probability = prob;
/*  83: 89 */                 reward.probabilityStartAt = probability;
/*  84:    */                 
/*  85: 91 */                 probability += prob;
/*  86: 92 */                 if (probability > 100)
/*  87:    */                 {
/*  88: 94 */                   this.rewards.clear();
/*  89: 95 */                   return;
/*  90:    */                 }
/*  91: 98 */                 this.rewards.add(reward);
/*  92:    */               }
/*  93:    */             }
/*  94:    */           }
/*  95:    */         }
/*  96:    */       }
/*  97:    */     }
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void saveData()
/* 101:    */   {
/* 102:    */     try
/* 103:    */     {
/* 104:105 */       Database.exec("INSERT INTO trigger_item (trigger_id,trigger_data)VALUES(" + this.itemId + ",?) on DUPLICATE KEY UPDATE `trigger_data`=?;", new Object[] { getWiredData(), getWiredData() });
/* 105:106 */       super.saveData();
/* 106:    */     }
/* 107:    */     catch (Exception ex)
/* 108:    */     {
/* 109:108 */       Log.printException("ShowMessage-saveData", ex);
/* 110:    */     }
/* 111:    */   }
/* 112:    */   
/* 113:    */   public void loadData(DBResult result)
/* 114:    */   {
/* 115:    */     try
/* 116:    */     {
/* 117:115 */       Database.query(result, "SELECT trigger_data FROM trigger_item WHERE trigger_id = " + this.itemId + ";", new Object[0]);
/* 118:116 */       if (result.data.next()) {
/* 119:117 */         setWiredData(result.data.getString("trigger_data"));
/* 120:    */       }
/* 121:119 */       super.loadData(result);
/* 122:    */     }
/* 123:    */     catch (Exception ex)
/* 124:    */     {
/* 125:121 */       Log.printException("ShowMessage-loadData", ex);
/* 126:    */     }
/* 127:    */   }
/* 128:    */   
/* 129:    */   public String getWiredData()
/* 130:    */   {
/* 131:127 */     String data = "";
/* 132:128 */     for (Reward reward : this.rewards)
/* 133:    */     {
/* 134:129 */       if (!data.isEmpty()) {
/* 135:130 */         data = data.concat(";");
/* 136:    */       }
/* 137:133 */       data = data.concat(reward.isBadge ? "0" : "1").concat(",").concat(reward.product).concat(",").concat(Integer.toString(reward.probability));
/* 138:    */     }
/* 139:135 */     return data;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void invoke(Connection invoker)
/* 143:    */   {
/* 144:140 */     if (this.delayEffect > 0) {
/* 145:141 */       getRoom().addItemEvent(new GiveRewardEvent(this, invoker), this.delayEffect);
/* 146:    */     } else {
/* 147:143 */       doEffect(this, invoker);
/* 148:    */     }
/* 149:    */   }
/* 150:    */   
/* 151:    */   public static void doEffect(GiveReward wired, Connection invoker)
/* 152:    */   {
/* 153:148 */     int rnd = Utils.GetRandomNumber(1, 100);
/* 154:149 */     for (Reward reward : wired.rewards) {
/* 155:150 */       if ((rnd >= reward.probabilityStartAt) && (reward.probability >= rnd))
/* 156:    */       {
/* 157:154 */         if (invoker == null) {
/* 158:155 */           reward.giveReward(wired.getRoom());
/* 159:    */         } else {
/* 160:157 */           reward.giveReward(invoker);
/* 161:    */         }
/* 162:160 */         return;
/* 163:    */       }
/* 164:    */     }
/* 165:163 */     QueueWriter.writeAndFlush(invoker.socket, WiredRewardNotificationComposer.compose(4));
/* 166:    */   }
/* 167:    */   
/* 168:166 */   private final List<Reward> rewards = new ArrayList();
/* 169:    */   
/* 170:    */   private class Reward
/* 171:    */   {
/* 172:    */     public int probabilityStartAt;
/* 173:    */     public int probability;
/* 174:    */     public boolean isBadge;
/* 175:    */     public String product;
/* 176:    */     
/* 177:    */     private Reward() {}
/* 178:    */     
/* 179:    */     public void giveReward(RoomTask room)
/* 180:    */     {
/* 181:175 */       for (Avatar User : room.userList.values()) {
/* 182:176 */         give(User.cn);
/* 183:    */       }
/* 184:    */     }
/* 185:    */     
/* 186:    */     public void giveReward(Connection cn)
/* 187:    */     {
/* 188:181 */       give(cn);
/* 189:    */     }
/* 190:    */     
/* 191:    */     private void give(Connection cn)
/* 192:    */     {
/* 193:185 */       if (!this.isBadge)
/* 194:    */       {
/* 195:186 */         Catalog.CatalogProduct catalogProduct = (Catalog.CatalogProduct)Catalog.Items.get(Integer.valueOf(Integer.parseInt(this.product)));
/* 196:187 */         if (catalogProduct == null) {
/* 197:188 */           return;
/* 198:    */         }
/* 199:191 */         PurchaseFromCatalogParser.buyProduct(catalogProduct, "", 1, cn);
/* 200:192 */         QueueWriter.writeAndFlush(cn.socket, WiredRewardNotificationComposer.compose(6));
/* 201:    */       }
/* 202:    */       else
/* 203:    */       {
/* 204:194 */         cn.giveBadge(this.product);
/* 205:195 */         QueueWriter.writeAndFlush(cn.socket, WiredRewardNotificationComposer.compose(7));
/* 206:    */       }
/* 207:    */     }
/* 208:    */   }
/* 209:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.effect.GiveReward
 * JD-Core Version:    0.7.0.1
 */