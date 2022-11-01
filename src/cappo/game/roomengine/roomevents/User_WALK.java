/*   1:    */ package cappo.game.roomengine.roomevents;
/*   2:    */ 
/*   3:    */ import cappo.engine.player.Connection;
/*   4:    */ import cappo.engine.threadpools.ItemTask;
/*   5:    */ import cappo.engine.threadpools.RoomTask;
/*   6:    */ import cappo.game.collections.BaseItem;
/*   7:    */ import cappo.game.games.snowwar.Direction8;
/*   8:    */ import cappo.game.player.PlayerData;
/*   9:    */ import cappo.game.roomeffects.special.SkatesEffect;
/*  10:    */ import cappo.game.roomeffects.special.SwimEffect;
/*  11:    */ import cappo.game.roomeffects.special.UserSpecialEffect;
/*  12:    */ import cappo.game.roomengine.RoomData;
/*  13:    */ import cappo.game.roomengine.Square;
/*  14:    */ import cappo.game.roomengine.SquareFlagManager;
/*  15:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  16:    */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  17:    */ import cappo.game.roomengine.entity.item.floor.RollerItem;
/*  18:    */ import cappo.game.roomengine.entity.live.Avatar;
/*  19:    */ import cappo.game.roomengine.entity.live.LiveEntity;
/*  20:    */ import cappo.game.roomengine.gamemap.GameMapBase;
/*  21:    */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/*  22:    */ import cappo.game.roomengine.wired.WiredManager;
/*  23:    */ import cappo.game.roomgames.RoomGamePlayer;
/*  24:    */ import cappo.game.roomgames.banzai.BattleBanzai;
/*  25:    */ import cappo.game.roomgames.banzai.utils.PuckBanzai;
/*  26:    */ import cappo.game.roomgames.banzai.utils.TileBanzaiWork;
/*  27:    */ import cappo.game.roomgames.football.FootBall;
/*  28:    */ import cappo.game.roomgames.football.utils.BallonFootBall;
/*  29:    */ import java.util.ArrayList;
/*  30:    */ import java.util.List;
/*  31:    */ import java.util.Map;
/*  32:    */ import java.util.Set;
/*  33:    */ 
/*  34:    */ public class User_WALK
/*  35:    */   extends Event
/*  36:    */ {
/*  37:    */   private static final int MAX_STEEPS = 25;
/*  38:    */   private final LiveEntity liveEntity;
/*  39:    */   private List<Square> walkPath;
/*  40:    */   private int walkX;
/*  41:    */   private int walkY;
/*  42:    */   private int nextX;
/*  43:    */   private int nextY;
/*  44:    */   private int nextXY;
/*  45:    */   private float nextZ;
/*  46:    */   private boolean doSteep;
/*  47:    */   private boolean findPath;
/*  48:    */   public boolean isWalking;
/*  49:    */   
/*  50:    */   public User_WALK(LiveEntity user)
/*  51:    */   {
/*  52: 54 */     this.liveEntity = user;
/*  53:    */   }
/*  54:    */   
/*  55:    */   private Square findBestPassThrough(RoomTask room, Set<Square> opened, Square start, Square goal)
/*  56:    */   {
/*  57: 58 */     Square best = null;
/*  58: 59 */     for (Square square : opened)
/*  59:    */     {
/*  60: 60 */       if (square.xy == goal.xy) {
/*  61: 61 */         return goal;
/*  62:    */       }
/*  63: 64 */       if (!this.liveEntity.allowOverride)
/*  64:    */       {
/*  65: 65 */         if (room.squareFlag.have(square.xy, 4)) {
/*  66: 69 */           if ((!room.roomData.haveFlag(8)) && (room.squareHasUsers(square.xy))) {}
/*  67:    */         }
/*  68:    */       }
/*  69: 74 */       else if ((best == null) || (square.getLocalCost(start, goal) < best.getLocalCost(start, goal))) {
/*  70: 75 */         best = square;
/*  71:    */       }
/*  72:    */     }
/*  73: 78 */     return best;
/*  74:    */   }
/*  75:    */   
/*  76:    */   private List<Square> findBestPath(RoomTask room, Square start, Square goal)
/*  77:    */   {
/*  78: 82 */     List<Square> bestList = new ArrayList(25);
/*  79:    */     
/*  80: 84 */     boolean disableDiagonal = false;
/*  81: 85 */     if (this.liveEntity.entityType == 1)
/*  82:    */     {
/*  83: 86 */       Avatar avatar = (Avatar)this.liveEntity;
/*  84: 87 */       disableDiagonal = avatar.cn.haveFlag(64);
/*  85:    */     }
/*  86: 90 */     Square best = start;
/*  87: 91 */     for (int i = 0; i < 25; i++)
/*  88:    */     {
/*  89: 92 */       Square newBest = findBestPassThrough(room, disableDiagonal ? best.adjacenciesNoDiagonal : best.adjacencies, start, goal);
/*  90: 93 */       if (newBest == null) {
/*  91: 94 */         return null;
/*  92:    */       }
/*  93: 97 */       bestList.add(newBest);
/*  94: 98 */       best = newBest;
/*  95:100 */       if (best.xy == goal.xy) {
/*  96:101 */         return bestList;
/*  97:    */       }
/*  98:    */     }
/*  99:104 */     return null;
/* 100:    */   }
/* 101:    */   
/* 102:    */   private void checkEffects(RoomTask room, Avatar avatar)
/* 103:    */   {
/* 104:109 */     if ((!avatar.HaveStatus("sit")) && (!avatar.HaveStatus("lay")))
/* 105:    */     {
/* 106:110 */       if (room.squareFlag.eventHave(this.nextXY, 1))
/* 107:    */       {
/* 108:111 */         FloorItem top = (FloorItem)room.topFloorItems.get(Integer.valueOf(this.nextXY));
/* 109:112 */         if (top.baseItem.interactorType == Interactor.InteractorType.fbgate) {
/* 110:113 */           FootBall.togglePlayer(avatar, (short)1);
/* 111:114 */         } else if (top.baseItem.interactorType == Interactor.InteractorType.banzaigatered) {
/* 112:115 */           BattleBanzai.togglePlayer(avatar, (short)1);
/* 113:116 */         } else if (top.baseItem.interactorType == Interactor.InteractorType.banzaigategreen) {
/* 114:117 */           BattleBanzai.togglePlayer(avatar, (short)2);
/* 115:118 */         } else if (top.baseItem.interactorType == Interactor.InteractorType.banzaigateblue) {
/* 116:119 */           BattleBanzai.togglePlayer(avatar, (short)3);
/* 117:120 */         } else if (top.baseItem.interactorType == Interactor.InteractorType.banzaigateyellow) {
/* 118:121 */           BattleBanzai.togglePlayer(avatar, (short)4);
/* 119:    */         }
/* 120:124 */         return;
/* 121:    */       }
/* 122:127 */       if (avatar.roomGamePlayer != null) {
/* 123:128 */         return;
/* 124:    */       }
/* 125:131 */       if (room.squareFlag.eventHave(this.nextXY, 4))
/* 126:    */       {
/* 127:132 */         short effectId = (short)(54 + avatar.cn.getPlayerData().sex);
/* 128:133 */         if ((avatar.userSpecialEffect == null) || (avatar.userSpecialEffect.effectId != effectId)) {
/* 129:134 */           avatar.userSpecialEffect = new SkatesEffect(avatar, effectId);
/* 130:    */         }
/* 131:136 */         return;
/* 132:    */       }
/* 133:139 */       if (room.squareFlag.eventHave(this.nextXY, 128))
/* 134:    */       {
/* 135:140 */         GenericFloorItem top = (GenericFloorItem)room.topFloorItems.get(Integer.valueOf(this.nextXY));
/* 136:141 */         if (top.baseItem.interactorType == Interactor.InteractorType.lowpool)
/* 137:    */         {
/* 138:142 */           if ((avatar.userSpecialEffect == null) || (avatar.userSpecialEffect.effectId != 30)) {
/* 139:143 */             avatar.userSpecialEffect = new SwimEffect(avatar, (short)30);
/* 140:    */           }
/* 141:    */         }
/* 142:145 */         else if (top.baseItem.interactorType == Interactor.InteractorType.pool)
/* 143:    */         {
/* 144:146 */           if ((avatar.userSpecialEffect == null) || (avatar.userSpecialEffect.effectId != 29)) {
/* 145:147 */             avatar.userSpecialEffect = new SwimEffect(avatar, (short)29);
/* 146:    */           }
/* 147:    */         }
/* 148:149 */         else if ((top.baseItem.interactorType == Interactor.InteractorType.haloweenpool) && (
/* 149:150 */           (avatar.userSpecialEffect == null) || (avatar.userSpecialEffect.effectId != 37))) {
/* 150:151 */           avatar.userSpecialEffect = new SwimEffect(avatar, (short)37);
/* 151:    */         }
/* 152:154 */         return;
/* 153:    */       }
/* 154:    */     }
/* 155:158 */     if (avatar.roomGamePlayer != null) {
/* 156:159 */       return;
/* 157:    */     }
/* 158:162 */     if (avatar.userSpecialEffect != null) {
/* 159:164 */       avatar.userSpecialEffect.stopEffect();
/* 160:    */     }
/* 161:    */   }
/* 162:    */   
/* 163:    */   private void findWalkPath(RoomTask room)
/* 164:    */   {
/* 165:169 */     if ((this.liveEntity.entityType != 1) && 
/* 166:170 */       (this.walkX == room.model.doorX) && (this.walkY == room.model.doorY)) {
/* 167:171 */       return;
/* 168:    */     }
/* 169:175 */     int walkXY = this.walkX + this.walkY * room.model.widthX;
/* 170:177 */     if (!room.canWalk(this.liveEntity, walkXY, true)) {
/* 171:178 */       return;
/* 172:    */     }
/* 173:181 */     if (!room.validTile(this.liveEntity.xy)) {
/* 174:182 */       return;
/* 175:    */     }
/* 176:185 */     Square start = room.model.getSquare(this.liveEntity.xy);
/* 177:186 */     Square goal = room.model.getSquare(walkXY);
/* 178:    */     
/* 179:188 */     this.walkPath = findBestPath(room, start, goal);
/* 180:    */   }
/* 181:    */   
/* 182:    */   public boolean doWalkSteep(RoomTask room)
/* 183:    */   {
/* 184:    */     int type;
/* 185:192 */     if (this.liveEntity.entityType == 1)
/* 186:    */     {
/* 187:193 */       Avatar avatar = (Avatar)this.liveEntity;
/* 188:195 */       if (room.squareFlag.eventHave(this.nextXY, 512)) {
/* 189:196 */         room.wiredManager.parseWiredWalksOffFurni(avatar.cn, this.nextXY);
/* 190:    */       }
/* 191:198 */       if (room.squareFlag.eventHave(this.nextXY, 256)) {
/* 192:199 */         room.wiredManager.parseWiredWalksOnFurni(avatar.cn, this.nextXY);
/* 193:    */       }
/* 194:202 */       if (!this.isWalking) {
/* 195:204 */         return false;
/* 196:    */       }
/* 197:207 */       if ((avatar.roomGamePlayer != null) && 
/* 198:208 */         (room.squareFlag.eventHave(this.nextXY, 8)))
/* 199:    */       {
/* 200:209 */         GenericFloorItem top = (GenericFloorItem)room.topFloorItems.get(Integer.valueOf(this.nextXY));
/* 201:210 */         type = avatar.roomGamePlayer.team;
/* 202:211 */         if (top.getIntData() != 3 * type + 2) {
/* 203:212 */           TileBanzaiWork.doWork(top, type, room);
/* 204:    */         }
/* 205:    */       }
/* 206:    */     }
/* 207:218 */     this.liveEntity.x = this.nextX;
/* 208:219 */     this.liveEntity.y = this.nextY;
/* 209:220 */     this.liveEntity.xy = this.nextXY;
/* 210:222 */     if (this.liveEntity.ridingEntity != null)
/* 211:    */     {
/* 212:223 */       this.liveEntity.ridingEntity.x = this.nextX;
/* 213:224 */       this.liveEntity.ridingEntity.y = this.nextY;
/* 214:225 */       this.liveEntity.ridingEntity.xy = this.nextXY;
/* 215:226 */       if (this.liveEntity.entityType == 2)
/* 216:    */       {
/* 217:227 */         this.liveEntity.ridingEntity.z = (this.nextZ + 1.0F);
/* 218:228 */         this.liveEntity.z = this.nextZ;
/* 219:    */       }
/* 220:    */       else
/* 221:    */       {
/* 222:230 */         this.liveEntity.ridingEntity.z = this.nextZ;
/* 223:231 */         this.liveEntity.z = (this.nextZ + 1.0F);
/* 224:    */       }
/* 225:234 */       room.userUpdateNeeded(this.liveEntity.ridingEntity);
/* 226:235 */       room.userUpdateNeeded(this.liveEntity);
/* 227:    */     }
/* 228:    */     else
/* 229:    */     {
/* 230:237 */       this.liveEntity.z = this.nextZ;
/* 231:    */       
/* 232:239 */       room.updateUserStatus(this.liveEntity, true);
/* 233:240 */       room.userUpdateNeeded(this.liveEntity);
/* 234:    */     }
/* 235:243 */     if ((this.walkX == this.liveEntity.x) && (this.liveEntity.y == this.walkY))
/* 236:    */     {
/* 237:244 */       this.isWalking = false;
/* 238:246 */       if ((this.walkX == room.model.doorX) && (this.walkY == room.model.doorY))
/* 239:    */       {
/* 240:247 */         if (this.liveEntity.entityType == 1)
/* 241:    */         {
/* 242:248 */           Avatar user = (Avatar)this.liveEntity;
/* 243:249 */           room.removeUserFromRoom(user.cn, true, false);
/* 244:    */         }
/* 245:    */       }
/* 246:252 */       else if (room.squareFlag.eventHave(this.liveEntity.xy, 2))
/* 247:    */       {
/* 248:253 */         Map<Integer, FloorItem> squareItems = (Map)room.mapFloorItems.get(Integer.valueOf(this.liveEntity.xy));
/* 249:254 */         if (squareItems != null) {
/* 250:255 */           for (FloorItem roller : squareItems.values()) {
/* 251:256 */             if (roller.baseItem.interactorType == Interactor.InteractorType.roller)
/* 252:    */             {
/* 253:257 */               room.rollers.put(Integer.valueOf(roller.itemId), (RollerItem)roller);
/* 254:258 */               break;
/* 255:    */             }
/* 256:    */           }
/* 257:    */         }
/* 258:    */       }
/* 259:    */     }
/* 260:266 */     return this.isWalking;
/* 261:    */   }
/* 262:    */   
/* 263:    */   public void run(RoomTask room)
/* 264:    */   {
/* 265:271 */     if (!this.isWalking)
/* 266:    */     {
/* 267:272 */       this.walkPath = null;
/* 268:273 */       room.entityWalk(this.nextXY, this.liveEntity, false);
/* 269:274 */       return;
/* 270:    */     }
/* 271:277 */     if (this.doSteep)
/* 272:    */     {
/* 273:278 */       this.doSteep = false;
/* 274:279 */       if (!doWalkSteep(room))
/* 275:    */       {
/* 276:280 */         this.walkPath = null;
/* 277:281 */         stopWalk(room);
/* 278:282 */         return;
/* 279:    */       }
/* 280:    */     }
/* 281:286 */     if (this.findPath)
/* 282:    */     {
/* 283:287 */       this.findPath = false;
/* 284:288 */       this.walkPath = null;
/* 285:289 */       findWalkPath(room);
/* 286:    */     }
/* 287:292 */     if ((this.walkPath == null) || (this.walkPath.isEmpty()))
/* 288:    */     {
/* 289:294 */       stopWalk(room);
/* 290:295 */       return;
/* 291:    */     }
/* 292:298 */     Square nextStep = (Square)this.walkPath.remove(0);
/* 293:299 */     if (!room.canWalk(this.liveEntity, nextStep.xy, (this.walkX == nextStep.x) && (this.walkY == nextStep.y)))
/* 294:    */     {
/* 295:301 */       if (this.walkPath.isEmpty())
/* 296:    */       {
/* 297:303 */         stopWalk(room);
/* 298:304 */         return;
/* 299:    */       }
/* 300:308 */       this.walkPath = null;
/* 301:309 */       findWalkPath(room);
/* 302:311 */       if ((this.walkPath == null) || (this.walkPath.isEmpty()))
/* 303:    */       {
/* 304:313 */         stopWalk(room);
/* 305:314 */         return;
/* 306:    */       }
/* 307:318 */       nextStep = (Square)this.walkPath.remove(0);
/* 308:    */     }
/* 309:321 */     Direction8 newRot = Direction8.getRot(this.liveEntity.x, this.liveEntity.y, nextStep.x, nextStep.y);
/* 310:322 */     if (this.liveEntity.entityType == 1)
/* 311:    */     {
/* 312:323 */       Avatar avatar = (Avatar)this.liveEntity;
/* 313:324 */       if (avatar.cn.haveFlag(32)) {
/* 314:325 */         newRot = newRot.rotateDirection180Degrees();
/* 315:    */       }
/* 316:    */     }
/* 317:329 */     this.nextZ = room.calculateZ(nextStep.xy);
/* 318:330 */     this.nextX = nextStep.x;
/* 319:331 */     this.nextY = nextStep.y;
/* 320:332 */     this.nextXY = nextStep.xy;
/* 321:    */     
/* 322:334 */     room.entityWalk(this.nextXY, this.liveEntity, true);
/* 323:335 */     room.entityWalk(this.liveEntity.xy, this.liveEntity, false);
/* 324:    */     
/* 325:337 */     this.liveEntity.RotBody = newRot;
/* 326:338 */     this.liveEntity.RotHead = newRot;
/* 327:339 */     if (this.liveEntity.ridingEntity != null)
/* 328:    */     {
/* 329:340 */       this.liveEntity.ridingEntity.RotBody = newRot;
/* 330:341 */       this.liveEntity.ridingEntity.RotHead = newRot;
/* 331:342 */       if (this.liveEntity.entityType == 2)
/* 332:    */       {
/* 333:343 */         this.liveEntity.ridingEntity.setStatus("mv", this.nextX + "," + this.nextY + "," + Float.toString(this.nextZ + 1.0F).replace(',', '.'));
/* 334:344 */         this.liveEntity.setStatus("mv", this.nextX + "," + this.nextY + "," + Float.toString(this.nextZ).replace(',', '.'));
/* 335:    */       }
/* 336:    */       else
/* 337:    */       {
/* 338:346 */         this.liveEntity.ridingEntity.setStatus("mv", this.nextX + "," + this.nextY + "," + Float.toString(this.nextZ).replace(',', '.'));
/* 339:347 */         this.liveEntity.setStatus("mv", this.nextX + "," + this.nextY + "," + Float.toString(this.nextZ + 1.0F).replace(',', '.'));
/* 340:    */       }
/* 341:    */     }
/* 342:    */     else
/* 343:    */     {
/* 344:350 */       this.liveEntity.setStatus("mv", this.nextX + "," + this.nextY + "," + Float.toString(this.nextZ).replace(',', '.'));
/* 345:351 */       if (this.liveEntity.entityType == 1) {
/* 346:352 */         checkEffects(room, (Avatar)this.liveEntity);
/* 347:    */       }
/* 348:    */     }
/* 349:356 */     this.doSteep = true;
/* 350:358 */     if (this.liveEntity.entityType == 1)
/* 351:    */     {
/* 352:359 */       Avatar avatar = (Avatar)this.liveEntity;
/* 353:361 */       if (room.squareFlag.eventHave(this.nextXY, 16))
/* 354:    */       {
/* 355:362 */         GenericFloorItem puckItem = (GenericFloorItem)room.topFloorItems.get(Integer.valueOf(this.nextXY));
/* 356:363 */         ItemTask.addTask(new PuckBanzai(puckItem, avatar, (this.walkX == nextStep.x) && (this.walkY == nextStep.y)), 50, 0);
/* 357:    */       }
/* 358:367 */       if (room.squareFlag.eventHave(this.nextXY, 32))
/* 359:    */       {
/* 360:368 */         GenericFloorItem ballItem = (GenericFloorItem)room.topFloorItems.get(Integer.valueOf(this.nextXY));
/* 361:369 */         ItemTask.addTask(new BallonFootBall(ballItem, avatar, (this.walkX == nextStep.x) && (this.walkY == nextStep.y)), 50, 0);
/* 362:    */       }
/* 363:    */     }
/* 364:373 */     this.Ticks = 0;
/* 365:    */   }
/* 366:    */   
/* 367:    */   private void stopWalk(RoomTask room)
/* 368:    */   {
/* 369:377 */     this.isWalking = false;
/* 370:    */     
/* 371:379 */     room.entityWalk(this.nextXY, this.liveEntity, false);
/* 372:380 */     room.entityWalk(this.liveEntity.xy, this.liveEntity, true);
/* 373:    */     
/* 374:382 */     this.liveEntity.allowOverride = false;
/* 375:383 */     if (this.liveEntity.Status.contains("mv")) {
/* 376:384 */       this.liveEntity.setStatus("", "");
/* 377:    */     }
/* 378:387 */     if ((this.liveEntity.ridingEntity != null) && 
/* 379:388 */       (this.liveEntity.ridingEntity.Status.contains("mv"))) {
/* 380:389 */       this.liveEntity.ridingEntity.setStatus("", "");
/* 381:    */     }
/* 382:    */   }
/* 383:    */   
/* 384:    */   public void walk(RoomTask room, int x, int y)
/* 385:    */   {
/* 386:395 */     this.walkX = x;
/* 387:396 */     this.walkY = y;
/* 388:    */     
/* 389:398 */     this.findPath = true;
/* 390:400 */     if (!this.isWalking)
/* 391:    */     {
/* 392:401 */       this.isWalking = true;
/* 393:402 */       room.addUserEvent(this, 0);
/* 394:    */     }
/* 395:    */   }
/* 396:    */   
/* 397:    */   public int getWalkX()
/* 398:    */   {
/* 399:407 */     return this.walkX;
/* 400:    */   }
/* 401:    */   
/* 402:    */   public void setWalkX(int walkX)
/* 403:    */   {
/* 404:411 */     this.walkX = walkX;
/* 405:    */   }
/* 406:    */   
/* 407:    */   public int getWalkY()
/* 408:    */   {
/* 409:415 */     return this.walkY;
/* 410:    */   }
/* 411:    */   
/* 412:    */   public void setWalkY(int walkY)
/* 413:    */   {
/* 414:419 */     this.walkY = walkY;
/* 415:    */   }
/* 416:    */   
/* 417:    */   public int getNextXY()
/* 418:    */   {
/* 419:423 */     return this.nextXY;
/* 420:    */   }
/* 421:    */   
/* 422:    */   public void setNextXY(int nextXY)
/* 423:    */   {
/* 424:427 */     this.nextXY = nextXY;
/* 425:    */   }
/* 426:    */   
/* 427:    */   public float getNextZ()
/* 428:    */   {
/* 429:431 */     return this.nextZ;
/* 430:    */   }
/* 431:    */   
/* 432:    */   public void setNextZ(float nextZ)
/* 433:    */   {
/* 434:435 */     this.nextZ = nextZ;
/* 435:    */   }
/* 436:    */   
/* 437:    */   public int getNextY()
/* 438:    */   {
/* 439:439 */     return this.nextY;
/* 440:    */   }
/* 441:    */   
/* 442:    */   public int setNextY(int nextY)
/* 443:    */   {
/* 444:443 */     this.nextY = nextY;
/* 445:444 */     return nextY;
/* 446:    */   }
/* 447:    */   
/* 448:    */   public int getNextX()
/* 449:    */   {
/* 450:448 */     return this.nextX;
/* 451:    */   }
/* 452:    */   
/* 453:    */   public int setNextX(int nextX)
/* 454:    */   {
/* 455:452 */     this.nextX = nextX;
/* 456:453 */     return nextX;
/* 457:    */   }
/* 458:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomevents.User_WALK
 * JD-Core Version:    0.7.0.1
 */