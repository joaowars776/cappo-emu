/*  1:   */ package cappo.protocol.messages.events.tracking;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ 
/*  7:   */ public class LatencyPingReportParser
/*  8:   */   extends IncomingMessageEvent
/*  9:   */ {
/* 10:   */   public void messageReceived(Connection Main)
/* 11:   */   {
/* 12:15 */     Main.currentPacket.readInt();
/* 13:16 */     Main.currentPacket.readInt();
/* 14:17 */     Main.currentPacket.readInt();
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.tracking.LatencyPingReportParser
 * JD-Core Version:    0.7.0.1
 */