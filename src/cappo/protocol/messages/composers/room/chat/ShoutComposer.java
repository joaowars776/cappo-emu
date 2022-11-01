/*  1:   */ package cappo.protocol.messages.composers.room.chat;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import cappo.protocol.messages.composers.serializers.SerializeSay;
/*  6:   */ import java.util.List;
/*  7:   */ 
/*  8:   */ public class ShoutComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(int VirtualId, String Text, int Gesture, int styleId, List<String> Urls, int SayId)
/* 13:   */   {
/* 14:19 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:21 */     SerializeSay.parse(ClientMessage, VirtualId, Text, Gesture, styleId, Urls, SayId);
/* 17:22 */     Composer.endPacket(ClientMessage);
/* 18:23 */     return ClientMessage;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.chat.ShoutComposer
 * JD-Core Version:    0.7.0.1
 */