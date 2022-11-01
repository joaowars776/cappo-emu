/*  1:   */ package cappo.protocol.messages.events.roomsettings;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.RoomData;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.navigator.RoomUpdatedComposer;
/*  9:   */ import cappo.protocol.messages.composers.room.chat.ChatSettingsComposer;
/* 10:   */ import cappo.protocol.messages.composers.room.engine.RoomVisualizationSettingsComposer;
/* 11:   */ import cappo.protocol.messages.composers.roomsettings.RoomSettingsSavedComposer;
/* 12:   */ import cappo.protocol.messages.parsers.roomsettings.SaveRoomSettingsMessageParser;
/* 13:   */ 
/* 14:   */ public class SaveRoomSettingsMessageEvent
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection cn)
/* 18:   */     throws Exception
/* 19:   */   {
/* 20:25 */     SaveRoomSettingsMessageParser parser = new SaveRoomSettingsMessageParser(cn.currentPacket, cn);
/* 21:26 */     if (!parser.isValid) {
/* 22:27 */       return;
/* 23:   */     }
/* 24:30 */     parser.setRoomName(3);
/* 25:31 */     parser.setRoomDesc();
/* 26:32 */     parser.setRoomState(-1, 3);
/* 27:33 */     parser.setRoomPassword();
/* 28:34 */     parser.setRoomMaxUsers(10, 100, 5);
/* 29:35 */     parser.setRoomCategory();
/* 30:36 */     parser.setRoomTags(2, 30);
/* 31:37 */     parser.setRoomTrading(0, 2);
/* 32:38 */     parser.setRoomOthersSettings();
/* 33:39 */     parser.setRoomVisualizationSettings();
/* 34:40 */     parser.setRoomModPermissionsSettings();
/* 35:41 */     parser.setRoomChatSettings();
/* 36:   */     
/* 37:43 */     RoomData roomData = parser.getRoomData();
/* 38:   */     
/* 39:45 */     QueueWriter.write(cn.socket, RoomSettingsSavedComposer.compose(roomData.roomId));
/* 40:   */     
/* 41:47 */     RoomTask room = roomData.room;
/* 42:48 */     if (room != null)
/* 43:   */     {
/* 44:49 */       room.sendMessage(RoomUpdatedComposer.compose(roomData.roomId));
/* 45:51 */       if (parser.roomVisualizationChanged) {
/* 46:52 */         room.sendMessage(RoomVisualizationSettingsComposer.compose(Boolean.valueOf(roomData.haveFlag(16)), roomData.wallAnchor, roomData.floorAnchor));
/* 47:   */       }
/* 48:55 */       if (parser.roomChatChanged) {
/* 49:56 */         room.sendMessage(ChatSettingsComposer.compose(roomData.chatSettings));
/* 50:   */       }
/* 51:   */     }
/* 52:   */   }
/* 53:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.roomsettings.SaveRoomSettingsMessageEvent
 * JD-Core Version:    0.7.0.1
 */