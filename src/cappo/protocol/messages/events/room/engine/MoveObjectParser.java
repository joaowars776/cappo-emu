/*  1:   */ package cappo.protocol.messages.events.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.games.snowwar.Direction8;
/*  8:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ import cappo.protocol.messages.composers.room.engine.ObjectUpdateComposer;
/* 12:   */ import cappo.protocol.messages.composers.room.engine.PlaceObjectErrorComposer;
/* 13:   */ 
/* 14:   */ public class MoveObjectParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:21 */     Avatar avatar = Main.avatar;
/* 20:22 */     if ((avatar == null) || ((avatar.controllerLevel != 1) && 
/* 21:23 */       (avatar.controllerLevel < 4)))
/* 22:   */     {
/* 23:24 */       QueueWriter.write(Main.socket, PlaceObjectErrorComposer.compose(11));
/* 24:25 */       return;
/* 25:   */     }
/* 26:28 */     FloorItem item = avatar.room.getFloorItem(Main.currentPacket.readInt());
/* 27:29 */     if (item == null)
/* 28:   */     {
/* 29:30 */       QueueWriter.write(Main.socket, PlaceObjectErrorComposer.compose(11));
/* 30:31 */       return;
/* 31:   */     }
/* 32:34 */     int x = Main.currentPacket.readInt();
/* 33:35 */     int y = Main.currentPacket.readInt();
/* 34:36 */     Direction8 rot = Direction8.getDirection(Main.currentPacket.readInt());
/* 35:38 */     if (!avatar.room.setFloorItem(Main, item, x, y, rot, false)) {
/* 36:39 */       QueueWriter.write(Main.socket, ObjectUpdateComposer.compose(item));
/* 37:   */     }
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.MoveObjectParser
 * JD-Core Version:    0.7.0.1
 */