/*  1:   */ package cappo.protocol.messages.composers.poll;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.polls.Poll;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class PollOfferMessageComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(Poll poll)
/* 12:   */   {
/* 13:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 14:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(poll.id), ClientMessage);
/* 16:20 */     Composer.add(poll.title, ClientMessage);
/* 17:21 */     Composer.endPacket(ClientMessage);
/* 18:22 */     return ClientMessage;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.poll.PollOfferMessageComposer
 * JD-Core Version:    0.7.0.1
 */