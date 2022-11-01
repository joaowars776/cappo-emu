/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.BaseItem;
/*  5:   */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*  6:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  7:   */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/*  8:   */ import cappo.protocol.messages.Composer;
/*  9:   */ 
/* 10:   */ public class WallItemComposer
/* 11:   */ {
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static final MessageWriter compose(GenericWallItem item)
/* 15:   */   {
/* 16:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 17:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 18:20 */     Composer.add(Integer.toString(item.itemId), ClientMessage);
/* 19:21 */     if (item.baseItem.interactorType == Interactor.InteractorType.postit) {
/* 20:22 */       Composer.add(item.extraData.getWallLegacyString().split(" ")[0], ClientMessage);
/* 21:   */     } else {
/* 22:24 */       Composer.add(item.extraData.getWallLegacyString(), ClientMessage);
/* 23:   */     }
/* 24:26 */     Composer.endPacket(ClientMessage);
/* 25:27 */     return ClientMessage;
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.WallItemComposer
 * JD-Core Version:    0.7.0.1
 */