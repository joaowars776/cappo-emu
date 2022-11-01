/*  1:   */ package cappo.game.roomengine.entity.item.wall;
/*  2:   */ 
/*  3:   */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*  4:   */ 
/*  5:   */ public class GenericWallItem
/*  6:   */   extends WallItem
/*  7:   */ {
/*  8:   */   private int intData;
/*  9:   */   
/* 10:   */   public int getIntData()
/* 11:   */   {
/* 12: 7 */     return this.intData;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void setIntData(int data)
/* 16:   */   {
/* 17:11 */     this.intData = data;
/* 18:12 */     this.extraData.setExtraData(Integer.valueOf(this.intData));
/* 19:   */   }
/* 20:   */   
/* 21:   */   public int incIntData(int ammount)
/* 22:   */   {
/* 23:16 */     this.intData += ammount;
/* 24:17 */     this.extraData.setExtraData(Integer.valueOf(this.intData));
/* 25:18 */     return this.intData;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public int decIntData(int ammount)
/* 29:   */   {
/* 30:22 */     this.intData -= ammount;
/* 31:23 */     this.extraData.setExtraData(Integer.valueOf(this.intData));
/* 32:24 */     return this.intData;
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.wall.GenericWallItem
 * JD-Core Version:    0.7.0.1
 */