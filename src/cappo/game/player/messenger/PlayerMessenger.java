/*   1:    */ package cappo.game.player.messenger;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.DBResult;
/*   4:    */ import cappo.engine.database.Database;
/*   5:    */ import cappo.engine.logging.Log;
/*   6:    */ import cappo.engine.network.MessageWriter;
/*   7:    */ import cappo.engine.network.QueueWriter;
/*   8:    */ import cappo.engine.player.Clients;
/*   9:    */ import cappo.engine.player.Connection;
/*  10:    */ import cappo.game.player.AvatarLook;
/*  11:    */ import cappo.game.player.PlayerData;
/*  12:    */ import cappo.protocol.messages.Composer;
/*  13:    */ import cappo.protocol.messages.composers.friendlist.FriendsUpdatesComposer;
/*  14:    */ import java.sql.ResultSet;
/*  15:    */ import java.util.Collection;
/*  16:    */ import java.util.Iterator;
/*  17:    */ import java.util.Map;
/*  18:    */ import java.util.Set;
/*  19:    */ import java.util.concurrent.ConcurrentHashMap;
/*  20:    */ 
/*  21:    */ public class PlayerMessenger
/*  22:    */ {
/*  23:    */   public PlayerData playerData;
/*  24:    */   public static final int FRIENDSLIMIT = 600;
/*  25:    */   public static final int FRIENDSLIMIT_VIP = 1200;
/*  26:    */   public static final int NONE = 0;
/*  27:    */   public static final int HEART = 1;
/*  28:    */   public static final int SMILE = 2;
/*  29:    */   public static final int BOBBA = 3;
/*  30:    */   public Map<Integer, Map<Integer, MessengerFriend>> friendships;
/*  31:    */   private Map<Integer, MessengerFriendCategory> categories;
/*  32:    */   private Map<Integer, MessengerFriend> friends;
/*  33:    */   private Map<Integer, MessengerFriendRequest> requests;
/*  34:    */   private Map<Integer, MessengerFriendUpdate> updates;
/*  35:    */   public boolean isOnline;
/*  36:    */   
/*  37:    */   public boolean isFull()
/*  38:    */   {
/*  39: 38 */     return friendsCount() >= getLimitFriends();
/*  40:    */   }
/*  41:    */   
/*  42:    */   public int getLimitFriends()
/*  43:    */   {
/*  44: 42 */     return 1200;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public PlayerMessenger(PlayerData data)
/*  48:    */   {
/*  49: 46 */     this.playerData = data;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public int friendsCount()
/*  53:    */   {
/*  54: 51 */     if (this.isOnline) {
/*  55: 52 */       return this.friends.size() + this.requests.size();
/*  56:    */     }
/*  57: 54 */     DBResult result = new DBResult();
/*  58:    */     try
/*  59:    */     {
/*  60: 56 */       Database.query(result, "SELECT (SELECT COUNT(user_id) FROM user_friends WHERE user_id=" + this.playerData.userId + ") AS friendsCount, (SELECT COUNT(user_id) FROM user_friendreqs WHERE user_id=" + this.playerData.userId + ") AS requestsCount;", new Object[0]);
/*  61: 57 */       if (result.data.next())
/*  62:    */       {
/*  63: 58 */         int count = result.data.getInt("friendsCount") + result.data.getInt("requestsCount");
/*  64: 59 */         result.close();
/*  65: 60 */         return count;
/*  66:    */       }
/*  67:    */     }
/*  68:    */     catch (Exception ex)
/*  69:    */     {
/*  70: 63 */       Log.printException("PlayerMessenger", ex);
/*  71:    */       
/*  72: 65 */       result.close();
/*  73:    */     }
/*  74: 68 */     return 0;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public MessengerFriend getFriend(int friendid)
/*  78:    */   {
/*  79: 72 */     return (MessengerFriend)this.friends.get(Integer.valueOf(friendid));
/*  80:    */   }
/*  81:    */   
/*  82:    */   public Collection<MessengerFriend> getFriends()
/*  83:    */   {
/*  84: 76 */     return this.friends.values();
/*  85:    */   }
/*  86:    */   
/*  87:    */   public Collection<MessengerFriendRequest> getFriendRequests()
/*  88:    */   {
/*  89: 80 */     return this.requests.values();
/*  90:    */   }
/*  91:    */   
/*  92:    */   public Collection<MessengerFriendCategory> getCategories()
/*  93:    */   {
/*  94: 84 */     return this.categories.values();
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void clearRequests()
/*  98:    */   {
/*  99: 88 */     this.requests.values();
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void setRelationshipStatus(int type, MessengerFriend friend)
/* 103:    */   {
/* 104: 92 */     Map<Integer, MessengerFriend> friendship = (Map)this.friendships.get(Integer.valueOf(type));
/* 105: 93 */     if (friendship == null)
/* 106:    */     {
/* 107: 94 */       friendship = new ConcurrentHashMap();
/* 108: 95 */       this.friendships.put(Integer.valueOf(type), friendship);
/* 109:    */     }
/* 110: 97 */     friendship.put(Integer.valueOf(friend.userId), friend);
/* 111:    */   }
/* 112:    */   
/* 113:    */   public void removeRelationship(int type, int userId)
/* 114:    */   {
/* 115:101 */     Map<Integer, MessengerFriend> friendship = (Map)this.friendships.get(Integer.valueOf(type));
/* 116:102 */     if (friendship == null) {
/* 117:103 */       return;
/* 118:    */     }
/* 119:105 */     friendship.remove(Integer.valueOf(userId));
/* 120:106 */     if (friendship.isEmpty()) {
/* 121:107 */       this.friendships.remove(Integer.valueOf(type));
/* 122:    */     }
/* 123:    */   }
/* 124:    */   
/* 125:    */   public void initMessenger(DBResult result)
/* 126:    */   {
/* 127:112 */     if (this.isOnline) {
/* 128:113 */       return;
/* 129:    */     }
/* 130:115 */     this.isOnline = true;
/* 131:    */     
/* 132:117 */     this.friendships = new ConcurrentHashMap();
/* 133:    */     
/* 134:119 */     this.categories = new ConcurrentHashMap();
/* 135:120 */     this.friends = new ConcurrentHashMap();
/* 136:121 */     this.requests = new ConcurrentHashMap();
/* 137:122 */     this.updates = new ConcurrentHashMap();
/* 138:    */     
/* 139:124 */     DBResult result2 = new DBResult();
/* 140:    */     try
/* 141:    */     {
/* 142:127 */       Database.query(result, "SELECT * FROM user_friends WHERE user_id = " + this.playerData.userId + ";", new Object[0]);
/* 143:128 */       while (result.data.next()) {
/* 144:129 */         addFriend(result.data.getInt("friend_id"), result.data.getInt("type"), result2);
/* 145:    */       }
/* 146:132 */       Database.query(result, "SELECT * FROM user_friendreqs WHERE user_id = " + this.playerData.userId + ";", new Object[0]);
/* 147:133 */       while (result.data.next()) {
/* 148:134 */         addFriendRequest(result.data.getInt("friend_id"), result.data.getString("friend_name"), false);
/* 149:    */       }
/* 150:    */     }
/* 151:    */     catch (Exception ex)
/* 152:    */     {
/* 153:138 */       Log.printException("PlayerInventory", ex);
/* 154:    */     }
/* 155:141 */     result2.close();
/* 156:    */   }
/* 157:    */   
/* 158:    */   public void addFriend(PlayerData plr)
/* 159:    */   {
/* 160:    */     try
/* 161:    */     {
/* 162:149 */       Database.exec("INSERT IGNORE INTO user_friends (user_id,friend_id,type)VALUES(" + this.playerData.userId + "," + plr.userId + ",'0');", new Object[0]);
/* 163:150 */       Database.exec("INSERT IGNORE INTO user_friends (user_id,friend_id,type)VALUES(" + plr.userId + "," + this.playerData.userId + ",'0');", new Object[0]);
/* 164:    */     }
/* 165:    */     catch (Exception ex)
/* 166:    */     {
/* 167:153 */       Log.printException("addFriend", ex);
/* 168:    */     }
/* 169:156 */     this.friends.put(Integer.valueOf(plr.userId), new MessengerFriend(plr.userId, 0));
/* 170:157 */     update(new MessengerFriendUpdate(plr.userId, 1));
/* 171:159 */     if (plr.messenger.isOnline)
/* 172:    */     {
/* 173:160 */       plr.messenger.friends.put(Integer.valueOf(this.playerData.userId), new MessengerFriend(this.playerData.userId, 0));
/* 174:161 */       plr.messenger.update(new MessengerFriendUpdate(this.playerData.userId, 1));
/* 175:162 */       QueueWriter.writeAndFlush(plr.connection.socket, plr.messenger.getFriendUpdstes());
/* 176:    */     }
/* 177:    */   }
/* 178:    */   
/* 179:    */   public void addFriend(int friendId)
/* 180:    */   {
/* 181:168 */     PlayerData plr = Clients.getPlayerData(friendId);
/* 182:169 */     if (plr == null) {
/* 183:170 */       return;
/* 184:    */     }
/* 185:    */     try
/* 186:    */     {
/* 187:174 */       Database.exec("INSERT IGNORE INTO user_friends (user_id,friend_id,type)VALUES(" + this.playerData.userId + "," + friendId + ",'0');", new Object[0]);
/* 188:175 */       Database.exec("INSERT IGNORE INTO user_friends (user_id,friend_id,type)VALUES(" + friendId + "," + this.playerData.userId + ",'0');", new Object[0]);
/* 189:    */     }
/* 190:    */     catch (Exception ex)
/* 191:    */     {
/* 192:178 */       Log.printException("Disconnect", ex);
/* 193:    */     }
/* 194:182 */     this.friends.put(Integer.valueOf(friendId), new MessengerFriend(friendId, 0));
/* 195:183 */     update(new MessengerFriendUpdate(friendId, 1));
/* 196:185 */     if (plr.messenger.isOnline)
/* 197:    */     {
/* 198:186 */       plr.messenger.friends.put(Integer.valueOf(this.playerData.userId), new MessengerFriend(this.playerData.userId, 0));
/* 199:187 */       plr.messenger.update(new MessengerFriendUpdate(this.playerData.userId, 1));
/* 200:188 */       QueueWriter.writeAndFlush(plr.connection.socket, plr.messenger.getFriendUpdstes());
/* 201:    */     }
/* 202:    */   }
/* 203:    */   
/* 204:    */   public void addFriend(int friendId, int type, DBResult result)
/* 205:    */   {
/* 206:195 */     PlayerData plr = Clients.getPlayerData(friendId);
/* 207:196 */     if (plr == null) {
/* 208:197 */       return;
/* 209:    */     }
/* 210:200 */     MessengerFriend friend = new MessengerFriend(friendId, type);
/* 211:201 */     this.friends.put(Integer.valueOf(friendId), friend);
/* 212:202 */     if (type != 0) {
/* 213:203 */       setRelationshipStatus(type, friend);
/* 214:    */     }
/* 215:206 */     if (plr.messenger.isOnline)
/* 216:    */     {
/* 217:207 */       plr.messenger.update(new MessengerFriendUpdate(this.playerData.userId, 0));
/* 218:208 */       QueueWriter.writeAndFlush(plr.connection.socket, plr.messenger.getFriendUpdstes());
/* 219:    */     }
/* 220:    */   }
/* 221:    */   
/* 222:    */   public MessageWriter getFriendUpdstes()
/* 223:    */   {
/* 224:213 */     MessageWriter message = FriendsUpdatesComposer.compose(this.updates.values(), this.playerData);
/* 225:214 */     this.updates.clear();
/* 226:215 */     return message;
/* 227:    */   }
/* 228:    */   
/* 229:    */   public void update(MessengerFriendUpdate update)
/* 230:    */   {
/* 231:219 */     this.updates.put(Integer.valueOf(update.userId), update);
/* 232:    */   }
/* 233:    */   
/* 234:    */   public void addFriendRequest(int friendId, String username, boolean needInsert)
/* 235:    */   {
/* 236:223 */     if (this.isOnline)
/* 237:    */     {
/* 238:224 */       this.requests.put(Integer.valueOf(friendId), new MessengerFriendRequest(friendId, username, needInsert));
/* 239:225 */       return;
/* 240:    */     }
/* 241:    */     try
/* 242:    */     {
/* 243:229 */       Database.exec("INSERT IGNORE INTO user_friendreqs (user_id,friend_id,friend_name)VALUES(" + this.playerData.userId + "," + friendId + ",?);", new Object[] { username });
/* 244:    */     }
/* 245:    */     catch (Exception ex)
/* 246:    */     {
/* 247:232 */       Log.printException("Disconnect", ex);
/* 248:    */     }
/* 249:    */   }
/* 250:    */   
/* 251:    */   public MessengerFriendRequest pickRequest(int friendId)
/* 252:    */   {
/* 253:237 */     return (MessengerFriendRequest)this.requests.remove(Integer.valueOf(friendId));
/* 254:    */   }
/* 255:    */   
/* 256:    */   public void removeFriend(int friendId)
/* 257:    */   {
/* 258:241 */     MessengerFriend friend = (MessengerFriend)this.friends.remove(Integer.valueOf(friendId));
/* 259:242 */     if ((friend != null) && (friend.friendType != 0)) {
/* 260:243 */       removeRelationship(friend.friendType, friendId);
/* 261:    */     }
/* 262:245 */     update(new MessengerFriendUpdate(friendId, -1));
/* 263:    */     
/* 264:247 */     PlayerData friendPlayerData = Clients.getPlayerDataLoaded(friendId);
/* 265:248 */     if (friendPlayerData == null) {
/* 266:249 */       return;
/* 267:    */     }
/* 268:252 */     PlayerMessenger friendMessenger = friendPlayerData.messenger;
/* 269:253 */     if (!friendMessenger.isOnline) {
/* 270:254 */       return;
/* 271:    */     }
/* 272:257 */     MessengerFriend friend2 = (MessengerFriend)friendMessenger.friends.remove(Integer.valueOf(this.playerData.userId));
/* 273:258 */     if (friend2 != null)
/* 274:    */     {
/* 275:259 */       if (friend2.friendType != 0) {
/* 276:260 */         friendMessenger.removeRelationship(friend2.friendType, this.playerData.userId);
/* 277:    */       }
/* 278:263 */       if (friendMessenger.isOnline)
/* 279:    */       {
/* 280:264 */         friendMessenger.update(new MessengerFriendUpdate(this.playerData.userId, -1));
/* 281:265 */         QueueWriter.writeAndFlush(friendPlayerData.connection.socket, friendMessenger.getFriendUpdstes());
/* 282:    */       }
/* 283:    */     }
/* 284:    */   }
/* 285:    */   
/* 286:    */   public boolean haveRequest(int friendId)
/* 287:    */   {
/* 288:271 */     return this.requests.containsKey(Integer.valueOf(friendId));
/* 289:    */   }
/* 290:    */   
/* 291:    */   public boolean haveFriend(int friendId)
/* 292:    */   {
/* 293:276 */     return this.friends.containsKey(Integer.valueOf(friendId));
/* 294:    */   }
/* 295:    */   
/* 296:    */   public void save()
/* 297:    */   {
/* 298:280 */     this.isOnline = false;
/* 299:    */     
/* 300:    */ 
/* 301:283 */     this.playerData.messenger = new PlayerMessenger(this.playerData);
/* 302:    */     try
/* 303:    */     {
/* 304:286 */       if (this.requests != null) {
/* 305:287 */         for (MessengerFriendRequest req : this.requests.values()) {
/* 306:288 */           if (req.needInsert)
/* 307:    */           {
/* 308:291 */             req.needInsert = false;
/* 309:    */             try
/* 310:    */             {
/* 311:294 */               Database.exec("INSERT IGNORE INTO user_friendreqs (user_id,friend_id,friend_name)VALUES(" + this.playerData.userId + "," + req.userid + ",?);", new Object[] { req.username });
/* 312:    */             }
/* 313:    */             catch (Exception ex)
/* 314:    */             {
/* 315:297 */               Log.printException("Disconnect", ex);
/* 316:    */             }
/* 317:    */           }
/* 318:    */         }
/* 319:    */       }
/* 320:    */     }
/* 321:    */     catch (Exception ex)
/* 322:    */     {
/* 323:303 */       Log.printException("saveFriendRequests", ex);
/* 324:    */     }
/* 325:    */     try
/* 326:    */     {
/* 327:307 */       if (this.friends != null) {
/* 328:308 */         for (MessengerFriend friend : this.friends.values())
/* 329:    */         {
/* 330:309 */           PlayerData friendPlayerData = Clients.getPlayerDataLoaded(friend.userId);
/* 331:310 */           if (friendPlayerData != null)
/* 332:    */           {
/* 333:311 */             PlayerMessenger friendMessenger = friendPlayerData.messenger;
/* 334:312 */             if (friendMessenger.isOnline)
/* 335:    */             {
/* 336:313 */               friendMessenger.update(new MessengerFriendUpdate(this.playerData.userId, 0));
/* 337:314 */               QueueWriter.writeAndFlush(friendPlayerData.connection.socket, friendMessenger.getFriendUpdstes());
/* 338:    */             }
/* 339:    */           }
/* 340:318 */           if (friend.needUpdate)
/* 341:    */           {
/* 342:319 */             friend.needUpdate = false;
/* 343:    */             try
/* 344:    */             {
/* 345:322 */               Database.exec("UPDATE user_friends SET type = '" + friend.friendType + "' WHERE user_id = " + this.playerData.userId + " AND friend_id = " + friend.userId + ";", new Object[0]);
/* 346:    */             }
/* 347:    */             catch (Exception ex)
/* 348:    */             {
/* 349:325 */               Log.printException("addFriend", ex);
/* 350:    */             }
/* 351:    */           }
/* 352:    */         }
/* 353:    */       }
/* 354:    */     }
/* 355:    */     catch (Exception ex)
/* 356:    */     {
/* 357:333 */       Log.printException("saveFriends", ex);
/* 358:    */     }
/* 359:    */   }
/* 360:    */   
/* 361:    */   public void serializeRelationshipStatus(MessageWriter clientMessage)
/* 362:    */   {
/* 363:338 */     if (this.isOnline)
/* 364:    */     {
/* 365:339 */       Composer.add(Integer.valueOf(this.friendships.size()), clientMessage);
/* 366:340 */       for (Iterator localIterator1 = this.friendships.keySet().iterator(); localIterator1.hasNext();)
/* 367:    */       {
/* 368:340 */         int key = ((Integer)localIterator1.next()).intValue();
/* 369:341 */         Map<Integer, MessengerFriend> friendship = (Map)this.friendships.get(Integer.valueOf(key));
/* 370:342 */         Composer.add(Integer.valueOf(key), clientMessage);
/* 371:343 */         Composer.add(Integer.valueOf(friendship.size()), clientMessage);
/* 372:344 */         Iterator localIterator2 = friendship.values().iterator();
/* 373:344 */         if (localIterator2.hasNext())
/* 374:    */         {
/* 375:344 */           MessengerFriend friend = (MessengerFriend)localIterator2.next();
/* 376:345 */           PlayerData friendPlayer = Clients.getPlayerData(friend.userId);
/* 377:346 */           Composer.add(Integer.valueOf(friendPlayer.userId), clientMessage);
/* 378:347 */           Composer.add(friendPlayer.userName, clientMessage);
/* 379:348 */           Composer.add(friendPlayer.avatarLook.toString(), clientMessage);
/* 380:    */         }
/* 381:    */       }
/* 382:    */     }
/* 383:    */     else
/* 384:    */     {
/* 385:355 */       DBResult result1 = new DBResult();
/* 386:356 */       DBResult result2 = new DBResult();
/* 387:357 */       int i = 0;
/* 388:    */       try
/* 389:    */       {
/* 390:359 */         Database.query(result1, "SELECT friend_id,type,count(type) AS typeCount FROM user_friends WHERE user_id = " + this.playerData.userId + " GROUP BY(type);", new Object[0]);
/* 391:360 */         Composer.add(clientMessage.setSaved(Integer.valueOf(0)), clientMessage);
/* 392:361 */         while (result1.data.next())
/* 393:    */         {
/* 394:362 */           PlayerData plr = Clients.getPlayerDataFast(result1.data.getInt("friend_id"), result2);
/* 395:363 */           if (plr != null)
/* 396:    */           {
/* 397:367 */             Composer.add(Integer.valueOf(result1.data.getInt("type")), clientMessage);
/* 398:368 */             Composer.add(Integer.valueOf(result1.data.getInt("typeCount")), clientMessage);
/* 399:369 */             Composer.add(Integer.valueOf(plr.userId), clientMessage);
/* 400:370 */             Composer.add(plr.userName, clientMessage);
/* 401:371 */             Composer.add(plr.avatarLook.toString(), clientMessage);
/* 402:372 */             i++;
/* 403:    */           }
/* 404:    */         }
/* 405:374 */         clientMessage.writeSaved(Integer.valueOf(i));
/* 406:    */       }
/* 407:    */       catch (Exception ex)
/* 408:    */       {
/* 409:376 */         Log.printException("PlayerMessenger", ex);
/* 410:    */       }
/* 411:378 */       result1.close();
/* 412:379 */       result2.close();
/* 413:    */     }
/* 414:    */   }
/* 415:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.messenger.PlayerMessenger
 * JD-Core Version:    0.7.0.1
 */