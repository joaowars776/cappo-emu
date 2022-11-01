/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.data.AvatarData;
/*  9:   */ import cappo.game.roomengine.RoomData;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.game.roomengine.roomlisting.RoomListing;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ import cappo.protocol.messages.composers.navigator.RoomRatingComposer;
/* 14:   */ import java.util.List;
/* 15:   */ 
/* 16:   */ public class RateFlatParser
/* 17:   */   extends IncomingMessageEvent
/* 18:   */ {
/* 19:   */   public void messageReceived(Connection Main)
/* 20:   */   {
/* 21:20 */     Avatar avatar = Main.avatar;
/* 22:21 */     if (avatar == null) {
/* 23:22 */       return;
/* 24:   */     }
/* 25:25 */     RoomTask room = avatar.room;
/* 26:27 */     if ((Main.avatarData.ratedRooms.contains(Integer.valueOf(room.roomId))) || (Main.playerData.userId == room.roomData.roomOwner.userId)) {
/* 27:28 */       return;
/* 28:   */     }
/* 29:31 */     int rating = Main.currentPacket.readInt();
/* 30:32 */     if (rating == 1) {
/* 31:33 */       room.roomData.rating += 1;
/* 32:   */     } else {
/* 33:35 */       room.roomData.rating -= 1;
/* 34:   */     }
/* 35:38 */     RoomListing.updateMostScoreRooms(room);
/* 36:39 */     Main.avatarData.ratedRooms.add(Integer.valueOf(room.roomId));
/* 37:40 */     QueueWriter.write(Main.socket, RoomRatingComposer.compose(room.roomData.rating, false));
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.RateFlatParser
 * JD-Core Version:    0.7.0.1
 */