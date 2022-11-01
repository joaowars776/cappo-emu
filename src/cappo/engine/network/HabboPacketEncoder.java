/*  1:   */ package cappo.engine.network;
/*  2:   */ 
/*  3:   */ import cappo.engine.logging.Log;
/*  4:   */ import io.netty.buffer.ByteBuf;
/*  5:   */ import io.netty.channel.ChannelHandlerContext;
/*  6:   */ import io.netty.handler.codec.MessageToByteEncoder;
/*  7:   */ 
/*  8:   */ public class HabboPacketEncoder
/*  9:   */   extends MessageToByteEncoder<Object>
/* 10:   */ {
/* 11:   */   protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf buf)
/* 12:   */     throws Exception
/* 13:   */   {
/* 14:18 */     if ((msg instanceof MessageWriter)) {
/* 15:   */       try
/* 16:   */       {
/* 17:20 */         MessageWriter v = (MessageWriter)msg;
/* 18:21 */         buf.writeBytes(v.getMessage());
/* 19:   */       }
/* 20:   */       catch (Exception ex)
/* 21:   */       {
/* 22:24 */         Log.printException("HabboPacketEncoder", ex);
/* 23:   */       }
/* 24:   */     }
/* 25:28 */     if ((msg instanceof byte[])) {
/* 26:30 */       buf.writeBytes((byte[])msg);
/* 27:   */     }
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.network.HabboPacketEncoder
 * JD-Core Version:    0.7.0.1
 */