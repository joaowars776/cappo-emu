/*   1:    */ package cappo.game.roomengine.itemInteractor;
/*   2:    */ 
/*   3:    */ import cappo.engine.player.Connection;
/*   4:    */ import cappo.engine.threadpools.RoomTask;
/*   5:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*   6:    */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*   7:    */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*   8:    */ 
/*   9:    */ public abstract class Interactor
/*  10:    */ {
/*  11: 21 */   public static final Interactor iterWired = new InteractorWiredFurnis();
/*  12: 23 */   public static final Interactor iterDefault = new InteractorSimple();
/*  13: 24 */   public static final Interactor iterOutfit = new InteractorOutfit();
/*  14: 25 */   public static final Interactor iterTeleport = new InteractorTeleport();
/*  15: 26 */   public static final Interactor iterVendingMachine = new InteractorVendingMachine();
/*  16: 27 */   public static final Interactor iterOneWayGate = new InteractorOneWayGate();
/*  17: 28 */   public static final Interactor iterDice = new InteractorDice();
/*  18: 29 */   public static final Interactor iterHabboWheel = new InteractorHabboWheel();
/*  19: 30 */   public static final Interactor iterTimer = new InteractorTimer();
/*  20: 31 */   public static final Interactor iterJukebox = new InteractorTrax();
/*  21:    */   public abstract void OnPlace(RoomTask paramRoomTask, Connection paramConnection, GenericFloorItem paramGenericFloorItem);
/*  22:    */   
/*  23:    */   public abstract void OnPickUp(RoomTask paramRoomTask, Connection paramConnection, GenericFloorItem paramGenericFloorItem);
/*  24:    */   
/*  25:    */   public abstract void OnTriggerFloor(RoomTask paramRoomTask, Connection paramConnection, FloorItem paramFloorItem, int paramInt, boolean paramBoolean);
/*  26:    */   
/*  27:    */   public abstract void OnTriggerWall(RoomTask paramRoomTask, Connection paramConnection, GenericWallItem paramGenericWallItem, int paramInt, boolean paramBoolean);
/*  28:    */   
/*  29:    */   public static enum InteractorType
/*  30:    */   {
/*  31: 35 */     none,  gift,  postit,  walkeablechange,  roomeffect,  ecotron_box,  bed,  scoreboard,  vendingmachine,  alert,  onewaygate,  loveshuffler,  habbowheel,  dice,  bottle,  teleport,  rentals,  pet,  pool,  roller,  iceskates,  normslaskates,  lowpool,  haloweenpool,  football,  fbgate,  footballcountergreen,  footballcounteryellow,  footballcounterblue,  footballcounterred,  banzaigateblue,  banzaigatered,  banzaigateyellow,  banzaigategreen,  banzaifloor,  banzaiscoreblue,  banzaiscorered,  banzaiscoreyellow,  banzaiscoregreen,  banzaicounter,  banzaitele,  banzaipuck,  banzaipyramid,  freezetimer,  freezeexit,  freezeredcounter,  freezebluecounter,  freezeyellowcounter,  freezegreencounter,  freezeyellowgate,  freezeredgate,  freezegreengate,  freezebluegate,  freezetileblock,  freezetile,  jukebox,  puzzlebox,  triggertimer,  triggerroomenter,  triggergameend,  triggergamestart,  triggerrepeater,  triggeronusersay,  triggerscoreachieved,  triggerstatechanged,  triggerwalkonfurni,  triggerwalkofffurni,  actiongivescore,  actionposreset,  actionmoverotate,  actionresettimer,  actionshowmessage,  actionteleportto,  actiontogglestate,  actiongivereward,  conditionfurnishaveusers,  conditionstatepos,  conditiontimelessthan,  conditiontimemorethan,  conditiontriggeronfurni,  arrowplate,  preassureplate,  ringplate,  colortile,  colorwheel,  floorswitch1,  floorswitch2,  firegate,  glassfoor,  specialrandom,  specialunseen,  wire,  wireCenter,  wireCorner,  wireSplitter,  wireStandard;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public static InteractorType GetInteractorType(String type)
/*  35:    */   {
/*  36:148 */     String str = type;
/*  37:148 */     switch (type.hashCode())
/*  38:    */     {
/*  39:    */     case -2141127330: 
/*  40:148 */       if (str.equals("triggerwalkonfurni")) {}
/*  41:    */       break;
/*  42:    */     case -1974415303: 
/*  43:148 */       if (str.equals("banzaifloor")) {}
/*  44:    */       break;
/*  45:    */     case -1961628894: 
/*  46:148 */       if (str.equals("actionmoverotate")) {}
/*  47:    */       break;
/*  48:    */     case -1912538708: 
/*  49:148 */       if (str.equals("preassureplate")) {}
/*  50:    */       break;
/*  51:    */     case -1888928336: 
/*  52:148 */       if (str.equals("freezegreencounter")) {}
/*  53:    */       break;
/*  54:    */     case -1847014262: 
/*  55:148 */       if (str.equals("vendingmachine")) {}
/*  56:    */       break;
/*  57:    */     case -1779610334: 
/*  58:148 */       if (str.equals("conditiontimelessthan")) {}
/*  59:    */       break;
/*  60:    */     case -1750453726: 
/*  61:148 */       if (str.equals("freezeredcounter")) {}
/*  62:    */       break;
/*  63:    */     case -1714125651: 
/*  64:148 */       if (str.equals("arrowplate")) {}
/*  65:    */       break;
/*  66:    */     case -1646089487: 
/*  67:148 */       if (str.equals("triggergameend")) {}
/*  68:    */       break;
/*  69:    */     case -1608668439: 
/*  70:148 */       if (str.equals("banzaicounter")) {}
/*  71:    */       break;
/*  72:    */     case -1521861436: 
/*  73:148 */       if (str.equals("iceskates")) {}
/*  74:    */       break;
/*  75:    */     case -1470190104: 
/*  76:148 */       if (str.equals("freezetileblock")) {}
/*  77:    */       break;
/*  78:    */     case -1457966386: 
/*  79:148 */       if (str.equals("footballcountered")) {}
/*  80:    */       break;
/*  81:    */     case -1402553903: 
/*  82:148 */       if (str.equals("freezeyellowcounter")) {}
/*  83:    */       break;
/*  84:    */     case -1383228986: 
/*  85:148 */       if (str.equals("bottle")) {}
/*  86:    */       break;
/*  87:    */     case -1360201941: 
/*  88:148 */       if (str.equals("teleport")) {}
/*  89:    */       break;
/*  90:    */     case -1330923272: 
/*  91:148 */       if (str.equals("triggergamestart")) {}
/*  92:    */       break;
/*  93:    */     case -1281123449: 
/*  94:148 */       if (str.equals("fbgate")) {}
/*  95:    */       break;
/*  96:    */     case -1257323578: 
/*  97:148 */       if (str.equals("jukebox")) {}
/*  98:    */       break;
/*  99:    */     case -1158766137: 
/* 100:148 */       if (str.equals("actiontogglestate")) {}
/* 101:    */       break;
/* 102:    */     case -1158741005: 
/* 103:148 */       if (str.equals("banzaiscoreyellow")) {}
/* 104:    */       break;
/* 105:    */     case -1097324337: 
/* 106:148 */       if (str.equals("haloweenpool")) {}
/* 107:    */       break;
/* 108:    */     case -982450741: 
/* 109:148 */       if (str.equals("postit")) {}
/* 110:    */       break;
/* 111:    */     case -946436407: 
/* 112:148 */       if (str.equals("footballcounterblue")) {}
/* 113:    */       break;
/* 114:    */     case -926780484: 
/* 115:148 */       if (str.equals("freezebluegate")) {}
/* 116:    */       break;
/* 117:    */     case -925408790: 
/* 118:148 */       if (str.equals("roller")) {}
/* 119:    */       break;
/* 120:    */     case -853286284: 
/* 121:148 */       if (str.equals("actionshowmessage")) {}
/* 122:    */       break;
/* 123:    */     case -602354165: 
/* 124:148 */       if (str.equals("loveshuffler")) {}
/* 125:    */       break;
/* 126:    */     case -563202047: 
/* 127:148 */       if (str.equals("firegate")) {}
/* 128:    */       break;
/* 129:    */     case -556856645: 
/* 130:148 */       if (str.equals("triggerstatechanged")) {}
/* 131:    */       break;
/* 132:    */     case -528778476: 
/* 133:148 */       if (str.equals("onewaygate")) {}
/* 134:    */       break;
/* 135:    */     case -520553109: 
/* 136:148 */       if (str.equals("freezebluecounter")) {}
/* 137:    */       break;
/* 138:    */     case -474291984: 
/* 139:148 */       if (str.equals("triggerwalkofffurni")) {}
/* 140:    */       break;
/* 141:    */     case -461319686: 
/* 142:148 */       if (str.equals("ecotron_box")) {}
/* 143:    */       break;
/* 144:    */     case -409042419: 
/* 145:148 */       if (str.equals("triggerscoreachieved")) {}
/* 146:    */       break;
/* 147:    */     case -309811727: 
/* 148:148 */       if (str.equals("floorswitch1")) {}
/* 149:    */       break;
/* 150:    */     case -309811726: 
/* 151:148 */       if (str.equals("floorswitch2")) {}
/* 152:    */       break;
/* 153:    */     case -144166598: 
/* 154:148 */       if (str.equals("wireCenter")) {}
/* 155:    */       break;
/* 156:    */     case -134817990: 
/* 157:148 */       if (str.equals("wireCorner")) {}
/* 158:    */       break;
/* 159:    */     case -20121924: 
/* 160:148 */       if (str.equals("specialrandom")) {}
/* 161:    */       break;
/* 162:    */     case 0: 
/* 163:148 */       if (str.equals("")) {
/* 164:    */         break;
/* 165:    */       }
/* 166:    */       break;
/* 167:    */     case 97409: 
/* 168:148 */       if (str.equals("bed")) {}
/* 169:    */       break;
/* 170:    */     case 3083175: 
/* 171:148 */       if (str.equals("dice")) {}
/* 172:    */       break;
/* 173:    */     case 3165387: 
/* 174:148 */       if (str.equals("gate")) {}
/* 175:    */       break;
/* 176:    */     case 3172656: 
/* 177:148 */       if (str.equals("gift")) {}
/* 178:    */       break;
/* 179:    */     case 3446812: 
/* 180:148 */       if (str.equals("pool")) {}
/* 181:    */       break;
/* 182:    */     case 3649669: 
/* 183:148 */       if (str.equals("wire")) {}
/* 184:    */       break;
/* 185:    */     case 77920909: 
/* 186:148 */       if (str.equals("specialunseen")) {}
/* 187:    */       break;
/* 188:    */     case 92899676: 
/* 189:148 */       if (str.equals("alert")) {}
/* 190:    */       break;
/* 191:    */     case 206743774: 
/* 192:148 */       if (str.equals("conditiontimemorethan")) {}
/* 193:    */       break;
/* 194:    */     case 207015989: 
/* 195:148 */       if (str.equals("freezeexit")) {}
/* 196:    */       break;
/* 197:    */     case 207448517: 
/* 198:148 */       if (str.equals("freezetile")) {}
/* 199:    */       break;
/* 200:    */     case 316821222: 
/* 201:148 */       if (str.equals("ringplate")) {}
/* 202:    */       break;
/* 203:    */     case 357331312: 
/* 204:148 */       if (str.equals("lowpool")) {}
/* 205:    */       break;
/* 206:    */     case 394668909: 
/* 207:148 */       if (str.equals("football")) {}
/* 208:    */       break;
/* 209:    */     case 394888333: 
/* 210:148 */       if (str.equals("triggertimer")) {}
/* 211:    */       break;
/* 212:    */     case 445798672: 
/* 213:148 */       if (str.equals("conditionfurnishaveusers")) {}
/* 214:    */       break;
/* 215:    */     case 474572888: 
/* 216:148 */       if (str.equals("banzaigateblue")) {}
/* 217:    */       break;
/* 218:    */     case 683047486: 
/* 219:148 */       if (str.equals("conditionstatepos")) {}
/* 220:    */       break;
/* 221:    */     case 730023540: 
/* 222:148 */       if (str.equals("footballcountergreen")) {}
/* 223:    */       break;
/* 224:    */     case 776710065: 
/* 225:148 */       if (str.equals("actionposreset")) {}
/* 226:    */       break;
/* 227:    */     case 842217762: 
/* 228:148 */       if (str.equals("conditiontriggeronfurni")) {}
/* 229:    */       break;
/* 230:    */     case 876710021: 
/* 231:148 */       if (str.equals("habbowheel")) {}
/* 232:    */       break;
/* 233:    */     case 882405413: 
/* 234:148 */       if (str.equals("triggerroomenter")) {}
/* 235:    */       break;
/* 236:    */     case 906446688: 
/* 237:148 */       if (str.equals("banzaipuck")) {}
/* 238:    */       break;
/* 239:    */     case 906550749: 
/* 240:148 */       if (str.equals("banzaitele")) {}
/* 241:    */       break;
/* 242:    */     case 1034986393: 
/* 243:148 */       if (str.equals("banzaiscoreblue")) {}
/* 244:    */       break;
/* 245:    */     case 1058549280: 
/* 246:148 */       if (str.equals("normalskates")) {}
/* 247:    */       break;
/* 248:    */     case 1059905122: 
/* 249:148 */       if (str.equals("wireStandard")) {}
/* 250:    */       break;
/* 251:    */     case 1311486424: 
/* 252:148 */       if (str.equals("colorwheel")) {}
/* 253:    */       break;
/* 254:    */     case 1350956520: 
/* 255:148 */       if (str.equals("glassfoor")) {}
/* 256:    */       break;
/* 257:    */     case 1373541900: 
/* 258:148 */       if (str.equals("actionresettimer")) {}
/* 259:    */       break;
/* 260:    */     case 1429269036: 
/* 261:148 */       if (str.equals("roomeffect")) {}
/* 262:    */       break;
/* 263:    */     case 1445634455: 
/* 264:148 */       if (str.equals("freezegreengate")) {}
/* 265:    */       break;
/* 266:    */     case 1449759986: 
/* 267:148 */       if (str.equals("banzaigateyellow")) {}
/* 268:    */       break;
/* 269:    */     case 1463098422: 
/* 270:148 */       if (str.equals("actiongivereward")) {}
/* 271:    */       break;
/* 272:    */     case 1468938953: 
/* 273:148 */       if (str.equals("triggeronusersay")) {}
/* 274:    */       break;
/* 275:    */     case 1539344595: 
/* 276:148 */       if (str.equals("banzaigatered")) {}
/* 277:    */       break;
/* 278:    */     case 1544803905: 
/* 279:148 */       if (str.equals("default")) {
/* 280:    */         break;
/* 281:    */       }
/* 282:    */       break;
/* 283:    */     case 1546885532: 
/* 284:148 */       if (str.equals("actionteleportto")) {}
/* 285:    */       break;
/* 286:    */     case 1557422450: 
/* 287:148 */       if (str.equals("banzaiscorered")) {}
/* 288:    */       break;
/* 289:    */     case 1586494356: 
/* 290:148 */       if (str.equals("scoreboard")) {}
/* 291:    */       break;
/* 292:    */     case 1622071869: 
/* 293:148 */       if (str.equals("banzaipyramid")) {}
/* 294:    */       break;
/* 295:    */     case 1625719062: 
/* 296:148 */       if (str.equals("freezeyellowgate")) {}
/* 297:    */       break;
/* 298:    */     case 1659427619: 
/* 299:148 */       if (str.equals("footballcounteryellow")) {}
/* 300:    */       break;
/* 301:    */     case 1675812045: 
/* 302:148 */       if (str.equals("puzzlebox")) {}
/* 303:    */       break;
/* 304:    */     case 1831638725: 
/* 305:148 */       if (str.equals("banzaigategreen")) {}
/* 306:    */       break;
/* 307:    */     case 1842699584: 
/* 308:148 */       if (str.equals("triggerrepeater")) {}
/* 309:    */       break;
/* 310:    */     case 1981880465: 
/* 311:148 */       if (str.equals("colortile")) {}
/* 312:    */       break;
/* 313:    */     case 2024588196: 
/* 314:148 */       if (str.equals("banzaiscoregreen")) {}
/* 315:    */       break;
/* 316:    */     case 2039042661: 
/* 317:148 */       if (str.equals("freezeredgate")) {}
/* 318:    */       break;
/* 319:    */     case 2115655276: 
/* 320:148 */       if (str.equals("wireSplitter")) {}
/* 321:    */       break;
/* 322:    */     case 2126263467: 
/* 323:148 */       if (str.equals("actiongivescore")) {}
/* 324:    */       break;
/* 325:    */     case 2135937806: 
/* 326:148 */       if (!str.equals("freezetimer"))
/* 327:    */       {
/* 328:    */         break label2306;
/* 329:152 */         return InteractorType.none;
/* 330:    */         
/* 331:    */ 
/* 332:155 */         return InteractorType.gift;
/* 333:    */         
/* 334:157 */         return InteractorType.postit;
/* 335:    */         
/* 336:159 */         return InteractorType.roomeffect;
/* 337:    */         
/* 338:161 */         return InteractorType.ecotron_box;
/* 339:    */         
/* 340:163 */         return InteractorType.bed;
/* 341:    */         
/* 342:165 */         return InteractorType.scoreboard;
/* 343:    */         
/* 344:167 */         return InteractorType.vendingmachine;
/* 345:    */         
/* 346:169 */         return InteractorType.alert;
/* 347:    */         
/* 348:171 */         return InteractorType.onewaygate;
/* 349:    */         
/* 350:173 */         return InteractorType.loveshuffler;
/* 351:    */         
/* 352:175 */         return InteractorType.habbowheel;
/* 353:    */         
/* 354:177 */         return InteractorType.dice;
/* 355:    */         
/* 356:179 */         return InteractorType.bottle;
/* 357:    */         
/* 358:181 */         return InteractorType.teleport;
/* 359:    */         
/* 360:183 */         return InteractorType.pool;
/* 361:    */         
/* 362:185 */         return InteractorType.roller;
/* 363:    */         
/* 364:187 */         return InteractorType.iceskates;
/* 365:    */         
/* 366:189 */         return InteractorType.normslaskates;
/* 367:    */         
/* 368:191 */         return InteractorType.lowpool;
/* 369:    */         
/* 370:193 */         return InteractorType.haloweenpool;
/* 371:    */         
/* 372:    */ 
/* 373:196 */         return InteractorType.football;
/* 374:    */         
/* 375:    */ 
/* 376:199 */         return InteractorType.fbgate;
/* 377:    */         
/* 378:    */ 
/* 379:202 */         return InteractorType.footballcountergreen;
/* 380:    */         
/* 381:204 */         return InteractorType.footballcounteryellow;
/* 382:    */         
/* 383:206 */         return InteractorType.footballcounterblue;
/* 384:    */         
/* 385:208 */         return InteractorType.footballcounterred;
/* 386:    */         
/* 387:    */ 
/* 388:211 */         return InteractorType.banzaigateblue;
/* 389:    */         
/* 390:213 */         return InteractorType.banzaigatered;
/* 391:    */         
/* 392:215 */         return InteractorType.banzaigateyellow;
/* 393:    */         
/* 394:217 */         return InteractorType.banzaigategreen;
/* 395:    */         
/* 396:219 */         return InteractorType.banzaifloor;
/* 397:    */         
/* 398:    */ 
/* 399:222 */         return InteractorType.banzaiscoreblue;
/* 400:    */         
/* 401:224 */         return InteractorType.banzaiscorered;
/* 402:    */         
/* 403:226 */         return InteractorType.banzaiscoreyellow;
/* 404:    */         
/* 405:228 */         return InteractorType.banzaiscoregreen;
/* 406:    */         
/* 407:    */ 
/* 408:231 */         return InteractorType.banzaicounter;
/* 409:    */         
/* 410:233 */         return InteractorType.banzaitele;
/* 411:    */         
/* 412:235 */         return InteractorType.banzaipuck;
/* 413:    */         
/* 414:237 */         return InteractorType.banzaipyramid;
/* 415:    */       }
/* 416:    */       else
/* 417:    */       {
/* 418:240 */         return InteractorType.freezetimer;
/* 419:    */         
/* 420:242 */         return InteractorType.freezeexit;
/* 421:    */         
/* 422:244 */         return InteractorType.freezeredcounter;
/* 423:    */         
/* 424:246 */         return InteractorType.freezebluecounter;
/* 425:    */         
/* 426:248 */         return InteractorType.freezeyellowcounter;
/* 427:    */         
/* 428:250 */         return InteractorType.freezegreencounter;
/* 429:    */         
/* 430:252 */         return InteractorType.freezeyellowgate;
/* 431:    */         
/* 432:254 */         return InteractorType.freezeredgate;
/* 433:    */         
/* 434:256 */         return InteractorType.freezegreengate;
/* 435:    */         
/* 436:258 */         return InteractorType.freezebluegate;
/* 437:    */         
/* 438:260 */         return InteractorType.freezetileblock;
/* 439:    */         
/* 440:262 */         return InteractorType.freezetile;
/* 441:    */         
/* 442:264 */         return InteractorType.jukebox;
/* 443:    */         
/* 444:    */ 
/* 445:267 */         return InteractorType.triggertimer;
/* 446:    */         
/* 447:269 */         return InteractorType.triggerroomenter;
/* 448:    */         
/* 449:271 */         return InteractorType.triggergameend;
/* 450:    */         
/* 451:273 */         return InteractorType.triggergamestart;
/* 452:    */         
/* 453:275 */         return InteractorType.triggerrepeater;
/* 454:    */         
/* 455:277 */         return InteractorType.triggeronusersay;
/* 456:    */         
/* 457:279 */         return InteractorType.triggerscoreachieved;
/* 458:    */         
/* 459:281 */         return InteractorType.triggerstatechanged;
/* 460:    */         
/* 461:283 */         return InteractorType.triggerwalkonfurni;
/* 462:    */         
/* 463:285 */         return InteractorType.triggerwalkofffurni;
/* 464:    */         
/* 465:287 */         return InteractorType.actiongivescore;
/* 466:    */         
/* 467:289 */         return InteractorType.actionposreset;
/* 468:    */         
/* 469:291 */         return InteractorType.actionmoverotate;
/* 470:    */         
/* 471:293 */         return InteractorType.actionresettimer;
/* 472:    */         
/* 473:295 */         return InteractorType.actionshowmessage;
/* 474:    */         
/* 475:297 */         return InteractorType.actionteleportto;
/* 476:    */         
/* 477:299 */         return InteractorType.actiontogglestate;
/* 478:    */         
/* 479:301 */         return InteractorType.actiongivereward;
/* 480:    */         
/* 481:303 */         return InteractorType.conditionfurnishaveusers;
/* 482:    */         
/* 483:305 */         return InteractorType.conditionstatepos;
/* 484:    */         
/* 485:307 */         return InteractorType.conditiontimelessthan;
/* 486:    */         
/* 487:309 */         return InteractorType.conditiontimemorethan;
/* 488:    */         
/* 489:311 */         return InteractorType.conditiontriggeronfurni;
/* 490:    */         
/* 491:313 */         return InteractorType.arrowplate;
/* 492:    */         
/* 493:315 */         return InteractorType.preassureplate;
/* 494:    */         
/* 495:317 */         return InteractorType.ringplate;
/* 496:    */         
/* 497:319 */         return InteractorType.colortile;
/* 498:    */         
/* 499:321 */         return InteractorType.colorwheel;
/* 500:    */         
/* 501:323 */         return InteractorType.floorswitch1;
/* 502:    */         
/* 503:325 */         return InteractorType.floorswitch2;
/* 504:    */         
/* 505:327 */         return InteractorType.firegate;
/* 506:    */         
/* 507:329 */         return InteractorType.glassfoor;
/* 508:    */         
/* 509:331 */         return InteractorType.specialrandom;
/* 510:    */         
/* 511:333 */         return InteractorType.specialunseen;
/* 512:    */         
/* 513:335 */         return InteractorType.wire;
/* 514:    */         
/* 515:337 */         return InteractorType.wireCenter;
/* 516:    */         
/* 517:339 */         return InteractorType.wireCorner;
/* 518:    */         
/* 519:341 */         return InteractorType.wireSplitter;
/* 520:    */         
/* 521:343 */         return InteractorType.wireStandard;
/* 522:    */         
/* 523:345 */         return InteractorType.puzzlebox;
/* 524:    */         
/* 525:347 */         return InteractorType.walkeablechange;
/* 526:    */       }
/* 527:    */       break;
/* 528:    */     }
/* 529:    */     label2306:
/* 530:349 */     return InteractorType.none;
/* 531:    */   }
/* 532:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.itemInteractor.Interactor
 * JD-Core Version:    0.7.0.1
 */