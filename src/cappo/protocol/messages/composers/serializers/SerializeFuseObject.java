/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.BaseItem;
/*  5:   */ import cappo.game.games.snowwar.GamefuseObject;
/*  6:   */ import cappo.game.games.snowwar.Tile;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ 
/*  9:   */ public class SerializeFuseObject
/* 10:   */ {
/* 11:   */   public static void parse(MessageWriter ClientMessage, GamefuseObject fuseItem)
/* 12:   */   {
/* 13:16 */     Composer.add(fuseItem.baseItem.Name, ClientMessage);
/* 14:17 */     Composer.add(Integer.valueOf(fuseItem.itemId), ClientMessage);
/* 15:18 */     Composer.add(Integer.valueOf(fuseItem.X), ClientMessage);
/* 16:19 */     Composer.add(Integer.valueOf(fuseItem.Y), ClientMessage);
/* 17:20 */     Composer.add(Integer.valueOf(fuseItem.baseItem.xDim), ClientMessage);
/* 18:21 */     Composer.add(Integer.valueOf(fuseItem.baseItem.yDim), ClientMessage);
/* 19:22 */     Composer.add(Integer.valueOf((int)(fuseItem.baseItem.Height * Tile.TILE_SIZE)), ClientMessage);
/* 20:23 */     Composer.add(Integer.valueOf(fuseItem.Rot), ClientMessage);
/* 21:24 */     Composer.add(Integer.valueOf(fuseItem.Z), ClientMessage);
/* 22:25 */     Composer.add(Boolean.valueOf(fuseItem.baseItem.allowWalk), ClientMessage);
/* 23:26 */     SerializeItemData.parse(ClientMessage, fuseItem.baseItem, fuseItem);
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeFuseObject
 * JD-Core Version:    0.7.0.1
 */