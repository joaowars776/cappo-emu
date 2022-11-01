/*  1:   */ package cappo.protocol.messages.composers.notifications;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.pets.Pet;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializePet;
/*  7:   */ 
/*  8:   */ public class PetReceivedMessageComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(boolean petBought, Pet pet)
/* 13:   */   {
/* 14:18 */     MessageWriter writer = new MessageWriter();
/* 15:19 */     Composer.initPacket(HEADER, writer);
/* 16:20 */     Composer.writeBoolean(petBought, writer);
/* 17:21 */     SerializePet.parse(pet, writer);
/* 18:22 */     Composer.endPacket(writer);
/* 19:23 */     return writer;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.notifications.PetReceivedMessageComposer
 * JD-Core Version:    0.7.0.1
 */