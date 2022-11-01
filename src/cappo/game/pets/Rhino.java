/*  1:   */ package cappo.game.pets;
/*  2:   */ 
/*  3:   */ public class Rhino
/*  4:   */   extends PetBase
/*  5:   */ {
/*  6:   */   public Rhino(short raceId)
/*  7:   */   {
/*  8: 6 */     super(raceId);
/*  9:   */     
/* 10: 8 */     addSpeech((short)3, "Auguruuuh...");
/* 11: 9 */     addSpeech((short)3, "Buff");
/* 12:10 */     addSpeech((short)3, "Augubuff...");
/* 13:11 */     addSpeech((short)3, "Buffuu...");
/* 14:12 */     addSpeech((short)3, "snf");
/* 15:13 */     addSpeech((short)3, "lay");
/* 16:14 */     addSpeech((short)3, "Aff");
/* 17:   */   }
/* 18:   */   
/* 19:   */   public Rhino()
/* 20:   */   {
/* 21:20 */     addRace(new Rhino((short)0));
/* 22:21 */     addRace(new Rhino((short)1));
/* 23:22 */     addRace(new Rhino((short)2));
/* 24:23 */     addRace(new Rhino((short)3));
/* 25:24 */     addRace(new Rhino((short)4));
/* 26:25 */     addRace(new Rhino((short)5));
/* 27:26 */     addRace(new Rhino((short)6));
/* 28:27 */     addRace(new Rhino((short)7));
/* 29:28 */     addRace(new Rhino((short)8));
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.pets.Rhino
 * JD-Core Version:    0.7.0.1
 */