/*  1:   */ package cappo.game.pets;
/*  2:   */ 
/*  3:   */ public class Monster
/*  4:   */   extends PetBase
/*  5:   */ {
/*  6:   */   public Monster(short raceId)
/*  7:   */   {
/*  8: 6 */     super(raceId);
/*  9:   */   }
/* 10:   */   
/* 11:   */   public Monster()
/* 12:   */   {
/* 13:13 */     addRace(new Monster((short)0));
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.pets.Monster
 * JD-Core Version:    0.7.0.1
 */