/*  1:   */ package cappo.game.polls;
/*  2:   */ 
/*  3:   */ import java.util.Map;
/*  4:   */ import java.util.concurrent.ConcurrentHashMap;
/*  5:   */ 
/*  6:   */ public class Poll
/*  7:   */ {
/*  8:   */   public int id;
/*  9:   */   public String title;
/* 10:   */   public String thanks;
/* 11:11 */   public Map<Integer, PollQuestion> questions = new ConcurrentHashMap();
/* 12:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.polls.Poll
 * JD-Core Version:    0.7.0.1
 */