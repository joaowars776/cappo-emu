/*  1:   */ package cappo.engine.network;
/*  2:   */ 
/*  3:   */ import cappo.engine.logging.Log;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import io.netty.buffer.ByteBuf;
/*  6:   */ import io.netty.channel.Channel;
/*  7:   */ import io.netty.channel.ChannelHandlerContext;
/*  8:   */ import io.netty.handler.codec.ByteToMessageDecoder;
/*  9:   */ import io.netty.util.Attribute;
/* 10:   */ import java.util.List;
/* 11:   */ 
/* 12:   */ public class HabboPacketDecoder
/* 13:   */   extends ByteToMessageDecoder
/* 14:   */ {
/* 15:20 */   private static final byte[] crossdomain = ""
/* 16:   */   
/* 17:   */ 
/* 18:   */ 
/* 19:24 */     .getBytes();
/* 20:   */   
/* 21:   */   protected void decode(ChannelHandlerContext ctx, ByteBuf buff, List<Object> out)
/* 22:   */     throws Exception
/* 23:   */   {
/* 24:   */     try
/* 25:   */     {
/* 26:30 */       Channel ch = ctx.channel();
/* 27:31 */       if (buff.readableBytes() < 6) {
/* 28:32 */         return;
/* 29:   */       }
/* 30:35 */       buff.markReaderIndex();
/* 31:   */       
/* 32:37 */       Connection cn = (Connection)ch.attr(FactorialServerHandler.CONNECTION).get();
/* 33:   */       
/* 34:39 */       byte[] packetLen = new byte[4];
/* 35:40 */       buff.readBytes(packetLen);
/* 36:41 */       if (cn == null)
/* 37:   */       {
/* 38:42 */         if (packetLen[0] == 60)
/* 39:   */         {
/* 40:43 */           ch.write(crossdomain);
/* 41:   */           
/* 42:   */ 
/* 43:46 */           buff.clear();
/* 44:   */         }
/* 45:   */       }
/* 46:51 */       else if (cn.RC4Decode != null)
/* 47:   */       {
/* 48:52 */         cn.RC4Decode.backup();
/* 49:53 */         cn.RC4Decode.parse(packetLen);
/* 50:   */       }
/* 51:57 */       MessageReader len = new MessageReader(packetLen);
/* 52:58 */       int bodyLen = len.readInt();
/* 53:59 */       if (buff.readableBytes() < bodyLen)
/* 54:   */       {
/* 55:60 */         if (cn == null)
/* 56:   */         {
/* 57:63 */           CappoServer.close(ch);
/* 58:64 */           return;
/* 59:   */         }
/* 60:67 */         if (cn.RC4Decode != null) {
/* 61:68 */           cn.RC4Decode.restore();
/* 62:   */         }
/* 63:70 */         buff.resetReaderIndex();
/* 64:71 */         return;
/* 65:   */       }
/* 66:74 */       if (bodyLen < 2)
/* 67:   */       {
/* 68:76 */         buff.clear();
/* 69:   */         
/* 70:78 */         Log.printLog("Bad packet len, bodyLen < 2 " + bodyLen + " " + buff.readableBytes());
/* 71:79 */         CappoServer.close(ch);
/* 72:80 */         return;
/* 73:   */       }
/* 74:83 */       out.add(readMessage(buff, cn == null ? null : cn.RC4Decode, bodyLen));
/* 75:   */     }
/* 76:   */     catch (Exception ex)
/* 77:   */     {
/* 78:86 */       Log.printException("HabboPacketDecoder", ex);
/* 79:   */     }
/* 80:   */   }
/* 81:   */   
/* 82:   */   private static MessageReader readMessage(ByteBuf buffer, Crypto crypto, int len)
/* 83:   */   {
/* 84:91 */     byte[] packet = new byte[len];
/* 85:92 */     buffer.readBytes(packet);
/* 86:93 */     if (crypto != null) {
/* 87:94 */       crypto.parse(packet);
/* 88:   */     }
/* 89:96 */     return new MessageReader(packet).setHeaderId();
/* 90:   */   }
/* 91:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.network.HabboPacketDecoder
 * JD-Core Version:    0.7.0.1
 */