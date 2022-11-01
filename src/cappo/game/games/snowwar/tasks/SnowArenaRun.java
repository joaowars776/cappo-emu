/*  1:   */ package cappo.game.games.snowwar.tasks;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  7:   */ import cappo.game.games.snowwar.gameobjects.GameItemObject;
/*  8:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  9:   */ import cappo.protocol.messages.composers.games.snowwar.FullGameStatusComposer;
/* 10:   */ import cappo.protocol.messages.composers.games.snowwar.GameStatusComposer;
/* 11:   */ import cappo.protocol.messages.composers.games.snowwar.StageEndingComposer;
/* 12:   */ import io.netty.channel.Channel;
/* 13:   */ import java.util.ArrayList;
/* 14:   */ import java.util.Iterator;
/* 15:   */ import java.util.List;
/* 16:   */ import java.util.Map;
/* 17:   */ 
/* 18:   */ public class SnowArenaRun
/* 19:   */ {
/* 20:   */   public static void exec(SnowWarRoom room)
/* 21:   */   {
/* 22:26 */     if (room.players.isEmpty())
/* 23:   */     {
/* 24:27 */       room.STATUS = 0; return;
/* 25:   */     }
/* 26:   */     Channel socket;
/* 27:35 */     synchronized (room.gameEvents)
/* 28:   */     {
/* 29:36 */       synchronized (room.fullGameStatusQueue)
/* 30:   */       {
/* 31:37 */         List<Channel> filter = room.fullGameStatusQueue;
/* 32:38 */         room.fullGameStatusQueue = new ArrayList();
/* 33:   */       }
/* 34:   */       List<Channel> filter;
/* 35:43 */       room.checksum = 0;
/* 36:44 */       for (GameItemObject Object : room.gameObjects.values()) {
/* 37:45 */         Object.GenerateCHECKSUM(room, 1);
/* 38:   */       }
/* 39:49 */       for (??? = filter.iterator(); ???.hasNext();)
/* 40:   */       {
/* 41:49 */         socket = (Channel)???.next();
/* 42:50 */         QueueWriter.writeAndFlush(socket, FullGameStatusComposer.compose(room));
/* 43:   */       }
/* 44:53 */       MessageWriter status = GameStatusComposer.compose(room);
/* 45:54 */       room.gameEvents.clear();
/* 46:   */     }
/* 47:   */     MessageWriter status;
/* 48:   */     List<Channel> filter;
/* 49:57 */     for (HumanGameObject player : room.players.values()) {
/* 50:58 */       if ((player.currentSnowWar != null) && (
/* 51:59 */         (filter == null) || (filter.isEmpty()) || 
/* 52:60 */         (!filter.contains(player.cn.socket)))) {
/* 53:64 */         QueueWriter.writeAndFlush(player.cn.socket, status);
/* 54:   */       }
/* 55:   */     }
/* 56:69 */     room.subturn();
/* 57:70 */     room.subturn();
/* 58:71 */     room.subturn();
/* 59:73 */     if (++room.Turn >= 800)
/* 60:   */     {
/* 61:74 */       room.STATUS = 6;
/* 62:75 */       room.broadcast(StageEndingComposer.compose());
/* 63:   */     }
/* 64:   */   }
/* 65:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.tasks.SnowArenaRun
 * JD-Core Version:    0.7.0.1
 */