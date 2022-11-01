/*  1:   */ package cappo.protocol.messages.events.moderator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.collections.Utils;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import java.util.Map;
/* 11:   */ 
/* 12:   */ public class ModerateRoomParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection cn)
/* 16:   */   {
/* 17:17 */     if (!cn.playerData.allowRoomAlert()) {
/* 18:18 */       return;
/* 19:   */     }
/* 20:21 */     Avatar avatar = cn.avatar;
/* 21:22 */     if (avatar == null) {
/* 22:23 */       return;
/* 23:   */     }
/* 24:26 */     cn.currentPacket.readInt();
/* 25:27 */     int action = cn.currentPacket.readInt();
/* 26:28 */     String text = cn.currentPacket.readString();
/* 27:30 */     if (action < 2) {
/* 28:31 */       for (Avatar user : avatar.room.userList.values())
/* 29:   */       {
/* 30:32 */         user.cn.playerData.cautions += 1;
/* 31:33 */         Utils.AlertFromHotel(user.cn.socket, text);
/* 32:   */       }
/* 33:   */     } else {
/* 34:36 */       for (Avatar user : avatar.room.userList.values()) {
/* 35:37 */         Utils.AlertFromHotel(user.cn.socket, text);
/* 36:   */       }
/* 37:   */     }
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.moderator.ModerateRoomParser
 * JD-Core Version:    0.7.0.1
 */