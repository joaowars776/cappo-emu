/*  1:   */ package cappo.game.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.game.player.PlayerData;
/*  5:   */ import cappo.game.roomengine.entity.item.Item;
/*  6:   */ import java.util.Map;
/*  7:   */ import java.util.concurrent.ConcurrentHashMap;
/*  8:   */ 
/*  9:   */ public class TradeUser
/* 10:   */ {
/* 11:   */   public int userId;
/* 12:   */   public Connection connection;
/* 13:   */   public Map<Integer, Item> furnis;
/* 14:   */   public int status;
/* 15:   */   
/* 16:   */   public TradeUser(Connection user)
/* 17:   */   {
/* 18:22 */     this.userId = user.playerData.userId;
/* 19:23 */     this.connection = user;
/* 20:24 */     this.furnis = new ConcurrentHashMap();
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.inventory.trading.TradeUser
 * JD-Core Version:    0.7.0.1
 */