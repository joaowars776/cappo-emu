/*  1:   */ package cappo.protocol.messages.events.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.player.PlayerData;
/*  6:   */ import cappo.game.player.messenger.PlayerMessenger;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.friendlist.BuddyRequestsComposer;
/*  9:   */ 
/* 10:   */ public class GetBuddyRequestsParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection Main)
/* 14:   */   {
/* 15:17 */     QueueWriter.write(Main.socket, BuddyRequestsComposer.compose(Main.playerData.messenger.getFriendRequests()));
/* 16:   */   }
/* 17:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.friendlist.GetBuddyRequestsParser
 * JD-Core Version:    0.7.0.1
 */