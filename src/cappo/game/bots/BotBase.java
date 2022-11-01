/*  1:   */ package cappo.game.bots;
/*  2:   */ 
/*  3:   */ import cappo.game.player.AvatarLook;
/*  4:   */ 
/*  5:   */ public class BotBase
/*  6:   */ {
/*  7:   */   public AvatarLook defaultLook;
/*  8:   */   public String defaultGender;
/*  9:   */   
/* 10:   */   public BotBase(String look, String gender)
/* 11:   */   {
/* 12:16 */     this.defaultLook = new AvatarLook(look);
/* 13:17 */     this.defaultGender = gender;
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.bots.BotBase
 * JD-Core Version:    0.7.0.1
 */