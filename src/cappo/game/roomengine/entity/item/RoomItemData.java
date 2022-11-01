/*  1:   */ package cappo.game.roomengine.entity.item;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ import cappo.game.collections.BaseItem;
/*  5:   */ 
/*  6:   */ public abstract class RoomItemData
/*  7:   */ {
/*  8:   */   public BaseItem baseItem;
/*  9:   */   public RoomTask currentRoom;
/* 10:   */   
/* 11:   */   public int getRoomId()
/* 12:   */   {
/* 13:11 */     return this.currentRoom.roomId;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public RoomItemData(BaseItem base, RoomTask room)
/* 17:   */   {
/* 18:15 */     this.baseItem = base;
/* 19:16 */     this.currentRoom = room;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.RoomItemData
 * JD-Core Version:    0.7.0.1
 */