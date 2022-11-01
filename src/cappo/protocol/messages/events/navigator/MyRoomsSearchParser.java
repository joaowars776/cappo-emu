/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ import cappo.protocol.messages.composers.navigator.GuestRoomSearchResultComposer;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class MyRoomsSearchParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection cn)
/* 13:   */   {
/* 14:17 */     QueueWriter.write(cn.socket, GuestRoomSearchResultComposer.compose(0, "5", cn.ownRooms.values()));
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.MyRoomsSearchParser
 * JD-Core Version:    0.7.0.1
 */