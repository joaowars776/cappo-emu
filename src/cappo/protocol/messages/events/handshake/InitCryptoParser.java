/*  1:   */ package cappo.protocol.messages.events.handshake;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ import cappo.protocol.messages.composers.handshake.BannerTokenComposer;
/*  7:   */ import java.math.BigInteger;
/*  8:   */ import java.security.SecureRandom;
/*  9:   */ import sun.misc.BASE64Encoder;
/* 10:   */ 
/* 11:   */ public class InitCryptoParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   private static BigInteger prime;
/* 15:   */   private static BigInteger generator;
/* 16:   */   
/* 17:   */   static {}
/* 18:   */   
/* 19:   */   private static void getPrime()
/* 20:   */   {
/* 21:27 */     SecureRandom random = new SecureRandom();
/* 22:   */     do
/* 23:   */     {
/* 24:29 */       prime = BigInteger.probablePrime(200, random);
/* 25:30 */     } while (!prime.isProbablePrime(10));
/* 26:   */     do
/* 27:   */     {
/* 28:34 */       generator = BigInteger.probablePrime(30, random);
/* 29:35 */     } while (!generator.isProbablePrime(10));
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void messageReceived(Connection Main)
/* 33:   */     throws Exception
/* 34:   */   {
/* 35:41 */     String data = prime.toString() + ":" + generator.toString();
/* 36:42 */     String secret = "!!#!#$#$#%GSdfiaje";
/* 37:43 */     int Length = "!!#!#$#$#%GSdfiaje".length();
/* 38:44 */     int magic = data.length();
/* 39:45 */     byte[] buf = new byte[magic];
/* 40:46 */     int p = 0;
/* 41:47 */     for (int i = 0; i < magic; i++)
/* 42:   */     {
/* 43:48 */       buf[i] = ((byte)(data.charAt(i) & 0xFF ^ "!!#!#$#$#%GSdfiaje".charAt(p) & 0xFF ^ magic));
/* 44:49 */       p++;
/* 45:49 */       if (p == Length) {
/* 46:50 */         p = 0;
/* 47:   */       }
/* 48:   */     }
/* 49:53 */     Main.InitDH(prime, generator, Main.generateRandomHexString(30));
/* 50:   */     
/* 51:55 */     BASE64Encoder encoder = new BASE64Encoder();
/* 52:56 */     QueueWriter.write(Main.socket, BannerTokenComposer.compose(encoder.encodeBuffer(buf).replaceAll("\r\n", "")));
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.handshake.InitCryptoParser
 * JD-Core Version:    0.7.0.1
 */