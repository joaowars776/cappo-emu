/*  1:   */ package cappo.protocol.messages.composers.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.roomengine.RoomData;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import cappo.protocol.messages.composers.serializers.SerializeRoom;
/*  8:   */ import java.util.Collection;
/*  9:   */ 
/* 10:   */ public class GuestRoomSearchResultComposer
/* 11:   */ {
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static final MessageWriter compose(int searchType, String Type, Collection<RoomData> roomList)
/* 15:   */   {
/* 16:21 */     MessageWriter ClientMessage = new MessageWriter(100 + roomList.size() * 800);
/* 17:22 */     Composer.initPacket(HEADER, ClientMessage);
/* 18:23 */     Composer.add(Integer.valueOf(searchType), ClientMessage);
/* 19:24 */     Composer.add(Type, ClientMessage);
/* 20:25 */     Composer.add(Integer.valueOf(roomList.size()), ClientMessage);
/* 21:26 */     for (RoomData room : roomList) {
/* 22:27 */       SerializeRoom.parse(ClientMessage, room);
/* 23:   */     }
/* 24:29 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 25:30 */     Composer.endPacket(ClientMessage);
/* 26:31 */     return ClientMessage;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public static final MessageWriter compose2(int searchType, String Type, Collection<RoomTask> roomList)
/* 30:   */   {
/* 31:35 */     MessageWriter ClientMessage = new MessageWriter(100 + roomList.size() * 800);
/* 32:36 */     Composer.initPacket(HEADER, ClientMessage);
/* 33:37 */     Composer.add(Integer.valueOf(searchType), ClientMessage);
/* 34:38 */     Composer.add(Type, ClientMessage);
/* 35:39 */     Composer.add(Integer.valueOf(roomList.size()), ClientMessage);
/* 36:40 */     for (RoomTask room : roomList) {
/* 37:41 */       SerializeRoom.parse(ClientMessage, room.roomData);
/* 38:   */     }
/* 39:43 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 40:44 */     Composer.endPacket(ClientMessage);
/* 41:45 */     return ClientMessage;
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.navigator.GuestRoomSearchResultComposer
 * JD-Core Version:    0.7.0.1
 */