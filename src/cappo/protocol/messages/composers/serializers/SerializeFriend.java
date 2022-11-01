/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.player.Clients;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.player.AvatarLook;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.messenger.MessengerFriend;
/*  9:   */ import cappo.protocol.messages.Composer;
/* 10:   */ 
/* 11:   */ public class SerializeFriend
/* 12:   */ {
/* 13:   */   public static void parse(MessageWriter ClientMessage, MessengerFriend friend)
/* 14:   */   {
/* 15:17 */     PlayerData friendPlayer = Clients.getPlayerData(friend.userId);
/* 16:   */     
/* 17:19 */     Composer.add(Integer.valueOf(friend.userId), ClientMessage);
/* 18:20 */     Composer.add(friendPlayer.userName, ClientMessage);
/* 19:21 */     Composer.add(Integer.valueOf(friendPlayer.sex), ClientMessage);
/* 20:22 */     if (friendPlayer.connection != null)
/* 21:   */     {
/* 22:23 */       Composer.add(Boolean.valueOf(true), ClientMessage);
/* 23:24 */       Composer.add(Boolean.valueOf(friendPlayer.connection.avatar != null), ClientMessage);
/* 24:   */     }
/* 25:   */     else
/* 26:   */     {
/* 27:26 */       Composer.add(Boolean.valueOf(false), ClientMessage);
/* 28:27 */       Composer.add(Boolean.valueOf(false), ClientMessage);
/* 29:   */     }
/* 30:29 */     Composer.add(friendPlayer.avatarLook.toString(), ClientMessage);
/* 31:30 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 32:31 */     Composer.add(friendPlayer.motto, ClientMessage);
/* 33:32 */     Composer.add(friendPlayer.getRealName(), ClientMessage);
/* 34:33 */     Composer.add("", ClientMessage);
/* 35:34 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 36:35 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 37:36 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 38:37 */     Composer.writeInt16(friend.friendType, ClientMessage);
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeFriend
 * JD-Core Version:    0.7.0.1
 */