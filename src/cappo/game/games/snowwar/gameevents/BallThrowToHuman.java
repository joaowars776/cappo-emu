/*  1:   */ package cappo.game.games.snowwar.gameevents;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.PlayerTile;
/*  4:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  5:   */ 
/*  6:   */ public class BallThrowToHuman
/*  7:   */   extends Event
/*  8:   */ {
/*  9:   */   public HumanGameObject attacker;
/* 10:   */   public HumanGameObject victim;
/* 11:   */   public int type;
/* 12:   */   
/* 13:   */   public BallThrowToHuman(HumanGameObject attacker, HumanGameObject victim, int type)
/* 14:   */   {
/* 15:17 */     this.EventType = 3;
/* 16:18 */     this.attacker = attacker;
/* 17:19 */     this.victim = victim;
/* 18:20 */     this.type = type;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void apply()
/* 22:   */   {
/* 23:25 */     this.attacker._vs(this.victim.location3D().x(), this.victim.location3D().y());
/* 24:26 */     this.attacker._w1();
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameevents.BallThrowToHuman
 * JD-Core Version:    0.7.0.1
 */