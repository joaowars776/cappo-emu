/*  1:   */ package cappo.game.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.protocol.messages.composers.inventory.furni.FurniListUpdateComposer;
/*  8:   */ import java.util.Map;
/*  9:   */ import java.util.concurrent.ConcurrentHashMap;
/* 10:   */ 
/* 11:   */ public class Trade
/* 12:   */ {
/* 13:18 */   public static final Map<Integer, Trade> tradeMap = new ConcurrentHashMap();
/* 14:   */   public final TradeUser ownerUser;
/* 15:   */   public final TradeUser guestUser;
/* 16:   */   
/* 17:   */   public Trade(Connection owner, Connection guest)
/* 18:   */   {
/* 19:24 */     this.ownerUser = new TradeUser(owner);
/* 20:25 */     tradeMap.put(Integer.valueOf(this.ownerUser.userId), this);
/* 21:   */     
/* 22:27 */     this.guestUser = new TradeUser(guest);
/* 23:28 */     tradeMap.put(Integer.valueOf(this.guestUser.userId), this);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void clean()
/* 27:   */   {
/* 28:32 */     tradeMap.remove(Integer.valueOf(this.ownerUser.userId));
/* 29:33 */     tradeMap.remove(Integer.valueOf(this.guestUser.userId));
/* 30:35 */     if (this.ownerUser.connection.avatar.HaveStatus("trd")) {
/* 31:36 */       this.ownerUser.connection.avatar.setStatus("", "");
/* 32:   */     }
/* 33:39 */     if (this.guestUser.connection.avatar.HaveStatus("trd")) {
/* 34:40 */       this.guestUser.connection.avatar.setStatus("", "");
/* 35:   */     }
/* 36:44 */     if (!this.ownerUser.furnis.isEmpty()) {
/* 37:54 */       this.ownerUser.furnis.clear();
/* 38:   */     }
/* 39:57 */     if (!this.guestUser.furnis.isEmpty()) {
/* 40:67 */       this.guestUser.furnis.clear();
/* 41:   */     }
/* 42:70 */     broadcast(FurniListUpdateComposer.compose());
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void broadcast(MessageWriter packet)
/* 46:   */   {
/* 47:74 */     QueueWriter.writeAndFlush(this.ownerUser.connection.socket, packet);
/* 48:75 */     QueueWriter.writeAndFlush(this.guestUser.connection.socket, packet);
/* 49:   */   }
/* 50:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.inventory.trading.Trade
 * JD-Core Version:    0.7.0.1
 */