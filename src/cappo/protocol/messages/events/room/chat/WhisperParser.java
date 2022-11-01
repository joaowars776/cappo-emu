/*  1:   */ package cappo.protocol.messages.events.room.chat;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.MessageWriter;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Clients;
/*  7:   */ import cappo.engine.player.Connection;
/*  8:   */ import cappo.engine.threadpools.RoomTask;
/*  9:   */ import cappo.game.player.PlayerData;
/* 10:   */ import cappo.game.roomengine.chat.wf.WordFilter;
/* 11:   */ import cappo.game.roomengine.chat.wf.WordFilterAction;
/* 12:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 13:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 14:   */ import cappo.protocol.messages.composers.room.chat.WhisperComposer;
/* 15:   */ 
/* 16:   */ public class WhisperParser
/* 17:   */   extends IncomingMessageEvent
/* 18:   */ {
/* 19:   */   public void messageReceived(Connection cn)
/* 20:   */   {
/* 21:24 */     Avatar avatar = cn.avatar;
/* 22:25 */     if (avatar == null) {
/* 23:26 */       return;
/* 24:   */     }
/* 25:29 */     String Params = cn.currentPacket.readString();
/* 26:   */     
/* 27:31 */     int find = Params.indexOf(" ");
/* 28:   */     
/* 29:33 */     PlayerData playerData = Clients.getPlayerData(Params.substring(0, find));
/* 30:34 */     if ((playerData == null) || (playerData.connection == null)) {
/* 31:35 */       return;
/* 32:   */     }
/* 33:38 */     Avatar clientAvatar = playerData.connection.avatar;
/* 34:39 */     if (clientAvatar == null) {
/* 35:40 */       return;
/* 36:   */     }
/* 37:43 */     if (avatar.room != clientAvatar.room) {
/* 38:44 */       return;
/* 39:   */     }
/* 40:47 */     if (!avatar.canChat()) {
/* 41:48 */       return;
/* 42:   */     }
/* 43:51 */     String text = Params.substring(find + 1);
/* 44:52 */     if (text.length() > 100) {
/* 45:53 */       return;
/* 46:   */     }
/* 47:56 */     WordFilterAction action = WordFilter.getAction(text);
/* 48:57 */     if ((action != null) && (action.run(cn))) {
/* 49:58 */       return;
/* 50:   */     }
/* 51:61 */     int styleId = cn.currentPacket.readInt();
/* 52:   */     
/* 53:63 */     MessageWriter message = WhisperComposer.compose(avatar.virtualId, text, 0, styleId, null, -1);
/* 54:64 */     QueueWriter.write(cn.socket, message);
/* 55:65 */     QueueWriter.writeAndFlush(playerData.connection.socket, message);
/* 56:66 */     MessageWriter toStaff = WhisperComposer.compose(avatar.virtualId, "TO (" + playerData.userName + "): " + text, 0, styleId, null, -1);
/* 57:67 */     avatar.room.sendMessage(toStaff, new int[] { 5 });
/* 58:   */   }
/* 59:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.chat.WhisperParser
 * JD-Core Version:    0.7.0.1
 */