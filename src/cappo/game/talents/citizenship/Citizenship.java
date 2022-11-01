/*  1:   */ package cappo.game.talents.citizenship;
/*  2:   */ 
/*  3:   */ import cappo.game.talents.TalentTrack;
/*  4:   */ import cappo.game.talents.citizenship.levels.CitizenshipLevel1;
/*  5:   */ import cappo.game.talents.citizenship.levels.CitizenshipLevel2;
/*  6:   */ import cappo.game.talents.citizenship.levels.CitizenshipLevel3;
/*  7:   */ import cappo.game.talents.citizenship.levels.CitizenshipLevel4;
/*  8:   */ import cappo.game.talents.citizenship.levels.CitizenshipLevel5;
/*  9:   */ import java.util.List;
/* 10:   */ 
/* 11:   */ public class Citizenship
/* 12:   */   extends TalentTrack
/* 13:   */ {
/* 14:   */   public Citizenship()
/* 15:   */   {
/* 16:12 */     this.levels.add(new CitizenshipLevel1());
/* 17:13 */     this.levels.add(new CitizenshipLevel2());
/* 18:14 */     this.levels.add(new CitizenshipLevel3());
/* 19:15 */     this.levels.add(new CitizenshipLevel4());
/* 20:16 */     this.levels.add(new CitizenshipLevel5());
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.talents.citizenship.Citizenship
 * JD-Core Version:    0.7.0.1
 */