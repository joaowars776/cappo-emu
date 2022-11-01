/*  1:   */ package cappo.protocol.messages.composers.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.player.PlayerData;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class UserTagsComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(PlayerData Client)
/* 12:   */   {
/* 13:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 14:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(Client.userId), ClientMessage);
/* 16:20 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 17:   */     
/* 18:   */ 
/* 19:   */ 
/* 20:   */ 
/* 21:25 */     Composer.endPacket(ClientMessage);
/* 22:26 */     return ClientMessage;
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.users.UserTagsComposer
 * JD-Core Version:    0.7.0.1
 */