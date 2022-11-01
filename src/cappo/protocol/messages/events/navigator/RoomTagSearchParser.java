/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import cappo.engine.logging.Log;
/*  6:   */ import cappo.engine.network.MessageReader;
/*  7:   */ import cappo.engine.network.QueueWriter;
/*  8:   */ import cappo.engine.player.Connection;
/*  9:   */ import cappo.game.roomengine.RoomData;
/* 10:   */ import cappo.game.roomengine.RoomManager;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.navigator.GuestRoomSearchResultComposer;
/* 13:   */ import java.sql.ResultSet;
/* 14:   */ import java.util.ArrayList;
/* 15:   */ import java.util.Collection;
/* 16:   */ import java.util.Iterator;
/* 17:   */ import java.util.List;
/* 18:   */ import java.util.Map;
/* 19:   */ 
/* 20:   */ public class RoomTagSearchParser
/* 21:   */   extends IncomingMessageEvent
/* 22:   */ {
/* 23:   */   public void messageReceived(Connection Main)
/* 24:   */   {
/* 25:25 */     String search = Main.currentPacket.readString();
/* 26:   */     
/* 27:27 */     List<Integer> results = new ArrayList();
/* 28:28 */     Object[] keys = RoomManager.GetRooms().values().toArray();
/* 29:30 */     for (Object key : keys)
/* 30:   */     {
/* 31:31 */       RoomData currentRoom = (RoomData)key;
/* 32:32 */       if ((currentRoom != null) && (!results.contains(Integer.valueOf(currentRoom.roomId))))
/* 33:   */       {
/* 34:36 */         for (String tag : currentRoom.tags) {
/* 35:37 */           if (tag.equals(search))
/* 36:   */           {
/* 37:38 */             results.add(Integer.valueOf(currentRoom.roomId));
/* 38:39 */             break;
/* 39:   */           }
/* 40:   */         }
/* 41:43 */         if (results.size() > 49) {
/* 42:   */           break;
/* 43:   */         }
/* 44:   */       }
/* 45:   */     }
/* 46:49 */     if (results.size() < 50)
/* 47:   */     {
/* 48:50 */       DBResult result = new DBResult();
/* 49:   */       try
/* 50:   */       {
/* 51:52 */         Database.query(result, "SELECT DISTINCT id FROM rooms WHERE tags = ? ORDER BY caption DESC LIMIT " + (50 - results.size()) + ";", new Object[] { search });
/* 52:54 */         while (result.data.next())
/* 53:   */         {
/* 54:55 */           int id = result.data.getInt("id");
/* 55:56 */           if (!results.contains(Integer.valueOf(id)))
/* 56:   */           {
/* 57:57 */             results.add(Integer.valueOf(id));
/* 58:59 */             if (results.size() > 49) {
/* 59:   */               break;
/* 60:   */             }
/* 61:   */           }
/* 62:   */         }
/* 63:   */       }
/* 64:   */       catch (Exception ex)
/* 65:   */       {
/* 66:65 */         Log.printException("RoomTagSearchParser-1", ex);
/* 67:   */       }
/* 68:67 */       result.close();
/* 69:   */     }
/* 70:70 */     List<RoomData> roomList = new ArrayList();
/* 71:71 */     for (Iterator localIterator = results.iterator(); localIterator.hasNext();)
/* 72:   */     {
/* 73:71 */       int RoomId = ((Integer)localIterator.next()).intValue();
/* 74:   */       try
/* 75:   */       {
/* 76:73 */         RoomData room = RoomManager.getRoom(RoomId);
/* 77:74 */         if (room == null)
/* 78:   */         {
/* 79:75 */           room = RoomManager.loadRoom(RoomId);
/* 80:76 */           if (room == null) {}
/* 81:   */         }
/* 82:   */         else
/* 83:   */         {
/* 84:80 */           roomList.add(room);
/* 85:   */         }
/* 86:   */       }
/* 87:   */       catch (Exception ex)
/* 88:   */       {
/* 89:82 */         Log.printException("RoomTagSearchParser-2", ex);
/* 90:   */       }
/* 91:   */     }
/* 92:86 */     QueueWriter.write(Main.socket, GuestRoomSearchResultComposer.compose(1, search, roomList));
/* 93:   */   }
/* 94:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.RoomTagSearchParser
 * JD-Core Version:    0.7.0.1
 */