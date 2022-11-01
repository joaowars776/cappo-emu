/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class ObjectRemoveComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(FloorItem Item, int pickerId, int delay)
/* 12:   */   {
/* 13:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 14:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 15:19 */     Composer.add(Integer.toString(Item.itemId), ClientMessage);
/* 16:20 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(pickerId), ClientMessage);
/* 18:22 */     Composer.add(Integer.valueOf(delay), ClientMessage);
/* 19:23 */     Composer.endPacket(ClientMessage);
/* 20:24 */     return ClientMessage;
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.ObjectRemoveComposer
 * JD-Core Version:    0.7.0.1
 */