/*  1:   */ package cappo.protocol.messages.composers.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.RoomData;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializeRoom;
/*  7:   */ import cappo.protocol.messages.composers.serializers.SerializeRoomChatConfig;
/*  8:   */ import cappo.protocol.messages.composers.serializers.SerializeRoomModerationPermissions;
/*  9:   */ 
/* 10:   */ public class GuestRoomResultComposer
/* 11:   */ {
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static final MessageWriter compose(RoomData room, boolean isLoading, boolean isPreEnter, boolean freeToEnter)
/* 15:   */   {
/* 16:22 */     MessageWriter writer = new MessageWriter();
/* 17:23 */     Composer.initPacket(HEADER, writer);
/* 18:24 */     Composer.add(Boolean.valueOf(isLoading), writer);
/* 19:25 */     SerializeRoom.parse(writer, room);
/* 20:26 */     Composer.add(Boolean.valueOf(isPreEnter), writer);
/* 21:27 */     Composer.add(Boolean.valueOf(room.haveFlag(32)), writer);
/* 22:28 */     Composer.add(Boolean.valueOf(freeToEnter), writer);
/* 23:29 */     Composer.add(Boolean.valueOf(room.muteAllOn), writer);
/* 24:30 */     SerializeRoomModerationPermissions.parse(room.modPermissions, writer);
/* 25:31 */     Composer.add(Boolean.valueOf(true), writer);
/* 26:32 */     SerializeRoomChatConfig.parse(room.chatSettings, writer);
/* 27:33 */     Composer.endPacket(writer);
/* 28:34 */     return writer;
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.navigator.GuestRoomResultComposer
 * JD-Core Version:    0.7.0.1
 */