/*  1:   */ package cappo.protocol.messages.events.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.messenger.MessengerFriendRequest;
/*  9:   */ import cappo.game.player.messenger.PlayerMessenger;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ import cappo.protocol.messages.composers.friendlist.MessengerErrorComposer;
/* 12:   */ import cappo.protocol.messages.composers.friendlist.NewBuddyRequestComposer;
/* 13:   */ 
/* 14:   */ public class RequestBuddyParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:21 */     if (Main.playerData.messenger.isFull())
/* 20:   */     {
/* 21:22 */       QueueWriter.write(Main.socket, MessengerErrorComposer.compose(0, 1));
/* 22:23 */       return;
/* 23:   */     }
/* 24:26 */     String UserName = Main.currentPacket.readString();
/* 25:   */     
/* 26:28 */     PlayerData reqPlayer = Clients.getPlayerData(UserName);
/* 27:29 */     if (reqPlayer == null)
/* 28:   */     {
/* 29:30 */       QueueWriter.write(Main.socket, MessengerErrorComposer.compose(0, 4));
/* 30:31 */       return;
/* 31:   */     }
/* 32:34 */     MessengerFriendRequest req = Main.playerData.messenger.pickRequest(reqPlayer.userId);
/* 33:35 */     if (req != null)
/* 34:   */     {
/* 35:36 */       Main.playerData.messenger.addFriend(reqPlayer);
/* 36:37 */       return;
/* 37:   */     }
/* 38:40 */     if (reqPlayer.messenger.isFull())
/* 39:   */     {
/* 40:41 */       QueueWriter.write(Main.socket, MessengerErrorComposer.compose(0, 2));
/* 41:42 */       return;
/* 42:   */     }
/* 43:45 */     reqPlayer.messenger.addFriendRequest(Main.playerData.userId, Main.playerData.userName, true);
/* 44:47 */     if (reqPlayer.connection != null) {
/* 45:48 */       QueueWriter.write(reqPlayer.connection.socket, NewBuddyRequestComposer.compose(Main.playerData.userId, Main.playerData.userName));
/* 46:   */     }
/* 47:   */   }
/* 48:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.friendlist.RequestBuddyParser
 * JD-Core Version:    0.7.0.1
 */