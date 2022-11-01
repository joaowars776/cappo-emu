/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.roomengine.RoomData;
/*  6:   */ import cappo.game.roomengine.RoomIcon;
/*  7:   */ import cappo.game.roomengine.settings.TradingSettings;
/*  8:   */ import cappo.protocol.messages.Composer;
/*  9:   */ 
/* 10:   */ public class SerializeRoom
/* 11:   */ {
/* 12:   */   public static void parse(MessageWriter ClientMessage, RoomData room)
/* 13:   */   {
/* 14:16 */     Composer.add(Integer.valueOf(room.roomId), ClientMessage);
/* 15:17 */     Composer.add(room.name, ClientMessage);
/* 16:18 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 17:19 */     Composer.add(Integer.valueOf(room.roomOwnerId), ClientMessage);
/* 18:20 */     Composer.add(room.roomOwnerName, ClientMessage);
/* 19:21 */     Composer.add(Integer.valueOf(room.state), ClientMessage);
/* 20:22 */     Composer.add(Integer.valueOf(room.room != null ? room.room.userCount : 0), ClientMessage);
/* 21:23 */     Composer.add(Integer.valueOf(room.usersMax), ClientMessage);
/* 22:24 */     Composer.add(room.description, ClientMessage);
/* 23:25 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 24:26 */     Composer.add(Integer.valueOf(room.tradingSettings.permissions), ClientMessage);
/* 25:27 */     Composer.add(Integer.valueOf(room.rating), ClientMessage);
/* 26:28 */     Composer.add(Integer.valueOf(room.ranking), ClientMessage);
/* 27:29 */     Composer.add(Integer.valueOf(room.category), ClientMessage);
/* 28:30 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 29:31 */     Composer.add("", ClientMessage);
/* 30:32 */     Composer.add("", ClientMessage);
/* 31:33 */     Composer.add("", ClientMessage);
/* 32:34 */     Composer.add(Integer.valueOf(room.tags.length), ClientMessage);
/* 33:35 */     for (String Tag : room.tags) {
/* 34:36 */       Composer.add(Tag, ClientMessage);
/* 35:   */     }
/* 36:38 */     Composer.add(Integer.valueOf(room.icon.backgroundImage), ClientMessage);
/* 37:39 */     Composer.add(Integer.valueOf(room.icon.foregroundImage), ClientMessage);
/* 38:40 */     Composer.add(Integer.valueOf(room.icon.items.length), ClientMessage);
/* 39:41 */     for (String item : room.icon.items) {
/* 40:42 */       if ((!item.isEmpty()) && (!item.equals(",")))
/* 41:   */       {
/* 42:43 */         String[] values = item.split(",");
/* 43:44 */         Composer.add(Integer.valueOf(Integer.parseInt(values[0])), ClientMessage);
/* 44:45 */         if ((values.length > 1) && (!values[1].isEmpty())) {
/* 45:46 */           Composer.add(Integer.valueOf(Integer.parseInt(values[1])), ClientMessage);
/* 46:   */         } else {
/* 47:48 */           Composer.add(Integer.valueOf(0), ClientMessage);
/* 48:   */         }
/* 49:   */       }
/* 50:   */       else
/* 51:   */       {
/* 52:51 */         Composer.add(Integer.valueOf(0), ClientMessage);
/* 53:52 */         Composer.add(Integer.valueOf(0), ClientMessage);
/* 54:   */       }
/* 55:   */     }
/* 56:55 */     Composer.add(Boolean.valueOf(room.haveFlag(2)), ClientMessage);
/* 57:56 */     Composer.add(Boolean.valueOf(true), ClientMessage);
/* 58:57 */     Composer.add("Event name here..", ClientMessage);
/* 59:58 */     Composer.add("Event description here..", ClientMessage);
/* 60:59 */     Composer.add(Integer.valueOf(30), ClientMessage);
/* 61:   */   }
/* 62:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeRoom
 * JD-Core Version:    0.7.0.1
 */