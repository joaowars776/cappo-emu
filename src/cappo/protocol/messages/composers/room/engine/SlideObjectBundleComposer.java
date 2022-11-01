/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.rollers.RollerMoveDataEntity;
/*  5:   */ import cappo.game.rollers.RollerMoveDataObject;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  7:   */ import cappo.game.roomengine.entity.live.LiveEntity;
/*  8:   */ import cappo.protocol.messages.Composer;
/*  9:   */ import java.util.List;
/* 10:   */ 
/* 11:   */ public class SlideObjectBundleComposer
/* 12:   */ {
/* 13:   */   public static final int MOVETYPE_NONE = 0;
/* 14:   */   public static final int MOVETYPE_MV = 1;
/* 15:   */   public static final int MOVETYPE_STD = 2;
/* 16:   */   public static int HEADER;
/* 17:   */   
/* 18:   */   public static final MessageWriter compose(FloorItem roller, int nextX, int nextY, List<RollerMoveDataObject> stackedItems, RollerMoveDataEntity moveDataEntity)
/* 19:   */   {
/* 20:25 */     MessageWriter ClientMessage = new MessageWriter(500 + stackedItems.size() * 40);
/* 21:26 */     Composer.initPacket(HEADER, ClientMessage);
/* 22:27 */     Composer.add(Integer.valueOf(roller.getX()), ClientMessage);
/* 23:28 */     Composer.add(Integer.valueOf(roller.getY()), ClientMessage);
/* 24:29 */     Composer.add(Integer.valueOf(nextX), ClientMessage);
/* 25:30 */     Composer.add(Integer.valueOf(nextY), ClientMessage);
/* 26:31 */     Composer.add(Integer.valueOf(stackedItems.size()), ClientMessage);
/* 27:32 */     for (RollerMoveDataObject stacked : stackedItems)
/* 28:   */     {
/* 29:33 */       Composer.add(Integer.valueOf(stacked.item.itemId), ClientMessage);
/* 30:34 */       Composer.add(Float.toString(stacked.fromZ).replace(',', '.'), ClientMessage);
/* 31:35 */       Composer.add(Float.toString(stacked.item.getZ()).replace(',', '.'), ClientMessage);
/* 32:   */     }
/* 33:37 */     Composer.add(Integer.valueOf(roller.itemId), ClientMessage);
/* 34:38 */     if (moveDataEntity != null)
/* 35:   */     {
/* 36:39 */       Composer.add(Integer.valueOf(moveDataEntity.entityMoveType), ClientMessage);
/* 37:40 */       if (moveDataEntity.entityMoveType != 0)
/* 38:   */       {
/* 39:41 */         Composer.add(Short.valueOf(moveDataEntity.entity.virtualId), ClientMessage);
/* 40:42 */         Composer.add(Float.toString(moveDataEntity.fromZ).replace(',', '.'), ClientMessage);
/* 41:43 */         Composer.add(Float.toString(moveDataEntity.entity.z).replace(',', '.'), ClientMessage);
/* 42:   */       }
/* 43:   */     }
/* 44:46 */     Composer.endPacket(ClientMessage);
/* 45:47 */     return ClientMessage;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public static final MessageWriter compose(int fromX, int fromY, RollerMoveDataObject stacked)
/* 49:   */   {
/* 50:51 */     MessageWriter ClientMessage = new MessageWriter(500);
/* 51:52 */     Composer.initPacket(HEADER, ClientMessage);
/* 52:53 */     Composer.add(Integer.valueOf(fromX), ClientMessage);
/* 53:54 */     Composer.add(Integer.valueOf(fromY), ClientMessage);
/* 54:55 */     Composer.add(Integer.valueOf(stacked.item.getX()), ClientMessage);
/* 55:56 */     Composer.add(Integer.valueOf(stacked.item.getY()), ClientMessage);
/* 56:57 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 57:   */     
/* 58:59 */     Composer.add(Integer.valueOf(stacked.item.itemId), ClientMessage);
/* 59:60 */     Composer.add(Float.toString(stacked.fromZ).replace(',', '.'), ClientMessage);
/* 60:61 */     Composer.add(Float.toString(stacked.item.getZ()).replace(',', '.'), ClientMessage);
/* 61:   */     
/* 62:63 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 63:64 */     Composer.endPacket(ClientMessage);
/* 64:65 */     return ClientMessage;
/* 65:   */   }
/* 66:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.SlideObjectBundleComposer
 * JD-Core Version:    0.7.0.1
 */