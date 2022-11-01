/*  1:   */ package cappo.protocol.messages.events;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.pets.Pet;
/*  7:   */ import cappo.game.pets.PetBase;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.game.roomengine.entity.live.PetEntity;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ 
/* 14:   */ public class PutHorseSaddleParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:21 */     Avatar avatar = Main.avatar;
/* 20:22 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 21:23 */       return;
/* 22:   */     }
/* 23:26 */     RoomTask room = avatar.room;
/* 24:   */     
/* 25:28 */     FloorItem flooritem = room.getFloorItem(Main.currentPacket.readInt());
/* 26:29 */     if (flooritem == null) {
/* 27:30 */       return;
/* 28:   */     }
/* 29:32 */     PetEntity petEntity = room.getRoomPetById(Main.currentPacket.readInt());
/* 30:33 */     if ((petEntity == null) || (petEntity.petData.petType != 15)) {
/* 31:34 */       return;
/* 32:   */     }
/* 33:37 */     room.removeFloorItem(flooritem, Main.playerData.userId);
/* 34:38 */     petEntity.petData.saddleFurni = flooritem;
/* 35:39 */     petEntity.petData.haveSaddle = true;
/* 36:40 */     petEntity.ridingEntity = null;
/* 37:41 */     petEntity.look = (petEntity.petData.petType + " " + petEntity.petData.base.raceId + " " + petEntity.petData.Color + "3 2 -1 1 4 10 0 3 -1 1");
/* 38:   */     
/* 39:   */ 
/* 40:   */ 
/* 41:45 */     room.userUpdateNeeded(petEntity);
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.PutHorseSaddleParser
 * JD-Core Version:    0.7.0.1
 */