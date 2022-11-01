/*  1:   */ package cappo.game.roomeffects.special;
/*  2:   */ 
/*  3:   */ import cappo.game.roomeffects.UserEffect;
/*  4:   */ import cappo.game.roomeffects.UserEffectBase;
/*  5:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  6:   */ 
/*  7:   */ public class UserSpecialEffect
/*  8:   */   extends UserEffectBase
/*  9:   */ {
/* 10:   */   public UserSpecialEffect(Avatar avatar, short effect)
/* 11:   */   {
/* 12: 8 */     super(avatar, effect);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void stopEffect()
/* 16:   */   {
/* 17:13 */     this.user.userSpecialEffect = null;
/* 18:14 */     if (this.user.userEffect != null) {
/* 19:15 */       this.user.userEffect.startEffect();
/* 20:   */     } else {
/* 21:17 */       super.stopEffect();
/* 22:   */     }
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomeffects.special.UserSpecialEffect
 * JD-Core Version:    0.7.0.1
 */