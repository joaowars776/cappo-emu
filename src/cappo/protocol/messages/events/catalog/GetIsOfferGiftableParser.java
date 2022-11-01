/*  1:   */ package cappo.protocol.messages.events.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.catalog.Catalog;
/*  7:   */ import cappo.game.catalog.Catalog.CatalogProduct;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.catalog.ItemAllowGiftComposer;
/* 10:   */ import java.util.Map;
/* 11:   */ 
/* 12:   */ public class GetIsOfferGiftableParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:19 */     Catalog.CatalogProduct item = (Catalog.CatalogProduct)Catalog.Items.get(Integer.valueOf(Main.currentPacket.readInt()));
/* 18:20 */     if (item == null) {
/* 19:21 */       return;
/* 20:   */     }
/* 21:24 */     QueueWriter.write(Main.socket, ItemAllowGiftComposer.compose(item.productId, Boolean.valueOf(item.AllowGift)));
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.catalog.GetIsOfferGiftableParser
 * JD-Core Version:    0.7.0.1
 */