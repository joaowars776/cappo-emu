/*  1:   */ package cappo.game.roomengine.settings;
/*  2:   */ 
/*  3:   */ import cappo.game.player.PlayerData;
/*  4:   */ 
/*  5:   */ public class PlayerBan
/*  6:   */ {
/*  7:   */   public PlayerData player;
/*  8:   */   public long timeout;
/*  9:   */   
/* 10:   */   public PlayerBan(PlayerData plr, long time)
/* 11:   */   {
/* 12:10 */     this.player = plr;
/* 13:11 */     this.timeout = time;
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.settings.PlayerBan
 * JD-Core Version:    0.7.0.1
 */