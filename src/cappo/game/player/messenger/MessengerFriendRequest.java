/*  1:   */ package cappo.game.player.messenger;
/*  2:   */ 
/*  3:   */ public class MessengerFriendRequest
/*  4:   */ {
/*  5:   */   public int userid;
/*  6:   */   public String username;
/*  7:   */   public boolean needInsert;
/*  8:   */   
/*  9:   */   public MessengerFriendRequest(int id, String name, boolean insert)
/* 10:   */   {
/* 11: 9 */     this.userid = id;
/* 12:10 */     this.username = name;
/* 13:11 */     this.needInsert = insert;
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.messenger.MessengerFriendRequest
 * JD-Core Version:    0.7.0.1
 */