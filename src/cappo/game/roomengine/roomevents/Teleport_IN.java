/*  1:   */ package cappo.game.roomengine.roomevents;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.collections.Teleports;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  7:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  8:   */ import cappo.game.roomengine.itemInteractor.InteractorTeleport.TeleportAttach;
/*  9:   */ 
/* 10:   */ public class Teleport_IN
/* 11:   */   extends Event
/* 12:   */ {
/* 13:   */   Avatar User;
/* 14:   */   
/* 15:   */   public Teleport_IN(Avatar user)
/* 16:   */   {
/* 17:19 */     this.User = user;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void run(RoomTask room)
/* 21:   */   {
/* 22:24 */     this.User.allowOverride = false;
/* 23:25 */     InteractorTeleport.TeleportAttach teleport = this.User.cn.teleport;
/* 24:   */     
/* 25:27 */     int TeleRoomId = Teleports.getRoom(teleport.itemId);
/* 26:28 */     if (TeleRoomId == -1) {
/* 27:29 */       return;
/* 28:   */     }
/* 29:33 */     if (room.roomId == TeleRoomId)
/* 30:   */     {
/* 31:34 */       GenericFloorItem Item = (GenericFloorItem)room.getFloorItem(teleport.itemId);
/* 32:35 */       if (Item != null)
/* 33:   */       {
/* 34:36 */         Item.setIntData(2);
/* 35:37 */         room.floorItemUpdateNeeded(Item);
/* 36:38 */         this.User.SetPos(Item.getX(), Item.getY(), Item.getZ());
/* 37:39 */         this.User.SetRot(Item.getDir());
/* 38:40 */         room.addUserEvent(new Teleport_OUT(this.User, Item), 2);
/* 39:   */       }
/* 40:   */     }
/* 41:   */     else
/* 42:   */     {
/* 43:43 */       this.User.cn.loadRoom(TeleRoomId, "");
/* 44:   */     }
/* 45:   */   }
/* 46:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomevents.Teleport_IN
 * JD-Core Version:    0.7.0.1
 */