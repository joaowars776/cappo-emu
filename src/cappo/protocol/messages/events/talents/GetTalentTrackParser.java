/*  1:   */ package cappo.protocol.messages.events.talents;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ 
/*  7:   */ public class GetTalentTrackParser
/*  8:   */   extends IncomingMessageEvent
/*  9:   */ {
/* 10:   */   public void messageReceived(Connection Main)
/* 11:   */     throws Exception
/* 12:   */   {
/* 13:15 */     Main.currentPacket.readString();
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.talents.GetTalentTrackParser
 * JD-Core Version:    0.7.0.1
 */