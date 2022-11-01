/*  1:   */ package cappo.protocol.messages.events.room.session;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ 
/*  8:   */ public class QuitParser
/*  9:   */   extends IncomingMessageEvent
/* 10:   */ {
/* 11:   */   public void messageReceived(Connection cn)
/* 12:   */   {
/* 13:17 */     Avatar avatar = cn.avatar;
/* 14:18 */     if (avatar == null) {
/* 15:19 */       return;
/* 16:   */     }
/* 17:22 */     RoomTask room = avatar.room;
/* 18:23 */     if (room == null) {
/* 19:24 */       return;
/* 20:   */     }
/* 21:27 */     room.removeUserFromRoom(cn, true, false);
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.session.QuitParser
 * JD-Core Version:    0.7.0.1
 */