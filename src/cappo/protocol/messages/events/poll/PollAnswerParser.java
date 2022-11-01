/*  1:   */ package cappo.protocol.messages.events.poll;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.Database;
/*  4:   */ import cappo.engine.network.MessageReader;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.polls.Poll;
/*  9:   */ import cappo.game.polls.PollManager;
/* 10:   */ import cappo.game.polls.PollQuestion;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.poll.PollErrorMessageComposer;
/* 13:   */ import java.util.Map;
/* 14:   */ 
/* 15:   */ public class PollAnswerParser
/* 16:   */   extends IncomingMessageEvent
/* 17:   */ {
/* 18:   */   public void messageReceived(Connection Main)
/* 19:   */   {
/* 20:21 */     Poll poll = (Poll)PollManager.polls.get(Integer.valueOf(Main.currentPacket.readInt()));
/* 21:22 */     if (poll == null)
/* 22:   */     {
/* 23:23 */       QueueWriter.write(Main.socket, PollErrorMessageComposer.compose());
/* 24:24 */       return;
/* 25:   */     }
/* 26:27 */     PollQuestion question = (PollQuestion)poll.questions.get(Integer.valueOf(Main.currentPacket.readInt()));
/* 27:28 */     if (question == null)
/* 28:   */     {
/* 29:29 */       QueueWriter.write(Main.socket, PollErrorMessageComposer.compose());
/* 30:30 */       return;
/* 31:   */     }
/* 32:33 */     int count = Main.currentPacket.readInt();
/* 33:34 */     for (int i = 0; i < count; i++)
/* 34:   */     {
/* 35:35 */       String answer = Main.currentPacket.readString();
/* 36:36 */       Database.exec("INSERT INTO poll_answers (userid,poll,question,answer)VALUES(" + Main.playerData.userId + "," + poll.id + "," + question.id + ",?);", new Object[] { answer });
/* 37:   */     }
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.poll.PollAnswerParser
 * JD-Core Version:    0.7.0.1
 */