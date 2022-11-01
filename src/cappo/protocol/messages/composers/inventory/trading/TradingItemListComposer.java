/*  1:   */ package cappo.protocol.messages.composers.inventory.trading;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.BaseItem;
/*  5:   */ import cappo.game.inventory.trading.TradeUser;
/*  6:   */ import cappo.game.roomengine.entity.item.Item;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ import cappo.protocol.messages.composers.serializers.SerializeItemData;
/*  9:   */ import java.util.Map;
/* 10:   */ 
/* 11:   */ public class TradingItemListComposer
/* 12:   */ {
/* 13:   */   public static int HEADER;
/* 14:   */   
/* 15:   */   public static final MessageWriter compose(TradeUser owner, TradeUser guest)
/* 16:   */   {
/* 17:19 */     MessageWriter ClientMessage = new MessageWriter(500 + owner.furnis.size() * 200 + guest.furnis.size() * 200);
/* 18:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 19:21 */     Composer.add(Integer.valueOf(owner.userId), ClientMessage);
/* 20:22 */     Composer.add(Integer.valueOf(owner.furnis.size()), ClientMessage);
/* 21:23 */     for (Item furni : owner.furnis.values())
/* 22:   */     {
/* 23:24 */       Composer.add(Integer.valueOf(furni.itemId), ClientMessage);
/* 24:25 */       Composer.add(furni.baseItem.Type, ClientMessage);
/* 25:26 */       Composer.add(Integer.valueOf(furni.refId), ClientMessage);
/* 26:27 */       Composer.add(Integer.valueOf(furni.baseItem.SpriteId), ClientMessage);
/* 27:28 */       Composer.add(Integer.valueOf(furni.baseItem.itemCategory), ClientMessage);
/* 28:29 */       Composer.add(Boolean.valueOf(true), ClientMessage);
/* 29:   */       
/* 30:31 */       SerializeItemData.parse(ClientMessage, furni.baseItem, furni);
/* 31:   */       
/* 32:   */ 
/* 33:34 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 34:35 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 35:36 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 36:38 */       if (furni.baseItem.Type.equals("s")) {
/* 37:40 */         Composer.add(Integer.valueOf(0), ClientMessage);
/* 38:   */       }
/* 39:   */     }
/* 40:43 */     Composer.add(Integer.valueOf(guest.userId), ClientMessage);
/* 41:44 */     Composer.add(Integer.valueOf(guest.furnis.size()), ClientMessage);
/* 42:45 */     for (Item furni : guest.furnis.values())
/* 43:   */     {
/* 44:46 */       Composer.add(Integer.valueOf(furni.itemId), ClientMessage);
/* 45:47 */       Composer.add(furni.baseItem.Type, ClientMessage);
/* 46:48 */       Composer.add(Integer.valueOf(furni.refId), ClientMessage);
/* 47:49 */       Composer.add(Integer.valueOf(furni.baseItem.SpriteId), ClientMessage);
/* 48:50 */       Composer.add(Integer.valueOf(furni.baseItem.itemCategory), ClientMessage);
/* 49:51 */       Composer.add(Boolean.valueOf(true), ClientMessage);
/* 50:   */       
/* 51:53 */       SerializeItemData.parse(ClientMessage, furni.baseItem, furni);
/* 52:   */       
/* 53:   */ 
/* 54:56 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 55:57 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 56:58 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 57:60 */       if (furni.baseItem.Type.equals("s")) {
/* 58:62 */         Composer.add(Integer.valueOf(0), ClientMessage);
/* 59:   */       }
/* 60:   */     }
/* 61:65 */     Composer.endPacket(ClientMessage);
/* 62:66 */     return ClientMessage;
/* 63:   */   }
/* 64:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.trading.TradingItemListComposer
 * JD-Core Version:    0.7.0.1
 */