/*  1:   */ package cappo.protocol.messages.events.landing;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.landing.UpdateLandingComposer;
/*  8:   */ 
/*  9:   */ public class RefreshLandingViewParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:17 */     String code = "";
/* 15:18 */     String data = Main.currentPacket.readString();
/* 16:20 */     if (!data.isEmpty())
/* 17:   */     {
/* 18:21 */       String[] tmp = data.split(";");
/* 19:22 */       String[] first = tmp[0].split(",");
/* 20:23 */       code = first[1];
/* 21:   */     }
/* 22:26 */     QueueWriter.write(Main.socket, UpdateLandingComposer.compose(data, code));
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.landing.RefreshLandingViewParser
 * JD-Core Version:    0.7.0.1
 */