/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.player.PlayerData;
/*  5:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import cappo.protocol.messages.composers.serializers.SerializeFloorItem;
/*  8:   */ import java.util.Collection;
/*  9:   */ import java.util.HashMap;
/* 10:   */ import java.util.Map;
/* 11:   */ 
/* 12:   */ public class ObjectsComposer
/* 13:   */ {
/* 14:   */   public static int HEADER;
/* 15:   */   
/* 16:   */   public static final MessageWriter compose(Collection<FloorItem> FloorItems)
/* 17:   */   {
/* 18:23 */     Map<Integer, String> owners = new HashMap();
/* 19:24 */     for (FloorItem Item : FloorItems) {
/* 20:25 */       if (!owners.containsKey(Integer.valueOf(Item.owner.userId))) {
/* 21:26 */         owners.put(Integer.valueOf(Item.owner.userId), Item.owner.userName);
/* 22:   */       }
/* 23:   */     }
/* 24:30 */     MessageWriter writer = new MessageWriter(500 + owners.size() * 50 + FloorItems.size() * 500);
/* 25:31 */     Composer.initPacket(HEADER, writer);
/* 26:32 */     Composer.add(Integer.valueOf(owners.size()), writer);
/* 27:33 */     for (Integer id : owners.keySet())
/* 28:   */     {
/* 29:34 */       Composer.add(id, writer);
/* 30:35 */       Composer.add(owners.get(id), writer);
/* 31:   */     }
/* 32:37 */     Composer.add(Integer.valueOf(FloorItems.size()), writer);
/* 33:38 */     for (FloorItem Item : FloorItems) {
/* 34:39 */       SerializeFloorItem.parse(writer, Item);
/* 35:   */     }
/* 36:41 */     Composer.endPacket(writer);
/* 37:   */     
/* 38:43 */     return writer;
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.ObjectsComposer
 * JD-Core Version:    0.7.0.1
 */