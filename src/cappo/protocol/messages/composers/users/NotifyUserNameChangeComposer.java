/*  1:   */ package cappo.protocol.messages.composers.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class NotifyUserNameChangeComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(int id, int virtualId, String name)
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(Integer.valueOf(id), ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(virtualId), ClientMessage);
/* 16:20 */     Composer.add(name, ClientMessage);
/* 17:21 */     Composer.endPacket(ClientMessage);
/* 18:22 */     return ClientMessage;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.users.NotifyUserNameChangeComposer
 * JD-Core Version:    0.7.0.1
 */