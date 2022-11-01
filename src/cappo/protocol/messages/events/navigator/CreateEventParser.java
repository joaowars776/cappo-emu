/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.collections.Utils;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.roomengine.RoomData;
/*  9:   */ import cappo.game.roomengine.RoomEvent;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.navigator.EventComposer;
/* 13:   */ import java.util.ArrayList;
/* 14:   */ import java.util.List;
/* 15:   */ 
/* 16:   */ public class CreateEventParser
/* 17:   */   extends IncomingMessageEvent
/* 18:   */ {
/* 19:   */   public void messageReceived(Connection Main)
/* 20:   */   {
/* 21:23 */     Avatar avatar = Main.avatar;
/* 22:24 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 23:25 */       return;
/* 24:   */     }
/* 25:28 */     RoomTask room = avatar.room;
/* 26:30 */     if ((room.roomData.event != null) || (room.roomData.state != 0)) {
/* 27:31 */       return;
/* 28:   */     }
/* 29:34 */     int category = Main.currentPacket.readInt();
/* 30:35 */     String name = Main.currentPacket.readString();
/* 31:36 */     String description = Main.currentPacket.readString();
/* 32:   */     
/* 33:38 */     room.roomData.event = new RoomEvent(room.roomId, name, description, category, null, (int)Utils.getTimestamp());
/* 34:39 */     room.roomData.event.tags = new ArrayList();
/* 35:   */     
/* 36:41 */     int tagCount = Main.currentPacket.readInt();
/* 37:42 */     for (int i = 0; i < tagCount; i++) {
/* 38:43 */       room.roomData.event.tags.add(Main.currentPacket.readString());
/* 39:   */     }
/* 40:46 */     room.sendMessage(EventComposer.compose(Main.playerData.userId, Main.playerData.userName, room.roomId, room.roomData.event.category, room.roomData.event.name, room.roomData.event.description, room.roomData.event.startTime));
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.CreateEventParser
 * JD-Core Version:    0.7.0.1
 */