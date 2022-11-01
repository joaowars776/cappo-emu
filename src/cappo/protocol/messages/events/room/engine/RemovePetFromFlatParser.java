/*  1:   */ package cappo.protocol.messages.events.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.pets.Pet;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.game.player.inventory.PlayerInventory;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.game.roomengine.entity.live.PetEntity;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ import cappo.protocol.messages.composers.inventory.pets.AddPetToInventoryComposer;
/* 14:   */ 
/* 15:   */ public class RemovePetFromFlatParser
/* 16:   */   extends IncomingMessageEvent
/* 17:   */ {
/* 18:   */   public void messageReceived(Connection Main)
/* 19:   */   {
/* 20:19 */     Avatar avatar = Main.avatar;
/* 21:20 */     if (avatar == null) {
/* 22:21 */       return;
/* 23:   */     }
/* 24:24 */     PetEntity petEntity = avatar.room.getRoomPetById(Main.currentPacket.readInt());
/* 25:25 */     if ((petEntity == null) || (petEntity.petData.ownerId != Main.playerData.userId)) {
/* 26:26 */       return;
/* 27:   */     }
/* 28:29 */     Main.inventory.addPet(petEntity.petData.id, petEntity.petData);
/* 29:30 */     QueueWriter.write(Main.socket, AddPetToInventoryComposer.compose(petEntity.petData));
/* 30:31 */     avatar.room.removePet(petEntity);
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.RemovePetFromFlatParser
 * JD-Core Version:    0.7.0.1
 */