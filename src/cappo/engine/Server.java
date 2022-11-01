/*   1:    */ package cappo.engine;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.DBResult;
/*   4:    */ import cappo.engine.database.Database;
/*   5:    */ import cappo.engine.logging.Log;
/*   6:    */ import cappo.engine.network.CappoServer;
/*   7:    */ import cappo.engine.network.FactorialServerHandler;
/*   8:    */ import cappo.engine.threadpools.WorkerTasks;
/*   9:    */ import cappo.game.catalog.Catalog;
/*  10:    */ import cappo.game.collections.BaseItem;
/*  11:    */ import cappo.game.collections.Utils;
/*  12:    */ import cappo.game.games.GamesManager;
/*  13:    */ import cappo.game.landing.LandingNews;
/*  14:    */ import cappo.game.navigator.NavigatorCategories;
/*  15:    */ import cappo.game.navigator.officialrooms.OfficialRooms;
/*  16:    */ import cappo.game.player.RightsManager;
/*  17:    */ import cappo.game.polls.PollManager;
/*  18:    */ import cappo.game.roomengine.RoomManager;
/*  19:    */ import cappo.game.roomengine.chat.wf.WordFilter;
/*  20:    */ import cappo.game.sound.trax.Trax;
/*  21:    */ import cappo.protocol.messages.OpCodesManager;
/*  22:    */ import java.io.PrintStream;
/*  23:    */ import java.rmi.Naming;
/*  24:    */ import java.rmi.RMISecurityManager;
/*  25:    */ import java.rmi.registry.LocateRegistry;
/*  26:    */ import java.rmi.registry.Registry;
/*  27:    */ import java.rmi.server.UnicastRemoteObject;
/*  28:    */ import java.sql.ResultSet;
/*  29:    */ import java.text.SimpleDateFormat;
/*  30:    */ import java.util.Properties;
/*  31:    */ 
/*  32:    */ public final class Server
/*  33:    */ {
/*  34: 39 */   public static SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
/*  35: 40 */   public static SimpleDateFormat date2 = new SimpleDateFormat("dd.MMM.yyyy HH:mm");
/*  36:    */   private static Integer ItemIdCount;
/*  37:    */   private static Integer RoomIdCount;
/*  38:    */   private static Integer badgeIdCount;
/*  39:    */   private static ShutdownInstance rmiShutdown;
/*  40:    */   private static Registry rmiRegistry;
/*  41:    */   public static boolean blockFF;
/*  42:    */   public static boolean blockMysql;
/*  43:    */   public static boolean blockTickets;
/*  44:    */   public static String serverId;
/*  45:    */   public static String ssoSecretKey;
/*  46:    */   public static String mysqlDB;
/*  47:    */   public static String fastfoodIP;
/*  48:    */   public static String fastfoodPORT;
/*  49:    */   public static Integer automaticGiveCredits;
/*  50:    */   public static Integer automaticGiveDuckets;
/*  51: 59 */   private static Integer refItemIdCount = Integer.valueOf(1);
/*  52:    */   
/*  53:    */   public static int generateRefItemId()
/*  54:    */   {
/*  55: 61 */     synchronized (refItemIdCount)
/*  56:    */     {
/*  57: 62 */       refItemIdCount = Integer.valueOf(refItemIdCount.intValue() + 1);
/*  58: 63 */       return refItemIdCount.intValue();
/*  59:    */     }
/*  60:    */   }
/*  61:    */   
/*  62:    */   public static int generateItemId()
/*  63:    */   {
/*  64: 68 */     synchronized (ItemIdCount)
/*  65:    */     {
/*  66: 69 */       ItemIdCount = Integer.valueOf(ItemIdCount.intValue() + 1);
/*  67: 70 */       return ItemIdCount.intValue();
/*  68:    */     }
/*  69:    */   }
/*  70:    */   
/*  71:    */   public static int generateRoomId()
/*  72:    */   {
/*  73: 75 */     synchronized (RoomIdCount)
/*  74:    */     {
/*  75: 76 */       RoomIdCount = Integer.valueOf(RoomIdCount.intValue() + 1);
/*  76: 77 */       return RoomIdCount.intValue();
/*  77:    */     }
/*  78:    */   }
/*  79:    */   
/*  80:    */   public static int generateBadgeId()
/*  81:    */   {
/*  82: 82 */     synchronized (badgeIdCount)
/*  83:    */     {
/*  84: 83 */       badgeIdCount = Integer.valueOf(badgeIdCount.intValue() + 1);
/*  85: 84 */       return badgeIdCount.intValue();
/*  86:    */     }
/*  87:    */   }
/*  88:    */   
/*  89:    */   public static void main(String[] args)
/*  90:    */     throws Exception
/*  91:    */   {
/*  92: 89 */     if (args.length < 1) {
/*  93: 90 */       return;
/*  94:    */     }
/*  95:    */     try
/*  96:    */     {
/*  97: 94 */       serverId = args[0];
/*  98: 96 */       if (args.length > 1)
/*  99:    */       {
/* 100: 98 */         System.setSecurityManager(new RMISecurityManager());
/* 101:    */         try
/* 102:    */         {
/* 103:101 */           RemoteCommand shutdown = (RemoteCommand)Naming.lookup("rmi://127.0.0.1/" + args[1] + "_" + serverId);
/* 104:102 */           System.out.println(shutdown.exec());
/* 105:    */         }
/* 106:    */         catch (Exception ex)
/* 107:    */         {
/* 108:105 */           rmiRegistry = LocateRegistry.getRegistry();
/* 109:106 */           rmiRegistry.unbind("shutdown_" + serverId);
/* 110:    */         }
/* 111:108 */         return;
/* 112:    */       }
/* 113:112 */       rmiRegistry = LocateRegistry.getRegistry();
/* 114:113 */       rmiShutdown = new ShutdownInstance();
/* 115:114 */       rmiRegistry.rebind("shutdown_" + serverId, rmiShutdown);
/* 116:    */       
/* 117:116 */       Properties props = new Properties();
/* 118:117 */       props.load(Server.class.getClassLoader().getResourceAsStream("Cappo.properties"));
/* 119:118 */       cappo.game.utils.lang.LangTexts.texts[0] = props.getProperty("lang.1", "�Has recibido ");
/* 120:119 */       cappo.game.utils.lang.LangTexts.texts[1] = props.getProperty("lang.2", " Cr�ditos!");
/* 121:120 */       cappo.game.utils.lang.LangTexts.texts[2] = props.getProperty("lang.3", " Duckets!");
/* 122:121 */       cappo.game.utils.lang.LangTexts.texts[3] = props.getProperty("lang.4", "Lo sentimos, este modelo de sala no esta disponible.");
/* 123:122 */       cappo.game.utils.lang.LangTexts.texts[4] = props.getProperty("lang.5", "�El nombre de la sala es muy corto!");
/* 124:123 */       cappo.game.utils.lang.LangTexts.texts[5] = props.getProperty("lang.6", "Bienvenido a Lavvos Beta!!");
/* 125:124 */       cappo.game.utils.lang.LangTexts.texts[6] = props.getProperty("lang.7", "Inventory is Full!");
/* 126:125 */       cappo.game.utils.lang.LangTexts.texts[7] = props.getProperty("lang.8", "�Eh!, levanta el pie. Tu tambi�n puedes hacer que Lavvos sea un lugar m�s c�modo para tod@s. Por Favor, respeta la Manera Lavvos.");
/* 127:126 */       cappo.game.utils.lang.LangTexts.texts[8] = props.getProperty("lang.9", "�Por qu� publicas otros hoteles? �No has visto nuestra �ltimas novedades? �El Fastfood te ha parecido poco? Te invito a que descubras un sin fin de cosas nuevas que OTROS no tienen. �Lavvos tu mejor opcion!.");
/* 128:127 */       cappo.game.utils.lang.LangTexts.texts[9] = props.getProperty("lang.10", " Diamonds!");
/* 129:    */       
/* 130:129 */       ServerProps.STATUS = true;
/* 131:    */       
/* 132:131 */       fastfoodIP = props.getProperty("ff.ip", "ff.lavvos.pl");
/* 133:132 */       fastfoodPORT = props.getProperty("ff.port", "30002");
/* 134:133 */       blockFF = props.getProperty("ff.block", "false").equals("true");
/* 135:134 */       blockMysql = props.getProperty("mysql.block", "false").equals("true");
/* 136:135 */       blockTickets = props.getProperty("game.tickets.block", "false").equals("true");
/* 137:    */       
/* 138:137 */       ssoSecretKey = props.getProperty("sso.secretkey", "log#in#key");
/* 139:138 */       mysqlDB = props.getProperty("mysql.db");
/* 140:    */       
/* 141:140 */       automaticGiveCredits = Integer.valueOf(Integer.parseInt(props.getProperty("game.give.credits", "0")));
/* 142:141 */       automaticGiveDuckets = Integer.valueOf(Integer.parseInt(props.getProperty("game.give.ducks", "0")));
/* 143:    */       
/* 144:143 */       int port = Integer.parseInt(props.getProperty("port.game"));
/* 145:    */       
/* 146:145 */       Log.Init(props.getProperty("log.infile", "false").equals("false"), date2.format(Utils.GetDateNow()));
/* 147:146 */       Log.printLog("Starting Cappo 2.0.0");
/* 148:    */       
/* 149:148 */       OpCodesManager.init();
/* 150:    */       
/* 151:150 */       Database.Init(props.getProperty("mysql.host"), props.getProperty("mysql.port"), mysqlDB, props.getProperty("mysql.user"), props.getProperty("mysql.pass"));
/* 152:    */       
/* 153:152 */       DBResult result = new DBResult();
/* 154:153 */       PollManager.load(result);
/* 155:154 */       RightsManager.load(result);
/* 156:155 */       LandingNews.Init(result);
/* 157:156 */       NavigatorCategories.Init(result);
/* 158:157 */       OfficialRooms.init(result);
/* 159:158 */       RoomManager.Init(result);
/* 160:159 */       Trax.Init(result);
/* 161:160 */       BaseItem.Init(result);
/* 162:161 */       Catalog.Init(result);
/* 163:162 */       WordFilter.init(result);
/* 164:    */       
/* 165:164 */       Database.query(result, "SELECT item_id FROM items ORDER BY item_id DESC LIMIT 1;", new Object[0]);
/* 166:165 */       ItemIdCount = Integer.valueOf(result.data.next() ? result.data.getInt("item_id") : 0);
/* 167:    */       
/* 168:167 */       Database.query(result, "SELECT id FROM furnis ORDER BY id DESC LIMIT 1;", new Object[0]);
/* 169:168 */       ItemIdCount = Integer.valueOf(result.data.next() ? 
/* 170:169 */         ItemIdCount.intValue() : result.data.getInt("id") > ItemIdCount.intValue() ? result.data.getInt("id") : 
/* 171:170 */         0);
/* 172:    */       
/* 173:172 */       Database.query(result, "SELECT id FROM `rooms` ORDER BY `id` DESC LIMIT 1;", new Object[0]);
/* 174:173 */       RoomIdCount = Integer.valueOf(result.data.next() ? result.data.getInt("id") : 0);
/* 175:174 */       Database.query(result, "SELECT id FROM `user_badges` ORDER BY `id` DESC LIMIT 1;", new Object[0]);
/* 176:175 */       badgeIdCount = Integer.valueOf(result.data.next() ? result.data.getInt("id") : 0);
/* 177:    */       
/* 178:    */ 
/* 179:178 */       result.close();
/* 180:    */       
/* 181:    */ 
/* 182:    */ 
/* 183:182 */       int serverType = 1;
/* 184:    */       try
/* 185:    */       {
/* 186:184 */         String type = props.getProperty("game.type");
/* 187:185 */         if (type != null) {
/* 188:186 */           serverType = Integer.parseInt(type);
/* 189:    */         }
/* 190:    */       }
/* 191:    */       catch (Exception localException1) {}
/* 192:190 */       WorkerTasks.initWorkers(serverType);
/* 193:    */       
/* 194:    */ 
/* 195:193 */       Thread t = new Thread(new ServerTasks());
/* 196:194 */       t.setPriority(1);
/* 197:195 */       t.start();
/* 198:    */       
/* 199:197 */       GamesManager.initManager();
/* 200:    */       
/* 201:    */ 
/* 202:200 */       new CappoServer(port).run();
/* 203:    */     }
/* 204:    */     catch (Exception ex)
/* 205:    */     {
/* 206:202 */       ex.printStackTrace();
/* 207:    */       
/* 208:    */ 
/* 209:205 */       rmiRegistry.unbind("shutdown_" + serverId);
/* 210:206 */       return;
/* 211:    */     }
/* 212:209 */     while (ServerProps.STATUS) {
/* 213:    */       try
/* 214:    */       {
/* 215:211 */         Thread.sleep(1000L);
/* 216:    */       }
/* 217:    */       catch (Exception localException2) {}
/* 218:    */     }
/* 219:218 */     Utils.AlertFromHotel(FactorialServerHandler.channels, "Disconnecting: Server Shutting Down");
/* 220:    */     
/* 221:220 */     Log.printLog("Server: closing..");
/* 222:    */     try
/* 223:    */     {
/* 224:223 */       rmiRegistry.unbind("shutdown_" + serverId);
/* 225:224 */       UnicastRemoteObject.unexportObject(rmiShutdown, false);
/* 226:    */     }
/* 227:    */     catch (Exception ex)
/* 228:    */     {
/* 229:226 */       Log.printException("closing", ex);
/* 230:    */     }
/* 231:230 */     Log.printLog("Flushing Sockets");
/* 232:    */     
/* 233:232 */     CappoServer.shutdown();
/* 234:    */   }
/* 235:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.Server
 * JD-Core Version:    0.7.0.1
 */