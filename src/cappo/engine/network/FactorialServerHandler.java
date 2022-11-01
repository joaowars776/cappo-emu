/*   1:    */ package cappo.engine.network;
/*   2:    */ 
/*   3:    */ import cappo.engine.logging.Log;
/*   4:    */ import cappo.engine.player.Connection;
/*   5:    */ import cappo.protocol.messages.IncomingMessageEvent;
/*   6:    */ import io.netty.channel.Channel;
/*   7:    */ import io.netty.channel.ChannelFuture;
/*   8:    */ import io.netty.channel.ChannelFutureListener;
/*   9:    */ import io.netty.channel.ChannelHandler.Sharable;
/*  10:    */ import io.netty.channel.ChannelHandlerAdapter;
/*  11:    */ import io.netty.channel.ChannelHandlerContext;
/*  12:    */ import io.netty.channel.group.DefaultChannelGroup;
/*  13:    */ import io.netty.util.Attribute;
/*  14:    */ import io.netty.util.AttributeKey;
/*  15:    */ import io.netty.util.concurrent.GlobalEventExecutor;
/*  16:    */ 
/*  17:    */ @ChannelHandler.Sharable
/*  18:    */ public class FactorialServerHandler
/*  19:    */   extends ChannelHandlerAdapter
/*  20:    */ {
/*  21: 25 */   public static final DefaultChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
/*  22: 26 */   public static final AttributeKey<Connection> CONNECTION = AttributeKey.valueOf(FactorialServerHandler.class, "FactorialServerHandler.connection");
/*  23: 27 */   private static final ChannelFutureListener remover = new ChannelFutureListener()
/*  24:    */   {
/*  25:    */     public void operationComplete(ChannelFuture future)
/*  26:    */       throws Exception
/*  27:    */     {
/*  28: 30 */       Channel ch = future.channel();
/*  29:    */       
/*  30:    */ 
/*  31:    */ 
/*  32:    */ 
/*  33: 35 */       Connection cn = (Connection)ch.attr(FactorialServerHandler.CONNECTION).getAndRemove();
/*  34: 36 */       if (cn == null)
/*  35:    */       {
/*  36: 37 */         Log.printException("FactorialServerHandler", new Exception("Channel closed without Connection Attribute!!"));
/*  37: 38 */         return;
/*  38:    */       }
/*  39:    */       try
/*  40:    */       {
/*  41: 42 */         cn.channelDisconnected();
/*  42:    */       }
/*  43:    */       catch (Exception ex)
/*  44:    */       {
/*  45: 44 */         Log.printException("disconnect", ex);
/*  46:    */       }
/*  47:    */     }
/*  48:    */   };
/*  49:    */   
/*  50:    */   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
/*  51:    */     throws Exception
/*  52:    */   {
/*  53: 51 */     ctx.close();
/*  54: 52 */     channelInactive(ctx);
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void channelRead(ChannelHandlerContext ctx, Object packet)
/*  58:    */   {
/*  59: 57 */     messageReceived(ctx, (MessageReader)packet);
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void channelReadComplete(ChannelHandlerContext ctx)
/*  63:    */   {
/*  64: 62 */     ctx.flush();
/*  65:    */   }
/*  66:    */   
/*  67:    */   private void messageReceived(ChannelHandlerContext ctx, MessageReader reader)
/*  68:    */   {
/*  69: 66 */     Channel ch = ctx.channel();
/*  70: 67 */     Connection cn = (Connection)ch.attr(CONNECTION).get();
/*  71: 69 */     if (cn == null)
/*  72:    */     {
/*  73: 70 */       if (reader.headerId == 4000)
/*  74:    */       {
/*  75: 71 */         if (channels.add(ch))
/*  76:    */         {
/*  77: 75 */           cn = new Connection();
/*  78: 76 */           cn.socket = ch;
/*  79:    */           
/*  80:    */ 
/*  81: 79 */           ch.attr(CONNECTION).set(cn);
/*  82: 80 */           ch.closeFuture().addListener(remover);
/*  83:    */         }
/*  84: 82 */         return;
/*  85:    */       }
/*  86: 84 */       Log.printException("messageReceived", new Exception("Not have Connection Class"));
/*  87: 85 */       return;
/*  88:    */     }
/*  89: 88 */     cn.currentPacket = reader;
/*  90: 90 */     if (IncomingMessageEvent.callBacks[reader.headerId] != null)
/*  91:    */     {
/*  92: 93 */       long now = System.currentTimeMillis();
/*  93:    */       try
/*  94:    */       {
/*  95: 95 */         IncomingMessageEvent.callBacks[reader.headerId].messageReceived(cn);
/*  96:    */       }
/*  97:    */       catch (Exception ex)
/*  98:    */       {
/*  99: 97 */         Log.printException("messageReceived", ex);
/* 100:    */       }
/* 101:100 */       long delay = System.currentTimeMillis() - now;
/* 102:101 */       if (delay > 1000L) {
/* 103:102 */         Log.printLog("PacketSlow | id = " + reader.headerId + " | ms = " + delay);
/* 104:    */       }
/* 105:    */     }
/* 106:    */     else
/* 107:    */     {
/* 108:106 */       Log.printLog("Packet desconocido <" + reader.headerId + ">");
/* 109:    */     }
/* 110:    */   }
/* 111:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.network.FactorialServerHandler
 * JD-Core Version:    0.7.0.1
 */