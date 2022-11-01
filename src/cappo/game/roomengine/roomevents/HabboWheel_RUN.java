/*  1:   */ package cappo.game.roomengine.roomevents;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ import cappo.game.collections.Utils;
/*  5:   */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*  6:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  7:   */ 
/*  8:   */ public class HabboWheel_RUN
/*  9:   */   extends Event
/* 10:   */ {
/* 11:   */   GenericWallItem Item;
/* 12:   */   
/* 13:   */   public HabboWheel_RUN(GenericWallItem item)
/* 14:   */   {
/* 15:17 */     this.Item = item;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void run(RoomTask room)
/* 19:   */   {
/* 20:22 */     this.Item.extraData.setExtraData(Integer.toString(Utils.GetRandomNumber(1, 10)));
/* 21:23 */     room.wallItemUpdateNeeded(this.Item);
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomevents.HabboWheel_RUN
 * JD-Core Version:    0.7.0.1
 */