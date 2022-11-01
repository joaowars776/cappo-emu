/*  1:   */ package cappo.protocol.messages.events.room.action;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.data.AvatarData;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.navigator.DoorBellNoAnswerComposer;
/* 11:   */ import cappo.protocol.messages.composers.room.session.FlatAccessibleComposer;
/* 12:   */ 
/* 13:   */ public class LetUserInParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection Main)
/* 17:   */   {
/* 18:20 */     String userName = Main.currentPacket.readString();
/* 19:21 */     PlayerData client = Clients.getPlayerData(userName);
/* 20:22 */     if ((client == null) || (client.connection == null)) {
/* 21:23 */       return;
/* 22:   */     }
/* 23:26 */     Connection clientCn = client.connection;
/* 24:27 */     if (clientCn == null) {
/* 25:28 */       return;
/* 26:   */     }
/* 27:31 */     if (clientCn.avatar != null) {
/* 28:32 */       return;
/* 29:   */     }
/* 30:35 */     if (clientCn.avatarData.LoadingRoom != 0) {
/* 31:36 */       return;
/* 32:   */     }
/* 33:39 */     if (Main.currentPacket.readBoolean()) {
/* 34:40 */       QueueWriter.writeAndFlush(clientCn.socket, FlatAccessibleComposer.compose(""));
/* 35:   */     } else {
/* 36:42 */       QueueWriter.writeAndFlush(clientCn.socket, DoorBellNoAnswerComposer.compose());
/* 37:   */     }
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.action.LetUserInParser
 * JD-Core Version:    0.7.0.1
 */