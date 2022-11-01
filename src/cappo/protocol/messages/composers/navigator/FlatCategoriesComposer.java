/*  1:   */ package cappo.protocol.messages.composers.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.navigator.NavigatorCategories;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import java.util.Map;
/*  7:   */ 
/*  8:   */ public class FlatCategoriesComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   private static MessageWriter ClientMessage;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose()
/* 14:   */   {
/* 15:18 */     if (ClientMessage == null)
/* 16:   */     {
/* 17:19 */       ClientMessage = new MessageWriter();
/* 18:20 */       Composer.initPacket(HEADER, ClientMessage);
/* 19:21 */       Composer.add(Integer.valueOf(NavigatorCategories.roomCategories.size()), ClientMessage);
/* 20:22 */       for (NavigatorCategories cat : NavigatorCategories.roomCategories.values())
/* 21:   */       {
/* 22:23 */         Composer.add(Integer.valueOf(cat.id), ClientMessage);
/* 23:24 */         Composer.add(cat.caption, ClientMessage);
/* 24:25 */         Composer.add(Boolean.valueOf(true), ClientMessage);
/* 25:   */       }
/* 26:27 */       Composer.endPacket(ClientMessage);
/* 27:   */     }
/* 28:29 */     return ClientMessage;
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.navigator.FlatCategoriesComposer
 * JD-Core Version:    0.7.0.1
 */