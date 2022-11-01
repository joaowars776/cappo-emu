/*  1:   */ package cappo.game.roomengine.itemInteractor;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.collections.BaseItem;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  7:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  8:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  9:   */ 
/* 10:   */ public class InteractorSimple
/* 11:   */   extends Interactor
/* 12:   */ {
/* 13:   */   public void OnPlace(RoomTask room, Connection User, GenericFloorItem Item) {}
/* 14:   */   
/* 15:   */   public void OnTriggerFloor(RoomTask room, Connection User, FloorItem fItem, int Request, boolean UserHasRights)
/* 16:   */   {
/* 17:21 */     if (!UserHasRights) {
/* 18:22 */       return;
/* 19:   */     }
/* 20:25 */     if (fItem.baseItem.interactionCount < 2) {
/* 21:26 */       return;
/* 22:   */     }
/* 23:29 */     GenericFloorItem Item = (GenericFloorItem)fItem;
/* 24:   */     
/* 25:31 */     Item.incIntDataMod(1, Item.baseItem.interactionCount);
/* 26:   */     
/* 27:33 */     room.floorItemUpdateNeeded(Item);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void OnTriggerWall(RoomTask room, Connection User, GenericWallItem Item, int Request, boolean UserHasRights)
/* 31:   */   {
/* 32:38 */     if (!UserHasRights) {
/* 33:39 */       return;
/* 34:   */     }
/* 35:42 */     if (Item.baseItem.interactionCount < 2) {
/* 36:43 */       return;
/* 37:   */     }
/* 38:46 */     Item.setIntData(Item.incIntData(1) % Item.baseItem.interactionCount);
/* 39:47 */     room.wallItemUpdateNeeded(Item);
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void OnPickUp(RoomTask room, Connection User, GenericFloorItem Item) {}
/* 43:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.itemInteractor.InteractorSimple
 * JD-Core Version:    0.7.0.1
 */