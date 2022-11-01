/*  1:   */ package cappo.protocol.messages.events.room.session;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.RoomData;
/*  7:   */ import cappo.game.roomengine.RoomManager;
/*  8:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ 
/* 11:   */ public class GoToFlatParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection cn)
/* 15:   */   {
/* 16:21 */     RoomData roomData = RoomManager.getRoom(cn.currentPacket.readInt());
/* 17:22 */     if (roomData == null) {
/* 18:23 */       return;
/* 19:   */     }
/* 20:26 */     RoomTask room = roomData.room;
/* 21:27 */     if (room == null) {
/* 22:28 */       return;
/* 23:   */     }
/* 24:34 */     Avatar avatar = cn.avatar;
/* 25:35 */     if (avatar != null)
/* 26:   */     {
/* 27:36 */       RoomTask oldRoom = avatar.room;
/* 28:37 */       if (oldRoom != null) {
/* 29:38 */         oldRoom.removeUserFromRoom(cn, false, false);
/* 30:   */       }
/* 31:   */     }
/* 32:43 */     room.startLoadingRoom(cn);
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.session.GoToFlatParser
 * JD-Core Version:    0.7.0.1
 */