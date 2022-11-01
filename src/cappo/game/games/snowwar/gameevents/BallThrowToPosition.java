/*  1:   */ package cappo.game.games.snowwar.gameevents;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  4:   */ 
/*  5:   */ public class BallThrowToPosition
/*  6:   */   extends Event
/*  7:   */ {
/*  8:   */   public HumanGameObject attacker;
/*  9:   */   public int x;
/* 10:   */   public int y;
/* 11:   */   public int type;
/* 12:   */   
/* 13:   */   public BallThrowToPosition(HumanGameObject attacker, int x, int y, int type)
/* 14:   */   {
/* 15:18 */     this.EventType = 4;
/* 16:19 */     this.attacker = attacker;
/* 17:20 */     this.x = x;
/* 18:21 */     this.y = y;
/* 19:22 */     this.type = type;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void apply()
/* 23:   */   {
/* 24:27 */     this.attacker._vs(this.x, this.y);
/* 25:28 */     this.attacker._w1();
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameevents.BallThrowToPosition
 * JD-Core Version:    0.7.0.1
 */