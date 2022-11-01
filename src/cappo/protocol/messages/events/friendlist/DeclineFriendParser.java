/*  1:   */ package cappo.protocol.messages.events.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.Database;
/*  4:   */ import cappo.engine.logging.Log;
/*  5:   */ import cappo.engine.network.MessageReader;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.messenger.MessengerFriendRequest;
/*  9:   */ import cappo.game.player.messenger.PlayerMessenger;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ 
/* 12:   */ public class DeclineFriendParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:19 */     PlayerData playerData = Main.getPlayerData();
/* 18:21 */     if (Main.currentPacket.readBoolean())
/* 19:   */     {
/* 20:22 */       playerData.messenger.clearRequests();
/* 21:   */       try
/* 22:   */       {
/* 23:25 */         Database.exec("DELETE FROM user_friendreqs WHERE user_id=" + playerData.userId + ";", new Object[0]);
/* 24:   */       }
/* 25:   */       catch (Exception ex)
/* 26:   */       {
/* 27:28 */         Log.printException("DeclineBuddyParser", ex);
/* 28:   */       }
/* 29:   */     }
/* 30:32 */     int count = Main.currentPacket.readInt();
/* 31:34 */     for (int i = 0; i < count; i++)
/* 32:   */     {
/* 33:35 */       MessengerFriendRequest req = playerData.messenger.pickRequest(Main.currentPacket.readInt());
/* 34:36 */       if (req != null) {
/* 35:   */         try
/* 36:   */         {
/* 37:41 */           Database.exec("DELETE FROM user_friendreqs WHERE user_id=" + playerData.userId + " AND friend_id=" + req.userid + ";", new Object[0]);
/* 38:   */         }
/* 39:   */         catch (Exception ex)
/* 40:   */         {
/* 41:44 */           Log.printException("DeclineBuddyParser", ex);
/* 42:   */         }
/* 43:   */       }
/* 44:   */     }
/* 45:   */   }
/* 46:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.friendlist.DeclineFriendParser
 * JD-Core Version:    0.7.0.1
 */