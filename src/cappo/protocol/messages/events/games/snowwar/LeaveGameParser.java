/*  1:   */ package cappo.protocol.messages.events.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.game.player.SnowWarPlayerData;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ 
/*  7:   */ public class LeaveGameParser
/*  8:   */   extends IncomingMessageEvent
/*  9:   */ {
/* 10:   */   public void messageReceived(Connection Main)
/* 11:   */   {
/* 12:15 */     Main.snowWarPlayerData.userLeft();
/* 13:   */   }
/* 14:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.games.snowwar.LeaveGameParser
 * JD-Core Version:    0.7.0.1
 */