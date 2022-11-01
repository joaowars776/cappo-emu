/*  1:   */ package cappo.protocol.messages.composers.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.pets.PetBase;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import java.util.Map;
/*  7:   */ 
/*  8:   */ public class SellablePetBreedsComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(String PetType)
/* 13:   */   {
/* 14:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:19 */     short Type = Short.parseShort(PetType.substring(6));
/* 16:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:21 */     Composer.add(PetType, ClientMessage);
/* 18:22 */     if ((Type >= 0) && (Type < 27))
/* 19:   */     {
/* 20:23 */       Composer.add(Integer.valueOf(cappo.game.pets.Pet.PETS[Type].races.size()), ClientMessage);
/* 21:24 */       for (PetBase race : cappo.game.pets.Pet.PETS[Type].races.values())
/* 22:   */       {
/* 23:25 */         Composer.add(Short.valueOf(Type), ClientMessage);
/* 24:26 */         Composer.add(Short.valueOf(race.raceId), ClientMessage);
/* 25:27 */         Composer.add(Short.valueOf(race.raceId), ClientMessage);
/* 26:28 */         Composer.add(Boolean.valueOf(true), ClientMessage);
/* 27:29 */         Composer.add(Boolean.valueOf(false), ClientMessage);
/* 28:   */       }
/* 29:   */     }
/* 30:   */     else
/* 31:   */     {
/* 32:32 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 33:   */     }
/* 34:34 */     Composer.endPacket(ClientMessage);
/* 35:35 */     return ClientMessage;
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.catalog.SellablePetBreedsComposer
 * JD-Core Version:    0.7.0.1
 */