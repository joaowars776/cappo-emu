/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ import cappo.protocol.messages.composers.navigator.CanCreateRoomComposer;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class CanCreateRoomParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:17 */     if (Main.ownRooms.size() < Main.MaxRooms) {
/* 15:18 */       QueueWriter.write(Main.socket, CanCreateRoomComposer.compose(0, 0));
/* 16:   */     } else {
/* 17:20 */       QueueWriter.write(Main.socket, CanCreateRoomComposer.compose(1, Main.MaxRooms));
/* 18:   */     }
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.CanCreateRoomParser
 * JD-Core Version:    0.7.0.1
 */