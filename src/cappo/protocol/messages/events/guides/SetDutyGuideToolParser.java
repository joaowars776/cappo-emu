/*  1:   */ package cappo.protocol.messages.events.guides;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.guides.UpdateGuideToolComposer;
/*  8:   */ 
/*  9:   */ public class SetDutyGuideToolParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */     throws Exception
/* 14:   */   {
/* 15:17 */     boolean onDuty = Main.currentPacket.readBoolean();
/* 16:18 */     Main.currentPacket.readBoolean();
/* 17:19 */     Main.currentPacket.readBoolean();
/* 18:20 */     QueueWriter.write(Main.socket, UpdateGuideToolComposer.compose(onDuty, 99, 99));
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.guides.SetDutyGuideToolParser
 * JD-Core Version:    0.7.0.1
 */