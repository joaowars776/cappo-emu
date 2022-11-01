/*  1:   */ package cappo.game.pets;
/*  2:   */ 
/*  3:   */ public class Croco
/*  4:   */   extends PetBase
/*  5:   */ {
/*  6:   */   public Croco(short raceId)
/*  7:   */   {
/*  8:12 */     super(raceId);
/*  9:   */     
/* 10:14 */     addSpeech((short)3, "Rrrr....Grrrrrg....");
/* 11:15 */     addSpeech((short)3, "*Abrir boca*");
/* 12:16 */     addSpeech((short)3, "Tick tock tick....");
/* 13:17 */     addSpeech((short)3, "Mover cola");
/* 14:18 */     addSpeech((short)3, "Estornudando");
/* 15:   */   }
/* 16:   */   
/* 17:   */   public Croco()
/* 18:   */   {
/* 19:24 */     addRace(new Croco((short)0));
/* 20:25 */     addRace(new Croco((short)1));
/* 21:26 */     addRace(new Croco((short)2));
/* 22:27 */     addRace(new Croco((short)3));
/* 23:28 */     addRace(new Croco((short)4));
/* 24:29 */     addRace(new Croco((short)5));
/* 25:30 */     addRace(new Croco((short)6));
/* 26:31 */     addRace(new Croco((short)7));
/* 27:32 */     addRace(new Croco((short)8));
/* 28:33 */     addRace(new Croco((short)9));
/* 29:34 */     addRace(new Croco((short)10));
/* 30:35 */     addRace(new Croco((short)11));
/* 31:36 */     addRace(new Croco((short)12));
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.pets.Croco
 * JD-Core Version:    0.7.0.1
 */