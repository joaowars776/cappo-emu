/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.player.PlayerData;
/*  7:   */ import cappo.game.roomengine.RoomData;
/*  8:   */ import cappo.game.roomengine.RoomEvent;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ import cappo.protocol.messages.composers.navigator.EventComposer;
/* 12:   */ import java.util.List;
/* 13:   */ 
/* 14:   */ public class EditEventParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:19 */     Avatar avatar = Main.avatar;
/* 20:20 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 21:21 */       return;
/* 22:   */     }
/* 23:24 */     RoomData roomData = avatar.room.roomData;
/* 24:25 */     if (roomData.event == null) {
/* 25:26 */       return;
/* 26:   */     }
/* 27:29 */     roomData.event.category = Main.currentPacket.readInt();
/* 28:30 */     roomData.event.name = Main.currentPacket.readString();
/* 29:31 */     roomData.event.description = Main.currentPacket.readString();
/* 30:32 */     roomData.event.tags.clear();
/* 31:   */     
/* 32:34 */     int tagCount = Main.currentPacket.readInt();
/* 33:35 */     for (int i = 0; i < tagCount; i++) {
/* 34:36 */       roomData.event.tags.add(Main.currentPacket.readString());
/* 35:   */     }
/* 36:39 */     avatar.room.sendMessage(EventComposer.compose(Main.playerData.userId, Main.playerData.userName, roomData.roomId, roomData.event.category, roomData.event.name, roomData.event.description, roomData.event.startTime));
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.EditEventParser
 * JD-Core Version:    0.7.0.1
 */