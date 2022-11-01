/*  1:   */ package cappo.protocol.messages.events.tracking;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.tracking.PingResponseComposer;
/*  8:   */ 
/*  9:   */ public class LatencyPingRequestParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:17 */     QueueWriter.write(Main.socket, PingResponseComposer.compose(Main.currentPacket.readInt()));
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.tracking.LatencyPingRequestParser
 * JD-Core Version:    0.7.0.1
 */