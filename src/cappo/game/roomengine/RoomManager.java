/*   1:    */ package cappo.game.roomengine;
/*   2:    */ 
/*   3:    */ import cappo.engine.Server;
/*   4:    */ import cappo.engine.database.DBResult;
/*   5:    */ import cappo.engine.database.Database;
/*   6:    */ import cappo.engine.logging.Log;
/*   7:    */ import cappo.engine.player.Connection;
/*   8:    */ import cappo.game.collections.Utils;
/*   9:    */ import cappo.game.games.snowwar.Direction8;
/*  10:    */ import cappo.game.player.PlayerData;
/*  11:    */ import cappo.game.roomengine.gamemap.CustomGameMap;
/*  12:    */ import cappo.game.roomengine.gamemap.GameMapBase;
/*  13:    */ import cappo.game.roomengine.roomlisting.RoomListing;
/*  14:    */ import cappo.game.roomengine.settings.ChatSettings;
/*  15:    */ import cappo.game.roomengine.settings.ModerationPermissions;
/*  16:    */ import cappo.game.roomengine.settings.TradingSettings;
/*  17:    */ import java.sql.ResultSet;
/*  18:    */ import java.util.Map;
/*  19:    */ import java.util.concurrent.ConcurrentHashMap;
/*  20:    */ 
/*  21:    */ public class RoomManager
/*  22:    */ {
/*  23:    */   private static int ActiveRooms;
/*  24: 31 */   private static Map<String, GameMapBase> models = new ConcurrentHashMap(50);
/*  25: 32 */   private static Map<String, Integer> PopularTags = new ConcurrentHashMap(50);
/*  26: 33 */   private static Map<Integer, RoomData> rooms = new ConcurrentHashMap(500);
/*  27:    */   
/*  28:    */   public static void Init(DBResult result)
/*  29:    */     throws Exception
/*  30:    */   {}
/*  31:    */   
/*  32:    */   public static GameMapBase getModel(String key)
/*  33:    */   {
/*  34: 40 */     GameMapBase model = (GameMapBase)models.get(key);
/*  35: 41 */     if (model == null)
/*  36:    */     {
/*  37: 42 */       DBResult result = new DBResult();
/*  38: 43 */       model = loadModel(key, result, key.startsWith("custom_"));
/*  39: 44 */       result.close();
/*  40:    */     }
/*  41: 46 */     return model;
/*  42:    */   }
/*  43:    */   
/*  44:    */   private static GameMapBase loadModel(String key, DBResult result, boolean custom)
/*  45:    */   {
/*  46: 50 */     model = null;
/*  47:    */     try
/*  48:    */     {
/*  49: 53 */       if (custom) {
/*  50: 54 */         Database.query(result, "SELECT heightmap,base FROM room_custom_models WHERE id = ?;", new Object[] { key });
/*  51:    */       } else {
/*  52: 56 */         Database.query(result, "SELECT door_x,door_y,door_z,door_dir,heightmap,club_only FROM room_models WHERE id = ?;", new Object[] { key });
/*  53:    */       }
/*  54: 59 */       if (result.data.next())
/*  55:    */       {
/*  56: 60 */         if (custom)
/*  57:    */         {
/*  58: 61 */           GameMapBase base = getModel(result.data.getString("base"));
/*  59:    */           
/*  60: 63 */           model = new CustomGameMap(key, base.doorX, 
/*  61: 64 */             base.doorY, base.doorZ, 
/*  62: 65 */             base.DoorOrientation, false);
/*  63:    */           
/*  64: 67 */           CustomGameMap tmp = (CustomGameMap)model;
/*  65: 68 */           tmp.baseName = base.modelName;
/*  66:    */         }
/*  67:    */         else
/*  68:    */         {
/*  69: 70 */           model = new GameMapBase(key, 
/*  70: 71 */             result.data.getInt("door_x"), 
/*  71: 72 */             result.data.getInt("door_y"), 
/*  72: 73 */             result.data.getFloat("door_z"), 
/*  73: 74 */             Direction8.getDirection(result.data.getInt("door_dir")), 
/*  74: 75 */             result.data.getInt("club_only") != 0);
/*  75:    */         }
/*  76:    */         try
/*  77:    */         {
/*  78: 80 */           String map = result.data.getString("heightmap");
/*  79: 81 */           map = map.replace('\r', '\n');
/*  80: 82 */           map = map.replace("\n\n", "\n");
/*  81: 83 */           model.generateModel(map.split("\n"));
/*  82: 85 */           if (!(model instanceof CustomGameMap)) {
/*  83: 86 */             models.put(model.modelName, model);
/*  84:    */           }
/*  85:    */         }
/*  86:    */         catch (Exception ex)
/*  87:    */         {
/*  88: 89 */           model = null;
/*  89: 90 */           Log.printException("RoomManager", ex);
/*  90:    */         }
/*  91:    */       }
/*  92: 99 */       return model;
/*  93:    */     }
/*  94:    */     catch (Exception ex)
/*  95:    */     {
/*  96: 95 */       Log.printException("", ex);
/*  97:    */     }
/*  98:    */   }
/*  99:    */   
/* 100:    */   public static void AddTag(String Tag)
/* 101:    */   {
/* 102:103 */     int Count = 1;
/* 103:104 */     if (PopularTags.containsKey(Tag)) {
/* 104:105 */       Count += ((Integer)PopularTags.get(Tag)).intValue();
/* 105:    */     }
/* 106:107 */     PopularTags.put(Tag, Integer.valueOf(Count));
/* 107:    */   }
/* 108:    */   
/* 109:    */   public static RoomData createRoom(Connection user, String roomName, String modelName)
/* 110:    */   {
/* 111:111 */     GameMapBase model = getModel(modelName);
/* 112:112 */     if (model == null)
/* 113:    */     {
/* 114:113 */       Utils.AlertFromHotel(user.socket, cappo.game.utils.lang.LangTexts.texts[3]);
/* 115:114 */       return null;
/* 116:    */     }
/* 117:117 */     if (roomName.length() < 3)
/* 118:    */     {
/* 119:118 */       Utils.AlertFromHotel(user.socket, cappo.game.utils.lang.LangTexts.texts[4]);
/* 120:119 */       return null;
/* 121:    */     }
/* 122:122 */     RoomData roomData = new RoomData(Server.generateRoomId(), 25);
/* 123:123 */     roomData.name = roomName;
/* 124:124 */     roomData.description = "";
/* 125:125 */     roomData.roomOwner = user.playerData;
/* 126:126 */     roomData.roomOwnerId = user.playerData.userId;
/* 127:127 */     roomData.roomOwnerName = user.playerData.userName;
/* 128:128 */     roomData.model = modelName;
/* 129:129 */     roomData.icon = new RoomIcon(1, 0, new String[0]);
/* 130:130 */     roomData.password = "";
/* 131:131 */     roomData.Wallpaper = "0.0";
/* 132:132 */     roomData.Floor = "0.0";
/* 133:133 */     roomData.Landscape = "0.0";
/* 134:134 */     roomData.tags = new String[0];
/* 135:    */     
/* 136:136 */     roomData.setFlag(2, true);
/* 137:137 */     roomData.setFlag(8, true);
/* 138:    */     
/* 139:139 */     roomData.modPermissions = new ModerationPermissions(0);
/* 140:140 */     roomData.tradingSettings = new TradingSettings(2);
/* 141:141 */     roomData.chatSettings = new ChatSettings(0);
/* 142:    */     
/* 143:143 */     roomData.lastUsedThis = Utils.getTimestamp();
/* 144:144 */     rooms.put(Integer.valueOf(roomData.roomId), roomData);
/* 145:    */     
/* 146:146 */     user.ownRooms.put(Integer.valueOf(roomData.roomId), roomData);
/* 147:    */     try
/* 148:    */     {
/* 149:149 */       Database.exec("INSERT INTO `rooms` (`id`,`model_name`,`caption`,`user_id`,`user_name`,`description`,`category`,`score`,`tags`,`icon_bg`,`icon_fg`,`icon_items`,`password`,`wallpaper`,`floor`,`landscape`,`allow_pets`,`allow_pets_eat`,`allow_walkthrough`,`allow_hidewall`,`wallthickness`,`floorthickness`,`staff_pickup`,`public_ccts`)VALUES(" + roomData.roomId + ",?,?," + roomData.roomOwner.userId + ",?,?," + roomData.category + "," + roomData.rating + ",''," + roomData.icon.backgroundImage + "," + roomData.icon.foregroundImage + ",'',?,?,?,?,'" + (roomData.haveFlag(2) ? 1 : 0) + "','" + (roomData.haveFlag(4) ? 1 : 0) + "','" + (roomData.haveFlag(8) ? 1 : 0) + "','" + (roomData.haveFlag(16) ? 1 : 0) + "'," + roomData.wallAnchor + "," + roomData.floorAnchor + ",'" + (roomData.haveFlag(32) ? 1 : 0) + "','');", new Object[] { model.modelName, roomData.name, roomData.roomOwner.userName, roomData.description, roomData.password, roomData.Wallpaper, roomData.Floor, roomData.Landscape });
/* 150:    */     }
/* 151:    */     catch (Exception ex)
/* 152:    */     {
/* 153:151 */       Log.printException("Room-3", ex);
/* 154:    */     }
/* 155:154 */     return roomData;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public static int GetActiveCount()
/* 159:    */   {
/* 160:158 */     return ActiveRooms;
/* 161:    */   }
/* 162:    */   
/* 163:    */   public static int GetLoadedCount()
/* 164:    */   {
/* 165:162 */     return rooms.size();
/* 166:    */   }
/* 167:    */   
/* 168:    */   public static RoomData getRoom(int RoomId)
/* 169:    */   {
/* 170:166 */     if (RoomId < 1) {
/* 171:167 */       return null;
/* 172:    */     }
/* 173:169 */     RoomData room = (RoomData)rooms.get(Integer.valueOf(RoomId));
/* 174:170 */     if (room != null) {
/* 175:171 */       room.lastUsedThis = Utils.getTimestamp();
/* 176:    */     }
/* 177:173 */     return room;
/* 178:    */   }
/* 179:    */   
/* 180:    */   public static Map<Integer, RoomData> GetRooms()
/* 181:    */   {
/* 182:177 */     return rooms;
/* 183:    */   }
/* 184:    */   
/* 185:    */   public static Map<String, Integer> GetTags()
/* 186:    */   {
/* 187:181 */     return PopularTags;
/* 188:    */   }
/* 189:    */   
/* 190:    */   public static RoomData loadRoomResultSet(ResultSet roomData)
/* 191:    */     throws Exception
/* 192:    */   {
/* 193:185 */     RoomData room = new RoomData(roomData.getInt("id"), roomData.getInt("users_max"));
/* 194:186 */     room.name = roomData.getString("caption");
/* 195:187 */     room.description = roomData.getString("description");
/* 196:188 */     room.roomOwnerId = roomData.getInt("user_id");
/* 197:189 */     room.roomOwnerName = roomData.getString("user_name");
/* 198:190 */     if (room.roomOwnerName == null) {
/* 199:191 */       room.roomOwnerName = "";
/* 200:    */     }
/* 201:193 */     room.state = roomData.getInt("state");
/* 202:194 */     room.category = roomData.getInt("category");
/* 203:    */     
/* 204:196 */     room.model = roomData.getString("model_name");
/* 205:197 */     if ((room.model == null) || (room.model.isEmpty())) {
/* 206:198 */       throw new Exception("roomModel is null: " + roomData.getString("model_name"));
/* 207:    */     }
/* 208:201 */     room.rating = roomData.getInt("score");
/* 209:    */     
/* 210:203 */     room.tags = roomData.getString("tags").split(",");
/* 211:204 */     for (String tag : room.tags) {
/* 212:205 */       if (tag.length() > 2) {
/* 213:206 */         AddTag(tag);
/* 214:    */       }
/* 215:    */     }
/* 216:210 */     String Icon_Items = roomData.getString("icon_items").replaceAll(".", ",");
/* 217:211 */     if (Icon_Items.isEmpty()) {
/* 218:212 */       room.icon = new RoomIcon(roomData.getInt("icon_bg"), roomData.getInt("icon_fg"), new String[0]);
/* 219:    */     } else {
/* 220:214 */       room.icon = new RoomIcon(roomData.getInt("icon_bg"), roomData.getInt("icon_fg"), Icon_Items.split("|"));
/* 221:    */     }
/* 222:216 */     room.password = roomData.getString("password");
/* 223:217 */     room.Wallpaper = roomData.getString("wallpaper");
/* 224:218 */     room.Floor = roomData.getString("floor");
/* 225:219 */     room.Landscape = roomData.getString("landscape");
/* 226:220 */     room.setFlag(2, roomData.getString("allow_pets").equals("1"));
/* 227:221 */     room.setFlag(4, roomData.getString("allow_pets_eat").equals("1"));
/* 228:222 */     room.setFlag(8, roomData.getString("allow_walkthrough").equals("1"));
/* 229:223 */     room.setFlag(16, roomData.getString("allow_hidewall").equals("1"));
/* 230:224 */     room.floorAnchor = roomData.getShort("floorthickness");
/* 231:225 */     room.wallAnchor = roomData.getShort("wallthickness");
/* 232:    */     
/* 233:    */ 
/* 234:    */ 
/* 235:229 */     room.modPermissions = new ModerationPermissions(roomData.getInt("settings_mod"));
/* 236:230 */     room.tradingSettings = new TradingSettings(roomData.getInt("settings_trd"));
/* 237:231 */     room.chatSettings = new ChatSettings(roomData.getInt("settings_chat"));
/* 238:    */     
/* 239:233 */     room.lastUsedThis = Utils.getTimestamp();
/* 240:234 */     rooms.put(Integer.valueOf(room.roomId), room);
/* 241:    */     
/* 242:236 */     return room;
/* 243:    */   }
/* 244:    */   
/* 245:    */   public static RoomData loadRoom(int roomId)
/* 246:    */     throws Exception
/* 247:    */   {
/* 248:240 */     if (roomId < 1) {
/* 249:241 */       return null;
/* 250:    */     }
/* 251:244 */     DBResult result = new DBResult();
/* 252:    */     try
/* 253:    */     {
/* 254:246 */       Database.query(result, "SELECT * FROM rooms WHERE id = '" + roomId + "';", new Object[0]);
/* 255:    */       
/* 256:248 */       RoomData room = null;
/* 257:249 */       if (result.data.next()) {
/* 258:250 */         room = loadRoomResultSet(result.data);
/* 259:    */       }
/* 260:253 */       result.close();
/* 261:    */       
/* 262:255 */       return room;
/* 263:    */     }
/* 264:    */     catch (Exception ex)
/* 265:    */     {
/* 266:258 */       result.close();
/* 267:259 */       throw ex;
/* 268:    */     }
/* 269:    */   }
/* 270:    */   
/* 271:    */   public static void RemoveTag(String Tag)
/* 272:    */   {
/* 273:264 */     if (PopularTags.containsKey(Tag))
/* 274:    */     {
/* 275:265 */       int Count = ((Integer)PopularTags.get(Tag)).intValue() - 1;
/* 276:266 */       if (Count > 0) {
/* 277:267 */         PopularTags.put(Tag, Integer.valueOf(Count));
/* 278:    */       } else {
/* 279:269 */         PopularTags.remove(Tag);
/* 280:    */       }
/* 281:    */     }
/* 282:    */   }
/* 283:    */   
/* 284:    */   public static void setActive()
/* 285:    */   {
/* 286:275 */     ActiveRooms += 1;
/* 287:    */   }
/* 288:    */   
/* 289:    */   public static void setInactive(RoomData roomData)
/* 290:    */   {
/* 291:279 */     ActiveRooms -= 1;
/* 292:280 */     roomData.room = null;
/* 293:    */   }
/* 294:    */   
/* 295:    */   public static void unloadRoom(int RoomId)
/* 296:    */   {
/* 297:285 */     rooms.remove(Integer.valueOf(RoomId));
/* 298:    */   }
/* 299:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.RoomManager
 * JD-Core Version:    0.7.0.1
 */