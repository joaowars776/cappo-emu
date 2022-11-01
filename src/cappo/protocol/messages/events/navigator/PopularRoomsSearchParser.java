/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.MessageWriter;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.roomengine.roomlisting.RoomListing;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import java.util.Map;
/* 10:   */ 
/* 11:   */ public class PopularRoomsSearchParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection Main)
/* 15:   */   {
/* 16:18 */     int Category = Integer.parseInt(Main.currentPacket.readString());
/* 17:20 */     if (Category == -1)
/* 18:   */     {
/* 19:21 */       if (RoomListing.PopularRooms != null) {
/* 20:22 */         QueueWriter.write(Main.socket, RoomListing.PopularRooms);
/* 21:   */       }
/* 22:   */     }
/* 23:   */     else
/* 24:   */     {
/* 25:25 */       MessageWriter packet = (MessageWriter)RoomListing.ByCatRooms.get(Integer.valueOf(Category));
/* 26:26 */       if (packet != null) {
/* 27:27 */         QueueWriter.write(Main.socket, packet);
/* 28:   */       }
/* 29:   */     }
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.PopularRoomsSearchParser
 * JD-Core Version:    0.7.0.1
 */