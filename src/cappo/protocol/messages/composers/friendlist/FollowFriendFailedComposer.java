/*  1:   */ package cappo.protocol.messages.composers.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class FollowFriendFailedComposer
/*  7:   */ {
/*  8:   */   public static final int NOT_FRIEND = 0;
/*  9:   */   public static final int OFFLINE = 1;
/* 10:   */   public static final int HOTEL_VIEW = 2;
/* 11:   */   public static final int PREVENTED = 3;
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static final MessageWriter compose(int ErrorCode)
/* 15:   */   {
/* 16:21 */     MessageWriter ClientMessage = new MessageWriter();
/* 17:22 */     Composer.initPacket(HEADER, ClientMessage);
/* 18:23 */     Composer.add(Integer.valueOf(ErrorCode), ClientMessage);
/* 19:24 */     Composer.endPacket(ClientMessage);
/* 20:25 */     return ClientMessage;
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.friendlist.FollowFriendFailedComposer
 * JD-Core Version:    0.7.0.1
 */