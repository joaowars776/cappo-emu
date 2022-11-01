/*  1:   */ package cappo.protocol.messages.events.userdefinedroomevents;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  8:   */ import cappo.game.roomengine.entity.item.floor.wired.effect.WiredEffectBase;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ import cappo.protocol.messages.composers.userdefinedroomevents.WiredUpdateFailedComposer;
/* 12:   */ import cappo.protocol.messages.composers.userdefinedroomevents.WiredUpdatedComposer;
/* 13:   */ 
/* 14:   */ public class UpdateActionParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:21 */     Avatar avatar = Main.avatar;
/* 20:22 */     if (avatar == null) {
/* 21:23 */       return;
/* 22:   */     }
/* 23:26 */     FloorItem Item = avatar.room.getFloorItem(Main.currentPacket.readInt());
/* 24:27 */     if (Item == null)
/* 25:   */     {
/* 26:28 */       QueueWriter.write(Main.socket, WiredUpdateFailedComposer.compose("Error"));
/* 27:29 */       return;
/* 28:   */     }
/* 29:32 */     if (!(Item instanceof WiredEffectBase))
/* 30:   */     {
/* 31:33 */       QueueWriter.write(Main.socket, WiredUpdateFailedComposer.compose("Error"));
/* 32:34 */       return;
/* 33:   */     }
/* 34:37 */     WiredEffectBase effect = (WiredEffectBase)Item;
/* 35:   */     
/* 36:39 */     int count = Main.currentPacket.readInt();
/* 37:40 */     if (count > 0) {
/* 38:41 */       for (int i = 0; i < count; i++) {
/* 39:42 */         effect.setWiredOption(i, Main.currentPacket.readInt());
/* 40:   */       }
/* 41:   */     }
/* 42:46 */     effect.setWiredData(Main.currentPacket.readString());
/* 43:   */     
/* 44:48 */     effect.refreshItems();
/* 45:49 */     count = Main.currentPacket.readInt();
/* 46:50 */     for (int i = 0; i < count; i++) {
/* 47:51 */       effect.addItem(avatar.room.getFloorItem(Main.currentPacket.readInt()));
/* 48:   */     }
/* 49:53 */     effect.cleanDeletedItems();
/* 50:   */     
/* 51:55 */     effect.delayEffect = Main.currentPacket.readInt();
/* 52:56 */     effect.selectionType = Main.currentPacket.readInt();
/* 53:   */     
/* 54:58 */     QueueWriter.write(Main.socket, WiredUpdatedComposer.compose());
/* 55:   */   }
/* 56:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.userdefinedroomevents.UpdateActionParser
 * JD-Core Version:    0.7.0.1
 */