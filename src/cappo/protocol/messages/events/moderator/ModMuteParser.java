/*  1:   */ package cappo.protocol.messages.events.moderator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Clients;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.collections.Utils;
/*  7:   */ import cappo.game.moderation.UserMuted;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ 
/* 11:   */ public class ModMuteParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection cn)
/* 15:   */   {
/* 16:19 */     if (!cn.playerData.allowModTools()) {
/* 17:20 */       return;
/* 18:   */     }
/* 19:23 */     PlayerData client = Clients.getPlayerDataLoaded(cn.currentPacket.readInt());
/* 20:24 */     if ((client == null) || (client.connection == null)) {
/* 21:25 */       return;
/* 22:   */     }
/* 23:28 */     Connection plrConnection = client.connection;
/* 24:   */     
/* 25:30 */     plrConnection.userMuted = new UserMuted();
/* 26:31 */     plrConnection.userMuted.reason = cn.currentPacket.readString();
/* 27:32 */     plrConnection.userMuted.unMuteTimeStamp = (Utils.getTimestamp() + cn.currentPacket.readInt());
/* 28:   */     
/* 29:34 */     String ticketMessage = cn.currentPacket.readString();
/* 30:35 */     if (!ticketMessage.isEmpty()) {
/* 31:36 */       cn.currentPacket.readInt();
/* 32:   */     }
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.moderator.ModMuteParser
 * JD-Core Version:    0.7.0.1
 */