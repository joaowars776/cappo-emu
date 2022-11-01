/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.collections.FavRoom;
/*  7:   */ import cappo.game.roomengine.RoomData;
/*  8:   */ import cappo.game.roomengine.RoomManager;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.navigator.FavouriteChangedComposer;
/* 11:   */ import java.util.Map;
/* 12:   */ 
/* 13:   */ public class AddFavouriteRoomParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection Main)
/* 17:   */   {
/* 18:20 */     if (Main.favoriteRooms.size() >= 30) {
/* 19:21 */       return;
/* 20:   */     }
/* 21:24 */     int roomId = Main.currentPacket.readInt();
/* 22:25 */     if (Main.favoriteRooms.containsKey(Integer.valueOf(roomId))) {
/* 23:26 */       return;
/* 24:   */     }
/* 25:29 */     RoomData room = RoomManager.getRoom(roomId);
/* 26:30 */     if (room == null) {
/* 27:31 */       return;
/* 28:   */     }
/* 29:33 */     QueueWriter.write(Main.socket, FavouriteChangedComposer.compose(roomId, Boolean.valueOf(true)));
/* 30:34 */     FavRoom fav = new FavRoom(room);
/* 31:35 */     fav.needInsert = true;
/* 32:36 */     Main.favoriteRooms.put(Integer.valueOf(roomId), fav);
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.AddFavouriteRoomParser
 * JD-Core Version:    0.7.0.1
 */