/*  1:   */ package cappo.protocol.messages.composers.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializeGame2GameObjects;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class StageStartingComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(SnowWarRoom arena)
/* 14:   */   {
/* 15:18 */     MessageWriter ClientMessage = new MessageWriter(1000 + arena.gameObjects.size() * 100);
/* 16:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 18:21 */     Composer.add("snowwar_arena_0", ClientMessage);
/* 19:22 */     Composer.add(Integer.valueOf(5), ClientMessage);
/* 20:23 */     SerializeGame2GameObjects.parse(ClientMessage, arena);
/* 21:24 */     Composer.endPacket(ClientMessage);
/* 22:25 */     return ClientMessage;
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.snowwar.StageStartingComposer
 * JD-Core Version:    0.7.0.1
 */