/*  1:   */ package cappo.protocol.messages.composers.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.player.messenger.MessengerFriendRequest;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import java.util.Collection;
/*  7:   */ 
/*  8:   */ public class BuddyRequestsComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(Collection<MessengerFriendRequest> collection)
/* 13:   */   {
/* 14:19 */     int len = collection.size();
/* 15:20 */     MessageWriter ClientMessage = new MessageWriter(40 + collection.size() * 40);
/* 16:21 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:22 */     Composer.add(Integer.valueOf(len), ClientMessage);
/* 18:23 */     Composer.add(Integer.valueOf(len), ClientMessage);
/* 19:24 */     for (MessengerFriendRequest request : collection)
/* 20:   */     {
/* 21:25 */       Composer.add(Integer.valueOf(request.userid), ClientMessage);
/* 22:26 */       Composer.add(request.username, ClientMessage);
/* 23:27 */       Composer.add(Integer.toString(request.userid), ClientMessage);
/* 24:   */     }
/* 25:29 */     Composer.endPacket(ClientMessage);
/* 26:30 */     return ClientMessage;
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.friendlist.BuddyRequestsComposer
 * JD-Core Version:    0.7.0.1
 */