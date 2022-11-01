/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.pets.Pet;
/*  5:   */ import cappo.game.pets.PetBase;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ 
/*  8:   */ public class SerializePetfigureData
/*  9:   */ {
/* 10:   */   public static void parse(Pet pet, MessageWriter writer)
/* 11:   */   {
/* 12:15 */     Composer.add(Short.valueOf(pet.petType), writer);
/* 13:16 */     Composer.add(Integer.valueOf(2), writer);
/* 14:17 */     Composer.add(pet.Color, writer);
/* 15:18 */     Composer.add(Short.valueOf(pet.base.raceId), writer);
/* 16:19 */     Composer.add(Integer.valueOf(1), writer);
/* 17:   */     
/* 18:21 */     Composer.add(Integer.valueOf(10), writer);
/* 19:22 */     Composer.add(Integer.valueOf(10), writer);
/* 20:23 */     Composer.add(Integer.valueOf(10), writer);
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializePetfigureData
 * JD-Core Version:    0.7.0.1
 */