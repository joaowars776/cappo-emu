/*  1:   */ package cappo.game.roomengine.chat.wf;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ 
/*  5:   */ public abstract class WordFilterAction
/*  6:   */ {
/*  7:   */   public static final int ALERT = 0;
/*  8:   */   public static final int SPAM = 1;
/*  9:   */   public static final int COUNT = 2;
/* 10:10 */   public static WordFilterAction[] actions = new WordFilterAction[2];
/* 11:   */   
/* 12:   */   public static void init()
/* 13:   */   {
/* 14:13 */     actions[0] = new WordFilterActionAlert();
/* 15:14 */     actions[1] = new WordFilterActionSpammer();
/* 16:   */   }
/* 17:   */   
/* 18:   */   public abstract boolean run(Connection paramConnection);
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.chat.wf.WordFilterAction
 * JD-Core Version:    0.7.0.1
 */