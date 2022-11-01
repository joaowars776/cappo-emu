/*  1:   */ package cappo.protocol.messages.events.room.avatar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.room.action.AvatarExpressionComposer;
/*  9:   */ 
/* 10:   */ public class SetAvatarExpressionParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection Main)
/* 14:   */   {
/* 15:17 */     Avatar avatar = Main.avatar;
/* 16:18 */     if (avatar == null) {
/* 17:19 */       return;
/* 18:   */     }
/* 19:22 */     int expression = Main.currentPacket.readInt();
/* 20:23 */     if (expression == 5)
/* 21:   */     {
/* 22:24 */       avatar.idleTime = 9999;
/* 23:   */     }
/* 24:   */     else
/* 25:   */     {
/* 26:27 */       avatar.idleTime = 0;
/* 27:28 */       avatar.room.sendMessage(AvatarExpressionComposer.compose(avatar.virtualId, expression));
/* 28:   */     }
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.avatar.SetAvatarExpressionParser
 * JD-Core Version:    0.7.0.1
 */