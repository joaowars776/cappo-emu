/*  1:   */ package cappo.engine.network;
/*  2:   */ 
/*  3:   */ import io.netty.channel.Channel;
/*  4:   */ import io.netty.channel.ChannelFuture;
/*  5:   */ 
/*  6:   */ public class QueueWriter
/*  7:   */ {
/*  8:   */   public static void writeAndFlush(Channel s, MessageWriter writer)
/*  9:   */   {
/* 10:16 */     s.writeAndFlush(writer);
/* 11:   */   }
/* 12:   */   
/* 13:   */   public static void writeAndClose(Channel s, MessageWriter Message)
/* 14:   */   {
/* 15:20 */     ChannelFuture f = s.writeAndFlush(Message);
/* 16:21 */     f.awaitUninterruptibly();
/* 17:22 */     s.close();
/* 18:   */   }
/* 19:   */   
/* 20:   */   public static void write(Channel s, MessageWriter writer)
/* 21:   */   {
/* 22:27 */     s.write(writer);
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.network.QueueWriter
 * JD-Core Version:    0.7.0.1
 */