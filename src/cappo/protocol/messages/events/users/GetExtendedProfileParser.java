/*  1:   */ package cappo.protocol.messages.events.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.messenger.PlayerMessenger;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.users.UserProfileInfoComposer;
/* 11:   */ 
/* 12:   */ public class GetExtendedProfileParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection cn)
/* 16:   */     throws Exception
/* 17:   */   {
/* 18:19 */     PlayerData client = Clients.getPlayerData(cn.currentPacket.readInt());
/* 19:20 */     if (client == null) {
/* 20:21 */       return;
/* 21:   */     }
/* 22:24 */     PlayerData playerData = cn.getPlayerData();
/* 23:   */     
/* 24:26 */     QueueWriter.write(cn.socket, UserProfileInfoComposer.compose(client, playerData.messenger.haveFriend(client.userId), playerData.messenger.haveRequest(playerData.userId)));
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.users.GetExtendedProfileParser
 * JD-Core Version:    0.7.0.1
 */