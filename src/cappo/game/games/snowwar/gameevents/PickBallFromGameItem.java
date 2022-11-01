/*  1:   */ package cappo.game.games.snowwar.gameevents;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  4:   */ import cappo.game.games.snowwar.gameobjects.PickBallsGameItemObject;
/*  5:   */ 
/*  6:   */ public class PickBallFromGameItem
/*  7:   */   extends Event
/*  8:   */ {
/*  9:   */   public HumanGameObject player;
/* 10:   */   public PickBallsGameItemObject gameItem;
/* 11:   */   
/* 12:   */   public PickBallFromGameItem(HumanGameObject player, PickBallsGameItemObject gameItem)
/* 13:   */   {
/* 14:17 */     this.EventType = 12;
/* 15:18 */     this.player = player;
/* 16:19 */     this.gameItem = gameItem;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void apply()
/* 20:   */   {
/* 21:25 */     int local1 = this.player.availableSnowBallSlots();
/* 22:26 */     if (local1 > 0)
/* 23:   */     {
/* 24:27 */       int local2 = this.gameItem.pickUp(1);
/* 25:28 */       if (local2 > 0) {
/* 26:29 */         this.player.addSnowBalls(local2);
/* 27:   */       }
/* 28:   */     }
/* 29:33 */     if (this.gameItem.concurrentUses > 0) {
/* 30:34 */       this.gameItem.concurrentUses -= 1;
/* 31:   */     }
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameevents.PickBallFromGameItem
 * JD-Core Version:    0.7.0.1
 */