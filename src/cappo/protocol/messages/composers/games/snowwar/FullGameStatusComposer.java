/*  1:   */ package cappo.protocol.messages.composers.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.SnowWar;
/*  5:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import cappo.protocol.messages.composers.serializers.SerializeGame2GameObjects;
/*  8:   */ import cappo.protocol.messages.composers.serializers.SerializeGameStatus;
/*  9:   */ import java.util.Map;
/* 10:   */ 
/* 11:   */ public class FullGameStatusComposer
/* 12:   */ {
/* 13:   */   public static int HEADER;
/* 14:   */   
/* 15:   */   public static final MessageWriter compose(SnowWarRoom arena)
/* 16:   */   {
/* 17:20 */     MessageWriter ClientMessage = new MessageWriter(1000 + arena.gameObjects.size() * 100 + arena.gameObjects.size() * 50);
/* 18:21 */     Composer.initPacket(HEADER, ClientMessage);
/* 19:22 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 20:23 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 21:24 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 22:25 */     SerializeGame2GameObjects.parse(ClientMessage, arena);
/* 23:26 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 24:27 */     Composer.add(Integer.valueOf(SnowWar.TEAMS.length), ClientMessage);
/* 25:28 */     SerializeGameStatus.parse(ClientMessage, arena, true);
/* 26:29 */     Composer.endPacket(ClientMessage);
/* 27:30 */     return ClientMessage;
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.snowwar.FullGameStatusComposer
 * JD-Core Version:    0.7.0.1
 */