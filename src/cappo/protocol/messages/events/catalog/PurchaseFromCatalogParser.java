/*   1:    */ package cappo.protocol.messages.events.catalog;
/*   2:    */ 
/*   3:    */ import cappo.engine.Server;
/*   4:    */ import cappo.engine.database.Database;
/*   5:    */ import cappo.engine.logging.Log;
/*   6:    */ import cappo.engine.network.MessageReader;
/*   7:    */ import cappo.engine.network.QueueWriter;
/*   8:    */ import cappo.engine.player.Connection;
/*   9:    */ import cappo.game.bots.RentalBot;
/*  10:    */ import cappo.game.catalog.Catalog;
/*  11:    */ import cappo.game.catalog.Catalog.CatalogPage;
/*  12:    */ import cappo.game.catalog.Catalog.CatalogProduct;
/*  13:    */ import cappo.game.catalog.Catalog.CatalogSubItem;
/*  14:    */ import cappo.game.collections.BaseItem;
/*  15:    */ import cappo.game.collections.BaseItem.FurniLogic;
/*  16:    */ import cappo.game.collections.Teleports;
/*  17:    */ import cappo.game.collections.UnseenItems;
/*  18:    */ import cappo.game.collections.Utils;
/*  19:    */ import cappo.game.pets.Pet;
/*  20:    */ import cappo.game.pets.PetBase;
/*  21:    */ import cappo.game.player.PlayerData;
/*  22:    */ import cappo.game.player.data.AvatarData;
/*  23:    */ import cappo.game.player.inventory.PlayerInventory;
/*  24:    */ import cappo.game.roomengine.entity.item.extradata.StuffDataReader;
/*  25:    */ import cappo.game.roomengine.entity.item.extradata.StuffDataWriter;
/*  26:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  27:    */ import cappo.game.roomengine.entity.item.wall.WallItem;
/*  28:    */ import cappo.game.roomengine.itemInteractor.Interactor;
/*  29:    */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/*  30:    */ import cappo.protocol.messages.IncomingMessageEvent;
/*  31:    */ import cappo.protocol.messages.composers.catalog.ErrorBuyComposer;
/*  32:    */ import cappo.protocol.messages.composers.catalog.ErrorPurchaseFromCatalogComposer;
/*  33:    */ import cappo.protocol.messages.composers.catalog.UniqueLimitedItemSoldOutComposer;
/*  34:    */ import cappo.protocol.messages.composers.inventory.bots.AddBotToInventoryComposer;
/*  35:    */ import cappo.protocol.messages.composers.inventory.furni.FurniListUpdateComposer;
/*  36:    */ import cappo.protocol.messages.composers.inventory.pets.AddPetToInventoryComposer;
/*  37:    */ import cappo.protocol.messages.composers.inventory.purse.CreditBalanceComposer;
/*  38:    */ import cappo.protocol.messages.composers.notifications.BuyNotificationComposer;
/*  39:    */ import cappo.protocol.messages.composers.notifications.HabboActivityPointNotificationComposer;
/*  40:    */ import cappo.protocol.messages.composers.notifications.PetReceivedMessageComposer;
/*  41:    */ import cappo.protocol.messages.composers.notifications.UnseenItemsComposer;
/*  42:    */ import java.text.SimpleDateFormat;
/*  43:    */ import java.util.Map;
/*  44:    */ 
/*  45:    */ public class PurchaseFromCatalogParser
/*  46:    */   extends IncomingMessageEvent
/*  47:    */ {
/*  48:    */   public void messageReceived(Connection Main)
/*  49:    */   {
/*  50: 61 */     int pageId = Main.currentPacket.readInt();
/*  51: 62 */     int itemId = Main.currentPacket.readInt();
/*  52: 63 */     String extraParam = Main.currentPacket.readString();
/*  53: 64 */     int quantity = Main.currentPacket.readInt();
/*  54: 66 */     if ((quantity > 50) || (quantity < 1)) {
/*  55: 67 */       return;
/*  56:    */     }
/*  57: 70 */     Catalog.CatalogPage page = (Catalog.CatalogPage)Catalog.pages.get(Integer.valueOf(pageId));
/*  58: 71 */     if ((page == null) || (!page.isEnabled))
/*  59:    */     {
/*  60: 72 */       QueueWriter.write(Main.socket, ErrorPurchaseFromCatalogComposer.compose(0));
/*  61: 73 */       return;
/*  62:    */     }
/*  63: 76 */     if (page.minRank > Main.playerData.staffLevel)
/*  64:    */     {
/*  65: 77 */       QueueWriter.write(Main.socket, ErrorPurchaseFromCatalogComposer.compose(0));
/*  66: 78 */       return;
/*  67:    */     }
/*  68: 81 */     Catalog.CatalogProduct product = (Catalog.CatalogProduct)Catalog.Items.get(Integer.valueOf(itemId));
/*  69: 82 */     if ((product == null) || (product.pageId != page.pageId)) {
/*  70: 83 */       return;
/*  71:    */     }
/*  72: 86 */     if ((quantity > 1) && ((!product.allowBundleDiscounts) || (product.uniqueLimitedItemsLaunched.intValue() > 0))) {
/*  73:    */       return;
/*  74:    */     }
/*  75:    */     int priceMultipler;
/*  76:    */     int priceMultipler;
/*  77: 91 */     if (quantity > 5) {
/*  78: 92 */       priceMultipler = quantity - (quantity / 5 * 2 - 1);
/*  79:    */     } else {
/*  80: 94 */       priceMultipler = quantity;
/*  81:    */     }
/*  82: 97 */     if (product.creditCost > 0)
/*  83:    */     {
/*  84: 98 */       int finalCost = product.creditCost * priceMultipler;
/*  85: 99 */       if (Main.credits < finalCost)
/*  86:    */       {
/*  87:100 */         QueueWriter.write(Main.socket, ErrorBuyComposer.compose(Boolean.valueOf(true), Boolean.valueOf(false), 0));
/*  88:101 */         return;
/*  89:    */       }
/*  90:104 */       Main.credits -= finalCost;
/*  91:105 */       QueueWriter.write(Main.socket, CreditBalanceComposer.compose(Main.credits));
/*  92:    */     }
/*  93:108 */     if (product.activityPointCost > 0)
/*  94:    */     {
/*  95:109 */       int finalCost = product.activityPointCost * priceMultipler;
/*  96:110 */       if (product.activityPointsType == 105)
/*  97:    */       {
/*  98:111 */         if (Main.diamondAmmount < finalCost)
/*  99:    */         {
/* 100:112 */           QueueWriter.write(Main.socket, ErrorBuyComposer.compose(Boolean.valueOf(false), Boolean.valueOf(true), 105));
/* 101:113 */           return;
/* 102:    */         }
/* 103:116 */         Main.diamondAmmount -= finalCost;
/* 104:117 */         QueueWriter.write(Main.socket, HabboActivityPointNotificationComposer.compose(Main.diamondAmmount, 0, 105));
/* 105:    */       }
/* 106:    */       else
/* 107:    */       {
/* 108:119 */         if (Main.pixelAmmount < finalCost)
/* 109:    */         {
/* 110:120 */           QueueWriter.write(Main.socket, ErrorBuyComposer.compose(Boolean.valueOf(false), Boolean.valueOf(true), 0));
/* 111:121 */           return;
/* 112:    */         }
/* 113:124 */         Main.pixelAmmount -= finalCost;
/* 114:125 */         QueueWriter.write(Main.socket, HabboActivityPointNotificationComposer.compose(Main.pixelAmmount, 0, 0));
/* 115:    */       }
/* 116:    */     }
/* 117:129 */     if ((product.itemName.startsWith("a0 pet")) && (product.itemName.length() <= 8))
/* 118:    */     {
/* 119:    */       try
/* 120:    */       {
/* 121:131 */         int type = Integer.parseInt(product.itemName.substring(6));
/* 122:132 */         if ((type < 0) || (type > 27))
/* 123:    */         {
/* 124:133 */           Log.printLog("Invalid Pet:" + type);
/* 125:134 */           return;
/* 126:    */         }
/* 127:137 */         String[] strArray = extraParam.split("\n");
/* 128:    */         
/* 129:139 */         short raceid = Short.parseShort(strArray[1]);
/* 130:140 */         if (!Pet.PETS[type].races.containsKey(Short.valueOf(raceid)))
/* 131:    */         {
/* 132:141 */           Log.printLog("Invalid Pet Race: type=" + type + " race=" + raceid);
/* 133:142 */           return;
/* 134:    */         }
/* 135:145 */         if (!validPetName(strArray[0])) {
/* 136:146 */           return;
/* 137:    */         }
/* 138:149 */         if (strArray[2].length() != 6) {
/* 139:150 */           return;
/* 140:    */         }
/* 141:153 */         QueueWriter.write(Main.socket, BuyNotificationComposer.compose(product));
/* 142:154 */         buyPet(product, strArray, raceid, Main);
/* 143:    */       }
/* 144:    */       catch (Exception ex)
/* 145:    */       {
/* 146:156 */         Log.printException("PurchaseFromCatalogParser-0", ex);
/* 147:    */       }
/* 148:    */     }
/* 149:    */     else
/* 150:    */     {
/* 151:159 */       if (Main.inventory.isFull(1))
/* 152:    */       {
/* 153:160 */         Utils.AlertFromHotel(Main.socket, cappo.game.utils.lang.LangTexts.texts[6]);
/* 154:161 */         return;
/* 155:    */       }
/* 156:164 */       if (product.uniqueLimitedItemsLaunched.intValue() > 0)
/* 157:    */       {
/* 158:165 */         if (product.uniqueLimitedItemsLeft.intValue() < 1)
/* 159:    */         {
/* 160:166 */           QueueWriter.write(Main.socket, UniqueLimitedItemSoldOutComposer.compose()); return;
/* 161:    */         }
/* 162:169 */         Catalog.CatalogProduct tmp689_687 = product;tmp689_687.uniqueLimitedItemsLeft = Integer.valueOf(tmp689_687.uniqueLimitedItemsLeft.intValue() - 1);
/* 163:    */       }
/* 164:173 */       QueueWriter.write(Main.socket, BuyNotificationComposer.compose(product));
/* 165:174 */       buyProduct(product, extraParam, quantity, Main);
/* 166:    */     }
/* 167:177 */     QueueWriter.write(Main.socket, UnseenItemsComposer.compose(Main.avatarData.UnseenItems));
/* 168:    */   }
/* 169:    */   
/* 170:    */   public static void buyProduct(Catalog.CatalogProduct product, String extraParam, int buyAmmount, Connection Main)
/* 171:    */   {
/* 172:181 */     PlayerData playerData = Main.getPlayerData();
/* 173:183 */     for (Catalog.CatalogSubItem subItem : product.items)
/* 174:    */     {
/* 175:184 */       if (subItem.baseItem.Type.equals("r"))
/* 176:    */       {
/* 177:185 */         for (int j = 0; j < subItem.amount.intValue(); j++) {
/* 178:186 */           buyBot(product, Main);
/* 179:    */         }
/* 180:188 */         return;
/* 181:    */       }
/* 182:191 */       if (subItem.baseItem.Type.equals("e"))
/* 183:    */       {
/* 184:192 */         for (int j = 0; j < subItem.amount.intValue(); j++)
/* 185:    */         {
/* 186:193 */           int Time = 10;
/* 187:194 */           Main.addEffect(subItem.baseItem.SpriteId, 601);
/* 188:    */         }
/* 189:196 */         return;
/* 190:    */       }
/* 191:199 */       StuffDataWriter data = null;
/* 192:201 */       if (subItem.baseItem.itemExtraType == 0)
/* 193:    */       {
/* 194:202 */         data = new StuffDataWriter(0);
/* 195:204 */         if (subItem.baseItem.logic == BaseItem.FurniLogic.ROOMDIMMER) {
/* 196:205 */           data.writeString("1,1,1,#000000,255");
/* 197:206 */         } else if (subItem.baseItem.itemCategory == 11) {
/* 198:207 */           data.writeString(playerData.userName + '\t' + Server.date.format(Utils.GetDateNow()) + '\t' + extraParam);
/* 199:    */         } else {
/* 200:209 */           data.writeString(extraParam);
/* 201:    */         }
/* 202:    */       }
/* 203:211 */       else if (subItem.baseItem.itemExtraType == 1)
/* 204:    */       {
/* 205:212 */         data = new StuffDataWriter(1);
/* 206:214 */         if (subItem.baseItem.itemCategory == 2)
/* 207:    */         {
/* 208:215 */           data.writeInt8(1);
/* 209:216 */           data.writeString("state");
/* 210:217 */           data.writeString(extraParam);
/* 211:    */         }
/* 212:218 */         else if (subItem.baseItem.logic == BaseItem.FurniLogic.MANNEQUIN)
/* 213:    */         {
/* 214:219 */           data.writeInt8(3);
/* 215:220 */           data.writeString("GENDER");
/* 216:221 */           data.writeString("M");
/* 217:222 */           data.writeString("FIGURE");
/* 218:223 */           data.writeString("lg-270-82.ch-210-66");
/* 219:224 */           data.writeString("OUTFIT_NAME");
/* 220:225 */           data.writeString("");
/* 221:    */         }
/* 222:    */         else
/* 223:    */         {
/* 224:227 */           data.writeInt8(0);
/* 225:    */         }
/* 226:    */       }
/* 227:229 */       else if (subItem.baseItem.itemExtraType == 2)
/* 228:    */       {
/* 229:230 */         data = new StuffDataWriter(2);
/* 230:232 */         if (subItem.baseItem.logic == BaseItem.FurniLogic.BADGEDISPLAY)
/* 231:    */         {
/* 232:233 */           data.writeInt8(4);
/* 233:234 */           data.writeString("0");
/* 234:235 */           data.writeString(extraParam);
/* 235:236 */           data.writeString(playerData.userName);
/* 236:237 */           data.writeString(Server.date.format(Utils.GetDateNow()));
/* 237:    */         }
/* 238:    */         else
/* 239:    */         {
/* 240:239 */           String[] parts = extraParam.split(";");
/* 241:240 */           data.writeInt8(parts.length);
/* 242:241 */           for (String val : parts) {
/* 243:242 */             data.writeString(val);
/* 244:    */           }
/* 245:    */         }
/* 246:    */       }
/* 247:245 */       else if (subItem.baseItem.itemExtraType == 3)
/* 248:    */       {
/* 249:246 */         data = new StuffDataWriter(3);
/* 250:247 */         data.writeString(extraParam);
/* 251:248 */         data.writeInt16(0);
/* 252:    */       }
/* 253:249 */       else if (subItem.baseItem.itemExtraType != 4)
/* 254:    */       {
/* 255:251 */         if (subItem.baseItem.itemExtraType == 5)
/* 256:    */         {
/* 257:252 */           data = new StuffDataWriter(5);
/* 258:    */           
/* 259:254 */           String[] parts = extraParam.split(";");
/* 260:255 */           data.writeInt8(parts.length);
/* 261:256 */           for (String val : parts) {
/* 262:257 */             data.writeInt32(Integer.parseInt(val));
/* 263:    */           }
/* 264:    */         }
/* 265:259 */         else if (subItem.baseItem.itemExtraType != 6)
/* 266:    */         {
/* 267:261 */           if (subItem.baseItem.itemExtraType == 7)
/* 268:    */           {
/* 269:262 */             data = new StuffDataWriter(7);
/* 270:263 */             data.writeString(extraParam);
/* 271:264 */             data.writeInt16(0);
/* 272:265 */             data.writeInt16(subItem.baseItem.interactionCount);
/* 273:    */           }
/* 274:    */         }
/* 275:    */       }
/* 276:268 */       if (data == null)
/* 277:    */       {
/* 278:269 */         Log.printLog("Not implemented extraType = " + subItem.baseItem.itemExtraType);
/* 279:270 */         return;
/* 280:    */       }
/* 281:273 */       if (subItem.baseItem.interactorType == Interactor.InteractorType.teleport)
/* 282:    */       {
/* 283:276 */         for (int x = 0; x < buyAmmount; x++)
/* 284:    */         {
/* 285:277 */           int ref1 = Server.generateRefItemId();
/* 286:278 */           int ref2 = Server.generateRefItemId();
/* 287:    */           
/* 288:280 */           int Id1 = Server.generateItemId();
/* 289:281 */           int Id2 = Server.generateItemId();
/* 290:    */           
/* 291:283 */           FloorItem floorItem1 = FloorItem.createItem(playerData, ref1, Id1, new StuffDataReader(data.getData()), subItem.extraParam, subItem.baseItem);
/* 292:284 */           Main.inventoryAddFloorItem(floorItem1);
/* 293:285 */           floorItem1.setMysqlState(3);
/* 294:286 */           FloorItem floorItem2 = FloorItem.createItem(playerData, ref2, Id2, new StuffDataReader(data.getData()), subItem.extraParam, subItem.baseItem);
/* 295:287 */           Main.inventoryAddFloorItem(floorItem2);
/* 296:288 */           floorItem2.setMysqlState(3);
/* 297:289 */           Teleports.setParents(Id1, Id2);
/* 298:    */           
/* 299:291 */           Main.avatarData.UnseenItems.AddItem(1, ref1);
/* 300:292 */           Main.avatarData.UnseenItems.AddItem(1, ref2);
/* 301:    */           try
/* 302:    */           {
/* 303:296 */             Database.exec("INSERT IGNORE INTO items_tele_links (tele_one_id,tele_two_id)VALUES(" + Id1 + "," + Id2 + ");", new Object[0]);
/* 304:    */           }
/* 305:    */           catch (Exception ex)
/* 306:    */           {
/* 307:298 */             Log.printException("", ex);
/* 308:    */           }
/* 309:    */         }
/* 310:    */       }
/* 311:    */       else
/* 312:    */       {
/* 313:306 */         if (subItem.baseItem.interactor == Interactor.iterWired) {
/* 314:307 */           Main.giveBadge("WIRD2");
/* 315:    */         }
/* 316:310 */         if (subItem.baseItem.Type.equals("s"))
/* 317:    */         {
/* 318:311 */           Main.avatarData.UnseenItems.AddItem(1, subItem.baseItem.SpriteId);
/* 319:313 */           for (int x = 0; x < buyAmmount; x++) {
/* 320:314 */             for (int i = 0; i < subItem.amount.intValue(); i++)
/* 321:    */             {
/* 322:315 */               int refId = subItem.baseItem.SpriteId;
/* 323:    */               
/* 324:317 */               FloorItem floorItem = FloorItem.createItem(playerData, refId, Server.generateItemId(), new StuffDataReader(data.getData()), subItem.extraParam, subItem.baseItem);
/* 325:318 */               Main.inventoryAddFloorItem(floorItem);
/* 326:319 */               floorItem.setMysqlState(3);
/* 327:    */             }
/* 328:    */           }
/* 329:    */         }
/* 330:325 */         else if (subItem.baseItem.Type.equals("i"))
/* 331:    */         {
/* 332:326 */           Main.avatarData.UnseenItems.AddItem(1, subItem.baseItem.SpriteId);
/* 333:328 */           for (int x = 0; x < buyAmmount; x++) {
/* 334:329 */             for (int i = 0; i < subItem.amount.intValue(); i++)
/* 335:    */             {
/* 336:330 */               int refId = subItem.baseItem.SpriteId;
/* 337:    */               
/* 338:332 */               WallItem wallItem = WallItem.createItem(playerData, refId, Server.generateItemId(), new StuffDataReader(data.getData()), subItem.baseItem);
/* 339:333 */               Main.inventoryAddWallItem(wallItem);
/* 340:334 */               wallItem.setMysqlState(3);
/* 341:    */             }
/* 342:    */           }
/* 343:    */         }
/* 344:    */       }
/* 345:    */     }
/* 346:342 */     QueueWriter.write(Main.socket, FurniListUpdateComposer.compose());
/* 347:    */   }
/* 348:    */   
/* 349:    */   private static void buyPet(Catalog.CatalogProduct item, String[] strArray, int raceid, Connection Main)
/* 350:    */   {
/* 351:355 */     int PetId = Server.generateItemId();
/* 352:    */     
/* 353:357 */     PlayerData playerData = Main.getPlayerData();
/* 354:    */     
/* 355:    */ 
/* 356:360 */     Pet pet = new Pet(PetId, strArray[0], (short)Integer.parseInt(item.itemName.substring(6)), (short)raceid, strArray[2]);
/* 357:361 */     pet.needInsert = true;
/* 358:    */     
/* 359:363 */     pet.ownerId = playerData.userId;
/* 360:364 */     pet.ownerName = playerData.userName;
/* 361:365 */     pet.TimeCreated = Utils.getTimestamp();
/* 362:366 */     pet.Nutrition = 50;
/* 363:367 */     pet.Experience = 0;
/* 364:368 */     pet.Energy = 100;
/* 365:369 */     pet.Respects = 0;
/* 366:370 */     pet.level = 1;
/* 367:    */     
/* 368:372 */     Main.inventory.addPet(PetId, pet);
/* 369:373 */     Main.avatarData.UnseenItems.AddItem(3, PetId);
/* 370:374 */     QueueWriter.write(Main.socket, AddPetToInventoryComposer.compose(pet));
/* 371:375 */     QueueWriter.write(Main.socket, PetReceivedMessageComposer.compose(false, pet));
/* 372:    */   }
/* 373:    */   
/* 374:    */   private static void buyBot(Catalog.CatalogProduct item, Connection Main)
/* 375:    */   {
/* 376:381 */     PlayerData playerData = Main.getPlayerData();
/* 377:    */     
/* 378:383 */     int botId = Server.generateItemId();
/* 379:384 */     short botType = (short)(item.itemName.equals("bot_bartender") ? 1 : 0);
/* 380:    */     
/* 381:386 */     RentalBot bot = new RentalBot(botId, item.itemName, botType);
/* 382:387 */     bot.setDefaults();
/* 383:    */     
/* 384:389 */     bot.ownerId = playerData.userId;
/* 385:390 */     bot.ownerName = playerData.userName;
/* 386:    */     
/* 387:392 */     Main.inventory.addBot(botId, bot);
/* 388:393 */     Main.avatarData.UnseenItems.AddItem(5, botId);
/* 389:394 */     QueueWriter.write(Main.socket, AddBotToInventoryComposer.compose(bot));
/* 390:    */   }
/* 391:    */   
/* 392:    */   private static boolean validPetName(String inputStr)
/* 393:    */   {
/* 394:398 */     int len = inputStr.length();
/* 395:400 */     if ((len < 3) || (len > 15)) {
/* 396:401 */       return false;
/* 397:    */     }
/* 398:404 */     for (char c : inputStr.toCharArray()) {
/* 399:405 */       if (((c < 'a') || (c > 'z')) && ((c < '0') || (c > '9'))) {
/* 400:406 */         return false;
/* 401:    */       }
/* 402:    */     }
/* 403:410 */     return true;
/* 404:    */   }
/* 405:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.catalog.PurchaseFromCatalogParser
 * JD-Core Version:    0.7.0.1
 */