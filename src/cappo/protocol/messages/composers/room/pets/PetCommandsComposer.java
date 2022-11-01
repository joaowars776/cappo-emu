/*  1:   */ package cappo.protocol.messages.composers.room.pets;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.pets.Pet;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class PetCommandsComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(Pet pet)
/* 12:   */   {
/* 13:17 */     int cmds = 32;
/* 14:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:20 */     Composer.add(Integer.valueOf(pet.id), ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(cmds), ClientMessage);
/* 18:22 */     for (int i = 0; i < cmds; i++) {
/* 19:23 */       Composer.add(Integer.valueOf(i), ClientMessage);
/* 20:   */     }
/* 21:25 */     if (pet.level < cmds) {
/* 22:26 */       cmds = pet.level;
/* 23:   */     }
/* 24:28 */     Composer.add(Integer.valueOf(cmds), ClientMessage);
/* 25:29 */     for (int i = 0; i < cmds; i++) {
/* 26:30 */       Composer.add(Integer.valueOf(i), ClientMessage);
/* 27:   */     }
/* 28:32 */     Composer.endPacket(ClientMessage);
/* 29:33 */     return ClientMessage;
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.pets.PetCommandsComposer
 * JD-Core Version:    0.7.0.1
 */