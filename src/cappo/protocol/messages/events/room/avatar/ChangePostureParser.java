/*  1:   */ package cappo.protocol.messages.events.room.avatar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ 
/*  8:   */ public class ChangePostureParser
/*  9:   */   extends IncomingMessageEvent
/* 10:   */ {
/* 11:   */   public void messageReceived(Connection Main)
/* 12:   */   {
/* 13:16 */     Avatar avatar = Main.avatar;
/* 14:17 */     if (avatar == null) {
/* 15:18 */       return;
/* 16:   */     }
/* 17:21 */     int posture = Main.currentPacket.readInt();
/* 18:22 */     if (posture == 1) {
/* 19:23 */       avatar.setStatus("sit", Float.toString(avatar.z + 0.5F) + " 1");
/* 20:   */     } else {
/* 21:25 */       avatar.setStatus("", "");
/* 22:   */     }
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.avatar.ChangePostureParser
 * JD-Core Version:    0.7.0.1
 */