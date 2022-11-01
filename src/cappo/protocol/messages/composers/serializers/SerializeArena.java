/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.GamefuseObject;
/*  5:   */ import cappo.game.games.snowwar.SnowWarArenaBase;
/*  6:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ import java.util.List;
/*  9:   */ 
/* 10:   */ public class SerializeArena
/* 11:   */ {
/* 12:   */   public static void parse(MessageWriter ClientMessage, SnowWarRoom arena)
/* 13:   */   {
/* 14:16 */     Composer.add(Integer.valueOf(arena.ArenaType.ArenaWidth), ClientMessage);
/* 15:17 */     Composer.add(Integer.valueOf(arena.ArenaType.ArenaHeight), ClientMessage);
/* 16:18 */     Composer.add(arena.ArenaType.HeightMap, ClientMessage);
/* 17:19 */     Composer.add(Integer.valueOf(arena.ArenaType.fuseObjects.size()), ClientMessage);
/* 18:20 */     for (GamefuseObject fuseItem : arena.ArenaType.fuseObjects) {
/* 19:21 */       SerializeFuseObject.parse(ClientMessage, fuseItem);
/* 20:   */     }
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeArena
 * JD-Core Version:    0.7.0.1
 */