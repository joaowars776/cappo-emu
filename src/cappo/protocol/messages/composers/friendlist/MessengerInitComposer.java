/*  1:   */ package cappo.protocol.messages.composers.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.player.messenger.MessengerFriend;
/*  5:   */ import cappo.game.player.messenger.MessengerFriendCategory;
/*  6:   */ import cappo.game.player.messenger.PlayerMessenger;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ import cappo.protocol.messages.composers.serializers.SerializeFriend;
/*  9:   */ import java.util.Collection;
/* 10:   */ 
/* 11:   */ public class MessengerInitComposer
/* 12:   */ {
/* 13:   */   public static int HEADER;
/* 14:   */   
/* 15:   */   public static final MessageWriter compose(PlayerMessenger messenger)
/* 16:   */   {
/* 17:23 */     Collection<MessengerFriendCategory> categories = messenger.getCategories();
/* 18:24 */     Collection<MessengerFriend> friends = messenger.getFriends();
/* 19:   */     
/* 20:26 */     MessageWriter ClientMessage = new MessageWriter(100 + (categories.size() + 30) + friends.size() * 250);
/* 21:27 */     Composer.initPacket(HEADER, ClientMessage);
/* 22:28 */     Composer.add(Integer.valueOf(messenger.getLimitFriends()), ClientMessage);
/* 23:29 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 24:30 */     Composer.add(Integer.valueOf(600), ClientMessage);
/* 25:31 */     Composer.add(Integer.valueOf(1200), ClientMessage);
/* 26:32 */     Composer.add(Integer.valueOf(categories.size()), ClientMessage);
/* 27:33 */     for (MessengerFriendCategory category : categories)
/* 28:   */     {
/* 29:34 */       Composer.add(Integer.valueOf(category.id), ClientMessage);
/* 30:35 */       Composer.add(category.name, ClientMessage);
/* 31:   */     }
/* 32:37 */     int friendSize = friends.size();
/* 33:38 */     if (friendSize > messenger.getLimitFriends()) {
/* 34:39 */       friendSize = messenger.getLimitFriends();
/* 35:   */     }
/* 36:41 */     Composer.add(Integer.valueOf(friendSize), ClientMessage);
/* 37:42 */     for (MessengerFriend friend : friends)
/* 38:   */     {
/* 39:43 */       if (friendSize-- < 1) {
/* 40:   */         break;
/* 41:   */       }
/* 42:46 */       SerializeFriend.parse(ClientMessage, friend);
/* 43:   */     }
/* 44:48 */     Composer.endPacket(ClientMessage);
/* 45:49 */     return ClientMessage;
/* 46:   */   }
/* 47:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.friendlist.MessengerInitComposer
 * JD-Core Version:    0.7.0.1
 */