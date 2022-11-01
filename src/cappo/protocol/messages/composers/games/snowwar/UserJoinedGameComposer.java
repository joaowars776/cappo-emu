/*  1:   */ package cappo.protocol.messages.composers.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializeGame2Player;
/*  7:   */ 
/*  8:   */ public class UserJoinedGameComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(Connection cn)
/* 13:   */   {
/* 14:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:20 */     SerializeGame2Player.parse(ClientMessage, cn);
/* 17:21 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 18:22 */     Composer.endPacket(ClientMessage);
/* 19:23 */     return ClientMessage;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.snowwar.UserJoinedGameComposer
 * JD-Core Version:    0.7.0.1
 */