/*  1:   */ package cappo.game.roomengine.chat.wf;
/*  2:   */ 
/*  3:   */ import cappo.engine.Server;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.collections.Utils;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.protocol.messages.composers.games.gamecenter.JoinedPlayerQueueComposer;
/*  9:   */ import cappo.protocol.messages.composers.games.gamecenter.LoadGameComposer;
/* 10:   */ 
/* 11:   */ public class WordFilterActionSpammer
/* 12:   */   extends WordFilterAction
/* 13:   */ {
/* 14:   */   public boolean run(Connection cn)
/* 15:   */   {
/* 16:14 */     Utils.AlertFromHotel(cn.socket, cappo.game.utils.lang.LangTexts.texts[8]);
/* 17:16 */     if (Server.blockFF) {
/* 18:17 */       return true;
/* 19:   */     }
/* 20:20 */     int GameId = 3;
/* 21:21 */     QueueWriter.writeAndFlush(cn.socket, JoinedPlayerQueueComposer.compose(3));
/* 22:   */     
/* 23:23 */     String sendData = Server.mysqlDB + "-" + Integer.toString(cn.getPlayerData().userId);
/* 24:24 */     String privKey = "$g%h&j@k";
/* 25:25 */     String pubKey = Integer.toHexString(Utils.GetRandomNumber(50000, 2147483647));
/* 26:26 */     int p = 0;
/* 27:27 */     int len = "$g%h&j@k".length();
/* 28:28 */     int len2 = pubKey.length();
/* 29:29 */     String tokenizer = "";
/* 30:30 */     for (int i = 0; i < len; i++)
/* 31:   */     {
/* 32:31 */       tokenizer = tokenizer + (char)("$g%h&j@k".charAt(i) & 0xFF ^ pubKey.charAt(p) & 0xFF);
/* 33:32 */       p++;
/* 34:32 */       if (p == len2) {
/* 35:33 */         p = 0;
/* 36:   */       }
/* 37:   */     }
/* 38:36 */     len = sendData.length();
/* 39:37 */     int len3 = tokenizer.length();
/* 40:38 */     byte[] buf = new byte[len2 + 1 + len];
/* 41:39 */     p = 0;
/* 42:40 */     for (int i = 0; i <= len2; i++) {
/* 43:41 */       if (i < len2) {
/* 44:42 */         buf[i] = ((byte)pubKey.charAt(i));
/* 45:   */       } else {
/* 46:44 */         buf[i] = 45;
/* 47:   */       }
/* 48:   */     }
/* 49:47 */     len2++;
/* 50:48 */     for (int i = 0; i < len; i++)
/* 51:   */     {
/* 52:49 */       buf[(len2 + i)] = ((byte)(sendData.charAt(i) & 0xFF ^ tokenizer.charAt(p) & 0xFF));
/* 53:50 */       p++;
/* 54:50 */       if (p == len3) {
/* 55:51 */         p = 0;
/* 56:   */       }
/* 57:   */     }
/* 58:55 */     QueueWriter.writeAndFlush(cn.socket, LoadGameComposer.compose(3, new String(buf)));
/* 59:   */     
/* 60:57 */     return true;
/* 61:   */   }
/* 62:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.chat.wf.WordFilterActionSpammer
 * JD-Core Version:    0.7.0.1
 */