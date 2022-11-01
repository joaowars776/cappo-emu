/*  1:   */ package cappo.protocol.messages.composers.avatar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import java.util.List;
/*  6:   */ 
/*  7:   */ public class ResultCheckUserNameComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(int result, String name, List<String> nicksavailable)
/* 12:   */   {
/* 13:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 14:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 15:20 */     Composer.add(Integer.valueOf(result), ClientMessage);
/* 16:21 */     Composer.add(name, ClientMessage);
/* 17:22 */     Composer.add(Integer.valueOf(nicksavailable.size()), ClientMessage);
/* 18:23 */     for (String nick : nicksavailable) {
/* 19:24 */       Composer.add(nick, ClientMessage);
/* 20:   */     }
/* 21:26 */     Composer.endPacket(ClientMessage);
/* 22:27 */     return ClientMessage;
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.avatar.ResultCheckUserNameComposer
 * JD-Core Version:    0.7.0.1
 */