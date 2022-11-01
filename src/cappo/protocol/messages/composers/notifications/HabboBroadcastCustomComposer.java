/*  1:   */ package cappo.protocol.messages.composers.notifications;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import java.util.Map;
/*  6:   */ 
/*  7:   */ public class HabboBroadcastCustomComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(String notificationKey, Map<String, String> params)
/* 12:   */   {
/* 13:18 */     MessageWriter writer = new MessageWriter();
/* 14:19 */     Composer.initPacket(HEADER, writer);
/* 15:20 */     Composer.add(notificationKey, writer);
/* 16:21 */     if (params == null)
/* 17:   */     {
/* 18:22 */       Composer.writeInt32(0, writer);
/* 19:   */     }
/* 20:   */     else
/* 21:   */     {
/* 22:24 */       Composer.add(Integer.valueOf(params.size()), writer);
/* 23:25 */       for (String paramKey : params.keySet())
/* 24:   */       {
/* 25:26 */         String value = (String)params.get(paramKey);
/* 26:27 */         Composer.add(paramKey, writer);
/* 27:28 */         Composer.add(value, writer);
/* 28:   */       }
/* 29:   */     }
/* 30:31 */     Composer.endPacket(writer);
/* 31:32 */     return writer;
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.notifications.HabboBroadcastCustomComposer
 * JD-Core Version:    0.7.0.1
 */