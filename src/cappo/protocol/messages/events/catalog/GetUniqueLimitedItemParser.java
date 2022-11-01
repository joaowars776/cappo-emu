/*  1:   */ package cappo.protocol.messages.events.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.catalog.Catalog;
/*  7:   */ import cappo.game.catalog.Catalog.CatalogProduct;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.catalog.UniqueLimitedItemComposer;
/* 10:   */ import cappo.protocol.messages.composers.catalog.UniqueLimitedItemSoldOutComposer;
/* 11:   */ import java.util.Map;
/* 12:   */ 
/* 13:   */ public class GetUniqueLimitedItemParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection Main)
/* 17:   */   {
/* 18:20 */     Catalog.CatalogProduct item = (Catalog.CatalogProduct)Catalog.Items.get(Integer.valueOf(Main.currentPacket.readInt()));
/* 19:21 */     if (item == null) {
/* 20:22 */       return;
/* 21:   */     }
/* 22:25 */     QueueWriter.write(Main.socket, UniqueLimitedItemComposer.compose(item));
/* 23:26 */     if (item.uniqueLimitedItemsLeft.intValue() < 1) {
/* 24:27 */       QueueWriter.write(Main.socket, UniqueLimitedItemSoldOutComposer.compose());
/* 25:   */     }
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.catalog.GetUniqueLimitedItemParser
 * JD-Core Version:    0.7.0.1
 */