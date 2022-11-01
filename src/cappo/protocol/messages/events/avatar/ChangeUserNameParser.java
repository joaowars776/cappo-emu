/*  1:   */ package cappo.protocol.messages.events.avatar;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import cappo.engine.logging.Log;
/*  6:   */ import cappo.engine.network.MessageReader;
/*  7:   */ import cappo.engine.network.QueueWriter;
/*  8:   */ import cappo.engine.player.Clients;
/*  9:   */ import cappo.engine.player.Connection;
/* 10:   */ import cappo.engine.threadpools.RoomTask;
/* 11:   */ import cappo.game.collections.Utils;
/* 12:   */ import cappo.game.player.PlayerData;
/* 13:   */ import cappo.game.player.data.AvatarData;
/* 14:   */ import cappo.game.player.messenger.MessengerFriend;
/* 15:   */ import cappo.game.player.messenger.MessengerFriendUpdate;
/* 16:   */ import cappo.game.player.messenger.PlayerMessenger;
/* 17:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 18:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 19:   */ import cappo.protocol.messages.composers.avatar.ResultChangeUserNameComposer;
/* 20:   */ import cappo.protocol.messages.composers.users.NotifyUserNameChangeComposer;
/* 21:   */ import java.sql.ResultSet;
/* 22:   */ import java.util.ArrayList;
/* 23:   */ 
/* 24:   */ public class ChangeUserNameParser
/* 25:   */   extends IncomingMessageEvent
/* 26:   */ {
/* 27:   */   public void messageReceived(Connection Main)
/* 28:   */   {
/* 29:31 */     if (!Main.haveFlag(4)) {
/* 30:32 */       return;
/* 31:   */     }
/* 32:35 */     Avatar avatar = Main.avatar;
/* 33:36 */     if (avatar == null) {
/* 34:37 */       return;
/* 35:   */     }
/* 36:41 */     long now = Utils.getTimestamp();
/* 37:42 */     if (Main.avatarData.lastCheckNameTry >= now) {
/* 38:43 */       return;
/* 39:   */     }
/* 40:45 */     Main.avatarData.lastChangeNameTry = (now + 1L);
/* 41:   */     
/* 42:47 */     String name = Main.currentPacket.readString();
/* 43:49 */     if (name.length() < 5) {
/* 44:50 */       return;
/* 45:   */     }
/* 46:52 */     if (name.length() > 20) {
/* 47:53 */       return;
/* 48:   */     }
/* 49:56 */     PlayerData playerData = Main.getPlayerData();
/* 50:58 */     if ((name == playerData.userName) || (name.toLowerCase().startsWith("mod-"))) {
/* 51:59 */       return;
/* 52:   */     }
/* 53:62 */     DBResult result = new DBResult();
/* 54:   */     try
/* 55:   */     {
/* 56:64 */       Database.query(result, "SELECT null FROM users WHERE username = ? LIMIT 1;", new Object[] { name });
/* 57:66 */       if (result.data.next())
/* 58:   */       {
/* 59:67 */         result.close();
/* 60:68 */         return;
/* 61:   */       }
/* 62:   */     }
/* 63:   */     catch (Exception ex)
/* 64:   */     {
/* 65:72 */       Log.printException("ChangeUserNameParser-1", ex);
/* 66:   */       
/* 67:74 */       result.close();
/* 68:   */       
/* 69:76 */       playerData.userName = name;
/* 70:77 */       Main.setFlag(4, false);
/* 71:78 */       QueueWriter.write(Main.socket, ResultChangeUserNameComposer.compose(0, name, new ArrayList()));
/* 72:79 */       avatar.room.sendMessage(NotifyUserNameChangeComposer.compose(playerData.userId, avatar.virtualId, name));
/* 73:82 */       for (MessengerFriend friend : playerData.messenger.getFriends())
/* 74:   */       {
/* 75:83 */         PlayerData friendPlayer = Clients.getPlayerDataLoaded(friend.userId);
/* 76:84 */         if (friendPlayer != null)
/* 77:   */         {
/* 78:88 */           PlayerMessenger messenger = friendPlayer.messenger;
/* 79:89 */           if (messenger.isOnline) {
/* 80:90 */             messenger.update(new MessengerFriendUpdate(playerData.userId, 0));
/* 81:   */           }
/* 82:   */         }
/* 83:   */       }
/* 84:   */     }
/* 85:   */   }
/* 86:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.avatar.ChangeUserNameParser
 * JD-Core Version:    0.7.0.1
 */