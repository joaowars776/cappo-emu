/*  1:   */ package cappo.protocol.messages.events.room.pets;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.collections.Utils;
/*  8:   */ import cappo.game.pets.Pet;
/*  9:   */ import cappo.game.pets.PetBase;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.game.roomengine.entity.live.PetEntity;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ import cappo.protocol.messages.composers.notifications.PetLevelNotificationComposer;
/* 14:   */ import cappo.protocol.messages.composers.notifications.PetRespectFailedComposer;
/* 15:   */ import cappo.protocol.messages.composers.users.PetRespectedComposer;
/* 16:   */ 
/* 17:   */ public class RespectPetParser
/* 18:   */   extends IncomingMessageEvent
/* 19:   */ {
/* 20:   */   public void messageReceived(Connection Main)
/* 21:   */   {
/* 22:24 */     Avatar avatar = Main.avatar;
/* 23:25 */     if (avatar == null) {
/* 24:26 */       return;
/* 25:   */     }
/* 26:29 */     RoomTask room = avatar.room;
/* 27:   */     
/* 28:31 */     PetEntity petEntity = room.getRoomPetById(Main.currentPacket.readInt());
/* 29:32 */     if (petEntity != null)
/* 30:   */     {
/* 31:33 */       Pet pet = petEntity.petData;
/* 32:   */       
/* 33:35 */       int petAge = (int)((Utils.getTimestamp() - pet.TimeCreated) / 86400L);
/* 34:36 */       if (petAge < 7)
/* 35:   */       {
/* 36:37 */         QueueWriter.write(Main.socket, PetRespectFailedComposer.compose(7, petAge));
/* 37:38 */         return;
/* 38:   */       }
/* 39:41 */       Main.dailyPetRespectPoints -= 1;
/* 40:42 */       pet.Experience += 10;
/* 41:   */       
/* 42:44 */       room.sendMessage(PetRespectedComposer.compose(petEntity.petData.id, ++pet.Respects, pet));
/* 43:45 */       if (pet.base.checkLevel(pet)) {
/* 44:46 */         room.sendMessage(PetLevelNotificationComposer.compose(petEntity.virtualId, pet));
/* 45:   */       }
/* 46:   */     }
/* 47:   */   }
/* 48:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.pets.RespectPetParser
 * JD-Core Version:    0.7.0.1
 */