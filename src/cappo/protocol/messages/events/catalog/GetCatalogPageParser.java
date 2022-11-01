/*  1:   */ package cappo.protocol.messages.events.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.MessageWriter;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.catalog.Catalog;
/*  8:   */ import cappo.game.catalog.Catalog.CatalogPage;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.catalog.CatalogPageComposer;
/* 11:   */ import java.util.Map;
/* 12:   */ 
/* 13:   */ public class GetCatalogPageParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection cn)
/* 17:   */   {
/* 18:20 */     Catalog.CatalogPage page = (Catalog.CatalogPage)Catalog.pages.get(Integer.valueOf(cn.currentPacket.readInt()));
/* 19:21 */     if ((Catalog.isBlocked) || (page == null) || (!page.isEnabled)) {
/* 20:22 */       return;
/* 21:   */     }
/* 22:25 */     int offerId = cn.currentPacket.readInt();
/* 23:26 */     String catalogType = cn.currentPacket.readString();
/* 24:28 */     if ((page.isCacheDisabled) || (offerId != -1) || (!catalogType.equals("NORMAL")))
/* 25:   */     {
/* 26:29 */       QueueWriter.write(cn.socket, CatalogPageComposer.compose(page, offerId, catalogType));
/* 27:30 */       return;
/* 28:   */     }
/* 29:33 */     MessageWriter response = (MessageWriter)Catalog.pageMap.get(Integer.valueOf(page.pageId));
/* 30:34 */     if (response == null)
/* 31:   */     {
/* 32:35 */       response = CatalogPageComposer.compose(page, -1, "NORMAL");
/* 33:36 */       Catalog.pageMap.put(Integer.valueOf(page.pageId), response);
/* 34:   */     }
/* 35:39 */     QueueWriter.write(cn.socket, response);
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.catalog.GetCatalogPageParser
 * JD-Core Version:    0.7.0.1
 */