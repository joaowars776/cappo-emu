/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.RoomData;
/*  7:   */ import cappo.game.roomengine.RoomManager;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.navigator.RoomUpdatedComposer;
/* 10:   */ 
/* 11:   */ public class ToggleStaffPickParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection Main)
/* 15:   */   {
/* 16:20 */     RoomData roomData = RoomManager.getRoom(Main.currentPacket.readInt());
/* 17:21 */     if (roomData == null) {
/* 18:22 */       return;
/* 19:   */     }
/* 20:24 */     roomData.xorFlag(32);
/* 21:   */     
/* 22:26 */     RoomTask room = roomData.room;
/* 23:27 */     if (room != null) {
/* 24:28 */       room.sendMessage(RoomUpdatedComposer.compose(room.roomId));
/* 25:   */     }
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.ToggleStaffPickParser
 * JD-Core Version:    0.7.0.1
 */