/*  1:   */ package cappo.protocol.messages.events.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import cappo.engine.logging.Log;
/*  6:   */ import cappo.engine.network.MessageReader;
/*  7:   */ import cappo.engine.network.QueueWriter;
/*  8:   */ import cappo.engine.player.Clients;
/*  9:   */ import cappo.engine.player.Connection;
/* 10:   */ import cappo.game.player.PlayerData;
/* 11:   */ import cappo.game.player.messenger.PlayerMessenger;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ import cappo.protocol.messages.composers.friendlist.HabboSearchResultsComposer;
/* 14:   */ import java.sql.ResultSet;
/* 15:   */ import java.util.ArrayList;
/* 16:   */ import java.util.Collection;
/* 17:   */ import java.util.Iterator;
/* 18:   */ import java.util.List;
/* 19:   */ import java.util.Map;
/* 20:   */ 
/* 21:   */ public class HabboSearchParser
/* 22:   */   extends IncomingMessageEvent
/* 23:   */ {
/* 24:   */   public void messageReceived(Connection Main)
/* 25:   */   {
/* 26:25 */     String Search = Main.currentPacket.readString();
/* 27:   */     
/* 28:27 */     List<Integer> Results = new ArrayList();
/* 29:28 */     int size = 0;
/* 30:   */     
/* 31:30 */     Object[] keys = Clients.GetClients().values().toArray();
/* 32:31 */     for (Object key : keys)
/* 33:   */     {
/* 34:32 */       PlayerData Current = (PlayerData)key;
/* 35:33 */       if (Current != null) {
/* 36:36 */         if (!Results.contains(Integer.valueOf(Current.userId))) {
/* 37:40 */           if (Current.userName.contains(Search))
/* 38:   */           {
/* 39:44 */             Results.add(Integer.valueOf(Current.userId));
/* 40:   */             
/* 41:46 */             size++;
/* 42:46 */             if (size >= 20) {
/* 43:   */               break;
/* 44:   */             }
/* 45:   */           }
/* 46:   */         }
/* 47:   */       }
/* 48:   */     }
/* 49:51 */     if (size < 20)
/* 50:   */     {
/* 51:53 */       DBResult result = new DBResult();
/* 52:   */       try
/* 53:   */       {
/* 54:55 */         Database.query(result, "SELECT id FROM users WHERE username LIKE ? ORDER BY username DESC LIMIT " + (20 - size) + ";", new Object[] { Search + "%" });
/* 55:56 */         while (result.data.next())
/* 56:   */         {
/* 57:57 */           int id = result.data.getInt("id");
/* 58:58 */           if (!Results.contains(Integer.valueOf(id))) {
/* 59:59 */             Results.add(Integer.valueOf(id));
/* 60:   */           }
/* 61:   */         }
/* 62:   */       }
/* 63:   */       catch (Exception ex)
/* 64:   */       {
/* 65:63 */         Log.printException("HabboSearchParser", ex);
/* 66:   */       }
/* 67:65 */       result.close();
/* 68:   */     }
/* 69:68 */     List<PlayerData> PlayersFriends = new ArrayList();
/* 70:69 */     Object Players = new ArrayList(Results.size());
/* 71:71 */     for (??? = Results.iterator(); ((Iterator)???).hasNext();)
/* 72:   */     {
/* 73:71 */       int UserId = ((Integer)((Iterator)???).next()).intValue();
/* 74:   */       try
/* 75:   */       {
/* 76:73 */         PlayerData User = Clients.getPlayerData(UserId);
/* 77:74 */         if (User != null) {
/* 78:78 */           if (Main.playerData.messenger.haveFriend(User.userId)) {
/* 79:79 */             PlayersFriends.add(User);
/* 80:   */           } else {
/* 81:81 */             ((List)Players).add(User);
/* 82:   */           }
/* 83:   */         }
/* 84:   */       }
/* 85:   */       catch (Exception ex)
/* 86:   */       {
/* 87:84 */         Log.printException("HabboSearchParser-1", ex);
/* 88:   */       }
/* 89:   */     }
/* 90:88 */     QueueWriter.write(Main.socket, HabboSearchResultsComposer.compose(PlayersFriends, (List)Players));
/* 91:   */   }
/* 92:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.friendlist.HabboSearchParser
 * JD-Core Version:    0.7.0.1
 */