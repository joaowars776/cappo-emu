/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.pets.Pet;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class SerializePet
/*  8:   */ {
/*  9:   */   public static void parse(Pet pet, MessageWriter writer)
/* 10:   */   {
/* 11:15 */     Composer.add(Integer.valueOf(pet.id), writer);
/* 12:16 */     Composer.add(pet.name, writer);
/* 13:17 */     SerializePetfigureData.parse(pet, writer);
/* 14:18 */     Composer.add(Integer.valueOf(pet.level), writer);
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializePet
 * JD-Core Version:    0.7.0.1
 */