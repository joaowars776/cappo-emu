/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.roomengine.RoomData;
/*  7:   */ import cappo.game.roomengine.RoomManager;
/*  8:   */ import cappo.game.roomengine.settings.ControllerLevels;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.navigator.GuestRoomResultComposer;
/* 11:   */ 
/* 12:   */ public class GetGuestRoomParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection cn)
/* 16:   */   {
/* 17:20 */     RoomData room = RoomManager.getRoom(cn.currentPacket.readInt());
/* 18:21 */     if (room == null) {
/* 19:22 */       return;
/* 20:   */     }
/* 21:25 */     boolean isLoading = cn.currentPacket.readInt() == 1;
/* 22:26 */     boolean isPreEnter = cn.currentPacket.readInt() == 1;
/* 23:27 */     boolean freeToEnter = ControllerLevels.getLevel(cn.playerData, room, room.room) >= 4;
/* 24:28 */     QueueWriter.write(cn.socket, GuestRoomResultComposer.compose(room, isLoading, isPreEnter, freeToEnter));
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.GetGuestRoomParser
 * JD-Core Version:    0.7.0.1
 */