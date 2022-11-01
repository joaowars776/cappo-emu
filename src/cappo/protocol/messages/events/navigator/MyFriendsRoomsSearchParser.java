/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.roomengine.RoomData;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.navigator.GuestRoomSearchResultComposer;
/*  8:   */ import java.util.ArrayList;
/*  9:   */ import java.util.List;
/* 10:   */ 
/* 11:   */ public class MyFriendsRoomsSearchParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection Main)
/* 15:   */   {
/* 16:23 */     List<RoomData> roomList = new ArrayList();
/* 17:24 */     QueueWriter.write(Main.socket, GuestRoomSearchResultComposer.compose(0, "3", roomList));
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.MyFriendsRoomsSearchParser
 * JD-Core Version:    0.7.0.1
 */