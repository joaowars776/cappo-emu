/*  1:   */ package cappo.protocol.messages.events.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.MessageWriter;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Clients;
/*  7:   */ import cappo.engine.player.Connection;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.game.roomengine.chat.wf.WordFilter;
/* 10:   */ import cappo.game.roomengine.chat.wf.WordFilterAction;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.friendlist.RoomInviteComposer;
/* 13:   */ import cappo.protocol.messages.composers.friendlist.RoomInviteErrorComposer;
/* 14:   */ import java.util.ArrayList;
/* 15:   */ import java.util.Iterator;
/* 16:   */ import java.util.List;
/* 17:   */ 
/* 18:   */ public class SendRoomInviteParser
/* 19:   */   extends IncomingMessageEvent
/* 20:   */ {
/* 21:   */   public void messageReceived(Connection cn)
/* 22:   */   {
/* 23:26 */     List<Integer> SendTo = new ArrayList();
/* 24:27 */     List<Integer> Failed = new ArrayList();
/* 25:   */     
/* 26:29 */     int count = cn.currentPacket.readInt();
/* 27:30 */     if (count > 100) {
/* 28:31 */       return;
/* 29:   */     }
/* 30:34 */     for (int i = 0; i < count; i++) {
/* 31:35 */       SendTo.add(Integer.valueOf(cn.currentPacket.readInt()));
/* 32:   */     }
/* 33:39 */     String message = cn.currentPacket.readString();
/* 34:   */     
/* 35:41 */     WordFilterAction action = WordFilter.getAction(message);
/* 36:42 */     if ((action != null) && (action.run(cn))) {
/* 37:43 */       return;
/* 38:   */     }
/* 39:46 */     MessageWriter Message = RoomInviteComposer.compose(cn.playerData.userId, message);
/* 40:48 */     for (Iterator localIterator = SendTo.iterator(); localIterator.hasNext();)
/* 41:   */     {
/* 42:48 */       int UserId = ((Integer)localIterator.next()).intValue();
/* 43:49 */       PlayerData client = Clients.getPlayerData(UserId);
/* 44:50 */       if ((client == null) || (client.connection == null)) {
/* 45:51 */         Failed.add(Integer.valueOf(UserId));
/* 46:   */       } else {
/* 47:55 */         QueueWriter.writeAndFlush(client.connection.socket, Message);
/* 48:   */       }
/* 49:   */     }
/* 50:58 */     if (!Failed.isEmpty()) {
/* 51:59 */       QueueWriter.write(cn.socket, RoomInviteErrorComposer.compose(1, Failed));
/* 52:   */     }
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.friendlist.SendRoomInviteParser
 * JD-Core Version:    0.7.0.1
 */