/*  1:   */ package cappo.protocol.messages.events.moderator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.moderation.ModeratorUserInfoComposer;
/* 10:   */ 
/* 11:   */ public class GetModeratorUserInfoParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection cn)
/* 15:   */   {
/* 16:19 */     if (!cn.playerData.allowModTools()) {
/* 17:20 */       return;
/* 18:   */     }
/* 19:23 */     int id = cn.currentPacket.readInt();
/* 20:24 */     PlayerData Client = Clients.getPlayerData(id);
/* 21:25 */     if (Client == null) {
/* 22:26 */       return;
/* 23:   */     }
/* 24:29 */     QueueWriter.write(cn.socket, ModeratorUserInfoComposer.compose(Client));
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.moderator.GetModeratorUserInfoParser
 * JD-Core Version:    0.7.0.1
 */