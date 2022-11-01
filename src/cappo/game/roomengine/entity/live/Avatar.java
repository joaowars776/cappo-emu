/*   1:    */ package cappo.game.roomengine.entity.live;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.DBResult;
/*   4:    */ import cappo.engine.database.Database;
/*   5:    */ import cappo.engine.logging.Log;
/*   6:    */ import cappo.engine.network.FactorialServerHandler;
/*   7:    */ import cappo.engine.network.MessageWriter;
/*   8:    */ import cappo.engine.network.QueueWriter;
/*   9:    */ import cappo.engine.player.Clients;
/*  10:    */ import cappo.engine.player.Connection;
/*  11:    */ import cappo.engine.threadpools.RoomTask;
/*  12:    */ import cappo.game.catalog.Catalog;
/*  13:    */ import cappo.game.collections.BaseItem;
/*  14:    */ import cappo.game.collections.Utils;
/*  15:    */ import cappo.game.games.snowwar.Direction8;
/*  16:    */ import cappo.game.landing.LandingNews;
/*  17:    */ import cappo.game.moderation.UserMuted;
/*  18:    */ import cappo.game.navigator.officialrooms.OfficialRooms;
/*  19:    */ import cappo.game.player.AvatarLook;
/*  20:    */ import cappo.game.player.PlayerData;
/*  21:    */ import cappo.game.player.RightsManager;
/*  22:    */ import cappo.game.player.inventory.PlayerInventory;
/*  23:    */ import cappo.game.polls.PollManager;
/*  24:    */ import cappo.game.roomeffects.UserEffect;
/*  25:    */ import cappo.game.roomeffects.special.UserSpecialEffect;
/*  26:    */ import cappo.game.roomengine.RoomData;
/*  27:    */ import cappo.game.roomengine.chat.UserRoomMuted;
/*  28:    */ import cappo.game.roomengine.chat.wf.WordFilter;
/*  29:    */ import cappo.game.roomengine.chat.wf.WordFilterAction;
/*  30:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  31:    */ import cappo.game.roomengine.entity.item.floor.wired.trigger.WiredTriggerBase;
/*  32:    */ import cappo.game.roomengine.entity.item.wall.WallItem;
/*  33:    */ import cappo.game.roomengine.roomevents.UserChat;
/*  34:    */ import cappo.game.roomengine.roomevents.User_WALK;
/*  35:    */ import cappo.game.roomengine.wired.WiredManager;
/*  36:    */ import cappo.game.roomgames.RoomGamePlayer;
/*  37:    */ import cappo.game.sound.trax.Trax;
/*  38:    */ import cappo.protocol.messages.Composer;
/*  39:    */ import cappo.protocol.messages.composers.inventory.furni.FurniListAddOrUpdateComposer;
/*  40:    */ import cappo.protocol.messages.composers.inventory.furni.FurniListUpdateComposer;
/*  41:    */ import cappo.protocol.messages.composers.inventory.purse.CreditBalanceComposer;
/*  42:    */ import cappo.protocol.messages.composers.notifications.HabboActivityPointNotificationComposer;
/*  43:    */ import cappo.protocol.messages.composers.notifications.HabboBroadcastCustomComposer;
/*  44:    */ import cappo.protocol.messages.composers.room.action.CarryObjectComposer;
/*  45:    */ import cappo.protocol.messages.composers.room.action.UserDanceComposer;
/*  46:    */ import cappo.protocol.messages.composers.room.action.UserEffectComposer;
/*  47:    */ import cappo.protocol.messages.composers.room.chat.ChatComposer;
/*  48:    */ import cappo.protocol.messages.composers.room.chat.FloodControlComposer;
/*  49:    */ import cappo.protocol.messages.composers.room.chat.ShoutComposer;
/*  50:    */ import cappo.protocol.messages.composers.room.chat.WhisperComposer;
/*  51:    */ import cappo.protocol.messages.composers.room.engine.UserChangeComposer;
/*  52:    */ import cappo.protocol.messages.composers.serializers.SerializeSay;
/*  53:    */ import io.netty.channel.Channel;
/*  54:    */ import io.netty.channel.group.DefaultChannelGroup;
/*  55:    */ import io.netty.util.Attribute;
/*  56:    */ import java.util.ArrayList;
/*  57:    */ import java.util.Collection;
/*  58:    */ import java.util.HashMap;
/*  59:    */ import java.util.Iterator;
/*  60:    */ import java.util.List;
/*  61:    */ import java.util.Map;
/*  62:    */ 
/*  63:    */ public class Avatar
/*  64:    */   extends LiveEntity
/*  65:    */ {
/*  66:    */   public int carryItemID;
/*  67:    */   public int carryTimer;
/*  68:    */   public Connection cn;
/*  69:    */   public int DanceId;
/*  70: 67 */   public int floodCount = 0;
/*  71:    */   public int id;
/*  72:    */   public int idleTime;
/*  73:    */   public boolean IsAsleep;
/*  74:    */   public boolean IsBuyEffect;
/*  75:    */   public boolean IsDancing;
/*  76: 73 */   public long lastSay = 0L;
/*  77:    */   public int controllerLevel;
/*  78:    */   public RoomGamePlayer roomGamePlayer;
/*  79:    */   public UserEffect userEffect;
/*  80:    */   public UserSpecialEffect userSpecialEffect;
/*  81:    */   public UserRoomMuted userRoomMuted;
/*  82:    */   public List<FloorItem> onFloorItems;
/*  83:    */   public UserChat evtChat;
/*  84:    */   
/*  85:    */   public Avatar(Connection User, RoomTask room, short virtualId)
/*  86:    */   {
/*  87: 88 */     super(room, virtualId);
/*  88:    */     
/*  89: 90 */     this.id = User.getPlayerData().userId;
/*  90: 91 */     this.cn = User;
/*  91: 92 */     this.evtChat = new UserChat();
/*  92: 93 */     this.idleTime = 0;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void CarryItem(int ItemId)
/*  96:    */   {
/*  97: 97 */     this.carryItemID = ItemId;
/*  98: 98 */     this.carryTimer = (ItemId > 0 ? 240 : 0);
/*  99: 99 */     this.room.sendMessage(CarryObjectComposer.compose(this.virtualId, ItemId));
/* 100:    */   }
/* 101:    */   
/* 102:    */   public boolean equals(Object arg0)
/* 103:    */   {
/* 104:104 */     if ((arg0 instanceof Avatar)) {
/* 105:105 */       return ((Avatar)arg0).id == this.id;
/* 106:    */     }
/* 107:107 */     return false;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void SetRot(Direction8 Rotation, boolean HeadOnly)
/* 111:    */   {
/* 112:112 */     if (this.evtWalk.isWalking) {
/* 113:113 */       return;
/* 114:    */     }
/* 115:116 */     super.SetRot(Rotation, HeadOnly);
/* 116:    */   }
/* 117:    */   
/* 118:    */   public boolean canChat()
/* 119:    */   {
/* 120:120 */     if (this.cn.userMuted != null)
/* 121:    */     {
/* 122:121 */       if (this.cn.userMuted.isMuted()) {
/* 123:122 */         return false;
/* 124:    */       }
/* 125:124 */       this.cn.userMuted = null;
/* 126:    */     }
/* 127:127 */     if (this.userRoomMuted != null)
/* 128:    */     {
/* 129:128 */       if (this.userRoomMuted.isMuted()) {
/* 130:129 */         return false;
/* 131:    */       }
/* 132:131 */       this.userRoomMuted = null;
/* 133:    */     }
/* 134:134 */     if ((this.room.roomData.muteAllOn) && 
/* 135:135 */       (this.controllerLevel < 4)) {
/* 136:136 */       return false;
/* 137:    */     }
/* 138:140 */     long currentTime = System.currentTimeMillis();
/* 139:141 */     if (this.floodCount == -1)
/* 140:    */     {
/* 141:142 */       if (this.lastSay > currentTime) {
/* 142:143 */         return false;
/* 143:    */       }
/* 144:146 */       this.floodCount = 0;
/* 145:    */     }
/* 146:    */     else
/* 147:    */     {
/* 148:148 */       if (this.controllerLevel < 5) {
/* 149:149 */         this.lastSay += 2000L;
/* 150:    */       } else {
/* 151:151 */         this.lastSay += 500L;
/* 152:    */       }
/* 153:153 */       if (this.lastSay > currentTime)
/* 154:    */       {
/* 155:154 */         if (++this.floodCount > 4)
/* 156:    */         {
/* 157:155 */           int Time = 15;
/* 158:    */           
/* 159:157 */           QueueWriter.writeAndFlush(this.cn.socket, FloodControlComposer.compose(15));
/* 160:    */           
/* 161:159 */           this.floodCount = -1;
/* 162:160 */           this.lastSay = (currentTime + 15000L);
/* 163:161 */           return false;
/* 164:    */         }
/* 165:    */       }
/* 166:    */       else {
/* 167:164 */         this.floodCount = 0;
/* 168:    */       }
/* 169:    */     }
/* 170:167 */     this.lastSay = currentTime;
/* 171:    */     
/* 172:169 */     return true;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public void say(String message, int styleId, int sayId, boolean isShout)
/* 176:    */   {
/* 177:174 */     if (sayId > 20) {
/* 178:175 */       sayId = 0;
/* 179:    */     }
/* 180:178 */     if (!canChat()) {
/* 181:179 */       return;
/* 182:    */     }
/* 183:182 */     message = message.trim();
/* 184:184 */     if ((message.isEmpty()) || (message.length() > 100)) {
/* 185:185 */       return;
/* 186:    */     }
/* 187:    */     try
/* 188:    */     {
/* 189:189 */       if ((message.charAt(0) == ':') && (parseCmd(message))) {
/* 190:190 */         return;
/* 191:    */       }
/* 192:    */     }
/* 193:    */     catch (Exception localException)
/* 194:    */     {
/* 195:197 */       if (WiredTriggerBase.launchTriggers(this.room.wiredManager.triggersUserSays, this.cn, message))
/* 196:    */       {
/* 197:198 */         QueueWriter.writeAndFlush(this.cn.socket, WhisperComposer.compose(this.virtualId, message, 0, 0, new ArrayList(), 0));
/* 198:199 */         return;
/* 199:    */       }
/* 200:202 */       this.room.onUserSay(this, message);
/* 201:    */       
/* 202:    */ 
/* 203:    */ 
/* 204:206 */       WordFilterAction action = WordFilter.getAction(message);
/* 205:207 */       if ((action != null) && (action.run(this.cn))) {
/* 206:208 */         return;
/* 207:    */       }
/* 208:211 */       List<String> Urls = new ArrayList();
/* 209:212 */       message = ParseMessage(message, Urls);
/* 210:214 */       if ((styleId == 1) || (styleId == 2)) {
/* 211:217 */         styleId = 0;
/* 212:    */       }
/* 213:220 */       if ((styleId == 23) && (!this.cn.getPlayerData().allowModTools())) {
/* 214:223 */         styleId = 0;
/* 215:    */       }
/* 216:226 */       if (this.cn.getPlayerData().useChatBot()) {
/* 217:227 */         styleId = 2;
/* 218:    */       }
/* 219:231 */       if (isShout) {
/* 220:232 */         this.evtChat.talk(this.room, ShoutComposer.compose(this.virtualId, message, GetSpeechEmotion(message), styleId, Urls, sayId));
/* 221:    */       } else {
/* 222:234 */         this.evtChat.talk(this.room, ChatComposer.compose(this.virtualId, message, GetSpeechEmotion(message), styleId, Urls, sayId));
/* 223:    */       }
/* 224:    */     }
/* 225:    */   }
/* 226:    */   
/* 227:    */   public static int GetSpeechEmotion(String Message)
/* 228:    */   {
/* 229:239 */     Message = Message.toLowerCase();
/* 230:240 */     if ((Message.contains(":)")) || (Message.contains(":d")) || (Message.contains("=]")) || (Message.contains("=d")) || (Message.contains(":>"))) {
/* 231:241 */       return 1;
/* 232:    */     }
/* 233:243 */     if ((Message.contains(">:(")) || (Message.contains(":@"))) {
/* 234:244 */       return 2;
/* 235:    */     }
/* 236:246 */     if (Message.contains(":o")) {
/* 237:247 */       return 3;
/* 238:    */     }
/* 239:249 */     if ((Message.contains(":(")) || (Message.contains("=[")) || (Message.contains(":'(")) || (Message.contains("='["))) {
/* 240:250 */       return 4;
/* 241:    */     }
/* 242:252 */     return 0;
/* 243:    */   }
/* 244:    */   
/* 245:    */   public boolean parseCmd(String cmd)
/* 246:    */   {
/* 247:256 */     String[] parsed = cmd.split(" ");
/* 248:    */     MessageWriter clientMessage;
/* 249:258 */     if (parsed[0].equals(":test"))
/* 250:    */     {
/* 251:259 */       int header = Integer.parseInt(parsed[1]);
/* 252:260 */       List<String> urls = new ArrayList();
/* 253:261 */       clientMessage = new MessageWriter();
/* 254:262 */       Composer.initPacket(header, clientMessage);
/* 255:263 */       SerializeSay.parse(clientMessage, this.virtualId, "test:" + header, 0, 0, urls, 0);
/* 256:264 */       Composer.endPacket(clientMessage);
/* 257:265 */       this.evtChat.talk(this.room, clientMessage);
/* 258:266 */       return true;
/* 259:    */     }
/* 260:275 */     if (parsed[0].equals(":commands"))
/* 261:    */     {
/* 262:276 */       String response = "Commands:\n";
/* 263:277 */       response = response + ":pickall\n";
/* 264:278 */       response = response + ":copy\n";
/* 265:279 */       response = response + ":moonwalk\n";
/* 266:280 */       response = response + ":toggletrade\n";
/* 267:281 */       response = response + ":togglediagonal\n";
/* 268:282 */       response = response + ":clearhand\n";
/* 269:283 */       response = response + ":effect\n";
/* 270:284 */       PlayerData playerData = this.cn.getPlayerData();
/* 271:286 */       if (playerData.staffLevel > 1)
/* 272:    */       {
/* 273:287 */         response = response + "\nStaff Commands:\n";
/* 274:289 */         if (playerData.staffLevel > 2)
/* 275:    */         {
/* 276:290 */           response = response + ":gethere\n";
/* 277:291 */           response = response + ":follow\n";
/* 278:    */         }
/* 279:294 */         if (playerData.allowRoomAlert()) {
/* 280:295 */           response = response + ":ra\n";
/* 281:    */         }
/* 282:298 */         if (playerData.allowHotelAlert())
/* 283:    */         {
/* 284:299 */           response = response + ":ha\n";
/* 285:300 */           response = response + ":hal\n";
/* 286:301 */           response = response + ":popout\n";
/* 287:302 */           response = response + ":bubble\n";
/* 288:303 */           response = response + ":rdance\n";
/* 289:    */         }
/* 290:306 */         if (playerData.allowHotelImageAlert()) {
/* 291:307 */           response = response + ":hia\n";
/* 292:    */         }
/* 293:310 */         if (playerData.allowDataReload())
/* 294:    */         {
/* 295:311 */           response = response + ":loadcata\n";
/* 296:312 */           response = response + ":loadwf\n";
/* 297:313 */           response = response + ":loadlanding\n";
/* 298:314 */           response = response + ":loadofficials\n";
/* 299:315 */           response = response + ":loadrights\n";
/* 300:316 */           response = response + ":reloaduser\n";
/* 301:317 */           response = response + ":setmax\n";
/* 302:    */         }
/* 303:320 */         if (playerData.allowGiveBadge())
/* 304:    */         {
/* 305:321 */           response = response + ":givebadge\n";
/* 306:322 */           response = response + ":massbadge\n";
/* 307:323 */           response = response + ":rbadge\n";
/* 308:    */         }
/* 309:326 */         if (playerData.allowGiveMoney())
/* 310:    */         {
/* 311:327 */           response = response + ":givediamonds\n";
/* 312:328 */           response = response + ":massdiamonds\n";
/* 313:329 */           response = response + ":rdiamonds\n";
/* 314:    */           
/* 315:331 */           response = response + ":giveducks\n";
/* 316:332 */           response = response + ":massducks\n";
/* 317:333 */           response = response + ":rducks\n";
/* 318:    */           
/* 319:335 */           response = response + ":givecredits\n";
/* 320:336 */           response = response + ":masscredits\n";
/* 321:337 */           response = response + ":rcredits\n";
/* 322:    */           
/* 323:339 */           response = response + ":delbadge\n";
/* 324:340 */           response = response + ":delbadges\n";
/* 325:    */         }
/* 326:    */       }
/* 327:343 */       Utils.AlertFromHotel(this.cn.socket, response);
/* 328:344 */       return true;
/* 329:    */     }
/* 330:347 */     if (parsed[0].equals(":pickall"))
/* 331:    */     {
/* 332:348 */       PlayerData playerData = this.cn.getPlayerData();
/* 333:350 */       for (WallItem wallItem : this.room.WallItems.values()) {
/* 334:351 */         if (wallItem.owner.userId == playerData.userId)
/* 335:    */         {
/* 336:355 */           this.room.removeWallItem(wallItem, playerData.userId);
/* 337:357 */           if (wallItem.baseItem.itemCategory != 6)
/* 338:    */           {
/* 339:362 */             this.cn.inventoryAddWallItem(wallItem);
/* 340:363 */             wallItem.setMysqlState(2);
/* 341:    */             
/* 342:365 */             QueueWriter.writeAndFlush(this.cn.socket, FurniListAddOrUpdateComposer.compose(wallItem));
/* 343:    */           }
/* 344:    */         }
/* 345:    */       }
/* 346:368 */       for (FloorItem floorItem : this.room.FloorItems.values()) {
/* 347:369 */         if (floorItem.owner.userId == playerData.userId)
/* 348:    */         {
/* 349:373 */           this.room.removeFloorItem(floorItem, playerData.userId);
/* 350:374 */           this.cn.inventoryAddFloorItem(floorItem);
/* 351:375 */           floorItem.setMysqlState(2);
/* 352:    */           
/* 353:377 */           QueueWriter.writeAndFlush(this.cn.socket, FurniListAddOrUpdateComposer.compose(floorItem));
/* 354:    */         }
/* 355:    */       }
/* 356:380 */       return true;
/* 357:    */     }
/* 358:    */     PlayerData playerData;
/* 359:383 */     if (parsed[0].equals(":copy"))
/* 360:    */     {
/* 361:384 */       if (parsed.length < 2) {
/* 362:385 */         return false;
/* 363:    */       }
/* 364:388 */       PlayerData player = Clients.getPlayerDataLoaded(parsed[1]);
/* 365:389 */       if (player == null) {
/* 366:390 */         return false;
/* 367:    */       }
/* 368:393 */       playerData = this.cn.getPlayerData();
/* 369:    */       
/* 370:395 */       playerData.avatarLook = player.avatarLook;
/* 371:396 */       playerData.sex = player.sex;
/* 372:    */       
/* 373:398 */       QueueWriter.writeAndFlush(this.cn.socket, UserChangeComposer.compose(-1, playerData.avatarLook.toString(), playerData.sex, playerData.motto, playerData.AchievementsScore));
/* 374:399 */       this.room.sendMessage(UserChangeComposer.compose(this.virtualId, playerData.avatarLook.toString(), playerData.sex, playerData.motto, playerData.AchievementsScore));
/* 375:400 */       return true;
/* 376:    */     }
/* 377:403 */     if (parsed[0].equals(":moonwalk"))
/* 378:    */     {
/* 379:404 */       this.cn.xorFlag(32);
/* 380:405 */       return true;
/* 381:    */     }
/* 382:408 */     if (parsed[0].equals(":toggletrade"))
/* 383:    */     {
/* 384:409 */       this.cn.xorFlag(8);
/* 385:410 */       return true;
/* 386:    */     }
/* 387:413 */     if (parsed[0].equals(":togglediagonal"))
/* 388:    */     {
/* 389:414 */       this.cn.xorFlag(64);
/* 390:415 */       return true;
/* 391:    */     }
/* 392:418 */     if (parsed[0].equals(":effect"))
/* 393:    */     {
/* 394:419 */       this.room.sendMessage(UserEffectComposer.compose(this.virtualId, Integer.parseInt(parsed[1])));
/* 395:420 */       return true;
/* 396:    */     }
/* 397:424 */     if (parsed[0].equals(":clearhand"))
/* 398:    */     {
/* 399:425 */       for (RoomData data : this.cn.ownRooms.values())
/* 400:    */       {
/* 401:426 */         RoomTask room = data.room;
/* 402:427 */         if (room != null) {
/* 403:431 */           room.updateMysqlData();
/* 404:    */         }
/* 405:    */       }
/* 406:434 */       this.cn.saveItems();
/* 407:435 */       this.cn.saveObjects();
/* 408:    */       
/* 409:437 */       this.cn.inventory.clearFurnis();
/* 410:438 */       QueueWriter.writeAndFlush(this.cn.socket, FurniListUpdateComposer.compose());
/* 411:    */       try
/* 412:    */       {
/* 413:441 */         Database.exec(
/* 414:    */         
/* 415:    */ 
/* 416:    */ 
/* 417:445 */           "DELETE da,db,dc FROM furnis AS da LEFT JOIN furnis_roomdata AS db ON db.id=da.id LEFT JOIN furnis_floorextra AS dc ON dc.id=da.id WHERE da.userid=" + this.cn.getPlayerData().userId + " AND da.roomid=0;", new Object[0]);
/* 418:    */       }
/* 419:    */       catch (Exception ex)
/* 420:    */       {
/* 421:447 */         Log.printException("Item-Delete", ex);
/* 422:    */       }
/* 423:450 */       return true;
/* 424:    */     }
/* 425:453 */     PlayerData playerData = this.cn.getPlayerData();
/* 426:455 */     if (playerData.staffLevel > 1)
/* 427:    */     {
/* 428:    */       Avatar avatar;
/* 429:456 */       if (playerData.staffLevel > 2)
/* 430:    */       {
/* 431:457 */         if (parsed[0].equals(":follow"))
/* 432:    */         {
/* 433:458 */           PlayerData client = Clients.getPlayerDataLoaded(parsed[1]);
/* 434:459 */           if ((client == null) || (client.connection == null)) {
/* 435:460 */             return false;
/* 436:    */           }
/* 437:463 */           avatar = client.connection.avatar;
/* 438:464 */           if (avatar == null) {
/* 439:465 */             return false;
/* 440:    */           }
/* 441:468 */           this.cn.loadRoom(avatar.room.roomId, avatar.room.roomData.password);
/* 442:469 */           return true;
/* 443:    */         }
/* 444:472 */         if (parsed[0].equals(":gethere"))
/* 445:    */         {
/* 446:473 */           PlayerData client = Clients.getPlayerDataLoaded(parsed[1]);
/* 447:474 */           if ((client == null) || (client.connection == null)) {
/* 448:475 */             return false;
/* 449:    */           }
/* 450:478 */           client.connection.loadRoom(this.room.roomId, this.room.roomData.password);
/* 451:479 */           return true;
/* 452:    */         }
/* 453:    */       }
/* 454:483 */       if ((playerData.allowRoomAlert()) && 
/* 455:484 */         (parsed[0].equals(":ra")))
/* 456:    */       {
/* 457:485 */         for (Avatar user : this.room.userList.values()) {
/* 458:486 */           Utils.AlertFromHotel(user.cn.socket, cmd.substring(4) + "\n\n- " + playerData.userName);
/* 459:    */         }
/* 460:488 */         return true;
/* 461:    */       }
/* 462:492 */       if (playerData.allowHotelAlert())
/* 463:    */       {
/* 464:493 */         if (parsed[0].equals(":ha"))
/* 465:    */         {
/* 466:494 */           Utils.AlertFromHotel(FactorialServerHandler.channels, cmd.substring(4) + "\n\n- " + playerData.userName);
/* 467:495 */           return true;
/* 468:    */         }
/* 469:498 */         if (parsed[0].equals(":popout"))
/* 470:    */         {
/* 471:499 */           Map<String, String> popout = new HashMap();
/* 472:500 */           popout.put("display", "POP_UP");
/* 473:501 */           FactorialServerHandler.channels.writeAndFlush(
/* 474:502 */             HabboBroadcastCustomComposer.compose(parsed[1], popout));
/* 475:    */           
/* 476:504 */           return true;
/* 477:    */         }
/* 478:507 */         if (parsed[0].equals(":bubble"))
/* 479:    */         {
/* 480:508 */           Map<String, String> bubble = new HashMap();
/* 481:509 */           bubble.put("display", "BUBBLE");
/* 482:510 */           FactorialServerHandler.channels.writeAndFlush(
/* 483:511 */             HabboBroadcastCustomComposer.compose(parsed[1], bubble));
/* 484:    */           
/* 485:513 */           return true;
/* 486:    */         }
/* 487:516 */         if (parsed[0].equals(":hal"))
/* 488:    */         {
/* 489:517 */           FactorialServerHandler.channels.writeAndFlush(
/* 490:518 */             HabboBroadcastCustomComposer.compose(parsed[1], null));
/* 491:    */           
/* 492:520 */           return true;
/* 493:    */         }
/* 494:523 */         if (parsed[0].equals(":rdance"))
/* 495:    */         {
/* 496:524 */           int DanceId = Integer.parseInt(parsed[1]);
/* 497:525 */           if (DanceId != 1) {
/* 498:526 */             if (DanceId < 0) {
/* 499:527 */               DanceId = 0;
/* 500:528 */             } else if (DanceId > 8) {
/* 501:529 */               DanceId = 0;
/* 502:    */             }
/* 503:    */           }
/* 504:533 */           for (Avatar user : this.room.userList.values())
/* 505:    */           {
/* 506:534 */             if ((DanceId > 0) && (user.carryItemID > 0)) {
/* 507:535 */               user.CarryItem(0);
/* 508:    */             }
/* 509:537 */             user.DanceId = DanceId;
/* 510:538 */             this.room.sendMessage(UserDanceComposer.compose(user.virtualId, DanceId));
/* 511:    */           }
/* 512:541 */           return true;
/* 513:    */         }
/* 514:    */       }
/* 515:545 */       if ((playerData.allowHotelImageAlert()) && 
/* 516:546 */         (parsed[0].equals(":hia")))
/* 517:    */       {
/* 518:547 */         Utils.broadcastImage(FactorialServerHandler.channels, parsed[1]);
/* 519:548 */         return true;
/* 520:    */       }
/* 521:552 */       if (parsed[0].equals(":capoloadcata"))
/* 522:    */       {
/* 523:553 */         Catalog.block();
/* 524:554 */         DBResult result = new DBResult();
/* 525:    */         try
/* 526:    */         {
/* 527:556 */           Trax.Init(result);
/* 528:557 */           BaseItem.Init(result);
/* 529:558 */           Catalog.Init(result);
/* 530:    */         }
/* 531:    */         catch (Exception ex)
/* 532:    */         {
/* 533:560 */           Log.printException("Avatar", ex);
/* 534:    */         }
/* 535:562 */         result.close();
/* 536:563 */         Catalog.unblock();
/* 537:564 */         return true;
/* 538:    */       }
/* 539:567 */       if (playerData.allowDataReload())
/* 540:    */       {
/* 541:568 */         if (parsed[0].equals(":loadcata"))
/* 542:    */         {
/* 543:569 */           Catalog.block();
/* 544:570 */           DBResult result = new DBResult();
/* 545:    */           try
/* 546:    */           {
/* 547:572 */             Trax.Init(result);
/* 548:573 */             BaseItem.Init(result);
/* 549:574 */             Catalog.Init(result);
/* 550:    */           }
/* 551:    */           catch (Exception ex)
/* 552:    */           {
/* 553:576 */             Log.printException("Avatar", ex);
/* 554:    */           }
/* 555:578 */           result.close();
/* 556:579 */           Catalog.unblock();
/* 557:580 */           return true;
/* 558:    */         }
/* 559:583 */         if (parsed[0].equals(":loadwf"))
/* 560:    */         {
/* 561:584 */           DBResult result = new DBResult();
/* 562:    */           try
/* 563:    */           {
/* 564:586 */             WordFilter.init(result);
/* 565:    */           }
/* 566:    */           catch (Exception ex)
/* 567:    */           {
/* 568:588 */             Log.printException("Avatar", ex);
/* 569:    */           }
/* 570:590 */           result.close();
/* 571:591 */           return true;
/* 572:    */         }
/* 573:594 */         if (parsed[0].equals(":loadlanding"))
/* 574:    */         {
/* 575:595 */           DBResult result = new DBResult();
/* 576:    */           try
/* 577:    */           {
/* 578:597 */             LandingNews.Init(result);
/* 579:    */           }
/* 580:    */           catch (Exception ex)
/* 581:    */           {
/* 582:599 */             Log.printException("Avatar", ex);
/* 583:    */           }
/* 584:601 */           result.close();
/* 585:602 */           return true;
/* 586:    */         }
/* 587:605 */         if (parsed[0].equals(":loadofficials"))
/* 588:    */         {
/* 589:606 */           DBResult result = new DBResult();
/* 590:    */           try
/* 591:    */           {
/* 592:608 */             OfficialRooms.init(result);
/* 593:    */           }
/* 594:    */           catch (Exception ex)
/* 595:    */           {
/* 596:610 */             Log.printException("Officials", ex);
/* 597:    */           }
/* 598:612 */           result.close();
/* 599:613 */           return true;
/* 600:    */         }
/* 601:616 */         if (parsed[0].equals(":loadrights"))
/* 602:    */         {
/* 603:617 */           DBResult result = new DBResult();
/* 604:    */           try
/* 605:    */           {
/* 606:619 */             RightsManager.load(result);
/* 607:    */           }
/* 608:    */           catch (Exception ex)
/* 609:    */           {
/* 610:621 */             Log.printException("loadRights", ex);
/* 611:    */           }
/* 612:623 */           result.close();
/* 613:624 */           return true;
/* 614:    */         }
/* 615:627 */         if (parsed[0].equals(":loadpolls"))
/* 616:    */         {
/* 617:628 */           DBResult result = new DBResult();
/* 618:    */           try
/* 619:    */           {
/* 620:630 */             PollManager.load(result);
/* 621:    */           }
/* 622:    */           catch (Exception ex)
/* 623:    */           {
/* 624:632 */             Log.printException("loadPolls", ex);
/* 625:    */           }
/* 626:634 */           result.close();
/* 627:635 */           return true;
/* 628:    */         }
/* 629:638 */         if (parsed[0].equals(":reloaduser"))
/* 630:    */         {
/* 631:639 */           PlayerData client = Clients.getPlayerDataLoaded(parsed[1]);
/* 632:640 */           if (client == null)
/* 633:    */           {
/* 634:641 */             Utils.AlertFromHotel(this.cn.socket, "User is not loaded, so not need reload!");
/* 635:642 */             return true;
/* 636:    */           }
/* 637:645 */           if (client.connection != null)
/* 638:    */           {
/* 639:646 */             Utils.AlertFromHotel(this.cn.socket, "User is connected, you cant reload user!");
/* 640:647 */             return true;
/* 641:    */           }
/* 642:650 */           Clients.deleteID(client.userId);
/* 643:651 */           return true;
/* 644:    */         }
/* 645:655 */         if (parsed[0].equals(":setmax"))
/* 646:    */         {
/* 647:656 */           RoomData roomData = this.room.roomData;
/* 648:657 */           if (roomData == null) {
/* 649:658 */             return false;
/* 650:    */           }
/* 651:661 */           int maxUsers = Integer.parseInt(parsed[1]);
/* 652:662 */           if ((maxUsers % 5 == 0) && (
/* 653:663 */             (maxUsers > 300) || (maxUsers < 10))) {
/* 654:664 */             return false;
/* 655:    */           }
/* 656:667 */           roomData.updateMaxUsers(maxUsers);
/* 657:    */           
/* 658:669 */           return true;
/* 659:    */         }
/* 660:    */       }
/* 661:674 */       if (playerData.allowGiveBadge())
/* 662:    */       {
/* 663:675 */         if (parsed[0].equals(":givebadge"))
/* 664:    */         {
/* 665:676 */           PlayerData Client = Clients.getPlayerDataLoaded(parsed[1]);
/* 666:677 */           if ((Client == null) || (Client.connection == null)) {
/* 667:678 */             return false;
/* 668:    */           }
/* 669:681 */           String badgeid = parsed[2];
/* 670:682 */           if (badgeid.contains("?")) {
/* 671:683 */             return false;
/* 672:    */           }
/* 673:686 */           Client.connection.giveBadge(badgeid);
/* 674:687 */           return true;
/* 675:    */         }
/* 676:    */         Object ch;
/* 677:690 */         if (parsed[0].equals(":massbadge"))
/* 678:    */         {
/* 679:691 */           String badgeid = parsed[1];
/* 680:692 */           if (badgeid.contains("?")) {
/* 681:693 */             return false;
/* 682:    */           }
/* 683:696 */           Iterator<Channel> itr = FactorialServerHandler.channels.iterator();
/* 684:697 */           while (itr.hasNext())
/* 685:    */           {
/* 686:698 */             ch = (Channel)itr.next();
/* 687:699 */             Connection con = (Connection)((Channel)ch).attr(FactorialServerHandler.CONNECTION).get();
/* 688:700 */             con.giveBadge(badgeid);
/* 689:    */           }
/* 690:702 */           return true;
/* 691:    */         }
/* 692:705 */         if (parsed[0].equals(":rbadge"))
/* 693:    */         {
/* 694:706 */           String badgeid = parsed[1];
/* 695:707 */           for (ch = this.room.userList.values().iterator(); ((Iterator)ch).hasNext();)
/* 696:    */           {
/* 697:707 */             Avatar user = (Avatar)((Iterator)ch).next();
/* 698:708 */             if (badgeid.contains("?")) {
/* 699:709 */               return false;
/* 700:    */             }
/* 701:712 */             user.cn.giveBadge(badgeid);
/* 702:    */           }
/* 703:715 */           return true;
/* 704:    */         }
/* 705:    */       }
/* 706:719 */       if (playerData.allowGiveMoney())
/* 707:    */       {
/* 708:720 */         if (parsed[0].equals(":givediamonds"))
/* 709:    */         {
/* 710:721 */           PlayerData Client = Clients.getPlayerDataLoaded(parsed[1]);
/* 711:722 */           if ((Client == null) || (Client.connection == null)) {
/* 712:723 */             return false;
/* 713:    */           }
/* 714:726 */           Connection plrConnection = Client.connection;
/* 715:    */           
/* 716:728 */           int give = Integer.parseInt(parsed[2]);
/* 717:729 */           QueueWriter.writeAndFlush(Client.connection.socket, HabboActivityPointNotificationComposer.compose(plrConnection.diamondAmmount += give, give, 3));
/* 718:730 */           Utils.AlertFromHotel(Client.connection.socket, cappo.game.utils.lang.LangTexts.texts[0] + give + cappo.game.utils.lang.LangTexts.texts[9] + "\n\n- " + playerData.userName);
/* 719:731 */           return true;
/* 720:    */         }
/* 721:    */         Object ch;
/* 722:733 */         if (parsed[0].equals(":massdiamonds"))
/* 723:    */         {
/* 724:734 */           int give = Integer.parseInt(parsed[1]);
/* 725:    */           
/* 726:736 */           Iterator<Channel> itr = FactorialServerHandler.channels.iterator();
/* 727:737 */           while (itr.hasNext())
/* 728:    */           {
/* 729:738 */             ch = (Channel)itr.next();
/* 730:739 */             Connection con = (Connection)((Channel)ch).attr(FactorialServerHandler.CONNECTION).get();
/* 731:741 */             if (con.avatarData != null) {
/* 732:745 */               QueueWriter.writeAndFlush((Channel)ch, HabboActivityPointNotificationComposer.compose(con.diamondAmmount += give, give, 3));
/* 733:    */             }
/* 734:    */           }
/* 735:748 */           Utils.AlertFromHotel(FactorialServerHandler.channels, cappo.game.utils.lang.LangTexts.texts[0] + give + cappo.game.utils.lang.LangTexts.texts[9] + "\n\n- " + playerData.userName);
/* 736:749 */           return true;
/* 737:    */         }
/* 738:751 */         if (parsed[0].equals(":rdiamonds"))
/* 739:    */         {
/* 740:752 */           int give = Integer.parseInt(parsed[1]);
/* 741:753 */           for (ch = this.room.userList.values().iterator(); ((Iterator)ch).hasNext();)
/* 742:    */           {
/* 743:753 */             Avatar user = (Avatar)((Iterator)ch).next();
/* 744:754 */             Connection con = user.cn;
/* 745:755 */             if (con != null)
/* 746:    */             {
/* 747:759 */               QueueWriter.writeAndFlush(con.socket, HabboActivityPointNotificationComposer.compose(con.diamondAmmount += give, give, 3));
/* 748:760 */               Utils.AlertFromHotel(con.socket, cappo.game.utils.lang.LangTexts.texts[0] + give + cappo.game.utils.lang.LangTexts.texts[9] + "\n\n- " + playerData.userName);
/* 749:    */             }
/* 750:    */           }
/* 751:763 */           return true;
/* 752:    */         }
/* 753:766 */         if (parsed[0].equals(":giveducks"))
/* 754:    */         {
/* 755:767 */           PlayerData Client = Clients.getPlayerDataLoaded(parsed[1]);
/* 756:768 */           if ((Client == null) || (Client.connection == null)) {
/* 757:769 */             return false;
/* 758:    */           }
/* 759:772 */           Connection plrConnection = Client.connection;
/* 760:    */           
/* 761:774 */           int give = Integer.parseInt(parsed[2]);
/* 762:775 */           QueueWriter.writeAndFlush(Client.connection.socket, HabboActivityPointNotificationComposer.compose(plrConnection.pixelAmmount += give, give, 0));
/* 763:776 */           Utils.AlertFromHotel(Client.connection.socket, cappo.game.utils.lang.LangTexts.texts[0] + give + cappo.game.utils.lang.LangTexts.texts[2] + "\n\n- " + playerData.userName);
/* 764:777 */           return true;
/* 765:    */         }
/* 766:    */         Object ch;
/* 767:779 */         if (parsed[0].equals(":massducks"))
/* 768:    */         {
/* 769:780 */           int give = Integer.parseInt(parsed[1]);
/* 770:    */           
/* 771:782 */           Iterator<Channel> itr = FactorialServerHandler.channels.iterator();
/* 772:783 */           while (itr.hasNext())
/* 773:    */           {
/* 774:784 */             ch = (Channel)itr.next();
/* 775:785 */             Connection con = (Connection)((Channel)ch).attr(FactorialServerHandler.CONNECTION).get();
/* 776:787 */             if (con.avatarData != null) {
/* 777:791 */               QueueWriter.writeAndFlush((Channel)ch, HabboActivityPointNotificationComposer.compose(con.pixelAmmount += give, give, 0));
/* 778:    */             }
/* 779:    */           }
/* 780:794 */           Utils.AlertFromHotel(FactorialServerHandler.channels, cappo.game.utils.lang.LangTexts.texts[0] + give + cappo.game.utils.lang.LangTexts.texts[2] + "\n\n- " + playerData.userName);
/* 781:795 */           return true;
/* 782:    */         }
/* 783:797 */         if (parsed[0].equals(":rducks"))
/* 784:    */         {
/* 785:798 */           int give = Integer.parseInt(parsed[1]);
/* 786:799 */           for (ch = this.room.userList.values().iterator(); ((Iterator)ch).hasNext();)
/* 787:    */           {
/* 788:799 */             Avatar user = (Avatar)((Iterator)ch).next();
/* 789:800 */             Connection con = user.cn;
/* 790:801 */             if (con != null)
/* 791:    */             {
/* 792:805 */               QueueWriter.writeAndFlush(con.socket, HabboActivityPointNotificationComposer.compose(con.pixelAmmount += give, give, 0));
/* 793:806 */               Utils.AlertFromHotel(con.socket, cappo.game.utils.lang.LangTexts.texts[0] + give + cappo.game.utils.lang.LangTexts.texts[2] + "\n\n- " + playerData.userName);
/* 794:    */             }
/* 795:    */           }
/* 796:810 */           return true;
/* 797:    */         }
/* 798:813 */         if (parsed[0].equals(":givecredits"))
/* 799:    */         {
/* 800:814 */           PlayerData Client = Clients.getPlayerDataLoaded(parsed[1]);
/* 801:815 */           if ((Client == null) || (Client.connection == null)) {
/* 802:816 */             return false;
/* 803:    */           }
/* 804:819 */           Connection plrConnection = Client.connection;
/* 805:    */           
/* 806:821 */           int give = Integer.parseInt(parsed[2]);
/* 807:822 */           QueueWriter.writeAndFlush(plrConnection.socket, CreditBalanceComposer.compose(plrConnection.credits += give));
/* 808:823 */           Utils.AlertFromHotel(plrConnection.socket, cappo.game.utils.lang.LangTexts.texts[0] + give + cappo.game.utils.lang.LangTexts.texts[1] + "\n\n- " + playerData.userName);
/* 809:824 */           return true;
/* 810:    */         }
/* 811:    */         Object ch;
/* 812:826 */         if (parsed[0].equals(":masscredits"))
/* 813:    */         {
/* 814:827 */           int give = Integer.parseInt(parsed[1]);
/* 815:    */           
/* 816:829 */           Iterator<Channel> itr = FactorialServerHandler.channels.iterator();
/* 817:830 */           while (itr.hasNext())
/* 818:    */           {
/* 819:831 */             ch = (Channel)itr.next();
/* 820:832 */             Connection con = (Connection)((Channel)ch).attr(FactorialServerHandler.CONNECTION).get();
/* 821:834 */             if (con.avatarData != null) {
/* 822:838 */               QueueWriter.writeAndFlush((Channel)ch, CreditBalanceComposer.compose(con.credits += give));
/* 823:    */             }
/* 824:    */           }
/* 825:841 */           Utils.AlertFromHotel(FactorialServerHandler.channels, cappo.game.utils.lang.LangTexts.texts[0] + give + cappo.game.utils.lang.LangTexts.texts[1] + "\n\n- " + playerData.userName);
/* 826:842 */           return true;
/* 827:    */         }
/* 828:844 */         if (parsed[0].equals(":rcredits"))
/* 829:    */         {
/* 830:845 */           int give = Integer.parseInt(parsed[1]);
/* 831:846 */           for (ch = this.room.userList.values().iterator(); ((Iterator)ch).hasNext();)
/* 832:    */           {
/* 833:846 */             Avatar user = (Avatar)((Iterator)ch).next();
/* 834:847 */             Connection con = user.cn;
/* 835:848 */             if (con != null)
/* 836:    */             {
/* 837:852 */               QueueWriter.writeAndFlush(con.socket, CreditBalanceComposer.compose(con.credits += give));
/* 838:853 */               Utils.AlertFromHotel(con.socket, cappo.game.utils.lang.LangTexts.texts[0] + give + cappo.game.utils.lang.LangTexts.texts[1] + "\n\n- " + playerData.userName);
/* 839:    */             }
/* 840:    */           }
/* 841:856 */           return true;
/* 842:    */         }
/* 843:859 */         if (parsed[0].equals(":delbadge"))
/* 844:    */         {
/* 845:860 */           PlayerData Client = Clients.getPlayerDataLoaded(parsed[1]);
/* 846:861 */           if ((Client == null) || (Client.connection == null)) {
/* 847:862 */             return false;
/* 848:    */           }
/* 849:865 */           Client.connection.delBadge(parsed[2]);
/* 850:    */           try
/* 851:    */           {
/* 852:868 */             Database.exec("DELETE FROM user_badges WHERE user_id = " + Client.userId + " AND  badge_id = ?;", new Object[] { parsed[2] });
/* 853:    */           }
/* 854:    */           catch (Exception ex)
/* 855:    */           {
/* 856:870 */             Log.printException("Item-Delete", ex);
/* 857:    */           }
/* 858:873 */           return true;
/* 859:    */         }
/* 860:876 */         if (parsed[0].equals(":delbadges"))
/* 861:    */         {
/* 862:877 */           String badge = parsed[1];
/* 863:    */           
/* 864:879 */           Iterator<Channel> itr = FactorialServerHandler.channels.iterator();
/* 865:880 */           while (itr.hasNext())
/* 866:    */           {
/* 867:881 */             Channel ch = (Channel)itr.next();
/* 868:882 */             Connection con = (Connection)ch.attr(FactorialServerHandler.CONNECTION).get();
/* 869:883 */             con.delBadge(badge);
/* 870:    */           }
/* 871:    */           try
/* 872:    */           {
/* 873:887 */             Database.exec("DELETE FROM user_badges WHERE badge_id = ?;", new Object[] { badge });
/* 874:    */           }
/* 875:    */           catch (Exception ex)
/* 876:    */           {
/* 877:889 */             Log.printException("Item-Delete", ex);
/* 878:    */           }
/* 879:892 */           return true;
/* 880:    */         }
/* 881:    */       }
/* 882:    */     }
/* 883:898 */     return false;
/* 884:    */   }
/* 885:    */   
/* 886:901 */   private static final char[] HttP = { 'h', 't', 't', 'p' };
/* 887:902 */   private static final char[] Separator = { ':', '/', '/' };
/* 888:    */   
/* 889:    */   public static String ParseMessage(String Message, List<String> Urls)
/* 890:    */   {
/* 891:905 */     String NewMsg = "";
/* 892:906 */     int len = Message.length();
/* 893:907 */     int UrlCount = 0;
/* 894:908 */     int pos = 0;
/* 895:909 */     int a = 0;
/* 896:910 */     for (; pos < len; pos++) {
/* 897:911 */       if (HttP[a] == Message.charAt(pos))
/* 898:    */       {
/* 899:912 */         a++;
/* 900:912 */         if (a == 4)
/* 901:    */         {
/* 902:913 */           a = 0;
/* 903:914 */           if (len > pos + 4) {
/* 904:915 */             if (Separator[a] == Message.charAt(++pos))
/* 905:    */             {
/* 906:916 */               if ((Separator[(++a)] == Message.charAt(++pos)) && 
/* 907:917 */                 (Separator[(++a)] == Message.charAt(++pos)))
/* 908:    */               {
/* 909:918 */                 int init = pos - 6;
/* 910:    */                 do
/* 911:    */                 {
/* 912:920 */                   pos++;
/* 913:920 */                 } while ((pos < len) && 
/* 914:921 */                   (' ' != Message.charAt(pos)));
/* 915:928 */                 NewMsg = NewMsg.concat(Message.substring(0, init)).concat("{").concat(Integer.toString(UrlCount)).concat("}");
/* 916:929 */                 UrlCount++;
/* 917:930 */                 Urls.add(Message.substring(init, pos));
/* 918:931 */                 Message = Message.substring(pos);
/* 919:932 */                 len -= pos;
/* 920:933 */                 pos = 0;
/* 921:    */               }
/* 922:    */             }
/* 923:936 */             else if ((Separator[a] == Message.charAt(++pos)) && 
/* 924:937 */               (Separator[(++a)] == Message.charAt(++pos)) && 
/* 925:938 */               (Separator[(++a)] == Message.charAt(++pos)))
/* 926:    */             {
/* 927:939 */               int init = pos - 7;
/* 928:    */               do
/* 929:    */               {
/* 930:941 */                 pos++;
/* 931:941 */               } while ((pos < len) && 
/* 932:942 */                 (' ' != Message.charAt(pos)));
/* 933:949 */               NewMsg = NewMsg.concat(Message.substring(0, init)).concat("{").concat(Integer.toString(UrlCount)).concat("}");
/* 934:950 */               UrlCount++;
/* 935:951 */               Urls.add(Message.substring(init, pos));
/* 936:952 */               Message = Message.substring(pos);
/* 937:953 */               len -= pos;
/* 938:954 */               pos = 0;
/* 939:    */             }
/* 940:    */           }
/* 941:959 */           a = 0;
/* 942:    */         }
/* 943:    */       }
/* 944:    */       else
/* 945:    */       {
/* 946:962 */         a = 0;
/* 947:    */       }
/* 948:    */     }
/* 949:965 */     if (len > 0) {
/* 950:966 */       NewMsg = NewMsg + Message.substring(0, pos);
/* 951:    */     }
/* 952:968 */     return NewMsg;
/* 953:    */   }
/* 954:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.live.Avatar
 * JD-Core Version:    0.7.0.1
 */