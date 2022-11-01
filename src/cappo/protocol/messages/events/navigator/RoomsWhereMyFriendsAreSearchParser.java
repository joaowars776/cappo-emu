/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Clients;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.messenger.MessengerFriend;
/*  9:   */ import cappo.game.player.messenger.PlayerMessenger;
/* 10:   */ import cappo.game.roomengine.RoomData;
/* 11:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ import cappo.protocol.messages.composers.navigator.GuestRoomSearchResultComposer;
/* 14:   */ import java.util.HashMap;
/* 15:   */ import java.util.Map;
/* 16:   */ 
/* 17:   */ public class RoomsWhereMyFriendsAreSearchParser
/* 18:   */   extends IncomingMessageEvent
/* 19:   */ {
/* 20:   */   public void messageReceived(Connection cn)
/* 21:   */   {
/* 22:26 */     Map<Integer, RoomData> roomList = new HashMap();
/* 23:27 */     for (MessengerFriend friend : cn.playerData.messenger.getFriends())
/* 24:   */     {
/* 25:28 */       PlayerData friendPlayer = Clients.getPlayerDataLoaded(friend.userId);
/* 26:29 */       if ((friendPlayer != null) && (friendPlayer.connection != null))
/* 27:   */       {
/* 28:33 */         Avatar avatar = friendPlayer.connection.avatar;
/* 29:34 */         if (avatar != null)
/* 30:   */         {
/* 31:38 */           RoomTask room = avatar.room;
/* 32:39 */           if (room != null) {
/* 33:40 */             roomList.put(Integer.valueOf(room.roomId), room.roomData);
/* 34:   */           }
/* 35:   */         }
/* 36:   */       }
/* 37:   */     }
/* 38:44 */     QueueWriter.write(cn.socket, GuestRoomSearchResultComposer.compose(0, "4", roomList.values()));
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.RoomsWhereMyFriendsAreSearchParser
 * JD-Core Version:    0.7.0.1
 */