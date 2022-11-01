/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import java.util.List;
/*  6:   */ 
/*  7:   */ public class SerializeSay
/*  8:   */ {
/*  9:   */   public static void parse(MessageWriter ClientMessage, int virtualId, String message, int gesture, int styleId, List<String> urls, int sayId)
/* 10:   */   {
/* 11:16 */     Composer.add(Integer.valueOf(virtualId), ClientMessage);
/* 12:17 */     Composer.add(message, ClientMessage);
/* 13:18 */     Composer.add(Integer.valueOf(gesture), ClientMessage);
/* 14:19 */     Composer.add(Integer.valueOf(styleId), ClientMessage);
/* 15:20 */     if (urls == null)
/* 16:   */     {
/* 17:21 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 18:   */     }
/* 19:   */     else
/* 20:   */     {
/* 21:23 */       Composer.add(Integer.valueOf(urls.size()), ClientMessage);
/* 22:24 */       for (String Link : urls)
/* 23:   */       {
/* 24:25 */         Composer.add("/link_to?url=" + Link, ClientMessage);
/* 25:26 */         Composer.add(Link, ClientMessage);
/* 26:27 */         Composer.add(Boolean.valueOf(true), ClientMessage);
/* 27:   */       }
/* 28:   */     }
/* 29:30 */     Composer.add(Integer.valueOf(sayId), ClientMessage);
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeSay
 * JD-Core Version:    0.7.0.1
 */