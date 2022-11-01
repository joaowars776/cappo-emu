/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.roomengine.RoomData;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.navigator.GuestRoomSearchResultComposer;
/*  9:   */ import java.util.ArrayList;
/* 10:   */ import java.util.List;
/* 11:   */ 
/* 12:   */ public class LatestEventsSearchParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:23 */     List<RoomData> roomList = new ArrayList();
/* 18:24 */     int Category = Integer.parseInt(Main.currentPacket.readString());
/* 19:25 */     QueueWriter.write(Main.socket, GuestRoomSearchResultComposer.compose(Category, "12", roomList));
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.LatestEventsSearchParser
 * JD-Core Version:    0.7.0.1
 */