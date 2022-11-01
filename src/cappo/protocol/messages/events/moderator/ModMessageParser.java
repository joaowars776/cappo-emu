/*  1:   */ package cappo.protocol.messages.events.moderator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Clients;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.collections.Utils;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ 
/* 10:   */ public class ModMessageParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection cn)
/* 14:   */   {
/* 15:18 */     if (!cn.playerData.allowModTools()) {
/* 16:19 */       return;
/* 17:   */     }
/* 18:22 */     PlayerData client = Clients.getPlayerData(cn.currentPacket.readInt());
/* 19:23 */     if (client == null) {
/* 20:24 */       return;
/* 21:   */     }
/* 22:27 */     if (client.connection != null) {
/* 23:28 */       Utils.AlertFromHotel(client.connection.socket, cn.currentPacket.readString());
/* 24:   */     }
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.moderator.ModMessageParser
 * JD-Core Version:    0.7.0.1
 */