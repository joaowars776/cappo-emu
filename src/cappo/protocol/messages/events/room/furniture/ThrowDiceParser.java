/*  1:   */ package cappo.protocol.messages.events.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.collections.BaseItem;
/*  7:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  8:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  9:   */ import cappo.game.roomengine.itemInteractor.Interactor;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ 
/* 12:   */ public class ThrowDiceParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:17 */     Avatar avatar = Main.avatar;
/* 18:18 */     if (avatar == null) {
/* 19:19 */       return;
/* 20:   */     }
/* 21:22 */     FloorItem item = avatar.room.getFloorItem(Main.currentPacket.readInt());
/* 22:23 */     if (item == null) {
/* 23:24 */       return;
/* 24:   */     }
/* 25:27 */     item.baseItem.interactor.OnTriggerFloor(avatar.room, Main, item, 0, false);
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.furniture.ThrowDiceParser
 * JD-Core Version:    0.7.0.1
 */