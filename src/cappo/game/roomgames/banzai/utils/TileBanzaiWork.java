/*   1:    */ package cappo.game.roomgames.banzai.utils;
/*   2:    */ 
/*   3:    */ import cappo.engine.threadpools.RoomTask;
/*   4:    */ import cappo.game.collections.BaseItem;
/*   5:    */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*   6:    */ import cappo.game.roomengine.gamemap.GameMapBase;
/*   7:    */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/*   8:    */ import java.util.ArrayList;
/*   9:    */ import java.util.List;
/*  10:    */ import java.util.Map;
/*  11:    */ 
/*  12:    */ public class TileBanzaiWork
/*  13:    */ {
/*  14:    */   public static void doWork(GenericFloorItem tile, int type, RoomTask room)
/*  15:    */   {
/*  16: 18 */     int data = tile.getIntData();
/*  17: 19 */     int prefix = 3 * type;
/*  18: 21 */     if ((data < prefix) || (data > prefix + 2))
/*  19:    */     {
/*  20: 22 */       data = prefix;
/*  21: 23 */       tile.setIntData(data);
/*  22:    */     }
/*  23:    */     else
/*  24:    */     {
/*  25: 25 */       data = tile.incIntData(1);
/*  26:    */     }
/*  27: 28 */     if (data == prefix + 2)
/*  28:    */     {
/*  29: 29 */       if (type == 1)
/*  30:    */       {
/*  31: 30 */         room.ScorePoints_R += 1;
/*  32: 31 */         for (GenericFloorItem item : room.roomGamesScorersRED)
/*  33:    */         {
/*  34: 32 */           item.setIntData(room.ScorePoints_R);
/*  35: 33 */           room.floorItemUpdateNeeded(item);
/*  36:    */         }
/*  37:    */       }
/*  38: 35 */       else if (type == 2)
/*  39:    */       {
/*  40: 36 */         room.ScorePoints_G += 1;
/*  41: 37 */         for (GenericFloorItem item : room.roomGamesScorersGREEN)
/*  42:    */         {
/*  43: 38 */           item.setIntData(room.ScorePoints_G);
/*  44: 39 */           room.floorItemUpdateNeeded(item);
/*  45:    */         }
/*  46:    */       }
/*  47: 41 */       else if (type == 3)
/*  48:    */       {
/*  49: 42 */         room.ScorePoints_B += 1;
/*  50: 43 */         for (GenericFloorItem item : room.roomGamesScorersBLUE)
/*  51:    */         {
/*  52: 44 */           item.setIntData(room.ScorePoints_B);
/*  53: 45 */           room.floorItemUpdateNeeded(item);
/*  54:    */         }
/*  55:    */       }
/*  56: 47 */       else if (type == 4)
/*  57:    */       {
/*  58: 48 */         room.ScorePoints_Y += 1;
/*  59: 49 */         for (GenericFloorItem item : room.roomGamesScorersYELLOW)
/*  60:    */         {
/*  61: 50 */           item.setIntData(room.ScorePoints_Y);
/*  62: 51 */           room.floorItemUpdateNeeded(item);
/*  63:    */         }
/*  64:    */       }
/*  65: 55 */       List<GenericFloorItem> combo = findCombo(tile, data, tile.getX(), tile.getY(), 0, 0, -1, 4);
/*  66: 56 */       if (combo != null) {
/*  67: 57 */         fillCombo(tile, type, room, combo);
/*  68:    */       }
/*  69:    */     }
/*  70: 61 */     room.floorItemUpdateNeeded(tile);
/*  71:    */   }
/*  72:    */   
/*  73:    */   private static List<GenericFloorItem> findCombo(GenericFloorItem tile, int find, int X, int Y, int xCan, int yCan, int curRot, int turn)
/*  74:    */   {
/*  75: 65 */     boolean[] moves = new boolean[4];
/*  76: 67 */     if (xCan == -1)
/*  77:    */     {
/*  78: 68 */       moves[0] = true;
/*  79:    */     }
/*  80: 69 */     else if (xCan == 1)
/*  81:    */     {
/*  82: 70 */       moves[2] = true;
/*  83:    */     }
/*  84: 71 */     else if (xCan == 0)
/*  85:    */     {
/*  86: 72 */       moves[0] = true;
/*  87: 73 */       moves[2] = true;
/*  88:    */     }
/*  89: 75 */     if (yCan == -1)
/*  90:    */     {
/*  91: 76 */       moves[1] = true;
/*  92:    */     }
/*  93: 77 */     else if (yCan == 1)
/*  94:    */     {
/*  95: 78 */       moves[3] = true;
/*  96:    */     }
/*  97: 79 */     else if (yCan == 0)
/*  98:    */     {
/*  99: 80 */       moves[1] = true;
/* 100: 81 */       moves[3] = true;
/* 101:    */     }
/* 102: 84 */     if (((xCan != 0) || (yCan != 0)) && (tile.getX() == X) && (tile.getY() == Y)) {
/* 103: 85 */       return new ArrayList();
/* 104:    */     }
/* 105: 88 */     for (int i = 0; i < 4; i++) {
/* 106: 89 */       if (moves[i] != 0)
/* 107:    */       {
/* 108:    */         int y;
/* 109:    */         int x;
/* 110:    */         int y;
/* 111: 94 */         if (i == 0)
/* 112:    */         {
/* 113: 95 */           int x = X + 1;
/* 114: 96 */           y = Y;
/* 115:    */         }
/* 116:    */         else
/* 117:    */         {
/* 118:    */           int y;
/* 119: 97 */           if (i == 1)
/* 120:    */           {
/* 121: 98 */             int x = X;
/* 122: 99 */             y = Y + 1;
/* 123:    */           }
/* 124:    */           else
/* 125:    */           {
/* 126:    */             int y;
/* 127:100 */             if (i == 2)
/* 128:    */             {
/* 129:101 */               int x = X - 1;
/* 130:102 */               y = Y;
/* 131:    */             }
/* 132:    */             else
/* 133:    */             {
/* 134:104 */               x = X;
/* 135:105 */               y = Y - 1;
/* 136:    */             }
/* 137:    */           }
/* 138:    */         }
/* 139:108 */         RoomTask room = tile.getRoom();
/* 140:    */         
/* 141:110 */         int nextXY = x + y * room.model.widthX;
/* 142:112 */         if ((x < room.model.widthX) && (y < room.model.heightY))
/* 143:    */         {
/* 144:113 */           GenericFloorItem top = (GenericFloorItem)room.topFloorItems.get(Integer.valueOf(nextXY));
/* 145:114 */           if ((top != null) && 
/* 146:115 */             (top.getIntData() == find)) {
/* 147:116 */             if ((curRot != i) && (curRot != -1))
/* 148:    */             {
/* 149:117 */               if (turn != 0)
/* 150:    */               {
/* 151:118 */                 List<GenericFloorItem> found = null;
/* 152:119 */                 if (i == 0) {
/* 153:120 */                   found = findCombo(tile, find, x, y, -1, yCan * -1, i, turn - 1);
/* 154:121 */                 } else if (i == 1) {
/* 155:122 */                   found = findCombo(tile, find, x, y, xCan * -1, -1, i, turn - 1);
/* 156:123 */                 } else if (i == 2) {
/* 157:124 */                   found = findCombo(tile, find, x, y, 1, yCan * -1, i, turn - 1);
/* 158:125 */                 } else if (i == 3) {
/* 159:126 */                   found = findCombo(tile, find, x, y, xCan * -1, 1, i, turn - 1);
/* 160:    */                 }
/* 161:128 */                 if (found != null)
/* 162:    */                 {
/* 163:129 */                   found.add(top);
/* 164:130 */                   return found;
/* 165:    */                 }
/* 166:    */               }
/* 167:    */             }
/* 168:    */             else
/* 169:    */             {
/* 170:134 */               List<GenericFloorItem> found = null;
/* 171:135 */               if (i == 0) {
/* 172:136 */                 found = findCombo(tile, find, x, y, -1, yCan, i, turn);
/* 173:137 */               } else if (i == 1) {
/* 174:138 */                 found = findCombo(tile, find, x, y, xCan, -1, i, turn);
/* 175:139 */               } else if (i == 2) {
/* 176:140 */                 found = findCombo(tile, find, x, y, 1, yCan, i, turn);
/* 177:141 */               } else if (i == 3) {
/* 178:142 */                 found = findCombo(tile, find, x, y, xCan, 1, i, turn);
/* 179:    */               }
/* 180:144 */               if (found != null)
/* 181:    */               {
/* 182:145 */                 found.add(top);
/* 183:146 */                 return found;
/* 184:    */               }
/* 185:    */             }
/* 186:    */           }
/* 187:    */         }
/* 188:    */       }
/* 189:    */     }
/* 190:154 */     return null;
/* 191:    */   }
/* 192:    */   
/* 193:    */   public static void fillCombo(GenericFloorItem tile, int type, RoomTask room, List<GenericFloorItem> combo)
/* 194:    */   {
/* 195:158 */     int startX = 255;
/* 196:159 */     int startY = 255;
/* 197:160 */     int endX = -1;
/* 198:161 */     int endY = -1;
/* 199:163 */     for (GenericFloorItem item : combo)
/* 200:    */     {
/* 201:164 */       if (item.getX() < startX) {
/* 202:165 */         startX = item.getX();
/* 203:    */       }
/* 204:167 */       if (item.getY() < startY) {
/* 205:168 */         startY = item.getY();
/* 206:    */       }
/* 207:170 */       if (item.getX() > endX) {
/* 208:171 */         endX = item.getX();
/* 209:    */       }
/* 210:173 */       if (item.getY() > endY) {
/* 211:174 */         endY = item.getY();
/* 212:    */       }
/* 213:    */     }
/* 214:178 */     startX++;
/* 215:179 */     startY++;
/* 216:    */     
/* 217:181 */     int score = 0;
/* 218:    */     GenericFloorItem top;
/* 219:183 */     for (; startX < endX; startX++) {
/* 220:184 */       for (int y = startY; y < endY; y++)
/* 221:    */       {
/* 222:185 */         top = (GenericFloorItem)room.topFloorItems.get(Integer.valueOf(startX + y * room.model.widthX));
/* 223:186 */         if ((top != null) && 
/* 224:187 */           (top.baseItem.interactorType == Interactor.InteractorType.banzaifloor) && (top.extraData != tile.extraData))
/* 225:    */         {
/* 226:188 */           top.setIntData(tile.getIntData());
/* 227:189 */           room.floorItemUpdateNeeded(top);
/* 228:190 */           score++;
/* 229:    */         }
/* 230:    */       }
/* 231:    */     }
/* 232:196 */     if (type == 1)
/* 233:    */     {
/* 234:197 */       room.ScorePoints_R += score;
/* 235:198 */       for (GenericFloorItem item : room.roomGamesScorersRED)
/* 236:    */       {
/* 237:199 */         item.setIntData(room.ScorePoints_R);
/* 238:200 */         room.floorItemUpdateNeeded(item);
/* 239:    */       }
/* 240:    */     }
/* 241:202 */     else if (type == 2)
/* 242:    */     {
/* 243:203 */       room.ScorePoints_G += score;
/* 244:204 */       for (GenericFloorItem item : room.roomGamesScorersGREEN)
/* 245:    */       {
/* 246:205 */         item.setIntData(room.ScorePoints_R);
/* 247:206 */         room.floorItemUpdateNeeded(item);
/* 248:    */       }
/* 249:    */     }
/* 250:208 */     else if (type == 3)
/* 251:    */     {
/* 252:209 */       room.ScorePoints_B += score;
/* 253:210 */       for (GenericFloorItem item : room.roomGamesScorersBLUE)
/* 254:    */       {
/* 255:211 */         item.setIntData(room.ScorePoints_R);
/* 256:212 */         room.floorItemUpdateNeeded(item);
/* 257:    */       }
/* 258:    */     }
/* 259:214 */     else if (type == 4)
/* 260:    */     {
/* 261:215 */       room.ScorePoints_Y += score;
/* 262:216 */       for (GenericFloorItem item : room.roomGamesScorersYELLOW)
/* 263:    */       {
/* 264:217 */         item.setIntData(room.ScorePoints_R);
/* 265:218 */         room.floorItemUpdateNeeded(item);
/* 266:    */       }
/* 267:    */     }
/* 268:    */   }
/* 269:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomgames.banzai.utils.TileBanzaiWork
 * JD-Core Version:    0.7.0.1
 */