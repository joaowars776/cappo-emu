/*   1:    */ package cappo.game.games.snowwar;
/*   2:    */ 
/*   3:    */ import cappo.game.collections.BaseItem;
/*   4:    */ import cappo.game.games.snowwar.gameobjects.GameItemObject;
/*   5:    */ import cappo.game.games.snowwar.gameobjects.PileGameObject;
/*   6:    */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*   7:    */ import cappo.game.roomengine.entity.item.extradata.MapStuffData;
/*   8:    */ import java.util.List;
/*   9:    */ import java.util.Map;
/*  10:    */ 
/*  11:    */ public class SnowWarArena9
/*  12:    */   extends SnowWarArenaBase
/*  13:    */ {
/*  14:    */   public SnowWarArena9()
/*  15:    */   {
/*  16: 20 */     this.ArenaType = 9;
/*  17: 21 */     this.ArenaHeight = 50;
/*  18: 22 */     this.ArenaWidth = 50;
/*  19: 23 */     this.HeightMap = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxx000000000000000xxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxx00000000000000000xxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxx0000000000000000000xxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxx000000000000000000000xxxxxxxxxxxxxxxxxx\rxxxxxxxxxx00000000000000000000000xxxxxxxxxxxxxxxxx\rxxxxxxxxx0000000000000000000000000xxxxxxxxxxxxxxxx\rxxxxxxxx000000000000000000000000000xxxxxxxxxxxxxxx\rxxxxxxx00000000000000000000000000000xxxxxxxxxxxxxx\rxxxxxx0000000000000000000000000000000xxxxxxxxxxxxx\rxxxxx000000000000000000000000000000000xxxxxxxxxxxx\rxxxxx0000000000000000000000000000000000xxxxxxxxxxx\rxxxxx00000000000000000000000000000000000xxxxxxxxxx\rxxxxx000000000000000000000000000000000000xxxxxxxxx\rxxxxx0000000000000000000000000000000000000xxxxxxxx\rxxxxx00000000000000000000000000000000000000xxxxxxx\rxxxxx000000000000000000000000000000000000000xxxxxx\rxxxxx0000000000000000000000000000000000000000xxxxx\r0xxxx00000000000000000000000000000000000000000xxxx\rxxxxx00000000000000000000000000000000000000000xxxx\rxxxxx00000000000000000000000000000000000000000xxxx\rxxxxx000000000000000000000000000000000000000000xxx\rxxxxx000000000000000000000000000000000000000000xxx\rxxxxx000000000000000000000000000000000000000000xxx\rxxxxxx00000000000000000000000000000000000000000xxx\rxxxxxxx0000000000000000000000000000000000000000xxx\rxxxxxxxx0000000000000000000000000000000000000xxxxx\rxxxxxxxxx00000000000000000000000000000000000xxxxxx\rxxxxxxxxxx000000000000000000000000000000000xxxxxxx\rxxxxxxxxxxx00000000000000000000000000000000xxxx0xx\rxxxxxxxxxxxx0000000000000000000000000000000xxxxxxx\rxxxxxxxxxxxxx00000000000000000000000000000xxxxxxxx\rxxxxxxxxxxxxxx0000000000000000000000000000xxxxxxxx\rxxxxxxxxxxxxxxx00000000000000000000000000xxxxxxxxx\rxxxxxxxxxxxxxxxx0000000000000000000000000xxxxxxxxx\rxxxxxxxxxxxxxxxxx00000000000000000000000xxxxxxxxxx\rxxxxxxxxxxxxxxxxxx0000000000000000000000xxxxxxxxxx\rxxxxxxxxxxxxxxxxxxx00000000000000000000xxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxx000000000000000xxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxx0000000000000xxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxx00000000000xxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxx0000000xxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r";
/*  20:    */     
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
/*  71: 75 */     GamefuseObject Item = new GamefuseObject();
/*  72: 76 */     Item.baseItem = BaseItem.snst_iceblock;
/*  73: 77 */     Item.baseItem.allowWalk = false;
/*  74: 78 */     Item.baseItem.Height = 1.0F;
/*  75: 79 */     Item.itemId = 0;
/*  76: 80 */     Item.X = 9;
/*  77: 81 */     Item.Y = 14;
/*  78: 82 */     Item.Rot = 0;
/*  79: 83 */     Item.Z = 0;
/*  80: 84 */     Item.extraData.setExtraData("");
/*  81: 85 */     this.fuseObjects.add(Item);
/*  82:    */     
/*  83: 87 */     Item = new GamefuseObject();
/*  84: 88 */     Item.baseItem = BaseItem.snst_ballpile;
/*  85: 89 */     Item.baseItem.allowWalk = true;
/*  86: 90 */     Item.baseItem.Height = 0.0F;
/*  87: 91 */     Item.itemId = 1;
/*  88: 92 */     Item.X = 22;
/*  89: 93 */     Item.Y = 27;
/*  90: 94 */     Item.Rot = 0;
/*  91: 95 */     Item.Z = 0;
/*  92: 96 */     Item.extraData.setExtraData("0");
/*  93: 97 */     this.fuseObjects.add(Item);
/*  94:    */     
/*  95: 99 */     Item = new GamefuseObject();
/*  96:100 */     Item.baseItem = BaseItem.snst_ballpile;
/*  97:101 */     Item.baseItem.allowWalk = true;
/*  98:102 */     Item.baseItem.Height = 0.0F;
/*  99:103 */     Item.itemId = 2;
/* 100:104 */     Item.X = 8;
/* 101:105 */     Item.Y = 20;
/* 102:106 */     Item.Rot = 0;
/* 103:107 */     Item.Z = 0;
/* 104:108 */     Item.extraData.setExtraData("0");
/* 105:109 */     this.fuseObjects.add(Item);
/* 106:    */     
/* 107:111 */     Item = new GamefuseObject();
/* 108:112 */     Item.baseItem = BaseItem.snst_ballpile;
/* 109:113 */     Item.baseItem.allowWalk = true;
/* 110:114 */     Item.baseItem.Height = 0.0F;
/* 111:115 */     Item.itemId = 3;
/* 112:116 */     Item.X = 8;
/* 113:117 */     Item.Y = 26;
/* 114:118 */     Item.Rot = 0;
/* 115:119 */     Item.Z = 0;
/* 116:120 */     Item.extraData.setExtraData("0");
/* 117:121 */     this.fuseObjects.add(Item);
/* 118:    */     
/* 119:123 */     Item = new GamefuseObject();
/* 120:124 */     Item.baseItem = BaseItem.snst_iceblock;
/* 121:125 */     Item.baseItem.allowWalk = false;
/* 122:126 */     Item.baseItem.Height = 1.0F;
/* 123:127 */     Item.itemId = 4;
/* 124:128 */     Item.X = 35;
/* 125:129 */     Item.Y = 30;
/* 126:130 */     Item.Rot = 2;
/* 127:131 */     Item.Z = 0;
/* 128:132 */     Item.extraData.setExtraData("");
/* 129:133 */     this.fuseObjects.add(Item);
/* 130:    */     
/* 131:135 */     Item = new GamefuseObject();
/* 132:136 */     Item.baseItem = BaseItem.snst_iceblock;
/* 133:137 */     Item.baseItem.allowWalk = false;
/* 134:138 */     Item.baseItem.Height = 1.0F;
/* 135:139 */     Item.itemId = 5;
/* 136:140 */     Item.X = 22;
/* 137:141 */     Item.Y = 17;
/* 138:142 */     Item.Rot = 2;
/* 139:143 */     Item.Z = 0;
/* 140:144 */     Item.extraData.setExtraData("");
/* 141:145 */     this.fuseObjects.add(Item);
/* 142:    */     
/* 143:147 */     Item = new GamefuseObject();
/* 144:148 */     Item.baseItem = BaseItem.snst_iceblock;
/* 145:149 */     Item.baseItem.allowWalk = false;
/* 146:150 */     Item.baseItem.Height = 1.0F;
/* 147:151 */     Item.itemId = 6;
/* 148:152 */     Item.X = 9;
/* 149:153 */     Item.Y = 17;
/* 150:154 */     Item.Rot = 2;
/* 151:155 */     Item.Z = 0;
/* 152:156 */     Item.extraData.setExtraData("");
/* 153:157 */     this.fuseObjects.add(Item);
/* 154:    */     
/* 155:159 */     Item = new GamefuseObject();
/* 156:160 */     Item.baseItem = BaseItem.snst_iceblock;
/* 157:161 */     Item.baseItem.allowWalk = false;
/* 158:162 */     Item.baseItem.Height = 1.0F;
/* 159:163 */     Item.itemId = 7;
/* 160:164 */     Item.X = 35;
/* 161:165 */     Item.Y = 24;
/* 162:166 */     Item.Rot = 4;
/* 163:167 */     Item.Z = 0;
/* 164:168 */     Item.extraData.setExtraData("");
/* 165:169 */     this.fuseObjects.add(Item);
/* 166:    */     
/* 167:171 */     Item = new GamefuseObject();
/* 168:172 */     Item.baseItem = BaseItem.snst_ballpile;
/* 169:173 */     Item.baseItem.allowWalk = true;
/* 170:174 */     Item.baseItem.Height = 0.0F;
/* 171:175 */     Item.itemId = 8;
/* 172:176 */     Item.X = 36;
/* 173:177 */     Item.Y = 18;
/* 174:178 */     Item.Rot = 0;
/* 175:179 */     Item.Z = 0;
/* 176:180 */     Item.extraData.setExtraData("0");
/* 177:181 */     this.fuseObjects.add(Item);
/* 178:    */     
/* 179:183 */     Item = new GamefuseObject();
/* 180:184 */     Item.baseItem = BaseItem.snst_ballpile;
/* 181:185 */     Item.baseItem.allowWalk = true;
/* 182:186 */     Item.baseItem.Height = 0.0F;
/* 183:187 */     Item.itemId = 9;
/* 184:188 */     Item.X = 24;
/* 185:189 */     Item.Y = 21;
/* 186:190 */     Item.Rot = 0;
/* 187:191 */     Item.Z = 0;
/* 188:192 */     Item.extraData.setExtraData("0");
/* 189:193 */     this.fuseObjects.add(Item);
/* 190:    */     
/* 191:195 */     Item = new GamefuseObject();
/* 192:196 */     Item.baseItem = BaseItem.snst_iceblock;
/* 193:197 */     Item.baseItem.allowWalk = false;
/* 194:198 */     Item.baseItem.Height = 1.0F;
/* 195:199 */     Item.itemId = 10;
/* 196:200 */     Item.X = 22;
/* 197:201 */     Item.Y = 25;
/* 198:202 */     Item.Rot = 4;
/* 199:203 */     Item.Z = 0;
/* 200:204 */     Item.extraData.setExtraData("");
/* 201:205 */     this.fuseObjects.add(Item);
/* 202:    */     
/* 203:207 */     Item = new GamefuseObject();
/* 204:208 */     Item.baseItem = BaseItem.snst_iceblock;
/* 205:209 */     Item.baseItem.allowWalk = false;
/* 206:210 */     Item.baseItem.Height = 1.0F;
/* 207:211 */     Item.itemId = 11;
/* 208:212 */     Item.X = 18;
/* 209:213 */     Item.Y = 21;
/* 210:214 */     Item.Rot = 2;
/* 211:215 */     Item.Z = 0;
/* 212:216 */     Item.extraData.setExtraData("");
/* 213:217 */     this.fuseObjects.add(Item);
/* 214:    */     
/* 215:219 */     Item = new GamefuseObject();
/* 216:220 */     Item.baseItem = BaseItem.snst_ballpile;
/* 217:221 */     Item.baseItem.allowWalk = true;
/* 218:222 */     Item.baseItem.Height = 0.0F;
/* 219:223 */     Item.itemId = 12;
/* 220:224 */     Item.X = 8;
/* 221:225 */     Item.Y = 23;
/* 222:226 */     Item.Rot = 0;
/* 223:227 */     Item.Z = 0;
/* 224:228 */     Item.extraData.setExtraData("0");
/* 225:229 */     this.fuseObjects.add(Item);
/* 226:    */     
/* 227:231 */     Item = new GamefuseObject();
/* 228:232 */     Item.baseItem = BaseItem.snst_ballpile;
/* 229:233 */     Item.baseItem.allowWalk = true;
/* 230:234 */     Item.baseItem.Height = 0.0F;
/* 231:235 */     Item.itemId = 13;
/* 232:236 */     Item.X = 36;
/* 233:237 */     Item.Y = 27;
/* 234:238 */     Item.Rot = 0;
/* 235:239 */     Item.Z = 0;
/* 236:240 */     Item.extraData.setExtraData("0");
/* 237:241 */     this.fuseObjects.add(Item);
/* 238:    */     
/* 239:243 */     Item = new GamefuseObject();
/* 240:244 */     Item.baseItem = BaseItem.snst_iceblock;
/* 241:245 */     Item.baseItem.allowWalk = false;
/* 242:246 */     Item.baseItem.Height = 1.0F;
/* 243:247 */     Item.itemId = 14;
/* 244:248 */     Item.X = 9;
/* 245:249 */     Item.Y = 20;
/* 246:250 */     Item.Rot = 6;
/* 247:251 */     Item.Z = 0;
/* 248:252 */     Item.extraData.setExtraData("");
/* 249:253 */     this.fuseObjects.add(Item);
/* 250:    */     
/* 251:255 */     Item = new GamefuseObject();
/* 252:256 */     Item.baseItem = BaseItem.snst_ballpile;
/* 253:257 */     Item.baseItem.allowWalk = true;
/* 254:258 */     Item.baseItem.Height = 0.0F;
/* 255:259 */     Item.itemId = 15;
/* 256:260 */     Item.X = 36;
/* 257:261 */     Item.Y = 21;
/* 258:262 */     Item.Rot = 0;
/* 259:263 */     Item.Z = 0;
/* 260:264 */     Item.extraData.setExtraData("0");
/* 261:265 */     this.fuseObjects.add(Item);
/* 262:    */     
/* 263:267 */     Item = new GamefuseObject();
/* 264:268 */     Item.baseItem = BaseItem.snst_ballpile;
/* 265:269 */     Item.baseItem.allowWalk = true;
/* 266:270 */     Item.baseItem.Height = 0.0F;
/* 267:271 */     Item.itemId = 16;
/* 268:272 */     Item.X = 28;
/* 269:273 */     Item.Y = 21;
/* 270:274 */     Item.Rot = 0;
/* 271:275 */     Item.Z = 0;
/* 272:276 */     Item.extraData.setExtraData("0");
/* 273:277 */     this.fuseObjects.add(Item);
/* 274:    */     
/* 275:279 */     Item = new GamefuseObject();
/* 276:280 */     Item.baseItem = BaseItem.snst_iceblock;
/* 277:281 */     Item.baseItem.allowWalk = false;
/* 278:282 */     Item.baseItem.Height = 1.0F;
/* 279:283 */     Item.itemId = 17;
/* 280:284 */     Item.X = 35;
/* 281:285 */     Item.Y = 15;
/* 282:286 */     Item.Rot = 2;
/* 283:287 */     Item.Z = 0;
/* 284:288 */     Item.extraData.setExtraData("");
/* 285:289 */     this.fuseObjects.add(Item);
/* 286:    */     
/* 287:291 */     Item = new GamefuseObject();
/* 288:292 */     Item.baseItem = BaseItem.snst_iceblock;
/* 289:293 */     Item.baseItem.allowWalk = false;
/* 290:294 */     Item.baseItem.Height = 1.0F;
/* 291:295 */     Item.itemId = 18;
/* 292:296 */     Item.X = 9;
/* 293:297 */     Item.Y = 29;
/* 294:298 */     Item.Rot = 2;
/* 295:299 */     Item.Z = 0;
/* 296:300 */     Item.extraData.setExtraData("");
/* 297:301 */     this.fuseObjects.add(Item);
/* 298:    */     
/* 299:303 */     Item = new GamefuseObject();
/* 300:304 */     Item.baseItem = BaseItem.snst_iceblock;
/* 301:305 */     Item.baseItem.allowWalk = false;
/* 302:306 */     Item.baseItem.Height = 1.0F;
/* 303:307 */     Item.itemId = 19;
/* 304:308 */     Item.X = 35;
/* 305:309 */     Item.Y = 18;
/* 306:310 */     Item.Rot = 6;
/* 307:311 */     Item.Z = 0;
/* 308:312 */     Item.extraData.setExtraData("");
/* 309:313 */     this.fuseObjects.add(Item);
/* 310:    */     
/* 311:315 */     Item = new GamefuseObject();
/* 312:316 */     Item.baseItem = BaseItem.snst_iceblock;
/* 313:317 */     Item.baseItem.allowWalk = false;
/* 314:318 */     Item.baseItem.Height = 1.0F;
/* 315:319 */     Item.itemId = 20;
/* 316:320 */     Item.X = 22;
/* 317:321 */     Item.Y = 21;
/* 318:322 */     Item.Rot = 0;
/* 319:323 */     Item.Z = 0;
/* 320:324 */     Item.extraData.setExtraData("");
/* 321:325 */     this.fuseObjects.add(Item);
/* 322:    */     
/* 323:327 */     Item = new GamefuseObject();
/* 324:328 */     Item.baseItem = BaseItem.snst_iceblock;
/* 325:329 */     Item.baseItem.allowWalk = false;
/* 326:330 */     Item.baseItem.Height = 1.0F;
/* 327:331 */     Item.itemId = 21;
/* 328:332 */     Item.X = 9;
/* 329:333 */     Item.Y = 23;
/* 330:334 */     Item.Rot = 4;
/* 331:335 */     Item.Z = 0;
/* 332:336 */     Item.extraData.setExtraData("");
/* 333:337 */     this.fuseObjects.add(Item);
/* 334:    */     
/* 335:339 */     Item = new GamefuseObject();
/* 336:340 */     Item.baseItem = BaseItem.snst_ballpile;
/* 337:341 */     Item.baseItem.allowWalk = true;
/* 338:342 */     Item.baseItem.Height = 0.0F;
/* 339:343 */     Item.itemId = 22;
/* 340:344 */     Item.X = 36;
/* 341:345 */     Item.Y = 30;
/* 342:346 */     Item.Rot = 0;
/* 343:347 */     Item.Z = 0;
/* 344:348 */     Item.extraData.setExtraData("0");
/* 345:349 */     this.fuseObjects.add(Item);
/* 346:    */     
/* 347:351 */     Item = new GamefuseObject();
/* 348:352 */     Item.baseItem = BaseItem.snst_ballpile;
/* 349:353 */     Item.baseItem.allowWalk = true;
/* 350:354 */     Item.baseItem.Height = 0.0F;
/* 351:355 */     Item.itemId = 23;
/* 352:356 */     Item.X = 22;
/* 353:357 */     Item.Y = 23;
/* 354:358 */     Item.Rot = 0;
/* 355:359 */     Item.Z = 0;
/* 356:360 */     Item.extraData.setExtraData("0");
/* 357:361 */     this.fuseObjects.add(Item);
/* 358:    */     
/* 359:363 */     Item = new GamefuseObject();
/* 360:364 */     Item.baseItem = BaseItem.snst_ballpile;
/* 361:365 */     Item.baseItem.allowWalk = true;
/* 362:366 */     Item.baseItem.Height = 0.0F;
/* 363:367 */     Item.itemId = 24;
/* 364:368 */     Item.X = 22;
/* 365:369 */     Item.Y = 19;
/* 366:370 */     Item.Rot = 0;
/* 367:371 */     Item.Z = 0;
/* 368:372 */     Item.extraData.setExtraData("0");
/* 369:373 */     this.fuseObjects.add(Item);
/* 370:    */     
/* 371:375 */     Item = new GamefuseObject();
/* 372:376 */     Item.baseItem = BaseItem.snst_ballpile;
/* 373:377 */     Item.baseItem.allowWalk = true;
/* 374:378 */     Item.baseItem.Height = 0.0F;
/* 375:379 */     Item.itemId = 25;
/* 376:380 */     Item.X = 8;
/* 377:381 */     Item.Y = 14;
/* 378:382 */     Item.Rot = 0;
/* 379:383 */     Item.Z = 0;
/* 380:384 */     Item.extraData.setExtraData("0");
/* 381:385 */     this.fuseObjects.add(Item);
/* 382:    */     
/* 383:387 */     Item = new GamefuseObject();
/* 384:388 */     Item.baseItem = BaseItem.snst_ballpile;
/* 385:389 */     Item.baseItem.allowWalk = true;
/* 386:390 */     Item.baseItem.Height = 0.0F;
/* 387:391 */     Item.itemId = 26;
/* 388:392 */     Item.X = 36;
/* 389:393 */     Item.Y = 15;
/* 390:394 */     Item.Rot = 0;
/* 391:395 */     Item.Z = 0;
/* 392:396 */     Item.extraData.setExtraData("0");
/* 393:397 */     this.fuseObjects.add(Item);
/* 394:    */     
/* 395:399 */     Item = new GamefuseObject();
/* 396:400 */     Item.baseItem = BaseItem.snst_iceblock;
/* 397:401 */     Item.baseItem.allowWalk = false;
/* 398:402 */     Item.baseItem.Height = 1.0F;
/* 399:403 */     Item.itemId = 27;
/* 400:404 */     Item.X = 35;
/* 401:405 */     Item.Y = 27;
/* 402:406 */     Item.Rot = 0;
/* 403:407 */     Item.Z = 0;
/* 404:408 */     Item.extraData.setExtraData("");
/* 405:409 */     this.fuseObjects.add(Item);
/* 406:    */     
/* 407:411 */     Item = new GamefuseObject();
/* 408:412 */     Item.baseItem = BaseItem.snst_iceblock;
/* 409:413 */     Item.baseItem.allowWalk = false;
/* 410:414 */     Item.baseItem.Height = 1.0F;
/* 411:415 */     Item.itemId = 28;
/* 412:416 */     Item.X = 35;
/* 413:417 */     Item.Y = 21;
/* 414:418 */     Item.Rot = 0;
/* 415:419 */     Item.Z = 0;
/* 416:420 */     Item.extraData.setExtraData("");
/* 417:421 */     this.fuseObjects.add(Item);
/* 418:    */     
/* 419:423 */     Item = new GamefuseObject();
/* 420:424 */     Item.baseItem = BaseItem.snst_iceblock;
/* 421:425 */     Item.baseItem.allowWalk = false;
/* 422:426 */     Item.baseItem.Height = 1.0F;
/* 423:427 */     Item.itemId = 29;
/* 424:428 */     Item.X = 26;
/* 425:429 */     Item.Y = 21;
/* 426:430 */     Item.Rot = 6;
/* 427:431 */     Item.Z = 0;
/* 428:432 */     Item.extraData.setExtraData("");
/* 429:433 */     this.fuseObjects.add(Item);
/* 430:    */     
/* 431:435 */     Item = new GamefuseObject();
/* 432:436 */     Item.baseItem = BaseItem.snst_ballpile;
/* 433:437 */     Item.baseItem.allowWalk = true;
/* 434:438 */     Item.baseItem.Height = 0.0F;
/* 435:439 */     Item.itemId = 30;
/* 436:440 */     Item.X = 8;
/* 437:441 */     Item.Y = 17;
/* 438:442 */     Item.Rot = 0;
/* 439:443 */     Item.Z = 0;
/* 440:444 */     Item.extraData.setExtraData("0");
/* 441:445 */     this.fuseObjects.add(Item);
/* 442:    */     
/* 443:447 */     Item = new GamefuseObject();
/* 444:448 */     Item.baseItem = BaseItem.snst_ballpile;
/* 445:449 */     Item.baseItem.allowWalk = true;
/* 446:450 */     Item.baseItem.Height = 0.0F;
/* 447:451 */     Item.itemId = 31;
/* 448:452 */     Item.X = 22;
/* 449:453 */     Item.Y = 15;
/* 450:454 */     Item.Rot = 0;
/* 451:455 */     Item.Z = 0;
/* 452:456 */     Item.extraData.setExtraData("0");
/* 453:457 */     this.fuseObjects.add(Item);
/* 454:    */     
/* 455:459 */     Item = new GamefuseObject();
/* 456:460 */     Item.baseItem = BaseItem.snst_iceblock;
/* 457:461 */     Item.baseItem.allowWalk = false;
/* 458:462 */     Item.baseItem.Height = 1.0F;
/* 459:463 */     Item.itemId = 32;
/* 460:464 */     Item.X = 9;
/* 461:465 */     Item.Y = 26;
/* 462:466 */     Item.Rot = 0;
/* 463:467 */     Item.Z = 0;
/* 464:468 */     Item.extraData.setExtraData("");
/* 465:469 */     this.fuseObjects.add(Item);
/* 466:    */     
/* 467:471 */     Item = new GamefuseObject();
/* 468:472 */     Item.baseItem = BaseItem.snst_ballpile;
/* 469:473 */     Item.baseItem.allowWalk = true;
/* 470:474 */     Item.baseItem.Height = 0.0F;
/* 471:475 */     Item.itemId = 33;
/* 472:476 */     Item.X = 16;
/* 473:477 */     Item.Y = 21;
/* 474:478 */     Item.Rot = 0;
/* 475:479 */     Item.Z = 0;
/* 476:480 */     Item.extraData.setExtraData("0");
/* 477:481 */     this.fuseObjects.add(Item);
/* 478:    */     
/* 479:483 */     Item = new GamefuseObject();
/* 480:484 */     Item.baseItem = BaseItem.snst_ballpile;
/* 481:485 */     Item.baseItem.allowWalk = true;
/* 482:486 */     Item.baseItem.Height = 0.0F;
/* 483:487 */     Item.itemId = 34;
/* 484:488 */     Item.X = 20;
/* 485:489 */     Item.Y = 21;
/* 486:490 */     Item.Rot = 0;
/* 487:491 */     Item.Z = 0;
/* 488:492 */     Item.extraData.setExtraData("0");
/* 489:493 */     this.fuseObjects.add(Item);
/* 490:    */     
/* 491:495 */     Item = new GamefuseObject();
/* 492:496 */     Item.baseItem = BaseItem.snst_ballpile;
/* 493:497 */     Item.baseItem.allowWalk = true;
/* 494:498 */     Item.baseItem.Height = 0.0F;
/* 495:499 */     Item.itemId = 35;
/* 496:500 */     Item.X = 36;
/* 497:501 */     Item.Y = 24;
/* 498:502 */     Item.Rot = 0;
/* 499:503 */     Item.Z = 0;
/* 500:504 */     Item.extraData.setExtraData("0");
/* 501:505 */     this.fuseObjects.add(Item);
/* 502:    */     
/* 503:507 */     Item = new GamefuseObject();
/* 504:508 */     Item.baseItem = BaseItem.ads_background;
/* 505:509 */     Item.baseItem.allowWalk = true;
/* 506:510 */     Item.baseItem.Height = 0.0F;
/* 507:511 */     Item.baseItem.itemExtraType = 1;
/* 508:512 */     Item.itemId = 36;
/* 509:513 */     Item.X = 0;
/* 510:514 */     Item.Y = 22;
/* 511:515 */     Item.Rot = 1;
/* 512:516 */     Item.Z = 0;
/* 513:517 */     Item.extraData = new MapStuffData("state=0\toffsetX=-1070\toffsetZ=9920\toffsetY=1520\timageUrl=http://dcr.lavvos.pl/lavvos/c_images/DEV_tests/snst_bg_2_big.png");
/* 514:518 */     this.fuseObjects.add(Item);
/* 515:    */     
/* 516:520 */     Item = new GamefuseObject();
/* 517:521 */     Item.baseItem = BaseItem.snst_ballpile;
/* 518:522 */     Item.baseItem.allowWalk = true;
/* 519:523 */     Item.baseItem.Height = 0.0F;
/* 520:524 */     Item.itemId = 37;
/* 521:525 */     Item.X = 8;
/* 522:526 */     Item.Y = 29;
/* 523:527 */     Item.Rot = 0;
/* 524:528 */     Item.Z = 0;
/* 525:529 */     Item.extraData.setExtraData("0");
/* 526:530 */     this.fuseObjects.add(Item);
/* 527:    */     
/* 528:532 */     this.spawnsBLUE.add(new SpawnPoint(10, 10));
/* 529:533 */     this.spawnsRED.add(new SpawnPoint(11, 11));
/* 530:    */   }
/* 531:    */   
/* 532:    */   public void gameObjects(Map<Integer, GameItemObject> gameObjects, SnowWarRoom room)
/* 533:    */   {
/* 534:538 */     gameObjects.put(Integer.valueOf(0), new PileGameObject(22, 27, 12, 12, 1, room.map, room));
/* 535:539 */     gameObjects.put(Integer.valueOf(1), new PileGameObject(8, 20, 12, 12, 2, room.map, room));
/* 536:540 */     gameObjects.put(Integer.valueOf(2), new PileGameObject(8, 26, 12, 12, 3, room.map, room));
/* 537:541 */     gameObjects.put(Integer.valueOf(3), new PileGameObject(36, 18, 12, 12, 8, room.map, room));
/* 538:542 */     gameObjects.put(Integer.valueOf(4), new PileGameObject(24, 21, 12, 12, 9, room.map, room));
/* 539:543 */     gameObjects.put(Integer.valueOf(5), new PileGameObject(8, 23, 12, 12, 12, room.map, room));
/* 540:544 */     gameObjects.put(Integer.valueOf(6), new PileGameObject(36, 27, 12, 12, 13, room.map, room));
/* 541:545 */     gameObjects.put(Integer.valueOf(7), new PileGameObject(36, 21, 12, 12, 15, room.map, room));
/* 542:546 */     gameObjects.put(Integer.valueOf(8), new PileGameObject(28, 21, 12, 12, 16, room.map, room));
/* 543:547 */     gameObjects.put(Integer.valueOf(9), new PileGameObject(36, 30, 12, 12, 22, room.map, room));
/* 544:548 */     gameObjects.put(Integer.valueOf(10), new PileGameObject(22, 23, 12, 12, 23, room.map, room));
/* 545:549 */     gameObjects.put(Integer.valueOf(11), new PileGameObject(22, 19, 12, 12, 24, room.map, room));
/* 546:550 */     gameObjects.put(Integer.valueOf(12), new PileGameObject(8, 14, 12, 12, 25, room.map, room));
/* 547:551 */     gameObjects.put(Integer.valueOf(13), new PileGameObject(36, 15, 12, 12, 26, room.map, room));
/* 548:552 */     gameObjects.put(Integer.valueOf(14), new PileGameObject(22, 15, 12, 12, 31, room.map, room));
/* 549:553 */     gameObjects.put(Integer.valueOf(15), new PileGameObject(16, 21, 12, 12, 33, room.map, room));
/* 550:554 */     gameObjects.put(Integer.valueOf(16), new PileGameObject(20, 21, 12, 12, 34, room.map, room));
/* 551:555 */     gameObjects.put(Integer.valueOf(17), new PileGameObject(36, 24, 12, 12, 35, room.map, room));
/* 552:556 */     gameObjects.put(Integer.valueOf(18), new PileGameObject(8, 29, 12, 12, 37, room.map, room));
/* 553:    */   }
/* 554:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.SnowWarArena9
 * JD-Core Version:    0.7.0.1
 */