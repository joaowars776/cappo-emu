/*  1:   */ package cappo.protocol.messages.composers.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.catalog.giftwrapping.GiftWrappingConfiguration;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import java.util.Iterator;
/*  7:   */ import java.util.Map;
/*  8:   */ import java.util.Set;
/*  9:   */ 
/* 10:   */ public class GiftWrappingConfigurationComposer
/* 11:   */ {
/* 12:   */   private static MessageWriter ClientMessage;
/* 13:   */   public static int HEADER;
/* 14:   */   
/* 15:   */   public static final MessageWriter compose()
/* 16:   */   {
/* 17:18 */     if ((ClientMessage == null) || (GiftWrappingConfiguration.needUpdate))
/* 18:   */     {
/* 19:19 */       GiftWrappingConfiguration.needUpdate = false;
/* 20:   */       
/* 21:21 */       ClientMessage = new MessageWriter();
/* 22:22 */       Composer.initPacket(HEADER, ClientMessage);
/* 23:23 */       Composer.add(Boolean.valueOf(true), ClientMessage);
/* 24:24 */       Composer.add(Integer.valueOf(1), ClientMessage);
/* 25:   */       
/* 26:   */ 
/* 27:27 */       Composer.add(Integer.valueOf(GiftWrappingConfiguration.baseGiftItems.size()), ClientMessage);
/* 28:28 */       for (Iterator localIterator = GiftWrappingConfiguration.baseGiftItems.keySet().iterator(); localIterator.hasNext();)
/* 29:   */       {
/* 30:28 */         int colorSprite = ((Integer)localIterator.next()).intValue();
/* 31:29 */         Composer.add(Integer.valueOf(colorSprite), ClientMessage);
/* 32:   */       }
/* 33:33 */       Composer.add(Integer.valueOf(7), ClientMessage);
/* 34:34 */       for (int i = 0; i < 7; i++) {
/* 35:35 */         Composer.add(Integer.valueOf(i), ClientMessage);
/* 36:   */       }
/* 37:39 */       Composer.add(Integer.valueOf(11), ClientMessage);
/* 38:40 */       for (int i = 0; i < 11; i++) {
/* 39:41 */         Composer.add(Integer.valueOf(i), ClientMessage);
/* 40:   */       }
/* 41:45 */       Composer.add(Integer.valueOf(GiftWrappingConfiguration.baseGiftFreeItems.size()), ClientMessage);
/* 42:46 */       for (localIterator = GiftWrappingConfiguration.baseGiftFreeItems.keySet().iterator(); localIterator.hasNext();)
/* 43:   */       {
/* 44:46 */         int colorSprite = ((Integer)localIterator.next()).intValue();
/* 45:47 */         Composer.add(Integer.valueOf(colorSprite), ClientMessage);
/* 46:   */       }
/* 47:49 */       Composer.endPacket(ClientMessage);
/* 48:   */     }
/* 49:51 */     return ClientMessage;
/* 50:   */   }
/* 51:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.catalog.GiftWrappingConfigurationComposer
 * JD-Core Version:    0.7.0.1
 */