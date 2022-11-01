/*  1:   */ package cappo.protocol.messages.events.roomsettings;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.collections.BaseItem;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.game.roomengine.RoomData;
/* 10:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/* 11:   */ import cappo.game.roomengine.entity.item.wall.WallItem;
/* 12:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 13:   */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/* 14:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 15:   */ import cappo.protocol.messages.composers.inventory.furni.FurniListUpdateComposer;
/* 16:   */ import java.util.Map;
/* 17:   */ 
/* 18:   */ public class DeleteRoomParser
/* 19:   */   extends IncomingMessageEvent
/* 20:   */ {
/* 21:   */   public void messageReceived(Connection cn)
/* 22:   */   {
/* 23:25 */     RoomData roomData = (RoomData)cn.ownRooms.remove(Integer.valueOf(cn.currentPacket.readInt()));
/* 24:26 */     if (roomData == null) {
/* 25:27 */       return;
/* 26:   */     }
/* 27:30 */     cn.ownRooms.remove(Integer.valueOf(roomData.roomId));
/* 28:   */     
/* 29:32 */     RoomTask room = roomData.room;
/* 30:33 */     if (room != null)
/* 31:   */     {
/* 32:34 */       for (FloorItem floorItem : room.FloorItems.values())
/* 33:   */       {
/* 34:35 */         room.removeFloorItem(floorItem, cn.playerData.userId);
/* 35:   */         
/* 36:37 */         cn.inventoryAddFloorItem(floorItem);
/* 37:38 */         floorItem.setMysqlState(2);
/* 38:   */       }
/* 39:42 */       room.FloorItems.clear();
/* 40:44 */       for (WallItem wallItem : room.WallItems.values())
/* 41:   */       {
/* 42:45 */         room.removeWallItem(wallItem, cn.playerData.userId);
/* 43:47 */         if (wallItem.baseItem.interactorType != Interactor.InteractorType.postit)
/* 44:   */         {
/* 45:52 */           cn.inventoryAddWallItem(wallItem);
/* 46:53 */           wallItem.setMysqlState(2);
/* 47:   */         }
/* 48:   */       }
/* 49:57 */       room.WallItems.clear();
/* 50:   */       
/* 51:59 */       QueueWriter.write(cn.socket, FurniListUpdateComposer.compose());
/* 52:61 */       for (Avatar user : room.userList.values()) {
/* 53:62 */         room.removeUserFromRoom(user.cn, true, false);
/* 54:   */       }
/* 55:   */     }
/* 56:66 */     roomData.setFlag(1, true);
/* 57:   */     
/* 58:   */ 
/* 59:69 */     roomData.delete();
/* 60:   */   }
/* 61:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.roomsettings.DeleteRoomParser
 * JD-Core Version:    0.7.0.1
 */