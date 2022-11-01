/*  1:   */ package cappo.protocol.messages.events.landing;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.landing.BadgeButtonStatusComposer;
/*  8:   */ 
/*  9:   */ public class RequestBadgeButtonStatusParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:17 */     boolean hidde = false;
/* 15:18 */     QueueWriter.write(Main.socket, BadgeButtonStatusComposer.compose(Main.currentPacket.readString(), false));
/* 16:   */   }
/* 17:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.landing.RequestBadgeButtonStatusParser
 * JD-Core Version:    0.7.0.1
 */