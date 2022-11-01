/*  1:   */ package cappo.game.roomengine;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ 
/*  5:   */ public class RoomEvent
/*  6:   */ {
/*  7:   */   public int category;
/*  8:   */   public String description;
/*  9:   */   public String name;
/* 10:   */   public int roomId;
/* 11:   */   public int startTime;
/* 12:   */   public List<String> tags;
/* 13:   */   
/* 14:   */   public RoomEvent(int RoomId, String eventName, String eventDescription, int eventCategory, List<String> eventTags, int time)
/* 15:   */   {
/* 16:20 */     this.roomId = RoomId;
/* 17:21 */     this.name = eventName;
/* 18:22 */     this.description = eventDescription;
/* 19:23 */     this.category = eventCategory;
/* 20:24 */     this.tags = eventTags;
/* 21:25 */     this.startTime = time;
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.RoomEvent
 * JD-Core Version:    0.7.0.1
 */