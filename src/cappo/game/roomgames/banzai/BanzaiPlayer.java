/*  1:   */ package cappo.game.roomgames.banzai;
/*  2:   */ 
/*  3:   */ import cappo.game.roomeffects.special.UserSpecialEffect;
/*  4:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  5:   */ import cappo.game.roomgames.RoomGamePlayer;
/*  6:   */ 
/*  7:   */ public class BanzaiPlayer
/*  8:   */   extends RoomGamePlayer
/*  9:   */ {
/* 10:   */   public BanzaiPlayer(short playerTeam, Avatar player)
/* 11:   */   {
/* 12: 8 */     super(playerTeam, player);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void removePlayer()
/* 16:   */   {
/* 17:13 */     super.removePlayer();
/* 18:14 */     this.avatarEntity.userSpecialEffect.stopEffect();
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomgames.banzai.BanzaiPlayer
 * JD-Core Version:    0.7.0.1
 */