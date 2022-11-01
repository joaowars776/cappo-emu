/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.roomengine.RoomData;
/*  7:   */ import cappo.game.roomengine.RoomManager;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.navigator.FlatCreatedComposer;
/* 10:   */ import java.util.Map;
/* 11:   */ 
/* 12:   */ public class CreateFlatParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:19 */     if (Main.ownRooms.size() >= Main.MaxRooms) {
/* 18:20 */       return;
/* 19:   */     }
/* 20:23 */     RoomData NewRoom = RoomManager.createRoom(Main, Main.currentPacket.readString(), Main.currentPacket.readString());
/* 21:25 */     if (NewRoom != null) {
/* 22:26 */       QueueWriter.write(Main.socket, FlatCreatedComposer.compose(NewRoom.roomId, NewRoom.name));
/* 23:   */     }
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.CreateFlatParser
 * JD-Core Version:    0.7.0.1
 */