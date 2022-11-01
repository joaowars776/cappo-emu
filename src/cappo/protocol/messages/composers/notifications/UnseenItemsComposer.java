/*  1:   */ package cappo.protocol.messages.composers.notifications;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.UnseenItems;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import java.util.Iterator;
/*  7:   */ import java.util.List;
/*  8:   */ 
/*  9:   */ public class UnseenItemsComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(UnseenItems Items)
/* 14:   */   {
/* 15:19 */     MessageWriter ClientMessage = new MessageWriter(10000);
/* 16:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(Items.Size), ClientMessage);
/* 18:22 */     for (int Type : new int[] { 1, 2, 3, 4, 5 })
/* 19:   */     {
/* 20:23 */       List<Integer> items = (List)Items.unseenItems.get(Type);
/* 21:24 */       if (!items.isEmpty())
/* 22:   */       {
/* 23:25 */         Composer.add(Integer.valueOf(Type), ClientMessage);
/* 24:26 */         Composer.add(Integer.valueOf(items.size()), ClientMessage);
/* 25:27 */         for (Iterator localIterator = items.iterator(); localIterator.hasNext();)
/* 26:   */         {
/* 27:27 */           int ItemId = ((Integer)localIterator.next()).intValue();
/* 28:28 */           Composer.add(Integer.valueOf(ItemId), ClientMessage);
/* 29:   */         }
/* 30:   */       }
/* 31:   */     }
/* 32:32 */     Composer.endPacket(ClientMessage);
/* 33:33 */     return ClientMessage;
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.notifications.UnseenItemsComposer
 * JD-Core Version:    0.7.0.1
 */