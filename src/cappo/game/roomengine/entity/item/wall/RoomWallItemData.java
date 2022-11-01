/*  1:   */ package cappo.game.roomengine.entity.item.wall;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.Database;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.collections.BaseItem;
/*  6:   */ import cappo.game.collections.BflyData;
/*  7:   */ import cappo.game.roomengine.entity.item.RoomItemData;
/*  8:   */ 
/*  9:   */ public class RoomWallItemData
/* 10:   */   extends RoomItemData
/* 11:   */ {
/* 12:   */   public WallItem wallItem;
/* 13:   */   public int widthX;
/* 14:   */   public int widthY;
/* 15:   */   public int lengthX;
/* 16:   */   public int lengthY;
/* 17:   */   public char side;
/* 18:   */   
/* 19:   */   public RoomWallItemData(RoomTask room, WallItem item, char side, int widthX, int widthY, int lengthX, int lengthY)
/* 20:   */   {
/* 21:20 */     super(item.baseItem, room);
/* 22:   */     
/* 23:22 */     this.wallItem = item;
/* 24:   */     
/* 25:24 */     this.side = side;
/* 26:25 */     this.widthX = widthX;
/* 27:26 */     this.widthY = widthY;
/* 28:27 */     this.lengthX = lengthX;
/* 29:28 */     this.lengthY = lengthY;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public RoomWallItemData(RoomTask room, BaseItem base, float x, float y, int n)
/* 33:   */   {
/* 34:33 */     super(base, room);
/* 35:   */     
/* 36:35 */     this.widthY = BflyData.getB(x);
/* 37:36 */     this.widthX = BflyData.getA(x, this.widthY);
/* 38:   */     
/* 39:38 */     this.lengthY = BflyData.getB(y);
/* 40:39 */     this.lengthX = BflyData.getA(y, this.lengthY);
/* 41:41 */     if (n == 7) {
/* 42:42 */       this.side = 'r';
/* 43:   */     } else {
/* 44:44 */       this.side = 'l';
/* 45:   */     }
/* 46:   */   }
/* 47:   */   
/* 48:   */   public String toString()
/* 49:   */   {
/* 50:51 */     return ":w=" + this.widthX + "," + this.widthY + " " + "l=" + this.lengthX + "," + this.lengthY + " " + this.side;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public float GetXValue()
/* 54:   */   {
/* 55:56 */     return BflyData.Combine(this.widthX, this.widthY);
/* 56:   */   }
/* 57:   */   
/* 58:   */   public float GetYValue()
/* 59:   */   {
/* 60:61 */     return BflyData.Combine(this.lengthX, this.lengthY);
/* 61:   */   }
/* 62:   */   
/* 63:   */   public int n()
/* 64:   */   {
/* 65:66 */     if (this.side == 'l') {
/* 66:67 */       return 8;
/* 67:   */     }
/* 68:69 */     return 7;
/* 69:   */   }
/* 70:   */   
/* 71:   */   public void save()
/* 72:   */     throws Exception
/* 73:   */   {
/* 74:74 */     Database.exec("UPDATE furnis_roomdata SET a='" + GetXValue() + "',b='" + GetYValue() + "',r=" + n() + " WHERE id=" + this.wallItem.itemId + ";", new Object[0]);
/* 75:   */   }
/* 76:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.wall.RoomWallItemData
 * JD-Core Version:    0.7.0.1
 */