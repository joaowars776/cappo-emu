/*  1:   */ package cappo.game.moderation;
/*  2:   */ 
/*  3:   */ import cappo.game.collections.Utils;
/*  4:   */ 
/*  5:   */ public class UserMuted
/*  6:   */ {
/*  7:   */   public long unMuteTimeStamp;
/*  8:   */   public String reason;
/*  9:   */   
/* 10:   */   public boolean isMuted()
/* 11:   */   {
/* 12:10 */     return this.unMuteTimeStamp > Utils.getTimestamp();
/* 13:   */   }
/* 14:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.moderation.UserMuted
 * JD-Core Version:    0.7.0.1
 */