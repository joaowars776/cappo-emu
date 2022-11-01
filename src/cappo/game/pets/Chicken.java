/*  1:   */ package cappo.game.pets;
/*  2:   */ 
/*  3:   */ public class Chicken
/*  4:   */   extends PetBase
/*  5:   */ {
/*  6:   */   public Chicken(short raceId)
/*  7:   */   {
/*  8:11 */     super(raceId);
/*  9:   */   }
/* 10:   */   
/* 11:   */   public Chicken()
/* 12:   */   {
/* 13:18 */     addRace(new Chicken((short)0));
/* 14:19 */     addRace(new Chicken((short)1));
/* 15:20 */     addRace(new Chicken((short)2));
/* 16:21 */     addRace(new Chicken((short)3));
/* 17:22 */     addRace(new Chicken((short)4));
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.pets.Chicken
 * JD-Core Version:    0.7.0.1
 */