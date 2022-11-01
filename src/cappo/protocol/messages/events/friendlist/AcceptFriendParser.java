/*  1:   */ package cappo.protocol.messages.events.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import cappo.engine.logging.Log;
/*  6:   */ import cappo.engine.network.MessageReader;
/*  7:   */ import cappo.engine.network.QueueWriter;
/*  8:   */ import cappo.engine.player.Connection;
/*  9:   */ import cappo.game.player.PlayerData;
/* 10:   */ import cappo.game.player.messenger.MessengerFriendRequest;
/* 11:   */ import cappo.game.player.messenger.PlayerMessenger;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ 
/* 14:   */ public class AcceptFriendParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:21 */     PlayerData playerData = Main.getPlayerData();
/* 20:   */     
/* 21:23 */     int Count = Main.currentPacket.readInt();
/* 22:24 */     String ids = "";
/* 23:25 */     DBResult result = new DBResult();
/* 24:26 */     for (int i = 0; i < Count; i++)
/* 25:   */     {
/* 26:27 */       MessengerFriendRequest req = playerData.messenger.pickRequest(Main.currentPacket.readInt());
/* 27:28 */       if (req != null)
/* 28:   */       {
/* 29:32 */         playerData.messenger.addFriend(req.userid);
/* 30:34 */         if (!req.needInsert)
/* 31:   */         {
/* 32:35 */           if (!ids.isEmpty()) {
/* 33:36 */             ids = ids.concat(" OR ");
/* 34:   */           }
/* 35:38 */           ids = ids.concat("(user_id=" + playerData.userId + " AND friend_id=" + req.userid + ")");
/* 36:   */         }
/* 37:   */       }
/* 38:   */     }
/* 39:41 */     result.close();
/* 40:43 */     if (!ids.isEmpty()) {
/* 41:   */       try
/* 42:   */       {
/* 43:45 */         Database.exec("DELETE FROM user_friendreqs WHERE " + ids + ";", new Object[0]);
/* 44:   */       }
/* 45:   */       catch (Exception ex)
/* 46:   */       {
/* 47:48 */         Log.printException("AcceptBuddyParser", ex);
/* 48:   */       }
/* 49:   */     }
/* 50:52 */     QueueWriter.write(Main.socket, playerData.messenger.getFriendUpdstes());
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.friendlist.AcceptFriendParser
 * JD-Core Version:    0.7.0.1
 */