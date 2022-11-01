/*  1:   */ package cappo.protocol.messages.composers.roomsettings;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.RoomData;
/*  5:   */ import cappo.game.roomengine.settings.TradingSettings;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import cappo.protocol.messages.composers.serializers.SerializeRoomChatConfig;
/*  8:   */ import cappo.protocol.messages.composers.serializers.SerializeRoomModerationPermissions;
/*  9:   */ 
/* 10:   */ public class RoomSettingsDataComposer
/* 11:   */ {
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static final MessageWriter compose(RoomData room)
/* 15:   */   {
/* 16:20 */     MessageWriter writer = new MessageWriter();
/* 17:21 */     Composer.initPacket(HEADER, writer);
/* 18:22 */     Composer.add(Integer.valueOf(room.roomId), writer);
/* 19:23 */     Composer.add(room.name, writer);
/* 20:24 */     Composer.add(room.description, writer);
/* 21:25 */     Composer.add(Integer.valueOf(room.state), writer);
/* 22:26 */     Composer.add(Integer.valueOf(room.category), writer);
/* 23:27 */     Composer.add(Integer.valueOf(room.usersMax), writer);
/* 24:28 */     Composer.add(Integer.valueOf(100), writer);
/* 25:29 */     Composer.add(Integer.valueOf(room.tags.length), writer);
/* 26:30 */     for (String tag : room.tags) {
/* 27:31 */       Composer.add(tag, writer);
/* 28:   */     }
/* 29:33 */     Composer.add(Integer.valueOf(room.tradingSettings.permissions), writer);
/* 30:34 */     Composer.add(Integer.valueOf(room.haveFlag(2) ? 1 : 0), writer);
/* 31:35 */     Composer.add(Integer.valueOf(room.haveFlag(4) ? 1 : 0), writer);
/* 32:36 */     Composer.add(Integer.valueOf(room.haveFlag(8) ? 1 : 0), writer);
/* 33:37 */     Composer.add(Integer.valueOf(room.haveFlag(16) ? 1 : 0), writer);
/* 34:38 */     Composer.writeInt32(room.wallAnchor, writer);
/* 35:39 */     Composer.writeInt32(room.floorAnchor, writer);
/* 36:40 */     SerializeRoomChatConfig.parse(room.chatSettings, writer);
/* 37:41 */     SerializeRoomModerationPermissions.parse(room.modPermissions, writer);
/* 38:42 */     Composer.endPacket(writer);
/* 39:43 */     return writer;
/* 40:   */   }
/* 41:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.roomsettings.RoomSettingsDataComposer
 * JD-Core Version:    0.7.0.1
 */