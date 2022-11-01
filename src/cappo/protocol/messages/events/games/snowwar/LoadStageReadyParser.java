/*  1:   */ package cappo.protocol.messages.events.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  6:   */ import cappo.game.player.SnowWarPlayerData;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ 
/*  9:   */ public class LoadStageReadyParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:17 */     SnowWarPlayerData snowPlayer = Main.snowWarPlayerData;
/* 15:18 */     if (snowPlayer.currentSnowWar == null) {
/* 16:19 */       return;
/* 17:   */     }
/* 18:22 */     HumanGameObject humanObject = snowPlayer.humanObject;
/* 19:23 */     if (humanObject == null) {
/* 20:24 */       return;
/* 21:   */     }
/* 22:28 */     snowPlayer.currentSnowWar.stageLoaded(humanObject);
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.games.snowwar.LoadStageReadyParser
 * JD-Core Version:    0.7.0.1
 */