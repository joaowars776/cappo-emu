/*  1:   */ package cappo.protocol.messages.composers.userdefinedroomevents;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class WiredUpdateFailedComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(String Error)
/* 11:   */   {
/* 12:16 */     MessageWriter Message = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, Message);
/* 14:18 */     Composer.add(Error, Message);
/* 15:19 */     Composer.endPacket(Message);
/* 16:20 */     return Message;
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.userdefinedroomevents.WiredUpdateFailedComposer
 * JD-Core Version:    0.7.0.1
 */