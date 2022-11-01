/*  1:   */ package cappo.protocol.messages.composers.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.player.PlayerData;
/*  5:   */ import cappo.game.player.messenger.MessengerFriend;
/*  6:   */ import cappo.game.player.messenger.MessengerFriendUpdate;
/*  7:   */ import cappo.game.player.messenger.PlayerMessenger;
/*  8:   */ import cappo.protocol.messages.Composer;
/*  9:   */ import cappo.protocol.messages.composers.serializers.SerializeFriend;
/* 10:   */ import java.util.Collection;
/* 11:   */ 
/* 12:   */ public class FriendsUpdatesComposer
/* 13:   */ {
/* 14:   */   public static int HEADER;
/* 15:   */   
/* 16:   */   public static final MessageWriter compose(Collection<MessengerFriendUpdate> updates, PlayerData playerData)
/* 17:   */   {
/* 18:22 */     MessageWriter ClientMessage = new MessageWriter(1000 + updates.size() * 300);
/* 19:23 */     Composer.initPacket(HEADER, ClientMessage);
/* 20:24 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 21:25 */     Composer.add(ClientMessage.setSaved(Integer.valueOf(0)), ClientMessage);
/* 22:26 */     int i = 0;
/* 23:27 */     for (MessengerFriendUpdate update : updates) {
/* 24:28 */       if (update.type == -1)
/* 25:   */       {
/* 26:29 */         i++;
/* 27:30 */         Composer.add(Integer.valueOf(update.type), ClientMessage);
/* 28:31 */         Composer.add(Integer.valueOf(update.userId), ClientMessage);
/* 29:   */       }
/* 30:   */       else
/* 31:   */       {
/* 32:35 */         MessengerFriend friend = playerData.messenger.getFriend(update.userId);
/* 33:36 */         if (friend != null)
/* 34:   */         {
/* 35:39 */           i++;
/* 36:   */           
/* 37:41 */           Composer.add(Integer.valueOf(update.type), ClientMessage);
/* 38:42 */           SerializeFriend.parse(ClientMessage, friend);
/* 39:   */         }
/* 40:   */       }
/* 41:   */     }
/* 42:44 */     ClientMessage.writeSaved(Integer.valueOf(i));
/* 43:45 */     Composer.endPacket(ClientMessage);
/* 44:46 */     return ClientMessage;
/* 45:   */   }
/* 46:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.friendlist.FriendsUpdatesComposer
 * JD-Core Version:    0.7.0.1
 */