/*  1:   */ package cappo.game.roomengine.itemInteractor;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.collections.BaseItem;
/*  6:   */ import cappo.game.roomengine.SquareFlagManager;
/*  7:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  8:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  9:   */ import cappo.game.roomengine.entity.item.floor.RoomFloorItemData.AffectedTile;
/* 10:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/* 11:   */ import java.util.List;
/* 12:   */ 
/* 13:   */ public class InteractorGate
/* 14:   */   extends Interactor
/* 15:   */ {
/* 16:   */   public void OnPlace(RoomTask room, Connection User, GenericFloorItem Item) {}
/* 17:   */   
/* 18:   */   public void OnTriggerFloor(RoomTask room, Connection User, FloorItem fitem, int Request, boolean UserHasRights)
/* 19:   */   {
/* 20:25 */     if (!UserHasRights) {
/* 21:26 */       return;
/* 22:   */     }
/* 23:29 */     GenericFloorItem floorItem = (GenericFloorItem)fitem;
/* 24:31 */     if (floorItem.baseItem.interactionCount < 2) {
/* 25:32 */       return;
/* 26:   */     }
/* 27:35 */     floorItem.setIntData((floorItem.getIntData() + 1) % floorItem.baseItem.interactionCount);
/* 28:   */     
/* 29:37 */     boolean walkeable = floorItem.getIntData() != 0;
/* 30:38 */     List<RoomFloorItemData.AffectedTile> PointList = floorItem.getAffectedTiles();
/* 31:39 */     for (RoomFloorItemData.AffectedTile Tile : PointList) {
/* 32:40 */       room.squareFlag.SetFlag(Tile.xy, 4, walkeable);
/* 33:   */     }
/* 34:43 */     room.floorItemUpdateNeeded(floorItem);
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void OnTriggerWall(RoomTask room, Connection User, GenericWallItem Item, int Request, boolean UserHasRights) {}
/* 38:   */   
/* 39:   */   public void OnPickUp(RoomTask room, Connection User, GenericFloorItem Item) {}
/* 40:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.itemInteractor.InteractorGate
 * JD-Core Version:    0.7.0.1
 */