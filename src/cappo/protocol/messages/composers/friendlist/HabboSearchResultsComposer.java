/*  1:   */ package cappo.protocol.messages.composers.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.player.AvatarLook;
/*  6:   */ import cappo.game.player.PlayerData;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ import java.util.List;
/*  9:   */ 
/* 10:   */ public class HabboSearchResultsComposer
/* 11:   */ {
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static final MessageWriter compose(List<PlayerData> PlayersFriends, List<PlayerData> Players)
/* 15:   */   {
/* 16:19 */     MessageWriter ClientMessage = new MessageWriter(1000 + (Players.size() + PlayersFriends.size()) * 150);
/* 17:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 18:21 */     Composer.add(Integer.valueOf(PlayersFriends.size()), ClientMessage);
/* 19:22 */     for (PlayerData client : PlayersFriends)
/* 20:   */     {
/* 21:23 */       Composer.add(Integer.valueOf(client.userId), ClientMessage);
/* 22:24 */       Composer.add(client.userName, ClientMessage);
/* 23:25 */       Composer.add(client.motto, ClientMessage);
/* 24:26 */       if (client.connection != null)
/* 25:   */       {
/* 26:27 */         Composer.add(Boolean.valueOf(true), ClientMessage);
/* 27:28 */         Composer.add(Boolean.valueOf(client.connection.avatar != null), ClientMessage);
/* 28:   */       }
/* 29:   */       else
/* 30:   */       {
/* 31:30 */         Composer.add(Boolean.valueOf(false), ClientMessage);
/* 32:31 */         Composer.add(Boolean.valueOf(false), ClientMessage);
/* 33:   */       }
/* 34:33 */       Composer.add("", ClientMessage);
/* 35:34 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 36:35 */       Composer.add(client.avatarLook.toString(), ClientMessage);
/* 37:36 */       Composer.add(client.getRealName(), ClientMessage);
/* 38:   */     }
/* 39:38 */     Composer.add(Integer.valueOf(Players.size()), ClientMessage);
/* 40:39 */     for (PlayerData client : Players)
/* 41:   */     {
/* 42:40 */       Composer.add(Integer.valueOf(client.userId), ClientMessage);
/* 43:41 */       Composer.add(client.userName, ClientMessage);
/* 44:42 */       Composer.add(client.motto, ClientMessage);
/* 45:43 */       if (client.connection != null)
/* 46:   */       {
/* 47:44 */         Composer.add(Boolean.valueOf(true), ClientMessage);
/* 48:45 */         Composer.add(Boolean.valueOf(client.connection.avatar != null), ClientMessage);
/* 49:   */       }
/* 50:   */       else
/* 51:   */       {
/* 52:47 */         Composer.add(Boolean.valueOf(false), ClientMessage);
/* 53:48 */         Composer.add(Boolean.valueOf(false), ClientMessage);
/* 54:   */       }
/* 55:50 */       Composer.add("", ClientMessage);
/* 56:51 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 57:52 */       Composer.add(client.avatarLook.toString(), ClientMessage);
/* 58:53 */       Composer.add(client.getRealName(), ClientMessage);
/* 59:   */     }
/* 60:55 */     Composer.endPacket(ClientMessage);
/* 61:56 */     return ClientMessage;
/* 62:   */   }
/* 63:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.friendlist.HabboSearchResultsComposer
 * JD-Core Version:    0.7.0.1
 */