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
/* 12:   */ public class DiceOffParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:18 */     Avatar avatar = Main.avatar;
/* 18:19 */     if (avatar == null) {
/* 19:20 */       return;
/* 20:   */     }
/* 21:23 */     RoomTask room = avatar.room;
/* 22:   */     
/* 23:25 */     FloorItem item = room.getFloorItem(Main.currentPacket.readInt());
/* 24:26 */     if (item == null) {
/* 25:27 */       return;
/* 26:   */     }
/* 27:30 */     item.baseItem.interactor.OnTriggerFloor(room, Main, item, -1, false);
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.furniture.DiceOffParser
 * JD-Core Version:    0.7.0.1
 */