/*   1:    */ package cappo.engine.network;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.Database;
/*   4:    */ import cappo.engine.logging.Log;
/*   5:    */ import cappo.engine.threadpools.WorkerTasks;
/*   6:    */ import io.netty.bootstrap.ServerBootstrap;
/*   7:    */ import io.netty.channel.Channel;
/*   8:    */ import io.netty.channel.ChannelFuture;
/*   9:    */ import io.netty.channel.ChannelHandler;
/*  10:    */ import io.netty.channel.ChannelInitializer;
/*  11:    */ import io.netty.channel.ChannelOption;
/*  12:    */ import io.netty.channel.ChannelPipeline;
/*  13:    */ import io.netty.channel.group.DefaultChannelGroup;
/*  14:    */ import io.netty.channel.nio.NioEventLoopGroup;
/*  15:    */ import io.netty.channel.socket.SocketChannel;
/*  16:    */ import io.netty.channel.socket.nio.NioServerSocketChannel;
/*  17:    */ import io.netty.handler.timeout.ReadTimeoutHandler;
/*  18:    */ import io.netty.util.ResourceLeakDetector;
/*  19:    */ import io.netty.util.ResourceLeakDetector.Level;
/*  20:    */ import java.util.Iterator;
/*  21:    */ import java.util.concurrent.ScheduledThreadPoolExecutor;
/*  22:    */ import java.util.concurrent.TimeUnit;
/*  23:    */ 
/*  24:    */ public class CappoServer
/*  25:    */ {
/*  26:    */   public static Channel serverChannel;
/*  27:    */   private static NioEventLoopGroup bossGroup;
/*  28:    */   private static NioEventLoopGroup workerGroup;
/*  29:    */   private static int port;
/*  30:    */   
/*  31:    */   public CappoServer(int p)
/*  32:    */   {
/*  33: 33 */     port = p;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void run()
/*  37:    */     throws Exception
/*  38:    */   {
/*  39: 37 */     Log.printLog("Opening Game Port : " + port);
/*  40: 39 */     if (port == 666) {
/*  41: 40 */       ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.PARANOID);
/*  42:    */     } else {
/*  43: 42 */       ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.DISABLED);
/*  44:    */     }
/*  45: 45 */     bossGroup = new NioEventLoopGroup();
/*  46: 47 */     if (WorkerTasks.serverType > 2) {
/*  47: 49 */       workerGroup = new NioEventLoopGroup(8);
/*  48: 50 */     } else if (WorkerTasks.serverType > 1) {
/*  49: 52 */       workerGroup = new NioEventLoopGroup(3);
/*  50:    */     } else {
/*  51: 55 */       workerGroup = new NioEventLoopGroup(2);
/*  52:    */     }
/*  53: 62 */     final FactorialServerHandler handler = new FactorialServerHandler();
/*  54:    */     
/*  55: 64 */     ServerBootstrap b = new ServerBootstrap();
/*  56:    */     
/*  57:    */ 
/*  58: 67 */     ((ServerBootstrap)b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, Integer.valueOf(200)))
/*  59: 68 */       .childHandler(new ChannelInitializer()
/*  60:    */       {
/*  61:    */         public void initChannel(SocketChannel ch)
/*  62:    */           throws Exception
/*  63:    */         {
/*  64: 71 */           ch.pipeline().addLast(new ChannelHandler[] { new ReadTimeoutHandler(100), 
/*  65: 72 */             new HabboPacketDecoder(), new HabboPacketEncoder(), handler });
/*  66:    */         }
/*  67: 75 */       }).childOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));
/*  68:    */     
/*  69:    */ 
/*  70: 78 */     serverChannel = b.bind(port).sync().channel();
/*  71:    */     
/*  72: 80 */     Log.printLog("Done.");
/*  73:    */   }
/*  74:    */   
/*  75:    */   public static void close(Channel ch)
/*  76:    */   {
/*  77: 84 */     if (ch == null) {
/*  78: 85 */       return;
/*  79:    */     }
/*  80: 89 */     ch.close();
/*  81:    */   }
/*  82:    */   
/*  83:    */   public static void shutdown()
/*  84:    */   {
/*  85: 93 */     ChannelFuture cf = serverChannel.close();
/*  86:    */     
/*  87:    */ 
/*  88: 96 */     Log.printLog("Removing Connections");
/*  89:    */     for (;;)
/*  90:    */     {
/*  91: 99 */       Iterator<Channel> itr = FactorialServerHandler.channels.iterator();
/*  92:101 */       if (!itr.hasNext()) {
/*  93:    */         break;
/*  94:    */       }
/*  95:    */       try
/*  96:    */       {
/*  97:106 */         close((Channel)itr.next());
/*  98:    */       }
/*  99:    */       catch (Exception ex)
/* 100:    */       {
/* 101:109 */         Log.printException("close", ex);
/* 102:    */       }
/* 103:    */       try
/* 104:    */       {
/* 105:114 */         Thread.sleep(1L);
/* 106:    */       }
/* 107:    */       catch (Exception localException1) {}
/* 108:    */     }
/* 109:120 */     cf.awaitUninterruptibly(10000L);
/* 110:    */     
/* 111:122 */     bossGroup.shutdownGracefully();
/* 112:123 */     workerGroup.shutdownGracefully();
/* 113:    */     try
/* 114:    */     {
/* 115:126 */       WorkerTasks.RoomsTasks.shutdown();
/* 116:127 */       WorkerTasks.RoomsTasks.awaitTermination(10L, TimeUnit.SECONDS);
/* 117:    */     }
/* 118:    */     catch (Exception ex)
/* 119:    */     {
/* 120:130 */       Log.printException("shutdown", ex);
/* 121:    */     }
/* 122:    */     try
/* 123:    */     {
/* 124:133 */       WorkerTasks.SnowWarTasks.shutdown();
/* 125:134 */       WorkerTasks.SnowWarTasks.awaitTermination(10L, TimeUnit.SECONDS);
/* 126:    */     }
/* 127:    */     catch (Exception ex)
/* 128:    */     {
/* 129:137 */       Log.printException("shutdown", ex);
/* 130:    */     }
/* 131:    */     try
/* 132:    */     {
/* 133:140 */       WorkerTasks.ItemsTasks.shutdown();
/* 134:141 */       WorkerTasks.ItemsTasks.awaitTermination(10L, TimeUnit.SECONDS);
/* 135:    */     }
/* 136:    */     catch (Exception ex)
/* 137:    */     {
/* 138:144 */       Log.printException("shutdown", ex);
/* 139:    */     }
/* 140:    */     try
/* 141:    */     {
/* 142:147 */       WorkerTasks.DatabaseExecTasks.shutdown();
/* 143:148 */       WorkerTasks.DatabaseExecTasks.awaitTermination(10L, TimeUnit.SECONDS);
/* 144:    */     }
/* 145:    */     catch (Exception ex)
/* 146:    */     {
/* 147:151 */       Log.printException("shutdown", ex);
/* 148:    */     }
/* 149:    */     try
/* 150:    */     {
/* 151:154 */       WorkerTasks.DatabaseQueryTasks.shutdown();
/* 152:155 */       WorkerTasks.DatabaseQueryTasks.awaitTermination(10L, TimeUnit.SECONDS);
/* 153:    */     }
/* 154:    */     catch (Exception ex)
/* 155:    */     {
/* 156:158 */       Log.printException("shutdown", ex);
/* 157:    */     }
/* 158:161 */     Database.close();
/* 159:    */     
/* 160:163 */     Log.printLog("");
/* 161:164 */     Log.printLog("Closed.");
/* 162:    */   }
/* 163:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.network.CappoServer
 * JD-Core Version:    0.7.0.1
 */