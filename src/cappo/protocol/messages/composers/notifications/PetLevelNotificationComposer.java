/*  1:   */ package cappo.protocol.messages.composers.notifications;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.pets.Pet;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializePetfigureData;
/*  7:   */ 
/*  8:   */ public class PetLevelNotificationComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(int VirtualId, Pet pet)
/* 13:   */   {
/* 14:18 */     MessageWriter writer = new MessageWriter();
/* 15:19 */     Composer.initPacket(HEADER, writer);
/* 16:20 */     Composer.add(Integer.valueOf(VirtualId), writer);
/* 17:21 */     Composer.add(pet.name, writer);
/* 18:22 */     Composer.add(Integer.valueOf(pet.level), writer);
/* 19:23 */     SerializePetfigureData.parse(pet, writer);
/* 20:24 */     Composer.endPacket(writer);
/* 21:25 */     return writer;
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.notifications.PetLevelNotificationComposer
 * JD-Core Version:    0.7.0.1
 */