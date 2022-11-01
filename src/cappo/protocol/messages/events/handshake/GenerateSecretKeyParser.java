/*  1:   */ package cappo.protocol.messages.events.handshake;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.Crypto;
/*  4:   */ import cappo.engine.network.MessageReader;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.handshake.ServerPublicKeyComposer;
/*  9:   */ import java.math.BigInteger;
/* 10:   */ 
/* 11:   */ public class GenerateSecretKeyParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:18 */   private static final BigInteger p = new BigInteger("e81c7e72545e0eeed92a94a7c698d58422787b44b829d1fef60ad8667722e22e07c2194f2c7966b20a65e34b6fc9f34b1989e1fd212f35a83509c1f797fa69fb", 16);
/* 15:19 */   private static final BigInteger q = new BigInteger("8487dd339b23fe8ff78397f39a7cb17f62517c059738306d8096b1bc74777772ce34d7338d5100453ec0a2b207eed2cc8c63c3df9a1695ad6424d88b4f9d058d", 16);
/* 16:20 */   private static final BigInteger modulus = p.multiply(q);
/* 17:21 */   private static final BigInteger pubExp = new BigInteger("10001", 16);
/* 18:22 */   private static final BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
/* 19:23 */   private static final BigInteger privExp = pubExp.modInverse(phi);
/* 20:25 */   private static final int bl = (modulus.bitLength() + 7) / 8;
/* 21:   */   
/* 22:   */   private static byte[] pkcs1unpad(BigInteger src)
/* 23:   */   {
/* 24:28 */     byte[] b = src.toByteArray();
/* 25:   */     
/* 26:30 */     int i = 0;
/* 27:31 */     while ((i < b.length) && (b[i] == 0)) {
/* 28:32 */       i++;
/* 29:   */     }
/* 30:35 */     if ((b.length - i != bl - 1) || (b[i] != 2)) {
/* 31:36 */       return null;
/* 32:   */     }
/* 33:39 */     i++;
/* 34:41 */     while (b[i] != 0)
/* 35:   */     {
/* 36:42 */       i++;
/* 37:42 */       if (i >= b.length) {
/* 38:43 */         return null;
/* 39:   */       }
/* 40:   */     }
/* 41:46 */     byte[] out = new byte[b.length - (i + 1)];
/* 42:47 */     int p = 0;
/* 43:   */     do
/* 44:   */     {
/* 45:49 */       out[p] = b[i];
/* 46:50 */       p++;i++;
/* 47:48 */     } while (i < b.length);
/* 48:52 */     return out;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public void messageReceived(Connection Main)
/* 52:   */   {
/* 53:57 */     String key = Main.currentPacket.readString();
/* 54:58 */     if ((key.isEmpty()) || (key.length() < 10)) {
/* 55:59 */       return;
/* 56:   */     }
/* 57:62 */     BigInteger clientKey = new BigInteger(key, 16);
/* 58:63 */     clientKey = clientKey.modPow(privExp, modulus);
/* 59:64 */     clientKey = new BigInteger(new String(pkcs1unpad(clientKey)));
/* 60:   */     
/* 61:66 */     Main.RC4Decode = new Crypto();
/* 62:67 */     Main.RC4Decode.init(Main.HextoBytes(Main.generateSharedKey(clientKey)));
/* 63:68 */     QueueWriter.write(Main.socket, ServerPublicKeyComposer.compose(Main.getPublicKey()));
/* 64:   */   }
/* 65:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.handshake.GenerateSecretKeyParser
 * JD-Core Version:    0.7.0.1
 */