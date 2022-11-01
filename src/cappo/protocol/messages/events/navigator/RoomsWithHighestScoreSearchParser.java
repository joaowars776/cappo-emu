/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.roomengine.roomlisting.RoomListing;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ 
/*  8:   */ public class RoomsWithHighestScoreSearchParser
/*  9:   */   extends IncomingMessageEvent
/* 10:   */ {
/* 11:   */   public void messageReceived(Connection Main)
/* 12:   */   {
/* 13:17 */     if (RoomListing.ScoreRooms != null) {
/* 14:18 */       QueueWriter.write(Main.socket, RoomListing.ScoreRooms);
/* 15:   */     }
/* 16:   */   }
/* 17:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.RoomsWithHighestScoreSearchParser
 * JD-Core Version:    0.7.0.1
 */