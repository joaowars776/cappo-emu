/*  1:   */ package cappo.game.roomengine.chat.wf;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import java.sql.ResultSet;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class WordFilter
/* 10:   */ {
/* 11:10 */   private static Map<String, WordFilterAction> wordMap = new HashMap();
/* 12:   */   
/* 13:   */   public static void init(DBResult result)
/* 14:   */     throws Exception
/* 15:   */   {
/* 16:13 */     wordMap.clear();
/* 17:   */     
/* 18:15 */     WordFilterAction.init();
/* 19:   */     
/* 20:17 */     Database.query(result, "SELECT * FROM wordfilter;", new Object[0]);
/* 21:18 */     while (result.data.next()) {
/* 22:19 */       wordMap.put(result.data.getString("word"), WordFilterAction.actions[result.data.getInt("action")]);
/* 23:   */     }
/* 24:   */   }
/* 25:   */   
/* 26:   */   public static WordFilterAction getAction(String text)
/* 27:   */   {
/* 28:24 */     String tmp = cleanText(text);
/* 29:25 */     for (String key : wordMap.keySet()) {
/* 30:26 */       if (tmp.contains(key)) {
/* 31:27 */         return (WordFilterAction)wordMap.get(key);
/* 32:   */       }
/* 33:   */     }
/* 34:30 */     return null;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public static String cleanText(String text)
/* 38:   */   {
/* 39:34 */     StringBuilder str = new StringBuilder(text.length());
/* 40:35 */     Character lastChar = null;
/* 41:36 */     for (char c : text.toLowerCase().toCharArray()) {
/* 42:37 */       if ((lastChar == null) || (c != lastChar.charValue())) {
/* 43:41 */         if ((c > '`') && (c < '{'))
/* 44:   */         {
/* 45:42 */           lastChar = Character.valueOf(c);
/* 46:43 */           str.append(c);
/* 47:   */         }
/* 48:   */       }
/* 49:   */     }
/* 50:46 */     return str.toString();
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.chat.wf.WordFilter
 * JD-Core Version:    0.7.0.1
 */