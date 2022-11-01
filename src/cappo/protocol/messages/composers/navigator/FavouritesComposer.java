/*  1:   */ package cappo.protocol.messages.composers.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import java.util.Iterator;
/*  6:   */ import java.util.Set;
/*  7:   */ 
/*  8:   */ public class FavouritesComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(Set<Integer> Favorite_Rooms)
/* 13:   */   {
/* 14:18 */     MessageWriter ClientMessage = new MessageWriter(20 + Favorite_Rooms.size() * 250);
/* 15:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:20 */     Composer.add(Integer.valueOf(30), ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(Favorite_Rooms.size()), ClientMessage);
/* 18:22 */     for (Iterator localIterator = Favorite_Rooms.iterator(); localIterator.hasNext();)
/* 19:   */     {
/* 20:22 */       int roomId = ((Integer)localIterator.next()).intValue();
/* 21:23 */       Composer.add(Integer.valueOf(roomId), ClientMessage);
/* 22:   */     }
/* 23:25 */     Composer.endPacket(ClientMessage);
/* 24:26 */     return ClientMessage;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.navigator.FavouritesComposer
 * JD-Core Version:    0.7.0.1
 */