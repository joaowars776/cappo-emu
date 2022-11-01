/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.logging.Log;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.navigator.OfficialRoomsComposer;
/*  8:   */ 
/*  9:   */ public class GetOfficialRoomsParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:   */     try
/* 15:   */     {
/* 16:19 */       QueueWriter.write(Main.socket, OfficialRoomsComposer.compose());
/* 17:   */     }
/* 18:   */     catch (Exception ex)
/* 19:   */     {
/* 20:22 */       Log.printException("GetOfficialRoomsParser", ex);
/* 21:   */     }
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.GetOfficialRoomsParser
 * JD-Core Version:    0.7.0.1
 */