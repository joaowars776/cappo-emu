/*  1:   */ package cappo.game.pets;
/*  2:   */ 
/*  3:   */ public class Pig
/*  4:   */   extends PetBase
/*  5:   */ {
/*  6:   */   public Pig(short raceId)
/*  7:   */   {
/*  8: 6 */     super(raceId);
/*  9:   */     
/* 10: 8 */     addSpeech((short)3, "Oink Oink..");
/* 11: 9 */     addSpeech((short)3, "*Meando*");
/* 12:10 */     addSpeech((short)3, "*Estornudando*");
/* 13:11 */     addSpeech((short)3, "*Tirandose un pedo*");
/* 14:12 */     addSpeech((short)3, "Oink!");
/* 15:13 */     addSpeech((short)3, "*Estornunando*");
/* 16:14 */     addSpeech((short)3, "*Hacer el cerdo*");
/* 17:15 */     addSpeech((short)3, "oink");
/* 18:   */   }
/* 19:   */   
/* 20:   */   public Pig()
/* 21:   */   {
/* 22:21 */     addRace(new Pig((short)0));
/* 23:22 */     addRace(new Pig((short)1));
/* 24:23 */     addRace(new Pig((short)2));
/* 25:24 */     addRace(new Pig((short)3));
/* 26:25 */     addRace(new Pig((short)4));
/* 27:26 */     addRace(new Pig((short)5));
/* 28:27 */     addRace(new Pig((short)6));
/* 29:28 */     addRace(new Pig((short)7));
/* 30:29 */     addRace(new Pig((short)8));
/* 31:30 */     addRace(new Pig((short)9));
/* 32:31 */     addRace(new Pig((short)10));
/* 33:32 */     addRace(new Pig((short)11));
/* 34:33 */     addRace(new Pig((short)12));
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.pets.Pig
 * JD-Core Version:    0.7.0.1
 */