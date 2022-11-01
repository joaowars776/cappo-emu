/* 1:  */ package cappo.game.roomengine.chat;
/* 2:  */ 
/* 3:  */ import cappo.game.collections.Utils;
/* 4:  */ 
/* 5:  */ public class UserRoomMuted
/* 6:  */ {
/* 7:  */   public long unMuteTimeStamp;
/* 8:  */   
/* 9:  */   public boolean isMuted()
/* ::  */   {
/* ;:9 */     return this.unMuteTimeStamp > Utils.getTimestamp();
/* <:  */   }
/* =:  */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.chat.UserRoomMuted
 * JD-Core Version:    0.7.0.1
 */