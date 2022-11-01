/*   1:    */ package cappo.game.games.snowwar.gameobjects;
/*   2:    */ 
/*   3:    */ import cappo.engine.player.Connection;
/*   4:    */ import cappo.game.collections.BaseItem;
/*   5:    */ import cappo.game.collections.Utils;
/*   6:    */ import cappo.game.games.snowwar.Direction360;
/*   7:    */ import cappo.game.games.snowwar.Direction8;
/*   8:    */ import cappo.game.games.snowwar.GamefuseObject;
/*   9:    */ import cappo.game.games.snowwar.MathUtil;
/*  10:    */ import cappo.game.games.snowwar.PlayerTile;
/*  11:    */ import cappo.game.games.snowwar.SnowWar;
/*  12:    */ import cappo.game.games.snowwar.SnowWarArenaBase;
/*  13:    */ import cappo.game.games.snowwar.SnowWarGameStage;
/*  14:    */ import cappo.game.games.snowwar.SnowWarRoom;
/*  15:    */ import cappo.game.games.snowwar.SpawnPoint;
/*  16:    */ import cappo.game.games.snowwar.SynchronizedGameStage;
/*  17:    */ import cappo.game.games.snowwar.Tile;
/*  18:    */ import cappo.game.games.snowwar.gameevents.PickBallFromGameItem;
/*  19:    */ import cappo.game.player.SnowWarPlayerData;
/*  20:    */ import java.util.List;
/*  21:    */ 
/*  22:    */ public class HumanGameObject
/*  23:    */   extends GameItemObject
/*  24:    */ {
/*  25:    */   public static final int _302 = 534;
/*  26:    */   public static final int MAX_HEALTH = 5;
/*  27:    */   public static final int MAX_SNOWBALLS = 5;
/*  28:    */   public static final int PICKUPBALL_TIME = 20;
/*  29:    */   public static final int RESPAWNING_TIME = 100;
/*  30:    */   public static final int NODAMAGE_TIME = 60;
/*  31:    */   public static final int STATUS_NORMAL = 0;
/*  32:    */   public static final int STATUS_PICKUPBALL = 1;
/*  33:    */   public static final int STATUS_RESPAWNING = 2;
/*  34:    */   public static final int STATUS_NODAMAGE = 3;
/*  35:    */   public static final int BALLTHROW_FIRERATE = 5;
/*  36: 37 */   public static final int[] boundingData = { 1600 };
/*  37:    */   public static final int collisionHeight = 5000;
/*  38:    */   private static final int SCORE_KILL = 5;
/*  39:    */   private static final int SCORE_HIT = 1;
/*  40:    */   public SnowWarRoom currentSnowWar;
/*  41:    */   public SnowWarPlayerData snowWarPlayer;
/*  42:    */   public Connection cn;
/*  43:    */   public int userId;
/*  44:    */   public String userName;
/*  45:    */   public String look;
/*  46:    */   public String motto;
/*  47:    */   public int sex;
/*  48:    */   public int hits;
/*  49:    */   public int kills;
/*  50:    */   public int score;
/*  51:    */   public int status;
/*  52:    */   public int team;
/*  53:    */   private final PlayerTile currentLocation;
/*  54:    */   private final PlayerTile moveTarget;
/*  55:    */   public Direction8 humanDir;
/*  56:    */   public Tile currentTile;
/*  57:    */   private Tile nextTile;
/*  58: 68 */   private int health = 5;
/*  59: 69 */   private int snowBalls = 5;
/*  60:    */   protected int unused;
/*  61:    */   private int taskTime;
/*  62:    */   private int currentStatus;
/*  63:    */   private int fireRateLimiter;
/*  64:    */   private int pickUpLimiter;
/*  65:    */   public boolean stageLoaded;
/*  66:    */   
/*  67:    */   public HumanGameObject(SnowWarRoom room, int teamId)
/*  68:    */   {
/*  69: 82 */     super(19);
/*  70:    */     
/*  71: 84 */     this.currentSnowWar = room;
/*  72:    */     
/*  73: 86 */     this.currentLocation = new PlayerTile(0, 0, 0);
/*  74: 87 */     this.moveTarget = new PlayerTile(0, 0, 0);
/*  75: 88 */     this.humanDir = Direction8.SE;
/*  76:    */     
/*  77: 90 */     this.team = teamId;
/*  78:    */     SpawnPoint spawn;
/*  79:    */     SpawnPoint spawn;
/*  80: 93 */     if (teamId == 1)
/*  81:    */     {
/*  82: 94 */       int i = Utils.GetRandomNumber(0, this.currentSnowWar.ArenaType.spawnsBLUE.size() - 1);
/*  83: 95 */       spawn = (SpawnPoint)this.currentSnowWar.ArenaType.spawnsBLUE.get(i);
/*  84:    */     }
/*  85:    */     else
/*  86:    */     {
/*  87: 97 */       int i = Utils.GetRandomNumber(0, this.currentSnowWar.ArenaType.spawnsRED.size() - 1);
/*  88: 98 */       spawn = (SpawnPoint)this.currentSnowWar.ArenaType.spawnsRED.get(i);
/*  89:    */     }
/*  90:101 */     this.currentTile = this.currentSnowWar.map.getTile(spawn.x, spawn.y);
/*  91:    */     
/*  92:103 */     this.currentTile._1tH(this);
/*  93:104 */     this.currentLocation.setXYZ(this.currentTile.location());
/*  94:105 */     this.moveTarget.setXYZ(this.currentTile.location());
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void cleanData()
/*  98:    */   {
/*  99:109 */     this.snowWarPlayer.setRoom(null);
/* 100:110 */     this.snowWarPlayer.setHumanObject(null);
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void setCurLocation(int x, int y)
/* 104:    */   {
/* 105:114 */     this.currentSnowWar.checksum += x * 3 - getVariable(2) * 3;
/* 106:115 */     this.currentSnowWar.checksum += y * 4 - getVariable(3) * 4;
/* 107:116 */     this.currentLocation.setXY(x, y);
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void setCurLocation(PlayerTile val)
/* 111:    */   {
/* 112:120 */     this.currentSnowWar.checksum += val.x() * 3 - getVariable(2) * 3;
/* 113:121 */     this.currentSnowWar.checksum += val.y() * 4 - getVariable(3) * 4;
/* 114:122 */     this.currentLocation.setXYZ(val);
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void setCurrentTile(Tile val)
/* 118:    */   {
/* 119:126 */     this.currentSnowWar.checksum += val._4gH[0] * 5 - getVariable(4) * 5;
/* 120:127 */     this.currentSnowWar.checksum += val._4gH[1] * 6 - getVariable(5) * 6;
/* 121:128 */     if (this.nextTile == null)
/* 122:    */     {
/* 123:129 */       this.currentSnowWar.checksum += val._4gH[0] * 13 - getVariable(12) * 13;
/* 124:130 */       this.currentSnowWar.checksum += val._4gH[1] * 14 - getVariable(13) * 14;
/* 125:    */     }
/* 126:132 */     this.currentTile = val;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public void setTaskTime(int val)
/* 130:    */   {
/* 131:136 */     this.currentSnowWar.checksum += val * 11 - getVariable(10) * 11;
/* 132:137 */     this.taskTime = val;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public void setNextTile(Tile val)
/* 136:    */   {
/* 137:141 */     if (this.nextTile != null)
/* 138:    */     {
/* 139:142 */       if (val == null)
/* 140:    */       {
/* 141:143 */         this.currentSnowWar.checksum -= this.currentTile._4gH[0] * 13 - getVariable(12) * 13;
/* 142:144 */         this.currentSnowWar.checksum -= this.currentTile._4gH[1] * 14 - getVariable(13) * 14;
/* 143:    */       }
/* 144:    */       else
/* 145:    */       {
/* 146:146 */         this.currentSnowWar.checksum += val._4gH[0] * 13 - getVariable(12) * 13;
/* 147:147 */         this.currentSnowWar.checksum += val._4gH[1] * 14 - getVariable(13) * 14;
/* 148:    */       }
/* 149:    */     }
/* 150:149 */     else if (val != null)
/* 151:    */     {
/* 152:150 */       this.currentSnowWar.checksum += val._4gH[0] * 13 - getVariable(12) * 13;
/* 153:151 */       this.currentSnowWar.checksum += val._4gH[1] * 14 - getVariable(13) * 14;
/* 154:    */     }
/* 155:154 */     this.nextTile = val;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public void setRot(Direction8 val)
/* 159:    */   {
/* 160:158 */     this.currentSnowWar.checksum += val.getRot() * 7 - getVariable(6) * 7;
/* 161:159 */     this.humanDir = val;
/* 162:    */   }
/* 163:    */   
/* 164:    */   public void setHealth(int val)
/* 165:    */   {
/* 166:163 */     this.currentSnowWar.checksum += val * 8 - getVariable(7) * 8;
/* 167:164 */     this.health = val;
/* 168:    */   }
/* 169:    */   
/* 170:    */   public void setSnowBalls(int val)
/* 171:    */   {
/* 172:168 */     this.currentSnowWar.checksum += val * 9 - getVariable(8) * 9;
/* 173:169 */     this.snowBalls = val;
/* 174:    */   }
/* 175:    */   
/* 176:    */   public void setCurrentStatus(int val)
/* 177:    */   {
/* 178:173 */     this.currentSnowWar.checksum += val * 12 - getVariable(11) * 12;
/* 179:174 */     this.currentStatus = val;
/* 180:    */   }
/* 181:    */   
/* 182:    */   public void setMoveTarget(int x, int y)
/* 183:    */   {
/* 184:178 */     this.currentSnowWar.checksum += x * 15 - getVariable(14) * 15;
/* 185:179 */     this.currentSnowWar.checksum += y * 16 - getVariable(15) * 16;
/* 186:180 */     this.moveTarget.setXY(x, y);
/* 187:    */   }
/* 188:    */   
/* 189:    */   public void setMoveTarget(PlayerTile val)
/* 190:    */   {
/* 191:184 */     this.currentSnowWar.checksum += val.x() * 15 - getVariable(14) * 15;
/* 192:185 */     this.currentSnowWar.checksum += val.y() * 16 - getVariable(15) * 16;
/* 193:186 */     this.moveTarget.setXYZ(val);
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void setScore(int val)
/* 197:    */   {
/* 198:190 */     this.currentSnowWar.checksum += val * 17 - getVariable(16) * 17;
/* 199:191 */     this.score = val;
/* 200:    */   }
/* 201:    */   
/* 202:    */   public void setTeam(int val)
/* 203:    */   {
/* 204:195 */     this.currentSnowWar.checksum += val * 18 - getVariable(17) * 18;
/* 205:196 */     this.team = val;
/* 206:    */   }
/* 207:    */   
/* 208:    */   public int getVariable(int var)
/* 209:    */   {
/* 210:201 */     if (var == 0) {
/* 211:202 */       return 5;
/* 212:    */     }
/* 213:204 */     if (var == 1) {
/* 214:205 */       return this.objectId;
/* 215:    */     }
/* 216:207 */     if (var == 2) {
/* 217:208 */       return this.currentLocation.x();
/* 218:    */     }
/* 219:210 */     if (var == 3) {
/* 220:211 */       return this.currentLocation.y();
/* 221:    */     }
/* 222:213 */     if (var == 4) {
/* 223:214 */       return this.currentTile._4gH[0];
/* 224:    */     }
/* 225:216 */     if (var == 5) {
/* 226:217 */       return this.currentTile._4gH[1];
/* 227:    */     }
/* 228:219 */     if (var == 6) {
/* 229:220 */       return this.humanDir.getRot();
/* 230:    */     }
/* 231:222 */     if (var == 7) {
/* 232:223 */       return this.health;
/* 233:    */     }
/* 234:225 */     if (var == 8) {
/* 235:226 */       return this.snowBalls;
/* 236:    */     }
/* 237:228 */     if (var == 9) {
/* 238:229 */       return this.unused;
/* 239:    */     }
/* 240:231 */     if (var == 10) {
/* 241:232 */       return this.taskTime;
/* 242:    */     }
/* 243:234 */     if (var == 11) {
/* 244:235 */       return this.currentStatus;
/* 245:    */     }
/* 246:237 */     if (var == 12) {
/* 247:238 */       return this.nextTile != null ? this.nextTile._4gH[0] : this.currentTile._4gH[0];
/* 248:    */     }
/* 249:240 */     if (var == 13) {
/* 250:241 */       return this.nextTile != null ? this.nextTile._4gH[1] : this.currentTile._4gH[1];
/* 251:    */     }
/* 252:243 */     if (var == 14) {
/* 253:244 */       return this.moveTarget.x();
/* 254:    */     }
/* 255:246 */     if (var == 15) {
/* 256:247 */       return this.moveTarget.y();
/* 257:    */     }
/* 258:249 */     if (var == 16) {
/* 259:250 */       return this.score;
/* 260:    */     }
/* 261:252 */     if (var == 17) {
/* 262:253 */       return this.team;
/* 263:    */     }
/* 264:256 */     return this.userId;
/* 265:    */   }
/* 266:    */   
/* 267:    */   public int[] boundingData()
/* 268:    */   {
/* 269:262 */     return boundingData;
/* 270:    */   }
/* 271:    */   
/* 272:    */   public PlayerTile location3D()
/* 273:    */   {
/* 274:267 */     return this.currentLocation;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public Direction360 direction360()
/* 278:    */   {
/* 279:272 */     return null;
/* 280:    */   }
/* 281:    */   
/* 282:    */   public void doCurrentTask()
/* 283:    */   {
/* 284:276 */     if (this.currentStatus == 2)
/* 285:    */     {
/* 286:277 */       setHealth(5);
/* 287:278 */       setCurrentStatus(3);
/* 288:279 */       setTaskTime(60);
/* 289:280 */       return;
/* 290:    */     }
/* 291:282 */     if (this.currentStatus == 1) {
/* 292:283 */       setSnowBalls(this.snowBalls + 1);
/* 293:    */     }
/* 294:285 */     setCurrentStatus(0);
/* 295:    */   }
/* 296:    */   
/* 297:    */   public void subturn(SynchronizedGameStage unused)
/* 298:    */   {
/* 299:290 */     if (this.taskTime > 0)
/* 300:    */     {
/* 301:291 */       if (this.taskTime == 1) {
/* 302:292 */         doCurrentTask();
/* 303:    */       }
/* 304:294 */       setTaskTime(this.taskTime - 1);
/* 305:    */     }
/* 306:297 */     if (this.fireRateLimiter > 0) {
/* 307:298 */       this.fireRateLimiter -= 1;
/* 308:    */     }
/* 309:300 */     if (this.pickUpLimiter > 0) {
/* 310:301 */       this.pickUpLimiter -= 1;
/* 311:    */     }
/* 312:306 */     if ((canWalk()) && (this.currentTile != null)) {
/* 313:307 */       if (this.nextTile != null)
/* 314:    */       {
/* 315:309 */         doWalkSteep();
/* 316:    */       }
/* 317:    */       else
/* 318:    */       {
/* 319:311 */         if ((this.pickUpLimiter < 1) && 
/* 320:312 */           (this.currentTile.pickBallsItem != null) && (this.currentTile.pickBallsItem.canPickUpFromHere()))
/* 321:    */         {
/* 322:313 */           this.currentTile.pickBallsItem.concurrentUses += 1;
/* 323:314 */           this.pickUpLimiter = 20;
/* 324:315 */           synchronized (this.currentSnowWar.gameEvents)
/* 325:    */           {
/* 326:316 */             this.currentSnowWar.gameEvents.add(new PickBallFromGameItem(this, this.currentTile.pickBallsItem));
/* 327:    */           }
/* 328:    */         }
/* 329:321 */         if (!this.currentTile.isTooAway(this.moveTarget))
/* 330:    */         {
/* 331:322 */           int rot = Direction360.getRot(this.moveTarget.x() - this.currentTile.location().x(), this.moveTarget.y() - this.currentTile.location().y());
/* 332:323 */           Direction8 direction = Direction360.direction360ValueToDirection8(rot);
/* 333:324 */           setNextTile(this.currentTile.getNextTileAtRot(direction));
/* 334:325 */           if ((this.nextTile == null) || (!this.nextTile.isOpen(this)))
/* 335:    */           {
/* 336:326 */             if ((this.nextTile != null) && (!this.nextTile.isOpen(this)) && 
/* 337:327 */               (this.moveTarget.isSamePosition(this.nextTile.location())))
/* 338:    */             {
/* 339:328 */               setNextTile(null);
/* 340:329 */               stopWalk();
/* 341:330 */               return;
/* 342:    */             }
/* 343:333 */             direction = direction.getDirectionAtRot(-1);
/* 344:334 */             setNextTile(this.currentTile.getNextTileAtRot(direction));
/* 345:335 */             if ((this.nextTile == null) || (!this.nextTile.isOpen(this)))
/* 346:    */             {
/* 347:336 */               direction = direction.getDirectionAtRot(2);
/* 348:337 */               setNextTile(this.currentTile.getNextTileAtRot(direction));
/* 349:338 */               if ((this.nextTile == null) || (!this.nextTile.isOpen(this)))
/* 350:    */               {
/* 351:339 */                 setNextTile(null);
/* 352:340 */                 stopWalk();
/* 353:341 */                 return;
/* 354:    */               }
/* 355:    */             }
/* 356:    */           }
/* 357:345 */           if (this.nextTile != null)
/* 358:    */           {
/* 359:346 */             this.currentTile._40T();
/* 360:347 */             this.nextTile._1tH(this);
/* 361:348 */             setRot(direction);
/* 362:349 */             doWalkSteep();
/* 363:    */           }
/* 364:    */         }
/* 365:    */       }
/* 366:    */     }
/* 367:    */   }
/* 368:    */   
/* 369:    */   public boolean canWalkTo(int walkX, int walkY)
/* 370:    */   {
/* 371:359 */     if ((!canWalk()) || (this.currentTile == null)) {
/* 372:360 */       return false;
/* 373:    */     }
/* 374:363 */     Tile localCurrent = this.currentTile;
/* 375:364 */     PlayerTile localTarget = new PlayerTile(walkX, walkY, 0);
/* 376:367 */     for (int i = 0; i < 50; i++)
/* 377:    */     {
/* 378:368 */       int rot = Direction360.getRot(localTarget.x() - localCurrent.location().x(), localTarget.y() - localCurrent.location().y());
/* 379:369 */       Direction8 direction = Direction360.direction360ValueToDirection8(rot);
/* 380:370 */       Tile localNext = localCurrent.getNextTileAtRot(direction);
/* 381:371 */       if ((localNext == null) || (!localNext.isOpen(this)))
/* 382:    */       {
/* 383:372 */         if ((localNext != null) && (!localNext.isOpen(this))) {
/* 384:374 */           if (localTarget.isSamePosition(localNext.location())) {
/* 385:375 */             return true;
/* 386:    */           }
/* 387:    */         }
/* 388:378 */         direction = direction.getDirectionAtRot(-1);
/* 389:379 */         localNext = localCurrent.getNextTileAtRot(direction);
/* 390:380 */         if ((localNext == null) || (!localNext.isOpen(this)))
/* 391:    */         {
/* 392:381 */           direction = direction.getDirectionAtRot(2);
/* 393:382 */           localNext = localCurrent.getNextTileAtRot(direction);
/* 394:383 */           if ((localNext == null) || (!localNext.isOpen(this))) {
/* 395:384 */             return false;
/* 396:    */           }
/* 397:    */         }
/* 398:    */       }
/* 399:389 */       localCurrent = localNext;
/* 400:390 */       localNext = null;
/* 401:393 */       if (localCurrent.isTooAway(localTarget)) {
/* 402:394 */         return true;
/* 403:    */       }
/* 404:    */     }
/* 405:398 */     return false;
/* 406:    */   }
/* 407:    */   
/* 408:    */   private void doFire()
/* 409:    */   {
/* 410:402 */     int pow = 15;
/* 411:    */     
/* 412:    */ 
/* 413:    */ 
/* 414:    */ 
/* 415:    */ 
/* 416:408 */     Tile tile = this.currentTile.getNextTileAtRot(this.humanDir);
/* 417:409 */     if ((this.humanDir == Direction8.N) || (this.humanDir == Direction8.W)) {
/* 418:410 */       tile = tile.getNextTileAtRot(this.humanDir);
/* 419:    */     }
/* 420:413 */     if (tile == null) {
/* 421:414 */       return;
/* 422:    */     }
/* 423:417 */     List<GamefuseObject> items = tile.fuseObjects();
/* 424:418 */     if (items.size() == 1)
/* 425:    */     {
/* 426:419 */       GamefuseObject item = (GamefuseObject)items.get(0);
/* 427:420 */       Direction8 dir = Direction8.getDirection(item.Rot);
/* 428:421 */       if ((dir == this.humanDir) && (item.baseItem.Name.equals("ads_igorraygun"))) {
/* 429:422 */         this.snowWarPlayer.throwSnowballFlood(item.X + dir.getDiffX() * pow, item.Y + dir.getDiffY() * pow);
/* 430:    */       }
/* 431:    */     }
/* 432:    */   }
/* 433:    */   
/* 434:    */   public void cleanTiles()
/* 435:    */   {
/* 436:428 */     if ((this.currentTile != null) && (this.currentTile._05Z() == this)) {
/* 437:429 */       this.currentTile._40T();
/* 438:    */     }
/* 439:431 */     if ((this.nextTile != null) && (this.nextTile._05Z() == this)) {
/* 440:432 */       this.nextTile._40T();
/* 441:    */     }
/* 442:    */   }
/* 443:    */   
/* 444:    */   public void onRemove()
/* 445:    */   {
/* 446:438 */     cleanTiles();
/* 447:440 */     if (this.snowWarPlayer != null) {
/* 448:441 */       cleanData();
/* 449:    */     }
/* 450:    */   }
/* 451:    */   
/* 452:    */   private void doWalkSteep()
/* 453:    */   {
/* 454:447 */     int local0 = this.nextTile.location().x();
/* 455:448 */     int local1 = this.currentLocation.x();
/* 456:449 */     int local2 = local1 - local0;
/* 457:450 */     if (local2 != 0) {
/* 458:451 */       if (local2 < 0)
/* 459:    */       {
/* 460:452 */         if (local2 > -534) {
/* 461:453 */           local1 = local0;
/* 462:    */         } else {
/* 463:455 */           local1 += 534;
/* 464:    */         }
/* 465:    */       }
/* 466:458 */       else if (local2 < 534) {
/* 467:459 */         local1 = local0;
/* 468:    */       } else {
/* 469:461 */         local1 -= 534;
/* 470:    */       }
/* 471:    */     }
/* 472:465 */     int local3 = this.nextTile.location().y();
/* 473:466 */     int local4 = this.currentLocation.y();
/* 474:467 */     int local5 = local4 - local3;
/* 475:468 */     if (local5 != 0) {
/* 476:469 */       if (local5 < 0)
/* 477:    */       {
/* 478:470 */         if (local5 > -534) {
/* 479:471 */           local4 = local3;
/* 480:    */         } else {
/* 481:473 */           local4 += 534;
/* 482:    */         }
/* 483:    */       }
/* 484:476 */       else if (local5 < 534) {
/* 485:477 */         local4 = local3;
/* 486:    */       } else {
/* 487:479 */         local4 -= 534;
/* 488:    */       }
/* 489:    */     }
/* 490:484 */     setCurLocation(local1, local4);
/* 491:486 */     if (this.currentLocation.distanceTo(this.nextTile.location()) < MathUtil._43Z(267.0D))
/* 492:    */     {
/* 493:487 */       if (this.moveTarget.isSamePosition(this.nextTile.location())) {
/* 494:488 */         doFire();
/* 495:    */       }
/* 496:491 */       setCurrentTile(this.nextTile);
/* 497:492 */       setNextTile(null);
/* 498:    */     }
/* 499:    */   }
/* 500:    */   
/* 501:    */   public void setMove(int x, int y)
/* 502:    */   {
/* 503:497 */     if (this.currentStatus == 1)
/* 504:    */     {
/* 505:498 */       setCurrentStatus(0);
/* 506:499 */       setTaskTime(0);
/* 507:    */     }
/* 508:501 */     if ((this.currentStatus == 0) || (this.currentStatus == 3)) {
/* 509:502 */       setMoveTarget(x, y);
/* 510:    */     }
/* 511:    */   }
/* 512:    */   
/* 513:    */   public void decrementHealth(HumanGameObject attacker, int rot)
/* 514:    */   {
/* 515:507 */     if (this.team == attacker.team) {
/* 516:508 */       return;
/* 517:    */     }
/* 518:511 */     if (this.health > 0)
/* 519:    */     {
/* 520:512 */       if (this.health == 1)
/* 521:    */       {
/* 522:513 */         doKill(rot);
/* 523:514 */         attacker.giveScorePerKill(this);
/* 524:    */       }
/* 525:516 */       setHealth(this.health - 1);
/* 526:    */     }
/* 527:    */   }
/* 528:    */   
/* 529:    */   public void giveScorePerHit(HumanGameObject _arg2)
/* 530:    */   {
/* 531:521 */     if ((this.team != _arg2.team) || (SnowWar.TEAMS.length == 1))
/* 532:    */     {
/* 533:522 */       this.hits += 1;
/* 534:523 */       giveScore(1);
/* 535:    */     }
/* 536:    */   }
/* 537:    */   
/* 538:    */   public void giveScorePerKill(HumanGameObject _arg2)
/* 539:    */   {
/* 540:528 */     if ((this.team != _arg2.team) || (SnowWar.TEAMS.length == 1))
/* 541:    */     {
/* 542:529 */       this.kills += 1;
/* 543:530 */       giveScore(5);
/* 544:    */     }
/* 545:    */   }
/* 546:    */   
/* 547:    */   public void giveScore(int _arg2)
/* 548:    */   {
/* 549:535 */     setScore(this.score + _arg2);
/* 550:536 */     this.currentSnowWar.TeamScore[(this.team - 1)] += _arg2;
/* 551:    */   }
/* 552:    */   
/* 553:    */   public void doKill(int _arg1)
/* 554:    */   {
/* 555:540 */     setCurrentStatus(2);
/* 556:541 */     setTaskTime(100);
/* 557:542 */     setRot(Direction360.direction360ValueToDirection8(_arg1).rotateDirection180Degrees());
/* 558:543 */     stopWalk();
/* 559:    */   }
/* 560:    */   
/* 561:    */   public void stopWalk()
/* 562:    */   {
/* 563:547 */     if (this.nextTile == null)
/* 564:    */     {
/* 565:548 */       setMoveTarget(this.currentTile.location());
/* 566:549 */       setCurLocation(this.currentTile.location());
/* 567:    */     }
/* 568:    */     else
/* 569:    */     {
/* 570:551 */       setCurrentTile(this.nextTile);
/* 571:552 */       setCurLocation(this.nextTile.location());
/* 572:553 */       setMoveTarget(this.nextTile.location());
/* 573:554 */       setNextTile(null);
/* 574:    */     }
/* 575:    */   }
/* 576:    */   
/* 577:    */   public boolean canThrowSnowBall()
/* 578:    */   {
/* 579:560 */     return (this.snowBalls > 0) && (this.fireRateLimiter < 1) && ((this.currentStatus == 0) || (this.currentStatus == 3));
/* 580:    */   }
/* 581:    */   
/* 582:    */   public void _w1()
/* 583:    */   {
/* 584:564 */     this.fireRateLimiter = 5;
/* 585:    */   }
/* 586:    */   
/* 587:    */   public boolean _vs(int victimX, int victimY)
/* 588:    */   {
/* 589:568 */     if (this.snowBalls < 1) {
/* 590:569 */       return false;
/* 591:    */     }
/* 592:571 */     stopWalk();
/* 593:572 */     int local2 = Direction360.getRot(victimX - this.currentLocation.x(), victimY - this.currentLocation.y());
/* 594:573 */     int local3 = Direction360.direction360ValueToDirection8(local2).getRot();
/* 595:574 */     setRot(Direction8.getDirection(local3));
/* 596:    */     
/* 597:576 */     setSnowBalls(this.snowBalls - 1);
/* 598:577 */     return true;
/* 599:    */   }
/* 600:    */   
/* 601:    */   public boolean canWalk()
/* 602:    */   {
/* 603:581 */     return (this.currentStatus == 0) || (this.currentStatus == 3);
/* 604:    */   }
/* 605:    */   
/* 606:    */   public boolean canPickSnowBalls()
/* 607:    */   {
/* 608:585 */     return ((this.currentStatus == 0) || (this.currentStatus == 3)) && (this.snowBalls < 5);
/* 609:    */   }
/* 610:    */   
/* 611:    */   public void makeSnowBall()
/* 612:    */   {
/* 613:589 */     if (canPickSnowBalls())
/* 614:    */     {
/* 615:590 */       setCurrentStatus(1);
/* 616:591 */       setTaskTime(20);
/* 617:592 */       stopWalk();
/* 618:    */     }
/* 619:    */   }
/* 620:    */   
/* 621:    */   public int availableSnowBallSlots()
/* 622:    */   {
/* 623:597 */     return 5 - this.snowBalls;
/* 624:    */   }
/* 625:    */   
/* 626:    */   public void addSnowBalls(int val)
/* 627:    */   {
/* 628:601 */     setSnowBalls(this.snowBalls + val);
/* 629:    */   }
/* 630:    */   
/* 631:    */   public boolean testSnowBallCollision(SnowBallGameObject snowBall)
/* 632:    */   {
/* 633:606 */     if ((this.currentStatus == 2) || (this.currentStatus == 3) || (snowBall.getAttacker() == this)) {
/* 634:607 */       return false;
/* 635:    */     }
/* 636:609 */     return super.testSnowBallCollision(snowBall);
/* 637:    */   }
/* 638:    */   
/* 639:    */   public void onSnowBallHit(SnowBallGameObject snowBall)
/* 640:    */   {
/* 641:614 */     HumanGameObject attacker = snowBall.getAttacker();
/* 642:615 */     decrementHealth(attacker, snowBall.direction360()._2Hq());
/* 643:616 */     attacker.giveScorePerHit(this);
/* 644:    */   }
/* 645:    */   
/* 646:    */   public int collisionHeight()
/* 647:    */   {
/* 648:621 */     return 5000;
/* 649:    */   }
/* 650:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameobjects.HumanGameObject
 * JD-Core Version:    0.7.0.1
 */