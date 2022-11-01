/*  1:   */ package cappo.protocol.messages.events.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.player.SnowWarPlayerData;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ 
/*  8:   */ public class ThrowSnowballAtPositionParser
/*  9:   */   extends IncomingMessageEvent
/* 10:   */ {
/* 11:   */   public void messageReceived(Connection Main)
/* 12:   */   {
/* 13:17 */     Main.snowWarPlayerData.throwSnowballAtPosition(Main.currentPacket.readInt(), Main.currentPacket.readInt(), Main.currentPacket.readInt());
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.games.snowwar.ThrowSnowballAtPositionParser
 * JD-Core Version:    0.7.0.1
 */