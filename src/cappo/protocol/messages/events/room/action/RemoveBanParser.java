/*  1:   */ package cappo.protocol.messages.events.room.action;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.RoomData;
/*  7:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  8:   */ import cappo.game.roomengine.settings.ModerationPermissions;
/*  9:   */ import cappo.game.roomengine.settings.PlayerBan;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ 
/* 12:   */ public class RemoveBanParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection cn)
/* 16:   */   {
/* 17:21 */     Avatar avatar = cn.avatar;
/* 18:22 */     if (avatar == null) {
/* 19:23 */       return;
/* 20:   */     }
/* 21:26 */     RoomTask room = avatar.room;
/* 22:27 */     RoomData roomData = room.roomData;
/* 23:29 */     if (roomData.modPermissions.permissionsBan == 1)
/* 24:   */     {
/* 25:30 */       if ((avatar.controllerLevel == 1) || 
/* 26:31 */         (avatar.controllerLevel >= 3)) {}
/* 27:   */     }
/* 28:36 */     else if (avatar.controllerLevel < 4) {
/* 29:37 */       return;
/* 30:   */     }
/* 31:41 */     PlayerBan playerBan = room.removeBan(cn.currentPacket.readInt());
/* 32:42 */     if (playerBan == null) {}
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.action.RemoveBanParser
 * JD-Core Version:    0.7.0.1
 */