/*  1:   */ package cappo.protocol.messages.events.room.session;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ 
/*  7:   */ public class OpenFlatConnectionParser
/*  8:   */   extends IncomingMessageEvent
/*  9:   */ {
/* 10:   */   public void messageReceived(Connection cn)
/* 11:   */   {
/* 12:16 */     cn.loadRoom(cn.currentPacket.readInt(), cn.currentPacket.readString());
/* 13:   */   }
/* 14:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.session.OpenFlatConnectionParser
 * JD-Core Version:    0.7.0.1
 */