/*   1:    */ package cappo.engine;
/*   2:    */ 
/*   3:    */ import cappo.engine.logging.Log;
/*   4:    */ import cappo.engine.network.FactorialServerHandler;
/*   5:    */ import cappo.engine.network.MessageWriter;
/*   6:    */ import cappo.engine.network.QueueWriter;
/*   7:    */ import cappo.engine.player.Clients;
/*   8:    */ import cappo.engine.player.Connection;
/*   9:    */ import cappo.engine.tasks.OnlineCounter;
/*  10:    */ import cappo.engine.tasks.OnlineCounterGrapth;
/*  11:    */ import cappo.game.collections.Utils;
/*  12:    */ import cappo.game.player.PlayerData;
/*  13:    */ import cappo.game.roomengine.RoomData;
/*  14:    */ import cappo.game.roomengine.RoomManager;
/*  15:    */ import cappo.protocol.messages.OpCodesManager;
/*  16:    */ import cappo.protocol.messages.composers.handshake.ConnectionPingComposer;
/*  17:    */ import cappo.protocol.messages.composers.inventory.purse.CreditBalanceComposer;
/*  18:    */ import cappo.protocol.messages.composers.notifications.HabboActivityPointNotificationComposer;
/*  19:    */ import io.netty.channel.Channel;
/*  20:    */ import io.netty.channel.group.DefaultChannelGroup;
/*  21:    */ import io.netty.util.Attribute;
/*  22:    */ import java.util.Iterator;
/*  23:    */ import java.util.Map;
/*  24:    */ import java.util.Set;
/*  25:    */ import java.util.concurrent.ScheduledThreadPoolExecutor;
/*  26:    */ import java.util.concurrent.TimeUnit;
/*  27:    */ 
/*  28:    */ public class ServerTasks
/*  29:    */   extends Thread
/*  30:    */ {
/*  31: 36 */   private static int counter1 = 0;
/*  32: 37 */   private static int counter2 = 0;
/*  33: 38 */   private static int counter3 = 0;
/*  34: 40 */   public static final MessageWriter PingMessage = ConnectionPingComposer.compose();
/*  35:    */   
/*  36:    */   private static void cleanClients()
/*  37:    */   {
/*  38:    */     try
/*  39:    */     {
/*  40: 44 */       long notused = Utils.getTimestamp() - 1800L;
/*  41:    */       
/*  42: 46 */       Object[] keys = Clients.GetClients().keySet().toArray();
/*  43:    */       
/*  44: 48 */       int loops = keys.length / 10 + 1;
/*  45: 49 */       int a = 0;
/*  46:    */       do
/*  47:    */       {
/*  48:    */         try
/*  49:    */         {
/*  50: 52 */           Thread.sleep(20L);
/*  51:    */         }
/*  52:    */         catch (Exception localException1) {}
/*  53: 55 */         int end = a + 10;
/*  54: 56 */         if (end > keys.length) {
/*  55: 57 */           end = keys.length;
/*  56:    */         }
/*  57: 60 */         for (; a < end; a++)
/*  58:    */         {
/*  59: 61 */           PlayerData current = Clients.getPlayerData(((Integer)keys[a]).intValue());
/*  60: 62 */           if (current != null)
/*  61:    */           {
/*  62: 66 */             Connection cn = current.connection;
/*  63: 67 */             if (cn == null)
/*  64:    */             {
/*  65: 68 */               if (current.LastUsedThis < notused) {
/*  66: 69 */                 Clients.deleteID(current.userId);
/*  67:    */               }
/*  68:    */             }
/*  69:    */             else {
/*  70:    */               try
/*  71:    */               {
/*  72: 75 */                 if (cn.haveFlag(2))
/*  73:    */                 {
/*  74: 76 */                   QueueWriter.writeAndFlush(cn.socket, PingMessage);
/*  75: 77 */                   cn.setFlag(2, false);
/*  76:    */                 }
/*  77:    */               }
/*  78:    */               catch (Exception ex)
/*  79:    */               {
/*  80: 81 */                 Log.printException("", ex);
/*  81:    */               }
/*  82:    */             }
/*  83:    */           }
/*  84:    */         }
/*  85: 50 */         loops--;
/*  86: 50 */       } while (loops >= 0);
/*  87:    */     }
/*  88:    */     catch (Exception ex)
/*  89:    */     {
/*  90: 88 */       Log.printException("ServerTasks", ex);
/*  91:    */     }
/*  92:    */   }
/*  93:    */   
/*  94:    */   private static void cleanRooms()
/*  95:    */   {
/*  96:    */     try
/*  97:    */     {
/*  98: 94 */       long notused = Utils.getTimestamp() - 3600L;
/*  99:    */       
/* 100: 96 */       Map<Integer, RoomData> rooms = RoomManager.GetRooms();
/* 101: 97 */       Object[] keys = rooms.keySet().toArray();
/* 102:    */       
/* 103: 99 */       int loops = keys.length / 10 + 1;
/* 104:100 */       int a = 0;
/* 105:    */       do
/* 106:    */       {
/* 107:    */         try
/* 108:    */         {
/* 109:103 */           Thread.sleep(20L);
/* 110:    */         }
/* 111:    */         catch (Exception localException1) {}
/* 112:106 */         int end = a + 10;
/* 113:107 */         if (end > keys.length) {
/* 114:108 */           end = keys.length;
/* 115:    */         }
/* 116:111 */         for (; a < end; a++)
/* 117:    */         {
/* 118:112 */           RoomData current = (RoomData)rooms.get(Integer.valueOf(((Integer)keys[a]).intValue()));
/* 119:113 */           if (current != null) {
/* 120:118 */             if (current.room == null) {
/* 121:123 */               if (current.lastUsedThis <= notused) {
/* 122:128 */                 if ((current.roomOwner == null) || (current.roomOwner.connection == null)) {
/* 123:132 */                   RoomManager.unloadRoom(current.roomId);
/* 124:    */                 }
/* 125:    */               }
/* 126:    */             }
/* 127:    */           }
/* 128:    */         }
/* 129:101 */         loops--;
/* 130:101 */       } while (loops >= 0);
/* 131:    */     }
/* 132:    */     catch (Exception ex)
/* 133:    */     {
/* 134:137 */       Log.printException("ServerTasks", ex);
/* 135:    */     }
/* 136:    */   }
/* 137:    */   
/* 138:    */   private static void giveCredits()
/* 139:    */   {
/* 140:    */     try
/* 141:    */     {
/* 142:143 */       int credits = Server.automaticGiveCredits.intValue();
/* 143:144 */       int ducks = Server.automaticGiveDuckets.intValue();
/* 144:145 */       if ((credits > 0) || (ducks > 0)) {
/* 145:146 */         if ((credits > 0) && (ducks > 0))
/* 146:    */         {
/* 147:147 */           Iterator<Channel> itr = FactorialServerHandler.channels.iterator();
/* 148:149 */           while (itr.hasNext())
/* 149:    */           {
/* 150:150 */             Channel ch = (Channel)itr.next();
/* 151:151 */             Connection con = (Connection)ch.attr(FactorialServerHandler.CONNECTION).get();
/* 152:153 */             if (con.avatarData != null)
/* 153:    */             {
/* 154:157 */               QueueWriter.writeAndFlush(ch, CreditBalanceComposer.compose(con.credits += credits));
/* 155:158 */               QueueWriter.writeAndFlush(ch, HabboActivityPointNotificationComposer.compose(con.pixelAmmount += ducks, ducks, 0));
/* 156:    */             }
/* 157:    */           }
/* 158:161 */           Utils.AlertFromHotel(FactorialServerHandler.channels, cappo.game.utils.lang.LangTexts.texts[0] + credits + cappo.game.utils.lang.LangTexts.texts[1] + "\n" + cappo.game.utils.lang.LangTexts.texts[0] + ducks + cappo.game.utils.lang.LangTexts.texts[2] + "\n\n");
/* 159:    */         }
/* 160:162 */         else if (credits > 0)
/* 161:    */         {
/* 162:163 */           Iterator<Channel> itr = FactorialServerHandler.channels.iterator();
/* 163:165 */           while (itr.hasNext())
/* 164:    */           {
/* 165:166 */             Channel ch = (Channel)itr.next();
/* 166:167 */             Connection con = (Connection)ch.attr(FactorialServerHandler.CONNECTION).get();
/* 167:169 */             if (con.avatarData != null) {
/* 168:173 */               QueueWriter.writeAndFlush(ch, CreditBalanceComposer.compose(con.credits += credits));
/* 169:    */             }
/* 170:    */           }
/* 171:176 */           Utils.AlertFromHotel(FactorialServerHandler.channels, cappo.game.utils.lang.LangTexts.texts[0] + credits + cappo.game.utils.lang.LangTexts.texts[1] + "\n\n");
/* 172:    */         }
/* 173:177 */         else if (ducks > 0)
/* 174:    */         {
/* 175:178 */           Iterator<Channel> itr = FactorialServerHandler.channels.iterator();
/* 176:180 */           while (itr.hasNext())
/* 177:    */           {
/* 178:181 */             Channel ch = (Channel)itr.next();
/* 179:182 */             Connection con = (Connection)ch.attr(FactorialServerHandler.CONNECTION).get();
/* 180:184 */             if (con.avatarData != null) {
/* 181:188 */               QueueWriter.writeAndFlush(ch, HabboActivityPointNotificationComposer.compose(con.pixelAmmount += ducks, ducks, 0));
/* 182:    */             }
/* 183:    */           }
/* 184:191 */           Utils.AlertFromHotel(FactorialServerHandler.channels, cappo.game.utils.lang.LangTexts.texts[0] + ducks + cappo.game.utils.lang.LangTexts.texts[2] + "\n\n");
/* 185:    */         }
/* 186:    */       }
/* 187:    */     }
/* 188:    */     catch (Exception ex)
/* 189:    */     {
/* 190:196 */       Log.printException("ServerTasks", ex);
/* 191:    */     }
/* 192:    */   }
/* 193:    */   
/* 194:    */   public void run()
/* 195:    */   {
/* 196:202 */     ScheduledThreadPoolExecutor statsWorker = new ScheduledThreadPoolExecutor(1);
/* 197:203 */     statsWorker.scheduleAtFixedRate(new OnlineCounter(), 60L, 5L, TimeUnit.SECONDS);
/* 198:204 */     statsWorker.scheduleAtFixedRate(new OnlineCounterGrapth(), 60L, 60L, TimeUnit.SECONDS);
/* 199:    */     do
/* 200:    */     {
/* 201:    */       try
/* 202:    */       {
/* 203:208 */         Thread.sleep(5000L);
/* 204:    */         
/* 205:210 */         OpCodesManager.checkComposerOverrides();
/* 206:211 */         OpCodesManager.checkParserOverrides();
/* 207:213 */         if (counter1++ > 555)
/* 208:    */         {
/* 209:215 */           counter1 = 0;
/* 210:    */           
/* 211:217 */           new Thread()
/* 212:    */           {
/* 213:    */             public void run() {}
/* 214:    */           }.start();
/* 215:    */         }
/* 216:225 */         if (counter2++ > 200)
/* 217:    */         {
/* 218:227 */           counter2 = 0;
/* 219:    */           
/* 220:229 */           new Thread()
/* 221:    */           {
/* 222:    */             public void run() {}
/* 223:    */           }.start();
/* 224:    */         }
/* 225:237 */         if (counter3++ > 200)
/* 226:    */         {
/* 227:239 */           counter3 = 0;
/* 228:    */           
/* 229:241 */           new Thread()
/* 230:    */           {
/* 231:    */             public void run() {}
/* 232:    */           }.start();
/* 233:    */         }
/* 234:    */       }
/* 235:    */       catch (Exception ex)
/* 236:    */       {
/* 237:250 */         Log.printException("ServerTasks", ex);
/* 238:    */       }
/* 239:252 */     } while (ServerProps.STATUS);
/* 240:254 */     statsWorker.shutdown();
/* 241:    */   }
/* 242:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.ServerTasks
 * JD-Core Version:    0.7.0.1
 */