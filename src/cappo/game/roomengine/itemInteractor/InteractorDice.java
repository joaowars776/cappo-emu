/*  1:   */ package cappo.game.roomengine.itemInteractor;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.collections.BaseItem;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  7:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  8:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.game.roomengine.roomevents.Dice_RUN;
/* 11:   */ 
/* 12:   */ public class InteractorDice
/* 13:   */   extends Interactor
/* 14:   */ {
/* 15:   */   public void OnPlace(RoomTask room, Connection User, GenericFloorItem Item)
/* 16:   */   {
/* 17:19 */     Item.setIntData(0);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void OnTriggerFloor(RoomTask room, Connection User, FloorItem fitem, int request, boolean UserHasRights)
/* 21:   */   {
/* 22:24 */     GenericFloorItem item = (GenericFloorItem)fitem;
/* 23:26 */     if (item.baseItem.interactorType != Interactor.InteractorType.dice) {
/* 24:27 */       return;
/* 25:   */     }
/* 26:30 */     if (item.getIntData() == -1) {
/* 27:31 */       return;
/* 28:   */     }
/* 29:34 */     if (User != null)
/* 30:   */     {
/* 31:35 */       int difX = item.getX() - User.avatar.x;
/* 32:36 */       int difY = item.getY() - User.avatar.y;
/* 33:38 */       if ((difX > 1) || (difX < -1) || (difY > 1) || (difY < -1)) {
/* 34:39 */         return;
/* 35:   */       }
/* 36:   */     }
/* 37:43 */     if (request != -1)
/* 38:   */     {
/* 39:44 */       item.setIntData(-1);
/* 40:45 */       room.floorItemUpdateNeeded(item);
/* 41:46 */       room.addItemEvent(new Dice_RUN(item), 5);
/* 42:   */     }
/* 43:48 */     else if (item.getIntData() > 0)
/* 44:   */     {
/* 45:49 */       item.setIntData(0);
/* 46:50 */       room.floorItemUpdateNeeded(item);
/* 47:   */     }
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void OnTriggerWall(RoomTask room, Connection User, GenericWallItem Item, int Request, boolean UserHasRights) {}
/* 51:   */   
/* 52:   */   public void OnPickUp(RoomTask room, Connection User, GenericFloorItem Item) {}
/* 53:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.itemInteractor.InteractorDice
 * JD-Core Version:    0.7.0.1
 */