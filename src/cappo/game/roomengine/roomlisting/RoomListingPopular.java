/*  1:   */ package cappo.game.roomengine.roomlisting;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ 
/*  5:   */ public class RoomListingPopular
/*  6:   */   extends RoomListingBase
/*  7:   */ {
/*  8: 6 */   public static final RoomListingPopular mainInstance = new RoomListingPopular(0);
/*  9:   */   
/* 10:   */   public RoomListingPopular(int id)
/* 11:   */   {
/* 12: 9 */     super(id);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public int compare(RoomTask o1, RoomTask o2)
/* 16:   */   {
/* 17:14 */     if (o1 == o2) {
/* 18:16 */       return 0;
/* 19:   */     }
/* 20:19 */     return o1.userCount == o2.userCount ? 0 : o1.userCount > o2.userCount ? -1 : 1;
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomlisting.RoomListingPopular
 * JD-Core Version:    0.7.0.1
 */