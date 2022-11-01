/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.navigator.EventComposer;
/*  8:   */ 
/*  9:   */ public class CancelEventParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:18 */     Avatar avatar = Main.avatar;
/* 15:19 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 16:20 */       return;
/* 17:   */     }
/* 18:23 */     avatar.room.roomData.event = null;
/* 19:24 */     avatar.room.sendMessage(EventComposer.compose());
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.CancelEventParser
 * JD-Core Version:    0.7.0.1
 */