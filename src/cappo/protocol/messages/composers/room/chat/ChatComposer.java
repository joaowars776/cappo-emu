/*  1:   */ package cappo.protocol.messages.composers.room.chat;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import cappo.protocol.messages.composers.serializers.SerializeSay;
/*  6:   */ import java.util.List;
/*  7:   */ 
/*  8:   */ public class ChatComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(int virtualId, String text, int gesture, int styleId, List<String> urls, int sayId)
/* 13:   */   {
/* 14:19 */     MessageWriter clientMessage = new MessageWriter();
/* 15:20 */     Composer.initPacket(HEADER, clientMessage);
/* 16:21 */     SerializeSay.parse(clientMessage, virtualId, text, gesture, styleId, urls, sayId);
/* 17:22 */     Composer.endPacket(clientMessage);
/* 18:23 */     return clientMessage;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.chat.ChatComposer
 * JD-Core Version:    0.7.0.1
 */