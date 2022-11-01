/*  1:   */ package cappo.protocol.messages.composers.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.pets.Pet;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializePet;
/*  7:   */ 
/*  8:   */ public class PetRespectedComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(int Id, int respects, Pet pet)
/* 13:   */   {
/* 14:18 */     MessageWriter writer = new MessageWriter();
/* 15:19 */     Composer.initPacket(HEADER, writer);
/* 16:20 */     Composer.add(Integer.valueOf(Id), writer);
/* 17:21 */     Composer.add(Integer.valueOf(respects), writer);
/* 18:22 */     SerializePet.parse(pet, writer);
/* 19:23 */     Composer.endPacket(writer);
/* 20:24 */     return writer;
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.users.PetRespectedComposer
 * JD-Core Version:    0.7.0.1
 */