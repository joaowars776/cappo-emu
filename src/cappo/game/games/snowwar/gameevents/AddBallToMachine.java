/*  1:   */ package cappo.game.games.snowwar.gameevents;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.gameobjects.MachineGameObject;
/*  4:   */ 
/*  5:   */ public class AddBallToMachine
/*  6:   */   extends Event
/*  7:   */ {
/*  8:   */   public MachineGameObject gameItem;
/*  9:   */   
/* 10:   */   public AddBallToMachine(MachineGameObject gameItem)
/* 11:   */   {
/* 12:15 */     this.EventType = 11;
/* 13:16 */     this.gameItem = gameItem;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public void apply()
/* 17:   */   {
/* 18:21 */     this.gameItem.addSnowBall();
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameevents.AddBallToMachine
 * JD-Core Version:    0.7.0.1
 */