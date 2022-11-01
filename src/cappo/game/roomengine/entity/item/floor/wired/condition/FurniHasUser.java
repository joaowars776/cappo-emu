/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired.condition;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.RoomFloorItemData.AffectedTile;
/*  7:   */ import java.util.List;
/*  8:   */ import java.util.Map;
/*  9:   */ 
/* 10:   */ public class FurniHasUser
/* 11:   */   extends WiredConditionBase
/* 12:   */ {
/* 13:   */   public int getCode()
/* 14:   */   {
/* 15:18 */     return 2;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public boolean needUser()
/* 19:   */   {
/* 20:23 */     return false;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean checkCondition(Connection invoker)
/* 24:   */   {
/* 25:28 */     for (FloorItem floorItem : this.items.values())
/* 26:   */     {
/* 27:29 */       boolean haveUser = false;
/* 28:   */       
/* 29:31 */       List<RoomFloorItemData.AffectedTile> PointList = floorItem.getAffectedTiles();
/* 30:32 */       for (RoomFloorItemData.AffectedTile Tile : PointList) {
/* 31:33 */         if (getRoom().squareHasUsers(Tile.xy))
/* 32:   */         {
/* 33:34 */           haveUser = true;
/* 34:35 */           break;
/* 35:   */         }
/* 36:   */       }
/* 37:39 */       if (!haveUser) {
/* 38:40 */         return false;
/* 39:   */       }
/* 40:   */     }
/* 41:44 */     return true;
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.condition.FurniHasUser
 * JD-Core Version:    0.7.0.1
 */