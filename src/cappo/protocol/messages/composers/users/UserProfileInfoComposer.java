/*  1:   */ package cappo.protocol.messages.composers.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.Server;
/*  4:   */ import cappo.engine.network.MessageWriter;
/*  5:   */ import cappo.game.collections.Utils;
/*  6:   */ import cappo.game.player.AvatarLook;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.messenger.PlayerMessenger;
/*  9:   */ import cappo.protocol.messages.Composer;
/* 10:   */ import java.text.SimpleDateFormat;
/* 11:   */ 
/* 12:   */ public class UserProfileInfoComposer
/* 13:   */ {
/* 14:   */   public static int HEADER;
/* 15:   */   
/* 16:   */   public static final MessageWriter compose(PlayerData client, boolean isFriend, boolean pendingFriend)
/* 17:   */   {
/* 18:19 */     MessageWriter ClientMessage = new MessageWriter();
/* 19:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 20:21 */     Composer.add(Integer.valueOf(client.userId), ClientMessage);
/* 21:22 */     Composer.add(client.userName, ClientMessage);
/* 22:23 */     Composer.add(client.avatarLook.toString(), ClientMessage);
/* 23:24 */     Composer.add(client.motto, ClientMessage);
/* 24:25 */     Composer.add(Server.date.format(Utils.GetDate(client.registerDate * 1000L)), ClientMessage);
/* 25:26 */     Composer.add(Integer.valueOf(client.AchievementsScore), ClientMessage);
/* 26:27 */     Composer.add(Integer.valueOf(client.messenger.friendsCount()), ClientMessage);
/* 27:28 */     Composer.add(Boolean.valueOf(isFriend), ClientMessage);
/* 28:29 */     Composer.add(Boolean.valueOf(pendingFriend), ClientMessage);
/* 29:30 */     Composer.add(Boolean.valueOf(client.connection != null), ClientMessage);
/* 30:31 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 31:   */     
/* 32:33 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 33:34 */     Composer.add("Cappo Java", ClientMessage);
/* 34:35 */     Composer.add("E001", ClientMessage);
/* 35:36 */     Composer.add("100", ClientMessage);
/* 36:37 */     Composer.add("100", ClientMessage);
/* 37:38 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 38:   */     
/* 39:40 */     Composer.add(Long.valueOf(Utils.getTimestamp() - client.lastVisit), ClientMessage);
/* 40:41 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 41:42 */     Composer.endPacket(ClientMessage);
/* 42:43 */     return ClientMessage;
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.users.UserProfileInfoComposer
 * JD-Core Version:    0.7.0.1
 */