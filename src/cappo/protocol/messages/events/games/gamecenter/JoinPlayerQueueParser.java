/*  1:   */ package cappo.protocol.messages.events.games.gamecenter;
/*  2:   */ 
/*  3:   */ import cappo.engine.Server;
/*  4:   */ import cappo.engine.network.MessageReader;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.collections.Utils;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.games.gamecenter.JoinedPlayerQueueComposer;
/* 11:   */ import cappo.protocol.messages.composers.games.gamecenter.LoadGameComposer;
/* 12:   */ 
/* 13:   */ public class JoinPlayerQueueParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection Main)
/* 17:   */   {
/* 18:20 */     if (Server.blockFF) {
/* 19:21 */       return;
/* 20:   */     }
/* 21:24 */     int GameId = Main.currentPacket.readInt();
/* 22:25 */     QueueWriter.write(Main.socket, JoinedPlayerQueueComposer.compose(GameId));
/* 23:   */     
/* 24:27 */     String sendData = Server.mysqlDB + "-" + Integer.toString(Main.playerData.userId);
/* 25:28 */     String privKey = "$g%h&j@k";
/* 26:29 */     String pubKey = Integer.toHexString(Utils.GetRandomNumber(50000, 2147483647));
/* 27:30 */     int p = 0;
/* 28:31 */     int len = "$g%h&j@k".length();
/* 29:32 */     int len2 = pubKey.length();
/* 30:33 */     String tokenizer = "";
/* 31:34 */     for (int i = 0; i < len; i++)
/* 32:   */     {
/* 33:35 */       tokenizer = tokenizer + (char)("$g%h&j@k".charAt(i) & 0xFF ^ pubKey.charAt(p) & 0xFF);
/* 34:36 */       p++;
/* 35:36 */       if (p == len2) {
/* 36:37 */         p = 0;
/* 37:   */       }
/* 38:   */     }
/* 39:40 */     len = sendData.length();
/* 40:41 */     int len3 = tokenizer.length();
/* 41:42 */     byte[] buf = new byte[len2 + 1 + len];
/* 42:43 */     p = 0;
/* 43:44 */     for (int i = 0; i <= len2; i++) {
/* 44:45 */       if (i < len2) {
/* 45:46 */         buf[i] = ((byte)pubKey.charAt(i));
/* 46:   */       } else {
/* 47:48 */         buf[i] = 45;
/* 48:   */       }
/* 49:   */     }
/* 50:51 */     len2++;
/* 51:52 */     for (int i = 0; i < len; i++)
/* 52:   */     {
/* 53:53 */       buf[(len2 + i)] = ((byte)(sendData.charAt(i) & 0xFF ^ tokenizer.charAt(p) & 0xFF));
/* 54:54 */       p++;
/* 55:54 */       if (p == len3) {
/* 56:55 */         p = 0;
/* 57:   */       }
/* 58:   */     }
/* 59:59 */     QueueWriter.write(Main.socket, LoadGameComposer.compose(GameId, new String(buf)));
/* 60:   */   }
/* 61:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.games.gamecenter.JoinPlayerQueueParser
 * JD-Core Version:    0.7.0.1
 */