/*  1:   */ package cappo.game.roomengine.entity.item.floor;
/*  2:   */ 
/*  3:   */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*  4:   */ 
/*  5:   */ public class GenericFloorItem
/*  6:   */   extends FloorItem
/*  7:   */ {
/*  8:   */   private int intData;
/*  9:   */   
/* 10:   */   public int getIntData()
/* 11:   */   {
/* 12:13 */     return this.intData;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void setIntData(int data)
/* 16:   */   {
/* 17:17 */     this.intData = data;
/* 18:18 */     this.extraData.setExtraData(Integer.valueOf(this.intData));
/* 19:   */   }
/* 20:   */   
/* 21:   */   public int incIntData(int ammount)
/* 22:   */   {
/* 23:22 */     this.intData += ammount;
/* 24:23 */     this.extraData.setExtraData(Integer.valueOf(this.intData));
/* 25:24 */     return this.intData;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public int incIntDataMod(int ammount, int modulus)
/* 29:   */   {
/* 30:28 */     this.intData = ((this.intData + ammount) % modulus);
/* 31:29 */     this.extraData.setExtraData(Integer.valueOf(this.intData));
/* 32:30 */     return this.intData;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public int decIntData(int ammount)
/* 36:   */   {
/* 37:34 */     this.intData -= ammount;
/* 38:35 */     this.extraData.setExtraData(Integer.valueOf(this.intData));
/* 39:36 */     return this.intData;
/* 40:   */   }
/* 41:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.GenericFloorItem
 * JD-Core Version:    0.7.0.1
 */