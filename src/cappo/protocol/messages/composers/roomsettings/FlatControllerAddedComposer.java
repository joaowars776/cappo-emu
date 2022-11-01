/*  1:   */ package cappo.protocol.messages.composers.roomsettings;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import cappo.protocol.messages.composers.serializers.SerializeFlatController;
/*  6:   */ 
/*  7:   */ public class FlatControllerAddedComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(int roomId, int clientId, String userName)
/* 12:   */   {
/* 13:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 14:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(roomId), ClientMessage);
/* 16:20 */     SerializeFlatController.parse(ClientMessage, clientId, userName);
/* 17:21 */     Composer.endPacket(ClientMessage);
/* 18:22 */     return ClientMessage;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.roomsettings.FlatControllerAddedComposer
 * JD-Core Version:    0.7.0.1
 */