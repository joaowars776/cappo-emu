/*  1:   */ package cappo.protocol.messages.events.room.action;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ 
/*  9:   */ public class ShareCarryObjectParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:16 */     Avatar avatar = Main.avatar;
/* 15:17 */     if (avatar == null) {
/* 16:18 */       return;
/* 17:   */     }
/* 18:21 */     if (avatar.carryItemID < 1) {
/* 19:22 */       return;
/* 20:   */     }
/* 21:25 */     Avatar clientAvatar = avatar.room.getRoomUserById(Main.currentPacket.readInt());
/* 22:26 */     if (clientAvatar == null) {
/* 23:27 */       return;
/* 24:   */     }
/* 25:30 */     clientAvatar.CarryItem(avatar.carryItemID);
/* 26:31 */     avatar.CarryItem(0);
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.action.ShareCarryObjectParser
 * JD-Core Version:    0.7.0.1
 */