/*  1:   */ package cappo.game.collections;
/*  2:   */ 
/*  3:   */ public class Wardrobe
/*  4:   */ {
/*  5:   */   public short slotId;
/*  6:   */   public String look;
/*  7:   */   public short gender;
/*  8:   */   public boolean needInsert;
/*  9:   */   
/* 10:   */   public Wardrobe(int slot, String sLook, int sex)
/* 11:   */   {
/* 12:16 */     this.slotId = ((short)slot);
/* 13:17 */     this.look = sLook;
/* 14:18 */     this.gender = ((short)sex);
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.collections.Wardrobe
 * JD-Core Version:    0.7.0.1
 */