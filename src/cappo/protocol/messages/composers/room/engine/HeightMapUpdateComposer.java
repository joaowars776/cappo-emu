/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.roomengine.SquareFlagManager;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ import java.util.ArrayList;
/*  9:   */ import java.util.Collections;
/* 10:   */ import java.util.HashMap;
/* 11:   */ import java.util.Iterator;
/* 12:   */ import java.util.List;
/* 13:   */ import java.util.Map;
/* 14:   */ 
/* 15:   */ public class HeightMapUpdateComposer
/* 16:   */ {
/* 17:   */   public static int HEADER;
/* 18:   */   private static final int MIN_CHAR = 32;
/* 19:   */   private static final int MAX_CHAR = 127;
/* 20:   */   private static final int MAX_NUM = 95;
/* 21:   */   
/* 22:   */   public static final MessageWriter compose(RoomTask room, List<FloorItem> items)
/* 23:   */   {
/* 24:29 */     Map<Integer, FloorItem> points = new HashMap();
/* 25:30 */     for (FloorItem item : items) {
/* 26:31 */       points.put(Integer.valueOf(item.getXy()), item);
/* 27:   */     }
/* 28:34 */     int currentPos = 0;
/* 29:   */     
/* 30:36 */     String data = "";
/* 31:37 */     List<Integer> orderedItems = new ArrayList(points.keySet());
/* 32:38 */     Collections.sort(orderedItems);
/* 33:39 */     for (Iterator localIterator2 = orderedItems.iterator(); localIterator2.hasNext();)
/* 34:   */     {
/* 35:39 */       int key = ((Integer)localIterator2.next()).intValue();
/* 36:40 */       FloorItem item = (FloorItem)points.get(Integer.valueOf(key));
/* 37:   */       
/* 38:42 */       key -= currentPos;
/* 39:43 */       currentPos += key;
/* 40:   */       do
/* 41:   */       {
/* 42:46 */         if (key > 95)
/* 43:   */         {
/* 44:47 */           data = data + "!";
/* 45:48 */           key -= 95;
/* 46:   */         }
/* 47:   */         else
/* 48:   */         {
/* 49:50 */           data = data + "!" + (char)(key + 32);
/* 50:   */         }
/* 51:45 */       } while (
/* 52:   */       
/* 53:   */ 
/* 54:   */ 
/* 55:   */ 
/* 56:   */ 
/* 57:   */ 
/* 58:   */ 
/* 59:53 */         key > 0);
/* 60:55 */       float floorHeight = ((Float)room.squareAbsoluteHeight.get(Integer.valueOf(item.getXy()))).floatValue();
/* 61:56 */       if (!room.squareFlag.have(item.getXy(), 4)) {
/* 62:57 */         floorHeight += 10.0F;
/* 63:   */       }
/* 64:59 */       data = data + (char)(int)floorHeight;
/* 65:   */     }
/* 66:62 */     MessageWriter ClientMessage = new MessageWriter();
/* 67:63 */     Composer.initPacket(HEADER, ClientMessage);
/* 68:64 */     Composer.add(data, ClientMessage);
/* 69:65 */     Composer.endPacket(ClientMessage);
/* 70:66 */     return ClientMessage;
/* 71:   */   }
/* 72:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.HeightMapUpdateComposer
 * JD-Core Version:    0.7.0.1
 */