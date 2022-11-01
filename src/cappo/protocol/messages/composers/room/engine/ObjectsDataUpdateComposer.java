/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializeItemData;
/*  7:   */ import java.util.Set;
/*  8:   */ 
/*  9:   */ public class ObjectsDataUpdateComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(Set<FloorItem> updateFloorItems)
/* 14:   */     throws Exception
/* 15:   */   {
/* 16:20 */     int size = 0;
/* 17:21 */     MessageWriter ClientMessage = new MessageWriter(100 + updateFloorItems.size() * 100);
/* 18:22 */     Composer.initPacket(HEADER, ClientMessage);
/* 19:23 */     Composer.add(ClientMessage.setSaved(Integer.valueOf(0)), ClientMessage);
/* 20:24 */     for (FloorItem item : updateFloorItems) {
/* 21:25 */       if (item.getRoomId() > 0)
/* 22:   */       {
/* 23:26 */         size++;
/* 24:27 */         Composer.add(Integer.valueOf(item.itemId), ClientMessage);
/* 25:28 */         SerializeItemData.parse(ClientMessage, item.baseItem, item);
/* 26:   */       }
/* 27:   */     }
/* 28:31 */     ClientMessage.writeSaved(Integer.valueOf(size));
/* 29:32 */     Composer.endPacket(ClientMessage);
/* 30:33 */     return ClientMessage;
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.ObjectsDataUpdateComposer
 * JD-Core Version:    0.7.0.1
 */