/*  1:   */ package cappo.game.roomengine.itemInteractor;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.collections.Teleports;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  7:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  8:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.game.roomengine.roomevents.Teleport_CLOSE;
/* 11:   */ import cappo.game.roomengine.roomevents.Teleport_IN;
/* 12:   */ 
/* 13:   */ public class InteractorTeleport
/* 14:   */   extends Interactor
/* 15:   */ {
/* 16:   */   public class TeleportAttach
/* 17:   */   {
/* 18:   */     public int itemId;
/* 19:   */     public int roomId;
/* 20:   */     
/* 21:   */     public TeleportAttach(int teleId, int teleportRoom)
/* 22:   */     {
/* 23:25 */       this.itemId = teleId;
/* 24:26 */       this.roomId = teleportRoom;
/* 25:   */     }
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void OnPlace(RoomTask room, Connection User, GenericFloorItem Item)
/* 29:   */   {
/* 30:32 */     Item.setIntData(0);
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void OnTriggerFloor(RoomTask room, Connection User, FloorItem fItem, int Request, boolean UserHasRights)
/* 34:   */   {
/* 35:37 */     if ((User == null) || (User.teleport != null)) {
/* 36:38 */       return;
/* 37:   */     }
/* 38:41 */     GenericFloorItem Item = (GenericFloorItem)fItem;
/* 39:   */     
/* 40:43 */     Avatar avatar = User.avatar;
/* 41:   */     
/* 42:45 */     byte[] xy = Item.SquareInFront();
/* 43:46 */     if (((avatar.x == xy[0]) && (avatar.y == xy[1])) || ((avatar.x == Item.getX()) && (avatar.y == Item.getY())))
/* 44:   */     {
/* 45:47 */       int TeleId = Teleports.getTele(Item.itemId);
/* 46:48 */       if (TeleId != -1)
/* 47:   */       {
/* 48:49 */         Item.setIntData(2);
/* 49:50 */         room.floorItemUpdateNeeded(Item);
/* 50:51 */         avatar.allowOverride = true;
/* 51:52 */         avatar.moveTo(Item.getX(), Item.getY());
/* 52:   */         
/* 53:54 */         int teleportRoom = Teleports.getRoom(TeleId);
/* 54:56 */         if (teleportRoom != -1)
/* 55:   */         {
/* 56:57 */           User.teleport = new TeleportAttach(TeleId, teleportRoom);
/* 57:58 */           room.addUserEvent(new Teleport_IN(avatar), 2);
/* 58:59 */           room.addUserEvent(new Teleport_CLOSE(avatar, Item), 2);
/* 59:   */         }
/* 60:   */       }
/* 61:   */     }
/* 62:   */     else
/* 63:   */     {
/* 64:63 */       avatar.moveTo(xy[0], xy[1]);
/* 65:   */     }
/* 66:   */   }
/* 67:   */   
/* 68:   */   public void OnTriggerWall(RoomTask room, Connection User, GenericWallItem Item, int Request, boolean UserHasRights) {}
/* 69:   */   
/* 70:   */   public void OnPickUp(RoomTask room, Connection User, GenericFloorItem Item) {}
/* 71:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.itemInteractor.InteractorTeleport
 * JD-Core Version:    0.7.0.1
 */