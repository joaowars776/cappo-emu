/*  1:   */ package cappo.protocol.messages.composers.recycler;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class RecyclerPrizesComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   private static MessageWriter ClientMessage;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose()
/* 12:   */   {
/* 13:17 */     if (ClientMessage == null)
/* 14:   */     {
/* 15:18 */       ClientMessage = new MessageWriter();
/* 16:   */       
/* 17:20 */       int[][] Category = { { 13, 13, 13, 13, 13 }, { 13, 13, 13, 13 }, { 13, 13, 13 }, { 13, 13 }, { 13 } };
/* 18:21 */       Composer.initPacket(HEADER, ClientMessage);
/* 19:22 */       Composer.add(Integer.valueOf(1), ClientMessage);
/* 20:   */       
/* 21:24 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 22:25 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 23:26 */       Composer.add(Integer.valueOf(Category.length), ClientMessage);
/* 24:27 */       int[][] arrayOfInt1 = Category;int j = Category.length;
/* 25:27 */       for (int i = 0; i < j; i++)
/* 26:   */       {
/* 27:27 */         int[] element = arrayOfInt1[i];
/* 28:   */         
/* 29:   */ 
/* 30:30 */         Composer.add("s", ClientMessage);
/* 31:31 */         Composer.add(Integer.valueOf(element.length), ClientMessage);
/* 32:32 */         for (int o = 0; o < element.length; o++)
/* 33:   */         {
/* 34:33 */           Composer.add("s", ClientMessage);
/* 35:34 */           Composer.add(Integer.valueOf(element[o]), ClientMessage);
/* 36:   */         }
/* 37:   */       }
/* 38:38 */       Composer.endPacket(ClientMessage);
/* 39:   */     }
/* 40:40 */     return ClientMessage;
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.recycler.RecyclerPrizesComposer
 * JD-Core Version:    0.7.0.1
 */