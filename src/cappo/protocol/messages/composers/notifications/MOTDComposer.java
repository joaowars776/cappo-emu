/*  1:   */ package cappo.protocol.messages.composers.notifications;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class MOTDComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(String[] Lines)
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(Integer.valueOf(Lines.length), ClientMessage);
/* 15:19 */     String[] arrayOfString = Lines;int j = Lines.length;
/* 16:19 */     for (int i = 0; i < j; i++)
/* 17:   */     {
/* 18:19 */       String line = arrayOfString[i];
/* 19:20 */       Composer.add(line, ClientMessage);
/* 20:   */     }
/* 21:22 */     Composer.endPacket(ClientMessage);
/* 22:23 */     return ClientMessage;
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.notifications.MOTDComposer
 * JD-Core Version:    0.7.0.1
 */