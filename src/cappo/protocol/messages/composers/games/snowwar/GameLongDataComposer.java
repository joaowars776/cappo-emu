/*  1:   */ package cappo.protocol.messages.composers.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.RoomQueue;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializeGame2;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class GameLongDataComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(RoomQueue lobby)
/* 14:   */   {
/* 15:18 */     MessageWriter ClientMessage = new MessageWriter(100 + lobby.players.size() * 200);
/* 16:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */     SerializeGame2.parse(ClientMessage, lobby);
/* 18:21 */     Composer.endPacket(ClientMessage);
/* 19:22 */     return ClientMessage;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.snowwar.GameLongDataComposer
 * JD-Core Version:    0.7.0.1
 */