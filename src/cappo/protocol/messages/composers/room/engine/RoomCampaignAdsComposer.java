/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class RoomCampaignAdsComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   private static MessageWriter ClientMessage;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose()
/* 12:   */   {
/* 13:17 */     if (ClientMessage == null)
/* 14:   */     {
/* 15:18 */       ClientMessage = new MessageWriter();
/* 16:   */       
/* 17:   */ 
/* 18:   */ 
/* 19:   */ 
/* 20:   */ 
/* 21:   */ 
/* 22:   */ 
/* 23:   */ 
/* 24:   */ 
/* 25:   */ 
/* 26:   */ 
/* 27:30 */       Composer.initPacket(HEADER, ClientMessage);
/* 28:31 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 29:32 */       Composer.endPacket(ClientMessage);
/* 30:   */     }
/* 31:34 */     return ClientMessage;
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.RoomCampaignAdsComposer
 * JD-Core Version:    0.7.0.1
 */