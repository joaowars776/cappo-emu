/*  1:   */ package cappo.game.games.snowwar.gameevents;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.PlayerTile;
/*  4:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  6:   */ import cappo.game.games.snowwar.gameobjects.SnowBallGameObject;
/*  7:   */ 
/*  8:   */ public class CreateSnowBall
/*  9:   */   extends Event
/* 10:   */ {
/* 11:   */   public SnowBallGameObject ball;
/* 12:   */   public HumanGameObject player;
/* 13:   */   public int x;
/* 14:   */   public int y;
/* 15:   */   public int type;
/* 16:   */   
/* 17:   */   public CreateSnowBall(SnowBallGameObject ball, HumanGameObject player, int x, int y, int type)
/* 18:   */   {
/* 19:20 */     this.EventType = 8;
/* 20:21 */     this.ball = ball;
/* 21:22 */     this.player = player;
/* 22:23 */     this.x = x;
/* 23:24 */     this.y = y;
/* 24:25 */     this.type = type;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void apply()
/* 28:   */   {
/* 29:30 */     this.ball.initialize(this.player.location3D().x(), this.player.location3D().y(), this.type, this.x, this.y, this.player);
/* 30:31 */     this.ball.GenerateCHECKSUM(this.player.currentSnowWar, 1);
/* 31:32 */     this.player.currentSnowWar.addGameObject(this.ball);
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameevents.CreateSnowBall
 * JD-Core Version:    0.7.0.1
 */