/*  1:   */ package cappo.protocol.messages.composers.friendlist;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import java.util.Iterator;
/*  6:   */ import java.util.List;
/*  7:   */ 
/*  8:   */ public class RoomInviteErrorComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(int ErrorCode, List<Integer> Failed)
/* 13:   */   {
/* 14:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:20 */     Composer.add(Integer.valueOf(ErrorCode), ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(Failed.size()), ClientMessage);
/* 18:22 */     for (Iterator localIterator = Failed.iterator(); localIterator.hasNext();)
/* 19:   */     {
/* 20:22 */       int UserId = ((Integer)localIterator.next()).intValue();
/* 21:23 */       Composer.add(Integer.valueOf(UserId), ClientMessage);
/* 22:   */     }
/* 23:25 */     Composer.endPacket(ClientMessage);
/* 24:26 */     return ClientMessage;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.friendlist.RoomInviteErrorComposer
 * JD-Core Version:    0.7.0.1
 */