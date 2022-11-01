/*  1:   */ package cappo.protocol.messages.events.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.entity.item.extradata.MapStuffData;
/*  7:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  8:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import java.util.Map;
/* 11:   */ 
/* 12:   */ public class ObjectSaveStuffDataParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:19 */     Avatar avatar = Main.avatar;
/* 18:20 */     if (avatar == null) {
/* 19:21 */       return;
/* 20:   */     }
/* 21:24 */     RoomTask room = avatar.room;
/* 22:   */     
/* 23:26 */     FloorItem floorItem = room.getFloorItem(Main.currentPacket.readInt());
/* 24:27 */     if (floorItem == null) {
/* 25:28 */       return;
/* 26:   */     }
/* 27:31 */     MapStuffData data = (MapStuffData)floorItem.extraData;
/* 28:   */     
/* 29:33 */     int size = Main.currentPacket.readInt() / 2;
/* 30:34 */     for (int i = 0; i < size; i++) {
/* 31:35 */       data.extraData.put(Main.currentPacket.readString(), Main.currentPacket.readString());
/* 32:   */     }
/* 33:38 */     room.floorItemUpdateNeeded(floorItem);
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.ObjectSaveStuffDataParser
 * JD-Core Version:    0.7.0.1
 */