/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.player.PlayerData;
/*  5:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  6:   */ import cappo.game.roomengine.entity.item.wall.WallItem;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ import cappo.protocol.messages.composers.serializers.SerializeWallItem;
/*  9:   */ import java.util.Collection;
/* 10:   */ import java.util.HashMap;
/* 11:   */ import java.util.Map;
/* 12:   */ 
/* 13:   */ public class ItemsComposer
/* 14:   */ {
/* 15:   */   public static int HEADER;
/* 16:   */   
/* 17:   */   public static final MessageWriter compose(Collection<GenericWallItem> WallItems)
/* 18:   */   {
/* 19:23 */     Map<Integer, String> owners = new HashMap();
/* 20:24 */     for (WallItem Item : WallItems) {
/* 21:25 */       if (!owners.containsKey(Integer.valueOf(Item.owner.userId))) {
/* 22:26 */         owners.put(Integer.valueOf(Item.owner.userId), Item.owner.userName);
/* 23:   */       }
/* 24:   */     }
/* 25:31 */     MessageWriter ClientMessage = new MessageWriter(500 + owners.size() * 24 + WallItems.size() * 70);
/* 26:32 */     Composer.initPacket(HEADER, ClientMessage);
/* 27:33 */     Composer.add(Integer.valueOf(owners.size()), ClientMessage);
/* 28:34 */     for (Integer id : owners.keySet())
/* 29:   */     {
/* 30:35 */       Composer.add(id, ClientMessage);
/* 31:36 */       Composer.add(owners.get(id), ClientMessage);
/* 32:   */     }
/* 33:38 */     Composer.add(Integer.valueOf(WallItems.size()), ClientMessage);
/* 34:39 */     for (GenericWallItem Item : WallItems) {
/* 35:40 */       SerializeWallItem.parse(ClientMessage, Item);
/* 36:   */     }
/* 37:42 */     Composer.endPacket(ClientMessage);
/* 38:   */     
/* 39:44 */     return ClientMessage;
/* 40:   */   }
/* 41:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.ItemsComposer
 * JD-Core Version:    0.7.0.1
 */