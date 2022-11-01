/*  1:   */ package cappo.game.pets;
/*  2:   */ 
/*  3:   */ public class Terrier
/*  4:   */   extends PetBase
/*  5:   */ {
/*  6:   */   public Terrier(short raceId)
/*  7:   */   {
/*  8: 6 */     super(raceId);
/*  9:   */     
/* 10: 8 */     addSpeech((short)3, "woof woof woof");
/* 11: 9 */     addSpeech((short)3, "Auuuu auuuu");
/* 12:10 */     addSpeech((short)3, "wooooof");
/* 13:11 */     addSpeech((short)3, "Grrrr");
/* 14:12 */     addSpeech((short)3, "Sentandose");
/* 15:13 */     addSpeech((short)3, "*Estornunando*");
/* 16:14 */     addSpeech((short)3, "lay");
/* 17:15 */     addSpeech((short)3, "Woof");
/* 18:   */   }
/* 19:   */   
/* 20:   */   public Terrier()
/* 21:   */   {
/* 22:21 */     addRace(new Terrier((short)0));
/* 23:22 */     addRace(new Terrier((short)1));
/* 24:23 */     addRace(new Terrier((short)2));
/* 25:24 */     addRace(new Terrier((short)3));
/* 26:25 */     addRace(new Terrier((short)4));
/* 27:26 */     addRace(new Terrier((short)5));
/* 28:27 */     addRace(new Terrier((short)6));
/* 29:28 */     addRace(new Terrier((short)7));
/* 30:29 */     addRace(new Terrier((short)8));
/* 31:30 */     addRace(new Terrier((short)9));
/* 32:31 */     addRace(new Terrier((short)10));
/* 33:32 */     addRace(new Terrier((short)11));
/* 34:33 */     addRace(new Terrier((short)12));
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.pets.Terrier
 * JD-Core Version:    0.7.0.1
 */