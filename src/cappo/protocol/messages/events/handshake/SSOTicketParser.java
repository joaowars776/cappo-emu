/*   1:    */ package cappo.protocol.messages.events.handshake;
/*   2:    */ 
/*   3:    */ import cappo.engine.Server;
/*   4:    */ import cappo.engine.ServerTasks;
/*   5:    */ import cappo.engine.database.Database;
/*   6:    */ import cappo.engine.logging.Log;
/*   7:    */ import cappo.engine.network.MessageReader;
/*   8:    */ import cappo.engine.network.QueueWriter;
/*   9:    */ import cappo.engine.player.Clients;
/*  10:    */ import cappo.engine.player.Connection;
/*  11:    */ import cappo.engine.threadpools.DatabaseQueryTask;
/*  12:    */ import cappo.game.achievements.UserAchievementManager;
/*  13:    */ import cappo.game.collections.Badge;
/*  14:    */ import cappo.game.collections.FavRoom;
/*  15:    */ import cappo.game.collections.Utils;
/*  16:    */ import cappo.game.collections.Wardrobe;
/*  17:    */ import cappo.game.player.PlayerData;
/*  18:    */ import cappo.game.player.data.AvatarData;
/*  19:    */ import cappo.game.roomengine.RoomData;
/*  20:    */ import cappo.game.roomengine.RoomManager;
/*  21:    */ import cappo.protocol.messages.IncomingMessageEvent;
/*  22:    */ import cappo.protocol.messages.composers.handshake.AuthOKComposer;
/*  23:    */ import cappo.protocol.messages.composers.handshake.GenericErrorComposer;
/*  24:    */ import cappo.protocol.messages.composers.handshake.UserDisconnectComposer;
/*  25:    */ import cappo.protocol.messages.composers.navigator.FavouritesComposer;
/*  26:    */ import cappo.protocol.messages.composers.navigator.NavigatorSettingsComposer;
/*  27:    */ import cappo.protocol.messages.composers.notifications.MOTDComposer;
/*  28:    */ import io.netty.channel.Channel;
/*  29:    */ import java.lang.reflect.Method;
/*  30:    */ import java.sql.ResultSet;
/*  31:    */ import java.util.Iterator;
/*  32:    */ import java.util.List;
/*  33:    */ import java.util.Map;
/*  34:    */ import java.util.Set;
/*  35:    */ import java.util.concurrent.ConcurrentHashMap;
/*  36:    */ import sun.misc.BASE64Decoder;
/*  37:    */ 
/*  38:    */ public class SSOTicketParser
/*  39:    */   extends IncomingMessageEvent
/*  40:    */ {
/*  41: 41 */   public static final Map<Integer, Integer> temporallyBans = new ConcurrentHashMap();
/*  42: 42 */   private static int bansCleaner = 0;
/*  43:    */   private static final int QUERY_COUNT = 7;
/*  44:    */   private static final int PARAM_1 = 0;
/*  45:    */   private static final int PARAM_2 = 1;
/*  46:    */   private static Method checkBannedCallBack;
/*  47:    */   private static Method avatarCallBack;
/*  48:    */   private static Method favRoomsCallBack;
/*  49:    */   private static Method ignoredUsersCallBack;
/*  50:    */   private static Method wardrobeCallBack;
/*  51:    */   private static Method badgesCallBack;
/*  52:    */   private static Method roomsCallBack;
/*  53:    */   
/*  54:    */   public void messageReceived(Connection cn)
/*  55:    */   {
/*  56: 48 */     BASE64Decoder decoder = new BASE64Decoder();
/*  57: 49 */     String token = "";
/*  58:    */     try
/*  59:    */     {
/*  60: 51 */       token = new String(decoder.decodeBuffer(cn.currentPacket.readString()));
/*  61:    */     }
/*  62:    */     catch (Exception ex)
/*  63:    */     {
/*  64: 53 */       QueueWriter.write(cn.socket, GenericErrorComposer.compose(-400));
/*  65: 54 */       Log.printException("SSOTicketParser", ex);
/*  66: 55 */       return;
/*  67:    */     }
/*  68: 58 */     Log.printLog("Token <" + token + ">");
/*  69:    */     
/*  70: 60 */     String[] parts = token.split("-", 3);
/*  71: 61 */     if (parts.length != 3)
/*  72:    */     {
/*  73: 62 */       QueueWriter.write(cn.socket, GenericErrorComposer.compose(-400));
/*  74: 63 */       return;
/*  75:    */     }
/*  76: 65 */     long timeOut = Long.parseLong(parts[0]);
/*  77: 66 */     if (Utils.getTimestamp() > timeOut)
/*  78:    */     {
/*  79: 67 */       QueueWriter.write(cn.socket, GenericErrorComposer.compose(-400));
/*  80: 68 */       return;
/*  81:    */     }
/*  82: 70 */     String pubKey = Long.toString(timeOut + Integer.parseInt(parts[1]));
/*  83: 71 */     String chunk = parts[2];
/*  84:    */     
/*  85: 73 */     int p = 0;
/*  86: 74 */     int len = Server.ssoSecretKey.length();
/*  87: 75 */     int len2 = pubKey.length();
/*  88: 76 */     String tokenizer = "";
/*  89: 77 */     for (int i = 0; i < len; i++)
/*  90:    */     {
/*  91: 78 */       tokenizer = tokenizer + (char)(Server.ssoSecretKey.charAt(i) & 0xFF ^ pubKey.charAt(p) & 0xFF);
/*  92: 79 */       p++;
/*  93: 79 */       if (p == len2) {
/*  94: 80 */         p = 0;
/*  95:    */       }
/*  96:    */     }
/*  97: 83 */     len = chunk.length();
/*  98: 84 */     int len3 = tokenizer.length();
/*  99: 85 */     byte[] buf = new byte[len];
/* 100: 86 */     p = 0;
/* 101: 87 */     for (int i = 0; i < len; i++)
/* 102:    */     {
/* 103: 88 */       buf[i] = ((byte)(chunk.charAt(i) & 0xFF ^ tokenizer.charAt(p) & 0xFF));
/* 104: 89 */       p++;
/* 105: 89 */       if (p == len3) {
/* 106: 90 */         p = 0;
/* 107:    */       }
/* 108:    */     }
/* 109: 94 */     int userId = Integer.parseInt(new String(buf));
/* 110:    */     
/* 111: 96 */     Log.printLog("Token-UseId <" + userId + "> " + System.currentTimeMillis());
/* 112:    */     try
/* 113:    */     {
/* 114:100 */       PlayerData playerData = Clients.getPlayerData(userId);
/* 115:103 */       if (playerData == null)
/* 116:    */       {
/* 117:104 */         QueueWriter.write(cn.socket, GenericErrorComposer.compose(-400));
/* 118:105 */         return;
/* 119:    */       }
/* 120:109 */       if (playerData.connection != null)
/* 121:    */       {
/* 122:110 */         QueueWriter.writeAndClose(cn.socket, UserDisconnectComposer.compose(2));
/* 123:112 */         if (cn.haveFlag(2))
/* 124:    */         {
/* 125:113 */           cn.setFlag(2, false);
/* 126:114 */           QueueWriter.write(playerData.connection.socket, ServerTasks.PingMessage);
/* 127:    */         }
/* 128:    */         else
/* 129:    */         {
/* 130:116 */           playerData.connection.socket.close();
/* 131:    */         }
/* 132:119 */         return;
/* 133:    */       }
/* 134:122 */       DatabaseQueryTask queryTask = new DatabaseQueryTask(7);
/* 135:    */       
/* 136:124 */       Integer tmpBan = (Integer)temporallyBans.get(Integer.valueOf(playerData.userId));
/* 137:125 */       if (tmpBan != null)
/* 138:    */       {
/* 139:126 */         if (tmpBan.intValue() < Utils.getTimestamp()) {
/* 140:127 */           temporallyBans.remove(Integer.valueOf(playerData.userId));
/* 141:    */         } else {
/* 142:129 */           QueueWriter.writeAndClose(cn.socket, UserDisconnectComposer.compose(1));
/* 143:    */         }
/* 144:    */       }
/* 145:133 */       else if (bansCleaner++ % 50 == 30) {
/* 146:134 */         for (Iterator localIterator = temporallyBans.keySet().iterator(); localIterator.hasNext();)
/* 147:    */         {
/* 148:134 */           int key = ((Integer)localIterator.next()).intValue();
/* 149:135 */           Integer timeout = (Integer)temporallyBans.get(Integer.valueOf(key));
/* 150:136 */           if (timeout.intValue() < Utils.getTimestamp())
/* 151:    */           {
/* 152:137 */             temporallyBans.remove(Integer.valueOf(key));
/* 153:138 */             break;
/* 154:    */           }
/* 155:    */         }
/* 156:    */       }
/* 157:145 */       queryTask.addQuery("SELECT type,hours,created FROM bans WHERE user_id = " + 
/* 158:    */       
/* 159:    */ 
/* 160:    */ 
/* 161:149 */         playerData.userId + " LIMIT 1;", checkBannedCallBack, new Object[] { cn, playerData }, new Object[0]);
/* 162:    */       
/* 163:151 */       queryTask.addQuery("SELECT credits,crystals,activity_points,activity_points_lastupdate,vip_points,home_room,respects,daily_respect_points,daily_pet_respect_points,newbie_status,block_newfriends,block_trade FROM users  WHERE id = " + 
/* 164:    */       
/* 165:    */ 
/* 166:    */ 
/* 167:    */ 
/* 168:    */ 
/* 169:    */ 
/* 170:    */ 
/* 171:    */ 
/* 172:    */ 
/* 173:    */ 
/* 174:    */ 
/* 175:    */ 
/* 176:164 */         playerData.userId + " LIMIT 1;", avatarCallBack, new Object[] { cn }, new Object[0]);
/* 177:    */       
/* 178:166 */       queryTask.addQuery("SELECT DISTINCT * FROM rooms JOIN user_favorites ON (user_favorites.room_id = rooms.id) WHERE user_favorites.user_id = " + playerData.userId + " LIMIT 30;", favRoomsCallBack, new Object[] { cn }, new Object[0]);
/* 179:167 */       queryTask.addQuery("SELECT * FROM user_ignores WHERE user_id = " + playerData.userId + ";", ignoredUsersCallBack, new Object[] { cn }, new Object[0]);
/* 180:168 */       queryTask.addQuery("SELECT * FROM user_wardrobe WHERE user_id = " + playerData.userId + ";", wardrobeCallBack, new Object[] { cn }, new Object[0]);
/* 181:169 */       queryTask.addQuery("SELECT * FROM user_badges WHERE user_id = " + playerData.userId + ";", badgesCallBack, new Object[] { cn }, new Object[0]);
/* 182:170 */       queryTask.addQuery("SELECT DISTINCT * FROM rooms WHERE user_id=" + playerData.userId + " LIMIT 200;", roomsCallBack, new Object[] { cn }, new Object[0]);
/* 183:171 */       DatabaseQueryTask.addTask(queryTask, 0, 0);
/* 184:    */     }
/* 185:    */     catch (Exception ex)
/* 186:    */     {
/* 187:173 */       Log.printException("SSOTicketParser-1", ex);
/* 188:174 */       QueueWriter.write(cn.socket, GenericErrorComposer.compose(-400));
/* 189:    */     }
/* 190:    */   }
/* 191:    */   
/* 192:    */   public static boolean checkBannedCallBack(ResultSet result, Object extra)
/* 193:    */     throws Exception
/* 194:    */   {
/* 195:179 */     Object[] data = (Object[])extra;
/* 196:180 */     Connection cn = (Connection)data[0];
/* 197:181 */     PlayerData playerData = (PlayerData)data[1];
/* 198:184 */     if (result.next())
/* 199:    */     {
/* 200:185 */       result.getString("type");
/* 201:186 */       int hours = result.getInt("hours");
/* 202:187 */       long created = result.getLong("created");
/* 203:189 */       if (hours == 100000)
/* 204:    */       {
/* 205:191 */         QueueWriter.writeAndClose(cn.socket, UserDisconnectComposer.compose(10));
/* 206:192 */         return false;
/* 207:    */       }
/* 208:195 */       long expire = created + hours * 3600;
/* 209:196 */       if (expire > Utils.getTimestamp())
/* 210:    */       {
/* 211:197 */         QueueWriter.writeAndClose(cn.socket, UserDisconnectComposer.compose(1));
/* 212:198 */         return false;
/* 213:    */       }
/* 214:    */       try
/* 215:    */       {
/* 216:201 */         Database.exec("DELETE FROM bans WHERE user_id =" + playerData.userId + ";", new Object[0]);
/* 217:    */       }
/* 218:    */       catch (Exception ex)
/* 219:    */       {
/* 220:204 */         Log.printException("Disconnect", ex);
/* 221:    */       }
/* 222:    */     }
/* 223:210 */     cn.setPlayerData(playerData);
/* 224:    */     
/* 225:    */ 
/* 226:213 */     playerData.lastVisit = Utils.getTimestamp();
/* 227:    */     
/* 228:215 */     return true;
/* 229:    */   }
/* 230:    */   
/* 231:    */   public static boolean avatarCallBack(ResultSet result, Object extra)
/* 232:    */     throws Exception
/* 233:    */   {
/* 234:219 */     Object[] data = (Object[])extra;
/* 235:220 */     Connection cn = (Connection)data[0];
/* 236:222 */     if (!result.next()) {
/* 237:223 */       return false;
/* 238:    */     }
/* 239:226 */     cn.credits = result.getInt("credits");
/* 240:227 */     cn.diamondAmmount = result.getInt("crystals");
/* 241:228 */     cn.pixelAmmount = result.getInt("activity_points");
/* 242:229 */     cn.nextPixelsUpdate = result.getLong("activity_points_lastupdate");
/* 243:230 */     cn.vipPoins = result.getInt("vip_points");
/* 244:231 */     cn.homeRoom = result.getInt("home_room");
/* 245:232 */     cn.respects = result.getInt("respects");
/* 246:233 */     cn.dailyRespectPoints = result.getInt("daily_respect_points");
/* 247:234 */     cn.dailyPetRespectPoints = result.getInt("daily_pet_respect_points");
/* 248:235 */     cn.setFlag(4, result.getInt("newbie_status") == 1);
/* 249:236 */     cn.setFlag(1, false);
/* 250:237 */     cn.setFlag(16, result.getInt("block_newfriends") == 1);
/* 251:238 */     cn.setFlag(8, result.getInt("block_trade") == 1);
/* 252:    */     
/* 253:240 */     cn.MaxRooms = 100;
/* 254:    */     
/* 255:    */ 
/* 256:    */ 
/* 257:    */ 
/* 258:    */ 
/* 259:    */ 
/* 260:    */ 
/* 261:    */ 
/* 262:    */ 
/* 263:    */ 
/* 264:    */ 
/* 265:    */ 
/* 266:    */ 
/* 267:    */ 
/* 268:    */ 
/* 269:256 */     return true;
/* 270:    */   }
/* 271:    */   
/* 272:    */   public static boolean favRoomsCallBack(ResultSet result, Object extra)
/* 273:    */     throws Exception
/* 274:    */   {
/* 275:260 */     Object[] data = (Object[])extra;
/* 276:261 */     Connection cn = (Connection)data[0];
/* 277:263 */     while (result.next())
/* 278:    */     {
/* 279:264 */       RoomData room = RoomManager.getRoom(result.getInt("id"));
/* 280:265 */       if (room == null) {
/* 281:266 */         room = RoomManager.loadRoomResultSet(result);
/* 282:    */       }
/* 283:269 */       if (room != null) {
/* 284:270 */         cn.favoriteRooms.put(Integer.valueOf(room.roomId), new FavRoom(room));
/* 285:    */       }
/* 286:    */     }
/* 287:274 */     return true;
/* 288:    */   }
/* 289:    */   
/* 290:    */   public static boolean ignoredUsersCallBack(ResultSet result, Object extra)
/* 291:    */     throws Exception
/* 292:    */   {
/* 293:278 */     Object[] data = (Object[])extra;
/* 294:279 */     Connection cn = (Connection)data[0];
/* 295:281 */     while (result.next()) {
/* 296:282 */       cn.ignoreUsers.add(Integer.valueOf(result.getInt("ignore_id")));
/* 297:    */     }
/* 298:285 */     return true;
/* 299:    */   }
/* 300:    */   
/* 301:    */   public static boolean wardrobeCallBack(ResultSet result, Object extra)
/* 302:    */     throws Exception
/* 303:    */   {
/* 304:289 */     Object[] data = (Object[])extra;
/* 305:290 */     Connection cn = (Connection)data[0];
/* 306:292 */     while (result.next())
/* 307:    */     {
/* 308:293 */       Wardrobe wrb = new Wardrobe(result.getInt("slot_id"), result.getString("look"), (short)(result.getString("gender").equals("M") ? 1 : 0));
/* 309:294 */       cn.Wardrobes.put(Short.valueOf(wrb.slotId), wrb);
/* 310:    */     }
/* 311:297 */     return true;
/* 312:    */   }
/* 313:    */   
/* 314:    */   public static boolean badgesCallBack(ResultSet result, Object extra)
/* 315:    */     throws Exception
/* 316:    */   {
/* 317:301 */     Object[] data = (Object[])extra;
/* 318:302 */     Connection cn = (Connection)data[0];
/* 319:304 */     while (result.next())
/* 320:    */     {
/* 321:305 */       Badge badge = new Badge(result.getInt("id"), result.getString("badge_id"), result.getInt("badge_slot"));
/* 322:306 */       cn.badges.put(badge.badgeCode, badge);
/* 323:307 */       if (badge.badgeSlot > 0)
/* 324:    */       {
/* 325:308 */         Badge prev = (Badge)cn.badgesSelected.put(Integer.valueOf(badge.badgeSlot), badge);
/* 326:309 */         if (prev != null)
/* 327:    */         {
/* 328:310 */           prev.badgeSlot = 0;
/* 329:311 */           prev.needInsert = true;
/* 330:    */         }
/* 331:    */       }
/* 332:    */     }
/* 333:316 */     return true;
/* 334:    */   }
/* 335:    */   
/* 336:    */   public static boolean roomsCallBack(ResultSet result, Object extra)
/* 337:    */     throws Exception
/* 338:    */   {
/* 339:320 */     Object[] data = (Object[])extra;
/* 340:321 */     Connection cn = (Connection)data[0];
/* 341:323 */     while (result.next()) {
/* 342:    */       try
/* 343:    */       {
/* 344:325 */         RoomData room = RoomManager.getRoom(result.getInt("id"));
/* 345:326 */         if (room == null) {
/* 346:327 */           room = RoomManager.loadRoomResultSet(result);
/* 347:    */         }
/* 348:330 */         if (room != null) {
/* 349:331 */           cn.ownRooms.put(Integer.valueOf(room.roomId), room);
/* 350:    */         }
/* 351:    */       }
/* 352:    */       catch (Exception ex)
/* 353:    */       {
/* 354:334 */         Log.printException("", ex);
/* 355:    */       }
/* 356:    */     }
/* 357:339 */     cn.setFlag(2, true);
/* 358:340 */     Clients.setOnline(true);
/* 359:    */     
/* 360:    */ 
/* 361:343 */     cn.avatarData.achievementManager.fillAchievements();
/* 362:    */     
/* 363:    */ 
/* 364:346 */     QueueWriter.write(cn.socket, AuthOKComposer.compose());
/* 365:347 */     QueueWriter.write(cn.socket, NavigatorSettingsComposer.compose(cn.homeRoom, 0));
/* 366:348 */     QueueWriter.write(cn.socket, FavouritesComposer.compose(cn.favoriteRooms.keySet()));
/* 367:    */     
/* 368:350 */     cn.getPlayerData().setupLevelStuff();
/* 369:352 */     if (!cappo.game.utils.lang.LangTexts.texts[5].isEmpty()) {
/* 370:353 */       QueueWriter.write(cn.socket, MOTDComposer.compose(new String[] { cappo.game.utils.lang.LangTexts.texts[5] }));
/* 371:    */     }
/* 372:357 */     cn.socket.flush();
/* 373:    */     
/* 374:359 */     return true;
/* 375:    */   }
/* 376:    */   
/* 377:    */   static
/* 378:    */   {
/* 379:    */     try
/* 380:    */     {
/* 381:375 */       checkBannedCallBack = SSOTicketParser.class.getMethod("checkBannedCallBack", new Class[] { ResultSet.class, Object.class });
/* 382:376 */       avatarCallBack = SSOTicketParser.class.getMethod("avatarCallBack", new Class[] { ResultSet.class, Object.class });
/* 383:377 */       favRoomsCallBack = SSOTicketParser.class.getMethod("favRoomsCallBack", new Class[] { ResultSet.class, Object.class });
/* 384:378 */       ignoredUsersCallBack = SSOTicketParser.class.getMethod("ignoredUsersCallBack", new Class[] { ResultSet.class, Object.class });
/* 385:379 */       wardrobeCallBack = SSOTicketParser.class.getMethod("wardrobeCallBack", new Class[] { ResultSet.class, Object.class });
/* 386:380 */       badgesCallBack = SSOTicketParser.class.getMethod("badgesCallBack", new Class[] { ResultSet.class, Object.class });
/* 387:381 */       roomsCallBack = SSOTicketParser.class.getMethod("roomsCallBack", new Class[] { ResultSet.class, Object.class });
/* 388:    */     }
/* 389:    */     catch (Exception ex)
/* 390:    */     {
/* 391:383 */       Log.printException("", ex);
/* 392:    */     }
/* 393:    */   }
/* 394:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.handshake.SSOTicketParser
 * JD-Core Version:    0.7.0.1
 */