/*  1:   */ package cappo.protocol.messages.events.games.gamecenter;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.games.gamecenter.StatusGameComposer;
/*  8:   */ 
/*  9:   */ public class GetStatusGameParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:18 */     int gameTypeId = Main.currentPacket.readInt();
/* 15:19 */     QueueWriter.write(Main.socket, StatusGameComposer.compose(gameTypeId, 0));
/* 16:   */   }
/* 17:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.games.gamecenter.GetStatusGameParser
 * JD-Core Version:    0.7.0.1
 */