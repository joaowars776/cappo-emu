/*  1:   */ package cappo.protocol.messages.composers.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.navigator.officialrooms.Official;
/*  5:   */ import cappo.game.navigator.officialrooms.OfficialRooms;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import java.util.List;
/*  8:   */ import java.util.Map;
/*  9:   */ 
/* 10:   */ public class OfficialRoomsComposer
/* 11:   */ {
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static final MessageWriter compose()
/* 15:   */     throws Exception
/* 16:   */   {
/* 17:20 */     MessageWriter message = new MessageWriter(1000 + OfficialRooms.SIZE * 500);
/* 18:21 */     Composer.initPacket(HEADER, message);
/* 19:22 */     Composer.add(message.setSaved(Integer.valueOf(0)), message);
/* 20:23 */     message.writeSaved(Integer.valueOf(dumpTab((List)OfficialRooms.items.get(Integer.valueOf(0)), message)));
/* 21:24 */     Composer.add(Integer.valueOf(0), message);
/* 22:25 */     Composer.add(Integer.valueOf(0), message);
/* 23:26 */     Composer.endPacket(message);
/* 24:27 */     return message;
/* 25:   */   }
/* 26:   */   
/* 27:   */   private static int dumpTab(List<Official> items, MessageWriter message)
/* 28:   */     throws Exception
/* 29:   */   {
/* 30:31 */     int size = 0;
/* 31:32 */     if (items != null) {
/* 32:33 */       for (Official official : items)
/* 33:   */       {
/* 34:34 */         Composer.add(Integer.valueOf(official.id), message);
/* 35:35 */         Composer.add(official.caption, message);
/* 36:36 */         Composer.add(official.desc, message);
/* 37:37 */         Composer.add(Integer.valueOf(official.showDetails ? 1 : 0), message);
/* 38:38 */         Composer.add(official.caption, message);
/* 39:39 */         Composer.add(official.image, message);
/* 40:40 */         Composer.add(Integer.valueOf(official.parentId), message);
/* 41:41 */         official.compose(message);
/* 42:43 */         if (OfficialRooms.items.containsKey(Integer.valueOf(official.id))) {
/* 43:44 */           size += dumpTab((List)OfficialRooms.items.get(Integer.valueOf(official.id)), message);
/* 44:   */         }
/* 45:47 */         size++;
/* 46:   */       }
/* 47:   */     }
/* 48:50 */     return size;
/* 49:   */   }
/* 50:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.navigator.OfficialRoomsComposer
 * JD-Core Version:    0.7.0.1
 */