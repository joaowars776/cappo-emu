/*  1:   */ package cappo.protocol.messages.composers.landing;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.landing.LandingNews;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import java.util.List;
/*  7:   */ 
/*  8:   */ public class LandingNewsComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose()
/* 13:   */   {
/* 14:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:19 */     Composer.add(Integer.valueOf(LandingNews.news.size()), ClientMessage);
/* 17:20 */     for (LandingNews lNew : LandingNews.news)
/* 18:   */     {
/* 19:22 */       Composer.add(Integer.valueOf(lNew.id), ClientMessage);
/* 20:23 */       Composer.add(lNew.newTitle, ClientMessage);
/* 21:24 */       Composer.add(lNew.newText, ClientMessage);
/* 22:25 */       Composer.add(lNew.button, ClientMessage);
/* 23:26 */       Composer.add(Integer.valueOf(lNew.isClientAction ? 1 : 0), ClientMessage);
/* 24:27 */       Composer.add(lNew.getLink(), ClientMessage);
/* 25:28 */       Composer.add(lNew.newImage, ClientMessage);
/* 26:   */     }
/* 27:30 */     Composer.endPacket(ClientMessage);
/* 28:31 */     return ClientMessage;
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.landing.LandingNewsComposer
 * JD-Core Version:    0.7.0.1
 */