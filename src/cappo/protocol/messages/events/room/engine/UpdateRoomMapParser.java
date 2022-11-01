/*  1:   */ package cappo.protocol.messages.events.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.logging.Log;
/*  4:   */ import cappo.engine.network.MessageReader;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.roomengine.RoomData;
/*  9:   */ import cappo.game.roomengine.RoomManager;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.game.roomengine.gamemap.CustomGameMap;
/* 12:   */ import cappo.game.roomengine.gamemap.GameMapBase;
/* 13:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 14:   */ import java.util.Map;
/* 15:   */ import java.util.concurrent.ScheduledFuture;
/* 16:   */ 
/* 17:   */ public class UpdateRoomMapParser
/* 18:   */   extends IncomingMessageEvent
/* 19:   */ {
/* 20:   */   public void messageReceived(Connection Main)
/* 21:   */   {
/* 22:20 */     Avatar avatar = Main.avatar;
/* 23:21 */     if (avatar == null) {
/* 24:22 */       return;
/* 25:   */     }
/* 26:25 */     RoomTask roomTask = avatar.room;
/* 27:26 */     if ((roomTask == null) || (roomTask.roomData.roomOwnerId != Main.playerData.userId)) {
/* 28:27 */       return;
/* 29:   */     }
/* 30:30 */     String newModel = "custom_" + roomTask.roomId;
/* 31:   */     
/* 32:32 */     CustomGameMap model = new CustomGameMap(newModel, 
/* 33:33 */       roomTask.model.doorX, 
/* 34:34 */       roomTask.model.doorY, 
/* 35:35 */       roomTask.model.doorZ, 
/* 36:36 */       roomTask.model.DoorOrientation, false);
/* 37:   */     CustomGameMap customModel;
/* 38:   */     try
/* 39:   */     {
/* 40:39 */       String map = Main.currentPacket.readString();
/* 41:40 */       model.generateModel(map.split("\r"));
/* 42:41 */       if ((roomTask.model instanceof CustomGameMap))
/* 43:   */       {
/* 44:42 */         customModel = (CustomGameMap)roomTask.model;
/* 45:43 */         model.baseName = customModel.baseName;
/* 46:   */         
/* 47:   */ 
/* 48:46 */         model.mysqlAction = 2;
/* 49:   */       }
/* 50:   */       else
/* 51:   */       {
/* 52:48 */         model.baseName = roomTask.model.modelName;
/* 53:   */         
/* 54:   */ 
/* 55:51 */         model.mysqlAction = 1;
/* 56:   */       }
/* 57:   */     }
/* 58:   */     catch (Exception ex)
/* 59:   */     {
/* 60:54 */       Log.printException("RoomManager", ex);
/* 61:55 */       return;
/* 62:   */     }
/* 63:58 */     roomTask.model = model;
/* 64:59 */     roomTask.roomData.model = model.modelName;
/* 65:   */     
/* 66:   */ 
/* 67:62 */     roomTask.future.cancel(false);
/* 68:65 */     for (Avatar user : roomTask.userList.values()) {
/* 69:66 */       roomTask.removeUserFromRoom(user.cn, true, false);
/* 70:   */     }
/* 71:70 */     RoomManager.setInactive(roomTask.roomData);
/* 72:71 */     roomTask.updateMysqlData();
/* 73:   */   }
/* 74:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.UpdateRoomMapParser
 * JD-Core Version:    0.7.0.1
 */