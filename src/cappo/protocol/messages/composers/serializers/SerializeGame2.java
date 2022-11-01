/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.games.snowwar.RoomQueue;
/*  6:   */ import cappo.game.games.snowwar.SnowWar;
/*  7:   */ import cappo.game.games.snowwar.SnowWarArenaBase;
/*  8:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  9:   */ import cappo.protocol.messages.Composer;
/* 10:   */ import java.util.Map;
/* 11:   */ 
/* 12:   */ public class SerializeGame2
/* 13:   */ {
/* 14:   */   public static void parse(MessageWriter ClientMessage, RoomQueue queue)
/* 15:   */   {
/* 16:17 */     Composer.add(Integer.valueOf(queue.room.roomId), ClientMessage);
/* 17:18 */     Composer.add(queue.room.Name, ClientMessage);
/* 18:19 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 19:20 */     Composer.add(Integer.valueOf(queue.room.ArenaType.ArenaType), ClientMessage);
/* 20:21 */     Composer.add(Integer.valueOf(SnowWar.TEAMS.length), ClientMessage);
/* 21:22 */     Composer.add(Integer.valueOf(10), ClientMessage);
/* 22:23 */     Composer.add(queue.room.Owner, ClientMessage);
/* 23:24 */     Composer.add(Integer.valueOf(14), ClientMessage);
/* 24:25 */     Composer.add(Integer.valueOf(queue.players.size()), ClientMessage);
/* 25:26 */     for (Connection cn : queue.players.values()) {
/* 26:27 */       SerializeGame2Player.parse(ClientMessage, cn);
/* 27:   */     }
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2
 * JD-Core Version:    0.7.0.1
 */