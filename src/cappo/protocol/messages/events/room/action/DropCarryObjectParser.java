/*  1:   */ package cappo.protocol.messages.events.room.action;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ 
/*  7:   */ public class DropCarryObjectParser
/*  8:   */   extends IncomingMessageEvent
/*  9:   */ {
/* 10:   */   public void messageReceived(Connection Main)
/* 11:   */   {
/* 12:16 */     Avatar avatar = Main.avatar;
/* 13:17 */     if (avatar == null) {
/* 14:18 */       return;
/* 15:   */     }
/* 16:21 */     if (avatar.carryItemID > 0) {
/* 17:22 */       avatar.CarryItem(0);
/* 18:   */     }
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.action.DropCarryObjectParser
 * JD-Core Version:    0.7.0.1
 */