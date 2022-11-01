/*  1:   */ package cappo.game.roomengine.itemInteractor;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  7:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  8:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  9:   */ 
/* 10:   */ public class InteractorOneWayGate
/* 11:   */   extends Interactor
/* 12:   */ {
/* 13:   */   public void OnPlace(RoomTask room, Connection User, GenericFloorItem Item)
/* 14:   */   {
/* 15:19 */     Item.setIntData(0);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void OnTriggerFloor(RoomTask room, Connection User, FloorItem floorItem, int Request, boolean UserHasRights)
/* 19:   */   {
/* 20:24 */     if (User == null) {
/* 21:25 */       return;
/* 22:   */     }
/* 23:28 */     Avatar avatar = User.avatar;
/* 24:   */     
/* 25:30 */     GenericFloorItem item = (GenericFloorItem)floorItem;
/* 26:   */     
/* 27:32 */     byte[] xy = floorItem.SquareInFront();
/* 28:33 */     if (((avatar.x == xy[0]) && (avatar.y == xy[1])) || ((avatar.x == floorItem.getX()) && (avatar.y == floorItem.getY())))
/* 29:   */     {
/* 30:34 */       item.setIntData(1);
/* 31:35 */       room.floorItemUpdateNeeded(floorItem);
/* 32:36 */       xy = floorItem.SquareBehind();
/* 33:37 */       avatar.allowOverride = true;
/* 34:38 */       avatar.moveTo(xy[0], xy[1]);
/* 35:   */     }
/* 36:   */     else
/* 37:   */     {
/* 38:40 */       avatar.moveTo(xy[0], xy[1]);
/* 39:   */     }
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void OnTriggerWall(RoomTask room, Connection User, GenericWallItem Item, int Request, boolean UserHasRights) {}
/* 43:   */   
/* 44:   */   public void OnPickUp(RoomTask room, Connection User, GenericFloorItem Item) {}
/* 45:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.itemInteractor.InteractorOneWayGate
 * JD-Core Version:    0.7.0.1
 */