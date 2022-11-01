/*  1:   */ package cappo.protocol.messages.events.roomsettings;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.roomengine.RoomData;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.game.roomengine.settings.ModerationPermissions;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.roomsettings.RoomMuteStateComposer;
/* 10:   */ 
/* 11:   */ public class SetRoomMuteStateParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection cn)
/* 15:   */   {
/* 16:21 */     Avatar avatar = cn.avatar;
/* 17:22 */     if (avatar == null) {
/* 18:23 */       return;
/* 19:   */     }
/* 20:26 */     RoomTask room = avatar.room;
/* 21:27 */     RoomData roomData = room.roomData;
/* 22:29 */     if (roomData.modPermissions.permissionsMute == 1)
/* 23:   */     {
/* 24:30 */       if ((avatar.controllerLevel == 1) || 
/* 25:31 */         (avatar.controllerLevel >= 3)) {}
/* 26:   */     }
/* 27:36 */     else if (avatar.controllerLevel < 4) {
/* 28:37 */       return;
/* 29:   */     }
/* 30:41 */     roomData.muteAllOn = (!roomData.muteAllOn);
/* 31:42 */     room.sendMessage(RoomMuteStateComposer.compose(roomData.muteAllOn), new int[] { 4, 5 });
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.roomsettings.SetRoomMuteStateParser
 * JD-Core Version:    0.7.0.1
 */