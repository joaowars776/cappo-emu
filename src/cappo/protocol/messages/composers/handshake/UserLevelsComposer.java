/*  1:   */ package cappo.protocol.messages.composers.handshake;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class UserLevelsComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(int ClubLevel, int RankLevel)
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter(14);
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(Integer.valueOf(ClubLevel), ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(RankLevel), ClientMessage);
/* 16:20 */     Composer.endPacket(ClientMessage);
/* 17:21 */     return ClientMessage;
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.handshake.UserLevelsComposer
 * JD-Core Version:    0.7.0.1
 */