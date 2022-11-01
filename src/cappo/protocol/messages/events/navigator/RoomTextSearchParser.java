/*   1:    */ package cappo.protocol.messages.events.navigator;
/*   2:    */ 
/*   3:    */ import cappo.engine.logging.Log;
/*   4:    */ import cappo.engine.network.MessageReader;
/*   5:    */ import cappo.engine.network.QueueWriter;
/*   6:    */ import cappo.engine.player.Connection;
/*   7:    */ import cappo.engine.threadpools.DatabaseQueryTask;
/*   8:    */ import cappo.game.roomengine.RoomData;
/*   9:    */ import cappo.game.roomengine.RoomManager;
/*  10:    */ import cappo.protocol.messages.IncomingMessageEvent;
/*  11:    */ import cappo.protocol.messages.composers.navigator.GuestRoomSearchResultComposer;
/*  12:    */ import java.lang.reflect.Method;
/*  13:    */ import java.sql.ResultSet;
/*  14:    */ import java.util.Collection;
/*  15:    */ import java.util.HashMap;
/*  16:    */ import java.util.Map;
/*  17:    */ 
/*  18:    */ public class RoomTextSearchParser
/*  19:    */   extends IncomingMessageEvent
/*  20:    */ {
/*  21:    */   private static final int PARAM_1 = 0;
/*  22:    */   private static final int PARAM_2 = 1;
/*  23:    */   private static Method roomsCallBack;
/*  24:    */   
/*  25:    */   public void messageReceived(Connection cn)
/*  26:    */   {
/*  27: 26 */     String search = cn.currentPacket.readString();
/*  28:    */     
/*  29: 28 */     Map<Integer, RoomData> roomList = new HashMap();
/*  30: 29 */     Object[] keys = RoomManager.GetRooms().values().toArray();
/*  31:    */     
/*  32: 31 */     int type = 1;
/*  33: 32 */     if (search.startsWith("owner:"))
/*  34:    */     {
/*  35: 33 */       search = search.substring(6);
/*  36: 34 */       type = 2;
/*  37:    */     }
/*  38: 37 */     for (Object key : keys)
/*  39:    */     {
/*  40: 38 */       RoomData currentRoom = (RoomData)key;
/*  41: 39 */       if ((currentRoom != null) && (!roomList.containsKey(Integer.valueOf(currentRoom.roomId)))) {
/*  42: 43 */         if (type == 1 ? 
/*  43: 44 */           !currentRoom.name.contains(search) : 
/*  44:    */           
/*  45:    */ 
/*  46:    */ 
/*  47: 48 */           (type != 2) || 
/*  48: 49 */           (currentRoom.roomOwnerName.startsWith(search)))
/*  49:    */         {
/*  50: 54 */           roomList.put(Integer.valueOf(currentRoom.roomId), currentRoom);
/*  51: 55 */           if (roomList.size() >= 50) {
/*  52:    */             break;
/*  53:    */           }
/*  54:    */         }
/*  55:    */       }
/*  56:    */     }
/*  57: 60 */     Object extra = { cn, roomList };
/*  58: 62 */     if (roomList.size() < 50)
/*  59:    */     {
/*  60:    */       DatabaseQueryTask queryTask;
/*  61:    */       DatabaseQueryTask queryTask;
/*  62: 64 */       if (type == 2)
/*  63:    */       {
/*  64: 65 */         queryTask = new DatabaseQueryTask("SELECT * FROM rooms WHERE user_name = ? ORDER BY caption DESC LIMIT " + (50 - roomList.size()) + ";", roomsCallBack, extra, new Object[] { search });
/*  65:    */       }
/*  66:    */       else
/*  67:    */       {
/*  68: 67 */         search = search + "%";
/*  69: 68 */         queryTask = new DatabaseQueryTask("SELECT * FROM rooms WHERE caption LIKE ? OR user_name LIKE ? ORDER BY caption DESC LIMIT " + (50 - roomList.size()) + ";", roomsCallBack, extra, new Object[] { search, search });
/*  70:    */       }
/*  71: 70 */       DatabaseQueryTask.addTask(queryTask, 0, 0);
/*  72:    */     }
/*  73:    */     else
/*  74:    */     {
/*  75:    */       try
/*  76:    */       {
/*  77: 73 */         roomsCallBack(null, extra);
/*  78:    */       }
/*  79:    */       catch (Exception ex)
/*  80:    */       {
/*  81: 75 */         Log.printException("", ex);
/*  82:    */       }
/*  83:    */     }
/*  84:    */   }
/*  85:    */   
/*  86:    */   public static boolean roomsCallBack(ResultSet result, Object extra)
/*  87:    */     throws Exception
/*  88:    */   {
/*  89: 81 */     Object[] data = (Object[])extra;
/*  90: 82 */     Connection cn = (Connection)data[0];
/*  91:    */     
/*  92:    */ 
/*  93: 85 */     Map<Integer, RoomData> roomList = (Map)data[1];
/*  94: 87 */     if (result != null) {
/*  95: 88 */       while (result.next())
/*  96:    */       {
/*  97: 89 */         RoomData room = RoomManager.getRoom(result.getInt("id"));
/*  98: 90 */         if (room == null) {
/*  99: 91 */           room = RoomManager.loadRoomResultSet(result);
/* 100:    */         }
/* 101: 94 */         if ((room != null) && 
/* 102: 95 */           (!roomList.containsKey(Integer.valueOf(room.roomId)))) {
/* 103: 96 */           roomList.put(Integer.valueOf(room.roomId), room);
/* 104:    */         }
/* 105:    */       }
/* 106:    */     }
/* 107:102 */     QueueWriter.write(cn.socket, GuestRoomSearchResultComposer.compose(1, "9", roomList.values()));
/* 108:    */     
/* 109:104 */     return true;
/* 110:    */   }
/* 111:    */   
/* 112:    */   static
/* 113:    */   {
/* 114:    */     try
/* 115:    */     {
/* 116:114 */       roomsCallBack = RoomTextSearchParser.class.getMethod("roomsCallBack", new Class[] { ResultSet.class, Object.class });
/* 117:    */     }
/* 118:    */     catch (Exception ex)
/* 119:    */     {
/* 120:116 */       Log.printException("", ex);
/* 121:    */     }
/* 122:    */   }
/* 123:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.RoomTextSearchParser
 * JD-Core Version:    0.7.0.1
 */