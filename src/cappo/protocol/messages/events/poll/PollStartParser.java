/*  1:   */ package cappo.protocol.messages.events.poll;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.polls.Poll;
/*  7:   */ import cappo.game.polls.PollManager;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.poll.PollContentsMessageComposer;
/* 10:   */ import cappo.protocol.messages.composers.poll.PollErrorMessageComposer;
/* 11:   */ import java.util.Map;
/* 12:   */ 
/* 13:   */ public class PollStartParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection Main)
/* 17:   */   {
/* 18:20 */     Poll poll = (Poll)PollManager.polls.get(Integer.valueOf(Main.currentPacket.readInt()));
/* 19:21 */     if (poll == null) {
/* 20:22 */       QueueWriter.write(Main.socket, PollErrorMessageComposer.compose());
/* 21:   */     } else {
/* 22:24 */       QueueWriter.write(Main.socket, PollContentsMessageComposer.compose(poll));
/* 23:   */     }
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.poll.PollStartParser
 * JD-Core Version:    0.7.0.1
 */