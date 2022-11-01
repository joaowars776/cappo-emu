/*  1:   */ package cappo.protocol.messages.events.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.collections.BaseItem;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/* 10:   */ import cappo.game.roomengine.entity.item.wall.WallItem;
/* 11:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 12:   */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/* 13:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 14:   */ import cappo.protocol.messages.composers.inventory.furni.FurniListAddOrUpdateComposer;
/* 15:   */ 
/* 16:   */ public class PickupObjectParser
/* 17:   */   extends IncomingMessageEvent
/* 18:   */ {
/* 19:   */   public void messageReceived(Connection Main)
/* 20:   */   {
/* 21:24 */     Avatar avatar = Main.avatar;
/* 22:25 */     if (avatar == null) {
/* 23:26 */       return;
/* 24:   */     }
/* 25:29 */     RoomTask room = avatar.room;
/* 26:30 */     PlayerData playerData = Main.getPlayerData();
/* 27:   */     
/* 28:32 */     int type = Main.currentPacket.readInt();
/* 29:33 */     if (type == 2)
/* 30:   */     {
/* 31:34 */       FloorItem flooritem = room.getFloorItem(Main.currentPacket.readInt());
/* 32:35 */       if (flooritem == null) {
/* 33:36 */         return;
/* 34:   */       }
/* 35:39 */       if ((flooritem.owner.userId != playerData.userId) && (!playerData.allowEjectFurni())) {
/* 36:40 */         return;
/* 37:   */       }
/* 38:43 */       room.removeFloorItem(flooritem, playerData.userId);
/* 39:   */       
/* 40:45 */       Main.inventoryAddFloorItem(flooritem);
/* 41:46 */       flooritem.setMysqlState(2);
/* 42:   */       
/* 43:48 */       QueueWriter.write(Main.socket, FurniListAddOrUpdateComposer.compose(flooritem));
/* 44:   */     }
/* 45:49 */     else if (type == 1)
/* 46:   */     {
/* 47:50 */       WallItem wallitem = room.getWallItem(Main.currentPacket.readInt());
/* 48:51 */       if (wallitem == null) {
/* 49:52 */         return;
/* 50:   */       }
/* 51:55 */       if ((wallitem.owner.userId != playerData.userId) && (!playerData.allowEjectFurni())) {
/* 52:56 */         return;
/* 53:   */       }
/* 54:59 */       room.removeWallItem(wallitem, playerData.userId);
/* 55:61 */       if (wallitem.baseItem.interactorType == Interactor.InteractorType.postit) {
/* 56:63 */         return;
/* 57:   */       }
/* 58:66 */       Main.inventoryAddWallItem(wallitem);
/* 59:67 */       wallitem.setMysqlState(2);
/* 60:   */       
/* 61:69 */       QueueWriter.write(Main.socket, FurniListAddOrUpdateComposer.compose(wallitem));
/* 62:   */     }
/* 63:   */   }
/* 64:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.PickupObjectParser
 * JD-Core Version:    0.7.0.1
 */