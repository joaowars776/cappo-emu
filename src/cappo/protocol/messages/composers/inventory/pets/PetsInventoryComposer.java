/*  1:   */ package cappo.protocol.messages.composers.inventory.pets;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.pets.Pet;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializePet;
/*  7:   */ import java.util.Collection;
/*  8:   */ 
/*  9:   */ public class PetsInventoryComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(int virtualId, Collection<Pet> InventoryPets)
/* 14:   */   {
/* 15:20 */     MessageWriter writer = new MessageWriter(100 + InventoryPets.size() * 100);
/* 16:21 */     Composer.initPacket(HEADER, writer);
/* 17:22 */     Composer.add(Integer.valueOf(1), writer);
/* 18:23 */     Composer.add(Integer.valueOf(1), writer);
/* 19:24 */     Composer.add(Integer.valueOf(InventoryPets.size()), writer);
/* 20:25 */     for (Pet pet : InventoryPets) {
/* 21:26 */       SerializePet.parse(pet, writer);
/* 22:   */     }
/* 23:28 */     Composer.endPacket(writer);
/* 24:29 */     return writer;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.pets.PetsInventoryComposer
 * JD-Core Version:    0.7.0.1
 */