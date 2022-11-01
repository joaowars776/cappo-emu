/*  1:   */ package cappo.protocol.messages.events.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.collections.BaseItem;
/*  7:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  8:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  9:   */ import cappo.game.roomengine.itemInteractor.Interactor;
/* 10:   */ import cappo.game.roomengine.wired.WiredManager;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ 
/* 13:   */ public class UseFurnitureParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection Main)
/* 17:   */   {
/* 18:19 */     Avatar avatar = Main.avatar;
/* 19:20 */     if (avatar == null) {
/* 20:21 */       return;
/* 21:   */     }
/* 22:24 */     RoomTask room = avatar.room;
/* 23:   */     
/* 24:26 */     FloorItem item = room.getFloorItem(Main.currentPacket.readInt());
/* 25:27 */     if (item == null) {
/* 26:28 */       return;
/* 27:   */     }
/* 28:31 */     item.baseItem.interactor.OnTriggerFloor(room, Main, item, Main.currentPacket.readInt(), (avatar.controllerLevel == 1) || (avatar.controllerLevel >= 4));
/* 29:32 */     room.wiredManager.parseWiredMutacion(Main, item);
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.UseFurnitureParser
 * JD-Core Version:    0.7.0.1
 */