/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.BaseItem;
/*  5:   */ import cappo.game.games.snowwar.Direction8;
/*  6:   */ import cappo.game.player.PlayerData;
/*  7:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  8:   */ import cappo.protocol.messages.Composer;
/*  9:   */ 
/* 10:   */ public class SerializeFloorItem
/* 11:   */ {
/* 12:   */   public static void parse(MessageWriter ClientMessage, FloorItem Item)
/* 13:   */   {
/* 14:15 */     Composer.add(Integer.valueOf(Item.itemId), ClientMessage);
/* 15:16 */     Composer.add(Integer.valueOf(Item.baseItem.SpriteId), ClientMessage);
/* 16:17 */     Composer.add(Integer.valueOf(Item.getX()), ClientMessage);
/* 17:18 */     Composer.add(Integer.valueOf(Item.getY()), ClientMessage);
/* 18:19 */     Composer.add(Integer.valueOf(Item.getDir().getRot()), ClientMessage);
/* 19:20 */     Composer.add(Float.toString(Item.getZ()).replace(",", "."), ClientMessage);
/* 20:21 */     Composer.add(Float.toString(Item.baseItem.Height).replace(",", "."), ClientMessage);
/* 21:22 */     Composer.add(Integer.valueOf(Item.getExtraParam()), ClientMessage);
/* 22:23 */     SerializeItemData.parse(ClientMessage, Item.baseItem, Item);
/* 23:24 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 24:25 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 25:26 */     Composer.add(Integer.valueOf(Item.owner.userId), ClientMessage);
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeFloorItem
 * JD-Core Version:    0.7.0.1
 */