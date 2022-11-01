/*  1:   */ package cappo.protocol.messages.composers.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*  5:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ 
/*  8:   */ public class RequestSpamWallPostItComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(GenericWallItem item)
/* 13:   */   {
/* 14:17 */     String data = item.extraData.getWallLegacyString();
/* 15:18 */     MessageWriter ClientMessage = new MessageWriter(25 + data.length());
/* 16:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */     Composer.add(Integer.toString(item.itemId), ClientMessage);
/* 18:21 */     Composer.add(data, ClientMessage);
/* 19:22 */     Composer.endPacket(ClientMessage);
/* 20:23 */     return ClientMessage;
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.furniture.RequestSpamWallPostItComposer
 * JD-Core Version:    0.7.0.1
 */