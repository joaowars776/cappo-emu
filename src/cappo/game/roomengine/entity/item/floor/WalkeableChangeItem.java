/*  1:   */ package cappo.game.roomengine.entity.item.floor;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ import cappo.game.roomengine.SquareFlagManager;
/*  5:   */ import java.util.List;
/*  6:   */ 
/*  7:   */ public class WalkeableChangeItem
/*  8:   */   extends GenericFloorItem
/*  9:   */ {
/* 10:   */   private void updateHole(boolean walkeable)
/* 11:   */   {
/* 12:17 */     RoomTask room = getRoom();
/* 13:18 */     List<RoomFloorItemData.AffectedTile> PointList = getAffectedTiles();
/* 14:19 */     for (RoomFloorItemData.AffectedTile Tile : PointList) {
/* 15:20 */       room.squareFlag.SetFlag(Tile.xy, 4, walkeable);
/* 16:   */     }
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void setIntData(int data)
/* 20:   */   {
/* 21:26 */     super.setIntData(data);
/* 22:27 */     updateHole(data != 0);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public int incIntData(int ammount)
/* 26:   */   {
/* 27:32 */     int data = super.incIntData(ammount);
/* 28:33 */     updateHole(data != 0);
/* 29:34 */     return data;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public int incIntDataMod(int ammount, int modulus)
/* 33:   */   {
/* 34:39 */     int data = super.incIntDataMod(ammount, modulus);
/* 35:40 */     updateHole(data != 0);
/* 36:41 */     return data;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public int decIntData(int ammount)
/* 40:   */   {
/* 41:46 */     int data = super.decIntData(ammount);
/* 42:47 */     updateHole(data != 0);
/* 43:48 */     return data;
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.WalkeableChangeItem
 * JD-Core Version:    0.7.0.1
 */