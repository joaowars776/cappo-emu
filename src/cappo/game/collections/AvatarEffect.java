/*  1:   */ package cappo.game.collections;
/*  2:   */ 
/*  3:   */ public class AvatarEffect
/*  4:   */ {
/*  5:   */   public static final int EFFECTS = 0;
/*  6:   */   public static final int COSTUMES = 1;
/*  7:   */   public boolean Activated;
/*  8:   */   public int effectType;
/*  9:   */   public int noNamed;
/* 10:   */   public long ActivateTimestamp;
/* 11:   */   public int TotalDuration;
/* 12:   */   
/* 13:   */   public AvatarEffect(int effectId, int totalDuration, boolean activated, long activateTimestamp)
/* 14:   */   {
/* 15:20 */     this.effectType = effectId;
/* 16:21 */     this.noNamed = 0;
/* 17:22 */     this.TotalDuration = totalDuration;
/* 18:23 */     this.Activated = activated;
/* 19:24 */     this.ActivateTimestamp = activateTimestamp;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.collections.AvatarEffect
 * JD-Core Version:    0.7.0.1
 */