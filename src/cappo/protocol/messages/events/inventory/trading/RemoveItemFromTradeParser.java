/*  1:   */ package cappo.protocol.messages.events.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.inventory.trading.Trade;
/*  6:   */ import cappo.game.inventory.trading.TradeUser;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.roomengine.entity.item.Item;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ import cappo.protocol.messages.composers.inventory.trading.TradingItemListComposer;
/* 12:   */ import java.util.Map;
/* 13:   */ 
/* 14:   */ public class RemoveItemFromTradeParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:20 */     Avatar avatar = Main.avatar;
/* 20:21 */     if (avatar == null) {
/* 21:22 */       return;
/* 22:   */     }
/* 23:25 */     Trade trade = (Trade)Trade.tradeMap.get(Integer.valueOf(Main.playerData.userId));
/* 24:26 */     if (trade == null) {
/* 25:27 */       return;
/* 26:   */     }
/* 27:30 */     TradeUser user = trade.guestUser;
/* 28:31 */     if (trade.ownerUser.userId == Main.playerData.userId) {
/* 29:32 */       user = trade.ownerUser;
/* 30:   */     }
/* 31:35 */     Item item = (Item)user.furnis.remove(Integer.valueOf(Main.currentPacket.readInt()));
/* 32:36 */     if (item == null) {
/* 33:37 */       return;
/* 34:   */     }
/* 35:46 */     trade.broadcast(TradingItemListComposer.compose(trade.ownerUser, trade.guestUser));
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.trading.RemoveItemFromTradeParser
 * JD-Core Version:    0.7.0.1
 */