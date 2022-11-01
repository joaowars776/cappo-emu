/*  1:   */ package cappo.protocol.messages;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ 
/*  5:   */ public class Composer
/*  6:   */ {
/*  7:   */   public static void writeInt32(int add, MessageWriter message)
/*  8:   */   {
/*  9:13 */     message.writetInt32(add);
/* 10:   */   }
/* 11:   */   
/* 12:   */   public static void writeInt16(int add, MessageWriter message)
/* 13:   */   {
/* 14:17 */     message.writeInt16(add);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public static void writeBoolean(boolean add, MessageWriter message)
/* 18:   */   {
/* 19:21 */     message.writeBoolean(add);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public static void writeByte(byte add, MessageWriter message)
/* 23:   */   {
/* 24:25 */     message.writeByte(add);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public static void writeChar(char add, MessageWriter message)
/* 28:   */   {
/* 29:29 */     message.writeChar(add);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public static void add(Object add, MessageWriter Message)
/* 33:   */   {
/* 34:33 */     if (add == null) {
/* 35:34 */       throw new UnsupportedOperationException("NULL Param in Append!");
/* 36:   */     }
/* 37:37 */     if ((add instanceof Integer))
/* 38:   */     {
/* 39:38 */       Message.writetInt32(((Integer)add).intValue());
/* 40:39 */       return;
/* 41:   */     }
/* 42:42 */     if ((add instanceof Short))
/* 43:   */     {
/* 44:43 */       Message.writetInt32(((Short)add).shortValue());
/* 45:44 */       return;
/* 46:   */     }
/* 47:47 */     if ((add instanceof String))
/* 48:   */     {
/* 49:48 */       Message.writeString((String)add);
/* 50:49 */       return;
/* 51:   */     }
/* 52:52 */     if ((add instanceof Boolean))
/* 53:   */     {
/* 54:53 */       Message.writeBoolean(((Boolean)add).booleanValue());
/* 55:54 */       return;
/* 56:   */     }
/* 57:57 */     if ((add instanceof byte[]))
/* 58:   */     {
/* 59:58 */       Message.writeBytes((byte[])add);
/* 60:59 */       return;
/* 61:   */     }
/* 62:62 */     if ((add instanceof Long))
/* 63:   */     {
/* 64:63 */       Message.writetInt32(((Long)add).intValue());
/* 65:64 */       return;
/* 66:   */     }
/* 67:67 */     throw new UnsupportedOperationException("Bad Param in Append " + add.getClass());
/* 68:   */   }
/* 69:   */   
/* 70:   */   public static void endPacket(MessageWriter Message)
/* 71:   */   {
/* 72:71 */     int tmp = Message.writer;
/* 73:72 */     int len = tmp - 4;
/* 74:73 */     if ((len < 2) || (len > 131072)) {
/* 75:74 */       throw new UnsupportedOperationException("Bad Message! Len=" + len);
/* 76:   */     }
/* 77:77 */     Message.writer = 0;
/* 78:78 */     Message.writetInt32(len);
/* 79:79 */     Message.writer = tmp;
/* 80:80 */     Message.isReady = true;
/* 81:   */   }
/* 82:   */   
/* 83:   */   public static void initPacket(int headerId, MessageWriter Message)
/* 84:   */   {
/* 85:84 */     if (headerId == 0) {
/* 86:85 */       throw new UnsupportedOperationException("Header = 0!!");
/* 87:   */     }
/* 88:88 */     Message.debugId = headerId;
/* 89:89 */     Message.writer = 4;
/* 90:90 */     Message.writeInt16((short)headerId);
/* 91:   */   }
/* 92:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.Composer
 * JD-Core Version:    0.7.0.1
 */