/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.logging.Log;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.collections.FavRoom;
/*  7:   */ import cappo.game.roomengine.RoomData;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.navigator.GuestRoomSearchResultComposer;
/* 10:   */ import java.util.ArrayList;
/* 11:   */ import java.util.List;
/* 12:   */ import java.util.Map;
/* 13:   */ 
/* 14:   */ public class MyFavouriteRoomsSearchParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:23 */     List<RoomData> roomList = new ArrayList();
/* 20:24 */     for (FavRoom fav : Main.favoriteRooms.values()) {
/* 21:   */       try
/* 22:   */       {
/* 23:26 */         roomList.add(fav.room);
/* 24:   */       }
/* 25:   */       catch (Exception ex)
/* 26:   */       {
/* 27:28 */         Log.printException("MyFavouriteRoomsSearchParser-1", ex);
/* 28:   */       }
/* 29:   */     }
/* 30:31 */     QueueWriter.write(Main.socket, GuestRoomSearchResultComposer.compose(0, "6", roomList));
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.MyFavouriteRoomsSearchParser
 * JD-Core Version:    0.7.0.1
 */