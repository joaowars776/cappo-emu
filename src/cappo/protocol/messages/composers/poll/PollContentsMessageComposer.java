/*  1:   */ package cappo.protocol.messages.composers.poll;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.polls.Poll;
/*  5:   */ import cappo.game.polls.PollQuestion;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import java.util.Collection;
/*  8:   */ import java.util.Iterator;
/*  9:   */ import java.util.Map;
/* 10:   */ 
/* 11:   */ public class PollContentsMessageComposer
/* 12:   */ {
/* 13:   */   public static int HEADER;
/* 14:   */   
/* 15:   */   public static final MessageWriter compose(Poll poll)
/* 16:   */   {
/* 17:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 18:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 19:20 */     Composer.writeInt32(poll.id, ClientMessage);
/* 20:21 */     Composer.add(poll.title, ClientMessage);
/* 21:22 */     Composer.add(poll.thanks, ClientMessage);
/* 22:23 */     Composer.writeInt32(poll.questions.size(), ClientMessage);
/* 23:   */     int j;
/* 24:   */     int i;
/* 25:   */     label196:
/* 26:24 */     for (Iterator localIterator = poll.questions.values().iterator(); localIterator.hasNext(); i < j)
/* 27:   */     {
/* 28:24 */       PollQuestion question = (PollQuestion)localIterator.next();
/* 29:   */       
/* 30:26 */       Composer.writeInt32(question.id, ClientMessage);
/* 31:27 */       Composer.writeInt32(question.orderid, ClientMessage);
/* 32:28 */       Composer.writeInt32(question.type, ClientMessage);
/* 33:29 */       Composer.add(question.text, ClientMessage);
/* 34:30 */       if ((question.type != 1) && (question.type != 2)) {
/* 35:   */         break label196;
/* 36:   */       }
/* 37:31 */       Composer.writeInt32(0, ClientMessage);
/* 38:32 */       Composer.writeInt32(question.answers.length, ClientMessage);
/* 39:33 */       int count = 0;
/* 40:   */       String[] arrayOfString;
/* 41:34 */       j = (arrayOfString = question.answers).length;i = 0; continue;String answer = arrayOfString[i];
/* 42:35 */       Composer.add(Integer.toString(count++), ClientMessage);
/* 43:36 */       Composer.add(answer, ClientMessage);i++;
/* 44:   */     }
/* 45:41 */     Composer.endPacket(ClientMessage);
/* 46:42 */     return ClientMessage;
/* 47:   */   }
/* 48:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.poll.PollContentsMessageComposer
 * JD-Core Version:    0.7.0.1
 */