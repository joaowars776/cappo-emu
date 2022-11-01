/*  1:   */ package cappo.game.roomengine.settings;
/*  2:   */ 
/*  3:   */ import cappo.game.player.PlayerData;
/*  4:   */ 
/*  5:   */ public class PlayerRight
/*  6:   */ {
/*  7:   */   public PlayerData player;
/*  8:   */   public boolean needInsert;
/*  9:   */   
/* 10:   */   public PlayerRight(PlayerData plr)
/* 11:   */   {
/* 12:10 */     this.player = plr;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public PlayerRight needInsert()
/* 16:   */   {
/* 17:14 */     this.needInsert = true;
/* 18:15 */     return this;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.settings.PlayerRight
 * JD-Core Version:    0.7.0.1
 */