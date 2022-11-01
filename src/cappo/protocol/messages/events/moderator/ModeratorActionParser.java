/*  1:   */ package cappo.protocol.messages.events.moderator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Clients;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.collections.Utils;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ 
/* 10:   */ public class ModeratorActionParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection cn)
/* 14:   */   {
/* 15:18 */     if (!cn.playerData.allowModTools()) {
/* 16:19 */       return;
/* 17:   */     }
/* 18:22 */     int UserId = cn.currentPacket.readInt();
/* 19:23 */     String Message = cn.currentPacket.readString();
/* 20:   */     
/* 21:25 */     PlayerData client = Clients.getPlayerData(UserId);
/* 22:26 */     if (client == null) {
/* 23:27 */       return;
/* 24:   */     }
/* 25:30 */     client.cautions += 1;
/* 26:32 */     if (client.connection != null) {
/* 27:33 */       Utils.AlertFromHotel(client.connection.socket, Message);
/* 28:   */     }
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.moderator.ModeratorActionParser
 * JD-Core Version:    0.7.0.1
 */