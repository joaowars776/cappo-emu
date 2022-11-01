/*  1:   */ package cappo.game.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import java.util.Map;
/*  7:   */ import java.util.concurrent.ConcurrentHashMap;
/*  8:   */ 
/*  9:   */ public class RoomQueue
/* 10:   */ {
/* 11:   */   public SnowWarRoom room;
/* 12:18 */   public final Map<Integer, Connection> players = new ConcurrentHashMap(10);
/* 13:   */   
/* 14:   */   public RoomQueue(SnowWarRoom snowRoom)
/* 15:   */   {
/* 16:21 */     this.room = snowRoom;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void broadcast(MessageWriter Message)
/* 20:   */   {
/* 21:25 */     for (Connection cn : this.players.values()) {
/* 22:26 */       QueueWriter.writeAndFlush(cn.socket, Message);
/* 23:   */     }
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.RoomQueue
 * JD-Core Version:    0.7.0.1
 */