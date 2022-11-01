/*   1:    */ package cappo.protocol.messages.composers.room.engine;
/*   2:    */ 
/*   3:    */ import cappo.engine.network.MessageWriter;
/*   4:    */ import cappo.engine.player.Connection;
/*   5:    */ import cappo.game.bots.RentalBot;
/*   6:    */ import cappo.game.games.snowwar.Direction8;
/*   7:    */ import cappo.game.pets.Pet;
/*   8:    */ import cappo.game.pets.PetBase;
/*   9:    */ import cappo.game.player.AvatarLook;
/*  10:    */ import cappo.game.player.PlayerData;
/*  11:    */ import cappo.game.roomengine.entity.live.Avatar;
/*  12:    */ import cappo.game.roomengine.entity.live.PetEntity;
/*  13:    */ import cappo.game.roomengine.entity.live.RentalBotEntity;
/*  14:    */ import cappo.protocol.messages.Composer;
/*  15:    */ import java.util.Collection;
/*  16:    */ 
/*  17:    */ public class UsersComposer
/*  18:    */ {
/*  19:    */   public static int HEADER;
/*  20:    */   
/*  21:    */   public static final MessageWriter compose(Collection<Avatar> UserList, Collection<PetEntity> petList, Collection<RentalBotEntity> rentalBotList)
/*  22:    */   {
/*  23: 23 */     int i = 0;
/*  24:    */     
/*  25: 25 */     MessageWriter ClientMessage = new MessageWriter(100 + (UserList.size() + petList.size() + rentalBotList.size()) * 400);
/*  26: 26 */     Composer.initPacket(HEADER, ClientMessage);
/*  27: 27 */     Composer.add(ClientMessage.setSaved(Integer.valueOf(0)), ClientMessage);
/*  28: 29 */     for (Avatar User : UserList)
/*  29:    */     {
/*  30: 30 */       PlayerData playerData = User.cn.getPlayerData();
/*  31: 31 */       Composer.add(Integer.valueOf(User.id), ClientMessage);
/*  32: 32 */       Composer.add(playerData.userName, ClientMessage);
/*  33: 33 */       Composer.add(playerData.motto, ClientMessage);
/*  34: 34 */       Composer.add(playerData.avatarLook.toString(), ClientMessage);
/*  35: 35 */       Composer.add(Short.valueOf(User.virtualId), ClientMessage);
/*  36: 36 */       Composer.add(Integer.valueOf(User.x), ClientMessage);
/*  37: 37 */       Composer.add(Integer.valueOf(User.y), ClientMessage);
/*  38: 38 */       Composer.add(Float.toString(User.z).replace(',', '.'), ClientMessage);
/*  39: 39 */       Composer.add(Integer.valueOf(User.RotBody.getRot()), ClientMessage);
/*  40: 40 */       Composer.add(Integer.valueOf(User.entityType), ClientMessage);
/*  41: 41 */       Composer.add(playerData.sex == 1 ? "M" : "F", ClientMessage);
/*  42: 42 */       Composer.add(Integer.valueOf(-1), ClientMessage);
/*  43: 43 */       Composer.add(Integer.valueOf(-1), ClientMessage);
/*  44: 44 */       Composer.add("GroupName", ClientMessage);
/*  45: 45 */       Composer.add("", ClientMessage);
/*  46: 46 */       Composer.add(Integer.valueOf(playerData.AchievementsScore), ClientMessage);
/*  47: 47 */       Composer.add(Boolean.valueOf(false), ClientMessage);
/*  48: 48 */       i++;
/*  49:    */     }
/*  50: 51 */     for (PetEntity User : petList)
/*  51:    */     {
/*  52: 52 */       Composer.add(Integer.valueOf(User.petData.id), ClientMessage);
/*  53: 53 */       Composer.add(User.petData.name, ClientMessage);
/*  54: 54 */       Composer.add(User.motto, ClientMessage);
/*  55: 55 */       Composer.add(User.look, ClientMessage);
/*  56: 56 */       Composer.add(Short.valueOf(User.virtualId), ClientMessage);
/*  57: 57 */       Composer.add(Integer.valueOf(User.x), ClientMessage);
/*  58: 58 */       Composer.add(Integer.valueOf(User.y), ClientMessage);
/*  59: 59 */       Composer.add(Float.toString(User.z).replace(',', '.'), ClientMessage);
/*  60: 60 */       Composer.add(Integer.valueOf(User.RotBody.getRot()), ClientMessage);
/*  61: 61 */       Composer.add(Integer.valueOf(User.entityType), ClientMessage);
/*  62: 62 */       Composer.add(Short.valueOf(User.petData.base.raceId), ClientMessage);
/*  63: 63 */       Composer.add(Integer.valueOf(User.petData.ownerId), ClientMessage);
/*  64: 64 */       Composer.add(User.petData.ownerName, ClientMessage);
/*  65: 65 */       Composer.add(Integer.valueOf(0), ClientMessage);
/*  66: 66 */       Composer.add(Boolean.valueOf(User.petData.haveSaddle), ClientMessage);
/*  67: 67 */       Composer.add(Boolean.valueOf(User.ridingEntity != null), ClientMessage);
/*  68: 68 */       Composer.add(Boolean.valueOf(false), ClientMessage);
/*  69: 69 */       Composer.add(Boolean.valueOf(false), ClientMessage);
/*  70: 70 */       Composer.add(Boolean.valueOf(false), ClientMessage);
/*  71: 71 */       Composer.add(Boolean.valueOf(false), ClientMessage);
/*  72: 72 */       Composer.add(Integer.valueOf(0), ClientMessage);
/*  73: 73 */       Composer.add("", ClientMessage);
/*  74: 74 */       i++;
/*  75:    */     }
/*  76: 77 */     for (RentalBotEntity bot : rentalBotList)
/*  77:    */     {
/*  78: 78 */       Composer.add(Integer.valueOf(bot.botData.id), ClientMessage);
/*  79: 79 */       Composer.add(bot.botData.name, ClientMessage);
/*  80: 80 */       Composer.add(bot.botData.motto, ClientMessage);
/*  81: 81 */       Composer.add(bot.botData.botLook.toString(), ClientMessage);
/*  82: 82 */       Composer.add(Short.valueOf(bot.virtualId), ClientMessage);
/*  83: 83 */       Composer.add(Integer.valueOf(bot.x), ClientMessage);
/*  84: 84 */       Composer.add(Integer.valueOf(bot.y), ClientMessage);
/*  85: 85 */       Composer.add(Float.toString(bot.z).replace(',', '.'), ClientMessage);
/*  86: 86 */       Composer.add(Integer.valueOf(bot.RotBody.getRot()), ClientMessage);
/*  87: 87 */       Composer.add(Integer.valueOf(bot.entityType), ClientMessage);
/*  88: 88 */       Composer.add(bot.botData.gender, ClientMessage);
/*  89: 89 */       Composer.add(Integer.valueOf(bot.botData.ownerId), ClientMessage);
/*  90: 90 */       Composer.add(bot.botData.ownerName, ClientMessage);
/*  91:    */       
/*  92: 92 */       Composer.add(Integer.valueOf(7), ClientMessage);
/*  93:    */       
/*  94: 94 */       Composer.writeInt16(0, ClientMessage);
/*  95: 95 */       Composer.writeInt16(1, ClientMessage);
/*  96: 96 */       Composer.writeInt16(2, ClientMessage);
/*  97: 97 */       Composer.writeInt16(3, ClientMessage);
/*  98: 98 */       Composer.writeInt16(4, ClientMessage);
/*  99: 99 */       Composer.writeInt16(5, ClientMessage);
/* 100:100 */       Composer.writeInt16(6, ClientMessage);
/* 101:    */       
/* 102:102 */       i++;
/* 103:    */     }
/* 104:105 */     ClientMessage.writeSaved(Integer.valueOf(i));
/* 105:106 */     Composer.endPacket(ClientMessage);
/* 106:107 */     return ClientMessage;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public static final MessageWriter compose(Avatar User)
/* 110:    */   {
/* 111:111 */     PlayerData playerData = User.cn.getPlayerData();
/* 112:    */     
/* 113:113 */     MessageWriter ClientMessage = new MessageWriter(400);
/* 114:114 */     Composer.initPacket(HEADER, ClientMessage);
/* 115:115 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 116:116 */     Composer.add(Integer.valueOf(User.id), ClientMessage);
/* 117:117 */     Composer.add(playerData.userName, ClientMessage);
/* 118:118 */     Composer.add(playerData.motto, ClientMessage);
/* 119:119 */     Composer.add(playerData.avatarLook.toString(), ClientMessage);
/* 120:120 */     Composer.add(Short.valueOf(User.virtualId), ClientMessage);
/* 121:121 */     Composer.add(Integer.valueOf(User.x), ClientMessage);
/* 122:122 */     Composer.add(Integer.valueOf(User.y), ClientMessage);
/* 123:123 */     Composer.add(Float.toString(User.z).replace(',', '.'), ClientMessage);
/* 124:124 */     Composer.add(Integer.valueOf(User.RotBody.getRot()), ClientMessage);
/* 125:125 */     Composer.add(Integer.valueOf(User.entityType), ClientMessage);
/* 126:126 */     Composer.add(playerData.sex == 1 ? "M" : "F", ClientMessage);
/* 127:127 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 128:128 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 129:129 */     Composer.add("GroupName", ClientMessage);
/* 130:130 */     Composer.add("", ClientMessage);
/* 131:131 */     Composer.add(Integer.valueOf(playerData.AchievementsScore), ClientMessage);
/* 132:132 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 133:133 */     Composer.endPacket(ClientMessage);
/* 134:134 */     return ClientMessage;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public static final MessageWriter compose(PetEntity User)
/* 138:    */   {
/* 139:138 */     MessageWriter ClientMessage = new MessageWriter(400);
/* 140:139 */     Composer.initPacket(HEADER, ClientMessage);
/* 141:140 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 142:141 */     Composer.add(Integer.valueOf(User.petData.id), ClientMessage);
/* 143:142 */     Composer.add(User.petData.name, ClientMessage);
/* 144:143 */     Composer.add(User.motto, ClientMessage);
/* 145:144 */     Composer.add(User.look, ClientMessage);
/* 146:145 */     Composer.add(Short.valueOf(User.virtualId), ClientMessage);
/* 147:146 */     Composer.add(Integer.valueOf(User.x), ClientMessage);
/* 148:147 */     Composer.add(Integer.valueOf(User.y), ClientMessage);
/* 149:148 */     Composer.add(Float.toString(User.z).replace(',', '.'), ClientMessage);
/* 150:149 */     Composer.add(Integer.valueOf(User.RotBody.getRot()), ClientMessage);
/* 151:150 */     Composer.add(Integer.valueOf(User.entityType), ClientMessage);
/* 152:151 */     Composer.add(Short.valueOf(User.petData.base.raceId), ClientMessage);
/* 153:152 */     Composer.add(Integer.valueOf(User.petData.ownerId), ClientMessage);
/* 154:153 */     Composer.add(User.petData.ownerName, ClientMessage);
/* 155:154 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 156:155 */     Composer.add(Boolean.valueOf(User.petData.haveSaddle), ClientMessage);
/* 157:156 */     Composer.add(Boolean.valueOf(User.ridingEntity != null), ClientMessage);
/* 158:157 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 159:158 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 160:159 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 161:160 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 162:161 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 163:162 */     Composer.add("", ClientMessage);
/* 164:163 */     Composer.endPacket(ClientMessage);
/* 165:164 */     return ClientMessage;
/* 166:    */   }
/* 167:    */   
/* 168:    */   public static final MessageWriter compose(RentalBotEntity bot)
/* 169:    */   {
/* 170:169 */     MessageWriter ClientMessage = new MessageWriter(400);
/* 171:170 */     Composer.initPacket(HEADER, ClientMessage);
/* 172:171 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 173:    */     
/* 174:173 */     Composer.add(Integer.valueOf(bot.botData.id), ClientMessage);
/* 175:174 */     Composer.add(bot.botData.name, ClientMessage);
/* 176:175 */     Composer.add(bot.botData.motto, ClientMessage);
/* 177:176 */     Composer.add(bot.botData.botLook.toString(), ClientMessage);
/* 178:177 */     Composer.add(Short.valueOf(bot.virtualId), ClientMessage);
/* 179:178 */     Composer.add(Integer.valueOf(bot.x), ClientMessage);
/* 180:179 */     Composer.add(Integer.valueOf(bot.y), ClientMessage);
/* 181:180 */     Composer.add(Float.toString(bot.z).replace(',', '.'), ClientMessage);
/* 182:181 */     Composer.add(Integer.valueOf(bot.RotBody.getRot()), ClientMessage);
/* 183:182 */     Composer.add(Integer.valueOf(bot.entityType), ClientMessage);
/* 184:183 */     Composer.add(bot.botData.gender, ClientMessage);
/* 185:184 */     Composer.add(Integer.valueOf(bot.botData.ownerId), ClientMessage);
/* 186:185 */     Composer.add(bot.botData.ownerName, ClientMessage);
/* 187:    */     
/* 188:187 */     Composer.add(Integer.valueOf(7), ClientMessage);
/* 189:    */     
/* 190:189 */     Composer.writeInt16(0, ClientMessage);
/* 191:190 */     Composer.writeInt16(1, ClientMessage);
/* 192:191 */     Composer.writeInt16(2, ClientMessage);
/* 193:192 */     Composer.writeInt16(3, ClientMessage);
/* 194:193 */     Composer.writeInt16(4, ClientMessage);
/* 195:194 */     Composer.writeInt16(5, ClientMessage);
/* 196:195 */     Composer.writeInt16(6, ClientMessage);
/* 197:    */     
/* 198:    */ 
/* 199:198 */     Composer.endPacket(ClientMessage);
/* 200:199 */     return ClientMessage;
/* 201:    */   }
/* 202:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.UsersComposer
 * JD-Core Version:    0.7.0.1
 */