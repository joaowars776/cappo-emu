/*  1:   */ package cappo.protocol.messages.events.games.gamecenter;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ import cappo.protocol.messages.composers.games.gamecenter.GameListComposer;
/*  7:   */ 
/*  8:   */ public class GetGameListParser
/*  9:   */   extends IncomingMessageEvent
/* 10:   */ {
/* 11:   */   public void messageReceived(Connection Main)
/* 12:   */   {
/* 13:17 */     QueueWriter.write(Main.socket, GameListComposer.compose());
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.games.gamecenter.GetGameListParser
 * JD-Core Version:    0.7.0.1
 */