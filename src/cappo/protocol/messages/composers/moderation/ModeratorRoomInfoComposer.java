/*  1:   */ package cappo.protocol.messages.composers.moderation;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.player.PlayerData;
/*  7:   */ import cappo.game.roomengine.RoomData;
/*  8:   */ import cappo.game.roomengine.RoomManager;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.protocol.messages.Composer;
/* 11:   */ 
/* 12:   */ public class ModeratorRoomInfoComposer
/* 13:   */ {
/* 14:   */   public static int HEADER;
/* 15:   */   
/* 16:   */   public static final MessageWriter compose(int RoomId)
/* 17:   */   {
/* 18:18 */     RoomData room = RoomManager.getRoom(RoomId);
/* 19:19 */     if (room == null) {
/* 20:20 */       return null;
/* 21:   */     }
/* 22:23 */     boolean OwnerInRoom = false;
/* 23:24 */     if ((room.roomOwner != null) && (room.roomOwner.connection != null)) {
/* 24:25 */       OwnerInRoom = room.roomOwner.connection.avatar.room.roomData == room;
/* 25:   */     }
/* 26:28 */     MessageWriter clientMessage = new MessageWriter();
/* 27:29 */     Composer.initPacket(HEADER, clientMessage);
/* 28:30 */     Composer.add(Integer.valueOf(room.roomId), clientMessage);
/* 29:31 */     Composer.add(Integer.valueOf(room.room != null ? room.room.userCount : 0), clientMessage);
/* 30:32 */     Composer.add(Boolean.valueOf(OwnerInRoom), clientMessage);
/* 31:33 */     Composer.add(Integer.valueOf(room.roomOwnerId), clientMessage);
/* 32:34 */     Composer.add(room.roomOwnerName, clientMessage);
/* 33:35 */     Composer.add(Boolean.valueOf(true), clientMessage);
/* 34:36 */     Composer.add(room.name, clientMessage);
/* 35:37 */     Composer.add(room.description, clientMessage);
/* 36:38 */     Composer.add(Integer.valueOf(room.tags.length), clientMessage);
/* 37:39 */     for (String Tag : room.tags) {
/* 38:40 */       Composer.add(Tag, clientMessage);
/* 39:   */     }
/* 40:42 */     Composer.endPacket(clientMessage);
/* 41:43 */     return clientMessage;
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.moderation.ModeratorRoomInfoComposer
 * JD-Core Version:    0.7.0.1
 */