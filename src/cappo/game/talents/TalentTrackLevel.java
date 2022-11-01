/*  1:   */ package cappo.game.talents;
/*  2:   */ 
/*  3:   */ import cappo.game.talents.rewards.badges.TalentTrackRewardBadge;
/*  4:   */ import cappo.game.talents.rewards.products.TalentTrackRewardProduct;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import java.util.List;
/*  7:   */ 
/*  8:   */ public class TalentTrackLevel
/*  9:   */ {
/* 10:   */   public static final int LOCKED = 0;
/* 11:   */   public static final int UNLOKED = 1;
/* 12:   */   public static final int ACHIEVED = 2;
/* 13:14 */   public List<TalentTrackRewardBadge> badgeRewards = new ArrayList();
/* 14:15 */   public List<TalentTrackRewardProduct> productRewards = new ArrayList();
/* 15:   */   
/* 16:   */   public void addBadge(TalentTrackRewardBadge badge)
/* 17:   */   {
/* 18:18 */     this.badgeRewards.add(badge);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void addProduct(TalentTrackRewardProduct product)
/* 22:   */   {
/* 23:22 */     this.productRewards.add(product);
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.talents.TalentTrackLevel
 * JD-Core Version:    0.7.0.1
 */