/*  1:   */ package cappo.protocol.messages.events.room.avatar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.room.action.UserDanceComposer;
/*  9:   */ 
/* 10:   */ public class DanceParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection Main)
/* 14:   */   {
/* 15:17 */     Avatar avatar = Main.avatar;
/* 16:18 */     if (avatar == null) {
/* 17:19 */       return;
/* 18:   */     }
/* 19:22 */     avatar.idleTime = 0;
/* 20:   */     
/* 21:24 */     int DanceId = Main.currentPacket.readInt();
/* 22:25 */     if (DanceId != 1) {
/* 23:26 */       if (DanceId < 0) {
/* 24:27 */         DanceId = 0;
/* 25:28 */       } else if (DanceId > 8) {
/* 26:29 */         DanceId = 0;
/* 27:   */       }
/* 28:   */     }
/* 29:33 */     if ((DanceId > 0) && (avatar.carryItemID > 0)) {
/* 30:34 */       avatar.CarryItem(0);
/* 31:   */     }
/* 32:37 */     avatar.DanceId = DanceId;
/* 33:   */     
/* 34:39 */     avatar.room.sendMessage(UserDanceComposer.compose(avatar.virtualId, avatar.DanceId));
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.avatar.DanceParser
 * JD-Core Version:    0.7.0.1
 */