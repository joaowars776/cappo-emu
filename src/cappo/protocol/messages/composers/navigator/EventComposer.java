/*  1:   */ package cappo.protocol.messages.composers.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class EventComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(int eventId, String userName, int roomId, int eventCategory, String eventName, String eventDescription, int eventStartTime)
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(eventId), ClientMessage);
/* 16:20 */     Composer.add(userName, ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(roomId), ClientMessage);
/* 18:22 */     Composer.add(Integer.valueOf(eventCategory), ClientMessage);
/* 19:23 */     Composer.add(eventName, ClientMessage);
/* 20:24 */     Composer.add(eventDescription, ClientMessage);
/* 21:25 */     Composer.add(Integer.valueOf(eventStartTime), ClientMessage);
/* 22:26 */     Composer.add(Integer.valueOf(eventStartTime), ClientMessage);
/* 23:   */     
/* 24:   */ 
/* 25:   */ 
/* 26:   */ 
/* 27:31 */     Composer.endPacket(ClientMessage);
/* 28:32 */     return ClientMessage;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public static final MessageWriter compose()
/* 32:   */   {
/* 33:36 */     MessageWriter ClientMessage = new MessageWriter();
/* 34:37 */     Composer.initPacket(HEADER, ClientMessage);
/* 35:38 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 36:39 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 37:40 */     Composer.add("", ClientMessage);
/* 38:41 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 39:42 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 40:43 */     Composer.add("", ClientMessage);
/* 41:44 */     Composer.add("", ClientMessage);
/* 42:45 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 43:46 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 44:47 */     Composer.endPacket(ClientMessage);
/* 45:48 */     return ClientMessage;
/* 46:   */   }
/* 47:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.navigator.EventComposer
 * JD-Core Version:    0.7.0.1
 */