/*  1:   */ package cappo.game.talents.citizenship.levels;
/*  2:   */ 
/*  3:   */ import cappo.game.talents.TalentTrackLevel;
/*  4:   */ import cappo.game.talents.rewards.badges.TalentTrackRewardBadge;
/*  5:   */ import cappo.game.talents.rewards.products.TalentTrackRewardGiveVip;
/*  6:   */ import cappo.game.talents.rewards.products.TalentTrackRewardProduct;
/*  7:   */ import java.util.List;
/*  8:   */ 
/*  9:   */ public class CitizenshipLevel5
/* 10:   */   extends TalentTrackLevel
/* 11:   */ {
/* 12:   */   public CitizenshipLevel5()
/* 13:   */   {
/* 14:10 */     this.badgeRewards.add(new TalentTrackRewardBadge("CITIZEN"));
/* 15:11 */     this.productRewards.add(new TalentTrackRewardProduct("A1 KUMIANKKA"));
/* 16:12 */     this.productRewards.add(new TalentTrackRewardGiveVip(7));
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.talents.citizenship.levels.CitizenshipLevel5
 * JD-Core Version:    0.7.0.1
 */