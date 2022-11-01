/*  1:   */ package cappo.game.games.snowwar.gameevents;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  4:   */ 
/*  5:   */ public class MakeSnowBall
/*  6:   */   extends Event
/*  7:   */ {
/*  8:   */   public HumanGameObject player;
/*  9:   */   
/* 10:   */   public MakeSnowBall(HumanGameObject player)
/* 11:   */   {
/* 12:15 */     this.EventType = 7;
/* 13:16 */     this.player = player;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public void apply()
/* 17:   */   {
/* 18:21 */     this.player.makeSnowBall();
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameevents.MakeSnowBall
 * JD-Core Version:    0.7.0.1
 */