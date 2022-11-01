/*  1:   */ package cappo.protocol.messages.events.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  8:   */ import cappo.game.roomengine.entity.item.wall.RoomWallItemData;
/*  9:   */ import cappo.game.roomengine.entity.item.wall.WallItem;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.room.engine.PlaceObjectErrorComposer;
/* 13:   */ 
/* 14:   */ public class MoveWallItemParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:22 */     Avatar avatar = Main.avatar;
/* 20:23 */     if ((avatar == null) || ((avatar.controllerLevel != 1) && 
/* 21:24 */       (avatar.controllerLevel < 4))) {
/* 22:25 */       return;
/* 23:   */     }
/* 24:28 */     WallItem wallItem = avatar.room.getWallItem(Main.currentPacket.readInt());
/* 25:29 */     if (wallItem == null) {
/* 26:30 */       return;
/* 27:   */     }
/* 28:33 */     if (!(wallItem instanceof GenericWallItem)) {
/* 29:34 */       return;
/* 30:   */     }
/* 31:37 */     String[] DataBits = Main.currentPacket.readString().split(" ");
/* 32:   */     
/* 33:39 */     String[] widD = DataBits[0].substring(3).split(",");
/* 34:40 */     int widthX = Integer.parseInt(widD[0]);
/* 35:41 */     int widthY = Integer.parseInt(widD[1]);
/* 36:42 */     if ((widthX < 0) || (widthY < 0) || (widthX > 100) || (widthY > 100))
/* 37:   */     {
/* 38:43 */       QueueWriter.write(Main.socket, PlaceObjectErrorComposer.compose(11));
/* 39:44 */       return;
/* 40:   */     }
/* 41:47 */     String[] lenD = DataBits[1].substring(2).split(",");
/* 42:48 */     int lengthX = Integer.parseInt(lenD[0]);
/* 43:49 */     int lengthY = Integer.parseInt(lenD[1]);
/* 44:50 */     if ((lengthX < 0) || (lengthY < 0) || (lengthX > 100) || (lengthY > 100))
/* 45:   */     {
/* 46:51 */       QueueWriter.write(Main.socket, PlaceObjectErrorComposer.compose(11)); return;
/* 47:   */     }
/* 48:   */     char side;
/* 49:   */     char side;
/* 50:56 */     if (DataBits[2].equals("r")) {
/* 51:57 */       side = 'r';
/* 52:   */     } else {
/* 53:60 */       side = 'l';
/* 54:   */     }
/* 55:63 */     wallItem.setRoomData(new RoomWallItemData(avatar.room, wallItem, side, widthX, widthY, lengthX, lengthY));
/* 56:   */     
/* 57:65 */     avatar.room.setWallItem(Main, (GenericWallItem)wallItem, false);
/* 58:   */   }
/* 59:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.MoveWallItemParser
 * JD-Core Version:    0.7.0.1
 */