/*  1:   */ package cappo.game.roomengine.entity.item.extradata;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.List;
/*  5:   */ 
/*  6:   */ public class StuffDataWriter
/*  7:   */ {
/*  8:13 */   public List<Integer> savedPositions = new ArrayList();
/*  9:   */   private byte[] bytes;
/* 10:   */   public int writer;
/* 11:   */   
/* 12:   */   public StuffDataWriter(int type, int Size)
/* 13:   */   {
/* 14:19 */     this.bytes = new byte[Size];
/* 15:20 */     writeInt8(type);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public StuffDataWriter(int type)
/* 19:   */   {
/* 20:24 */     this(type, 1000);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public byte[] getData()
/* 24:   */   {
/* 25:28 */     if (this.writer == this.bytes.length) {
/* 26:29 */       return this.bytes;
/* 27:   */     }
/* 28:32 */     byte[] rtn = new byte[this.writer];
/* 29:33 */     for (int i = 0; i < this.writer; i++) {
/* 30:34 */       rtn[i] = this.bytes[i];
/* 31:   */     }
/* 32:36 */     this.bytes = rtn;
/* 33:   */     
/* 34:38 */     return this.bytes;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void writeInt32(int in)
/* 38:   */   {
/* 39:42 */     this.bytes[(this.writer++)] = ((byte)(in >>> 24 & 0xFF));
/* 40:43 */     this.bytes[(this.writer++)] = ((byte)(in >>> 16 & 0xFF));
/* 41:44 */     this.bytes[(this.writer++)] = ((byte)(in >>> 8 & 0xFF));
/* 42:45 */     this.bytes[(this.writer++)] = ((byte)(in >>> 0 & 0xFF));
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void writeInt16(int in)
/* 46:   */   {
/* 47:49 */     this.bytes[(this.writer++)] = ((byte)(in >>> 8 & 0xFF));
/* 48:50 */     this.bytes[(this.writer++)] = ((byte)(in >>> 0 & 0xFF));
/* 49:   */   }
/* 50:   */   
/* 51:   */   public void writeInt8(int in)
/* 52:   */   {
/* 53:54 */     this.bytes[(this.writer++)] = ((byte)(in >>> 0 & 0xFF));
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void writeString(String in)
/* 57:   */   {
/* 58:58 */     int len = in.length();
/* 59:59 */     writeInt16(len);
/* 60:60 */     for (int i = 0; i < len; i++) {
/* 61:61 */       this.bytes[(this.writer++)] = ((byte)(in.charAt(i) & 0xFF));
/* 62:   */     }
/* 63:   */   }
/* 64:   */   
/* 65:   */   public void writeBytes(byte[] in)
/* 66:   */   {
/* 67:66 */     int len = in.length;
/* 68:67 */     writeInt16(len);
/* 69:68 */     for (int i = 0; i < len; i++) {
/* 70:69 */       this.bytes[(this.writer++)] = in[i];
/* 71:   */     }
/* 72:   */   }
/* 73:   */   
/* 74:   */   public Object setSaved(Object add)
/* 75:   */   {
/* 76:75 */     this.savedPositions.add(Integer.valueOf(this.writer));
/* 77:76 */     return add;
/* 78:   */   }
/* 79:   */   
/* 80:   */   public void writeSaved(Object add)
/* 81:   */   {
/* 82:80 */     if ((add instanceof Integer))
/* 83:   */     {
/* 84:81 */       int tmp = this.writer;
/* 85:82 */       this.writer = ((Integer)this.savedPositions.remove(this.savedPositions.size() - 1)).intValue();
/* 86:83 */       writeInt32(((Integer)add).intValue());
/* 87:84 */       this.writer = tmp;
/* 88:   */     }
/* 89:   */     else
/* 90:   */     {
/* 91:86 */       throw new UnsupportedOperationException("Bad Param in SetWriter " + add.getClass());
/* 92:   */     }
/* 93:   */   }
/* 94:   */   
/* 95:   */   public void writeSavedInt8(int add)
/* 96:   */   {
/* 97:91 */     int tmp = this.writer;
/* 98:92 */     this.writer = ((Integer)this.savedPositions.remove(this.savedPositions.size() - 1)).intValue();
/* 99:93 */     writeInt8(add);
/* :0:94 */     this.writer = tmp;
/* :1:   */   }
/* :2:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.extradata.StuffDataWriter
 * JD-Core Version:    0.7.0.1
 */