/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializeItemData;
/*  7:   */ 
/*  8:   */ public class ObjectDataUpdateComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(FloorItem Item)
/* 13:   */   {
/* 14:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:20 */     Composer.add(Integer.toString(Item.itemId), ClientMessage);
/* 17:21 */     SerializeItemData.parse(ClientMessage, Item.baseItem, Item);
/* 18:22 */     Composer.endPacket(ClientMessage);
/* 19:23 */     return ClientMessage;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.ObjectDataUpdateComposer
 * JD-Core Version:    0.7.0.1
 */