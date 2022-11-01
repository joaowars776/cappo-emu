/*   1:    */ package cappo.engine.player;
/*   2:    */ 
/*   3:    */ import cappo.engine.Server;
/*   4:    */ import cappo.engine.database.DBResult;
/*   5:    */ import cappo.engine.database.Database;
/*   6:    */ import cappo.engine.logging.Log;
/*   7:    */ import cappo.game.bots.RentalBot;
/*   8:    */ import cappo.game.collections.Utils;
/*   9:    */ import cappo.game.pets.Pet;
/*  10:    */ import cappo.game.player.AvatarLook;
/*  11:    */ import cappo.game.player.PlayerData;
/*  12:    */ import java.sql.ResultSet;
/*  13:    */ import java.text.SimpleDateFormat;
/*  14:    */ import java.util.Date;
/*  15:    */ import java.util.Map;
/*  16:    */ import java.util.concurrent.ConcurrentHashMap;
/*  17:    */ 
/*  18:    */ public class Clients
/*  19:    */ {
/*  20: 25 */   private static Map<Integer, PlayerData> clients = new ConcurrentHashMap(
/*  21: 26 */     400, 
/*  22: 27 */     0.8F, 
/*  23: 28 */     8);
/*  24: 32 */   private static Map<String, PlayerData> clientsByName = new ConcurrentHashMap(
/*  25: 33 */     400, 
/*  26: 34 */     0.8F, 
/*  27: 35 */     8);
/*  28:    */   private static int onlineUsers;
/*  29:    */   
/*  30:    */   public static void addPlayerData(PlayerData Client)
/*  31:    */   {
/*  32: 41 */     Client.LastUsedThis = Utils.getTimestamp();
/*  33: 42 */     clients.put(Integer.valueOf(Client.userId), Client);
/*  34: 43 */     clientsByName.put(Client.userName, Client);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public static void deleteID(int ClientId)
/*  38:    */   {
/*  39: 47 */     PlayerData Client = (PlayerData)clients.remove(Integer.valueOf(ClientId));
/*  40: 48 */     if (Client != null) {
/*  41: 49 */       clientsByName.remove(Client.userName);
/*  42:    */     }
/*  43:    */   }
/*  44:    */   
/*  45:    */   public static Map<Integer, PlayerData> GetClients()
/*  46:    */   {
/*  47: 54 */     return clients;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public static int GetLoadedCount()
/*  51:    */   {
/*  52: 58 */     return clients.size();
/*  53:    */   }
/*  54:    */   
/*  55:    */   public static int GetOnlineCount()
/*  56:    */   {
/*  57: 62 */     return onlineUsers;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public static void setOnline(boolean online)
/*  61:    */   {
/*  62: 66 */     if (online) {
/*  63: 67 */       onlineUsers += 1;
/*  64:    */     } else {
/*  65: 69 */       onlineUsers -= 1;
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   public static PlayerData getPlayerDataLoaded(int clientId)
/*  70:    */   {
/*  71: 74 */     if (clientId < 1) {
/*  72: 75 */       return null;
/*  73:    */     }
/*  74: 78 */     PlayerData p = (PlayerData)clients.get(Integer.valueOf(clientId));
/*  75: 80 */     if (p != null) {
/*  76: 81 */       p.LastUsedThis = Utils.getTimestamp();
/*  77:    */     }
/*  78: 83 */     return p;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public static PlayerData getPlayerDataLoaded(String clientName)
/*  82:    */   {
/*  83: 87 */     if (clientName.isEmpty()) {
/*  84: 88 */       return null;
/*  85:    */     }
/*  86: 91 */     PlayerData p = (PlayerData)clientsByName.get(clientName);
/*  87: 93 */     if (p != null) {
/*  88: 94 */       p.LastUsedThis = Utils.getTimestamp();
/*  89:    */     }
/*  90: 96 */     return p;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public static PlayerData getPlayerDataFast(int clientId, DBResult result)
/*  94:    */   {
/*  95:100 */     if (clientId < 1) {
/*  96:101 */       return null;
/*  97:    */     }
/*  98:104 */     PlayerData p = (PlayerData)clients.get(Integer.valueOf(clientId));
/*  99:105 */     if (p == null) {
/* 100:106 */       p = getPlayerData(clientId, result);
/* 101:    */     }
/* 102:109 */     if (p != null) {
/* 103:110 */       p.LastUsedThis = Utils.getTimestamp();
/* 104:    */     }
/* 105:112 */     return p;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public static PlayerData getPlayerData(int clientId)
/* 109:    */   {
/* 110:116 */     if (clientId < 1) {
/* 111:117 */       return null;
/* 112:    */     }
/* 113:120 */     PlayerData p = (PlayerData)clients.get(Integer.valueOf(clientId));
/* 114:121 */     if (p == null)
/* 115:    */     {
/* 116:122 */       DBResult result = new DBResult();
/* 117:123 */       p = getPlayerData(clientId, result);
/* 118:124 */       result.close();
/* 119:    */     }
/* 120:127 */     if (p != null) {
/* 121:128 */       p.LastUsedThis = Utils.getTimestamp();
/* 122:    */     }
/* 123:130 */     return p;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public static PlayerData getPlayerData(String UserName)
/* 127:    */   {
/* 128:134 */     if ((UserName == null) || (UserName.isEmpty())) {
/* 129:135 */       return null;
/* 130:    */     }
/* 131:138 */     PlayerData p = (PlayerData)clientsByName.get(UserName);
/* 132:139 */     if (p == null)
/* 133:    */     {
/* 134:140 */       DBResult result = new DBResult();
/* 135:141 */       p = getPlayerData(UserName, result);
/* 136:142 */       result.close();
/* 137:    */     }
/* 138:145 */     if (p != null) {
/* 139:146 */       p.LastUsedThis = Utils.getTimestamp();
/* 140:    */     }
/* 141:148 */     return p;
/* 142:    */   }
/* 143:    */   
/* 144:    */   private static PlayerData getPlayerData(String UserName, DBResult result)
/* 145:    */   {
/* 146:152 */     PlayerData p = null;
/* 147:    */     try
/* 148:    */     {
/* 149:154 */       Database.query(result, "SELECT user_info.*,users.id,users.rank,users.username,users.real_name,users.mail,users.look,users.achievement_points,users.gender,users.motto,users.account_created FROM users LEFT JOIN user_info ON (users.id=user_info.user_id) WHERE users.username = ? LIMIT 1;", new Object[] {
/* 150:    */       
/* 151:    */ 
/* 152:    */ 
/* 153:    */ 
/* 154:    */ 
/* 155:    */ 
/* 156:    */ 
/* 157:    */ 
/* 158:    */ 
/* 159:    */ 
/* 160:165 */         UserName });
/* 161:167 */       if (result.data.next())
/* 162:    */       {
/* 163:168 */         p = PlayerData.getPlayer(result.data.getInt("rank"));
/* 164:169 */         generatePlayerData(result, p);
/* 165:170 */         addPlayerData(p);
/* 166:    */       }
/* 167:    */     }
/* 168:    */     catch (Exception ex)
/* 169:    */     {
/* 170:173 */       Log.printException("Clients-2", ex);
/* 171:    */     }
/* 172:175 */     return p;
/* 173:    */   }
/* 174:    */   
/* 175:    */   private static PlayerData getPlayerData(int clientId, DBResult result)
/* 176:    */   {
/* 177:179 */     PlayerData p = null;
/* 178:    */     try
/* 179:    */     {
/* 180:181 */       Database.query(result, "SELECT user_info.*,users.id,users.rank,users.username,users.real_name,users.mail,users.look,users.achievement_points,users.gender,users.motto,users.account_created FROM users LEFT JOIN user_info ON (users.id=user_info.user_id) WHERE users.id = " + 
/* 181:    */       
/* 182:    */ 
/* 183:    */ 
/* 184:    */ 
/* 185:    */ 
/* 186:    */ 
/* 187:    */ 
/* 188:    */ 
/* 189:    */ 
/* 190:    */ 
/* 191:192 */         clientId + " LIMIT 1;", new Object[0]);
/* 192:194 */       if (result.data.next())
/* 193:    */       {
/* 194:195 */         p = PlayerData.getPlayer(result.data.getInt("rank"));
/* 195:196 */         generatePlayerData(result, p);
/* 196:197 */         addPlayerData(p);
/* 197:    */       }
/* 198:    */     }
/* 199:    */     catch (Exception ex)
/* 200:    */     {
/* 201:200 */       Log.printException("Clients-2", ex);
/* 202:    */     }
/* 203:202 */     return p;
/* 204:    */   }
/* 205:    */   
/* 206:    */   private static void generatePlayerData(DBResult result, PlayerData playerData)
/* 207:    */     throws Exception
/* 208:    */   {
/* 209:207 */     playerData.lastVisit = result.data.getInt("login_timestamp");
/* 210:208 */     playerData.bans = result.data.getInt("bans");
/* 211:209 */     playerData.cautions = result.data.getInt("cautions");
/* 212:210 */     playerData.cfhs = result.data.getInt("cfhs");
/* 213:211 */     playerData.cfhs_abusive = result.data.getInt("cfhs_abusive");
/* 214:    */     
/* 215:    */ 
/* 216:    */ 
/* 217:215 */     playerData.userId = result.data.getInt("id");
/* 218:216 */     playerData.userName = result.data.getString("username");
/* 219:    */     
/* 220:218 */     playerData.email = result.data.getString("mail");
/* 221:219 */     playerData.AchievementsScore = result.data.getInt("achievement_points");
/* 222:    */     
/* 223:    */ 
/* 224:222 */     String avatarLook = result.data.getString("look");
/* 225:223 */     if (!AvatarLook.validateLook(avatarLook)) {
/* 226:224 */       playerData.avatarLook = new AvatarLook();
/* 227:    */     } else {
/* 228:226 */       playerData.avatarLook = new AvatarLook(avatarLook);
/* 229:    */     }
/* 230:229 */     playerData.sex = (result.data.getString("gender").equals("M") ? 1 : 0);
/* 231:230 */     playerData.motto = result.data.getString("motto");
/* 232:231 */     String tmp = result.data.getString("account_created");
/* 233:234 */     if (tmp.isEmpty())
/* 234:    */     {
/* 235:235 */       playerData.registerDate = Utils.getTimestamp();
/* 236:236 */       Database.exec("UPDATE users SET account_created = '" + playerData.registerDate + "' WHERE id = " + playerData.userId + ";", new Object[0]);
/* 237:    */     }
/* 238:    */     else
/* 239:    */     {
/* 240:238 */       if (tmp.contains("-")) {
/* 241:239 */         tmp = tmp.replace("-", "/");
/* 242:    */       }
/* 243:242 */       if (tmp.contains("/"))
/* 244:    */       {
/* 245:    */         try
/* 246:    */         {
/* 247:244 */           Date date = Server.date.parse(tmp);
/* 248:245 */           playerData.registerDate = (date.getTime() / 1000L);
/* 249:    */         }
/* 250:    */         catch (Exception ex)
/* 251:    */         {
/* 252:248 */           playerData.registerDate = Utils.getTimestamp();
/* 253:    */         }
/* 254:250 */         Database.exec("UPDATE users SET account_created = '" + playerData.registerDate + "' WHERE id = " + playerData.userId + ";", new Object[0]);
/* 255:    */       }
/* 256:    */       else
/* 257:    */       {
/* 258:253 */         playerData.registerDate = Long.parseLong(tmp);
/* 259:    */       }
/* 260:    */     }
/* 261:    */   }
/* 262:    */   
/* 263:    */   public static Pet generatePetsData(ResultSet userdata, PlayerData playerData)
/* 264:    */     throws Exception
/* 265:    */   {
/* 266:259 */     int type = userdata.getInt("type");
/* 267:260 */     if ((type < 0) || (type > 27)) {
/* 268:261 */       return null;
/* 269:    */     }
/* 270:265 */     Pet pet = new Pet(userdata.getInt("id"), userdata.getString("name"), (short)userdata.getInt("type"), (short)Integer.parseInt(userdata.getString("race")), userdata.getString("color"));
/* 271:266 */     pet.ownerId = playerData.userId;
/* 272:267 */     pet.ownerName = playerData.userName;
/* 273:268 */     pet.TimeCreated = userdata.getInt("createstamp");
/* 274:269 */     pet.Nutrition = userdata.getInt("nutrition");
/* 275:270 */     pet.Experience = userdata.getInt("expirience");
/* 276:271 */     pet.Energy = userdata.getInt("energy");
/* 277:272 */     pet.Respects = userdata.getInt("respect");
/* 278:273 */     pet.level = 1;
/* 279:274 */     return pet;
/* 280:    */   }
/* 281:    */   
/* 282:    */   public static RentalBot generateBotsData(ResultSet userdata, PlayerData playerData)
/* 283:    */     throws Exception
/* 284:    */   {
/* 285:278 */     int type = userdata.getInt("type");
/* 286:279 */     if ((type < 0) || (type > 2)) {
/* 287:280 */       return null;
/* 288:    */     }
/* 289:283 */     RentalBot bot = new RentalBot(userdata.getInt("id"), userdata.getString("name"), (short)userdata.getInt("type"));
/* 290:284 */     bot.botLook = new AvatarLook(userdata.getString("look"));
/* 291:285 */     bot.gender = userdata.getString("gender");
/* 292:286 */     bot.motto = userdata.getString("motto");
/* 293:287 */     bot.ownerId = playerData.userId;
/* 294:288 */     bot.ownerName = playerData.userName;
/* 295:289 */     return bot;
/* 296:    */   }
/* 297:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.player.Clients
 * JD-Core Version:    0.7.0.1
 */