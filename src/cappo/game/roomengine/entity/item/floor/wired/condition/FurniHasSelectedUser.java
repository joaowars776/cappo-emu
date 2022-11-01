/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired.condition;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  5:   */ import cappo.game.roomengine.entity.item.floor.RoomFloorItemData.AffectedTile;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import java.util.Collection;
/*  8:   */ import java.util.Iterator;
/*  9:   */ import java.util.List;
/* 10:   */ import java.util.Map;
/* 11:   */ 
/* 12:   */ public class FurniHasSelectedUser
/* 13:   */   extends WiredConditionBase
/* 14:   */ {
/* 15:   */   public int getCode()
/* 16:   */   {
/* 17:18 */     return 8;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public boolean needUser()
/* 21:   */   {
/* 22:23 */     return true;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public boolean checkCondition(Connection invoker)
/* 26:   */   {
/* 27:   */     Iterator localIterator2;
/* 28:28 */     for (Iterator localIterator1 = this.items.values().iterator(); localIterator1.hasNext(); localIterator2.hasNext())
/* 29:   */     {
/* 30:28 */       FloorItem floorItem = (FloorItem)localIterator1.next();
/* 31:29 */       List<RoomFloorItemData.AffectedTile> PointList = floorItem.getAffectedTiles();
/* 32:30 */       localIterator2 = PointList.iterator(); continue;RoomFloorItemData.AffectedTile Tile = (RoomFloorItemData.AffectedTile)localIterator2.next();
/* 33:31 */       if (invoker.avatar.xy == Tile.xy) {
/* 34:32 */         return true;
/* 35:   */       }
/* 36:   */     }
/* 37:37 */     return false;
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.condition.FurniHasSelectedUser
 * JD-Core Version:    0.7.0.1
 */