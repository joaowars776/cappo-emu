/*  1:   */ package cappo.game.polls;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import java.sql.ResultSet;
/*  6:   */ import java.util.Map;
/*  7:   */ import java.util.concurrent.ConcurrentHashMap;
/*  8:   */ 
/*  9:   */ public class PollManager
/* 10:   */ {
/* 11:11 */   public static Map<Integer, Poll> roomPolls = new ConcurrentHashMap();
/* 12:14 */   public static Map<Integer, Poll> polls = new ConcurrentHashMap();
/* 13:   */   
/* 14:   */   public static void load(DBResult result)
/* 15:   */     throws Exception
/* 16:   */   {
/* 17:17 */     roomPolls.clear();
/* 18:18 */     polls.clear();
/* 19:   */     
/* 20:20 */     Database.query(result, "SELECT * FROM poll_data WHERE active = 1;", new Object[0]);
/* 21:21 */     while (result.data.next())
/* 22:   */     {
/* 23:22 */       Poll poll = new Poll();
/* 24:23 */       poll.id = result.data.getInt("id");
/* 25:24 */       poll.title = result.data.getString("title");
/* 26:25 */       poll.thanks = result.data.getString("thanks");
/* 27:26 */       polls.put(Integer.valueOf(poll.id), poll);
/* 28:   */     }
/* 29:29 */     Database.query(result, "SELECT * FROM poll_questions;", new Object[0]);
/* 30:30 */     while (result.data.next())
/* 31:   */     {
/* 32:31 */       Poll poll = (Poll)polls.get(Integer.valueOf(result.data.getInt("poll")));
/* 33:32 */       if (poll != null)
/* 34:   */       {
/* 35:37 */         PollQuestion question = new PollQuestion();
/* 36:38 */         question.id = result.data.getInt("id");
/* 37:39 */         question.orderid = (poll.questions.size() + 1);
/* 38:40 */         question.type = ((byte)result.data.getInt("type"));
/* 39:41 */         question.text = result.data.getString("question");
/* 40:42 */         if ((question.type == 1) || (question.type == 2)) {
/* 41:43 */           question.answers = result.data.getString("answers").split(";");
/* 42:   */         }
/* 43:45 */         poll.questions.put(Integer.valueOf(question.id), question);
/* 44:   */       }
/* 45:   */     }
/* 46:48 */     Database.query(result, "SELECT * FROM room_poll;", new Object[0]);
/* 47:49 */     while (result.data.next())
/* 48:   */     {
/* 49:50 */       Poll poll = (Poll)polls.get(Integer.valueOf(result.data.getInt("poll")));
/* 50:51 */       if (poll != null) {
/* 51:56 */         roomPolls.put(Integer.valueOf(result.data.getInt("roomid")), poll);
/* 52:   */       }
/* 53:   */     }
/* 54:   */   }
/* 55:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.polls.PollManager
 * JD-Core Version:    0.7.0.1
 */