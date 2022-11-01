/*  1:   */ package cappo.game.roomengine.roomlisting;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ import cappo.game.roomengine.RoomData;
/*  5:   */ 
/*  6:   */ public class RoomListingScore
/*  7:   */   extends RoomListingBase
/*  8:   */ {
/*  9: 6 */   public static final RoomListingScore mainInstance = new RoomListingScore(1);
/* 10:   */   
/* 11:   */   public RoomListingScore(int id)
/* 12:   */   {
/* 13: 9 */     super(id);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public int compare(RoomTask o1, RoomTask o2)
/* 17:   */   {
/* 18:14 */     if (o1 == o2) {
/* 19:16 */       return 0;
/* 20:   */     }
/* 21:19 */     return o1.roomData.rating == o2.roomData.rating ? 0 : o1.roomData.rating > o2.roomData.rating ? -1 : 1;
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomlisting.RoomListingScore
 * JD-Core Version:    0.7.0.1
 */