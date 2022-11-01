/*  1:   */ package cappo.game.pets;
/*  2:   */ 
/*  3:   */ public class Bear
/*  4:   */   extends PetBase
/*  5:   */ {
/*  6:   */   public Bear(short raceId)
/*  7:   */   {
/*  8:12 */     super(raceId);
/*  9:   */     
/* 10:14 */     addSpeech((short)3, "*Dame un salmón fresco por favor*");
/* 11:15 */     addSpeech((short)3, "Grrrrrrr");
/* 12:16 */     addSpeech((short)3, "*Estornudando*");
/* 13:17 */     addSpeech((short)3, "Grrrr... grrrr");
/* 14:   */   }
/* 15:   */   
/* 16:   */   public Bear()
/* 17:   */   {
/* 18:23 */     addRace(new Bear((short)0));
/* 19:24 */     addRace(new Bear((short)1));
/* 20:25 */     addRace(new Bear((short)2));
/* 21:26 */     addRace(new Bear((short)3));
/* 22:27 */     addRace(new Bear((short)4));
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.pets.Bear
 * JD-Core Version:    0.7.0.1
 */