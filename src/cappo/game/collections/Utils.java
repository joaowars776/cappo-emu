/*  1:   */ package cappo.game.collections;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.protocol.messages.composers.moderation.ModMessageComposer;
/*  5:   */ import cappo.protocol.messages.composers.notifications.BroadcastImageComposer;
/*  6:   */ import cappo.protocol.messages.composers.notifications.HabboBroadcastComposer;
/*  7:   */ import io.netty.channel.Channel;
/*  8:   */ import io.netty.channel.group.DefaultChannelGroup;
/*  9:   */ import java.util.Date;
/* 10:   */ import java.util.Random;
/* 11:   */ 
/* 12:   */ public class Utils
/* 13:   */ {
/* 14:22 */   private static Date now = new Date();
/* 15:24 */   static Random ran = new Random();
/* 16:   */   
/* 17:   */   public static Date GetDate(long t)
/* 18:   */   {
/* 19:27 */     return new Date(t);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public static Date GetDateNow()
/* 23:   */   {
/* 24:31 */     now.setTime(System.currentTimeMillis());
/* 25:32 */     return now;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public static int GetRandomNumber(int min, int max)
/* 29:   */   {
/* 30:36 */     if (min < 0)
/* 31:   */     {
/* 32:37 */       min *= -1;
/* 33:38 */       return (int)(ran.nextDouble() * (max + min)) - min;
/* 34:   */     }
/* 35:41 */     max++;
/* 36:   */     
/* 37:43 */     return (min + (int)(ran.nextDouble() * (max - min))) % max;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public static long getTimestamp()
/* 41:   */   {
/* 42:47 */     return System.currentTimeMillis() / 1000L;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public static boolean isBadInteger(String i)
/* 46:   */   {
/* 47:   */     try
/* 48:   */     {
/* 49:52 */       Integer.parseInt(i);
/* 50:53 */       return false;
/* 51:   */     }
/* 52:   */     catch (NumberFormatException ex) {}
/* 53:55 */     return true;
/* 54:   */   }
/* 55:   */   
/* 56:   */   public static String convertToHex(byte[] data)
/* 57:   */   {
/* 58:61 */     StringBuilder buf = new StringBuilder(50);
/* 59:62 */     byte[] arrayOfByte = data;int j = data.length;
/* 60:62 */     for (int i = 0; i < j; i++)
/* 61:   */     {
/* 62:62 */       byte element = arrayOfByte[i];
/* 63:63 */       int halfbyte = element >>> 4 & 0xF;
/* 64:64 */       int two_halfs = 0;
/* 65:   */       do
/* 66:   */       {
/* 67:66 */         if ((halfbyte >= 0) && (halfbyte <= 9)) {
/* 68:67 */           buf.append((char)(48 + halfbyte));
/* 69:   */         } else {
/* 70:69 */           buf.append((char)(97 + (halfbyte - 10)));
/* 71:   */         }
/* 72:71 */         halfbyte = element & 0xF;
/* 73:65 */       } while (
/* 74:   */       
/* 75:   */ 
/* 76:   */ 
/* 77:   */ 
/* 78:   */ 
/* 79:   */ 
/* 80:72 */         two_halfs++ < 1);
/* 81:   */     }
/* 82:74 */     return buf.toString();
/* 83:   */   }
/* 84:   */   
/* 85:   */   public static void AlertFromStaffOld(Channel Socket, String Text, String Link)
/* 86:   */   {
/* 87:79 */     QueueWriter.writeAndFlush(Socket, ModMessageComposer.compose(Text, Link));
/* 88:   */   }
/* 89:   */   
/* 90:   */   public static void AlertFromHotel(Channel Socket, String Text)
/* 91:   */   {
/* 92:83 */     QueueWriter.writeAndFlush(Socket, HabboBroadcastComposer.compose(Text));
/* 93:   */   }
/* 94:   */   
/* 95:   */   public static void AlertFromHotel(DefaultChannelGroup group, String Text)
/* 96:   */   {
/* 97:87 */     group.writeAndFlush(HabboBroadcastComposer.compose(Text));
/* 98:   */   }
/* 99:   */   
/* :0:   */   public static void broadcastImage(Channel socket, String uri)
/* :1:   */   {
/* :2:91 */     QueueWriter.writeAndFlush(socket, BroadcastImageComposer.compose(uri));
/* :3:   */   }
/* :4:   */   
/* :5:   */   public static void broadcastImage(DefaultChannelGroup group, String uri)
/* :6:   */   {
/* :7:95 */     group.writeAndFlush(BroadcastImageComposer.compose(uri));
/* :8:   */   }
/* :9:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.collections.Utils
 * JD-Core Version:    0.7.0.1
 */