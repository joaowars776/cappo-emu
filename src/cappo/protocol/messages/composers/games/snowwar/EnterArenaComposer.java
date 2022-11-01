/*  1:   */ package cappo.protocol.messages.composers.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.SnowWar;
/*  5:   */ import cappo.game.games.snowwar.SnowWarArenaBase;
/*  6:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  7:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  8:   */ import cappo.protocol.messages.Composer;
/*  9:   */ import cappo.protocol.messages.composers.serializers.SerializeArena;
/* 10:   */ import cappo.protocol.messages.composers.serializers.SerializeGame2PlayerData;
/* 11:   */ import java.util.List;
/* 12:   */ import java.util.Map;
/* 13:   */ 
/* 14:   */ public class EnterArenaComposer
/* 15:   */ {
/* 16:   */   public static int HEADER;
/* 17:   */   
/* 18:   */   public static final MessageWriter compose(SnowWarRoom arena)
/* 19:   */   {
/* 20:21 */     MessageWriter ClientMessage = new MessageWriter(5000 + arena.ArenaType.fuseObjects.size() * 100);
/* 21:22 */     Composer.initPacket(HEADER, ClientMessage);
/* 22:23 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 23:24 */     Composer.add(Integer.valueOf(arena.ArenaType.ArenaType), ClientMessage);
/* 24:25 */     Composer.add(Integer.valueOf(SnowWar.TEAMS.length), ClientMessage);
/* 25:26 */     Composer.add(Integer.valueOf(arena.players.size()), ClientMessage);
/* 26:27 */     for (HumanGameObject Player : arena.players.values()) {
/* 27:28 */       SerializeGame2PlayerData.parse(ClientMessage, Player);
/* 28:   */     }
/* 29:30 */     SerializeArena.parse(ClientMessage, arena);
/* 30:31 */     Composer.endPacket(ClientMessage);
/* 31:32 */     return ClientMessage;
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.snowwar.EnterArenaComposer
 * JD-Core Version:    0.7.0.1
 */