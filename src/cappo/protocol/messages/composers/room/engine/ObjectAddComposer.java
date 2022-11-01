/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.player.PlayerData;
/*  5:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import cappo.protocol.messages.composers.serializers.SerializeFloorItem;
/*  8:   */ 
/*  9:   */ public class ObjectAddComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(FloorItem Item)
/* 14:   */   {
/* 15:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 16:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */     SerializeFloorItem.parse(ClientMessage, Item);
/* 18:21 */     Composer.add(Item.owner.userName, ClientMessage);
/* 19:22 */     Composer.endPacket(ClientMessage);
/* 20:23 */     return ClientMessage;
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.ObjectAddComposer
 * JD-Core Version:    0.7.0.1
 */