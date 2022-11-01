/*  1:   */ package cappo.protocol.messages.events.landing;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.landing.RewardResultComposer;
/*  8:   */ 
/*  9:   */ public class RequestBadgeParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:20 */     String req = Main.currentPacket.readString();
/* 15:21 */     if (req.equals("REQ001"))
/* 16:   */     {
/* 17:22 */       Main.giveBadge("ancients_start");
/* 18:23 */       QueueWriter.write(Main.socket, RewardResultComposer.compose(7));
/* 19:   */     }
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.landing.RequestBadgeParser
 * JD-Core Version:    0.7.0.1
 */