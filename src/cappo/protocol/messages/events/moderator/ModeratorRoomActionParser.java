/*  1:   */ package cappo.protocol.messages.events.moderator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.player.PlayerData;
/*  7:   */ import cappo.game.roomengine.RoomData;
/*  8:   */ import cappo.game.roomengine.RoomManager;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ import java.util.Map;
/* 12:   */ 
/* 13:   */ public class ModeratorRoomActionParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection cn)
/* 17:   */   {
/* 18:18 */     if (!cn.playerData.allowModTools()) {
/* 19:19 */       return;
/* 20:   */     }
/* 21:22 */     RoomData room = RoomManager.getRoom(cn.currentPacket.readInt());
/* 22:23 */     if (room == null) {
/* 23:24 */       return;
/* 24:   */     }
/* 25:27 */     if (cn.currentPacket.readInt() == 1) {
/* 26:28 */       room.state = 1;
/* 27:   */     }
/* 28:31 */     if (cn.currentPacket.readInt() == 1)
/* 29:   */     {
/* 30:32 */       room.name = "Inappropriate to Hotel Management";
/* 31:33 */       room.description = "Inappropriate to Hotel Management";
/* 32:34 */       room.tags = new String[0];
/* 33:   */     }
/* 34:37 */     if ((cn.currentPacket.readInt() == 1) && 
/* 35:38 */       (room.room != null)) {
/* 36:39 */       for (Avatar user : room.room.userList.values()) {
/* 37:40 */         room.room.removeUserFromRoom(user.cn, true, false);
/* 38:   */       }
/* 39:   */     }
/* 40:   */   }
/* 41:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.moderator.ModeratorRoomActionParser
 * JD-Core Version:    0.7.0.1
 */