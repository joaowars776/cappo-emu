/*  1:   */ package cappo.game.moderation.tickets;
/*  2:   */ 
/*  3:   */ public class HelpTicket
/*  4:   */ {
/*  5:   */   public static final short STATUS_OPEN = 1;
/*  6:   */   public static final short STATUS_PICKED = 2;
/*  7:   */   public static final short STATUS_CLOSED = 3;
/*  8:   */   public int id;
/*  9:   */   public short status;
/* 10:   */   public short type;
/* 11:   */   public short category;
/* 12:   */   public short priority;
/* 13:   */   public int reporterId;
/* 14:   */   public String reporterName;
/* 15:   */   public int reportedId;
/* 16:   */   public String reportedName;
/* 17:   */   public int handlerId;
/* 18:   */   public String handlerName;
/* 19:   */   public long timeStamp;
/* 20:   */   public int chatLogId;
/* 21:   */   public String roomName;
/* 22:   */   public int roomId;
/* 23:26 */   public int roomType = 1;
/* 24:   */   public String text;
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.moderation.tickets.HelpTicket
 * JD-Core Version:    0.7.0.1
 */