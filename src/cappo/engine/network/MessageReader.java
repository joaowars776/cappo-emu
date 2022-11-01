/*  1:   */ package cappo.engine.network;
/*  2:   */ 
/*  3:   */ public class MessageReader
/*  4:   */ {
/*  5:   */   public int headerId;
/*  6:   */   public byte[] bytes;
/*  7:   */   public int reader;
/*  8:   */   
/*  9:   */   public MessageReader(byte[] arr)
/* 10:   */   {
/* 11:16 */     this.bytes = arr;
/* 12:   */   }
/* 13:   */   
/* 14:   */   public MessageReader setHeaderId()
/* 15:   */   {
/* 16:20 */     this.headerId = readShort();
/* 17:21 */     return this;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public int readInt()
/* 21:   */   {
/* 22:25 */     return ((this.bytes[(this.reader++)] & 0xFF) << 24) + ((this.bytes[(this.reader++)] & 0xFF) << 16) + ((this.bytes[(this.reader++)] & 0xFF) << 8) + (this.bytes[(this.reader++)] & 0xFF);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public int readShort()
/* 26:   */   {
/* 27:29 */     return ((this.bytes[(this.reader++)] & 0xFF) << 8) + (this.bytes[(this.reader++)] & 0xFF);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public boolean readBoolean()
/* 31:   */   {
/* 32:33 */     return (this.bytes[(this.reader++)] & 0xFF) == 1;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public String readString()
/* 36:   */   {
/* 37:37 */     int len = readShort();
/* 38:38 */     String result = new String(this.bytes, this.reader, len);
/* 39:39 */     this.reader += len;
/* 40:40 */     return result;
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.network.MessageReader
 * JD-Core Version:    0.7.0.1
 */