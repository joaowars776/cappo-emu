/*  1:   */ package cappo.game.games.snowwar.tasks;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.SnowWar;
/*  4:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  6:   */ import cappo.protocol.messages.composers.games.snowwar.GameEndingComposer;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class SnowArenaEnd
/* 10:   */ {
/* 11:   */   public static void exec(SnowWarRoom room)
/* 12:   */   {
/* 13:16 */     room.Winner = 0;
/* 14:17 */     for (int TeamId : SnowWar.TEAMS)
/* 15:   */     {
/* 16:18 */       if (room.Winner == 0) {
/* 17:19 */         room.Winner = TeamId;
/* 18:   */       }
/* 19:21 */       if (room.TeamScore[(TeamId - 1)] == room.TeamScore[(room.Winner - 1)])
/* 20:   */       {
/* 21:22 */         room.Result = 2;
/* 22:   */       }
/* 23:23 */       else if (room.TeamScore[(TeamId - 1)] > room.TeamScore[(room.Winner - 1)])
/* 24:   */       {
/* 25:24 */         room.Result = 1;
/* 26:25 */         room.Winner = TeamId;
/* 27:   */       }
/* 28:   */     }
/* 29:28 */     if (room.Result == 2) {
/* 30:29 */       room.Winner = 0;
/* 31:   */     }
/* 32:32 */     for (HumanGameObject player : room.players.values())
/* 33:   */     {
/* 34:33 */       if (room.MostHits == null) {
/* 35:34 */         room.MostHits = player;
/* 36:   */       }
/* 37:36 */       if (room.MostKills == null) {
/* 38:37 */         room.MostKills = player;
/* 39:   */       }
/* 40:39 */       if (player.hits > room.MostHits.hits) {
/* 41:40 */         room.MostHits = player;
/* 42:   */       }
/* 43:42 */       if (player.kills > room.MostKills.kills) {
/* 44:43 */         room.MostKills = player;
/* 45:   */       }
/* 46:   */     }
/* 47:47 */     room.broadcast(GameEndingComposer.compose(room));
/* 48:   */   }
/* 49:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.tasks.SnowArenaEnd
 * JD-Core Version:    0.7.0.1
 */