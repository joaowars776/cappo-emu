/*   1:    */ package cappo.engine.player;
/*   2:    */ 
/*   3:    */ import cappo.engine.Server;
/*   4:    */ import cappo.engine.database.Database;
/*   5:    */ import cappo.engine.logging.Log;
/*   6:    */ import cappo.engine.network.Crypto;
/*   7:    */ import cappo.engine.network.MessageReader;
/*   8:    */ import cappo.engine.network.QueueWriter;
/*   9:    */ import cappo.engine.threadpools.RoomTask;
/*  10:    */ import cappo.game.bots.RentalBot;
/*  11:    */ import cappo.game.collections.AvatarEffect;
/*  12:    */ import cappo.game.collections.Badge;
/*  13:    */ import cappo.game.collections.BaseItem;
/*  14:    */ import cappo.game.collections.FavRoom;
/*  15:    */ import cappo.game.collections.UnseenItems;
/*  16:    */ import cappo.game.collections.Utils;
/*  17:    */ import cappo.game.collections.Wardrobe;
/*  18:    */ import cappo.game.moderation.StaffManager;
/*  19:    */ import cappo.game.moderation.UserMuted;
/*  20:    */ import cappo.game.pets.Pet;
/*  21:    */ import cappo.game.pets.PetBase;
/*  22:    */ import cappo.game.player.AvatarLook;
/*  23:    */ import cappo.game.player.PlayerData;
/*  24:    */ import cappo.game.player.PlayerModerator;
/*  25:    */ import cappo.game.player.SnowWarPlayerData;
/*  26:    */ import cappo.game.player.data.AvatarData;
/*  27:    */ import cappo.game.player.inventory.PlayerInventory;
/*  28:    */ import cappo.game.player.messenger.PlayerMessenger;
/*  29:    */ import cappo.game.roomeffects.UserEffect;
/*  30:    */ import cappo.game.roomengine.RoomData;
/*  31:    */ import cappo.game.roomengine.RoomManager;
/*  32:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  33:    */ import cappo.game.roomengine.entity.item.floor.SongItem;
/*  34:    */ import cappo.game.roomengine.entity.item.floor.wired.WiredItemBase;
/*  35:    */ import cappo.game.roomengine.entity.item.wall.WallItem;
/*  36:    */ import cappo.game.roomengine.entity.live.Avatar;
/*  37:    */ import cappo.game.roomengine.itemInteractor.Interactor;
/*  38:    */ import cappo.game.roomengine.itemInteractor.InteractorTeleport.TeleportAttach;
/*  39:    */ import cappo.protocol.messages.composers.inventory.avatareffect.EffectAddedComposer;
/*  40:    */ import cappo.protocol.messages.composers.inventory.avatareffect.EffectEnabledComposer;
/*  41:    */ import cappo.protocol.messages.composers.inventory.avatareffect.EffectStopedComposer;
/*  42:    */ import cappo.protocol.messages.composers.inventory.badges.BadgesComposer;
/*  43:    */ import cappo.protocol.messages.composers.inventory.furni.FurniListRemoveComposer;
/*  44:    */ import cappo.protocol.messages.composers.notifications.HabboActivityPointNotificationComposer;
/*  45:    */ import cappo.protocol.messages.composers.notifications.UnseenItemsComposer;
/*  46:    */ import io.netty.channel.Channel;
/*  47:    */ import java.math.BigInteger;
/*  48:    */ import java.util.ArrayList;
/*  49:    */ import java.util.List;
/*  50:    */ import java.util.Map;
/*  51:    */ import java.util.Random;
/*  52:    */ import java.util.concurrent.ConcurrentHashMap;
/*  53:    */ 
/*  54:    */ public class Connection
/*  55:    */ {
/*  56:    */   private BigInteger prime;
/*  57:    */   private BigInteger privateKey;
/*  58:    */   private BigInteger publicKey;
/*  59:    */   public Crypto RC4Decode;
/*  60:    */   public long nextPixelsUpdate;
/*  61:    */   public int credits;
/*  62:    */   public int diamondAmmount;
/*  63:    */   public int pixelAmmount;
/*  64:    */   public int vipPoins;
/*  65:    */   public int homeRoom;
/*  66:    */   public int respects;
/*  67:    */   public int dailyPetRespectPoints;
/*  68:    */   public int dailyRespectPoints;
/*  69:    */   public int MaxRooms;
/*  70:    */   public Avatar avatar;
/*  71:    */   public AvatarData avatarData;
/*  72:    */   public InteractorTeleport.TeleportAttach teleport;
/*  73:    */   public PlayerInventory inventory;
/*  74:    */   public SnowWarPlayerData snowWarPlayerData;
/*  75:    */   public UserMuted userMuted;
/*  76:    */   public List<AvatarEffect> avatarEffects;
/*  77:    */   public Map<String, Badge> badges;
/*  78:    */   public Map<Integer, Badge> badgesSelected;
/*  79:    */   public Map<Integer, FavRoom> favoriteRooms;
/*  80:    */   public Map<Short, Wardrobe> Wardrobes;
/*  81:    */   public List<Integer> ignoreUsers;
/*  82:    */   public Map<Integer, RoomData> ownRooms;
/*  83:    */   public Channel socket;
/*  84:    */   public MessageReader currentPacket;
/*  85:    */   public PlayerData playerData;
/*  86:    */   private int flags;
/*  87:    */   
/*  88:    */   public Connection()
/*  89:    */   {
/*  90:103 */     this.avatarData = new AvatarData();
/*  91:104 */     this.inventory = new PlayerInventory(this);
/*  92:    */     
/*  93:106 */     this.avatarEffects = new ArrayList();
/*  94:107 */     this.badges = new ConcurrentHashMap();
/*  95:108 */     this.badgesSelected = new ConcurrentHashMap();
/*  96:109 */     this.favoriteRooms = new ConcurrentHashMap();
/*  97:110 */     this.Wardrobes = new ConcurrentHashMap();
/*  98:111 */     this.ignoreUsers = new ArrayList();
/*  99:112 */     this.ownRooms = new ConcurrentHashMap();
/* 100:    */   }
/* 101:    */   
/* 102:    */   public PlayerData getPlayerData()
/* 103:    */   {
/* 104:125 */     return this.playerData;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void setPlayerData(PlayerData player)
/* 108:    */   {
/* 109:129 */     this.playerData = player;
/* 110:130 */     this.playerData.connection = this;
/* 111:131 */     this.snowWarPlayerData = new SnowWarPlayerData(player);
/* 112:    */   }
/* 113:    */   
/* 114:    */   public boolean haveFlag(int bit)
/* 115:    */   {
/* 116:135 */     return (this.flags & bit) == bit;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public final void setFlag(int flag, boolean add)
/* 120:    */   {
/* 121:139 */     if (add) {
/* 122:140 */       this.flags |= flag;
/* 123:    */     } else {
/* 124:142 */       this.flags &= (flag ^ 0xFFFFFFFF);
/* 125:    */     }
/* 126:    */   }
/* 127:    */   
/* 128:    */   public final void xorFlag(int flag)
/* 129:    */   {
/* 130:147 */     this.flags ^= flag;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public void inventoryAddFloorItem(FloorItem item)
/* 134:    */   {
/* 135:151 */     this.inventory.addObject(item.itemId, item);
/* 136:152 */     if (item.baseItem.itemCategory == 8) {
/* 137:153 */       this.inventory.addSong(item.itemId, (SongItem)item);
/* 138:    */     }
/* 139:    */   }
/* 140:    */   
/* 141:    */   public void inventoryAddWallItem(WallItem item)
/* 142:    */   {
/* 143:158 */     this.inventory.addItem(item.itemId, item);
/* 144:    */   }
/* 145:    */   
/* 146:    */   public void addEffect(int EffectId, int Duration)
/* 147:    */   {
/* 148:162 */     AvatarEffect effect = new AvatarEffect(EffectId, Duration, false, Utils.getTimestamp());
/* 149:163 */     this.avatarEffects.add(effect);
/* 150:164 */     QueueWriter.writeAndFlush(this.socket, EffectAddedComposer.compose(effect));
/* 151:    */   }
/* 152:    */   
/* 153:    */   public void applyEffect(short EffectId)
/* 154:    */   {
/* 155:168 */     if (this.avatar == null) {
/* 156:169 */       return;
/* 157:    */     }
/* 158:172 */     if (!HasEffect(EffectId, true)) {
/* 159:173 */       return;
/* 160:    */     }
/* 161:176 */     this.avatar.IsBuyEffect = (EffectId > 0);
/* 162:178 */     if (this.avatar.IsBuyEffect) {
/* 163:179 */       this.avatar.userEffect = new UserEffect(this.avatar, EffectId);
/* 164:181 */     } else if (this.avatar.userEffect != null) {
/* 165:182 */       this.avatar.userEffect.stopEffect();
/* 166:    */     }
/* 167:    */   }
/* 168:    */   
/* 169:    */   public void saveUserData()
/* 170:    */   {
/* 171:    */     try
/* 172:    */     {
/* 173:189 */       if (this.playerData != null) {
/* 174:190 */         Database.exec(
/* 175:    */         
/* 176:    */ 
/* 177:    */ 
/* 178:    */ 
/* 179:    */ 
/* 180:    */ 
/* 181:    */ 
/* 182:    */ 
/* 183:    */ 
/* 184:    */ 
/* 185:    */ 
/* 186:    */ 
/* 187:203 */           "UPDATE `users` LEFT JOIN `user_info` ON (user_info.user_id=users.id) SET users.username=?,users.motto=?,users.look=?,users.respects=" + this.respects + "," + "users.home_room=" + this.homeRoom + "," + "users.credits=" + this.credits + "," + "users.activity_points=" + this.pixelAmmount + "," + "users.crystals=" + this.diamondAmmount + "," + "users.vip_points=" + this.vipPoins + "," + "users.gender='" + (this.playerData.sex == 1 ? "M" : "F") + "'," + "users.block_trade='" + (haveFlag(8) ? 1 : 0) + "'," + "users.newbie_status=0" + " WHERE users.id=" + this.playerData.userId + ";", new Object[] { this.playerData.userName, this.playerData.motto, this.playerData.avatarLook.toString() });
/* 188:    */       }
/* 189:    */     }
/* 190:    */     catch (Exception ex)
/* 191:    */     {
/* 192:207 */       Log.printException("saveItems", ex);
/* 193:    */     }
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void saveFavRooms()
/* 197:    */   {
/* 198:    */     try
/* 199:    */     {
/* 200:213 */       if (this.favoriteRooms != null) {
/* 201:214 */         for (FavRoom fav : this.favoriteRooms.values()) {
/* 202:215 */           if (fav.needInsert)
/* 203:    */           {
/* 204:218 */             fav.needInsert = false;
/* 205:    */             
/* 206:220 */             Database.exec("INSERT IGNORE INTO user_favorites (room_id,user_id)VALUES(" + fav.room.roomId + "," + this.playerData.userId + ");", new Object[0]);
/* 207:    */           }
/* 208:    */         }
/* 209:    */       }
/* 210:    */     }
/* 211:    */     catch (Exception ex)
/* 212:    */     {
/* 213:225 */       Log.printException("saveFavRooms", ex);
/* 214:    */     }
/* 215:    */   }
/* 216:    */   
/* 217:    */   public void saveWardRobes()
/* 218:    */   {
/* 219:    */     try
/* 220:    */     {
/* 221:232 */       if (this.Wardrobes != null) {
/* 222:233 */         for (Wardrobe wrb : this.Wardrobes.values()) {
/* 223:234 */           if (wrb.needInsert)
/* 224:    */           {
/* 225:237 */             wrb.needInsert = false;
/* 226:    */             
/* 227:239 */             Database.exec("INSERT INTO user_wardrobe (slot_id,look,gender,user_id)VALUES(" + wrb.slotId + ",'" + wrb.look + "','" + (wrb.gender == 1 ? "M" : "F") + "'," + this.playerData.userId + ") on DUPLICATE KEY UPDATE `look`='" + wrb.look + "',`gender`='" + (wrb.gender == 1 ? "M" : "F") + "';", new Object[0]);
/* 228:    */           }
/* 229:    */         }
/* 230:    */       }
/* 231:    */     }
/* 232:    */     catch (Exception ex)
/* 233:    */     {
/* 234:244 */       Log.printException("saveWardRobes", ex);
/* 235:    */     }
/* 236:    */   }
/* 237:    */   
/* 238:    */   public void saveBadges()
/* 239:    */   {
/* 240:    */     try
/* 241:    */     {
/* 242:250 */       if (this.badges != null) {
/* 243:251 */         for (Badge badge : this.badges.values()) {
/* 244:252 */           if (badge.needInsert)
/* 245:    */           {
/* 246:255 */             badge.needInsert = false;
/* 247:    */             
/* 248:257 */             Database.exec("INSERT INTO user_badges (id,badge_id,badge_slot,user_id)VALUES(" + badge.badgeId + ",?," + badge.badgeSlot + "," + this.playerData.userId + ") on DUPLICATE KEY UPDATE `badge_slot`=" + badge.badgeSlot + ";", new Object[] { badge.badgeCode });
/* 249:    */           }
/* 250:    */         }
/* 251:    */       }
/* 252:    */     }
/* 253:    */     catch (Exception ex)
/* 254:    */     {
/* 255:262 */       Log.printException("saveBadges", ex);
/* 256:    */     }
/* 257:    */   }
/* 258:    */   
/* 259:    */   public void savePets()
/* 260:    */   {
/* 261:    */     try
/* 262:    */     {
/* 263:268 */       if (this.inventory.petsReady()) {
/* 264:269 */         for (Pet pet : this.inventory.getPets()) {
/* 265:270 */           if (pet.needInsert)
/* 266:    */           {
/* 267:273 */             pet.needInsert = false;
/* 268:    */             
/* 269:275 */             Database.exec("INSERT INTO user_pets (id,type,name,race,color,createstamp,nutrition,expirience,energy,respect,user_id,room_id)VALUES(" + pet.id + "," + pet.petType + ",?," + pet.base.raceId + ",?," + pet.TimeCreated + "," + pet.Nutrition + "," + pet.Experience + "," + pet.Energy + "," + pet.Respects + "," + this.playerData.userId + ",0);", new Object[] { pet.name, pet.Color });
/* 270:    */           }
/* 271:    */         }
/* 272:    */       }
/* 273:    */     }
/* 274:    */     catch (Exception ex)
/* 275:    */     {
/* 276:280 */       Log.printException("savePets", ex);
/* 277:    */     }
/* 278:    */   }
/* 279:    */   
/* 280:    */   public void saveBots()
/* 281:    */   {
/* 282:    */     try
/* 283:    */     {
/* 284:286 */       if (this.inventory.botsReady()) {
/* 285:287 */         for (RentalBot bot : this.inventory.getBots()) {
/* 286:288 */           if (bot.needInsert)
/* 287:    */           {
/* 288:291 */             bot.needInsert = false;
/* 289:    */             
/* 290:293 */             Database.exec("INSERT INTO user_bots (id,type,name,look,gender,motto,user_id,room_id)VALUES(" + bot.id + "," + bot.botType + ",?,?,?,?," + this.playerData.userId + ",0);", new Object[] { bot.name, bot.botLook.toString(), bot.gender, bot.motto });
/* 291:    */           }
/* 292:    */         }
/* 293:    */       }
/* 294:    */     }
/* 295:    */     catch (Exception ex)
/* 296:    */     {
/* 297:298 */       Log.printException("savePets", ex);
/* 298:    */     }
/* 299:    */   }
/* 300:    */   
/* 301:    */   public void saveItems()
/* 302:    */   {
/* 303:    */     try
/* 304:    */     {
/* 305:304 */       if (this.inventory.itemsReady()) {
/* 306:305 */         for (WallItem wallItem : this.inventory.getItems()) {
/* 307:    */           try
/* 308:    */           {
/* 309:307 */             wallItem.mysqlSave();
/* 310:    */           }
/* 311:    */           catch (Exception ex)
/* 312:    */           {
/* 313:309 */             Log.printException("saveItems", ex);
/* 314:    */           }
/* 315:    */         }
/* 316:    */       }
/* 317:    */     }
/* 318:    */     catch (Exception ex)
/* 319:    */     {
/* 320:315 */       Log.printException("savePets", ex);
/* 321:    */     }
/* 322:    */   }
/* 323:    */   
/* 324:    */   public void saveObjects()
/* 325:    */   {
/* 326:    */     try
/* 327:    */     {
/* 328:321 */       if (this.inventory.objectsReady()) {
/* 329:322 */         for (FloorItem floorItem : this.inventory.getObjects()) {
/* 330:    */           try
/* 331:    */           {
/* 332:324 */             if (floorItem.baseItem.interactor == Interactor.iterWired) {
/* 333:326 */               if ((floorItem instanceof WiredItemBase))
/* 334:    */               {
/* 335:327 */                 WiredItemBase wired = (WiredItemBase)floorItem;
/* 336:    */                 try
/* 337:    */                 {
/* 338:330 */                   wired.deleteData();
/* 339:    */                 }
/* 340:    */                 catch (Exception ex)
/* 341:    */                 {
/* 342:332 */                   Log.printException("channelDisconnected", ex);
/* 343:    */                 }
/* 344:    */               }
/* 345:    */             }
/* 346:337 */             floorItem.mysqlSave();
/* 347:    */           }
/* 348:    */           catch (Exception ex)
/* 349:    */           {
/* 350:339 */             Log.printException("saveObjects", ex);
/* 351:    */           }
/* 352:    */         }
/* 353:    */       }
/* 354:    */     }
/* 355:    */     catch (Exception ex)
/* 356:    */     {
/* 357:345 */       Log.printException("savePets", ex);
/* 358:    */     }
/* 359:    */   }
/* 360:    */   
/* 361:    */   public void channelDisconnected()
/* 362:    */   {
/* 363:350 */     if (haveFlag(128)) {
/* 364:351 */       return;
/* 365:    */     }
/* 366:354 */     setFlag(128, true);
/* 367:356 */     if (this.playerData == null) {
/* 368:357 */       return;
/* 369:    */     }
/* 370:360 */     this.playerData.connection = null;
/* 371:362 */     if (this.playerData.userId != 0)
/* 372:    */     {
/* 373:363 */       if ((this.playerData instanceof PlayerModerator)) {
/* 374:364 */         StaffManager.removeStaff(this.playerData.userId);
/* 375:    */       }
/* 376:367 */       Clients.setOnline(false);
/* 377:    */       try
/* 378:    */       {
/* 379:370 */         if (this.avatar != null)
/* 380:    */         {
/* 381:371 */           RoomTask room = this.avatar.room;
/* 382:372 */           if (room != null) {
/* 383:373 */             room.removeUserFromRoom(this, this.playerData, false, false);
/* 384:    */           }
/* 385:    */         }
/* 386:    */       }
/* 387:    */       catch (Exception ex)
/* 388:    */       {
/* 389:378 */         Log.printException("Disconnect", ex);
/* 390:    */       }
/* 391:    */       try
/* 392:    */       {
/* 393:382 */         if (this.snowWarPlayerData != null) {
/* 394:383 */           this.snowWarPlayerData.userLeft();
/* 395:    */         }
/* 396:    */       }
/* 397:    */       catch (Exception ex)
/* 398:    */       {
/* 399:387 */         Log.printException("Disconnect", ex);
/* 400:    */       }
/* 401:390 */       saveBots();
/* 402:391 */       saveItems();
/* 403:392 */       saveObjects();
/* 404:393 */       savePets();
/* 405:394 */       saveBadges();
/* 406:395 */       saveWardRobes();
/* 407:396 */       saveFavRooms();
/* 408:397 */       saveUserData();
/* 409:    */       try
/* 410:    */       {
/* 411:400 */         this.playerData.messenger.save();
/* 412:401 */         this.inventory.clean();
/* 413:    */       }
/* 414:    */       catch (Exception ex)
/* 415:    */       {
/* 416:404 */         Log.printException("Disconnect", ex);
/* 417:    */       }
/* 418:    */     }
/* 419:    */   }
/* 420:    */   
/* 421:    */   public void EffectsCheckExpired()
/* 422:    */   {
/* 423:410 */     for (AvatarEffect Effect : this.avatarEffects) {
/* 424:411 */       if (Effect.Activated)
/* 425:    */       {
/* 426:415 */         long diff = Utils.getTimestamp() - Effect.ActivateTimestamp;
/* 427:416 */         if (diff > Effect.TotalDuration) {
/* 428:417 */           StopEffect(Effect);
/* 429:    */         }
/* 430:    */       }
/* 431:    */     }
/* 432:    */   }
/* 433:    */   
/* 434:    */   public void EnableEffect(int EffectId)
/* 435:    */   {
/* 436:423 */     AvatarEffect Effect = GetEffect(EffectId, false);
/* 437:425 */     if (Effect == null) {
/* 438:426 */       return;
/* 439:    */     }
/* 440:429 */     if (Effect.Activated) {
/* 441:430 */       return;
/* 442:    */     }
/* 443:433 */     Effect.Activated = true;
/* 444:434 */     Effect.ActivateTimestamp = Utils.getTimestamp();
/* 445:    */     
/* 446:436 */     QueueWriter.writeAndFlush(this.socket, EffectEnabledComposer.compose(Effect.effectType, Effect.TotalDuration));
/* 447:    */   }
/* 448:    */   
/* 449:    */   public String generateRandomHexString(int len)
/* 450:    */   {
/* 451:441 */     String result = "";
/* 452:442 */     Random rnd = new Random();
/* 453:443 */     for (int i = 0; i < len; i++)
/* 454:    */     {
/* 455:444 */       int rand = 1 + (int)(rnd.nextDouble() * 254.0D);
/* 456:445 */       result = result + Integer.toString(rand, 16);
/* 457:    */     }
/* 458:447 */     return result;
/* 459:    */   }
/* 460:    */   
/* 461:    */   public String generateSharedKey(BigInteger ClientKey)
/* 462:    */   {
/* 463:451 */     return ClientKey.modPow(this.privateKey, this.prime).toString(16).toUpperCase();
/* 464:    */   }
/* 465:    */   
/* 466:    */   private AvatarEffect GetEffect(int EffectId, boolean IfEnabledOnly)
/* 467:    */   {
/* 468:455 */     if (IfEnabledOnly) {
/* 469:456 */       for (AvatarEffect Effect : this.avatarEffects) {
/* 470:457 */         if ((Effect.effectType == EffectId) && (Effect.Activated) && (Utils.getTimestamp() - Effect.ActivateTimestamp < Effect.TotalDuration)) {
/* 471:458 */           return Effect;
/* 472:    */         }
/* 473:    */       }
/* 474:    */     } else {
/* 475:462 */       for (AvatarEffect Effect : this.avatarEffects) {
/* 476:463 */         if (Effect.effectType == EffectId) {
/* 477:464 */           return Effect;
/* 478:    */         }
/* 479:    */       }
/* 480:    */     }
/* 481:468 */     return null;
/* 482:    */   }
/* 483:    */   
/* 484:    */   public void inventoryRemoveItem(int itemId, boolean isWall)
/* 485:    */   {
/* 486:472 */     QueueWriter.writeAndFlush(this.socket, FurniListRemoveComposer.compose(itemId));
/* 487:474 */     if (isWall)
/* 488:    */     {
/* 489:475 */       this.inventory.removeItem(itemId);
/* 490:    */     }
/* 491:    */     else
/* 492:    */     {
/* 493:477 */       FloorItem Item = this.inventory.removeObject(itemId);
/* 494:478 */       if (Item.baseItem.itemCategory == 8) {
/* 495:479 */         this.inventory.removeSong(itemId);
/* 496:    */       }
/* 497:    */     }
/* 498:    */   }
/* 499:    */   
/* 500:    */   public String getPublicKey()
/* 501:    */   {
/* 502:485 */     String p = this.publicKey.toString(10);
/* 503:    */     
/* 504:    */ 
/* 505:488 */     this.prime = null;
/* 506:489 */     this.privateKey = null;
/* 507:490 */     this.publicKey = null;
/* 508:    */     
/* 509:492 */     return p;
/* 510:    */   }
/* 511:    */   
/* 512:    */   public void giveBadge(String code)
/* 513:    */   {
/* 514:496 */     if (this.badges.containsKey(code)) {
/* 515:497 */       return;
/* 516:    */     }
/* 517:500 */     Badge badge = new Badge(Server.generateBadgeId(), code, 0);
/* 518:501 */     badge.needInsert = true;
/* 519:    */     
/* 520:503 */     this.badges.put(code, badge);
/* 521:504 */     QueueWriter.writeAndFlush(this.socket, BadgesComposer.compose(this.badges.values(), this.badgesSelected.values()));
/* 522:    */     
/* 523:506 */     this.avatarData.UnseenItems.AddItem(4, badge.badgeId);
/* 524:507 */     QueueWriter.writeAndFlush(this.socket, UnseenItemsComposer.compose(this.avatarData.UnseenItems));
/* 525:    */   }
/* 526:    */   
/* 527:    */   public void delBadge(String code)
/* 528:    */   {
/* 529:511 */     Badge badge = (Badge)this.badges.remove(code);
/* 530:512 */     if (badge == null) {
/* 531:513 */       return;
/* 532:    */     }
/* 533:515 */     QueueWriter.writeAndFlush(this.socket, BadgesComposer.compose(this.badges.values(), this.badgesSelected.values()));
/* 534:    */   }
/* 535:    */   
/* 536:    */   public void GivePixels(int i)
/* 537:    */   {
/* 538:519 */     QueueWriter.writeAndFlush(this.socket, HabboActivityPointNotificationComposer.compose(this.pixelAmmount += i, i, 0));
/* 539:520 */     this.nextPixelsUpdate = (Utils.getTimestamp() + 600L);
/* 540:    */   }
/* 541:    */   
/* 542:    */   private boolean HasEffect(int EffectId, boolean IfEnabledOnly)
/* 543:    */   {
/* 544:524 */     if (EffectId == -1) {
/* 545:525 */       return true;
/* 546:    */     }
/* 547:528 */     if (IfEnabledOnly) {
/* 548:529 */       for (AvatarEffect Effect : this.avatarEffects) {
/* 549:530 */         if ((Effect.effectType == EffectId) && (Effect.Activated) && (Utils.getTimestamp() - Effect.ActivateTimestamp < Effect.TotalDuration)) {
/* 550:531 */           return true;
/* 551:    */         }
/* 552:    */       }
/* 553:    */     } else {
/* 554:535 */       for (AvatarEffect Effect : this.avatarEffects) {
/* 555:536 */         if ((Effect.effectType == EffectId) && (Utils.getTimestamp() - Effect.ActivateTimestamp < Effect.TotalDuration)) {
/* 556:537 */           return true;
/* 557:    */         }
/* 558:    */       }
/* 559:    */     }
/* 560:541 */     return false;
/* 561:    */   }
/* 562:    */   
/* 563:    */   public byte[] HextoBytes(String hexString)
/* 564:    */   {
/* 565:545 */     if (hexString.length() % 2 != 0) {
/* 566:546 */       hexString = "0" + hexString;
/* 567:    */     }
/* 568:548 */     byte[] bytes = new byte[hexString.length() / 2];
/* 569:549 */     int j = 0;
/* 570:550 */     for (int i = 0; i < bytes.length; i++)
/* 571:    */     {
/* 572:551 */       bytes[i] = ((byte)Integer.parseInt(hexString.substring(j, j + 2), 16));
/* 573:552 */       j += 2;
/* 574:    */     }
/* 575:554 */     return bytes;
/* 576:    */   }
/* 577:    */   
/* 578:    */   public void InitDH(BigInteger p, BigInteger generator, String bigrand)
/* 579:    */   {
/* 580:558 */     this.prime = p;
/* 581:559 */     this.privateKey = new BigInteger(bigrand, 16);
/* 582:560 */     this.publicKey = generator.modPow(this.privateKey, this.prime);
/* 583:    */   }
/* 584:    */   
/* 585:    */   public void loadRoom(int RoomId, String Password)
/* 586:    */   {
/* 587:565 */     if (this.avatar != null)
/* 588:    */     {
/* 589:567 */       RoomTask oldRoom = this.avatar.room;
/* 590:568 */       if (oldRoom != null) {
/* 591:569 */         oldRoom.removeUserFromRoom(this, false, false);
/* 592:    */       }
/* 593:    */     }
/* 594:573 */     RoomData roomData = RoomManager.getRoom(RoomId);
/* 595:574 */     if (roomData == null)
/* 596:    */     {
/* 597:    */       try
/* 598:    */       {
/* 599:576 */         roomData = RoomManager.loadRoom(RoomId);
/* 600:    */       }
/* 601:    */       catch (Exception ex)
/* 602:    */       {
/* 603:578 */         Log.printException("Connection-3", ex);
/* 604:    */       }
/* 605:581 */       if (roomData == null) {
/* 606:582 */         return;
/* 607:    */       }
/* 608:    */     }
/* 609:586 */     RoomTask room = roomData.room;
/* 610:587 */     if (room == null)
/* 611:    */     {
/* 612:588 */       room = roomData.room = new RoomTask(roomData);
/* 613:589 */       if (room.model == null) {
/* 614:590 */         return;
/* 615:    */       }
/* 616:593 */       room.init();
/* 617:594 */       RoomTask.addTask(room, 0, 500);
/* 618:    */     }
/* 619:596 */     room.loadRoom(this, Password);
/* 620:    */   }
/* 621:    */   
/* 622:    */   public boolean PixelsNeedsUpdate()
/* 623:    */   {
/* 624:600 */     if (Utils.getTimestamp() > this.nextPixelsUpdate) {
/* 625:601 */       return true;
/* 626:    */     }
/* 627:604 */     return false;
/* 628:    */   }
/* 629:    */   
/* 630:    */   private void StopEffect(AvatarEffect Effect)
/* 631:    */   {
/* 632:608 */     this.avatarEffects.remove(Effect);
/* 633:    */     
/* 634:610 */     QueueWriter.writeAndFlush(this.socket, EffectStopedComposer.compose(Effect.effectType));
/* 635:612 */     if (this.avatar == null) {
/* 636:613 */       return;
/* 637:    */     }
/* 638:616 */     if (this.avatar.userEffect != null) {
/* 639:617 */       this.avatar.userEffect.stopEffect();
/* 640:    */     }
/* 641:    */   }
/* 642:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.player.Connection
 * JD-Core Version:    0.7.0.1
 */