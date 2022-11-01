/*  1:   */ package cappo.game.roomengine.gamemap;
/*  2:   */ 
/*  3:   */ import cappo.engine.logging.Log;
/*  4:   */ import cappo.game.games.snowwar.Direction8;
/*  5:   */ import cappo.game.roomengine.Square;
/*  6:   */ 
/*  7:   */ public class CustomGameMap
/*  8:   */   extends GameMapBase
/*  9:   */ {
/* 10:   */   public String baseName;
/* 11:   */   public int mysqlAction;
/* 12:   */   
/* 13:   */   public CustomGameMap(String name, int doorx, int doory, float doorz, Direction8 doorRot, boolean club)
/* 14:   */   {
/* 15:10 */     super(name, doorx, doory, doorz, doorRot, club);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public String buildHeightMap()
/* 19:   */   {
/* 20:17 */     char[] map = new char[this.widthX * this.heightY + this.heightY];
/* 21:   */     
/* 22:19 */     int pos = 0;
/* 23:20 */     int xy = 0;
/* 24:21 */     for (int y = 0; y < this.heightY; y++)
/* 25:   */     {
/* 26:22 */       for (int x = 0; x < this.widthX; x++)
/* 27:   */       {
/* 28:   */         try
/* 29:   */         {
/* 30:24 */           Square sq = getSquare(xy);
/* 31:25 */           if (sq == null) {
/* 32:26 */             map[pos] = 'x';
/* 33:   */           } else {
/* 34:28 */             map[pos] = Integer.toString((int)sq.height, 36).charAt(0);
/* 35:   */           }
/* 36:   */         }
/* 37:   */         catch (Exception ex)
/* 38:   */         {
/* 39:31 */           map[pos] = 'x';
/* 40:32 */           Log.printException("", ex);
/* 41:   */         }
/* 42:35 */         xy++;
/* 43:36 */         pos++;
/* 44:   */       }
/* 45:39 */       map[pos] = '\n';
/* 46:40 */       pos++;
/* 47:   */     }
/* 48:43 */     return new String(map);
/* 49:   */   }
/* 50:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.gamemap.CustomGameMap
 * JD-Core Version:    0.7.0.1
 */