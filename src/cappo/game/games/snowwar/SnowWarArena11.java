/*   1:    */ package cappo.game.games.snowwar;
/*   2:    */ 
/*   3:    */ import cappo.game.collections.BaseItem;
/*   4:    */ import cappo.game.games.snowwar.gameobjects.GameItemObject;
/*   5:    */ import cappo.game.games.snowwar.gameobjects.PileGameObject;
/*   6:    */ import cappo.game.games.snowwar.gameobjects.TreeGameObject;
/*   7:    */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*   8:    */ import cappo.game.roomengine.entity.item.extradata.MapStuffData;
/*   9:    */ import java.util.List;
/*  10:    */ import java.util.Map;
/*  11:    */ 
/*  12:    */ public class SnowWarArena11
/*  13:    */   extends SnowWarArenaBase
/*  14:    */ {
/*  15:    */   public SnowWarArena11()
/*  16:    */   {
/*  17: 21 */     this.ArenaType = 11;
/*  18: 22 */     this.ArenaWidth = 50;
/*  19: 23 */     this.ArenaHeight = 50;
/*  20: 24 */     this.HeightMap = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxx00000000xxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxx00000000000xxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxx000000000000000xxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxx00000000000000000xxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxx0000000000000000000xxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxx000000000000000000000xxxxxxxxxxxxxxxxxx\rxxxxxxxxxx00000000000000000000000xxxxxxxxxxxxxxxxx\rxxxxxxxxx0000000000000000000000000xxxxxxxxxxxxxxxx\rxxxxxxxx000000000000000000000000000xxxxxxxxxxxxxxx\rxxxxxxx00000000000000000000000000000xxxxxxxxxxxxxx\rxxxxxx0000000000000000000000000000000xxxxxxxxxxxxx\rxxxxx000000000000000000000000000000000xxxxxxxxxxxx\rxxxxx0000000000000000000000000000000000xxxxxxxxxxx\rxxxxx00000000000000000000000000000000000xxxxxxxxxx\rxxxxx000000000000000000000000000000000000xxxxxxxxx\rxxxx00000000000000000000000000000000000000xxxxxxxx\rxxxx000000000000000000000000000000000000000xxxxxxx\rxxxx0000000000000000000000000000000000000000xxxxxx\rxxxx00000000000000000000000000000000000000000xxxxx\r0xxx000000000000000000000000000000000000000000xxxx\rxxxx000000000000000000000000000000000000000000xxxx\rxxxx0000000000000000000000000000000000000000000xxx\rxxxx0000000000000000000000000000000000000000000xxx\rxxxx0000000000000000000000000000000000000000000xxx\rxxxxx000000000000000000000000000000000000000000xxx\rxxxxxx00000000000000000000000000000000000000000xxx\rxxxxxxx0000000000000000000000000000000000000000xxx\rxxxxxxxx000000000000000000000000000000000000000xxx\rxxxxxxxxx0000000000000000000000000000000000000xxxx\rxxxxxxxxxx000000000000000000000000000000000000xxxx\rxxxxxxxxxxx0000000000000000000000000000000000xxxxx\rxxxxxxxxxxxx00000000000000000000000000000000xxxxxx\rxxxxxxxxxxxxx000000000000000000000000000000xxxxxxx\rxxxxxxxxxxxxxx0000000000000000000000000000xxxxxxxx\rxxxxxxxxxxxxxxx00000000000000000000000000xxxxxxxxx\rxxxxxxxxxxxxxxxx0000000000000000000000000xxxxxxxxx\rxxxxxxxxxxxxxxxxx00000000000000000000000xxxxxxxxxx\rxxxxxxxxxxxxxxxxxx0000000000000000000000xxxxxxxxxx\rxxxxxxxxxxxxxxxxxxx00000000000000000000xxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxx000000000000000000xxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxx0000000000000000xxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxx0000000000000xxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxx000000000xxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxx000000xxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r";
/*  21:    */     
/*  22:    */ 
/*  23:    */ 
/*  24:    */ 
/*  25:    */ 
/*  26:    */ 
/*  27:    */ 
/*  28:    */ 
/*  29:    */ 
/*  30:    */ 
/*  31:    */ 
/*  32:    */ 
/*  33:    */ 
/*  34:    */ 
/*  35:    */ 
/*  36:    */ 
/*  37:    */ 
/*  38:    */ 
/*  39:    */ 
/*  40:    */ 
/*  41:    */ 
/*  42:    */ 
/*  43:    */ 
/*  44:    */ 
/*  45:    */ 
/*  46:    */ 
/*  47:    */ 
/*  48:    */ 
/*  49:    */ 
/*  50:    */ 
/*  51:    */ 
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55:    */ 
/*  56:    */ 
/*  57:    */ 
/*  58:    */ 
/*  59:    */ 
/*  60:    */ 
/*  61:    */ 
/*  62:    */ 
/*  63:    */ 
/*  64:    */ 
/*  65:    */ 
/*  66:    */ 
/*  67:    */ 
/*  68:    */ 
/*  69:    */ 
/*  70:    */ 
/*  71:    */ 
/*  72: 76 */     GamefuseObject Item = new GamefuseObject();
/*  73: 77 */     Item.baseItem = BaseItem.snst_tree1_d;
/*  74: 78 */     Item.baseItem.allowWalk = false;
/*  75: 79 */     Item.baseItem.Height = 1.0F;
/*  76: 80 */     Item.itemId = 0;
/*  77: 81 */     Item.X = 29;
/*  78: 82 */     Item.Y = 23;
/*  79: 83 */     Item.Rot = 0;
/*  80: 84 */     Item.Z = 0;
/*  81: 85 */     Item.extraData.setExtraData("0");
/*  82: 86 */     this.fuseObjects.add(Item);
/*  83:    */     
/*  84: 88 */     Item = new GamefuseObject();
/*  85: 89 */     Item.baseItem = BaseItem.snst_tree1_d;
/*  86: 90 */     Item.baseItem.allowWalk = false;
/*  87: 91 */     Item.baseItem.Height = 1.0F;
/*  88: 92 */     Item.itemId = 1;
/*  89: 93 */     Item.X = 11;
/*  90: 94 */     Item.Y = 16;
/*  91: 95 */     Item.Rot = 2;
/*  92: 96 */     Item.Z = 0;
/*  93: 97 */     Item.extraData.setExtraData("0");
/*  94: 98 */     this.fuseObjects.add(Item);
/*  95:    */     
/*  96:100 */     Item = new GamefuseObject();
/*  97:101 */     Item.baseItem = BaseItem.snst_tree1_d;
/*  98:102 */     Item.baseItem.allowWalk = false;
/*  99:103 */     Item.baseItem.Height = 1.0F;
/* 100:104 */     Item.itemId = 2;
/* 101:105 */     Item.X = 26;
/* 102:106 */     Item.Y = 28;
/* 103:107 */     Item.Rot = 2;
/* 104:108 */     Item.Z = 0;
/* 105:109 */     Item.extraData.setExtraData("0");
/* 106:110 */     this.fuseObjects.add(Item);
/* 107:    */     
/* 108:112 */     Item = new GamefuseObject();
/* 109:113 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 110:114 */     Item.baseItem.allowWalk = false;
/* 111:115 */     Item.baseItem.Height = 1.0F;
/* 112:116 */     Item.itemId = 3;
/* 113:117 */     Item.X = 31;
/* 114:118 */     Item.Y = 42;
/* 115:119 */     Item.Rot = 2;
/* 116:120 */     Item.Z = 0;
/* 117:121 */     Item.extraData.setExtraData("0");
/* 118:122 */     this.fuseObjects.add(Item);
/* 119:    */     
/* 120:124 */     Item = new GamefuseObject();
/* 121:125 */     Item.baseItem = BaseItem.snst_ballpile;
/* 122:126 */     Item.baseItem.allowWalk = true;
/* 123:127 */     Item.baseItem.Height = 0.0F;
/* 124:128 */     Item.itemId = 4;
/* 125:129 */     Item.X = 27;
/* 126:130 */     Item.Y = 16;
/* 127:131 */     Item.Rot = 0;
/* 128:132 */     Item.Z = 0;
/* 129:133 */     Item.extraData.setExtraData("0");
/* 130:134 */     this.fuseObjects.add(Item);
/* 131:    */     
/* 132:136 */     Item = new GamefuseObject();
/* 133:137 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 134:138 */     Item.baseItem.allowWalk = false;
/* 135:139 */     Item.baseItem.Height = 1.0F;
/* 136:140 */     Item.itemId = 5;
/* 137:141 */     Item.X = 32;
/* 138:142 */     Item.Y = 10;
/* 139:143 */     Item.Rot = 0;
/* 140:144 */     Item.Z = 0;
/* 141:145 */     Item.extraData.setExtraData("1");
/* 142:146 */     this.fuseObjects.add(Item);
/* 143:    */     
/* 144:148 */     Item = new GamefuseObject();
/* 145:149 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 146:150 */     Item.baseItem.allowWalk = false;
/* 147:151 */     Item.baseItem.Height = 1.0F;
/* 148:152 */     Item.itemId = 6;
/* 149:153 */     Item.X = 13;
/* 150:154 */     Item.Y = 21;
/* 151:155 */     Item.Rot = 2;
/* 152:156 */     Item.Z = 0;
/* 153:157 */     Item.extraData.setExtraData("0");
/* 154:158 */     this.fuseObjects.add(Item);
/* 155:    */     
/* 156:160 */     Item = new GamefuseObject();
/* 157:161 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 158:162 */     Item.baseItem.allowWalk = false;
/* 159:163 */     Item.baseItem.Height = 1.0F;
/* 160:164 */     Item.itemId = 7;
/* 161:165 */     Item.X = 33;
/* 162:166 */     Item.Y = 14;
/* 163:167 */     Item.Rot = 0;
/* 164:168 */     Item.Z = 0;
/* 165:169 */     Item.extraData.setExtraData("0");
/* 166:170 */     this.fuseObjects.add(Item);
/* 167:    */     
/* 168:172 */     Item = new GamefuseObject();
/* 169:173 */     Item.baseItem = BaseItem.snst_ballpile;
/* 170:174 */     Item.baseItem.allowWalk = true;
/* 171:175 */     Item.baseItem.Height = 0.0F;
/* 172:176 */     Item.itemId = 8;
/* 173:177 */     Item.X = 18;
/* 174:178 */     Item.Y = 14;
/* 175:179 */     Item.Rot = 0;
/* 176:180 */     Item.Z = 0;
/* 177:181 */     Item.extraData.setExtraData("0");
/* 178:182 */     this.fuseObjects.add(Item);
/* 179:    */     
/* 180:184 */     Item = new GamefuseObject();
/* 181:185 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 182:186 */     Item.baseItem.allowWalk = false;
/* 183:187 */     Item.baseItem.Height = 1.0F;
/* 184:188 */     Item.itemId = 9;
/* 185:189 */     Item.X = 15;
/* 186:190 */     Item.Y = 36;
/* 187:191 */     Item.Rot = 2;
/* 188:192 */     Item.Z = 0;
/* 189:193 */     Item.extraData.setExtraData("1");
/* 190:194 */     this.fuseObjects.add(Item);
/* 191:    */     
/* 192:196 */     Item = new GamefuseObject();
/* 193:197 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 194:198 */     Item.baseItem.allowWalk = false;
/* 195:199 */     Item.baseItem.Height = 1.0F;
/* 196:200 */     Item.itemId = 10;
/* 197:201 */     Item.X = 34;
/* 198:202 */     Item.Y = 17;
/* 199:203 */     Item.Rot = 0;
/* 200:204 */     Item.Z = 0;
/* 201:205 */     Item.extraData.setExtraData("2");
/* 202:206 */     this.fuseObjects.add(Item);
/* 203:    */     
/* 204:208 */     Item = new GamefuseObject();
/* 205:209 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 206:210 */     Item.baseItem.allowWalk = false;
/* 207:211 */     Item.baseItem.Height = 1.0F;
/* 208:212 */     Item.itemId = 11;
/* 209:213 */     Item.X = 17;
/* 210:214 */     Item.Y = 33;
/* 211:215 */     Item.Rot = 2;
/* 212:216 */     Item.Z = 0;
/* 213:217 */     Item.extraData.setExtraData("2");
/* 214:218 */     this.fuseObjects.add(Item);
/* 215:    */     
/* 216:220 */     Item = new GamefuseObject();
/* 217:221 */     Item.baseItem = BaseItem.snst_block1;
/* 218:222 */     Item.baseItem.allowWalk = false;
/* 219:223 */     Item.baseItem.Height = 1.0F;
/* 220:224 */     Item.itemId = 12;
/* 221:225 */     Item.X = 27;
/* 222:226 */     Item.Y = 17;
/* 223:227 */     Item.Rot = 4;
/* 224:228 */     Item.Z = 0;
/* 225:229 */     Item.extraData.setExtraData("");
/* 226:230 */     this.fuseObjects.add(Item);
/* 227:    */     
/* 228:232 */     Item = new GamefuseObject();
/* 229:233 */     Item.baseItem = BaseItem.snst_ballpile;
/* 230:234 */     Item.baseItem.allowWalk = true;
/* 231:235 */     Item.baseItem.Height = 0.0F;
/* 232:236 */     Item.itemId = 13;
/* 233:237 */     Item.X = 31;
/* 234:238 */     Item.Y = 12;
/* 235:239 */     Item.Rot = 0;
/* 236:240 */     Item.Z = 0;
/* 237:241 */     Item.extraData.setExtraData("0");
/* 238:242 */     this.fuseObjects.add(Item);
/* 239:    */     
/* 240:244 */     Item = new GamefuseObject();
/* 241:245 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 242:246 */     Item.baseItem.allowWalk = false;
/* 243:247 */     Item.baseItem.Height = 1.0F;
/* 244:248 */     Item.itemId = 14;
/* 245:249 */     Item.X = 34;
/* 246:250 */     Item.Y = 42;
/* 247:251 */     Item.Rot = 2;
/* 248:252 */     Item.Z = 0;
/* 249:253 */     Item.extraData.setExtraData("2");
/* 250:254 */     this.fuseObjects.add(Item);
/* 251:    */     
/* 252:256 */     Item = new GamefuseObject();
/* 253:257 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 254:258 */     Item.baseItem.allowWalk = false;
/* 255:259 */     Item.baseItem.Height = 1.0F;
/* 256:260 */     Item.itemId = 15;
/* 257:261 */     Item.X = 41;
/* 258:262 */     Item.Y = 31;
/* 259:263 */     Item.Rot = 0;
/* 260:264 */     Item.Z = 0;
/* 261:265 */     Item.extraData.setExtraData("0");
/* 262:266 */     this.fuseObjects.add(Item);
/* 263:    */     
/* 264:268 */     Item = new GamefuseObject();
/* 265:269 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 266:270 */     Item.baseItem.allowWalk = false;
/* 267:271 */     Item.baseItem.Height = 1.0F;
/* 268:272 */     Item.itemId = 16;
/* 269:273 */     Item.X = 9;
/* 270:274 */     Item.Y = 20;
/* 271:275 */     Item.Rot = 2;
/* 272:276 */     Item.Z = 0;
/* 273:277 */     Item.extraData.setExtraData("0");
/* 274:278 */     this.fuseObjects.add(Item);
/* 275:    */     
/* 276:280 */     Item = new GamefuseObject();
/* 277:281 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 278:282 */     Item.baseItem.allowWalk = false;
/* 279:283 */     Item.baseItem.Height = 1.0F;
/* 280:284 */     Item.itemId = 17;
/* 281:285 */     Item.X = 30;
/* 282:286 */     Item.Y = 39;
/* 283:287 */     Item.Rot = 0;
/* 284:288 */     Item.Z = 0;
/* 285:289 */     Item.extraData.setExtraData("1");
/* 286:290 */     this.fuseObjects.add(Item);
/* 287:    */     
/* 288:292 */     Item = new GamefuseObject();
/* 289:293 */     Item.baseItem = BaseItem.snst_block1;
/* 290:294 */     Item.baseItem.allowWalk = false;
/* 291:295 */     Item.baseItem.Height = 1.0F;
/* 292:296 */     Item.itemId = 18;
/* 293:297 */     Item.X = 21;
/* 294:298 */     Item.Y = 27;
/* 295:299 */     Item.Rot = 6;
/* 296:300 */     Item.Z = 0;
/* 297:301 */     Item.extraData.setExtraData("");
/* 298:302 */     this.fuseObjects.add(Item);
/* 299:    */     
/* 300:304 */     Item = new GamefuseObject();
/* 301:305 */     Item.baseItem = BaseItem.snst_ballpile;
/* 302:306 */     Item.baseItem.allowWalk = true;
/* 303:307 */     Item.baseItem.Height = 0.0F;
/* 304:308 */     Item.itemId = 19;
/* 305:309 */     Item.X = 27;
/* 306:310 */     Item.Y = 12;
/* 307:311 */     Item.Rot = 0;
/* 308:312 */     Item.Z = 0;
/* 309:313 */     Item.extraData.setExtraData("0");
/* 310:314 */     this.fuseObjects.add(Item);
/* 311:    */     
/* 312:316 */     Item = new GamefuseObject();
/* 313:317 */     Item.baseItem = BaseItem.snst_block1;
/* 314:318 */     Item.baseItem.allowWalk = false;
/* 315:319 */     Item.baseItem.Height = 1.0F;
/* 316:320 */     Item.itemId = 20;
/* 317:321 */     Item.X = 22;
/* 318:322 */     Item.Y = 28;
/* 319:323 */     Item.Rot = 4;
/* 320:324 */     Item.Z = 0;
/* 321:325 */     Item.extraData.setExtraData("");
/* 322:326 */     this.fuseObjects.add(Item);
/* 323:    */     
/* 324:328 */     Item = new GamefuseObject();
/* 325:329 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 326:330 */     Item.baseItem.allowWalk = false;
/* 327:331 */     Item.baseItem.Height = 1.0F;
/* 328:332 */     Item.itemId = 21;
/* 329:333 */     Item.X = 13;
/* 330:334 */     Item.Y = 31;
/* 331:335 */     Item.Rot = 2;
/* 332:336 */     Item.Z = 0;
/* 333:337 */     Item.extraData.setExtraData("0");
/* 334:338 */     this.fuseObjects.add(Item);
/* 335:    */     
/* 336:340 */     Item = new GamefuseObject();
/* 337:341 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 338:342 */     Item.baseItem.allowWalk = false;
/* 339:343 */     Item.baseItem.Height = 1.0F;
/* 340:344 */     Item.itemId = 22;
/* 341:345 */     Item.X = 10;
/* 342:346 */     Item.Y = 30;
/* 343:347 */     Item.Rot = 2;
/* 344:348 */     Item.Z = 0;
/* 345:349 */     Item.extraData.setExtraData("0");
/* 346:350 */     this.fuseObjects.add(Item);
/* 347:    */     
/* 348:352 */     Item = new GamefuseObject();
/* 349:353 */     Item.baseItem = BaseItem.snst_ballpile;
/* 350:354 */     Item.baseItem.allowWalk = true;
/* 351:355 */     Item.baseItem.Height = 0.0F;
/* 352:356 */     Item.itemId = 23;
/* 353:357 */     Item.X = 22;
/* 354:358 */     Item.Y = 35;
/* 355:359 */     Item.Rot = 0;
/* 356:360 */     Item.Z = 0;
/* 357:361 */     Item.extraData.setExtraData("0");
/* 358:362 */     this.fuseObjects.add(Item);
/* 359:    */     
/* 360:364 */     Item = new GamefuseObject();
/* 361:365 */     Item.baseItem = BaseItem.snst_ballpile;
/* 362:366 */     Item.baseItem.allowWalk = true;
/* 363:367 */     Item.baseItem.Height = 0.0F;
/* 364:368 */     Item.itemId = 24;
/* 365:369 */     Item.X = 21;
/* 366:370 */     Item.Y = 28;
/* 367:371 */     Item.Rot = 0;
/* 368:372 */     Item.Z = 0;
/* 369:373 */     Item.extraData.setExtraData("0");
/* 370:374 */     this.fuseObjects.add(Item);
/* 371:    */     
/* 372:376 */     Item = new GamefuseObject();
/* 373:377 */     Item.baseItem = BaseItem.xm09_man_a;
/* 374:378 */     Item.baseItem.allowWalk = false;
/* 375:379 */     Item.baseItem.Height = 0.0F;
/* 376:380 */     Item.itemId = 25;
/* 377:381 */     Item.X = 14;
/* 378:382 */     Item.Y = 9;
/* 379:383 */     Item.Rot = 2;
/* 380:384 */     Item.Z = 0;
/* 381:385 */     Item.extraData.setExtraData("0");
/* 382:386 */     this.fuseObjects.add(Item);
/* 383:    */     
/* 384:388 */     Item = new GamefuseObject();
/* 385:389 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 386:390 */     Item.baseItem.allowWalk = false;
/* 387:391 */     Item.baseItem.Height = 1.0F;
/* 388:392 */     Item.itemId = 26;
/* 389:393 */     Item.X = 40;
/* 390:394 */     Item.Y = 34;
/* 391:395 */     Item.Rot = 0;
/* 392:396 */     Item.Z = 0;
/* 393:397 */     Item.extraData.setExtraData("1");
/* 394:398 */     this.fuseObjects.add(Item);
/* 395:    */     
/* 396:400 */     Item = new GamefuseObject();
/* 397:401 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 398:402 */     Item.baseItem.allowWalk = false;
/* 399:403 */     Item.baseItem.Height = 1.0F;
/* 400:404 */     Item.itemId = 27;
/* 401:405 */     Item.X = 42;
/* 402:406 */     Item.Y = 21;
/* 403:407 */     Item.Rot = 0;
/* 404:408 */     Item.Z = 0;
/* 405:409 */     Item.extraData.setExtraData("0");
/* 406:410 */     this.fuseObjects.add(Item);
/* 407:    */     
/* 408:412 */     Item = new GamefuseObject();
/* 409:413 */     Item.baseItem = BaseItem.snst_ballpile;
/* 410:414 */     Item.baseItem.allowWalk = true;
/* 411:415 */     Item.baseItem.Height = 0.0F;
/* 412:416 */     Item.itemId = 28;
/* 413:417 */     Item.X = 27;
/* 414:418 */     Item.Y = 32;
/* 415:419 */     Item.Rot = 0;
/* 416:420 */     Item.Z = 0;
/* 417:421 */     Item.extraData.setExtraData("0");
/* 418:422 */     this.fuseObjects.add(Item);
/* 419:    */     
/* 420:424 */     Item = new GamefuseObject();
/* 421:425 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 422:426 */     Item.baseItem.allowWalk = false;
/* 423:427 */     Item.baseItem.Height = 1.0F;
/* 424:428 */     Item.itemId = 29;
/* 425:429 */     Item.X = 25;
/* 426:430 */     Item.Y = 20;
/* 427:431 */     Item.Rot = 0;
/* 428:432 */     Item.Z = 0;
/* 429:433 */     Item.extraData.setExtraData("2");
/* 430:434 */     this.fuseObjects.add(Item);
/* 431:    */     
/* 432:436 */     Item = new GamefuseObject();
/* 433:437 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 434:438 */     Item.baseItem.allowWalk = false;
/* 435:439 */     Item.baseItem.Height = 1.0F;
/* 436:440 */     Item.itemId = 30;
/* 437:441 */     Item.X = 36;
/* 438:442 */     Item.Y = 14;
/* 439:443 */     Item.Rot = 0;
/* 440:444 */     Item.Z = 0;
/* 441:445 */     Item.extraData.setExtraData("0");
/* 442:446 */     this.fuseObjects.add(Item);
/* 443:    */     
/* 444:448 */     Item = new GamefuseObject();
/* 445:449 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 446:450 */     Item.baseItem.allowWalk = false;
/* 447:451 */     Item.baseItem.Height = 1.0F;
/* 448:452 */     Item.itemId = 31;
/* 449:453 */     Item.X = 11;
/* 450:454 */     Item.Y = 26;
/* 451:455 */     Item.Rot = 2;
/* 452:456 */     Item.Z = 0;
/* 453:457 */     Item.extraData.setExtraData("0");
/* 454:458 */     this.fuseObjects.add(Item);
/* 455:    */     
/* 456:460 */     Item = new GamefuseObject();
/* 457:461 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 458:462 */     Item.baseItem.allowWalk = false;
/* 459:463 */     Item.baseItem.Height = 1.0F;
/* 460:464 */     Item.itemId = 32;
/* 461:465 */     Item.X = 39;
/* 462:466 */     Item.Y = 36;
/* 463:467 */     Item.Rot = 0;
/* 464:468 */     Item.Z = 0;
/* 465:469 */     Item.extraData.setExtraData("1");
/* 466:470 */     this.fuseObjects.add(Item);
/* 467:    */     
/* 468:472 */     Item = new GamefuseObject();
/* 469:473 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 470:474 */     Item.baseItem.allowWalk = false;
/* 471:475 */     Item.baseItem.Height = 1.0F;
/* 472:476 */     Item.itemId = 33;
/* 473:477 */     Item.X = 25;
/* 474:478 */     Item.Y = 24;
/* 475:479 */     Item.Rot = 0;
/* 476:480 */     Item.Z = 0;
/* 477:481 */     Item.extraData.setExtraData("0");
/* 478:482 */     this.fuseObjects.add(Item);
/* 479:    */     
/* 480:484 */     Item = new GamefuseObject();
/* 481:485 */     Item.baseItem = BaseItem.snst_ballpile;
/* 482:486 */     Item.baseItem.allowWalk = true;
/* 483:487 */     Item.baseItem.Height = 0.0F;
/* 484:488 */     Item.itemId = 34;
/* 485:489 */     Item.X = 22;
/* 486:490 */     Item.Y = 13;
/* 487:491 */     Item.Rot = 0;
/* 488:492 */     Item.Z = 0;
/* 489:493 */     Item.extraData.setExtraData("0");
/* 490:494 */     this.fuseObjects.add(Item);
/* 491:    */     
/* 492:496 */     Item = new GamefuseObject();
/* 493:497 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 494:498 */     Item.baseItem.allowWalk = false;
/* 495:499 */     Item.baseItem.Height = 1.0F;
/* 496:500 */     Item.itemId = 35;
/* 497:501 */     Item.X = 13;
/* 498:502 */     Item.Y = 13;
/* 499:503 */     Item.Rot = 0;
/* 500:504 */     Item.Z = 0;
/* 501:505 */     Item.extraData.setExtraData("0");
/* 502:506 */     this.fuseObjects.add(Item);
/* 503:    */     
/* 504:508 */     Item = new GamefuseObject();
/* 505:509 */     Item.baseItem = BaseItem.snst_fence;
/* 506:510 */     Item.baseItem.allowWalk = false;
/* 507:511 */     Item.baseItem.Height = 0.0F;
/* 508:512 */     Item.itemId = 36;
/* 509:513 */     Item.X = 19;
/* 510:514 */     Item.Y = 22;
/* 511:515 */     Item.Rot = 2;
/* 512:516 */     Item.Z = 0;
/* 513:517 */     Item.extraData.setExtraData("0");
/* 514:518 */     this.fuseObjects.add(Item);
/* 515:    */     
/* 516:520 */     Item = new GamefuseObject();
/* 517:521 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 518:522 */     Item.baseItem.allowWalk = false;
/* 519:523 */     Item.baseItem.Height = 1.0F;
/* 520:524 */     Item.itemId = 37;
/* 521:525 */     Item.X = 28;
/* 522:526 */     Item.Y = 9;
/* 523:527 */     Item.Rot = 0;
/* 524:528 */     Item.Z = 0;
/* 525:529 */     Item.extraData.setExtraData("1");
/* 526:530 */     this.fuseObjects.add(Item);
/* 527:    */     
/* 528:532 */     Item = new GamefuseObject();
/* 529:533 */     Item.baseItem = BaseItem.snst_fence;
/* 530:534 */     Item.baseItem.allowWalk = false;
/* 531:535 */     Item.baseItem.Height = 0.0F;
/* 532:536 */     Item.itemId = 38;
/* 533:537 */     Item.X = 34;
/* 534:538 */     Item.Y = 26;
/* 535:539 */     Item.Rot = 2;
/* 536:540 */     Item.Z = 0;
/* 537:541 */     Item.extraData.setExtraData("1");
/* 538:542 */     this.fuseObjects.add(Item);
/* 539:    */     
/* 540:544 */     Item = new GamefuseObject();
/* 541:545 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 542:546 */     Item.baseItem.allowWalk = false;
/* 543:547 */     Item.baseItem.Height = 1.0F;
/* 544:548 */     Item.itemId = 39;
/* 545:549 */     Item.X = 19;
/* 546:550 */     Item.Y = 11;
/* 547:551 */     Item.Rot = 0;
/* 548:552 */     Item.Z = 0;
/* 549:553 */     Item.extraData.setExtraData("1");
/* 550:554 */     this.fuseObjects.add(Item);
/* 551:    */     
/* 552:556 */     Item = new GamefuseObject();
/* 553:557 */     Item.baseItem = BaseItem.snst_ballpile;
/* 554:558 */     Item.baseItem.allowWalk = true;
/* 555:559 */     Item.baseItem.Height = 0.0F;
/* 556:560 */     Item.itemId = 40;
/* 557:561 */     Item.X = 32;
/* 558:562 */     Item.Y = 20;
/* 559:563 */     Item.Rot = 0;
/* 560:564 */     Item.Z = 0;
/* 561:565 */     Item.extraData.setExtraData("0");
/* 562:566 */     this.fuseObjects.add(Item);
/* 563:    */     
/* 564:568 */     Item = new GamefuseObject();
/* 565:569 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 566:570 */     Item.baseItem.allowWalk = false;
/* 567:571 */     Item.baseItem.Height = 1.0F;
/* 568:572 */     Item.itemId = 41;
/* 569:573 */     Item.X = 29;
/* 570:574 */     Item.Y = 44;
/* 571:575 */     Item.Rot = 2;
/* 572:576 */     Item.Z = 0;
/* 573:577 */     Item.extraData.setExtraData("0");
/* 574:578 */     this.fuseObjects.add(Item);
/* 575:    */     
/* 576:580 */     Item = new GamefuseObject();
/* 577:581 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 578:582 */     Item.baseItem.allowWalk = false;
/* 579:583 */     Item.baseItem.Height = 1.0F;
/* 580:584 */     Item.itemId = 42;
/* 581:585 */     Item.X = 43;
/* 582:586 */     Item.Y = 27;
/* 583:587 */     Item.Rot = 0;
/* 584:588 */     Item.Z = 0;
/* 585:589 */     Item.extraData.setExtraData("0");
/* 586:590 */     this.fuseObjects.add(Item);
/* 587:    */     
/* 588:592 */     Item = new GamefuseObject();
/* 589:593 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 590:594 */     Item.baseItem.allowWalk = false;
/* 591:595 */     Item.baseItem.Height = 1.0F;
/* 592:596 */     Item.itemId = 43;
/* 593:597 */     Item.X = 19;
/* 594:598 */     Item.Y = 36;
/* 595:599 */     Item.Rot = 2;
/* 596:600 */     Item.Z = 0;
/* 597:601 */     Item.extraData.setExtraData("0");
/* 598:602 */     this.fuseObjects.add(Item);
/* 599:    */     
/* 600:604 */     Item = new GamefuseObject();
/* 601:605 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 602:606 */     Item.baseItem.allowWalk = false;
/* 603:607 */     Item.baseItem.Height = 1.0F;
/* 604:608 */     Item.itemId = 44;
/* 605:609 */     Item.X = 29;
/* 606:610 */     Item.Y = 26;
/* 607:611 */     Item.Rot = 0;
/* 608:612 */     Item.Z = 0;
/* 609:613 */     Item.extraData.setExtraData("1");
/* 610:614 */     this.fuseObjects.add(Item);
/* 611:    */     
/* 612:616 */     Item = new GamefuseObject();
/* 613:617 */     Item.baseItem = BaseItem.xm09_man_c;
/* 614:618 */     Item.baseItem.allowWalk = false;
/* 615:619 */     Item.baseItem.Height = 0.0F;
/* 616:620 */     Item.itemId = 45;
/* 617:621 */     Item.X = 14;
/* 618:622 */     Item.Y = 9;
/* 619:623 */     Item.Rot = 4;
/* 620:624 */     Item.Z = 2480;
/* 621:625 */     Item.extraData.setExtraData("6");
/* 622:626 */     this.fuseObjects.add(Item);
/* 623:    */     
/* 624:628 */     Item = new GamefuseObject();
/* 625:629 */     Item.baseItem = BaseItem.ads_background;
/* 626:630 */     Item.baseItem.allowWalk = true;
/* 627:631 */     Item.baseItem.Height = 0.0F;
/* 628:632 */     Item.baseItem.itemExtraType = 1;
/* 629:633 */     Item.itemId = 46;
/* 630:634 */     Item.X = 0;
/* 631:635 */     Item.Y = 22;
/* 632:636 */     Item.Rot = 1;
/* 633:637 */     Item.Z = 0;
/* 634:638 */     Item.extraData = new MapStuffData("state=0\toffsetX=-1119\toffsetZ=9950\toffsetY=390\timageUrl=http://dcr.lavvos.pl/lavvos/c_images/DEV_tests/snst_bg_3_noscale.png");
/* 635:639 */     this.fuseObjects.add(Item);
/* 636:    */     
/* 637:641 */     Item = new GamefuseObject();
/* 638:642 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 639:643 */     Item.baseItem.allowWalk = false;
/* 640:644 */     Item.baseItem.Height = 1.0F;
/* 641:645 */     Item.itemId = 47;
/* 642:646 */     Item.X = 14;
/* 643:647 */     Item.Y = 17;
/* 644:648 */     Item.Rot = 2;
/* 645:649 */     Item.Z = 0;
/* 646:650 */     Item.extraData.setExtraData("0");
/* 647:651 */     this.fuseObjects.add(Item);
/* 648:    */     
/* 649:653 */     Item = new GamefuseObject();
/* 650:654 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 651:655 */     Item.baseItem.allowWalk = false;
/* 652:656 */     Item.baseItem.Height = 1.0F;
/* 653:657 */     Item.itemId = 48;
/* 654:658 */     Item.X = 24;
/* 655:659 */     Item.Y = 9;
/* 656:660 */     Item.Rot = 0;
/* 657:661 */     Item.Z = 0;
/* 658:662 */     Item.extraData.setExtraData("0");
/* 659:663 */     this.fuseObjects.add(Item);
/* 660:    */     
/* 661:665 */     Item = new GamefuseObject();
/* 662:666 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 663:667 */     Item.baseItem.allowWalk = false;
/* 664:668 */     Item.baseItem.Height = 1.0F;
/* 665:669 */     Item.itemId = 49;
/* 666:670 */     Item.X = 22;
/* 667:671 */     Item.Y = 20;
/* 668:672 */     Item.Rot = 0;
/* 669:673 */     Item.Z = 0;
/* 670:674 */     Item.extraData.setExtraData("0");
/* 671:675 */     this.fuseObjects.add(Item);
/* 672:    */     
/* 673:677 */     Item = new GamefuseObject();
/* 674:678 */     Item.baseItem = BaseItem.snst_fence;
/* 675:679 */     Item.baseItem.allowWalk = false;
/* 676:680 */     Item.baseItem.Height = 0.0F;
/* 677:681 */     Item.itemId = 50;
/* 678:682 */     Item.X = 17;
/* 679:683 */     Item.Y = 22;
/* 680:684 */     Item.Rot = 2;
/* 681:685 */     Item.Z = 0;
/* 682:686 */     Item.extraData.setExtraData("1");
/* 683:687 */     this.fuseObjects.add(Item);
/* 684:    */     
/* 685:689 */     Item = new GamefuseObject();
/* 686:690 */     Item.baseItem = BaseItem.snst_block1;
/* 687:691 */     Item.baseItem.allowWalk = false;
/* 688:692 */     Item.baseItem.Height = 1.0F;
/* 689:693 */     Item.itemId = 51;
/* 690:694 */     Item.X = 26;
/* 691:695 */     Item.Y = 16;
/* 692:696 */     Item.Rot = 6;
/* 693:697 */     Item.Z = 0;
/* 694:698 */     Item.extraData.setExtraData("");
/* 695:699 */     this.fuseObjects.add(Item);
/* 696:    */     
/* 697:701 */     Item = new GamefuseObject();
/* 698:702 */     Item.baseItem = BaseItem.snst_ballpile;
/* 699:703 */     Item.baseItem.allowWalk = true;
/* 700:704 */     Item.baseItem.Height = 0.0F;
/* 701:705 */     Item.itemId = 52;
/* 702:706 */     Item.X = 17;
/* 703:707 */     Item.Y = 29;
/* 704:708 */     Item.Rot = 0;
/* 705:709 */     Item.Z = 0;
/* 706:710 */     Item.extraData.setExtraData("0");
/* 707:711 */     this.fuseObjects.add(Item);
/* 708:    */     
/* 709:713 */     Item = new GamefuseObject();
/* 710:714 */     Item.baseItem = BaseItem.snst_block1;
/* 711:715 */     Item.baseItem.allowWalk = false;
/* 712:716 */     Item.baseItem.Height = 1.0F;
/* 713:717 */     Item.itemId = 53;
/* 714:718 */     Item.X = 22;
/* 715:719 */     Item.Y = 27;
/* 716:720 */     Item.Rot = 6;
/* 717:721 */     Item.Z = 0;
/* 718:722 */     Item.extraData.setExtraData("");
/* 719:723 */     this.fuseObjects.add(Item);
/* 720:    */     
/* 721:725 */     Item = new GamefuseObject();
/* 722:726 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 723:727 */     Item.baseItem.allowWalk = false;
/* 724:728 */     Item.baseItem.Height = 1.0F;
/* 725:729 */     Item.itemId = 54;
/* 726:730 */     Item.X = 37;
/* 727:731 */     Item.Y = 40;
/* 728:732 */     Item.Rot = 2;
/* 729:733 */     Item.Z = 0;
/* 730:734 */     Item.extraData.setExtraData("0");
/* 731:735 */     this.fuseObjects.add(Item);
/* 732:    */     
/* 733:737 */     Item = new GamefuseObject();
/* 734:738 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 735:739 */     Item.baseItem.allowWalk = false;
/* 736:740 */     Item.baseItem.Height = 1.0F;
/* 737:741 */     Item.itemId = 55;
/* 738:742 */     Item.X = 24;
/* 739:743 */     Item.Y = 42;
/* 740:744 */     Item.Rot = 2;
/* 741:745 */     Item.Z = 0;
/* 742:746 */     Item.extraData.setExtraData("0");
/* 743:747 */     this.fuseObjects.add(Item);
/* 744:    */     
/* 745:749 */     Item = new GamefuseObject();
/* 746:750 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 747:751 */     Item.baseItem.allowWalk = false;
/* 748:752 */     Item.baseItem.Height = 1.0F;
/* 749:753 */     Item.itemId = 56;
/* 750:754 */     Item.X = 17;
/* 751:755 */     Item.Y = 10;
/* 752:756 */     Item.Rot = 0;
/* 753:757 */     Item.Z = 0;
/* 754:758 */     Item.extraData.setExtraData("0");
/* 755:759 */     this.fuseObjects.add(Item);
/* 756:    */     
/* 757:761 */     Item = new GamefuseObject();
/* 758:762 */     Item.baseItem = BaseItem.snst_ballpile;
/* 759:763 */     Item.baseItem.allowWalk = true;
/* 760:764 */     Item.baseItem.Height = 0.0F;
/* 761:765 */     Item.itemId = 57;
/* 762:766 */     Item.X = 14;
/* 763:767 */     Item.Y = 24;
/* 764:768 */     Item.Rot = 0;
/* 765:769 */     Item.Z = 0;
/* 766:770 */     Item.extraData.setExtraData("0");
/* 767:771 */     this.fuseObjects.add(Item);
/* 768:    */     
/* 769:773 */     Item = new GamefuseObject();
/* 770:774 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 771:775 */     Item.baseItem.allowWalk = false;
/* 772:776 */     Item.baseItem.Height = 1.0F;
/* 773:777 */     Item.itemId = 58;
/* 774:778 */     Item.X = 20;
/* 775:779 */     Item.Y = 40;
/* 776:780 */     Item.Rot = 2;
/* 777:781 */     Item.Z = 0;
/* 778:782 */     Item.extraData.setExtraData("0");
/* 779:783 */     this.fuseObjects.add(Item);
/* 780:    */     
/* 781:785 */     Item = new GamefuseObject();
/* 782:786 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 783:787 */     Item.baseItem.allowWalk = false;
/* 784:788 */     Item.baseItem.Height = 1.0F;
/* 785:789 */     Item.itemId = 59;
/* 786:790 */     Item.X = 20;
/* 787:791 */     Item.Y = 8;
/* 788:792 */     Item.Rot = 0;
/* 789:793 */     Item.Z = 0;
/* 790:794 */     Item.extraData.setExtraData("0");
/* 791:795 */     this.fuseObjects.add(Item);
/* 792:    */     
/* 793:797 */     Item = new GamefuseObject();
/* 794:798 */     Item.baseItem = BaseItem.snst_ballpile;
/* 795:799 */     Item.baseItem.allowWalk = true;
/* 796:800 */     Item.baseItem.Height = 0.0F;
/* 797:801 */     Item.itemId = 60;
/* 798:802 */     Item.X = 17;
/* 799:803 */     Item.Y = 35;
/* 800:804 */     Item.Rot = 0;
/* 801:805 */     Item.Z = 0;
/* 802:806 */     Item.extraData.setExtraData("0");
/* 803:807 */     this.fuseObjects.add(Item);
/* 804:    */     
/* 805:809 */     Item = new GamefuseObject();
/* 806:810 */     Item.baseItem = BaseItem.snst_block1;
/* 807:811 */     Item.baseItem.allowWalk = false;
/* 808:812 */     Item.baseItem.Height = 1.0F;
/* 809:813 */     Item.itemId = 61;
/* 810:814 */     Item.X = 26;
/* 811:815 */     Item.Y = 17;
/* 812:816 */     Item.Rot = 4;
/* 813:817 */     Item.Z = 0;
/* 814:818 */     Item.extraData.setExtraData("");
/* 815:819 */     this.fuseObjects.add(Item);
/* 816:    */     
/* 817:821 */     Item = new GamefuseObject();
/* 818:822 */     Item.baseItem = BaseItem.snst_tree1_d;
/* 819:823 */     Item.baseItem.allowWalk = false;
/* 820:824 */     Item.baseItem.Height = 1.0F;
/* 821:825 */     Item.itemId = 62;
/* 822:826 */     Item.X = 22;
/* 823:827 */     Item.Y = 24;
/* 824:828 */     Item.Rot = 2;
/* 825:829 */     Item.Z = 0;
/* 826:830 */     Item.extraData.setExtraData("0");
/* 827:831 */     this.fuseObjects.add(Item);
/* 828:    */     
/* 829:833 */     Item = new GamefuseObject();
/* 830:834 */     Item.baseItem = BaseItem.snst_fence;
/* 831:835 */     Item.baseItem.allowWalk = false;
/* 832:836 */     Item.baseItem.Height = 0.0F;
/* 833:837 */     Item.itemId = 63;
/* 834:838 */     Item.X = 30;
/* 835:839 */     Item.Y = 26;
/* 836:840 */     Item.Rot = 2;
/* 837:841 */     Item.Z = 0;
/* 838:842 */     Item.extraData.setExtraData("1");
/* 839:843 */     this.fuseObjects.add(Item);
/* 840:    */     
/* 841:845 */     Item = new GamefuseObject();
/* 842:846 */     Item.baseItem = BaseItem.xm09_man_b;
/* 843:847 */     Item.baseItem.allowWalk = false;
/* 844:848 */     Item.baseItem.Height = 0.0F;
/* 845:849 */     Item.itemId = 64;
/* 846:850 */     Item.X = 14;
/* 847:851 */     Item.Y = 9;
/* 848:852 */     Item.Rot = 4;
/* 849:853 */     Item.Z = 1280;
/* 850:854 */     Item.extraData.setExtraData("5");
/* 851:855 */     this.fuseObjects.add(Item);
/* 852:    */     
/* 853:857 */     this.spawnsBLUE.add(new SpawnPoint(10, 10));
/* 854:858 */     this.spawnsRED.add(new SpawnPoint(11, 11));
/* 855:    */   }
/* 856:    */   
/* 857:    */   public void gameObjects(Map<Integer, GameItemObject> gameObjects, SnowWarRoom room)
/* 858:    */   {
/* 859:863 */     gameObjects.put(Integer.valueOf(0), new TreeGameObject(29, 23, 0, 1, 0, 3, 0, room.map, room));
/* 860:864 */     gameObjects.put(Integer.valueOf(1), new TreeGameObject(11, 16, 2, 1, 1, 3, 0, room.map, room));
/* 861:865 */     gameObjects.put(Integer.valueOf(2), new TreeGameObject(26, 28, 2, 1, 2, 3, 0, room.map, room));
/* 862:866 */     gameObjects.put(Integer.valueOf(3), new TreeGameObject(31, 42, 2, 1, 3, 3, 0, room.map, room));
/* 863:867 */     gameObjects.put(Integer.valueOf(4), new PileGameObject(27, 16, 12, 12, 4, room.map, room));
/* 864:868 */     gameObjects.put(Integer.valueOf(5), new TreeGameObject(32, 10, 0, 1, 5, 3, 1, room.map, room));
/* 865:869 */     gameObjects.put(Integer.valueOf(6), new TreeGameObject(13, 21, 2, 1, 6, 3, 0, room.map, room));
/* 866:870 */     gameObjects.put(Integer.valueOf(7), new TreeGameObject(33, 14, 0, 1, 7, 3, 0, room.map, room));
/* 867:871 */     gameObjects.put(Integer.valueOf(8), new PileGameObject(18, 14, 12, 12, 8, room.map, room));
/* 868:872 */     gameObjects.put(Integer.valueOf(9), new TreeGameObject(15, 36, 2, 1, 9, 3, 1, room.map, room));
/* 869:873 */     gameObjects.put(Integer.valueOf(10), new TreeGameObject(34, 17, 0, 1, 10, 3, 2, room.map, room));
/* 870:874 */     gameObjects.put(Integer.valueOf(11), new TreeGameObject(17, 33, 2, 1, 11, 3, 2, room.map, room));
/* 871:875 */     gameObjects.put(Integer.valueOf(12), new PileGameObject(31, 12, 12, 12, 13, room.map, room));
/* 872:876 */     gameObjects.put(Integer.valueOf(13), new TreeGameObject(34, 42, 2, 1, 14, 3, 2, room.map, room));
/* 873:877 */     gameObjects.put(Integer.valueOf(14), new TreeGameObject(41, 31, 0, 1, 15, 3, 0, room.map, room));
/* 874:878 */     gameObjects.put(Integer.valueOf(15), new TreeGameObject(9, 20, 2, 1, 16, 3, 0, room.map, room));
/* 875:879 */     gameObjects.put(Integer.valueOf(16), new TreeGameObject(30, 39, 0, 1, 17, 3, 1, room.map, room));
/* 876:880 */     gameObjects.put(Integer.valueOf(17), new PileGameObject(27, 12, 12, 12, 19, room.map, room));
/* 877:881 */     gameObjects.put(Integer.valueOf(18), new TreeGameObject(13, 31, 2, 1, 21, 3, 0, room.map, room));
/* 878:882 */     gameObjects.put(Integer.valueOf(19), new TreeGameObject(10, 30, 2, 1, 22, 3, 0, room.map, room));
/* 879:883 */     gameObjects.put(Integer.valueOf(20), new PileGameObject(22, 35, 12, 12, 23, room.map, room));
/* 880:884 */     gameObjects.put(Integer.valueOf(21), new PileGameObject(21, 28, 12, 12, 24, room.map, room));
/* 881:885 */     gameObjects.put(Integer.valueOf(22), new TreeGameObject(40, 34, 0, 1, 26, 3, 1, room.map, room));
/* 882:886 */     gameObjects.put(Integer.valueOf(23), new TreeGameObject(42, 21, 0, 1, 27, 3, 0, room.map, room));
/* 883:887 */     gameObjects.put(Integer.valueOf(24), new PileGameObject(27, 32, 12, 12, 28, room.map, room));
/* 884:888 */     gameObjects.put(Integer.valueOf(25), new TreeGameObject(25, 20, 0, 1, 29, 3, 2, room.map, room));
/* 885:889 */     gameObjects.put(Integer.valueOf(26), new TreeGameObject(36, 15, 0, 1, 30, 3, 0, room.map, room));
/* 886:890 */     gameObjects.put(Integer.valueOf(27), new TreeGameObject(11, 26, 2, 1, 31, 3, 0, room.map, room));
/* 887:891 */     gameObjects.put(Integer.valueOf(28), new TreeGameObject(39, 36, 0, 1, 32, 3, 1, room.map, room));
/* 888:892 */     gameObjects.put(Integer.valueOf(29), new TreeGameObject(25, 24, 0, 1, 33, 3, 0, room.map, room));
/* 889:893 */     gameObjects.put(Integer.valueOf(30), new PileGameObject(22, 13, 12, 12, 34, room.map, room));
/* 890:894 */     gameObjects.put(Integer.valueOf(31), new TreeGameObject(13, 13, 0, 1, 35, 3, 0, room.map, room));
/* 891:895 */     gameObjects.put(Integer.valueOf(32), new TreeGameObject(28, 9, 0, 1, 37, 3, 1, room.map, room));
/* 892:896 */     gameObjects.put(Integer.valueOf(33), new TreeGameObject(19, 11, 0, 1, 39, 3, 1, room.map, room));
/* 893:897 */     gameObjects.put(Integer.valueOf(34), new PileGameObject(32, 20, 12, 12, 40, room.map, room));
/* 894:898 */     gameObjects.put(Integer.valueOf(35), new TreeGameObject(29, 44, 2, 1, 41, 3, 0, room.map, room));
/* 895:899 */     gameObjects.put(Integer.valueOf(36), new TreeGameObject(43, 27, 0, 1, 42, 3, 0, room.map, room));
/* 896:900 */     gameObjects.put(Integer.valueOf(37), new TreeGameObject(19, 36, 2, 1, 43, 3, 0, room.map, room));
/* 897:901 */     gameObjects.put(Integer.valueOf(38), new TreeGameObject(29, 26, 0, 1, 44, 3, 1, room.map, room));
/* 898:902 */     gameObjects.put(Integer.valueOf(39), new TreeGameObject(14, 17, 2, 1, 47, 3, 0, room.map, room));
/* 899:903 */     gameObjects.put(Integer.valueOf(40), new TreeGameObject(24, 9, 0, 1, 48, 3, 0, room.map, room));
/* 900:904 */     gameObjects.put(Integer.valueOf(41), new TreeGameObject(23, 20, 0, 1, 49, 3, 0, room.map, room));
/* 901:905 */     gameObjects.put(Integer.valueOf(42), new PileGameObject(17, 29, 12, 12, 52, room.map, room));
/* 902:906 */     gameObjects.put(Integer.valueOf(43), new TreeGameObject(37, 40, 2, 1, 54, 3, 0, room.map, room));
/* 903:907 */     gameObjects.put(Integer.valueOf(44), new TreeGameObject(24, 42, 2, 1, 55, 3, 0, room.map, room));
/* 904:908 */     gameObjects.put(Integer.valueOf(45), new TreeGameObject(17, 10, 0, 1, 56, 3, 0, room.map, room));
/* 905:909 */     gameObjects.put(Integer.valueOf(46), new PileGameObject(14, 24, 12, 12, 57, room.map, room));
/* 906:910 */     gameObjects.put(Integer.valueOf(47), new TreeGameObject(20, 40, 2, 1, 58, 3, 0, room.map, room));
/* 907:911 */     gameObjects.put(Integer.valueOf(48), new TreeGameObject(20, 8, 0, 1, 59, 3, 0, room.map, room));
/* 908:912 */     gameObjects.put(Integer.valueOf(49), new PileGameObject(17, 35, 12, 12, 60, room.map, room));
/* 909:913 */     gameObjects.put(Integer.valueOf(50), new TreeGameObject(22, 24, 2, 1, 62, 3, 0, room.map, room));
/* 910:    */   }
/* 911:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.SnowWarArena11
 * JD-Core Version:    0.7.0.1
 */