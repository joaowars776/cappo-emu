/*  1:   */ package cappo.protocol.messages.events.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.Database;
/*  4:   */ import cappo.engine.logging.Log;
/*  5:   */ import cappo.engine.network.MessageReader;
/*  6:   */ import cappo.engine.network.QueueWriter;
/*  7:   */ import cappo.engine.player.Connection;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.game.player.messenger.PlayerMessenger;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ 
/* 12:   */ public class RemoveFriendParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:18 */     int Count = Main.currentPacket.readInt();
/* 18:19 */     String ids = "";
/* 19:20 */     for (int i = 0; i < Count; i++)
/* 20:   */     {
/* 21:21 */       int userId = Main.currentPacket.readInt();
/* 22:22 */       Main.playerData.messenger.removeFriend(userId);
/* 23:24 */       if (!ids.isEmpty()) {
/* 24:25 */         ids = ids.concat(" OR ");
/* 25:   */       }
/* 26:27 */       ids = ids.concat("(user_id=" + Main.playerData.userId + " AND friend_id=" + userId + ") OR (user_id=" + userId + " AND friend_id=" + Main.playerData.userId + ")");
/* 27:   */     }
/* 28:30 */     if (!ids.isEmpty()) {
/* 29:   */       try
/* 30:   */       {
/* 31:32 */         Database.exec("DELETE FROM user_friends WHERE " + ids + ";", new Object[0]);
/* 32:   */       }
/* 33:   */       catch (Exception ex)
/* 34:   */       {
/* 35:35 */         Log.printException("AcceptBuddyParser", ex);
/* 36:   */       }
/* 37:   */     }
/* 38:39 */     QueueWriter.write(Main.socket, Main.playerData.messenger.getFriendUpdstes());
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.friendlist.RemoveFriendParser
 * JD-Core Version:    0.7.0.1
 */