/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class SerializeFlatController
/*  7:   */ {
/*  8:   */   public static void parse(MessageWriter ClientMessage, int userId, String userName)
/*  9:   */   {
/* 10:14 */     Composer.add(Integer.valueOf(userId), ClientMessage);
/* 11:15 */     Composer.add(userName, ClientMessage);
/* 12:   */   }
/* 13:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeFlatController
 * JD-Core Version:    0.7.0.1
 */