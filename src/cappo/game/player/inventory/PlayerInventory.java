/*   1:    */ package cappo.game.player.inventory;
/*   2:    */ 
/*   3:    */ import cappo.engine.Server;
/*   4:    */ import cappo.engine.database.DBResult;
/*   5:    */ import cappo.engine.database.Database;
/*   6:    */ import cappo.engine.logging.Log;
/*   7:    */ import cappo.engine.player.Clients;
/*   8:    */ import cappo.engine.player.Connection;
/*   9:    */ import cappo.game.bots.RentalBot;
/*  10:    */ import cappo.game.collections.BaseItem;
/*  11:    */ import cappo.game.pets.Pet;
/*  12:    */ import cappo.game.player.PlayerData;
/*  13:    */ import cappo.game.roomengine.entity.item.Item;
/*  14:    */ import cappo.game.roomengine.entity.item.extradata.StuffDataReader;
/*  15:    */ import cappo.game.roomengine.entity.item.extradata.StuffDataWriter;
/*  16:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  17:    */ import cappo.game.roomengine.entity.item.floor.SongItem;
/*  18:    */ import cappo.game.roomengine.entity.item.wall.WallItem;
/*  19:    */ import java.sql.ResultSet;
/*  20:    */ import java.util.Collection;
/*  21:    */ import java.util.Map;
/*  22:    */ import java.util.concurrent.ConcurrentHashMap;
/*  23:    */ 
/*  24:    */ public class PlayerInventory
/*  25:    */ {
/*  26:    */   public Connection cn;
/*  27:    */   public static final int FURNIS = 1;
/*  28:    */   public static final int PETS = 3;
/*  29:    */   public static final int BOTS = 4;
/*  30:    */   public static final int SONGS = 5;
/*  31:    */   private Map<Integer, FloorItem> inventoryObjects;
/*  32:    */   private Map<Integer, WallItem> inventoryItems;
/*  33:    */   private Map<Integer, Pet> inventoryPets;
/*  34:    */   private Map<Integer, RentalBot> inventoryBots;
/*  35:    */   private Map<Integer, SongItem> SongInInventory;
/*  36:    */   
/*  37:    */   public PlayerInventory(Connection con)
/*  38:    */   {
/*  39: 38 */     this.cn = con;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public boolean isFull(int id)
/*  43:    */   {
/*  44: 42 */     if (id == 1)
/*  45:    */     {
/*  46: 43 */       initFurnis();
/*  47: 44 */       return this.inventoryObjects.size() + this.inventoryItems.size() > 6000;
/*  48:    */     }
/*  49: 45 */     if (id == 3)
/*  50:    */     {
/*  51: 46 */       initPets();
/*  52: 47 */       return this.inventoryPets.size() > 19;
/*  53:    */     }
/*  54: 48 */     if (id == 4)
/*  55:    */     {
/*  56: 49 */       initBots();
/*  57: 50 */       return this.inventoryBots.size() > 149;
/*  58:    */     }
/*  59: 52 */     return true;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public boolean objectsReady()
/*  63:    */   {
/*  64: 56 */     return this.inventoryObjects != null;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public boolean itemsReady()
/*  68:    */   {
/*  69: 60 */     return this.inventoryItems != null;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public boolean petsReady()
/*  73:    */   {
/*  74: 64 */     return this.inventoryPets != null;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public boolean botsReady()
/*  78:    */   {
/*  79: 68 */     return this.inventoryBots != null;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public Collection<FloorItem> getObjectsForSave()
/*  83:    */   {
/*  84: 72 */     return this.inventoryObjects.values();
/*  85:    */   }
/*  86:    */   
/*  87:    */   public Collection<WallItem> getItemsForSave()
/*  88:    */   {
/*  89: 76 */     return this.inventoryItems.values();
/*  90:    */   }
/*  91:    */   
/*  92:    */   public Collection<Pet> getPetsForSave()
/*  93:    */   {
/*  94: 80 */     return this.inventoryPets.values();
/*  95:    */   }
/*  96:    */   
/*  97:    */   public Collection<FloorItem> getObjects()
/*  98:    */   {
/*  99: 84 */     initFurnis();
/* 100: 85 */     return this.inventoryObjects.values();
/* 101:    */   }
/* 102:    */   
/* 103:    */   public Collection<WallItem> getItems()
/* 104:    */   {
/* 105: 89 */     initFurnis();
/* 106: 90 */     return this.inventoryItems.values();
/* 107:    */   }
/* 108:    */   
/* 109:    */   public Collection<SongItem> getSongs()
/* 110:    */   {
/* 111: 94 */     initFurnis();
/* 112: 95 */     return this.SongInInventory.values();
/* 113:    */   }
/* 114:    */   
/* 115:    */   public Collection<Pet> getPets()
/* 116:    */   {
/* 117:100 */     initPets();
/* 118:101 */     return this.inventoryPets.values();
/* 119:    */   }
/* 120:    */   
/* 121:    */   public Collection<RentalBot> getBots()
/* 122:    */   {
/* 123:105 */     initBots();
/* 124:106 */     return this.inventoryBots.values();
/* 125:    */   }
/* 126:    */   
/* 127:    */   public Item getFurni(int id)
/* 128:    */   {
/* 129:110 */     initFurnis();
/* 130:111 */     Item item = (Item)this.inventoryObjects.get(Integer.valueOf(id));
/* 131:112 */     if (item == null) {
/* 132:113 */       item = (Item)this.inventoryItems.get(Integer.valueOf(id));
/* 133:    */     }
/* 134:116 */     return item;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public FloorItem getObject(int id)
/* 138:    */   {
/* 139:120 */     initFurnis();
/* 140:121 */     return (FloorItem)this.inventoryObjects.get(Integer.valueOf(id));
/* 141:    */   }
/* 142:    */   
/* 143:    */   public WallItem getItem(int id)
/* 144:    */   {
/* 145:125 */     initFurnis();
/* 146:126 */     return (WallItem)this.inventoryItems.get(Integer.valueOf(id));
/* 147:    */   }
/* 148:    */   
/* 149:    */   public SongItem getSong(int id)
/* 150:    */   {
/* 151:130 */     initFurnis();
/* 152:131 */     return (SongItem)this.SongInInventory.get(Integer.valueOf(id));
/* 153:    */   }
/* 154:    */   
/* 155:    */   public Pet getPet(int id)
/* 156:    */   {
/* 157:135 */     initPets();
/* 158:136 */     return (Pet)this.inventoryPets.get(Integer.valueOf(id));
/* 159:    */   }
/* 160:    */   
/* 161:    */   public RentalBot getBot(int id)
/* 162:    */   {
/* 163:140 */     initBots();
/* 164:141 */     return (RentalBot)this.inventoryBots.get(Integer.valueOf(id));
/* 165:    */   }
/* 166:    */   
/* 167:    */   public void addObject(int id, FloorItem object)
/* 168:    */   {
/* 169:145 */     initFurnis();
/* 170:146 */     this.inventoryObjects.put(Integer.valueOf(id), object);
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void addItem(int id, WallItem item)
/* 174:    */   {
/* 175:150 */     initFurnis();
/* 176:151 */     this.inventoryItems.put(Integer.valueOf(id), item);
/* 177:    */   }
/* 178:    */   
/* 179:    */   public void addSong(int id, SongItem song)
/* 180:    */   {
/* 181:155 */     initFurnis();
/* 182:156 */     this.SongInInventory.put(Integer.valueOf(id), song);
/* 183:    */   }
/* 184:    */   
/* 185:    */   public void addPet(int id, Pet pet)
/* 186:    */   {
/* 187:160 */     initPets();
/* 188:161 */     this.inventoryPets.put(Integer.valueOf(id), pet);
/* 189:    */   }
/* 190:    */   
/* 191:    */   public void addBot(int id, RentalBot bot)
/* 192:    */   {
/* 193:165 */     initBots();
/* 194:166 */     this.inventoryBots.put(Integer.valueOf(id), bot);
/* 195:    */   }
/* 196:    */   
/* 197:    */   public FloorItem removeObject(int id)
/* 198:    */   {
/* 199:170 */     initFurnis();
/* 200:171 */     return (FloorItem)this.inventoryObjects.remove(Integer.valueOf(id));
/* 201:    */   }
/* 202:    */   
/* 203:    */   public WallItem removeItem(int id)
/* 204:    */   {
/* 205:175 */     initFurnis();
/* 206:176 */     return (WallItem)this.inventoryItems.remove(Integer.valueOf(id));
/* 207:    */   }
/* 208:    */   
/* 209:    */   public SongItem removeSong(int id)
/* 210:    */   {
/* 211:180 */     initFurnis();
/* 212:181 */     return (SongItem)this.SongInInventory.remove(Integer.valueOf(id));
/* 213:    */   }
/* 214:    */   
/* 215:    */   public Pet removePet(int id)
/* 216:    */   {
/* 217:185 */     initPets();
/* 218:186 */     return (Pet)this.inventoryPets.remove(Integer.valueOf(id));
/* 219:    */   }
/* 220:    */   
/* 221:    */   public RentalBot removeBot(int id)
/* 222:    */   {
/* 223:190 */     initPets();
/* 224:191 */     return (RentalBot)this.inventoryBots.remove(Integer.valueOf(id));
/* 225:    */   }
/* 226:    */   
/* 227:    */   public void clearFurnis()
/* 228:    */   {
/* 229:195 */     this.inventoryObjects = new ConcurrentHashMap();
/* 230:196 */     this.inventoryItems = new ConcurrentHashMap();
/* 231:197 */     this.SongInInventory = new ConcurrentHashMap();
/* 232:    */   }
/* 233:    */   
/* 234:    */   private void initFurnis()
/* 235:    */   {
/* 236:201 */     if (this.inventoryObjects != null) {
/* 237:202 */       return;
/* 238:    */     }
/* 239:205 */     this.inventoryObjects = new ConcurrentHashMap();
/* 240:206 */     this.inventoryItems = new ConcurrentHashMap();
/* 241:207 */     this.SongInInventory = new ConcurrentHashMap();
/* 242:    */     
/* 243:209 */     DBResult result = new DBResult();
/* 244:    */     try
/* 245:    */     {
/* 246:211 */       boolean ready = checkUpgradeBflyInventory(result);
/* 247:212 */       boolean old = ready;
/* 248:213 */       if (!ready) {
/* 249:214 */         loadInventory(result);
/* 250:    */       }
/* 251:217 */       PlayerData playerData = this.cn.getPlayerData();
/* 252:219 */       while ((ready) || (result.data.next()))
/* 253:    */       {
/* 254:221 */         ready = false;
/* 255:    */         
/* 256:223 */         BaseItem base = (BaseItem)BaseItem.baseItems.get(Integer.valueOf(old ? result.data.getInt("base_id") : result.data.getInt("baseid")));
/* 257:224 */         if (base != null)
/* 258:    */         {
/* 259:228 */           StuffDataReader data = new StuffDataReader(
/* 260:229 */             old ? BaseItem.upgradeStuffData(base, result.data.getString("data")).getData() : 
/* 261:230 */             result.data.getBytes("data"));
/* 262:    */           
/* 263:232 */           int extraParam = old ? result.data.getInt("extra_param") : result.data.getInt("param");
/* 264:233 */           int itemId = old ? result.data.getInt("item_id") : result.data.getInt("id");
/* 265:    */           try
/* 266:    */           {
/* 267:236 */             if (base.Type.equals("s"))
/* 268:    */             {
/* 269:237 */               FloorItem item = FloorItem.createItem(playerData, Server.generateRefItemId(), itemId, data, extraParam, base);
/* 270:238 */               this.cn.inventoryAddFloorItem(item);
/* 271:239 */               if (old) {
/* 272:240 */                 item.setMysqlState(3);
/* 273:    */               }
/* 274:    */             }
/* 275:242 */             else if (base.Type.equals("i"))
/* 276:    */             {
/* 277:243 */               WallItem item = WallItem.createItem(playerData, Server.generateRefItemId(), itemId, data, base);
/* 278:244 */               this.cn.inventoryAddWallItem(item);
/* 279:245 */               if (old) {
/* 280:246 */                 item.setMysqlState(3);
/* 281:    */               }
/* 282:    */             }
/* 283:    */           }
/* 284:    */           catch (Exception ex)
/* 285:    */           {
/* 286:251 */             Log.printException("SSO", ex);
/* 287:    */           }
/* 288:    */         }
/* 289:    */       }
/* 290:255 */       if (old) {
/* 291:256 */         Database.exec(
/* 292:    */         
/* 293:    */ 
/* 294:    */ 
/* 295:260 */           "DELETE da,db,dc FROM items_users AS da LEFT JOIN items_extradata AS db ON db.item_id=da.item_id LEFT JOIN items AS dc ON dc.item_id=da.item_id WHERE da.user_id = " + playerData.userId + ";", new Object[0]);
/* 296:    */       }
/* 297:    */     }
/* 298:    */     catch (Exception ex)
/* 299:    */     {
/* 300:263 */       Log.printException("PlayerInventory", ex);
/* 301:    */     }
/* 302:266 */     result.close();
/* 303:    */   }
/* 304:    */   
/* 305:    */   private boolean checkUpgradeBflyInventory(DBResult result)
/* 306:    */     throws Exception
/* 307:    */   {
/* 308:270 */     Database.query(result, "SELECT items.base_id,items_users.user_id,items_extradata.* FROM items_users LEFT JOIN items ON (items.item_id = items_users.item_id) LEFT JOIN items_extradata ON (items_extradata.item_id = items_users.item_id) WHERE items_users.user_id = " + 
/* 309:    */     
/* 310:    */ 
/* 311:    */ 
/* 312:    */ 
/* 313:    */ 
/* 314:    */ 
/* 315:277 */       this.cn.getPlayerData().userId + ";", new Object[0]);
/* 316:    */     
/* 317:279 */     return result.data.next();
/* 318:    */   }
/* 319:    */   
/* 320:    */   private void loadInventory(DBResult result)
/* 321:    */     throws Exception
/* 322:    */   {
/* 323:283 */     Database.query(result, "SELECT furnis.*,furnis_floorextra.param FROM furnis LEFT JOIN furnis_floorextra ON (furnis_floorextra.id = furnis.id) WHERE furnis.userid=" + 
/* 324:    */     
/* 325:    */ 
/* 326:    */ 
/* 327:    */ 
/* 328:288 */       this.cn.getPlayerData().userId + " AND furnis.roomid=0;", new Object[0]);
/* 329:    */   }
/* 330:    */   
/* 331:    */   private void initPets()
/* 332:    */   {
/* 333:292 */     if (this.inventoryPets != null) {
/* 334:293 */       return;
/* 335:    */     }
/* 336:296 */     this.inventoryPets = new ConcurrentHashMap();
/* 337:    */     
/* 338:298 */     DBResult result = new DBResult();
/* 339:    */     try
/* 340:    */     {
/* 341:300 */       PlayerData playerData = this.cn.getPlayerData();
/* 342:    */       
/* 343:302 */       Database.query(result, "SELECT * FROM user_pets WHERE user_id = " + playerData.userId + " AND room_id = 0;", new Object[0]);
/* 344:303 */       while (result.data.next())
/* 345:    */       {
/* 346:304 */         Pet pet = Clients.generatePetsData(result.data, playerData);
/* 347:305 */         if (pet != null) {
/* 348:306 */           this.inventoryPets.put(Integer.valueOf(pet.id), pet);
/* 349:    */         }
/* 350:    */       }
/* 351:    */     }
/* 352:    */     catch (Exception ex)
/* 353:    */     {
/* 354:310 */       Log.printException("PlayerInventory", ex);
/* 355:    */     }
/* 356:312 */     result.close();
/* 357:    */   }
/* 358:    */   
/* 359:    */   private void initBots()
/* 360:    */   {
/* 361:317 */     if (this.inventoryBots != null) {
/* 362:318 */       return;
/* 363:    */     }
/* 364:321 */     this.inventoryBots = new ConcurrentHashMap();
/* 365:    */     
/* 366:323 */     DBResult result = new DBResult();
/* 367:    */     try
/* 368:    */     {
/* 369:325 */       PlayerData playerData = this.cn.getPlayerData();
/* 370:    */       
/* 371:327 */       Database.query(result, "SELECT * FROM user_bots WHERE user_id = " + playerData.userId + " AND room_id = 0;", new Object[0]);
/* 372:328 */       while (result.data.next())
/* 373:    */       {
/* 374:329 */         RentalBot bot = Clients.generateBotsData(result.data, playerData);
/* 375:330 */         if (bot != null) {
/* 376:331 */           this.inventoryBots.put(Integer.valueOf(bot.id), bot);
/* 377:    */         }
/* 378:    */       }
/* 379:    */     }
/* 380:    */     catch (Exception ex)
/* 381:    */     {
/* 382:335 */       Log.printException("PlayerInventory", ex);
/* 383:    */     }
/* 384:337 */     result.close();
/* 385:    */   }
/* 386:    */   
/* 387:    */   public void clean()
/* 388:    */   {
/* 389:341 */     this.inventoryObjects = null;
/* 390:342 */     this.inventoryItems = null;
/* 391:343 */     this.inventoryPets = null;
/* 392:344 */     this.inventoryBots = null;
/* 393:345 */     this.SongInInventory = null;
/* 394:    */   }
/* 395:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.inventory.PlayerInventory
 * JD-Core Version:    0.7.0.1
 */