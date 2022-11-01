/*  1:   */ package cappo.game.games.snowwar.gameevents;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  4:   */ 
/*  5:   */ public class UserMove
/*  6:   */   extends Event
/*  7:   */ {
/*  8:   */   public HumanGameObject player;
/*  9:   */   public int x;
/* 10:   */   public int y;
/* 11:   */   
/* 12:   */   public UserMove(HumanGameObject player, int x, int y)
/* 13:   */   {
/* 14:17 */     this.EventType = 2;
/* 15:18 */     this.player = player;
/* 16:19 */     this.x = x;
/* 17:20 */     this.y = y;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void apply()
/* 21:   */   {
/* 22:25 */     this.player.setMove(this.x, this.y);
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameevents.UserMove
 * JD-Core Version:    0.7.0.1
 */