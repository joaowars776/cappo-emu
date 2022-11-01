/*  1:   */ package cappo.engine.network;
/*  2:   */ 
/*  3:   */ public class Crypto
/*  4:   */ {
/*  5:   */   private int i;
/*  6:   */   private int j;
/*  7:12 */   private final int[] Table = new int[256];
/*  8:14 */   private final int[] _Table = new int[258];
/*  9:   */   
/* 10:   */   public void init(byte[] key)
/* 11:   */   {
/* 12:17 */     int keyLen = key.length;
/* 13:18 */     this.i = 0;
/* 14:19 */     while (this.i < 256) {
/* 15:20 */       this.Table[this.i] = (this.i++);
/* 16:   */     }
/* 17:22 */     this.j = (this.i = 0);
/* 18:23 */     while (this.i < 256)
/* 19:   */     {
/* 20:24 */       this.j = ((this.j + this.Table[this.i] + (key[(this.i % keyLen)] & 0xFF)) % 256);
/* 21:25 */       Swamp(this.i++, this.j);
/* 22:   */     }
/* 23:27 */     this.j = (this.i = 0);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void parse(byte[] b)
/* 27:   */   {
/* 28:31 */     for (int a = 0; a < b.length; a++)
/* 29:   */     {
/* 30:32 */       this.i = (++this.i % 256);
/* 31:33 */       this.j = ((this.j + this.Table[this.i]) % 256);
/* 32:34 */       Swamp(this.i, this.j);
/* 33:35 */       b[a] = ((byte)(b[a] & 0xFF ^ this.Table[((this.Table[this.i] + this.Table[this.j]) % 256)]));
/* 34:   */     }
/* 35:   */   }
/* 36:   */   
/* 37:   */   private void Swamp(int a, int b)
/* 38:   */   {
/* 39:40 */     int k = this.Table[a];
/* 40:41 */     this.Table[a] = this.Table[b];
/* 41:42 */     this.Table[b] = k;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public void backup()
/* 45:   */   {
/* 46:46 */     int _i = 0;
/* 47:47 */     while (_i < 256)
/* 48:   */     {
/* 49:48 */       this._Table[_i] = this.Table[_i];
/* 50:49 */       _i++;
/* 51:   */     }
/* 52:51 */     this._Table[256] = this.i;
/* 53:52 */     this._Table[257] = this.j;
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void restore()
/* 57:   */   {
/* 58:56 */     int _i = 0;
/* 59:57 */     while (_i < 256)
/* 60:   */     {
/* 61:58 */       this.Table[_i] = this._Table[_i];
/* 62:59 */       _i++;
/* 63:   */     }
/* 64:61 */     this.i = this._Table[256];
/* 65:62 */     this.j = this._Table[257];
/* 66:   */   }
/* 67:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.network.Crypto
 * JD-Core Version:    0.7.0.1
 */