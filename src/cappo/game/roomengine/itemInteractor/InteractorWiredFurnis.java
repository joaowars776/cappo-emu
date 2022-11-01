/*  1:   */ package cappo.game.roomengine.itemInteractor;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.collections.BaseItem;
/*  7:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  8:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  9:   */ import cappo.game.roomengine.entity.item.floor.wired.WiredItemBase;
/* 10:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/* 11:   */ import cappo.game.roomengine.wired.WiredManager;
/* 12:   */ import cappo.protocol.messages.composers.userdefinedroomevents.OpenWiredComposer;
/* 13:   */ 
/* 14:   */ public class InteractorWiredFurnis
/* 15:   */   extends Interactor
/* 16:   */ {
/* 17:   */   public void OnPlace(RoomTask room, Connection User, GenericFloorItem Item)
/* 18:   */   {
/* 19:21 */     if ((Item instanceof WiredItemBase)) {
/* 20:22 */       room.wiredManager.registerWired((WiredItemBase)Item, Item.baseItem.itemType);
/* 21:   */     }
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void OnPickUp(RoomTask room, Connection User, GenericFloorItem Item)
/* 25:   */   {
/* 26:28 */     if ((Item instanceof WiredItemBase)) {
/* 27:29 */       room.wiredManager.removeWired((WiredItemBase)Item, Item.baseItem.itemType, Item.getXy());
/* 28:   */     }
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void OnTriggerFloor(RoomTask room, Connection User, FloorItem fitem, int Request, boolean UserHasRights)
/* 32:   */   {
/* 33:35 */     if (User == null) {
/* 34:36 */       return;
/* 35:   */     }
/* 36:39 */     if (!UserHasRights) {
/* 37:40 */       return;
/* 38:   */     }
/* 39:43 */     GenericFloorItem Item = (GenericFloorItem)fitem;
/* 40:   */     
/* 41:45 */     Item.setIntData(Item.getIntData() == 0 ? 1 : 0);
/* 42:46 */     room.floorItemUpdateNeeded(Item);
/* 43:   */     
/* 44:48 */     QueueWriter.writeAndFlush(User.socket, OpenWiredComposer.compose(Item.itemId));
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void OnTriggerWall(RoomTask room, Connection User, GenericWallItem Item, int Request, boolean UserHasRights) {}
/* 48:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.itemInteractor.InteractorWiredFurnis
 * JD-Core Version:    0.7.0.1
 */