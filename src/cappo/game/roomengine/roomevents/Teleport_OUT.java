/*  1:   */ package cappo.game.roomengine.roomevents;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  5:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  6:   */ 
/*  7:   */ public class Teleport_OUT
/*  8:   */   extends Event
/*  9:   */ {
/* 10:   */   Avatar User;
/* 11:   */   GenericFloorItem item;
/* 12:   */   
/* 13:   */   public Teleport_OUT(Avatar user, GenericFloorItem Item)
/* 14:   */   {
/* 15:18 */     this.User = user;
/* 16:19 */     this.item = Item;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void run(RoomTask room)
/* 20:   */   {
/* 21:24 */     byte[] xy = this.item.SquareInFront();
/* 22:25 */     this.User.moveTo(xy[0], xy[1]);
/* 23:26 */     room.addUserEvent(new Teleport_CLOSE(this.User, this.item), 1);
/* 24:27 */     this.User.cn.teleport = null;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomevents.Teleport_OUT
 * JD-Core Version:    0.7.0.1
 */