/*  1:   */ package cappo.protocol.messages.events.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.messenger.PlayerMessenger;
/*  9:   */ import cappo.game.roomengine.chat.wf.WordFilter;
/* 10:   */ import cappo.game.roomengine.chat.wf.WordFilterAction;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.friendlist.BuddyMessageComposer;
/* 13:   */ import cappo.protocol.messages.composers.friendlist.InstantMessageErrorComposer;
/* 14:   */ 
/* 15:   */ public class SendMsgParser
/* 16:   */   extends IncomingMessageEvent
/* 17:   */ {
/* 18:   */   public void messageReceived(Connection cn)
/* 19:   */   {
/* 20:22 */     int userId = cn.currentPacket.readInt();
/* 21:   */     
/* 22:24 */     PlayerData client = Clients.getPlayerDataLoaded(userId);
/* 23:25 */     if ((client == null) || (client.connection == null))
/* 24:   */     {
/* 25:26 */       QueueWriter.write(cn.socket, InstantMessageErrorComposer.compose(5, userId, ""));
/* 26:27 */       return;
/* 27:   */     }
/* 28:30 */     if (!cn.playerData.messenger.haveFriend(client.userId))
/* 29:   */     {
/* 30:31 */       QueueWriter.write(cn.socket, InstantMessageErrorComposer.compose(6, userId, ""));
/* 31:32 */       return;
/* 32:   */     }
/* 33:35 */     String text = cn.currentPacket.readString();
/* 34:36 */     if (text.isEmpty()) {
/* 35:37 */       return;
/* 36:   */     }
/* 37:40 */     WordFilterAction action = WordFilter.getAction(text);
/* 38:41 */     if ((action != null) && (action.run(cn))) {
/* 39:42 */       return;
/* 40:   */     }
/* 41:45 */     QueueWriter.writeAndFlush(client.connection.socket, BuddyMessageComposer.compose(cn.playerData.userId, text));
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.friendlist.SendMsgParser
 * JD-Core Version:    0.7.0.1
 */