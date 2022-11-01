/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.BaseItem;
/*  5:   */ import cappo.game.player.PlayerData;
/*  6:   */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*  7:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  8:   */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/*  9:   */ import cappo.protocol.messages.Composer;
/* 10:   */ 
/* 11:   */ public class SerializeWallItem
/* 12:   */ {
/* 13:   */   public static void parse(MessageWriter ClientMessage, GenericWallItem item)
/* 14:   */   {
/* 15:16 */     Composer.add(Integer.toString(item.itemId), ClientMessage);
/* 16:17 */     Composer.add(Integer.valueOf(item.baseItem.SpriteId), ClientMessage);
/* 17:18 */     Composer.add(item.roomDataString(), ClientMessage);
/* 18:19 */     if (item.baseItem.interactorType == Interactor.InteractorType.postit) {
/* 19:20 */       Composer.add(item.extraData.getWallLegacyString().split(" ")[0], ClientMessage);
/* 20:   */     } else {
/* 21:22 */       Composer.add(item.extraData.getWallLegacyString(), ClientMessage);
/* 22:   */     }
/* 23:24 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 24:25 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 25:26 */     Composer.add(Integer.valueOf(item.owner.userId), ClientMessage);
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeWallItem
 * JD-Core Version:    0.7.0.1
 */