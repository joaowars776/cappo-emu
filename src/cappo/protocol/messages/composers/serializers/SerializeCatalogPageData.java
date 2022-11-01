/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class SerializeCatalogPageData
/*  7:   */ {
/*  8:   */   public static void parse(MessageWriter ClientMessage, String[][] PageData)
/*  9:   */   {
/* 10:14 */     for (String[] PagData : PageData)
/* 11:   */     {
/* 12:15 */       Composer.add(Integer.valueOf(PagData.length), ClientMessage);
/* 13:16 */       for (String Data : PagData) {
/* 14:17 */         Composer.add(Data, ClientMessage);
/* 15:   */       }
/* 16:   */     }
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeCatalogPageData
 * JD-Core Version:    0.7.0.1
 */