/*  1:   */ package cappo.protocol.messages.events.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.engine.threadpools.RoomTask;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.game.player.messenger.PlayerMessenger;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.friendlist.FollowFriendFailedComposer;
/* 13:   */ import cappo.protocol.messages.composers.navigator.RoomForwardComposer;
/* 14:   */ 
/* 15:   */ public class FollowFriendParser
/* 16:   */   extends IncomingMessageEvent
/* 17:   */ {
/* 18:   */   public void messageReceived(Connection cn)
/* 19:   */   {
/* 20:22 */     int userId = cn.currentPacket.readInt();
/* 21:   */     
/* 22:24 */     PlayerData client = Clients.getPlayerDataLoaded(userId);
/* 23:25 */     if ((client == null) || (client.connection == null))
/* 24:   */     {
/* 25:26 */       QueueWriter.write(cn.socket, FollowFriendFailedComposer.compose(1));
/* 26:27 */       return;
/* 27:   */     }
/* 28:30 */     Avatar clientAvatar = client.connection.avatar;
/* 29:32 */     if (!cn.playerData.messenger.haveFriend(client.userId))
/* 30:   */     {
/* 31:33 */       QueueWriter.write(cn.socket, FollowFriendFailedComposer.compose(0));
/* 32:34 */       return;
/* 33:   */     }
/* 34:37 */     if (clientAvatar == null)
/* 35:   */     {
/* 36:38 */       QueueWriter.write(cn.socket, FollowFriendFailedComposer.compose(2));
/* 37:39 */       return;
/* 38:   */     }
/* 39:42 */     RoomTask room = clientAvatar.room;
/* 40:43 */     if ((cn.avatar != null) && (cn.avatar.room == room)) {
/* 41:45 */       return;
/* 42:   */     }
/* 43:48 */     QueueWriter.write(cn.socket, RoomForwardComposer.compose(false, room.roomId));
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.friendlist.FollowFriendParser
 * JD-Core Version:    0.7.0.1
 */