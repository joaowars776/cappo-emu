/*  1:   */ package cappo.game.roomgames.banzai;
/*  2:   */ 
/*  3:   */ import cappo.game.roomeffects.special.BanzaiEffect;
/*  4:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  5:   */ import cappo.game.roomgames.RoomGamePlayer;
/*  6:   */ 
/*  7:   */ public class BattleBanzai
/*  8:   */ {
/*  9:   */   public static void togglePlayer(Avatar avatar, short team)
/* 10:   */   {
/* 11: 8 */     if (avatar.roomGamePlayer != null)
/* 12:   */     {
/* 13: 9 */       if ((avatar.roomGamePlayer instanceof BanzaiPlayer)) {
/* 14:10 */         avatar.roomGamePlayer.removePlayer();
/* 15:   */       }
/* 16:   */     }
/* 17:   */     else
/* 18:   */     {
/* 19:13 */       avatar.roomGamePlayer = new BanzaiPlayer(team, avatar);
/* 20:14 */       avatar.userSpecialEffect = new BanzaiEffect(avatar, (short)(team + 32));
/* 21:   */     }
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomgames.banzai.BattleBanzai
 * JD-Core Version:    0.7.0.1
 */