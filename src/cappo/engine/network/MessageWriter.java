/*   1:    */ package cappo.engine.network;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.List;
/*   5:    */ 
/*   6:    */ public class MessageWriter
/*   7:    */ {
/*   8: 13 */   public List<Integer> savedPositions = new ArrayList();
/*   9:    */   public boolean isReady;
/*  10:    */   private boolean bytesReady;
/*  11:    */   public int writer;
/*  12:    */   private byte[] bytes;
/*  13:    */   public int debugId;
/*  14:    */   
/*  15:    */   public MessageWriter(int Size)
/*  16:    */   {
/*  17: 23 */     this.bytes = new byte[Size];
/*  18:    */   }
/*  19:    */   
/*  20:    */   public MessageWriter()
/*  21:    */   {
/*  22: 27 */     this(1000);
/*  23:    */   }
/*  24:    */   
/*  25:    */   public byte[] getMessage()
/*  26:    */     throws Exception
/*  27:    */   {
/*  28: 31 */     if (!this.isReady) {
/*  29: 32 */       throw new Exception("Not ended MessageWriter!");
/*  30:    */     }
/*  31: 35 */     if (this.bytesReady) {
/*  32: 36 */       return this.bytes;
/*  33:    */     }
/*  34: 39 */     byte[] rtn = new byte[this.writer];
/*  35: 40 */     for (int i = 0; i < this.writer; i++) {
/*  36: 41 */       rtn[i] = this.bytes[i];
/*  37:    */     }
/*  38: 44 */     this.bytes = rtn;
/*  39: 45 */     this.bytesReady = true;
/*  40:    */     
/*  41: 47 */     return rtn;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void writetInt32(int in)
/*  45:    */   {
/*  46: 51 */     this.bytes[(this.writer++)] = ((byte)(in >>> 24 & 0xFF));
/*  47: 52 */     this.bytes[(this.writer++)] = ((byte)(in >>> 16 & 0xFF));
/*  48: 53 */     this.bytes[(this.writer++)] = ((byte)(in >>> 8 & 0xFF));
/*  49: 54 */     this.bytes[(this.writer++)] = ((byte)(in >>> 0 & 0xFF));
/*  50:    */   }
/*  51:    */   
/*  52:    */   public void writeInt16(int in)
/*  53:    */   {
/*  54: 58 */     this.bytes[(this.writer++)] = ((byte)(in >>> 8 & 0xFF));
/*  55: 59 */     this.bytes[(this.writer++)] = ((byte)(in >>> 0 & 0xFF));
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void writeBoolean(boolean in)
/*  59:    */   {
/*  60: 63 */     this.bytes[(this.writer++)] = (in ? 1 : 0);
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void writeChar(char in)
/*  64:    */   {
/*  65: 67 */     this.bytes[(this.writer++)] = ((byte)(in & 0xFF));
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void writeByte(byte in)
/*  69:    */   {
/*  70: 71 */     this.bytes[(this.writer++)] = in;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void writeString(String in)
/*  74:    */   {
/*  75: 75 */     int len = in.length();
/*  76: 76 */     writeInt16(len);
/*  77: 77 */     for (int i = 0; i < len; i++) {
/*  78: 78 */       this.bytes[(this.writer++)] = ((byte)(in.charAt(i) & 0xFF));
/*  79:    */     }
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void writeBytes(byte[] in)
/*  83:    */   {
/*  84: 83 */     int len = in.length;
/*  85: 84 */     writeInt16(len);
/*  86: 85 */     for (int i = 0; i < len; i++) {
/*  87: 86 */       this.bytes[(this.writer++)] = in[i];
/*  88:    */     }
/*  89:    */   }
/*  90:    */   
/*  91:    */   public Object setSaved(Object add)
/*  92:    */   {
/*  93: 92 */     this.savedPositions.add(Integer.valueOf(this.writer));
/*  94: 93 */     return add;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void writeSaved(Object add)
/*  98:    */   {
/*  99: 97 */     if ((add instanceof Integer))
/* 100:    */     {
/* 101: 98 */       int tmp = this.writer;
/* 102: 99 */       this.writer = ((Integer)this.savedPositions.remove(this.savedPositions.size() - 1)).intValue();
/* 103:100 */       writetInt32(((Integer)add).intValue());
/* 104:101 */       this.writer = tmp;
/* 105:    */     }
/* 106:102 */     else if ((add instanceof Boolean))
/* 107:    */     {
/* 108:103 */       int tmp = this.writer;
/* 109:104 */       this.writer = ((Integer)this.savedPositions.remove(this.savedPositions.size() - 1)).intValue();
/* 110:105 */       writeBoolean(((Boolean)add).booleanValue());
/* 111:106 */       this.writer = tmp;
/* 112:    */     }
/* 113:    */     else
/* 114:    */     {
/* 115:108 */       throw new UnsupportedOperationException("Bad Param in SetWriter " + add.getClass());
/* 116:    */     }
/* 117:    */   }
/* 118:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.network.MessageWriter
 * JD-Core Version:    0.7.0.1
 */