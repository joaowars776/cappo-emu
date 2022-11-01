/*    1:     */ package cappo.engine.threadpools;
/*    2:     */ 
/*    3:     */ import cappo.engine.Server;
/*    4:     */ import cappo.engine.ServerProps;
/*    5:     */ import cappo.engine.database.DBResult;
/*    6:     */ import cappo.engine.database.Database;
/*    7:     */ import cappo.engine.logging.Log;
/*    8:     */ import cappo.engine.network.MessageWriter;
/*    9:     */ import cappo.engine.network.QueueWriter;
/*   10:     */ import cappo.engine.player.Clients;
/*   11:     */ import cappo.engine.player.Connection;
/*   12:     */ import cappo.game.bots.RentalBot;
/*   13:     */ import cappo.game.collections.BaseItem;
/*   14:     */ import cappo.game.collections.BaseItem.FurniLogic;
/*   15:     */ import cappo.game.collections.BaseItem.ItemType;
/*   16:     */ import cappo.game.collections.BflyData;
/*   17:     */ import cappo.game.collections.MoodlightData;
/*   18:     */ import cappo.game.collections.Teleports;
/*   19:     */ import cappo.game.collections.Utils;
/*   20:     */ import cappo.game.games.snowwar.Direction8;
/*   21:     */ import cappo.game.inventory.trading.Trade;
/*   22:     */ import cappo.game.inventory.trading.TradeUser;
/*   23:     */ import cappo.game.navigator.NavigatorCategories;
/*   24:     */ import cappo.game.pets.Pet;
/*   25:     */ import cappo.game.pets.PetBase;
/*   26:     */ import cappo.game.player.AvatarLook;
/*   27:     */ import cappo.game.player.PlayerData;
/*   28:     */ import cappo.game.player.data.AvatarData;
/*   29:     */ import cappo.game.player.inventory.PlayerInventory;
/*   30:     */ import cappo.game.player.messenger.MessengerFriend;
/*   31:     */ import cappo.game.player.messenger.MessengerFriendUpdate;
/*   32:     */ import cappo.game.player.messenger.PlayerMessenger;
/*   33:     */ import cappo.game.roomeffects.special.UserSpecialEffect;
/*   34:     */ import cappo.game.roomengine.RoomData;
/*   35:     */ import cappo.game.roomengine.RoomIcon;
/*   36:     */ import cappo.game.roomengine.RoomManager;
/*   37:     */ import cappo.game.roomengine.Square;
/*   38:     */ import cappo.game.roomengine.SquareFlagManager;
/*   39:     */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*   40:     */ import cappo.game.roomengine.entity.item.extradata.StuffDataReader;
/*   41:     */ import cappo.game.roomengine.entity.item.extradata.StuffDataWriter;
/*   42:     */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*   43:     */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*   44:     */ import cappo.game.roomengine.entity.item.floor.RollerItem;
/*   45:     */ import cappo.game.roomengine.entity.item.floor.RoomFloorItemData;
/*   46:     */ import cappo.game.roomengine.entity.item.floor.RoomFloorItemData.AffectedTile;
/*   47:     */ import cappo.game.roomengine.entity.item.floor.SongItem;
/*   48:     */ import cappo.game.roomengine.entity.item.floor.wired.WiredItemBase;
/*   49:     */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*   50:     */ import cappo.game.roomengine.entity.item.wall.RoomWallItemData;
/*   51:     */ import cappo.game.roomengine.entity.item.wall.WallItem;
/*   52:     */ import cappo.game.roomengine.entity.live.Avatar;
/*   53:     */ import cappo.game.roomengine.entity.live.LiveEntity;
/*   54:     */ import cappo.game.roomengine.entity.live.PetEntity;
/*   55:     */ import cappo.game.roomengine.entity.live.RentalBotEntity;
/*   56:     */ import cappo.game.roomengine.gamemap.CustomGameMap;
/*   57:     */ import cappo.game.roomengine.gamemap.DynamicGameMap;
/*   58:     */ import cappo.game.roomengine.gamemap.GameMapBase;
/*   59:     */ import cappo.game.roomengine.itemInteractor.Interactor;
/*   60:     */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/*   61:     */ import cappo.game.roomengine.itemInteractor.InteractorTeleport.TeleportAttach;
/*   62:     */ import cappo.game.roomengine.roomevents.Event;
/*   63:     */ import cappo.game.roomengine.roomevents.RollerEvent;
/*   64:     */ import cappo.game.roomengine.roomevents.Teleport_OUT;
/*   65:     */ import cappo.game.roomengine.roomevents.UserChat;
/*   66:     */ import cappo.game.roomengine.roomlisting.RoomListing;
/*   67:     */ import cappo.game.roomengine.roomlisting.RoomListing.ListingRoomState;
/*   68:     */ import cappo.game.roomengine.settings.ChatSettings;
/*   69:     */ import cappo.game.roomengine.settings.ControllerLevels;
/*   70:     */ import cappo.game.roomengine.settings.ModerationPermissions;
/*   71:     */ import cappo.game.roomengine.settings.PlayerBan;
/*   72:     */ import cappo.game.roomengine.settings.PlayerRight;
/*   73:     */ import cappo.game.roomengine.settings.TradingSettings;
/*   74:     */ import cappo.game.roomengine.wired.WiredManager;
/*   75:     */ import cappo.game.sound.trax.TraxPlaylist;
/*   76:     */ import cappo.protocol.messages.composers.handshake.GenericErrorComposer;
/*   77:     */ import cappo.protocol.messages.composers.inventory.pets.AddPetToInventoryComposer;
/*   78:     */ import cappo.protocol.messages.composers.inventory.trading.TradingCloseComposer;
/*   79:     */ import cappo.protocol.messages.composers.navigator.DoorBellNoAnswerComposer;
/*   80:     */ import cappo.protocol.messages.composers.navigator.DoorbellUserComposer;
/*   81:     */ import cappo.protocol.messages.composers.navigator.EventComposer;
/*   82:     */ import cappo.protocol.messages.composers.navigator.FlatAccessDeniedComposer;
/*   83:     */ import cappo.protocol.messages.composers.navigator.RoomRatingComposer;
/*   84:     */ import cappo.protocol.messages.composers.room.action.UserAsleepComposer;
/*   85:     */ import cappo.protocol.messages.composers.room.engine.ItemAddComposer;
/*   86:     */ import cappo.protocol.messages.composers.room.engine.ItemRemoveComposer;
/*   87:     */ import cappo.protocol.messages.composers.room.engine.ItemUpdateComposer;
/*   88:     */ import cappo.protocol.messages.composers.room.engine.ObjectAddComposer;
/*   89:     */ import cappo.protocol.messages.composers.room.engine.ObjectDataUpdateComposer;
/*   90:     */ import cappo.protocol.messages.composers.room.engine.ObjectRemoveComposer;
/*   91:     */ import cappo.protocol.messages.composers.room.engine.ObjectUpdateComposer;
/*   92:     */ import cappo.protocol.messages.composers.room.engine.ObjectsDataUpdateComposer;
/*   93:     */ import cappo.protocol.messages.composers.room.engine.RoomPropertyComposer;
/*   94:     */ import cappo.protocol.messages.composers.room.engine.UserRemoveComposer;
/*   95:     */ import cappo.protocol.messages.composers.room.engine.UserUpdateComposer;
/*   96:     */ import cappo.protocol.messages.composers.room.engine.UsersComposer;
/*   97:     */ import cappo.protocol.messages.composers.room.permissions.YouAreControllerComposer;
/*   98:     */ import cappo.protocol.messages.composers.room.permissions.YouAreNotControllerComposer;
/*   99:     */ import cappo.protocol.messages.composers.room.permissions.YouAreOwnerComposer;
/*  100:     */ import cappo.protocol.messages.composers.room.session.CloseConnectionComposer;
/*  101:     */ import cappo.protocol.messages.composers.room.session.OpenConnectionComposer;
/*  102:     */ import cappo.protocol.messages.composers.room.session.RoomReadyComposer;
/*  103:     */ import cappo.protocol.messages.composers.room.session.YouArePlayingGameComposer;
/*  104:     */ import java.sql.ResultSet;
/*  105:     */ import java.util.ArrayList;
/*  106:     */ import java.util.Collection;
/*  107:     */ import java.util.HashMap;
/*  108:     */ import java.util.HashSet;
/*  109:     */ import java.util.Iterator;
/*  110:     */ import java.util.List;
/*  111:     */ import java.util.Map;
/*  112:     */ import java.util.Set;
/*  113:     */ import java.util.concurrent.ConcurrentHashMap;
/*  114:     */ import java.util.concurrent.ScheduledFuture;
/*  115:     */ 
/*  116:     */ public class RoomTask
/*  117:     */   extends GameTask
/*  118:     */ {
/*  119:     */   public RoomData roomData;
/*  120:     */   public int roomId;
/*  121:     */   public GameMapBase model;
/*  122:     */   public Map<Integer, Avatar> userList;
/*  123:     */   
/*  124:     */   public static void addTask(GameTask task, int initDelay, int repeatDelay)
/*  125:     */   {
/*  126: 110 */     WorkerTasks.addTask(task, initDelay, repeatDelay, WorkerTasks.RoomsTasks);
/*  127:     */   }
/*  128:     */   
/*  129: 119 */   public final Map<Integer, PetEntity> petList = new ConcurrentHashMap(5);
/*  130: 120 */   public final Map<Integer, RentalBotEntity> rentalBotList = new ConcurrentHashMap(5);
/*  131:     */   private Map<Integer, Event> userEvents;
/*  132:     */   private Integer eventIdGeneratorUsers;
/*  133:     */   public Map<Integer, PlayerRight> usersWithRights;
/*  134:     */   public Map<Integer, PlayerBan> usersBanned;
/*  135: 129 */   public final List<GenericFloorItem> roomGamesScorersRED = new ArrayList(3);
/*  136: 130 */   public final List<GenericFloorItem> roomGamesScorersGREEN = new ArrayList(3);
/*  137: 131 */   public final List<GenericFloorItem> roomGamesScorersBLUE = new ArrayList(3);
/*  138: 132 */   public final List<GenericFloorItem> roomGamesScorersYELLOW = new ArrayList(3);
/*  139:     */   public Map<Integer, Direction8> MatrixRot;
/*  140:     */   public Map<Integer, FloorItem> FloorItems;
/*  141:     */   public Map<Integer, GenericWallItem> WallItems;
/*  142: 138 */   public short VirtualIdCounter = 1;
/*  143: 140 */   private final Set<FloorItem> UpdateFloorItems = new HashSet(50);
/*  144: 141 */   private final Set<WallItem> UpdateWallItems = new HashSet(50);
/*  145: 142 */   private final Map<Short, LiveEntity> UpdateUsers = new ConcurrentHashMap();
/*  146:     */   public Map<Integer, Float> squareAbsoluteHeight;
/*  147:     */   public Map<Integer, FloorItem> topFloorItems;
/*  148:     */   public Map<Integer, Map<Integer, FloorItem>> mapFloorItems;
/*  149:     */   public Map<Integer, RollerItem> rollers;
/*  150:     */   public SquareFlagManager squareFlag;
/*  151:     */   public MoodlightData MoodlightData;
/*  152:     */   public WiredManager wiredManager;
/*  153:     */   public int userCount;
/*  154:     */   public int PetCounter;
/*  155:     */   public int ScorePoints_B;
/*  156:     */   public int ScorePoints_G;
/*  157:     */   public int ScorePoints_R;
/*  158:     */   public int ScorePoints_Y;
/*  159: 162 */   public final TraxPlaylist traxPlaylist = new TraxPlaylist();
/*  160:     */   public final RoomListing.ListingRoomState[] roomListingState;
/*  161:     */   public Map<Integer, Map<Short, LiveEntity>> usersMatrix;
/*  162:     */   private int timeOut;
/*  163:     */   
/*  164:     */   public boolean squareHasUsers(int xy)
/*  165:     */   {
/*  166: 172 */     Map<Short, LiveEntity> users = (Map)this.usersMatrix.get(Integer.valueOf(xy));
/*  167: 173 */     if ((users == null) || (users.isEmpty())) {
/*  168: 174 */       return false;
/*  169:     */     }
/*  170: 176 */     return true;
/*  171:     */   }
/*  172:     */   
/*  173:     */   public void entityWalk(int xy, LiveEntity ent, boolean add)
/*  174:     */   {
/*  175: 180 */     Map<Short, LiveEntity> users = (Map)this.usersMatrix.get(Integer.valueOf(xy));
/*  176: 181 */     if (users == null)
/*  177:     */     {
/*  178: 182 */       if (!add) {
/*  179: 183 */         return;
/*  180:     */       }
/*  181: 185 */       users = new ConcurrentHashMap();
/*  182: 186 */       this.usersMatrix.put(Integer.valueOf(xy), users);
/*  183:     */     }
/*  184: 189 */     if (add)
/*  185:     */     {
/*  186: 190 */       users.put(Short.valueOf(ent.virtualId), ent);
/*  187:     */     }
/*  188:     */     else
/*  189:     */     {
/*  190: 192 */       users.remove(Short.valueOf(ent.virtualId));
/*  191: 193 */       if (users.isEmpty()) {
/*  192: 194 */         this.usersMatrix.remove(Integer.valueOf(xy));
/*  193:     */       }
/*  194:     */     }
/*  195:     */   }
/*  196:     */   
/*  197:     */   public RoomTask(RoomData self)
/*  198:     */   {
/*  199: 200 */     this.roomData = self;
/*  200: 201 */     this.roomId = self.roomId;
/*  201: 202 */     this.model = RoomManager.getModel(self.model);
/*  202:     */     
/*  203: 204 */     this.roomListingState = new RoomListing.ListingRoomState[2 + (1 + NavigatorCategories.MAX_ID)];
/*  204:     */   }
/*  205:     */   
/*  206:     */   private boolean checkUpgradeBflyFurnis(DBResult result)
/*  207:     */     throws Exception
/*  208:     */   {
/*  209: 209 */     Database.query(result, "SELECT items.base_id,items_rooms.*,items_extradata.* FROM items_rooms LEFT JOIN items ON (items.item_id = items_rooms.item_id) LEFT JOIN items_extradata ON (items_extradata.item_id = items_rooms.item_id) WHERE items_rooms.room_id = " + 
/*  210:     */     
/*  211:     */ 
/*  212:     */ 
/*  213:     */ 
/*  214:     */ 
/*  215:     */ 
/*  216: 216 */       this.roomId + ";", new Object[0]);
/*  217:     */     
/*  218: 218 */     return result.data.next();
/*  219:     */   }
/*  220:     */   
/*  221:     */   public void init()
/*  222:     */   {
/*  223: 222 */     PlayerData roomOwner = Clients.getPlayerData(this.roomData.roomOwnerId);
/*  224: 223 */     if (roomOwner == null)
/*  225:     */     {
/*  226: 224 */       roomOwner = Clients.getPlayerData(this.roomData.roomOwnerName);
/*  227: 225 */       if (roomOwner == null)
/*  228:     */       {
/*  229: 226 */         Log.printLog("loadRoomResultSet, owner=NULL, id=" + this.roomData.roomId);
/*  230: 227 */         return;
/*  231:     */       }
/*  232: 230 */       this.roomData.roomOwnerId = roomOwner.userId;
/*  233:     */       try
/*  234:     */       {
/*  235: 233 */         Database.exec("UPDATE `rooms` SET `user_id`='" + roomOwner.userId + "' WHERE id='" + this.roomData.roomId + "';", new Object[0]);
/*  236:     */       }
/*  237:     */       catch (Exception ex)
/*  238:     */       {
/*  239: 235 */         Log.printException("loadRoomResultSet", ex);
/*  240:     */       }
/*  241:     */     }
/*  242: 238 */     if (!roomOwner.userName.equals(this.roomData.roomOwnerName))
/*  243:     */     {
/*  244: 239 */       this.roomData.roomOwnerName = roomOwner.userName;
/*  245:     */       try
/*  246:     */       {
/*  247: 241 */         Database.exec("UPDATE `rooms` SET `user_name`='" + roomOwner.userName + "' WHERE id='" + this.roomData.roomId + "';", new Object[0]);
/*  248:     */       }
/*  249:     */       catch (Exception ex)
/*  250:     */       {
/*  251: 243 */         Log.printException("loadRoomResultSet", ex);
/*  252:     */       }
/*  253:     */     }
/*  254: 246 */     this.roomData.roomOwner = roomOwner;
/*  255:     */     
/*  256: 248 */     this.userList = new ConcurrentHashMap(this.roomData.usersMax);
/*  257:     */     
/*  258: 250 */     this.rollers = new ConcurrentHashMap();
/*  259:     */     
/*  260: 252 */     this.wiredManager = new WiredManager();
/*  261: 253 */     this.MatrixRot = new ConcurrentHashMap();
/*  262: 254 */     this.WallItems = new ConcurrentHashMap(10);
/*  263: 255 */     this.FloorItems = new ConcurrentHashMap(100);
/*  264: 256 */     this.usersMatrix = new ConcurrentHashMap();
/*  265:     */     
/*  266: 258 */     this.userEvents = new ConcurrentHashMap();
/*  267:     */     
/*  268: 260 */     this.eventIdGeneratorUsers = Integer.valueOf(0);
/*  269:     */     
/*  270: 262 */     DBResult result = new DBResult();
/*  271: 263 */     DBResult resultInternal = new DBResult();
/*  272: 264 */     DBResult resultInternal2 = new DBResult();
/*  273:     */     try
/*  274:     */     {
/*  275: 266 */       Map<FloorItem, List<RoomFloorItemData.AffectedTile>> tmpFloorItems = new HashMap();
/*  276: 267 */       int maxX = this.model.widthX - 1;int maxY = this.model.heightY - 1;
/*  277:     */       
/*  278: 269 */       boolean ready = checkUpgradeBflyFurnis(result);
/*  279: 270 */       boolean old = ready;
/*  280: 271 */       if (!ready) {
/*  281: 272 */         loadFurnis(result);
/*  282:     */       }
/*  283:     */       int itemOwnerId;
/*  284:     */       int extraParam;
/*  285: 275 */       while ((ready) || (result.data.next()))
/*  286:     */       {
/*  287: 277 */         ready = false;
/*  288:     */         try
/*  289:     */         {
/*  290: 280 */           BaseItem base = (BaseItem)BaseItem.baseItems.get(Integer.valueOf(old ? result.data.getInt("base_id") : result.data.getInt("baseid")));
/*  291: 281 */           if (base != null)
/*  292:     */           {
/*  293: 285 */             StuffDataReader data = new StuffDataReader(
/*  294: 286 */               old ? BaseItem.upgradeStuffData(base, result.data.getString("data")).getData() : 
/*  295: 287 */               result.data.getBytes("data"));
/*  296:     */             
/*  297: 289 */             itemOwnerId = old ? 0 : result.data.getInt("userid");
/*  298: 290 */             int itemId = old ? result.data.getInt("item_id") : result.data.getInt("id");
/*  299: 291 */             extraParam = old ? result.data.getInt("extra_param") : result.data.getInt("param");
/*  300:     */             
/*  301: 293 */             float x = old ? result.data.getFloat("x") : result.data.getFloat("a");
/*  302: 294 */             float y = old ? result.data.getFloat("y") : result.data.getFloat("b");
/*  303: 295 */             int n = old ? result.data.getInt("n") : result.data.getInt("r");
/*  304:     */             PlayerData itemOwner;
/*  305:     */             PlayerData itemOwner;
/*  306: 299 */             if ((this.roomData.roomOwner != null) && (this.roomData.roomOwner.userId == itemOwnerId))
/*  307:     */             {
/*  308: 300 */               itemOwner = this.roomData.roomOwner;
/*  309:     */             }
/*  310:     */             else
/*  311:     */             {
/*  312: 302 */               itemOwner = Clients.getPlayerDataLoaded(itemOwnerId);
/*  313: 303 */               if (itemOwner == null) {
/*  314: 304 */                 itemOwner = this.roomData.roomOwner;
/*  315: 306 */               } else if (this.roomData.roomOwner == null) {
/*  316: 308 */                 this.roomData.roomOwner = itemOwner;
/*  317:     */               }
/*  318:     */             }
/*  319: 313 */             if (base.Type.equals("s"))
/*  320:     */             {
/*  321: 314 */               int refId = Server.generateRefItemId();
/*  322: 315 */               FloorItem roomItem = FloorItem.createItem(itemOwner, refId, itemId, data, extraParam, base);
/*  323: 316 */               if (old) {
/*  324: 317 */                 roomItem.setMysqlState(3);
/*  325:     */               }
/*  326: 321 */               if ((roomItem.baseItem.interactor != Interactor.iterWired) || 
/*  327: 322 */                 ((roomItem instanceof WiredItemBase)))
/*  328:     */               {
/*  329: 327 */                 int iY = BflyData.getB(x);
/*  330: 328 */                 int iX = BflyData.getA(x, iY);
/*  331: 329 */                 roomItem.setRoomData(new RoomFloorItemData(this, roomItem));
/*  332: 330 */                 roomItem.setPosition(iX, iY);
/*  333: 331 */                 roomItem.setPosition(y);
/*  334: 332 */                 roomItem.setDir(Direction8.getDirection(n));
/*  335:     */                 
/*  336: 334 */                 List<RoomFloorItemData.AffectedTile> tiles = roomItem.getAffectedTiles(true);
/*  337:     */                 
/*  338: 336 */                 boolean invalid = false;
/*  339: 337 */                 for (RoomFloorItemData.AffectedTile tile : tiles)
/*  340:     */                 {
/*  341: 338 */                   if (tile.x > maxX) {
/*  342: 339 */                     maxX = tile.x;
/*  343:     */                   }
/*  344: 341 */                   if (tile.y > maxY) {
/*  345: 342 */                     maxY = tile.y;
/*  346:     */                   }
/*  347: 345 */                   if ((tile.x == this.model.doorX) && (tile.y == this.model.doorY)) {
/*  348: 346 */                     invalid = true;
/*  349:     */                   }
/*  350:     */                 }
/*  351: 349 */                 if (!invalid)
/*  352:     */                 {
/*  353: 353 */                   if (roomItem.baseItem.interactorType == Interactor.InteractorType.roller) {
/*  354: 354 */                     this.rollers.put(Integer.valueOf(roomItem.itemId), (RollerItem)roomItem);
/*  355:     */                   }
/*  356: 357 */                   tmpFloorItems.put(roomItem, tiles);
/*  357:     */                 }
/*  358:     */               }
/*  359:     */             }
/*  360: 358 */             else if (base.Type.equals("i"))
/*  361:     */             {
/*  362: 359 */               int refId = Server.generateRefItemId();
/*  363: 360 */               WallItem wallItem = WallItem.createItem(itemOwner, refId, itemId, data, base);
/*  364: 361 */               if (old) {
/*  365: 362 */                 wallItem.setMysqlState(3);
/*  366:     */               }
/*  367: 365 */               if ((wallItem instanceof GenericWallItem))
/*  368:     */               {
/*  369: 369 */                 GenericWallItem roomItem = (GenericWallItem)wallItem;
/*  370: 370 */                 if (roomItem.baseItem.logic == BaseItem.FurniLogic.ROOMDIMMER)
/*  371:     */                 {
/*  372: 371 */                   if (this.MoodlightData == null)
/*  373:     */                   {
/*  374: 375 */                     this.MoodlightData = new MoodlightData(roomItem.itemId);
/*  375: 376 */                     roomItem.extraData.setExtraData(this.MoodlightData.GenerateExtraData());
/*  376:     */                   }
/*  377:     */                 }
/*  378:     */                 else
/*  379:     */                 {
/*  380: 379 */                   roomItem.setRoomData(new RoomWallItemData(this, roomItem.baseItem, x, y, n));
/*  381: 380 */                   this.WallItems.put(Integer.valueOf(roomItem.itemId), roomItem);
/*  382:     */                 }
/*  383:     */               }
/*  384:     */             }
/*  385:     */           }
/*  386:     */         }
/*  387:     */         catch (Exception ex)
/*  388:     */         {
/*  389: 384 */           Log.printException("Room", ex);
/*  390:     */         }
/*  391:     */       }
/*  392: 388 */       if (old) {
/*  393: 389 */         Database.exec(
/*  394:     */         
/*  395:     */ 
/*  396:     */ 
/*  397: 393 */           "DELETE da,db,dc FROM items_rooms AS da LEFT JOIN items_extradata AS db ON db.item_id=da.item_id LEFT JOIN items AS dc ON dc.item_id=da.item_id WHERE da.room_id = " + this.roomId + ";", new Object[0]);
/*  398:     */       }
/*  399: 397 */       if ((maxX >= this.model.widthX) || (maxY >= this.model.heightY))
/*  400:     */       {
/*  401: 400 */         GameMapBase exModel = this.model;
/*  402:     */         
/*  403: 402 */         this.model = new DynamicGameMap(this.model, maxX + 1, maxY + 1);
/*  404: 403 */         for (itemOwnerId = tmpFloorItems.values().iterator(); itemOwnerId.hasNext(); extraParam.hasNext())
/*  405:     */         {
/*  406: 403 */           List<RoomFloorItemData.AffectedTile> tiles = (List)itemOwnerId.next();
/*  407: 404 */           extraParam = tiles.iterator(); continue;RoomFloorItemData.AffectedTile tile = (RoomFloorItemData.AffectedTile)extraParam.next();
/*  408: 405 */           if ((tile.x >= exModel.widthX) || (tile.y >= exModel.heightY))
/*  409:     */           {
/*  410: 406 */             int pos = tile.x + tile.y * this.model.widthX;
/*  411: 407 */             this.model.setSquare(pos, new Square(tile.x, tile.y, pos, 0.0F));
/*  412:     */           }
/*  413:     */         }
/*  414: 411 */         this.model.buildSquares();
/*  415:     */       }
/*  416: 414 */       this.squareFlag = new SquareFlagManager();
/*  417: 415 */       this.squareAbsoluteHeight = new ConcurrentHashMap();
/*  418: 416 */       this.topFloorItems = new ConcurrentHashMap();
/*  419: 417 */       this.mapFloorItems = new ConcurrentHashMap();
/*  420:     */       
/*  421: 419 */       int xy = 0;
/*  422:     */       int x;
/*  423: 420 */       for (int y = 0; y < this.model.heightY; y++) {
/*  424: 421 */         for (x = 0; x < this.model.widthX; x++)
/*  425:     */         {
/*  426: 422 */           Square sq = this.model.getSquare(xy);
/*  427: 424 */           if ((x == this.model.doorX) && (y == this.model.doorY))
/*  428:     */           {
/*  429: 425 */             this.squareAbsoluteHeight.put(Integer.valueOf(xy), Float.valueOf(sq.height));
/*  430: 426 */             this.squareFlag.SetFlag(xy, 8, true);
/*  431:     */           }
/*  432: 427 */           else if (sq != null)
/*  433:     */           {
/*  434: 428 */             this.squareAbsoluteHeight.put(Integer.valueOf(xy), Float.valueOf(sq.height));
/*  435:     */           }
/*  436: 431 */           xy++;
/*  437:     */         }
/*  438:     */       }
/*  439: 435 */       for (FloorItem roomItem : tmpFloorItems.keySet())
/*  440:     */       {
/*  441: 436 */         roomItem.setPosition();
/*  442: 437 */         roomItem.finishPlace((List)tmpFloorItems.get(roomItem));
/*  443:     */       }
/*  444: 440 */       for (FloorItem roomItem : tmpFloorItems.keySet()) {
/*  445: 442 */         if (roomItem.baseItem.interactor == Interactor.iterWired)
/*  446:     */         {
/*  447: 443 */           WiredItemBase wired = (WiredItemBase)roomItem;
/*  448: 444 */           wired.loadData(resultInternal);
/*  449:     */         }
/*  450: 445 */         else if ((roomItem.baseItem.interactorType == Interactor.InteractorType.teleport) && 
/*  451: 446 */           (!Teleports.teleLoaded(roomItem.itemId)))
/*  452:     */         {
/*  453: 447 */           Teleports.setRoom(roomItem.itemId, this.roomId);
/*  454: 448 */           Database.query(resultInternal, "SELECT * FROM items_tele_links WHERE tele_one_id=" + roomItem.itemId + " OR tele_two_id=" + roomItem.itemId + " LIMIT 1;", new Object[0]);
/*  455: 449 */           if (resultInternal.data.next())
/*  456:     */           {
/*  457: 451 */             int tl1 = resultInternal.data.getInt("tele_one_id");
/*  458:     */             int otherId;
/*  459:     */             int otherId;
/*  460: 452 */             if (tl1 != roomItem.itemId)
/*  461:     */             {
/*  462: 453 */               Teleports.setParents(tl1, roomItem.itemId);
/*  463: 454 */               otherId = tl1;
/*  464:     */             }
/*  465:     */             else
/*  466:     */             {
/*  467: 456 */               int tl2 = resultInternal.data.getInt("tele_two_id");
/*  468: 457 */               Teleports.setParents(roomItem.itemId, tl2);
/*  469: 458 */               otherId = tl2;
/*  470:     */             }
/*  471: 461 */             Database.query(resultInternal2, "SELECT roomid FROM furnis WHERE id=" + otherId + " LIMIT 1;", new Object[0]);
/*  472: 462 */             if (resultInternal2.data.next()) {
/*  473: 463 */               Teleports.setRoom(otherId, resultInternal2.data.getInt("roomid"));
/*  474:     */             }
/*  475:     */           }
/*  476:     */         }
/*  477:     */       }
/*  478: 470 */       Database.query(result, "SELECT * FROM user_pets WHERE room_id = " + this.roomId + " LIMIT 1;", new Object[0]);
/*  479: 471 */       while (result.data.next())
/*  480:     */       {
/*  481: 472 */         Pet pet = Clients.generatePetsData(result.data, this.roomData.roomOwner);
/*  482: 473 */         if (pet != null) {
/*  483: 474 */           deployPet(pet, result.data.getInt("x") + result.data.getInt("y") * this.model.widthX, result.data.getInt("z"));
/*  484:     */         }
/*  485:     */       }
/*  486: 478 */       Database.query(result, "SELECT * FROM user_bots WHERE room_id = " + this.roomId + " LIMIT 1;", new Object[0]);
/*  487: 479 */       while (result.data.next())
/*  488:     */       {
/*  489: 480 */         RentalBot bot = Clients.generateBotsData(result.data, this.roomData.roomOwner);
/*  490: 481 */         if (bot != null) {
/*  491: 482 */           deployBot(bot, 
/*  492: 483 */             result.data.getInt("x") + result.data.getInt("y") * this.model.widthX, 
/*  493: 484 */             result.data.getInt("z"), 
/*  494: 485 */             new AvatarLook(result.data.getString("look")), 
/*  495: 486 */             result.data.getString("gender"));
/*  496:     */         }
/*  497:     */       }
/*  498: 490 */       Database.query(result, "SELECT songid,itemid FROM room_discs WHERE roomid = " + this.roomId + ";", new Object[0]);
/*  499: 491 */       while (result.data.next())
/*  500:     */       {
/*  501: 492 */         int songDisc = result.data.getInt("songid");
/*  502: 493 */         if (songDisc > 0)
/*  503:     */         {
/*  504: 494 */           SongItem song = new SongItem();
/*  505: 495 */           song.itemId = result.data.getInt("itemid");
/*  506: 496 */           song.baseItem = ((BaseItem)BaseItem.baseItems.get(Integer.valueOf(2964)));
/*  507: 497 */           song.owner = this.roomData.roomOwner;
/*  508: 498 */           song.setExtraParam(songDisc);
/*  509: 499 */           this.traxPlaylist.PlaylistByIndex.add(song);
/*  510:     */         }
/*  511:     */       }
/*  512:     */     }
/*  513:     */     catch (Exception ex)
/*  514:     */     {
/*  515: 503 */       Log.printException("Room-LoadRoom", ex);
/*  516:     */     }
/*  517: 506 */     resultInternal.close();
/*  518: 507 */     resultInternal2.close();
/*  519: 508 */     result.close();
/*  520:     */     
/*  521:     */ 
/*  522: 511 */     int xy = 0;
/*  523: 512 */     for (int y = 0; y < this.model.heightY; y++) {
/*  524: 513 */       for (int x = 0; x < this.model.widthX; x++)
/*  525:     */       {
/*  526: 514 */         if ((this.model.getSquare(xy) != null) && 
/*  527: 515 */           (this.mapFloorItems.get(Integer.valueOf(xy)) == null)) {
/*  528: 516 */           this.squareFlag.SetFlag(xy, 4, true);
/*  529:     */         }
/*  530: 520 */         xy++;
/*  531:     */       }
/*  532:     */     }
/*  533: 524 */     RoomManager.setActive();
/*  534:     */   }
/*  535:     */   
/*  536:     */   private void loadFurnis(DBResult result)
/*  537:     */     throws Exception
/*  538:     */   {
/*  539: 528 */     Database.query(result, "SELECT furnis.*,furnis_floorextra.param,furnis_roomdata.a,furnis_roomdata.b,furnis_roomdata.r FROM furnis LEFT JOIN furnis_roomdata ON (furnis_roomdata.id = furnis.id) LEFT JOIN furnis_floorextra ON (furnis_floorextra.id = furnis.id) WHERE furnis.roomid = " + 
/*  540:     */     
/*  541:     */ 
/*  542:     */ 
/*  543:     */ 
/*  544:     */ 
/*  545:     */ 
/*  546:     */ 
/*  547:     */ 
/*  548: 537 */       this.roomId + ";", new Object[0]);
/*  549:     */   }
/*  550:     */   
/*  551:     */   public void addBan(PlayerData player, int seconds)
/*  552:     */   {
/*  553: 542 */     this.usersBanned.put(Integer.valueOf(player.userId), new PlayerBan(player, Utils.getTimestamp() + seconds));
/*  554:     */   }
/*  555:     */   
/*  556:     */   public PlayerBan removeBan(int userId)
/*  557:     */   {
/*  558: 546 */     return (PlayerBan)this.usersBanned.remove(Integer.valueOf(userId));
/*  559:     */   }
/*  560:     */   
/*  561:     */   public boolean hasBanExpired(int Id)
/*  562:     */   {
/*  563: 550 */     PlayerBan ban = (PlayerBan)this.usersBanned.get(Integer.valueOf(Id));
/*  564: 551 */     if ((ban != null) && (Utils.getTimestamp() > ban.timeout))
/*  565:     */     {
/*  566: 552 */       this.usersBanned.remove(Integer.valueOf(Id));
/*  567: 553 */       return true;
/*  568:     */     }
/*  569: 556 */     return false;
/*  570:     */   }
/*  571:     */   
/*  572:     */   public boolean userIsBanned(int Id)
/*  573:     */   {
/*  574: 560 */     return this.usersBanned.containsKey(Integer.valueOf(Id));
/*  575:     */   }
/*  576:     */   
/*  577:     */   public void updateMaxUsers(int size)
/*  578:     */   {
/*  579: 564 */     if (this.userList == null)
/*  580:     */     {
/*  581: 565 */       this.userList = new ConcurrentHashMap(size);
/*  582:     */     }
/*  583:     */     else
/*  584:     */     {
/*  585: 567 */       if (this.userList.size() > size) {
/*  586: 569 */         return;
/*  587:     */       }
/*  588: 572 */       Map<Integer, Avatar> tmp = this.userList;
/*  589: 573 */       this.userList = new ConcurrentHashMap(size);
/*  590: 574 */       this.userList.putAll(tmp);
/*  591:     */     }
/*  592:     */   }
/*  593:     */   
/*  594:     */   public void sendMessage(MessageWriter message)
/*  595:     */   {
/*  596: 579 */     for (Avatar User : this.userList.values()) {
/*  597: 580 */       QueueWriter.writeAndFlush(User.cn.socket, message);
/*  598:     */     }
/*  599:     */   }
/*  600:     */   
/*  601:     */   public void sendMessage(MessageWriter message, int... needRights)
/*  602:     */   {
/*  603: 585 */     if (needRights.length == 0) {
/*  604: 586 */       sendMessage(message);
/*  605:     */     } else {
/*  606: 588 */       for (Avatar User : this.userList.values()) {
/*  607: 589 */         for (int level : needRights) {
/*  608: 590 */           if (User.controllerLevel == level)
/*  609:     */           {
/*  610: 591 */             QueueWriter.writeAndFlush(User.cn.socket, message);
/*  611: 592 */             break;
/*  612:     */           }
/*  613:     */         }
/*  614:     */       }
/*  615:     */     }
/*  616:     */   }
/*  617:     */   
/*  618:     */   public void addItemEvent(Event task, int ticks)
/*  619:     */   {
/*  620: 600 */     addUserEvent(task, ticks);
/*  621:     */   }
/*  622:     */   
/*  623:     */   public void addUserEvent(Event task, int ticks)
/*  624:     */   {
/*  625: 604 */     task.Ticks = ticks;
/*  626: 605 */     synchronized (this.eventIdGeneratorUsers)
/*  627:     */     {
/*  628: 606 */       this.eventIdGeneratorUsers = Integer.valueOf((this.eventIdGeneratorUsers.intValue() + 1) % 999999);
/*  629: 607 */       this.userEvents.put(this.eventIdGeneratorUsers, task);
/*  630: 608 */       task.eventId = this.eventIdGeneratorUsers;
/*  631:     */     }
/*  632:     */   }
/*  633:     */   
/*  634:     */   public void onUserSay(Avatar User, String Message)
/*  635:     */   {
/*  636: 613 */     for (PetEntity pet : this.petList.values()) {
/*  637: 614 */       pet.OnUserSay(this, User, Message);
/*  638:     */     }
/*  639:     */   }
/*  640:     */   
/*  641:     */   public void addUserToRoom(Connection connection)
/*  642:     */   {
/*  643: 619 */     this.VirtualIdCounter = ((short)((this.VirtualIdCounter = (short)(this.VirtualIdCounter + 1)) % 32767));
/*  644:     */     
/*  645: 621 */     Avatar avatarEntity = new Avatar(connection, this, this.VirtualIdCounter);
/*  646: 622 */     connection.avatar = avatarEntity;
/*  647:     */     
/*  648: 624 */     avatarEntity.SetPos(this.model.doorX, this.model.doorY, this.model.doorZ);
/*  649: 625 */     avatarEntity.SetRot(this.model.DoorOrientation);
/*  650:     */     
/*  651: 627 */     avatarEntity.controllerLevel = ControllerLevels.getLevel(connection.playerData, this.roomData, this);
/*  652: 628 */     avatarEntity.entityType = 1;
/*  653: 630 */     if ((avatarEntity.entityType == 1) && (avatarEntity.cn.teleport != null))
/*  654:     */     {
/*  655: 631 */       GenericFloorItem Item = (GenericFloorItem)getFloorItem(avatarEntity.cn.teleport.itemId);
/*  656: 633 */       if (Item != null)
/*  657:     */       {
/*  658: 634 */         Item.setIntData(2);
/*  659: 635 */         floorItemUpdateNeeded(Item);
/*  660: 636 */         avatarEntity.SetPos(Item.getX(), Item.getY(), Item.getZ());
/*  661: 637 */         avatarEntity.SetRot(Item.getDir());
/*  662: 638 */         addItemEvent(new Teleport_OUT(avatarEntity, Item), 2);
/*  663:     */       }
/*  664:     */     }
/*  665: 642 */     for (MessengerFriend friend : connection.playerData.messenger.getFriends())
/*  666:     */     {
/*  667: 643 */       PlayerData friendPlayer = Clients.getPlayerData(friend.userId);
/*  668: 644 */       if (friendPlayer != null)
/*  669:     */       {
/*  670: 645 */         PlayerMessenger messenger = friendPlayer.messenger;
/*  671: 646 */         if (messenger.isOnline) {
/*  672: 647 */           messenger.update(new MessengerFriendUpdate(connection.playerData.userId, 0));
/*  673:     */         }
/*  674:     */       }
/*  675:     */     }
/*  676: 652 */     for (PetEntity roomUser : this.petList.values()) {
/*  677: 653 */       roomUser.OnUserEnterRoom(this, avatarEntity);
/*  678:     */     }
/*  679: 656 */     this.userList.put(Integer.valueOf(avatarEntity.id), avatarEntity);
/*  680: 657 */     sendMessage(UsersComposer.compose(avatarEntity));
/*  681:     */     
/*  682: 659 */     this.userCount += 1;
/*  683: 660 */     RoomListing.updatePopularRooms(this);
/*  684:     */   }
/*  685:     */   
/*  686:     */   public void deployPet(Pet PetData, int xy, float Z)
/*  687:     */   {
/*  688: 664 */     this.VirtualIdCounter = ((short)((this.VirtualIdCounter = (short)(this.VirtualIdCounter + 1)) % 32767));
/*  689: 665 */     PetEntity petEntity = new PetEntity(this, this.VirtualIdCounter);
/*  690:     */     
/*  691: 667 */     this.PetCounter += 1;
/*  692:     */     int Y;
/*  693:     */     int X;
/*  694: 671 */     if (!validTile(xy))
/*  695:     */     {
/*  696: 672 */       int X = this.model.doorX;
/*  697: 673 */       int Y = this.model.doorY;
/*  698: 674 */       Z = this.model.doorZ;
/*  699:     */     }
/*  700:     */     else
/*  701:     */     {
/*  702: 677 */       Y = xy / this.model.widthX;
/*  703: 678 */       X = xy - Y * this.model.widthX;
/*  704:     */     }
/*  705: 681 */     petEntity.entityType = 2;
/*  706: 682 */     petEntity.SetPos(X, Y, Z);
/*  707: 683 */     petEntity.SetRot(this.model.DoorOrientation);
/*  708:     */     
/*  709: 685 */     petEntity.petData = PetData;
/*  710: 686 */     petEntity.look = (PetData.petType + " " + PetData.base.raceId + " " + PetData.Color);
/*  711: 687 */     petEntity.motto = "";
/*  712: 688 */     PetData.petEntity = petEntity;
/*  713:     */     
/*  714: 690 */     entityWalk(petEntity.xy, petEntity, true);
/*  715:     */     
/*  716: 692 */     updateUserStatus(petEntity, false);
/*  717:     */     
/*  718: 694 */     this.petList.put(Integer.valueOf(PetData.id), petEntity);
/*  719:     */     
/*  720: 696 */     sendMessage(UsersComposer.compose(petEntity));
/*  721:     */     
/*  722: 698 */     petEntity.OnSelfEnterRoom(this);
/*  723:     */   }
/*  724:     */   
/*  725:     */   public void deployBot(RentalBot botData, int xy, float Z, AvatarLook look, String gender)
/*  726:     */   {
/*  727: 703 */     this.VirtualIdCounter = ((short)((this.VirtualIdCounter = (short)(this.VirtualIdCounter + 1)) % 32767));
/*  728: 704 */     RentalBotEntity botEntity = new RentalBotEntity(this, this.VirtualIdCounter);
/*  729:     */     
/*  730: 706 */     this.PetCounter += 1;
/*  731:     */     int Y;
/*  732:     */     int X;
/*  733: 710 */     if (!validTile(xy))
/*  734:     */     {
/*  735: 711 */       int X = this.model.doorX;
/*  736: 712 */       int Y = this.model.doorY;
/*  737: 713 */       Z = this.model.doorZ;
/*  738:     */     }
/*  739:     */     else
/*  740:     */     {
/*  741: 716 */       Y = xy / this.model.widthX;
/*  742: 717 */       X = xy - Y * this.model.widthX;
/*  743:     */     }
/*  744: 720 */     botEntity.entityType = 4;
/*  745: 721 */     botEntity.SetPos(X, Y, Z);
/*  746: 722 */     botEntity.SetRot(this.model.DoorOrientation);
/*  747:     */     
/*  748: 724 */     botEntity.botData = botData;
/*  749: 725 */     botData.botLook = look;
/*  750: 726 */     botData.gender = gender;
/*  751: 727 */     botData.motto = "I am a bot!";
/*  752: 728 */     botData.botEntity = botEntity;
/*  753:     */     
/*  754: 730 */     entityWalk(botEntity.xy, botEntity, true);
/*  755:     */     
/*  756: 732 */     updateUserStatus(botEntity, false);
/*  757:     */     
/*  758: 734 */     this.rentalBotList.put(Integer.valueOf(botData.id), botEntity);
/*  759:     */     
/*  760: 736 */     sendMessage(UsersComposer.compose(botEntity));
/*  761:     */     
/*  762: 738 */     botEntity.OnSelfEnterRoom(this);
/*  763:     */   }
/*  764:     */   
/*  765:     */   public FloorItem getFloorItem(int Id)
/*  766:     */   {
/*  767: 742 */     return (FloorItem)this.FloorItems.get(Integer.valueOf(Id));
/*  768:     */   }
/*  769:     */   
/*  770:     */   public WallItem getWallItem(int Id)
/*  771:     */   {
/*  772: 746 */     return (WallItem)this.WallItems.get(Integer.valueOf(Id));
/*  773:     */   }
/*  774:     */   
/*  775:     */   public PetEntity getRoomPetById(int Id)
/*  776:     */   {
/*  777: 750 */     return (PetEntity)this.petList.get(Integer.valueOf(Id));
/*  778:     */   }
/*  779:     */   
/*  780:     */   public RentalBotEntity getRoomBotById(int Id)
/*  781:     */   {
/*  782: 754 */     return (RentalBotEntity)this.rentalBotList.get(Integer.valueOf(Id));
/*  783:     */   }
/*  784:     */   
/*  785:     */   public Avatar getRoomUserById(int Id)
/*  786:     */   {
/*  787: 758 */     return (Avatar)this.userList.get(Integer.valueOf(Id));
/*  788:     */   }
/*  789:     */   
/*  790:     */   public Avatar getRoomUserByVirtualId(int VirtualId)
/*  791:     */   {
/*  792: 762 */     for (Avatar User : this.userList.values()) {
/*  793: 763 */       if (User.virtualId == VirtualId) {
/*  794: 764 */         return User;
/*  795:     */       }
/*  796:     */     }
/*  797: 767 */     return null;
/*  798:     */   }
/*  799:     */   
/*  800:     */   public float calculateZ(int xy)
/*  801:     */   {
/*  802: 772 */     Float newZ = (Float)this.squareAbsoluteHeight.get(Integer.valueOf(xy));
/*  803: 773 */     if (newZ == null) {
/*  804: 774 */       newZ = Float.valueOf(0.0F);
/*  805:     */     }
/*  806: 777 */     if (this.squareFlag.have(xy, 2))
/*  807:     */     {
/*  808: 778 */       FloorItem top = (FloorItem)this.topFloorItems.get(Integer.valueOf(xy));
/*  809: 779 */       if (top == null) {
/*  810: 780 */         return newZ.floatValue();
/*  811:     */       }
/*  812: 783 */       newZ = Float.valueOf(newZ.floatValue() - top.baseItem.Height);
/*  813:     */     }
/*  814: 784 */     else if (this.squareFlag.have(xy, 1))
/*  815:     */     {
/*  816: 785 */       FloorItem top = (FloorItem)this.topFloorItems.get(Integer.valueOf(xy));
/*  817: 786 */       if (top == null) {
/*  818: 787 */         return newZ.floatValue();
/*  819:     */       }
/*  820: 790 */       newZ = Float.valueOf(newZ.floatValue() - top.baseItem.Height);
/*  821:     */     }
/*  822: 793 */     return newZ.floatValue();
/*  823:     */   }
/*  824:     */   
/*  825:     */   public void updateUserStatus(LiveEntity User, boolean check)
/*  826:     */   {
/*  827: 797 */     Float newZ = (Float)this.squareAbsoluteHeight.get(Integer.valueOf(User.xy));
/*  828: 798 */     if (newZ == null) {
/*  829: 799 */       newZ = Float.valueOf(0.0F);
/*  830:     */     }
/*  831: 802 */     if (check)
/*  832:     */     {
/*  833: 803 */       if (this.squareFlag.have(User.xy, 2))
/*  834:     */       {
/*  835: 804 */         FloorItem top = (FloorItem)this.topFloorItems.get(Integer.valueOf(User.xy));
/*  836: 805 */         if (top != null)
/*  837:     */         {
/*  838: 806 */           User.z = top.getZ();
/*  839: 807 */           Direction8 dir = (Direction8)this.MatrixRot.get(Integer.valueOf(User.xy));
/*  840: 808 */           if (dir == null)
/*  841:     */           {
/*  842: 809 */             Log.printLog("ERROR: updateUserStatus - MatrixRot=NULL");
/*  843:     */           }
/*  844:     */           else
/*  845:     */           {
/*  846: 811 */             User.RotHead = dir;
/*  847: 812 */             User.RotBody = dir;
/*  848:     */           }
/*  849: 814 */           User.setStatus("sit", Float.toString(top.baseItem.Height));
/*  850: 816 */           if ((User instanceof Avatar))
/*  851:     */           {
/*  852: 817 */             Avatar avatar = (Avatar)User;
/*  853: 818 */             if (avatar.userSpecialEffect != null) {
/*  854: 820 */               avatar.userSpecialEffect.stopEffect();
/*  855:     */             }
/*  856:     */           }
/*  857: 823 */           return;
/*  858:     */         }
/*  859:     */       }
/*  860: 828 */       if (this.squareFlag.have(User.xy, 1))
/*  861:     */       {
/*  862: 829 */         FloorItem top = (FloorItem)this.topFloorItems.get(Integer.valueOf(User.xy));
/*  863: 831 */         if (top != null)
/*  864:     */         {
/*  865: 832 */           if ((top.getDir() == Direction8.N) || (top.getDir() == Direction8.S)) {
/*  866: 833 */             User.y = top.getY();
/*  867:     */           } else {
/*  868: 835 */             User.x = top.getX();
/*  869:     */           }
/*  870: 837 */           User.z = top.getZ();
/*  871: 838 */           Direction8 dir = (Direction8)this.MatrixRot.get(Integer.valueOf(User.xy));
/*  872: 839 */           if (dir == null)
/*  873:     */           {
/*  874: 840 */             Log.printLog("ERROR: updateUserStatus - MatrixRot=NULL");
/*  875:     */           }
/*  876:     */           else
/*  877:     */           {
/*  878: 842 */             User.RotHead = dir;
/*  879: 843 */             User.RotBody = dir;
/*  880:     */           }
/*  881: 846 */           User.setStatus("lay", Float.toString(top.baseItem.Height));
/*  882: 848 */           if ((User instanceof Avatar))
/*  883:     */           {
/*  884: 849 */             Avatar avatar = (Avatar)User;
/*  885: 850 */             if (avatar.userSpecialEffect != null) {
/*  886: 852 */               avatar.userSpecialEffect.stopEffect();
/*  887:     */             }
/*  888:     */           }
/*  889: 855 */           return;
/*  890:     */         }
/*  891:     */       }
/*  892: 859 */       if (User.HaveStatus("sit")) {
/*  893: 860 */         User.setStatus("", "");
/*  894: 861 */       } else if (User.HaveStatus("lay")) {
/*  895: 862 */         User.setStatus("", "");
/*  896:     */       }
/*  897:     */     }
/*  898: 866 */     if (newZ.floatValue() != User.z)
/*  899:     */     {
/*  900: 867 */       User.z = newZ.floatValue();
/*  901: 868 */       userUpdateNeeded(User);
/*  902:     */     }
/*  903:     */   }
/*  904:     */   
/*  905:     */   public void userUpdateNeeded(LiveEntity ent)
/*  906:     */   {
/*  907: 873 */     this.UpdateUsers.put(Short.valueOf(ent.virtualId), ent);
/*  908:     */   }
/*  909:     */   
/*  910:     */   public boolean validTile(int xy)
/*  911:     */   {
/*  912: 877 */     if ((xy < 0) || (xy >= this.model.widthX * this.model.heightY)) {
/*  913: 878 */       return false;
/*  914:     */     }
/*  915: 881 */     return this.model.getSquare(xy) != null;
/*  916:     */   }
/*  917:     */   
/*  918:     */   public void sendMessageCustom(MessageWriter message, MessageWriter alternativeMessage, int... filterIds)
/*  919:     */   {
/*  920: 885 */     for (Avatar User : this.userList.values())
/*  921:     */     {
/*  922: 886 */       boolean sended = false;
/*  923: 887 */       for (int userId : filterIds) {
/*  924: 888 */         if (User.cn.playerData.userId == userId)
/*  925:     */         {
/*  926: 889 */           QueueWriter.writeAndFlush(User.cn.socket, message);
/*  927: 890 */           sended = true;
/*  928: 891 */           break;
/*  929:     */         }
/*  930:     */       }
/*  931: 894 */       if (!sended) {
/*  932: 895 */         QueueWriter.writeAndFlush(User.cn.socket, alternativeMessage);
/*  933:     */       }
/*  934:     */     }
/*  935:     */   }
/*  936:     */   
/*  937: 902 */   private int rollerTest = 0;
/*  938:     */   
/*  939:     */   public void run()
/*  940:     */   {
/*  941:     */     try
/*  942:     */     {
/*  943: 906 */       if (this.timeOut >= 0) {
/*  944: 907 */         if (this.userCount == 0)
/*  945:     */         {
/*  946: 908 */           if (++this.timeOut > 1200)
/*  947:     */           {
/*  948: 911 */             this.timeOut = -1;
/*  949: 912 */             RoomManager.setInactive(this.roomData);
/*  950: 913 */             updateMysqlData();
/*  951: 914 */             this.future.cancel(false);
/*  952:     */           }
/*  953:     */         }
/*  954: 918 */         else if (this.timeOut > 0) {
/*  955: 919 */           this.timeOut = 0;
/*  956:     */         }
/*  957:     */       }
/*  958: 924 */       if (this.timeOut == -1)
/*  959:     */       {
/*  960: 925 */         this.future.cancel(false);
/*  961: 926 */         return;
/*  962:     */       }
/*  963: 929 */       if (!ServerProps.STATUS)
/*  964:     */       {
/*  965: 930 */         updateMysqlData();
/*  966: 931 */         this.future.cancel(false);
/*  967: 932 */         return;
/*  968:     */       }
/*  969: 935 */       long now = System.currentTimeMillis();
/*  970: 937 */       for (Event evt : this.userEvents.values()) {
/*  971: 938 */         parseEvent(evt);
/*  972:     */       }
/*  973: 941 */       if (this.rollerTest == 0) {
/*  974: 942 */         RollerEvent.run(this, this.rollers);
/*  975:     */       }
/*  976: 944 */       this.rollerTest = ((this.rollerTest + 1) % 4);
/*  977: 946 */       for (PetEntity pet : this.petList.values()) {
/*  978: 947 */         pet.OnTimerTick(this);
/*  979:     */       }
/*  980: 950 */       for (RentalBotEntity bot : this.rentalBotList.values()) {
/*  981: 951 */         bot.OnTimerTick(this);
/*  982:     */       }
/*  983: 954 */       for (Avatar user : this.userList.values())
/*  984:     */       {
/*  985: 955 */         if (user.carryItemID > 0) {
/*  986: 956 */           if (--user.carryTimer < 1) {
/*  987: 957 */             user.CarryItem(0);
/*  988:     */           }
/*  989:     */         }
/*  990: 961 */         if (++user.idleTime < 600)
/*  991:     */         {
/*  992: 962 */           if (user.IsAsleep)
/*  993:     */           {
/*  994: 963 */             user.IsAsleep = false;
/*  995:     */             
/*  996: 965 */             sendMessage(UserAsleepComposer.compose(user.virtualId, Boolean.valueOf(user.IsAsleep)));
/*  997:     */           }
/*  998:     */         }
/*  999: 967 */         else if (!user.IsAsleep)
/* 1000:     */         {
/* 1001: 968 */           user.IsAsleep = true;
/* 1002: 969 */           sendMessage(UserAsleepComposer.compose(user.virtualId, Boolean.valueOf(user.IsAsleep)));
/* 1003:     */         }
/* 1004:     */       }
/* 1005:     */       FloorItem Item;
/* 1006: 973 */       synchronized (this.UpdateFloorItems)
/* 1007:     */       {
/* 1008: 974 */         int size = this.UpdateFloorItems.size();
/* 1009: 975 */         if (size > 1)
/* 1010:     */         {
/* 1011: 976 */           sendMessage(ObjectsDataUpdateComposer.compose(this.UpdateFloorItems));
/* 1012: 977 */           this.UpdateFloorItems.clear();
/* 1013:     */         }
/* 1014: 978 */         else if (size == 1)
/* 1015:     */         {
/* 1016: 979 */           for (Iterator localIterator2 = this.UpdateFloorItems.iterator(); localIterator2.hasNext();)
/* 1017:     */           {
/* 1018: 979 */             Item = (FloorItem)localIterator2.next();
/* 1019: 980 */             sendMessage(ObjectDataUpdateComposer.compose(Item));
/* 1020:     */           }
/* 1021: 982 */           this.UpdateFloorItems.clear();
/* 1022:     */         }
/* 1023:     */       }
/* 1024: 986 */       synchronized (this.UpdateWallItems)
/* 1025:     */       {
/* 1026: 987 */         if (!this.UpdateWallItems.isEmpty())
/* 1027:     */         {
/* 1028: 988 */           for (WallItem wallIten : this.UpdateWallItems) {
/* 1029: 989 */             if (wallIten.getRoomId() == this.roomId) {
/* 1030: 990 */               sendMessage(ItemUpdateComposer.compose((GenericWallItem)wallIten));
/* 1031:     */             }
/* 1032:     */           }
/* 1033: 993 */           this.UpdateWallItems.clear();
/* 1034:     */         }
/* 1035:     */       }
/* 1036: 997 */       long delay = System.currentTimeMillis() - now;
/* 1037: 998 */       if (delay > 10L) {
/* 1038: 999 */         Log.printLog("RoomSlow | id=" + this.roomId + " | FloorItems.size()=" + this.FloorItems.size() + " | PetCounter=" + this.PetCounter + " | userCount=" + this.userCount + " | ms=" + delay);
/* 1039:     */       }
/* 1040:1002 */       synchronized (this.UpdateUsers)
/* 1041:     */       {
/* 1042:1003 */         if (!this.UpdateUsers.isEmpty())
/* 1043:     */         {
/* 1044:1004 */           sendMessage(UserUpdateComposer.compose(this.UpdateUsers.values()));
/* 1045:1005 */           this.UpdateUsers.clear();
/* 1046:     */         }
/* 1047:     */       }
/* 1048:1011 */       return;
/* 1049:     */     }
/* 1050:     */     catch (Exception ex)
/* 1051:     */     {
/* 1052:1009 */       Log.printException("Room-1", ex);
/* 1053:     */     }
/* 1054:     */   }
/* 1055:     */   
/* 1056:     */   public boolean canPlacePet(int xy)
/* 1057:     */   {
/* 1058:1016 */     if (!validTile(xy)) {
/* 1059:1017 */       return false;
/* 1060:     */     }
/* 1061:1020 */     if (!this.squareFlag.have(xy, 4)) {
/* 1062:1021 */       return false;
/* 1063:     */     }
/* 1064:1024 */     if (squareHasUsers(xy)) {
/* 1065:1025 */       return false;
/* 1066:     */     }
/* 1067:1028 */     return true;
/* 1068:     */   }
/* 1069:     */   
/* 1070:     */   public boolean canWalk(LiveEntity user, int xy, boolean LastStep)
/* 1071:     */   {
/* 1072:1032 */     if (!validTile(xy)) {
/* 1073:1033 */       return false;
/* 1074:     */     }
/* 1075:1036 */     if (user.allowOverride) {
/* 1076:1037 */       return true;
/* 1077:     */     }
/* 1078:1040 */     if (!this.squareFlag.have(xy, 4))
/* 1079:     */     {
/* 1080:1041 */       if (!LastStep) {
/* 1081:1042 */         return false;
/* 1082:     */       }
/* 1083:1045 */       if (!this.squareFlag.have(xy, 8)) {
/* 1084:1046 */         return false;
/* 1085:     */       }
/* 1086:     */     }
/* 1087:1050 */     if ((!this.roomData.haveFlag(8)) && (squareHasUsers(xy))) {
/* 1088:1051 */       return false;
/* 1089:     */     }
/* 1090:1054 */     return true;
/* 1091:     */   }
/* 1092:     */   
/* 1093:     */   private FloorItem updateTopFloorItem(Map<Integer, FloorItem> squareItems)
/* 1094:     */   {
/* 1095:1058 */     float topZ = 0.0F;
/* 1096:1059 */     FloorItem top = null;
/* 1097:1060 */     for (FloorItem floorItem : squareItems.values()) {
/* 1098:1061 */       if ((top == null) || (floorItem.getZ() > topZ))
/* 1099:     */       {
/* 1100:1062 */         topZ = floorItem.getZ();
/* 1101:1063 */         top = floorItem;
/* 1102:     */       }
/* 1103:     */     }
/* 1104:1067 */     return top;
/* 1105:     */   }
/* 1106:     */   
/* 1107:     */   public void generateSquare(int xy, FloorItem item, boolean add, boolean isLoading)
/* 1108:     */   {
/* 1109:1073 */     if (this.model.getSquare(xy) == null) {
/* 1110:1074 */       return;
/* 1111:     */     }
/* 1112:1077 */     BaseItem baseIten = item.baseItem;
/* 1113:     */     
/* 1114:1079 */     Map<Integer, FloorItem> squareItems = (Map)this.mapFloorItems.get(Integer.valueOf(xy));
/* 1115:1080 */     if ((add) && (squareItems == null))
/* 1116:     */     {
/* 1117:1081 */       squareItems = new ConcurrentHashMap();
/* 1118:1082 */       this.mapFloorItems.put(Integer.valueOf(xy), squareItems);
/* 1119:     */     }
/* 1120:1086 */     FloorItem top = (FloorItem)this.topFloorItems.get(Integer.valueOf(xy));
/* 1121:1087 */     if (add)
/* 1122:     */     {
/* 1123:1088 */       if ((!isLoading) || (top == null))
/* 1124:     */       {
/* 1125:1089 */         top = item;
/* 1126:1090 */         this.topFloorItems.put(Integer.valueOf(xy), top);
/* 1127:     */       }
/* 1128:1093 */       else if ((top.getZ() < item.getZ()) || (
/* 1129:1094 */         (top.getZ() == item.getZ()) && (top.baseItem.Height < baseIten.Height)))
/* 1130:     */       {
/* 1131:1095 */         top = item;
/* 1132:1096 */         this.topFloorItems.put(Integer.valueOf(xy), top);
/* 1133:     */       }
/* 1134:1100 */       squareItems.put(Integer.valueOf(item.itemId), item);
/* 1135:     */     }
/* 1136:     */     else
/* 1137:     */     {
/* 1138:1102 */       squareItems.remove(Integer.valueOf(item.itemId));
/* 1139:1103 */       if (squareItems.isEmpty())
/* 1140:     */       {
/* 1141:1104 */         squareItems = null;
/* 1142:1105 */         this.mapFloorItems.remove(Integer.valueOf(xy));
/* 1143:     */       }
/* 1144:1108 */       if (top.itemId == item.itemId)
/* 1145:     */       {
/* 1146:1109 */         top = squareItems == null ? null : updateTopFloorItem(squareItems);
/* 1147:1110 */         if (top == null) {
/* 1148:1111 */           this.topFloorItems.remove(Integer.valueOf(xy));
/* 1149:     */         } else {
/* 1150:1113 */           this.topFloorItems.put(Integer.valueOf(xy), top);
/* 1151:     */         }
/* 1152:     */       }
/* 1153:     */     }
/* 1154:1122 */     if (top != null) {
/* 1155:1123 */       this.squareAbsoluteHeight.put(Integer.valueOf(xy), Float.valueOf(top.getZ() + top.baseItem.Height));
/* 1156:     */     } else {
/* 1157:1125 */       this.squareAbsoluteHeight.put(Integer.valueOf(xy), Float.valueOf(this.model.getSquare(xy).height));
/* 1158:     */     }
/* 1159:1128 */     if (add)
/* 1160:     */     {
/* 1161:1129 */       if (baseIten.allowSit) {
/* 1162:1130 */         this.MatrixRot.put(Integer.valueOf(xy), item.getDir());
/* 1163:     */       }
/* 1164:1133 */       if (baseIten.itemType == BaseItem.ItemType.ROOMGAME_SCORE) {
/* 1165:1134 */         if ((baseIten.interactorType == Interactor.InteractorType.banzaiscoreblue) || 
/* 1166:1135 */           (baseIten.interactorType == Interactor.InteractorType.footballcounterblue)) {
/* 1167:1136 */           this.roomGamesScorersBLUE.add((GenericFloorItem)item);
/* 1168:1137 */         } else if ((baseIten.interactorType == Interactor.InteractorType.banzaiscoregreen) || 
/* 1169:1138 */           (baseIten.interactorType == Interactor.InteractorType.footballcountergreen)) {
/* 1170:1139 */           this.roomGamesScorersGREEN.add((GenericFloorItem)item);
/* 1171:1140 */         } else if ((baseIten.interactorType == Interactor.InteractorType.banzaiscorered) || 
/* 1172:1141 */           (baseIten.interactorType == Interactor.InteractorType.footballcounterred)) {
/* 1173:1142 */           this.roomGamesScorersRED.add((GenericFloorItem)item);
/* 1174:1143 */         } else if ((baseIten.interactorType == Interactor.InteractorType.banzaiscoreyellow) || 
/* 1175:1144 */           (baseIten.interactorType == Interactor.InteractorType.footballcounteryellow)) {
/* 1176:1145 */           this.roomGamesScorersYELLOW.add((GenericFloorItem)item);
/* 1177:     */         }
/* 1178:     */       }
/* 1179:     */     }
/* 1180:     */     else
/* 1181:     */     {
/* 1182:1149 */       if (baseIten.allowSit) {
/* 1183:1150 */         this.MatrixRot.remove(Integer.valueOf(xy));
/* 1184:     */       }
/* 1185:1153 */       if (baseIten.interactorType == Interactor.InteractorType.banzaiscoreblue) {
/* 1186:1154 */         this.roomGamesScorersBLUE.remove(item);
/* 1187:1155 */       } else if (baseIten.interactorType == Interactor.InteractorType.banzaiscoregreen) {
/* 1188:1156 */         this.roomGamesScorersGREEN.remove(item);
/* 1189:1157 */       } else if (baseIten.interactorType == Interactor.InteractorType.banzaiscorered) {
/* 1190:1158 */         this.roomGamesScorersRED.remove(item);
/* 1191:1159 */       } else if (baseIten.interactorType == Interactor.InteractorType.banzaiscoreyellow) {
/* 1192:1160 */         this.roomGamesScorersYELLOW.remove(item);
/* 1193:     */       }
/* 1194:     */     }
/* 1195:1164 */     setupSquareFlags(xy, item, add, isLoading, top);
/* 1196:     */     
/* 1197:1166 */     Map<Short, LiveEntity> users = (Map)this.usersMatrix.get(Integer.valueOf(xy));
/* 1198:1167 */     if (users != null) {
/* 1199:1168 */       for (LiveEntity roomUser : users.values()) {
/* 1200:1169 */         updateUserStatus(roomUser, true);
/* 1201:     */       }
/* 1202:     */     }
/* 1203:     */   }
/* 1204:     */   
/* 1205:     */   private void setupSquareFlags(int xy, FloorItem item, boolean add, boolean isLoading, FloorItem topItem)
/* 1206:     */   {
/* 1207:1175 */     BaseItem baseIten = item.baseItem;
/* 1208:1177 */     if (add)
/* 1209:     */     {
/* 1210:1178 */       if (baseIten.allowSit)
/* 1211:     */       {
/* 1212:1179 */         this.squareFlag.SetFlag(xy, 4, false);
/* 1213:1180 */         this.squareFlag.SetFlag(xy, 8, true);
/* 1214:1182 */         if (baseIten.interactorType == Interactor.InteractorType.bed) {
/* 1215:1183 */           this.squareFlag.SetFlag(xy, 1, true);
/* 1216:     */         } else {
/* 1217:1185 */           this.squareFlag.SetFlag(xy, 2, true);
/* 1218:     */         }
/* 1219:     */       }
/* 1220:1188 */       else if ((!isLoading) || (topItem == item))
/* 1221:     */       {
/* 1222:1189 */         this.squareFlag.SetFlag(xy, 4, baseIten.allowWalk);
/* 1223:     */       }
/* 1224:1193 */       if (baseIten.interactorType == Interactor.InteractorType.walkeablechange)
/* 1225:     */       {
/* 1226:1194 */         GenericFloorItem floorItem = (GenericFloorItem)item;
/* 1227:1195 */         this.squareFlag.SetFlag(xy, 4, floorItem.getIntData() != 0);
/* 1228:     */       }
/* 1229:1198 */       if (baseIten.interactorType == Interactor.InteractorType.roller)
/* 1230:     */       {
/* 1231:1199 */         this.squareFlag.eventSetFlag(xy, 2, true);
/* 1232:     */       }
/* 1233:1200 */       else if (baseIten.interactorType == Interactor.InteractorType.normslaskates)
/* 1234:     */       {
/* 1235:1201 */         this.squareFlag.eventSetFlag(xy, 4, true);
/* 1236:     */       }
/* 1237:1202 */       else if (baseIten.interactorType == Interactor.InteractorType.banzaifloor)
/* 1238:     */       {
/* 1239:1203 */         this.squareFlag.eventSetFlag(xy, 8, true);
/* 1240:     */       }
/* 1241:1204 */       else if (baseIten.itemType == BaseItem.ItemType.ROOMGAME_GATE)
/* 1242:     */       {
/* 1243:1205 */         this.squareFlag.eventSetFlag(xy, 1, true);
/* 1244:1206 */         this.squareFlag.SetFlag(xy, 4, false);
/* 1245:     */       }
/* 1246:1207 */       else if (baseIten.interactorType == Interactor.InteractorType.banzaipuck)
/* 1247:     */       {
/* 1248:1208 */         this.squareFlag.eventSetFlag(xy, 16, true);
/* 1249:     */       }
/* 1250:1209 */       else if (baseIten.interactorType == Interactor.InteractorType.football)
/* 1251:     */       {
/* 1252:1210 */         this.squareFlag.eventSetFlag(xy, 32, true);
/* 1253:     */       }
/* 1254:1211 */       else if (baseIten.itemType == BaseItem.ItemType.FOOTBALL_GOAL)
/* 1255:     */       {
/* 1256:1212 */         this.squareFlag.eventSetFlag(xy, 64, true);
/* 1257:1213 */         this.squareFlag.SetFlag(xy, 4, true);
/* 1258:     */       }
/* 1259:1214 */       else if (baseIten.itemType == BaseItem.ItemType.WATER)
/* 1260:     */       {
/* 1261:1215 */         this.squareFlag.eventSetFlag(xy, 128, true);
/* 1262:     */       }
/* 1263:1216 */       else if (baseIten.logic == BaseItem.FurniLogic.FLOORHOLE)
/* 1264:     */       {
/* 1265:1217 */         this.squareFlag.SetFlag(xy, 4, false);
/* 1266:     */       }
/* 1267:     */     }
/* 1268:     */     else
/* 1269:     */     {
/* 1270:1220 */       if (baseIten.allowSit) {
/* 1271:1221 */         if (baseIten.interactorType == Interactor.InteractorType.bed) {
/* 1272:1222 */           this.squareFlag.SetFlag(xy, 1, false);
/* 1273:     */         } else {
/* 1274:1224 */           this.squareFlag.SetFlag(xy, 2, false);
/* 1275:     */         }
/* 1276:     */       }
/* 1277:1228 */       if (topItem == null)
/* 1278:     */       {
/* 1279:1229 */         this.squareFlag.SetFlag(xy, 8, false);
/* 1280:1230 */         this.squareFlag.SetFlag(xy, 4, true);
/* 1281:     */       }
/* 1282:     */       else
/* 1283:     */       {
/* 1284:1232 */         this.squareFlag.SetFlag(xy, 8, topItem.baseItem.allowSit);
/* 1285:1234 */         if (topItem.baseItem.interactorType == Interactor.InteractorType.walkeablechange)
/* 1286:     */         {
/* 1287:1235 */           GenericFloorItem floorItem = (GenericFloorItem)topItem;
/* 1288:1236 */           this.squareFlag.SetFlag(xy, 4, floorItem.getIntData() != 0);
/* 1289:     */         }
/* 1290:     */         else
/* 1291:     */         {
/* 1292:1238 */           this.squareFlag.SetFlag(xy, 4, topItem.baseItem.allowWalk);
/* 1293:     */         }
/* 1294:     */       }
/* 1295:1242 */       if (baseIten.interactorType == Interactor.InteractorType.roller) {
/* 1296:1243 */         this.squareFlag.eventSetFlag(xy, 2, false);
/* 1297:1244 */       } else if (baseIten.interactorType == Interactor.InteractorType.normslaskates) {
/* 1298:1245 */         this.squareFlag.eventSetFlag(xy, 4, false);
/* 1299:1246 */       } else if (baseIten.interactorType == Interactor.InteractorType.banzaifloor) {
/* 1300:1247 */         this.squareFlag.eventSetFlag(xy, 8, false);
/* 1301:1248 */       } else if (baseIten.itemType == BaseItem.ItemType.ROOMGAME_GATE) {
/* 1302:1249 */         this.squareFlag.eventSetFlag(xy, 1, false);
/* 1303:1250 */       } else if (baseIten.interactorType == Interactor.InteractorType.banzaipuck) {
/* 1304:1251 */         this.squareFlag.eventSetFlag(xy, 16, false);
/* 1305:1252 */       } else if (baseIten.interactorType == Interactor.InteractorType.football) {
/* 1306:1253 */         this.squareFlag.eventSetFlag(xy, 32, false);
/* 1307:1254 */       } else if (baseIten.itemType == BaseItem.ItemType.FOOTBALL_GOAL) {
/* 1308:1255 */         this.squareFlag.eventSetFlag(xy, 64, false);
/* 1309:1256 */       } else if (baseIten.itemType == BaseItem.ItemType.WATER) {
/* 1310:1257 */         this.squareFlag.eventSetFlag(xy, 128, false);
/* 1311:     */       }
/* 1312:     */     }
/* 1313:     */   }
/* 1314:     */   
/* 1315:     */   public void floorItemUpdateNeeded(FloorItem florItem)
/* 1316:     */   {
/* 1317:1263 */     synchronized (this.UpdateFloorItems)
/* 1318:     */     {
/* 1319:1264 */       this.UpdateFloorItems.add(florItem);
/* 1320:     */     }
/* 1321:1266 */     florItem.setMysqlState(1);
/* 1322:     */   }
/* 1323:     */   
/* 1324:     */   public void wallItemUpdateNeeded(WallItem wallItem)
/* 1325:     */   {
/* 1326:1270 */     synchronized (this.UpdateWallItems)
/* 1327:     */     {
/* 1328:1271 */       this.UpdateWallItems.add(wallItem);
/* 1329:     */     }
/* 1330:1273 */     wallItem.setMysqlState(1);
/* 1331:     */   }
/* 1332:     */   
/* 1333:     */   private void parseEvent(Event evt)
/* 1334:     */   {
/* 1335:1277 */     if (evt.Ticks-- > 0) {
/* 1336:1278 */       return;
/* 1337:     */     }
/* 1338:1281 */     long now = System.currentTimeMillis();
/* 1339:     */     try
/* 1340:     */     {
/* 1341:1283 */       evt.run(this);
/* 1342:     */     }
/* 1343:     */     catch (Exception ex)
/* 1344:     */     {
/* 1345:1285 */       Log.printException("Room-Events", ex);
/* 1346:     */     }
/* 1347:1287 */     long delay = System.currentTimeMillis() - now;
/* 1348:1288 */     if (delay > 10L) {
/* 1349:1289 */       Log.printLog("RoomEvent slow = " + delay + " | " + evt.toString());
/* 1350:     */     }
/* 1351:1292 */     if (evt.Ticks < 0) {
/* 1352:1293 */       this.userEvents.remove(evt.eventId);
/* 1353:     */     }
/* 1354:     */   }
/* 1355:     */   
/* 1356:     */   public void removeFloorItem(FloorItem floorItem, int pickerId)
/* 1357:     */   {
/* 1358:1298 */     List<RoomFloorItemData.AffectedTile> PointList = floorItem.getAffectedTiles();
/* 1359:1299 */     for (RoomFloorItemData.AffectedTile Tile : PointList) {
/* 1360:1300 */       generateSquare(Tile.xy, floorItem, false, false);
/* 1361:     */     }
/* 1362:1303 */     if (floorItem.baseItem.interactorType == Interactor.InteractorType.teleport) {
/* 1363:1304 */       Teleports.delRoom(floorItem.itemId);
/* 1364:     */     }
/* 1365:1307 */     sendMessage(ObjectRemoveComposer.compose(floorItem, pickerId, 0));
/* 1366:     */     
/* 1367:1309 */     this.FloorItems.remove(Integer.valueOf(floorItem.itemId));
/* 1368:1311 */     if ((floorItem instanceof GenericFloorItem)) {
/* 1369:1312 */       floorItem.baseItem.interactor.OnPickUp(this, null, (GenericFloorItem)floorItem);
/* 1370:     */     }
/* 1371:1315 */     floorItem.itemPick(floorItem.getXy(), floorItem.getDir());
/* 1372:     */   }
/* 1373:     */   
/* 1374:     */   public void removeWallItem(WallItem wallItem, int pickerId)
/* 1375:     */   {
/* 1376:1319 */     if ((this.MoodlightData != null) && (this.MoodlightData.ItemId == wallItem.itemId)) {
/* 1377:1320 */       this.MoodlightData = null;
/* 1378:     */     }
/* 1379:1323 */     sendMessage(ItemRemoveComposer.compose(wallItem, pickerId));
/* 1380:     */     
/* 1381:1325 */     this.WallItems.remove(Integer.valueOf(wallItem.itemId));
/* 1382:1326 */     wallItem.cleanRoomData();
/* 1383:     */   }
/* 1384:     */   
/* 1385:     */   public void removePet(PetEntity pet)
/* 1386:     */   {
/* 1387:1330 */     this.petList.remove(Integer.valueOf(pet.petData.id));
/* 1388:1331 */     entityWalk(pet.xy, pet, false);
/* 1389:     */     
/* 1390:1333 */     this.PetCounter -= 1;
/* 1391:1334 */     pet.petData.petEntity = null;
/* 1392:1335 */     sendMessage(UserRemoveComposer.compose(pet.virtualId));
/* 1393:     */   }
/* 1394:     */   
/* 1395:     */   public void removeBot(RentalBotEntity bot)
/* 1396:     */   {
/* 1397:1339 */     this.rentalBotList.remove(Integer.valueOf(bot.botData.id));
/* 1398:1340 */     entityWalk(bot.xy, bot, false);
/* 1399:     */     
/* 1400:1342 */     this.PetCounter -= 1;
/* 1401:1343 */     bot.botData.botEntity = null;
/* 1402:1344 */     sendMessage(UserRemoveComposer.compose(bot.virtualId));
/* 1403:     */   }
/* 1404:     */   
/* 1405:     */   private void setupFloorItemXYZ(FloorItem Item, int newXY, Direction8 newRot, List<RoomFloorItemData.AffectedTile> Points)
/* 1406:     */   {
/* 1407:1348 */     float topZ = 0.0F;
/* 1408:1350 */     for (RoomFloorItemData.AffectedTile Tile : Points)
/* 1409:     */     {
/* 1410:1351 */       Float f = (Float)this.squareAbsoluteHeight.get(Integer.valueOf(Tile.xy));
/* 1411:1352 */       if ((f != null) && (topZ < f.floatValue())) {
/* 1412:1353 */         topZ = f.floatValue();
/* 1413:     */       }
/* 1414:     */     }
/* 1415:1357 */     Item.setPosition(newXY);
/* 1416:1358 */     Item.setPosition(topZ);
/* 1417:1359 */     Item.setDir(newRot);
/* 1418:     */   }
/* 1419:     */   
/* 1420:     */   public boolean setFloorItem(Connection User, FloorItem floorItem, int newX, int newY, Direction8 newRot, boolean newItem)
/* 1421:     */   {
/* 1422:1363 */     int newXY = newX + newY * this.model.widthX;
/* 1423:     */     
/* 1424:1365 */     List<RoomFloorItemData.AffectedTile> Points = floorItem.getAffectedTiles(newXY, newRot);
/* 1425:1367 */     if (!canPlace(floorItem, Points, newXY, newItem)) {
/* 1426:1368 */       return false;
/* 1427:     */     }
/* 1428:1371 */     if (newItem)
/* 1429:     */     {
/* 1430:1372 */       setupFloorItemXYZ(floorItem, newXY, newRot, Points);
/* 1431:1374 */       if (floorItem.baseItem.interactorType == Interactor.InteractorType.roller) {
/* 1432:1375 */         this.rollers.put(Integer.valueOf(floorItem.itemId), (RollerItem)floorItem);
/* 1433:     */       }
/* 1434:     */     }
/* 1435:1378 */     else if (!moveObject(floorItem, Points, newXY, newRot))
/* 1436:     */     {
/* 1437:1379 */       return false;
/* 1438:     */     }
/* 1439:1383 */     if (newItem) {
/* 1440:1384 */       floorItem.setMysqlState(2);
/* 1441:     */     } else {
/* 1442:1386 */       floorItem.setMysqlState(1);
/* 1443:     */     }
/* 1444:1389 */     floorItem.finishPlace(User, Points, newItem);
/* 1445:1391 */     if (newItem) {
/* 1446:1392 */       sendMessage(ObjectAddComposer.compose(floorItem));
/* 1447:     */     } else {
/* 1448:1394 */       sendMessage(ObjectUpdateComposer.compose(floorItem));
/* 1449:     */     }
/* 1450:1397 */     return true;
/* 1451:     */   }
/* 1452:     */   
/* 1453:     */   public boolean canPlace(FloorItem floorItem, List<RoomFloorItemData.AffectedTile> Points, int newXY, boolean newItem)
/* 1454:     */   {
/* 1455:1401 */     for (RoomFloorItemData.AffectedTile Tile : Points)
/* 1456:     */     {
/* 1457:1402 */       if (!validTile(Tile.xy)) {
/* 1458:1403 */         return false;
/* 1459:     */       }
/* 1460:1406 */       if ((Tile.x == this.model.doorX) && 
/* 1461:1407 */         (Tile.y == this.model.doorY)) {
/* 1462:1408 */         return false;
/* 1463:     */       }
/* 1464:1412 */       float maxAbsoluteHeight = 49.0F;
/* 1465:1413 */       float maxRelativeHeight = this.model.getSquare(Tile.xy).height + 32.0F;
/* 1466:     */       
/* 1467:1415 */       Float f = (Float)this.squareAbsoluteHeight.get(Integer.valueOf(Tile.xy));
/* 1468:1416 */       if ((f != null) && (
/* 1469:1417 */         (f.floatValue() > maxRelativeHeight) || (f.floatValue() > maxAbsoluteHeight))) {
/* 1470:1418 */         return false;
/* 1471:     */       }
/* 1472:1422 */       FloorItem top = (FloorItem)this.topFloorItems.get(Integer.valueOf(Tile.xy));
/* 1473:1423 */       if ((top != null) && (top.itemId != floorItem.itemId) && 
/* 1474:1424 */         (!top.baseItem.AllowStack)) {
/* 1475:1425 */         return false;
/* 1476:     */       }
/* 1477:1429 */       if ((!floorItem.baseItem.allowWalk) && 
/* 1478:1430 */         (squareHasUsers(Tile.xy))) {
/* 1479:1431 */         return false;
/* 1480:     */       }
/* 1481:1435 */       if (this.squareFlag.eventHave(Tile.xy, 2))
/* 1482:     */       {
/* 1483:1436 */         if ((floorItem.baseItem.xDim != 1) || (floorItem.baseItem.yDim != 1)) {
/* 1484:1438 */           return false;
/* 1485:     */         }
/* 1486:1442 */         if ((!newItem) && (floorItem.getXy() != newXY) && 
/* 1487:1443 */           (floorItem.baseItem.interactorType == Interactor.InteractorType.roller)) {
/* 1488:1445 */           return false;
/* 1489:     */         }
/* 1490:     */       }
/* 1491:     */     }
/* 1492:1451 */     return true;
/* 1493:     */   }
/* 1494:     */   
/* 1495:     */   public boolean moveObject(FloorItem floorItem, List<RoomFloorItemData.AffectedTile> Points, int newXY, Direction8 newRot)
/* 1496:     */   {
/* 1497:1455 */     int xy = floorItem.getXy();
/* 1498:1456 */     Direction8 dir = floorItem.getDir();
/* 1499:1458 */     if ((xy == newXY) && (dir == newRot)) {
/* 1500:1459 */       return false;
/* 1501:     */     }
/* 1502:1462 */     List<RoomFloorItemData.AffectedTile> oldTiles = floorItem.getAffectedTiles();
/* 1503:1465 */     for (RoomFloorItemData.AffectedTile Tile : oldTiles) {
/* 1504:1466 */       if (validTile(Tile.xy)) {
/* 1505:1467 */         generateSquare(Tile.xy, floorItem, false, false);
/* 1506:     */       }
/* 1507:     */     }
/* 1508:1471 */     setupFloorItemXYZ(floorItem, newXY, newRot, Points);
/* 1509:1473 */     if ((floorItem instanceof WiredItemBase)) {
/* 1510:1474 */       this.wiredManager.moveWired((WiredItemBase)floorItem, floorItem.baseItem.itemType, xy);
/* 1511:     */     }
/* 1512:1477 */     floorItem.itemMoved(xy, dir);
/* 1513:     */     
/* 1514:1479 */     return true;
/* 1515:     */   }
/* 1516:     */   
/* 1517:     */   public void moveObject2(FloorItem floorItem, List<RoomFloorItemData.AffectedTile> Points, int newXY, Direction8 newRot)
/* 1518:     */   {
/* 1519:1483 */     int xy = floorItem.getXy();
/* 1520:1484 */     Direction8 dir = floorItem.getDir();
/* 1521:     */     
/* 1522:1486 */     setupFloorItemXYZ(floorItem, newXY, newRot, Points);
/* 1523:1488 */     if ((floorItem instanceof WiredItemBase)) {
/* 1524:1489 */       this.wiredManager.moveWired((WiredItemBase)floorItem, floorItem.baseItem.itemType, xy);
/* 1525:     */     }
/* 1526:1492 */     floorItem.itemMoved(xy, dir);
/* 1527:     */   }
/* 1528:     */   
/* 1529:     */   public boolean setWallItem(Connection User, GenericWallItem wallItem, boolean newItem)
/* 1530:     */   {
/* 1531:1496 */     if ((wallItem.baseItem.logic == BaseItem.FurniLogic.ROOMDIMMER) && 
/* 1532:1497 */       (this.MoodlightData == null))
/* 1533:     */     {
/* 1534:1498 */       this.MoodlightData = new MoodlightData();
/* 1535:1499 */       this.MoodlightData.Enabled = true;
/* 1536:1500 */       this.MoodlightData.CurrentPreset = 1;
/* 1537:1501 */       this.MoodlightData.AddPresent("#000000,255,0");
/* 1538:1502 */       this.MoodlightData.AddPresent("#000000,255,0");
/* 1539:1503 */       this.MoodlightData.AddPresent("#000000,255,0");
/* 1540:1504 */       this.MoodlightData.ItemId = wallItem.itemId;
/* 1541:1505 */       wallItem.extraData.setExtraData(this.MoodlightData.GenerateExtraData());
/* 1542:     */     }
/* 1543:1509 */     this.WallItems.put(Integer.valueOf(wallItem.itemId), wallItem);
/* 1544:     */     
/* 1545:1511 */     sendMessage(ItemAddComposer.compose(wallItem));
/* 1546:1513 */     if (newItem) {
/* 1547:1514 */       wallItem.setMysqlState(2);
/* 1548:     */     } else {
/* 1549:1516 */       wallItem.setMysqlState(1);
/* 1550:     */     }
/* 1551:1519 */     return true;
/* 1552:     */   }
/* 1553:     */   
/* 1554:     */   private void updateRoom()
/* 1555:     */   {
/* 1556:1523 */     String roomTags = "";
/* 1557:1524 */     for (String tag : this.roomData.tags)
/* 1558:     */     {
/* 1559:1525 */       if (!roomTags.isEmpty()) {
/* 1560:1526 */         roomTags = roomTags.concat(",");
/* 1561:     */       }
/* 1562:1528 */       roomTags = roomTags.concat(tag);
/* 1563:     */     }
/* 1564:1532 */     String iconItems = "";
/* 1565:1533 */     int j = 0;
/* 1566:1534 */     for (String item : this.roomData.icon.items)
/* 1567:     */     {
/* 1568:1535 */       if (j > 0) {
/* 1569:1536 */         iconItems = iconItems.concat("|");
/* 1570:     */       }
/* 1571:1538 */       iconItems = iconItems.concat(item);
/* 1572:1539 */       j++;
/* 1573:     */     }
/* 1574:     */     try
/* 1575:     */     {
/* 1576:1543 */       Database.exec(
/* 1577:     */       
/* 1578:     */ 
/* 1579:     */ 
/* 1580:     */ 
/* 1581:     */ 
/* 1582:     */ 
/* 1583:     */ 
/* 1584:     */ 
/* 1585:     */ 
/* 1586:     */ 
/* 1587:     */ 
/* 1588:     */ 
/* 1589:     */ 
/* 1590:     */ 
/* 1591:     */ 
/* 1592:     */ 
/* 1593:     */ 
/* 1594:     */ 
/* 1595:     */ 
/* 1596:     */ 
/* 1597:     */ 
/* 1598:     */ 
/* 1599:     */ 
/* 1600:     */ 
/* 1601:     */ 
/* 1602:     */ 
/* 1603:1570 */         "UPDATE `rooms` SET `model_name`='" + this.model.modelName + "'," + "`caption`=?," + "`user_id`='" + this.roomData.roomOwner.userId + "'," + "`user_name`=?," + "`description`=?," + "`category`='" + this.roomData.category + "'," + "`state`='" + this.roomData.state + "'," + "`users_max`='" + this.roomData.usersMax + "'," + "`score`='" + this.roomData.rating + "'," + "`tags`=?," + "`icon_bg`='" + this.roomData.icon.backgroundImage + "'," + "`icon_fg`='" + this.roomData.icon.foregroundImage + "'," + "`password`=?," + "`wallpaper`='" + this.roomData.Wallpaper + "'," + "`floor`='" + this.roomData.Floor + "'," + "`landscape`='" + this.roomData.Landscape + "'," + "`allow_pets`='" + (this.roomData.haveFlag(2) ? 1 : 0) + "'," + "`allow_pets_eat`='" + (this.roomData.haveFlag(4) ? 1 : 0) + "'," + "`allow_walkthrough`='" + (this.roomData.haveFlag(8) ? 1 : 0) + "'," + "`allow_hidewall`='" + (this.roomData.haveFlag(16) ? 1 : 0) + "'," + "`wallthickness`='" + this.roomData.wallAnchor + "'," + "`floorthickness`='" + this.roomData.floorAnchor + "'," + "`staff_pickup`='" + (this.roomData.haveFlag(32) ? 1 : 0) + "'," + "`settings_mod`='" + this.roomData.modPermissions.getIntValue() + "'," + "`settings_trd`='" + this.roomData.tradingSettings.getIntValue() + "'," + "`settings_chat`='" + this.roomData.chatSettings.getIntValue() + "'" + " WHERE `id`='" + this.roomId + "';", new Object[] {
/* 1604:1571 */         this.roomData.name, 
/* 1605:1572 */         this.roomData.roomOwner.userName, 
/* 1606:1573 */         this.roomData.description, 
/* 1607:1574 */         roomTags, 
/* 1608:1575 */         this.roomData.password });
/* 1609:     */     }
/* 1610:     */     catch (Exception ex)
/* 1611:     */     {
/* 1612:1578 */       Log.printException("Room-4", ex);
/* 1613:     */     }
/* 1614:     */   }
/* 1615:     */   
/* 1616:     */   public void updateMysqlData()
/* 1617:     */   {
/* 1618:1583 */     if (!this.roomData.haveFlag(1))
/* 1619:     */     {
/* 1620:1584 */       if ((this.model instanceof CustomGameMap))
/* 1621:     */       {
/* 1622:1585 */         CustomGameMap customModel = (CustomGameMap)this.model;
/* 1623:1586 */         if (customModel.mysqlAction == 1) {
/* 1624:     */           try
/* 1625:     */           {
/* 1626:1588 */             String heightMap = customModel.buildHeightMap();
/* 1627:1589 */             Database.exec("INSERT INTO `room_custom_models` (`id`,`base`,`heightmap`) VALUES(?,?,?) ;", new Object[] { customModel.modelName, customModel.baseName, heightMap });
/* 1628:     */           }
/* 1629:     */           catch (Exception ex)
/* 1630:     */           {
/* 1631:1591 */             Log.printException("Room-4", ex);
/* 1632:     */           }
/* 1633:1593 */         } else if (customModel.mysqlAction == 2) {
/* 1634:     */           try
/* 1635:     */           {
/* 1636:1595 */             String heightMap = customModel.buildHeightMap();
/* 1637:1596 */             Database.exec("UPDATE `room_custom_models` SET `heightmap`=? WHERE `id` = ?;", new Object[] { heightMap, customModel.modelName });
/* 1638:     */           }
/* 1639:     */           catch (Exception ex)
/* 1640:     */           {
/* 1641:1598 */             Log.printException("Room-4", ex);
/* 1642:     */           }
/* 1643:     */         }
/* 1644:1601 */         customModel.mysqlAction = 0;
/* 1645:     */       }
/* 1646:1605 */       updateRoom();
/* 1647:1607 */       for (FloorItem floorItem : this.FloorItems.values()) {
/* 1648:     */         try
/* 1649:     */         {
/* 1650:1609 */           if (floorItem.baseItem.interactor == Interactor.iterWired)
/* 1651:     */           {
/* 1652:1610 */             if ((floorItem instanceof WiredItemBase))
/* 1653:     */             {
/* 1654:1613 */               WiredItemBase wired = (WiredItemBase)floorItem;
/* 1655:     */               try
/* 1656:     */               {
/* 1657:1615 */                 wired.saveData();
/* 1658:     */               }
/* 1659:     */               catch (Exception ex)
/* 1660:     */               {
/* 1661:1617 */                 Log.printException("Room-updateMysqlData", ex);
/* 1662:     */               }
/* 1663:     */             }
/* 1664:     */           }
/* 1665:1620 */           else if ((floorItem.baseItem.logic == BaseItem.FurniLogic.ROOMDIMMER) && 
/* 1666:1621 */             (this.MoodlightData != null) && (this.MoodlightData.ItemId == floorItem.itemId)) {
/* 1667:1622 */             this.MoodlightData.mysqlSave();
/* 1668:     */           }
/* 1669:1626 */           floorItem.mysqlSave();
/* 1670:     */         }
/* 1671:     */         catch (Exception ex)
/* 1672:     */         {
/* 1673:1628 */           Log.printException("Room", ex);
/* 1674:     */         }
/* 1675:     */       }
/* 1676:1632 */       for (WallItem wallItem : this.WallItems.values()) {
/* 1677:     */         try
/* 1678:     */         {
/* 1679:1634 */           wallItem.mysqlSave();
/* 1680:     */         }
/* 1681:     */         catch (Exception ex)
/* 1682:     */         {
/* 1683:1636 */           Log.printException("Room", ex);
/* 1684:     */         }
/* 1685:     */       }
/* 1686:1640 */       for (PetEntity petEntity : this.petList.values())
/* 1687:     */       {
/* 1688:1641 */         Pet pet = petEntity.petData;
/* 1689:1643 */         if (pet.needInsert)
/* 1690:     */         {
/* 1691:1644 */           pet.needInsert = false;
/* 1692:     */           try
/* 1693:     */           {
/* 1694:1647 */             Database.exec("INSERT IGNORE INTO user_pets (id,room_id,x,y,z,type,name,race,color,createstamp,nutrition,expirience,energy,respect,user_id)VALUES(" + pet.id + "," + this.roomId + "," + petEntity.x + "," + petEntity.y + "," + petEntity.z + "," + pet.petType + ",?," + pet.base.raceId + ",?," + pet.TimeCreated + "," + pet.Nutrition + "," + pet.Experience + "," + pet.Energy + "," + pet.Respects + "," + pet.ownerId + ");", new Object[] { pet.name, pet.Color });
/* 1695:     */           }
/* 1696:     */           catch (Exception ex)
/* 1697:     */           {
/* 1698:1649 */             Log.printException("Room-updateMysqlData", ex);
/* 1699:     */           }
/* 1700:     */         }
/* 1701:     */         else
/* 1702:     */         {
/* 1703:     */           try
/* 1704:     */           {
/* 1705:1654 */             Database.exec("UPDATE user_pets SET `room_id`=" + this.roomId + ",`x`=" + petEntity.x + ",`y`=" + petEntity.y + ",`z`=" + petEntity.z + ",`nutrition`=" + pet.Nutrition + ",`expirience`=" + pet.Experience + ",`energy`=" + pet.Energy + ",`respect`=" + pet.Respects + " WHERE id=" + pet.id + ";", new Object[0]);
/* 1706:     */           }
/* 1707:     */           catch (Exception ex)
/* 1708:     */           {
/* 1709:1656 */             Log.printException("Room-updateMysqlData", ex);
/* 1710:     */           }
/* 1711:     */         }
/* 1712:     */       }
/* 1713:1661 */       for (RentalBotEntity botEntity : this.rentalBotList.values())
/* 1714:     */       {
/* 1715:1662 */         RentalBot bot = botEntity.botData;
/* 1716:1664 */         if (bot.needInsert)
/* 1717:     */         {
/* 1718:1665 */           bot.needInsert = false;
/* 1719:     */           try
/* 1720:     */           {
/* 1721:1668 */             Database.exec("INSERT IGNORE INTO user_bots (id,room_id,x,y,z,type,name,look,motto,gender,user_id)VALUES(" + bot.id + "," + this.roomId + "," + botEntity.x + "," + botEntity.y + "," + botEntity.z + "," + bot.botType + ",?,?,?,?," + bot.ownerId + ");", new Object[] { bot.name, bot.botLook.toString(), bot.motto, bot.gender });
/* 1722:     */           }
/* 1723:     */           catch (Exception ex)
/* 1724:     */           {
/* 1725:1670 */             Log.printException("Room-updateMysqlData", ex);
/* 1726:     */           }
/* 1727:     */         }
/* 1728:     */         else
/* 1729:     */         {
/* 1730:     */           try
/* 1731:     */           {
/* 1732:1675 */             Database.exec("UPDATE user_bots SET `room_id`=" + this.roomId + ",`x`=" + botEntity.x + ",`y`=" + botEntity.y + ",`z`=" + botEntity.z + ",`name`=?,`look`=?,`motto`=?,`gender`=? WHERE id=" + bot.id + ";", new Object[] { bot.name, bot.botLook.toString(), bot.motto, bot.gender });
/* 1733:     */           }
/* 1734:     */           catch (Exception ex)
/* 1735:     */           {
/* 1736:1677 */             Log.printException("Room-updateMysqlData", ex);
/* 1737:     */           }
/* 1738:     */         }
/* 1739:     */       }
/* 1740:1682 */       for (PlayerRight right : this.usersWithRights.values()) {
/* 1741:1683 */         if (right.needInsert)
/* 1742:     */         {
/* 1743:1684 */           right.needInsert = false;
/* 1744:     */           try
/* 1745:     */           {
/* 1746:1687 */             Database.exec("INSERT IGNORE INTO room_rights (room_id,user_id)VALUES(" + this.roomId + "," + right.player.userId + ");", new Object[0]);
/* 1747:     */           }
/* 1748:     */           catch (Exception ex)
/* 1749:     */           {
/* 1750:1689 */             Log.printException("Room-updateMysqlData", ex);
/* 1751:     */           }
/* 1752:     */         }
/* 1753:     */       }
/* 1754:     */     }
/* 1755:1695 */     Connection ownerConnection = this.roomData.roomOwner.connection;
/* 1756:1696 */     if (ownerConnection != null)
/* 1757:     */     {
/* 1758:1698 */       ownerConnection.saveItems();
/* 1759:1699 */       ownerConnection.saveObjects();
/* 1760:     */     }
/* 1761:     */   }
/* 1762:     */   
/* 1763:     */   public void removeUserFromRoom(Connection client, boolean NotifyClient, boolean NotifyKick)
/* 1764:     */   {
/* 1765:1705 */     removeUserFromRoom(client, client.playerData, NotifyClient, NotifyKick);
/* 1766:     */   }
/* 1767:     */   
/* 1768:     */   public void removeUserFromRoom(Connection cn, PlayerData player, boolean NotifyClient, boolean NotifyKick)
/* 1769:     */   {
/* 1770:1709 */     if (NotifyClient)
/* 1771:     */     {
/* 1772:1710 */       if (NotifyKick) {
/* 1773:1711 */         QueueWriter.writeAndFlush(cn.socket, GenericErrorComposer.compose(4008));
/* 1774:     */       }
/* 1775:1713 */       QueueWriter.writeAndFlush(cn.socket, CloseConnectionComposer.compose());
/* 1776:     */     }
/* 1777:1715 */     QueueWriter.writeAndFlush(cn.socket, YouArePlayingGameComposer.compose(false));
/* 1778:     */     
/* 1779:1717 */     Avatar avatar = cn.avatar;
/* 1780:1718 */     if (avatar == null) {
/* 1781:1719 */       return;
/* 1782:     */     }
/* 1783:1722 */     avatar.evtChat.stop(this);
/* 1784:1723 */     avatar.clearMovement();
/* 1785:1724 */     entityWalk(avatar.xy, avatar, false);
/* 1786:1725 */     sendMessage(UserRemoveComposer.compose(avatar.virtualId));
/* 1787:1727 */     if (this.userList.remove(Integer.valueOf(avatar.id)) == null)
/* 1788:     */     {
/* 1789:1728 */       cn.avatar = null;
/* 1790:1729 */       return;
/* 1791:     */     }
/* 1792:1732 */     this.userCount -= 1;
/* 1793:     */     
/* 1794:     */ 
/* 1795:1735 */     Trade trade = (Trade)Trade.tradeMap.get(Integer.valueOf(player.userId));
/* 1796:1736 */     if (trade != null)
/* 1797:     */     {
/* 1798:1737 */       trade.clean();
/* 1799:1738 */       trade.broadcast(TradingCloseComposer.compose(trade.ownerUser.userId, 0));
/* 1800:     */     }
/* 1801:1741 */     cn.avatar = null;
/* 1802:1743 */     for (MessengerFriend friend : player.messenger.getFriends())
/* 1803:     */     {
/* 1804:1744 */       PlayerData friendPlayer = Clients.getPlayerData(friend.userId);
/* 1805:1745 */       if (friendPlayer != null)
/* 1806:     */       {
/* 1807:1746 */         PlayerMessenger messenger = friendPlayer.messenger;
/* 1808:1747 */         if (messenger.isOnline) {
/* 1809:1748 */           messenger.update(new MessengerFriendUpdate(player.userId, 0));
/* 1810:     */         }
/* 1811:     */       }
/* 1812:     */     }
/* 1813:1753 */     if (player.userId == this.roomData.roomOwner.userId)
/* 1814:     */     {
/* 1815:1754 */       if (this.roomData.event != null)
/* 1816:     */       {
/* 1817:1755 */         this.roomData.event = null;
/* 1818:1756 */         sendMessage(EventComposer.compose());
/* 1819:     */       }
/* 1820:     */     }
/* 1821:     */     else {
/* 1822:1759 */       for (PetEntity pet : this.petList.values())
/* 1823:     */       {
/* 1824:1760 */         pet.OnUserLeaveRoom(this, cn);
/* 1825:1762 */         if ((pet.entityType == 2) && (pet.petData.ownerId == player.userId))
/* 1826:     */         {
/* 1827:1763 */           cn.inventory.addPet(pet.petData.id, pet.petData);
/* 1828:1764 */           QueueWriter.writeAndFlush(cn.socket, AddPetToInventoryComposer.compose(pet.petData));
/* 1829:1765 */           removePet(pet);
/* 1830:     */         }
/* 1831:     */       }
/* 1832:     */     }
/* 1833:1770 */     RoomListing.updatePopularRooms(this);
/* 1834:     */   }
/* 1835:     */   
/* 1836:     */   public void loadRoom(Connection cn, String Password)
/* 1837:     */   {
/* 1838:1775 */     if (this.usersWithRights == null)
/* 1839:     */     {
/* 1840:1776 */       this.usersWithRights = new ConcurrentHashMap();
/* 1841:     */       
/* 1842:1778 */       DBResult result = new DBResult();
/* 1843:     */       try
/* 1844:     */       {
/* 1845:1780 */         Database.query(result, "SELECT user_id FROM room_rights WHERE room_id = " + this.roomData.roomId + " LIMIT 1;", new Object[0]);
/* 1846:1781 */         while (result.data.next())
/* 1847:     */         {
/* 1848:1782 */           PlayerData player = Clients.getPlayerData(result.data.getInt("user_id"));
/* 1849:1783 */           if (player != null) {
/* 1850:1787 */             this.usersWithRights.put(Integer.valueOf(player.userId), new PlayerRight(player));
/* 1851:     */           }
/* 1852:     */         }
/* 1853:     */       }
/* 1854:     */       catch (Exception ex)
/* 1855:     */       {
/* 1856:1790 */         Log.printException("loadRoom", ex);
/* 1857:     */       }
/* 1858:1792 */       result.close();
/* 1859:     */     }
/* 1860:1795 */     if (this.usersBanned == null) {
/* 1861:1796 */       this.usersBanned = new ConcurrentHashMap();
/* 1862:     */     }
/* 1863:1801 */     int controllerLevel = ControllerLevels.getLevel(cn.playerData, this.roomData, this);
/* 1864:1802 */     if (controllerLevel < 4)
/* 1865:     */     {
/* 1866:1803 */       if (this.userCount >= this.roomData.usersMax)
/* 1867:     */       {
/* 1868:1804 */         QueueWriter.writeAndFlush(cn.socket, FlatAccessDeniedComposer.compose(1, ""));
/* 1869:1805 */         QueueWriter.writeAndFlush(cn.socket, CloseConnectionComposer.compose());
/* 1870:1806 */         return;
/* 1871:     */       }
/* 1872:1809 */       if ((userIsBanned(cn.playerData.userId)) && 
/* 1873:1810 */         (!hasBanExpired(cn.playerData.userId)))
/* 1874:     */       {
/* 1875:1811 */         QueueWriter.writeAndFlush(cn.socket, FlatAccessDeniedComposer.compose(4, ""));
/* 1876:1812 */         QueueWriter.writeAndFlush(cn.socket, CloseConnectionComposer.compose());
/* 1877:1813 */         return;
/* 1878:     */       }
/* 1879:1817 */       if ((cn.teleport == null) || (cn.teleport.roomId != this.roomData.roomId))
/* 1880:     */       {
/* 1881:1818 */         if (this.roomData.state == 1)
/* 1882:     */         {
/* 1883:1819 */           if (this.userCount == 0)
/* 1884:     */           {
/* 1885:1820 */             QueueWriter.writeAndFlush(cn.socket, DoorBellNoAnswerComposer.compose());
/* 1886:     */           }
/* 1887:     */           else
/* 1888:     */           {
/* 1889:1822 */             QueueWriter.writeAndFlush(cn.socket, DoorbellUserComposer.compose(""));
/* 1890:1823 */             sendMessage(DoorbellUserComposer.compose(cn.playerData.userName), new int[] { 1, 4, 5 });
/* 1891:     */           }
/* 1892:1825 */           return;
/* 1893:     */         }
/* 1894:1828 */         if ((this.roomData.state == 2) && 
/* 1895:1829 */           (!Password.equals(this.roomData.password)))
/* 1896:     */         {
/* 1897:1830 */           QueueWriter.writeAndFlush(cn.socket, GenericErrorComposer.compose(-100002));
/* 1898:1831 */           QueueWriter.writeAndFlush(cn.socket, CloseConnectionComposer.compose());
/* 1899:1832 */           return;
/* 1900:     */         }
/* 1901:     */       }
/* 1902:     */     }
/* 1903:1838 */     startLoadingRoom(cn);
/* 1904:     */   }
/* 1905:     */   
/* 1906:     */   public void startLoadingRoom(Connection cn)
/* 1907:     */   {
/* 1908:1843 */     QueueWriter.writeAndFlush(cn.socket, OpenConnectionComposer.compose());
/* 1909:     */     
/* 1910:     */ 
/* 1911:1846 */     QueueWriter.writeAndFlush(cn.socket, RoomReadyComposer.compose(this.model.modelName, this.roomData.roomId));
/* 1912:1848 */     if (!this.roomData.Floor.equals("0.0")) {
/* 1913:1849 */       QueueWriter.writeAndFlush(cn.socket, RoomPropertyComposer.compose("floor", this.roomData.Floor));
/* 1914:     */     }
/* 1915:1852 */     if (!this.roomData.Wallpaper.equals("0.0")) {
/* 1916:1853 */       QueueWriter.writeAndFlush(cn.socket, RoomPropertyComposer.compose("wallpaper", this.roomData.Wallpaper));
/* 1917:     */     }
/* 1918:1856 */     QueueWriter.writeAndFlush(cn.socket, RoomPropertyComposer.compose("landscape", this.roomData.Landscape));
/* 1919:     */     
/* 1920:1858 */     int controllerLevel = ControllerLevels.getLevel(cn.playerData, this.roomData, this);
/* 1921:1859 */     if (controllerLevel != 0)
/* 1922:     */     {
/* 1923:1860 */       QueueWriter.writeAndFlush(cn.socket, YouAreControllerComposer.compose(controllerLevel));
/* 1924:1861 */       if (cn.playerData.userId == this.roomData.roomOwner.userId) {
/* 1925:1862 */         QueueWriter.writeAndFlush(cn.socket, YouAreOwnerComposer.compose());
/* 1926:     */       }
/* 1927:     */     }
/* 1928:     */     else
/* 1929:     */     {
/* 1930:1865 */       QueueWriter.writeAndFlush(cn.socket, YouAreNotControllerComposer.compose());
/* 1931:     */     }
/* 1932:1868 */     QueueWriter.writeAndFlush(cn.socket, RoomRatingComposer.compose(this.roomData.rating, (!cn.avatarData.ratedRooms.contains(Integer.valueOf(this.roomData.roomId))) && (cn.playerData.userId != this.roomData.roomOwner.userId)));
/* 1933:     */     
/* 1934:1870 */     cn.avatarData.LoadingRoom = this.roomData.roomId;
/* 1935:     */   }
/* 1936:     */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.threadpools.RoomTask
 * JD-Core Version:    0.7.0.1
 */