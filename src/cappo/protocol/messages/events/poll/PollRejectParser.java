/*  1:   */ package cappo.protocol.messages.events.poll;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.Database;
/*  4:   */ import cappo.engine.network.MessageReader;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.polls.Poll;
/*  9:   */ import cappo.game.polls.PollManager;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ import cappo.protocol.messages.composers.poll.PollErrorMessageComposer;
/* 12:   */ import java.util.Map;
/* 13:   */ 
/* 14:   */ public class PollRejectParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:20 */     Poll poll = (Poll)PollManager.polls.get(Integer.valueOf(Main.currentPacket.readInt()));
/* 20:21 */     if (poll == null) {
/* 21:22 */       QueueWriter.write(Main.socket, PollErrorMessageComposer.compose());
/* 22:   */     } else {
/* 23:24 */       Database.exec("INSERT INTO poll_answers (userid,poll,question,answer)VALUES(" + Main.playerData.userId + "," + poll.id + ", 0, NULL);", new Object[0]);
/* 24:   */     }
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.poll.PollRejectParser
 * JD-Core Version:    0.7.0.1
 */