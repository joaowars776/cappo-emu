/*  1:   */ package cappo.protocol.messages.events.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.player.PlayerData;
/*  7:   */ import cappo.game.player.messenger.MessengerFriend;
/*  8:   */ import cappo.game.player.messenger.MessengerFriendUpdate;
/*  9:   */ import cappo.game.player.messenger.PlayerMessenger;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ 
/* 12:   */ public class SetRelationshipStatusParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:19 */     PlayerMessenger messenger = Main.playerData.messenger;
/* 18:20 */     MessengerFriend friend = messenger.getFriend(Main.currentPacket.readInt());
/* 19:21 */     if (friend == null) {
/* 20:22 */       return;
/* 21:   */     }
/* 22:25 */     messenger.removeRelationship(friend.friendType, friend.userId);
/* 23:   */     
/* 24:27 */     friend.friendType = Main.currentPacket.readInt();
/* 25:28 */     if (friend.friendType != 0) {
/* 26:29 */       messenger.setRelationshipStatus(friend.friendType, friend);
/* 27:   */     }
/* 28:32 */     friend.needUpdate = true;
/* 29:   */     
/* 30:34 */     messenger.update(new MessengerFriendUpdate(friend.userId, 0));
/* 31:35 */     QueueWriter.write(Main.socket, messenger.getFriendUpdstes());
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.friendlist.SetRelationshipStatusParser
 * JD-Core Version:    0.7.0.1
 */