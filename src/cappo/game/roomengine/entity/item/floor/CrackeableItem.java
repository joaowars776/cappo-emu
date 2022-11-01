/*  1:   */ package cappo.game.roomengine.entity.item.floor;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ import cappo.game.roomengine.entity.item.extradata.CrackableExtraData;
/*  5:   */ 
/*  6:   */ public class CrackeableItem
/*  7:   */   extends GenericFloorItem
/*  8:   */ {
/*  9:   */   private void updateHits(int hits)
/* 10:   */   {
/* 11:14 */     CrackableExtraData data = (CrackableExtraData)this.extraData;
/* 12:15 */     data.hits = hits;
/* 13:   */     
/* 14:17 */     RoomTask room = getRoom();
/* 15:18 */     if (room == null) {
/* 16:19 */       return;
/* 17:   */     }
/* 18:26 */     room.floorItemUpdateNeeded(this);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void setIntData(int data)
/* 22:   */   {
/* 23:31 */     super.setIntData(data);
/* 24:32 */     updateHits(data);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public int incIntData(int ammount)
/* 28:   */   {
/* 29:37 */     int data = super.incIntData(ammount);
/* 30:38 */     updateHits(data);
/* 31:39 */     return data;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public int incIntDataMod(int ammount, int modulus)
/* 35:   */   {
/* 36:44 */     int data = super.incIntDataMod(ammount, modulus);
/* 37:45 */     updateHits(data);
/* 38:46 */     return data;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public int decIntData(int ammount)
/* 42:   */   {
/* 43:51 */     int data = super.decIntData(ammount);
/* 44:52 */     updateHits(data);
/* 45:53 */     return data;
/* 46:   */   }
/* 47:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.CrackeableItem
 * JD-Core Version:    0.7.0.1
 */