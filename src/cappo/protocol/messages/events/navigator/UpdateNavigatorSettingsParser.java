/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.roomengine.RoomData;
/*  7:   */ import cappo.game.roomengine.RoomManager;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.navigator.NavigatorSettingsComposer;
/* 10:   */ 
/* 11:   */ public class UpdateNavigatorSettingsParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection Main)
/* 15:   */   {
/* 16:19 */     RoomData room = RoomManager.getRoom(Main.currentPacket.readInt());
/* 17:20 */     if (room == null) {
/* 18:21 */       return;
/* 19:   */     }
/* 20:24 */     Main.homeRoom = room.roomId;
/* 21:25 */     QueueWriter.write(Main.socket, NavigatorSettingsComposer.compose(room.roomId, 0));
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.UpdateNavigatorSettingsParser
 * JD-Core Version:    0.7.0.1
 */