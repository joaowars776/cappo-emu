/*  1:   */ package cappo.game.games.snowwar.tasks;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  4:   */ import cappo.protocol.messages.composers.games.snowwar.StageRunningComposer;
/*  5:   */ 
/*  6:   */ public class SnowStageRun
/*  7:   */ {
/*  8:   */   public static void exec(SnowWarRoom room)
/*  9:   */   {
/* 10:15 */     room.broadcast(StageRunningComposer.compose(120));
/* 11:   */   }
/* 12:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.tasks.SnowStageRun
 * JD-Core Version:    0.7.0.1
 */