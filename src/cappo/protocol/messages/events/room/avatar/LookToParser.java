/*  1:   */ package cappo.protocol.messages.events.room.avatar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.games.snowwar.Direction8;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ 
/*  9:   */ public class LookToParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:17 */     Avatar avatar = Main.avatar;
/* 15:18 */     if (avatar == null) {
/* 16:19 */       return;
/* 17:   */     }
/* 18:22 */     int X = Main.currentPacket.readInt();
/* 19:23 */     int Y = Main.currentPacket.readInt();
/* 20:   */     
/* 21:25 */     avatar.idleTime = 0;
/* 22:27 */     if ((X != avatar.x) || (Y != avatar.y)) {
/* 23:28 */       avatar.SetRot(Direction8.getRot(avatar.x, avatar.y, X, Y));
/* 24:   */     }
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.avatar.LookToParser
 * JD-Core Version:    0.7.0.1
 */