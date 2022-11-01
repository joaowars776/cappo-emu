/*  1:   */ package cappo.game.roomengine.chat.wf;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.game.collections.Utils;
/*  5:   */ 
/*  6:   */ public class WordFilterActionAlert
/*  7:   */   extends WordFilterAction
/*  8:   */ {
/*  9:   */   public boolean run(Connection cn)
/* 10:   */   {
/* 11:10 */     Utils.AlertFromHotel(cn.socket, cappo.game.utils.lang.LangTexts.texts[7]);
/* 12:11 */     return true;
/* 13:   */   }
/* 14:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.chat.wf.WordFilterActionAlert
 * JD-Core Version:    0.7.0.1
 */