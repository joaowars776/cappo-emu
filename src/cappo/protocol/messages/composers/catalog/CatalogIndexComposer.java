/*  1:   */ package cappo.protocol.messages.composers.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.catalog.Catalog;
/*  5:   */ import cappo.game.catalog.Catalog.CatalogPage;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import java.util.List;
/*  8:   */ import java.util.Map;
/*  9:   */ 
/* 10:   */ public class CatalogIndexComposer
/* 11:   */ {
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static final MessageWriter compose(int rank, String catalogType)
/* 15:   */   {
/* 16:20 */     MessageWriter message = new MessageWriter(100 + Catalog.pages.size() * 100);
/* 17:21 */     Composer.initPacket(HEADER, message);
/* 18:22 */     dumpCatalogTab(rank, (List)Catalog.catalogMap.get(Integer.valueOf(0)), message);
/* 19:23 */     Composer.add(Boolean.valueOf(false), message);
/* 20:24 */     Composer.add(catalogType, message);
/* 21:25 */     Composer.endPacket(message);
/* 22:26 */     return message;
/* 23:   */   }
/* 24:   */   
/* 25:   */   private static int dumpCatalogTab(int rank, List<Catalog.CatalogPage> pageList, MessageWriter message)
/* 26:   */   {
/* 27:30 */     int size = 0;
/* 28:31 */     for (Catalog.CatalogPage page : pageList) {
/* 29:32 */       if ((page.minRank <= rank) && (page.isVisible))
/* 30:   */       {
/* 31:36 */         size++;
/* 32:   */         
/* 33:38 */         Composer.add(Boolean.valueOf(page.isVisible), message);
/* 34:   */         
/* 35:40 */         Composer.add(Integer.valueOf(page.IconImage), message);
/* 36:41 */         Composer.add(Integer.valueOf(page.pageId), message);
/* 37:42 */         Composer.add(page.pageName, message);
/* 38:43 */         Composer.add(page.caption, message);
/* 39:44 */         Composer.add(Integer.valueOf(0), message);
/* 40:49 */         if (Catalog.catalogMap.containsKey(Integer.valueOf(page.pageId)))
/* 41:   */         {
/* 42:50 */           Composer.add(message.setSaved(Integer.valueOf(0)), message);
/* 43:51 */           message.writeSaved(Integer.valueOf(dumpCatalogTab(rank, (List)Catalog.catalogMap.get(Integer.valueOf(page.pageId)), message)));
/* 44:   */         }
/* 45:   */         else
/* 46:   */         {
/* 47:53 */           Composer.add(Integer.valueOf(0), message);
/* 48:   */         }
/* 49:   */       }
/* 50:   */     }
/* 51:56 */     return size;
/* 52:   */   }
/* 53:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.catalog.CatalogIndexComposer
 * JD-Core Version:    0.7.0.1
 */