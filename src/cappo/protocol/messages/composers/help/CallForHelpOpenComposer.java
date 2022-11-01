/*  1:   */ package cappo.protocol.messages.composers.help;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class CallForHelpOpenComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   private static MessageWriter ClientMessage;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose()
/* 12:   */   {
/* 13:17 */     if (ClientMessage == null)
/* 14:   */     {
/* 15:18 */       ClientMessage = new MessageWriter(20);
/* 16:19 */       Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */       Composer.endPacket(ClientMessage);
/* 18:   */     }
/* 19:22 */     return ClientMessage;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.help.CallForHelpOpenComposer
 * JD-Core Version:    0.7.0.1
 */