/*  1:   */ package cappo.protocol.messages.events.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  5:   */ import cappo.game.player.SnowWarPlayerData;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import java.util.List;
/*  8:   */ 
/*  9:   */ public class RequestFullStatusUpdateParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:16 */     SnowWarRoom room = Main.snowWarPlayerData.currentSnowWar;
/* 15:17 */     if (room == null) {
/* 16:18 */       return;
/* 17:   */     }
/* 18:21 */     synchronized (room.fullGameStatusQueue)
/* 19:   */     {
/* 20:22 */       room.fullGameStatusQueue.add(Main.socket);
/* 21:   */     }
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.games.snowwar.RequestFullStatusUpdateParser
 * JD-Core Version:    0.7.0.1
 */