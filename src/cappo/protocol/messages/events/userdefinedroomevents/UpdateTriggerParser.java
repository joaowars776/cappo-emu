/*  1:   */ package cappo.protocol.messages.events.userdefinedroomevents;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  8:   */ import cappo.game.roomengine.entity.item.floor.wired.trigger.WiredTriggerBase;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ import cappo.protocol.messages.composers.userdefinedroomevents.WiredUpdateFailedComposer;
/* 12:   */ import cappo.protocol.messages.composers.userdefinedroomevents.WiredUpdatedComposer;
/* 13:   */ 
/* 14:   */ public class UpdateTriggerParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:21 */     Avatar avatar = Main.avatar;
/* 20:22 */     if (avatar == null)
/* 21:   */     {
/* 22:23 */       QueueWriter.write(Main.socket, WiredUpdateFailedComposer.compose("Error"));
/* 23:24 */       return;
/* 24:   */     }
/* 25:27 */     FloorItem Item = avatar.room.getFloorItem(Main.currentPacket.readInt());
/* 26:28 */     if (Item == null)
/* 27:   */     {
/* 28:29 */       QueueWriter.write(Main.socket, WiredUpdateFailedComposer.compose("Error"));
/* 29:30 */       return;
/* 30:   */     }
/* 31:33 */     if (!(Item instanceof WiredTriggerBase))
/* 32:   */     {
/* 33:34 */       QueueWriter.write(Main.socket, WiredUpdateFailedComposer.compose("Error"));
/* 34:35 */       return;
/* 35:   */     }
/* 36:38 */     WiredTriggerBase trigger = (WiredTriggerBase)Item;
/* 37:   */     
/* 38:40 */     int count = Main.currentPacket.readInt();
/* 39:41 */     if (count > 0) {
/* 40:42 */       for (int i = 0; i < count; i++) {
/* 41:43 */         trigger.setWiredOption(i, Main.currentPacket.readInt());
/* 42:   */       }
/* 43:   */     }
/* 44:47 */     trigger.setWiredData(Main.currentPacket.readString());
/* 45:   */     
/* 46:49 */     trigger.refreshItems();
/* 47:50 */     count = Main.currentPacket.readInt();
/* 48:51 */     for (int i = 0; i < count; i++) {
/* 49:52 */       trigger.addItem(avatar.room.getFloorItem(Main.currentPacket.readInt()));
/* 50:   */     }
/* 51:54 */     trigger.cleanDeletedItems();
/* 52:   */     
/* 53:56 */     trigger.selectionType = Main.currentPacket.readInt();
/* 54:   */     
/* 55:58 */     QueueWriter.write(Main.socket, WiredUpdatedComposer.compose());
/* 56:   */   }
/* 57:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.userdefinedroomevents.UpdateTriggerParser
 * JD-Core Version:    0.7.0.1
 */