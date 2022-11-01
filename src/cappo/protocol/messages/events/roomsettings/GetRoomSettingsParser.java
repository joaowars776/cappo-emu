/*  1:   */ package cappo.protocol.messages.events.roomsettings;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.roomsettings.RoomSettingsDataComposer;
/*  9:   */ 
/* 10:   */ public class GetRoomSettingsParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection cn)
/* 14:   */   {
/* 15:19 */     Avatar avatar = cn.avatar;
/* 16:20 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 17:21 */       return;
/* 18:   */     }
/* 19:24 */     QueueWriter.write(cn.socket, RoomSettingsDataComposer.compose(avatar.room.roomData));
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.roomsettings.GetRoomSettingsParser
 * JD-Core Version:    0.7.0.1
 */