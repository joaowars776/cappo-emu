/*   1:    */ package cappo.game.roomengine.entity.live;
/*   2:    */ 
/*   3:    */ import cappo.engine.player.Connection;
/*   4:    */ import cappo.engine.threadpools.RoomTask;
/*   5:    */ import cappo.game.collections.Utils;
/*   6:    */ import cappo.game.games.snowwar.Direction8;
/*   7:    */ import cappo.game.pets.Pet;
/*   8:    */ import cappo.game.pets.PetBase;
/*   9:    */ import cappo.game.roomengine.gamemap.GameMapBase;
/*  10:    */ import cappo.protocol.messages.composers.notifications.PetLevelNotificationComposer;
/*  11:    */ 
/*  12:    */ public class PetEntity
/*  13:    */   extends LiveEntity
/*  14:    */ {
/*  15:    */   public Pet petData;
/*  16:    */   public String motto;
/*  17:    */   public String look;
/*  18: 15 */   private long NextThink = 0L;
/*  19:    */   
/*  20:    */   public PetEntity(RoomTask room, short virtualId)
/*  21:    */   {
/*  22: 18 */     super(room, virtualId);
/*  23:    */   }
/*  24:    */   
/*  25:    */   public void OnSelfEnterRoom(RoomTask room)
/*  26:    */   {
/*  27: 22 */     moveTo(Utils.GetRandomNumber(0, room.model.widthX), Utils.GetRandomNumber(0, room.model.heightY));
/*  28:    */   }
/*  29:    */   
/*  30:    */   public void OnTimerTick(RoomTask room)
/*  31:    */   {
/*  32: 26 */     long timenow = Utils.getTimestamp();
/*  33: 27 */     if (timenow < this.NextThink) {
/*  34: 28 */       return;
/*  35:    */     }
/*  36: 31 */     int r = Utils.GetRandomNumber(1, 50);
/*  37: 32 */     if (r < 4)
/*  38:    */     {
/*  39: 33 */       moveTo(Utils.GetRandomNumber(1, room.model.widthX), Utils.GetRandomNumber(1, room.model.heightY));
/*  40: 34 */       this.NextThink = (timenow + 4L);
/*  41:    */     }
/*  42: 35 */     else if (r == 8)
/*  43:    */     {
/*  44: 36 */       String spech = this.petData.base.getSpeech((short)3);
/*  45: 37 */       if (!spech.isEmpty()) {
/*  46: 38 */         if (spech.length() == 3) {
/*  47: 39 */           setStatus(spech, Float.toString(this.z));
/*  48:    */         } else {
/*  49: 41 */           say(spech, 0, -1, false);
/*  50:    */         }
/*  51:    */       }
/*  52: 44 */       this.NextThink = (timenow + 3L);
/*  53:    */     }
/*  54: 45 */     else if (r == 35)
/*  55:    */     {
/*  56: 46 */       this.petData.Experience += 10;
/*  57: 47 */       if (this.petData.base.checkLevel(this.petData)) {
/*  58: 48 */         room.sendMessage(PetLevelNotificationComposer.compose(this.virtualId, this.petData));
/*  59:    */       }
/*  60:    */     }
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void OnUserEnterRoom(RoomTask room, Avatar connection) {}
/*  64:    */   
/*  65:    */   public void OnUserLeaveRoom(RoomTask room, Connection connection) {}
/*  66:    */   
/*  67:    */   public void OnUserSay(RoomTask room, Avatar User, String Say)
/*  68:    */   {
/*  69: 62 */     Say = Say.toLowerCase();
/*  70:    */     
/*  71: 64 */     String petName = this.petData.name.toLowerCase();
/*  72: 66 */     if (Say.equals(petName))
/*  73:    */     {
/*  74: 67 */       SetRot(Direction8.getRot(this.x, this.y, User.x, User.y));
/*  75: 68 */       return;
/*  76:    */     }
/*  77: 71 */     if (Say.startsWith(petName + " "))
/*  78:    */     {
/*  79: 72 */       if (User.id != this.petData.ownerId) {
/*  80: 73 */         return;
/*  81:    */       }
/*  82: 76 */       Say = Say.substring(petName.length() + 1);
/*  83: 78 */       if (Utils.GetRandomNumber(1, 8) < 5)
/*  84:    */       {
/*  85: 79 */         if (this.petData.Energy < 10)
/*  86:    */         {
/*  87: 80 */           String rSpeech = this.petData.base.getSpeech((short)1);
/*  88: 81 */           if (rSpeech.length() != 3) {
/*  89: 82 */             say(rSpeech, 0, -1, false);
/*  90:    */           } else {
/*  91: 84 */             setStatus(rSpeech, Float.toString(this.z));
/*  92:    */           }
/*  93:    */         }
/*  94: 86 */         else if (Say.charAt(0) == 's')
/*  95:    */         {
/*  96: 87 */           if (Say.equals("silent"))
/*  97:    */           {
/*  98: 88 */             setStatus("", "");
/*  99:    */             
/* 100: 90 */             this.petData.Experience += 8;
/* 101: 91 */             this.petData.Nutrition -= 2;
/* 102: 92 */             this.petData.Energy += 5;
/* 103: 93 */             if (this.petData.base.checkLevel(this.petData)) {
/* 104: 94 */               room.sendMessage(PetLevelNotificationComposer.compose(this.virtualId, this.petData));
/* 105:    */             }
/* 106:    */           }
/* 107: 96 */           else if (Say.equals("stand"))
/* 108:    */           {
/* 109: 97 */             setStatus("", "");
/* 110: 98 */             this.petData.Experience += 10;
/* 111: 99 */             this.petData.Nutrition -= 1;
/* 112:100 */             this.petData.Energy += 5;
/* 113:101 */             if (this.petData.base.checkLevel(this.petData)) {
/* 114:102 */               room.sendMessage(PetLevelNotificationComposer.compose(this.virtualId, this.petData));
/* 115:    */             }
/* 116:    */           }
/* 117:104 */           else if (Say.equals("sit"))
/* 118:    */           {
/* 119:105 */             setStatus("sit", Float.toString(this.z));
/* 120:106 */             this.petData.Experience += 10;
/* 121:107 */             this.petData.Nutrition -= 5;
/* 122:108 */             this.petData.Energy -= 5;
/* 123:109 */             if (this.petData.base.checkLevel(this.petData)) {
/* 124:110 */               room.sendMessage(PetLevelNotificationComposer.compose(this.virtualId, this.petData));
/* 125:    */             }
/* 126:    */           }
/* 127:112 */           else if (Say.equals("sleep"))
/* 128:    */           {
/* 129:113 */             say("ZzzZZZzzzzZzz", 0, -1, false);
/* 130:114 */             setStatus("lay", Float.toString(this.z));
/* 131:115 */             this.petData.Experience += 5;
/* 132:116 */             this.petData.Nutrition += 10;
/* 133:117 */             this.petData.Energy += 10;
/* 134:118 */             if (this.petData.base.checkLevel(this.petData)) {
/* 135:119 */               room.sendMessage(PetLevelNotificationComposer.compose(this.virtualId, this.petData));
/* 136:    */             }
/* 137:    */           }
/* 138:    */         }
/* 139:122 */         else if (Say.charAt(0) == 'h')
/* 140:    */         {
/* 141:123 */           if (Say.equals("here"))
/* 142:    */           {
/* 143:124 */             setStatus("", "");
/* 144:125 */             this.petData.Experience += 10;
/* 145:126 */             this.petData.Nutrition -= 10;
/* 146:127 */             this.petData.Energy -= 10;
/* 147:128 */             if (this.petData.base.checkLevel(this.petData)) {
/* 148:129 */               room.sendMessage(PetLevelNotificationComposer.compose(this.virtualId, this.petData));
/* 149:    */             }
/* 150:132 */             moveTo(User.x + User.RotBody.getDiffX(), User.y + User.RotBody.getDiffY());
/* 151:    */           }
/* 152:    */         }
/* 153:134 */         else if (Say.equals("play dead"))
/* 154:    */         {
/* 155:135 */           setStatus("ded", Float.toString(this.z));
/* 156:136 */           this.petData.Experience += 10;
/* 157:137 */           this.petData.Nutrition -= 3;
/* 158:138 */           this.petData.Energy -= 5;
/* 159:139 */           if (this.petData.base.checkLevel(this.petData)) {
/* 160:140 */             room.sendMessage(PetLevelNotificationComposer.compose(this.virtualId, this.petData));
/* 161:    */           }
/* 162:    */         }
/* 163:142 */         else if (Say.charAt(0) == 'd')
/* 164:    */         {
/* 165:143 */           if (Say.equals("dead"))
/* 166:    */           {
/* 167:144 */             setStatus("ded", Float.toString(this.z));
/* 168:145 */             this.petData.Experience += 10;
/* 169:146 */             this.petData.Nutrition -= 3;
/* 170:147 */             this.petData.Energy -= 5;
/* 171:148 */             if (this.petData.base.checkLevel(this.petData)) {
/* 172:149 */               room.sendMessage(PetLevelNotificationComposer.compose(this.virtualId, this.petData));
/* 173:    */             }
/* 174:    */           }
/* 175:151 */           else if (Say.equals("drink"))
/* 176:    */           {
/* 177:152 */             setStatus("drk", Float.toString(this.z));
/* 178:153 */             say("*Drinks*", 0, -1, false);
/* 179:154 */             this.petData.Experience += 10;
/* 180:155 */             this.petData.Nutrition += 30;
/* 181:156 */             this.petData.Energy += 40;
/* 182:157 */             if (this.petData.base.checkLevel(this.petData)) {
/* 183:158 */               room.sendMessage(PetLevelNotificationComposer.compose(this.virtualId, this.petData));
/* 184:    */             }
/* 185:    */           }
/* 186:    */         }
/* 187:161 */         else if (Say.equals("move"))
/* 188:    */         {
/* 189:162 */           setStatus("", "");
/* 190:163 */           moveTo(Utils.GetRandomNumber(1, room.model.widthX), Utils.GetRandomNumber(1, room.model.heightY));
/* 191:164 */           this.petData.Experience += 10;
/* 192:165 */           this.petData.Nutrition -= 5;
/* 193:166 */           this.petData.Energy -= 10;
/* 194:167 */           if (this.petData.base.checkLevel(this.petData)) {
/* 195:168 */             room.sendMessage(PetLevelNotificationComposer.compose(this.virtualId, this.petData));
/* 196:    */           }
/* 197:    */         }
/* 198:170 */         else if (Say.equals("jump"))
/* 199:    */         {
/* 200:171 */           setStatus("jmp", Float.toString(this.z));
/* 201:172 */           this.petData.Experience += 15;
/* 202:173 */           this.petData.Nutrition -= 20;
/* 203:174 */           this.petData.Energy -= 20;
/* 204:175 */           if (this.petData.base.checkLevel(this.petData)) {
/* 205:176 */             room.sendMessage(PetLevelNotificationComposer.compose(this.virtualId, this.petData));
/* 206:    */           }
/* 207:    */         }
/* 208:178 */         else if (Say.equals("eat"))
/* 209:    */         {
/* 210:179 */           setStatus("eat", Float.toString(this.z));
/* 211:180 */           say("*Eats*", 0, -1, false);
/* 212:181 */           this.petData.Experience += 10;
/* 213:182 */           this.petData.Nutrition += 10;
/* 214:183 */           this.petData.Energy += 10;
/* 215:184 */           if (this.petData.base.checkLevel(this.petData)) {
/* 216:185 */             room.sendMessage(PetLevelNotificationComposer.compose(this.virtualId, this.petData));
/* 217:    */           }
/* 218:    */         }
/* 219:187 */         else if (Say.equals("free"))
/* 220:    */         {
/* 221:188 */           this.petData.Experience += 5;
/* 222:189 */           this.petData.Nutrition += 5;
/* 223:190 */           this.petData.Energy -= 20;
/* 224:191 */           if (this.petData.base.checkLevel(this.petData)) {
/* 225:192 */             room.sendMessage(PetLevelNotificationComposer.compose(this.virtualId, this.petData));
/* 226:    */           }
/* 227:    */         }
/* 228:    */         else
/* 229:    */         {
/* 230:195 */           String rSpeech = this.petData.base.getSpeech((short)0);
/* 231:196 */           if (rSpeech.length() != 3) {
/* 232:197 */             say(rSpeech, 0, -1, false);
/* 233:    */           } else {
/* 234:199 */             setStatus(rSpeech, Float.toString(this.z));
/* 235:    */           }
/* 236:    */         }
/* 237:    */       }
/* 238:    */       else
/* 239:    */       {
/* 240:204 */         String rSpeech = this.petData.base.getSpeech((short)2);
/* 241:205 */         if (rSpeech.length() != 3) {
/* 242:206 */           say(rSpeech, 0, -1, false);
/* 243:    */         } else {
/* 244:208 */           setStatus(rSpeech, Float.toString(this.z));
/* 245:    */         }
/* 246:210 */         this.petData.Energy -= 10;
/* 247:    */       }
/* 248:    */     }
/* 249:    */   }
/* 250:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.live.PetEntity
 * JD-Core Version:    0.7.0.1
 */