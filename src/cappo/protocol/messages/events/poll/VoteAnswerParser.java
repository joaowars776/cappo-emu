/*  1:   */ package cappo.protocol.messages.events.poll;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ 
/*  9:   */ public class VoteAnswerParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:16 */   private final int[] Results = new int[6];
/* 13:   */   private int Total;
/* 14:   */   
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:21 */     this.Results[(Main.currentPacket.readInt() - 1)] += 1;
/* 18:22 */     this.Total += 1;
/* 19:   */     
/* 20:24 */     MessageWriter ClientMessage = new MessageWriter();
/* 21:   */     
/* 22:26 */     Composer.initPacket(80, ClientMessage);
/* 23:27 */     Composer.add("Que puntaje le das al Emu", ClientMessage);
/* 24:28 */     Composer.add(Integer.valueOf(6), ClientMessage);
/* 25:   */     
/* 26:30 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 27:31 */     Composer.add("5", ClientMessage);
/* 28:32 */     Composer.add(Integer.valueOf(this.Results[0]), ClientMessage);
/* 29:   */     
/* 30:34 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 31:35 */     Composer.add("6", ClientMessage);
/* 32:36 */     Composer.add(Integer.valueOf(this.Results[1]), ClientMessage);
/* 33:   */     
/* 34:38 */     Composer.add(Integer.valueOf(2), ClientMessage);
/* 35:39 */     Composer.add("7", ClientMessage);
/* 36:40 */     Composer.add(Integer.valueOf(this.Results[2]), ClientMessage);
/* 37:   */     
/* 38:42 */     Composer.add(Integer.valueOf(3), ClientMessage);
/* 39:43 */     Composer.add("8", ClientMessage);
/* 40:44 */     Composer.add(Integer.valueOf(this.Results[3]), ClientMessage);
/* 41:   */     
/* 42:46 */     Composer.add(Integer.valueOf(4), ClientMessage);
/* 43:47 */     Composer.add("9", ClientMessage);
/* 44:48 */     Composer.add(Integer.valueOf(this.Results[4]), ClientMessage);
/* 45:   */     
/* 46:50 */     Composer.add(Integer.valueOf(5), ClientMessage);
/* 47:51 */     Composer.add("10", ClientMessage);
/* 48:52 */     Composer.add(Integer.valueOf(this.Results[5]), ClientMessage);
/* 49:   */     
/* 50:54 */     Composer.add(Integer.valueOf(this.Total), ClientMessage);
/* 51:   */     
/* 52:56 */     QueueWriter.write(Main.socket, ClientMessage);
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.poll.VoteAnswerParser
 * JD-Core Version:    0.7.0.1
 */