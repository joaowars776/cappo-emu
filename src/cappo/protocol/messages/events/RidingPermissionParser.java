/*  1:   */ package cappo.protocol.messages.events;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.pets.Pet;
/*  7:   */ import cappo.game.pets.PetBase;
/*  8:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  9:   */ import cappo.game.roomengine.entity.live.PetEntity;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ 
/* 12:   */ public class RidingPermissionParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:19 */     Avatar avatar = Main.avatar;
/* 18:20 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 19:21 */       return;
/* 20:   */     }
/* 21:24 */     PetEntity petEntity = avatar.room.getRoomPetById(Main.currentPacket.readInt());
/* 22:25 */     if ((petEntity == null) || (petEntity.petData.petType != 15)) {
/* 23:26 */       return;
/* 24:   */     }
/* 25:29 */     petEntity.petData.ridingAll = (!petEntity.petData.ridingAll);
/* 26:30 */     petEntity.look = (petEntity.petData.petType + " " + petEntity.petData.base.raceId + " " + petEntity.petData.Color + "3 2 -1 1 4 10 0 3 -1 1");
/* 27:   */     
/* 28:   */ 
/* 29:   */ 
/* 30:34 */     avatar.room.userUpdateNeeded(petEntity);
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.RidingPermissionParser
 * JD-Core Version:    0.7.0.1
 */