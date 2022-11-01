/*  1:   */ package cappo.protocol.messages.composers.notifications;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class HabboBroadcastComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(String Text)
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(Text, ClientMessage);
/* 15:19 */     Composer.endPacket(ClientMessage);
/* 16:20 */     return ClientMessage;
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.notifications.HabboBroadcastComposer
 * JD-Core Version:    0.7.0.1
 */