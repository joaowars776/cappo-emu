/*  1:   */ package cappo.protocol.messages.composers.inventory.furni;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  5:   */ import cappo.game.roomengine.entity.item.wall.WallItem;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ 
/*  8:   */ public class FurniListAddOrUpdateComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(FloorItem obj)
/* 13:   */   {
/* 14:18 */     MessageWriter message = new MessageWriter();
/* 15:19 */     Composer.initPacket(HEADER, message);
/* 16:20 */     FurniListComposer.writeObject(obj, message);
/* 17:21 */     Composer.endPacket(message);
/* 18:22 */     return message;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public static final MessageWriter compose(WallItem item)
/* 22:   */   {
/* 23:26 */     MessageWriter message = new MessageWriter(300);
/* 24:27 */     Composer.initPacket(HEADER, message);
/* 25:28 */     FurniListComposer.writeItem(item, message);
/* 26:29 */     Composer.endPacket(message);
/* 27:30 */     return message;
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.furni.FurniListAddOrUpdateComposer
 * JD-Core Version:    0.7.0.1
 */