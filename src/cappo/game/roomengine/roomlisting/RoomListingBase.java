/*  1:   */ package cappo.game.roomengine.roomlisting;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ import java.util.ArrayList;
/*  5:   */ import java.util.Comparator;
/*  6:   */ import java.util.List;
/*  7:   */ 
/*  8:   */ public abstract class RoomListingBase
/*  9:   */   implements Comparator<RoomTask>
/* 10:   */ {
/* 11:   */   public static final int POPULAR_MAIN = 0;
/* 12:   */   public static final int SCORE_MAIN = 1;
/* 13:   */   public static final int CUSTOM = 2;
/* 14:14 */   public List<RoomTask> rankedList = new ArrayList(50);
/* 15:   */   public final int listingID;
/* 16:   */   
/* 17:   */   public RoomListingBase(int id)
/* 18:   */   {
/* 19:18 */     this.listingID = id;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomlisting.RoomListingBase
 * JD-Core Version:    0.7.0.1
 */