/*  1:   */ package cappo.protocol.messages.events.room.avatar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ 
/*  8:   */ public class SignParser
/*  9:   */   extends IncomingMessageEvent
/* 10:   */ {
/* 11:   */   public void messageReceived(Connection Main)
/* 12:   */   {
/* 13:16 */     Avatar avatar = Main.avatar;
/* 14:17 */     if (avatar == null) {
/* 15:18 */       return;
/* 16:   */     }
/* 17:21 */     int SignId = Main.currentPacket.readInt();
/* 18:22 */     if ((SignId < 0) || (SignId > 17)) {
/* 19:23 */       return;
/* 20:   */     }
/* 21:26 */     avatar.idleTime = 0;
/* 22:27 */     avatar.setStatus("sign", Integer.toString(SignId));
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.avatar.SignParser
 * JD-Core Version:    0.7.0.1
 */