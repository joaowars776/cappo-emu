/*  1:   */ package cappo.protocol.messages.composers.inventory.furni;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.entity.item.wall.WallItem;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class PostItPlacedComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(WallItem item)
/* 12:   */   {
/* 13:17 */     MessageWriter ClientMessage = new MessageWriter(50);
/* 14:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(item.itemId), ClientMessage);
/* 16:20 */     Composer.add(item.roomDataString(), ClientMessage);
/* 17:21 */     Composer.endPacket(ClientMessage);
/* 18:22 */     return ClientMessage;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.furni.PostItPlacedComposer
 * JD-Core Version:    0.7.0.1
 */