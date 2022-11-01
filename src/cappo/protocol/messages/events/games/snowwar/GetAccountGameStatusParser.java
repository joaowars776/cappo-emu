/*  1:   */ package cappo.protocol.messages.events.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.games.snowwar.AccountGameStatusComposer;
/*  8:   */ 
/*  9:   */ public class GetAccountGameStatusParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:17 */     QueueWriter.write(Main.socket, AccountGameStatusComposer.compose(Main.currentPacket.readInt()));
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.games.snowwar.GetAccountGameStatusParser
 * JD-Core Version:    0.7.0.1
 */