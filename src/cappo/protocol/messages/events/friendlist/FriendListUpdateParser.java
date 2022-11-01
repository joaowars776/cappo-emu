/*  1:   */ package cappo.protocol.messages.events.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.player.PlayerData;
/*  6:   */ import cappo.game.player.messenger.PlayerMessenger;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ 
/*  9:   */ public class FriendListUpdateParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:16 */     QueueWriter.write(Main.socket, Main.playerData.messenger.getFriendUpdstes());
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.friendlist.FriendListUpdateParser
 * JD-Core Version:    0.7.0.1
 */