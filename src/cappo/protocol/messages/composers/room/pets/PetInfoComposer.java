/*  1:   */ package cappo.protocol.messages.composers.room.pets;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.Utils;
/*  5:   */ import cappo.game.pets.Pet;
/*  6:   */ import cappo.game.pets.PetBase;
/*  7:   */ import cappo.game.roomengine.entity.live.PetEntity;
/*  8:   */ import cappo.protocol.messages.Composer;
/*  9:   */ 
/* 10:   */ public class PetInfoComposer
/* 11:   */ {
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static final MessageWriter compose(Pet pet)
/* 15:   */   {
/* 16:19 */     MessageWriter ClientMessage = new MessageWriter(1000);
/* 17:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 18:21 */     Composer.add(Integer.valueOf(pet.id), ClientMessage);
/* 19:22 */     Composer.add(pet.name, ClientMessage);
/* 20:23 */     Composer.add(Integer.valueOf(pet.level), ClientMessage);
/* 21:24 */     Composer.add(Integer.valueOf(20), ClientMessage);
/* 22:25 */     Composer.add(Integer.valueOf(pet.Experience), ClientMessage);
/* 23:26 */     Composer.add(Integer.valueOf(PetBase.ExperienceLevels[pet.level]), ClientMessage);
/* 24:27 */     Composer.add(Integer.valueOf(pet.Energy), ClientMessage);
/* 25:28 */     Composer.add(Integer.valueOf(PetBase.MaxEnergyLevels[pet.level]), ClientMessage);
/* 26:29 */     Composer.add(Integer.valueOf(pet.happiness), ClientMessage);
/* 27:30 */     Composer.add(Integer.valueOf(PetBase.MaxHappinessLevels[pet.level]), ClientMessage);
/* 28:31 */     Composer.add(Integer.valueOf(pet.Respects), ClientMessage);
/* 29:32 */     Composer.add(Integer.valueOf(pet.ownerId), ClientMessage);
/* 30:33 */     Composer.add(Long.valueOf((Utils.getTimestamp() - pet.TimeCreated) / 86400L), ClientMessage);
/* 31:34 */     Composer.add(pet.ownerName, ClientMessage);
/* 32:35 */     Composer.add(Short.valueOf(pet.base.raceId), ClientMessage);
/* 33:36 */     Composer.add(Boolean.valueOf(pet.haveSaddle), ClientMessage);
/* 34:37 */     Composer.add(Boolean.valueOf(pet.petEntity.ridingEntity != null), ClientMessage);
/* 35:   */     
/* 36:   */ 
/* 37:40 */     Composer.add(Integer.valueOf(3), ClientMessage);
/* 38:   */     
/* 39:42 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 40:43 */     Composer.add(Integer.valueOf(3), ClientMessage);
/* 41:44 */     Composer.add(Integer.valueOf(5), ClientMessage);
/* 42:   */     
/* 43:   */ 
/* 44:47 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 45:48 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 46:49 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 47:50 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 48:51 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 49:52 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 50:53 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 51:54 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 52:55 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 53:56 */     Composer.endPacket(ClientMessage);
/* 54:57 */     return ClientMessage;
/* 55:   */   }
/* 56:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.pets.PetInfoComposer
 * JD-Core Version:    0.7.0.1
 */