/*  1:   */ package cappo.game.games.snowwar.tasks;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  4:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  5:   */ import cappo.protocol.messages.composers.games.snowwar.StageStillLoadingComposer;
/*  6:   */ import java.util.Collection;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class SnowStageLoading
/* 10:   */ {
/* 11:   */   public static void exec(SnowWarRoom room)
/* 12:   */   {
/* 13:18 */     Collection<HumanGameObject> playersLoaded = room.getStageLoadedPlayers();
/* 14:20 */     if (playersLoaded != null)
/* 15:   */     {
/* 16:21 */       room.broadcast(StageStillLoadingComposer.compose(playersLoaded));
/* 17:23 */       if (!playersLoaded.isEmpty()) {
/* 18:24 */         return;
/* 19:   */       }
/* 20:   */     }
/* 21:28 */     for (HumanGameObject player : room.players.values()) {
/* 22:29 */       if (!player.stageLoaded) {
/* 23:30 */         return;
/* 24:   */       }
/* 25:   */     }
/* 26:34 */     room.STATUS = 3;
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.tasks.SnowStageLoading
 * JD-Core Version:    0.7.0.1
 */