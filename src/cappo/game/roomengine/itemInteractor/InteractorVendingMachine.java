/*  1:   */ package cappo.game.roomengine.itemInteractor;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.collections.BaseItem;
/*  6:   */ import cappo.game.collections.Utils;
/*  7:   */ import cappo.game.games.snowwar.Direction8;
/*  8:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  9:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/* 10:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/* 11:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 12:   */ import cappo.game.roomengine.roomevents.VengingMachineClose;
/* 13:   */ import java.util.List;
/* 14:   */ 
/* 15:   */ public class InteractorVendingMachine
/* 16:   */   extends Interactor
/* 17:   */ {
/* 18:   */   public void OnPlace(RoomTask room, Connection User, GenericFloorItem Item)
/* 19:   */   {
/* 20:21 */     Item.setIntData(0);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void OnTriggerFloor(RoomTask room, Connection User, FloorItem fitem, int Request, boolean UserHasRights)
/* 24:   */   {
/* 25:26 */     if (User == null) {
/* 26:27 */       return;
/* 27:   */     }
/* 28:30 */     Avatar avatar = User.avatar;
/* 29:   */     
/* 30:32 */     GenericFloorItem Item = (GenericFloorItem)fitem;
/* 31:   */     
/* 32:34 */     byte[] xy = Item.SquareInFront();
/* 33:   */     
/* 34:36 */     boolean onFront = (avatar.x == xy[0]) && (avatar.y == xy[1]);
/* 35:37 */     boolean onTop = false;
/* 36:38 */     if (!onFront) {
/* 37:39 */       onFront = onTop = (avatar.x == Item.getX()) && (avatar.y == Item.getY()) ? 1 : 0;
/* 38:   */     }
/* 39:42 */     if (onFront)
/* 40:   */     {
/* 41:43 */       Item.setIntData(1);
/* 42:44 */       room.floorItemUpdateNeeded(Item);
/* 43:45 */       int vendingId = ((Integer)Item.baseItem.vendingIds.get(Utils.GetRandomNumber(0, Item.baseItem.vendingIds.size() - 1))).intValue();
/* 44:46 */       if (onTop) {
/* 45:47 */         avatar.SetRot(Item.getDir());
/* 46:   */       } else {
/* 47:49 */         avatar.SetRot(Item.getDir().rotateDirection180Degrees());
/* 48:   */       }
/* 49:51 */       avatar.CarryItem(vendingId);
/* 50:52 */       room.addItemEvent(new VengingMachineClose(Item), 5);
/* 51:   */     }
/* 52:   */     else
/* 53:   */     {
/* 54:54 */       avatar.moveTo(xy[0], xy[1]);
/* 55:   */     }
/* 56:   */   }
/* 57:   */   
/* 58:   */   public void OnTriggerWall(RoomTask room, Connection User, GenericWallItem Item, int Request, boolean UserHasRights) {}
/* 59:   */   
/* 60:   */   public void OnPickUp(RoomTask room, Connection User, GenericFloorItem Item) {}
/* 61:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.itemInteractor.InteractorVendingMachine
 * JD-Core Version:    0.7.0.1
 */