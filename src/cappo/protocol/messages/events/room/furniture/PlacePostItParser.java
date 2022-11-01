/*  1:   */ package cappo.protocol.messages.events.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.collections.BaseItem;
/*  8:   */ import cappo.game.player.inventory.PlayerInventory;
/*  9:   */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/* 10:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/* 11:   */ import cappo.game.roomengine.entity.item.wall.RoomWallItemData;
/* 12:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 13:   */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/* 14:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 15:   */ import cappo.protocol.messages.composers.inventory.furni.PostItPlacedComposer;
/* 16:   */ import cappo.protocol.messages.composers.room.engine.PlaceObjectErrorComposer;
/* 17:   */ 
/* 18:   */ public class PlacePostItParser
/* 19:   */   extends IncomingMessageEvent
/* 20:   */ {
/* 21:   */   public void messageReceived(Connection Main)
/* 22:   */   {
/* 23:23 */     Avatar avatar = Main.avatar;
/* 24:24 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 25:25 */       return;
/* 26:   */     }
/* 27:28 */     GenericWallItem item = (GenericWallItem)Main.inventory.getItem(Main.currentPacket.readInt());
/* 28:29 */     if ((item == null) || (item.baseItem.interactorType != Interactor.InteractorType.postit)) {
/* 29:30 */       return;
/* 30:   */     }
/* 31:33 */     String location = Main.currentPacket.readString();
/* 32:34 */     String[] DataBits = location.split(" ");
/* 33:35 */     if (DataBits.length < 3) {
/* 34:36 */       return;
/* 35:   */     }
/* 36:40 */     String[] widD = DataBits[0].substring(3).split(",");
/* 37:41 */     int widthX = Integer.parseInt(widD[0]);
/* 38:42 */     int widthY = Integer.parseInt(widD[1]);
/* 39:43 */     if ((widthX < 0) || (widthY < 0) || (widthX > 200) || (widthY > 200))
/* 40:   */     {
/* 41:44 */       QueueWriter.write(Main.socket, PlaceObjectErrorComposer.compose(11));
/* 42:45 */       return;
/* 43:   */     }
/* 44:48 */     String[] lenD = DataBits[1].substring(2).split(",");
/* 45:49 */     int lengthX = Integer.parseInt(lenD[0]);
/* 46:50 */     int lengthY = Integer.parseInt(lenD[1]);
/* 47:51 */     if ((lengthX < 0) || (lengthY < 0) || (lengthX > 200) || (lengthY > 200))
/* 48:   */     {
/* 49:52 */       QueueWriter.write(Main.socket, PlaceObjectErrorComposer.compose(11)); return;
/* 50:   */     }
/* 51:   */     char side;
/* 52:   */     char side;
/* 53:57 */     if (DataBits[2].equals("r")) {
/* 54:58 */       side = 'r';
/* 55:   */     } else {
/* 56:60 */       side = 'l';
/* 57:   */     }
/* 58:63 */     item.setRoomData(new RoomWallItemData(avatar.room, item, side, widthX, widthY, lengthX, lengthY));
/* 59:64 */     item.extraData.setExtraData("FFFF33 ");
/* 60:   */     
/* 61:66 */     QueueWriter.write(Main.socket, PostItPlacedComposer.compose(item));
/* 62:68 */     if (avatar.room.setWallItem(Main, item, true)) {
/* 63:69 */       Main.inventoryRemoveItem(item.itemId, true);
/* 64:   */     }
/* 65:   */   }
/* 66:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.furniture.PlacePostItParser
 * JD-Core Version:    0.7.0.1
 */