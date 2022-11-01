/*  1:   */ package cappo.protocol.messages.events.room.chat;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.room.chat.UserTypingComposer;
/*  8:   */ 
/*  9:   */ public class CancelTypingParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:17 */     Avatar avatar = Main.avatar;
/* 15:18 */     if (avatar == null) {
/* 16:19 */       return;
/* 17:   */     }
/* 18:22 */     avatar.room.sendMessage(UserTypingComposer.compose(avatar.virtualId, Boolean.valueOf(false)));
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.chat.CancelTypingParser
 * JD-Core Version:    0.7.0.1
 */