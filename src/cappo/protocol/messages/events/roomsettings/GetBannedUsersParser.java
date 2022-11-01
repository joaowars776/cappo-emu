/*  1:   */ package cappo.protocol.messages.events.roomsettings;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.roomsettings.BannedUsersComposer;
/*  9:   */ import java.util.Map;
/* 10:   */ 
/* 11:   */ public class GetBannedUsersParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection cn)
/* 15:   */   {
/* 16:19 */     Avatar avatar = cn.avatar;
/* 17:20 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 18:21 */       return;
/* 19:   */     }
/* 20:24 */     QueueWriter.write(cn.socket, BannedUsersComposer.compose(avatar.room.roomId, avatar.room.usersBanned.values()));
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.roomsettings.GetBannedUsersParser
 * JD-Core Version:    0.7.0.1
 */