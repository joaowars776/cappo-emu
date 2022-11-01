/*  1:   */ package cappo.protocol.messages.events.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ 
/*  8:   */ public class MoveAvatarParser
/*  9:   */   extends IncomingMessageEvent
/* 10:   */ {
/* 11:   */   public void messageReceived(Connection Main)
/* 12:   */   {
/* 13:16 */     Avatar avatar = Main.avatar;
/* 14:17 */     if (avatar == null) {
/* 15:18 */       return;
/* 16:   */     }
/* 17:21 */     if (Main.teleport != null) {
/* 18:22 */       return;
/* 19:   */     }
/* 20:25 */     avatar.idleTime = 0;
/* 21:   */     
/* 22:27 */     int targetX = Main.currentPacket.readInt();
/* 23:28 */     int targetY = Main.currentPacket.readInt();
/* 24:30 */     if ((targetX != avatar.x) || (targetY != avatar.y)) {
/* 25:31 */       avatar.moveTo(targetX, targetY);
/* 26:   */     }
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.MoveAvatarParser
 * JD-Core Version:    0.7.0.1
 */