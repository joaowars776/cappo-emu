/*  1:   */ package cappo.game.talents.rewards.products;
/*  2:   */ 
/*  3:   */ public class TalentTrackRewardProduct
/*  4:   */ {
/*  5:   */   public final String product;
/*  6:   */   public final int extraValue;
/*  7:   */   
/*  8:   */   public TalentTrackRewardProduct(String reward)
/*  9:   */   {
/* 10: 8 */     this.product = reward;
/* 11: 9 */     this.extraValue = 0;
/* 12:   */   }
/* 13:   */   
/* 14:   */   public TalentTrackRewardProduct(String reward, int extra)
/* 15:   */   {
/* 16:13 */     this.product = reward;
/* 17:14 */     this.extraValue = extra;
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.talents.rewards.products.TalentTrackRewardProduct
 * JD-Core Version:    0.7.0.1
 */