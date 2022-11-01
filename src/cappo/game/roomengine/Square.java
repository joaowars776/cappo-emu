/*  1:   */ package cappo.game.roomengine;
/*  2:   */ 
/*  3:   */ import java.util.HashSet;
/*  4:   */ import java.util.Set;
/*  5:   */ 
/*  6:   */ public final class Square
/*  7:   */ {
/*  8:   */   public static final int HEIGHT_MULT = 256;
/*  9:   */   public static final int FLAG_BLOCKED = 16384;
/* 10:   */   public static final int MAX_HEIGHT = 64;
/* 11:20 */   public Set<Square> adjacencies = new HashSet(4, 1.0F);
/* 12:21 */   public Set<Square> adjacenciesNoDiagonal = new HashSet(4, 1.0F);
/* 13:   */   public float height;
/* 14:   */   public int x;
/* 15:   */   public int y;
/* 16:   */   public int xy;
/* 17:   */   
/* 18:   */   public Square(int x, int y, int xy, float z)
/* 19:   */   {
/* 20:30 */     this.x = x;
/* 21:31 */     this.y = y;
/* 22:32 */     this.xy = xy;
/* 23:33 */     this.height = z;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public final int getLocalCost(Square start, Square goal)
/* 27:   */   {
/* 28:37 */     if (this.xy == start.xy) {
/* 29:38 */       return 999999;
/* 30:   */     }
/* 31:40 */     return Math.abs(this.x - goal.x) + Math.abs(this.y - goal.y);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public boolean equals(Object arg0)
/* 35:   */   {
/* 36:45 */     return ((Square)arg0).xy == this.xy;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public int hashCode()
/* 40:   */   {
/* 41:50 */     return this.xy;
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.Square
 * JD-Core Version:    0.7.0.1
 */