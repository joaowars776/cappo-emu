/*  1:   */ package cappo.protocol.messages.events.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.room.action.AvatarExpressionComposer;
/*  9:   */ import cappo.protocol.messages.composers.users.UserRespectedComposer;
/* 10:   */ 
/* 11:   */ public class RespectUserParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection Main)
/* 15:   */   {
/* 16:19 */     Avatar avatar = Main.avatar;
/* 17:20 */     if (avatar == null) {
/* 18:21 */       return;
/* 19:   */     }
/* 20:24 */     if (Main.dailyRespectPoints < 1) {
/* 21:25 */       return;
/* 22:   */     }
/* 23:28 */     RoomTask room = avatar.room;
/* 24:   */     
/* 25:   */ 
/* 26:31 */     Avatar User = room.getRoomUserById(Main.currentPacket.readInt());
/* 27:32 */     if (User != null)
/* 28:   */     {
/* 29:33 */       room.sendMessage(UserRespectedComposer.compose(User.id, ++User.cn.respects));
/* 30:34 */       room.sendMessage(AvatarExpressionComposer.compose(avatar.virtualId, 7));
/* 31:35 */       Main.dailyRespectPoints -= 1;
/* 32:   */     }
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.users.RespectUserParser
 * JD-Core Version:    0.7.0.1
 */