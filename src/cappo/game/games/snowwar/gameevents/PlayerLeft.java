/*  1:   */ package cappo.game.games.snowwar.gameevents;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  4:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  5:   */ 
/*  6:   */ public class PlayerLeft
/*  7:   */   extends Event
/*  8:   */ {
/*  9:   */   public HumanGameObject player;
/* 10:   */   
/* 11:   */   public PlayerLeft(HumanGameObject player)
/* 12:   */   {
/* 13:15 */     this.EventType = 1;
/* 14:16 */     this.player = player;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void apply()
/* 18:   */   {
/* 19:21 */     this.player.currentSnowWar.queueDeleteObject(this.player);
/* 20:22 */     this.player.cleanTiles();
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameevents.PlayerLeft
 * JD-Core Version:    0.7.0.1
 */