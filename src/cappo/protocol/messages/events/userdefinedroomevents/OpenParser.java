/*  1:   */ package cappo.protocol.messages.events.userdefinedroomevents;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.MessageWriter;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.engine.threadpools.RoomTask;
/*  8:   */ import cappo.game.collections.BaseItem;
/*  9:   */ import cappo.game.collections.BaseItem.ItemType;
/* 10:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/* 11:   */ import cappo.game.roomengine.entity.item.floor.wired.WiredItemBase;
/* 12:   */ import cappo.game.roomengine.entity.item.floor.wired.effect.WiredEffectBase;
/* 13:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 14:   */ import cappo.protocol.messages.Composer;
/* 15:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 16:   */ import java.util.Iterator;
/* 17:   */ import java.util.Map;
/* 18:   */ import java.util.Set;
/* 19:   */ 
/* 20:   */ public class OpenParser
/* 21:   */   extends IncomingMessageEvent
/* 22:   */ {
/* 23:   */   public void messageReceived(Connection Main)
/* 24:   */   {
/* 25:24 */     Avatar avatar = Main.avatar;
/* 26:25 */     if (avatar == null) {
/* 27:26 */       return;
/* 28:   */     }
/* 29:29 */     FloorItem item = avatar.room.getFloorItem(Main.currentPacket.readInt());
/* 30:30 */     if (item == null) {
/* 31:31 */       return;
/* 32:   */     }
/* 33:34 */     if (!(item instanceof WiredItemBase)) {
/* 34:35 */       return;
/* 35:   */     }
/* 36:38 */     WiredItemBase wired = (WiredItemBase)item;
/* 37:   */     
/* 38:40 */     MessageWriter ClientMessage = new MessageWriter();
/* 39:41 */     if (item.baseItem.itemType == BaseItem.ItemType.WIRED_TRIGGER)
/* 40:   */     {
/* 41:42 */       Composer.initPacket(970, ClientMessage);
/* 42:43 */       serializeWired(wired, ClientMessage);
/* 43:44 */       Composer.add(Integer.valueOf(wired.getCode()), ClientMessage);
/* 44:45 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 45:   */       
/* 46:47 */       Composer.endPacket(ClientMessage);
/* 47:48 */       QueueWriter.write(Main.socket, ClientMessage);
/* 48:   */     }
/* 49:49 */     else if (item.baseItem.itemType == BaseItem.ItemType.WIRED_EFFECT)
/* 50:   */     {
/* 51:50 */       Composer.initPacket(2221, ClientMessage);
/* 52:51 */       serializeWired(wired, ClientMessage);
/* 53:52 */       Composer.add(Integer.valueOf(wired.getCode()), ClientMessage);
/* 54:53 */       Composer.add(Integer.valueOf(((WiredEffectBase)wired).delayEffect), ClientMessage);
/* 55:54 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 56:   */       
/* 57:56 */       Composer.endPacket(ClientMessage);
/* 58:57 */       QueueWriter.write(Main.socket, ClientMessage);
/* 59:   */     }
/* 60:58 */     else if (item.baseItem.itemType == BaseItem.ItemType.WIRED_CONDITION)
/* 61:   */     {
/* 62:59 */       Composer.initPacket(3402, ClientMessage);
/* 63:60 */       serializeWired(wired, ClientMessage);
/* 64:61 */       Composer.add(Integer.valueOf(wired.getCode()), ClientMessage);
/* 65:62 */       Composer.endPacket(ClientMessage);
/* 66:63 */       QueueWriter.write(Main.socket, ClientMessage);
/* 67:   */     }
/* 68:   */   }
/* 69:   */   
/* 70:   */   private void serializeWired(WiredItemBase wired, MessageWriter ClientMessage)
/* 71:   */   {
/* 72:68 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 73:69 */     Composer.add(Integer.valueOf(5), ClientMessage);
/* 74:70 */     Composer.add(Integer.valueOf(wired.items.size()), ClientMessage);
/* 75:71 */     for (Iterator localIterator = wired.items.keySet().iterator(); localIterator.hasNext();)
/* 76:   */     {
/* 77:71 */       int id = ((Integer)localIterator.next()).intValue();
/* 78:72 */       Composer.add(Integer.valueOf(id), ClientMessage);
/* 79:   */     }
/* 80:74 */     Composer.add(Integer.valueOf(wired.baseItem.SpriteId), ClientMessage);
/* 81:75 */     Composer.add(Integer.valueOf(wired.itemId), ClientMessage);
/* 82:76 */     Composer.add(wired.getWiredData(), ClientMessage);
/* 83:77 */     int[] options = wired.getWiredOptions();
/* 84:78 */     Composer.add(Integer.valueOf(options.length), ClientMessage);
/* 85:79 */     for (int option : options) {
/* 86:80 */       Composer.add(Integer.valueOf(option), ClientMessage);
/* 87:   */     }
/* 88:82 */     Composer.add(Integer.valueOf(wired.selectionType), ClientMessage);
/* 89:   */   }
/* 90:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.userdefinedroomevents.OpenParser
 * JD-Core Version:    0.7.0.1
 */