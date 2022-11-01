/*  1:   */ package cappo.game.roomengine.entity.item.extradata;
/*  2:   */ 
/*  3:   */ public class StuffDataReader
/*  4:   */ {
/*  5:   */   public int type;
/*  6:   */   public byte[] bytes;
/*  7:   */   public int reader;
/*  8:   */   
/*  9:   */   public StuffDataReader(byte[] arr)
/* 10:   */   {
/* 11:16 */     if (arr == null)
/* 12:   */     {
/* 13:18 */       this.bytes = new byte[2];
/* 14:19 */       return;
/* 15:   */     }
/* 16:21 */     this.bytes = arr;
/* 17:22 */     this.type = readInt8();
/* 18:   */   }
/* 19:   */   
/* 20:   */   public int readInt32()
/* 21:   */   {
/* 22:26 */     return ((this.bytes[(this.reader++)] & 0xFF) << 24) + ((this.bytes[(this.reader++)] & 0xFF) << 16) + ((this.bytes[(this.reader++)] & 0xFF) << 8) + (this.bytes[(this.reader++)] & 0xFF);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public int readInt16()
/* 26:   */   {
/* 27:30 */     return ((this.bytes[(this.reader++)] & 0xFF) << 8) + (this.bytes[(this.reader++)] & 0xFF);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public int readInt8()
/* 31:   */   {
/* 32:34 */     return this.bytes[(this.reader++)] & 0xFF;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public String readString()
/* 36:   */   {
/* 37:38 */     int len = readInt16();
/* 38:39 */     byte[] text = new byte[len];
/* 39:40 */     System.arraycopy(this.bytes, this.reader, text, 0, len);
/* 40:41 */     this.reader += len;
/* 41:42 */     return new String(text);
/* 42:   */   }
/* 43:   */   
/* 44:   */   public boolean canRead(int len)
/* 45:   */   {
/* 46:46 */     return this.bytes.length - this.reader >= len;
/* 47:   */   }
/* 48:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.extradata.StuffDataReader
 * JD-Core Version:    0.7.0.1
 */