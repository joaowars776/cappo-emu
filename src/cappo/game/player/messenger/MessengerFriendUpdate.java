/*  1:   */ package cappo.game.player.messenger;
/*  2:   */ 
/*  3:   */ public class MessengerFriendUpdate
/*  4:   */ {
/*  5:   */   public static final int UPDATE = 0;
/*  6:   */   public static final int ADD = 1;
/*  7:   */   public static final int REMOVE = -1;
/*  8:   */   public int userId;
/*  9:   */   public int type;
/* 10:   */   
/* 11:   */   public MessengerFriendUpdate(int id, int updateType)
/* 12:   */   {
/* 13:12 */     this.userId = id;
/* 14:13 */     this.type = updateType;
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.messenger.MessengerFriendUpdate
 * JD-Core Version:    0.7.0.1
 */