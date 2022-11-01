/*  1:   */ package cappo.game.collections;
/*  2:   */ 
/*  3:   */ public class BflyData
/*  4:   */ {
/*  5:   */   public static int getB(float k)
/*  6:   */   {
/*  7:12 */     return Math.round(k % 1.0F * 100.0F);
/*  8:   */   }
/*  9:   */   
/* 10:   */   public static int getA(float k, int b)
/* 11:   */   {
/* 12:18 */     return (Math.round(k * 100.0F) - b) / 100;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public static float Combine(int a, int b)
/* 16:   */   {
/* 17:23 */     return a + b / 100.0F;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public static int Parse(String a)
/* 21:   */   {
/* 22:28 */     int w = 0;int i = 0;
/* 23:29 */     int length = a.length();
/* 24:32 */     if (length == 0) {
/* 25:33 */       return 0;
/* 26:   */     }
/* 27:   */     do
/* 28:   */     {
/* 29:38 */       int k = a.charAt(i++);
/* 30:39 */       if ((k < 48) || (k > 59)) {
/* 31:40 */         return 0;
/* 32:   */       }
/* 33:42 */       w = 10 * w + k - 48;
/* 34:36 */     } while (
/* 35:   */     
/* 36:   */ 
/* 37:   */ 
/* 38:   */ 
/* 39:   */ 
/* 40:   */ 
/* 41:   */ 
/* 42:44 */       i < length);
/* 43:46 */     return w;
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.collections.BflyData
 * JD-Core Version:    0.7.0.1
 */