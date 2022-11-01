/*  1:   */ package cappo.game.roomengine.gamemap;
/*  2:   */ 
/*  3:   */ import cappo.engine.logging.Log;
/*  4:   */ import cappo.game.roomengine.Square;
/*  5:   */ 
/*  6:   */ public class DynamicGameMap
/*  7:   */   extends GameMapBase
/*  8:   */ {
/*  9:   */   public DynamicGameMap(GameMapBase base, int sizeX, int sizeY)
/* 10:   */     throws Exception
/* 11:   */   {
/* 12: 8 */     super(base.modelName, base.doorX, base.doorY, base.doorZ, base.DoorOrientation, base.clubOnly);
/* 13:   */     
/* 14:10 */     this.widthX = sizeX;
/* 15:11 */     this.heightY = sizeY;
/* 16:   */     
/* 17:13 */     initFloorMap(this.widthX * this.heightY);
/* 18:   */     
/* 19:15 */     int index = 0;
/* 20:16 */     for (int y = 0; y < base.heightY; y++) {
/* 21:17 */       for (int x = 0; x < base.widthX; x++)
/* 22:   */       {
/* 23:   */         try
/* 24:   */         {
/* 25:19 */           int newXY = x + y * this.widthX;
/* 26:21 */           if ((this.doorX == x) && (this.doorY == y))
/* 27:   */           {
/* 28:22 */             setSquare(newXY, new Square(x, y, newXY, this.doorZ));
/* 29:   */           }
/* 30:   */           else
/* 31:   */           {
/* 32:24 */             Square sq = base.getSquare(index);
/* 33:25 */             if (sq != null) {
/* 34:26 */               setSquare(newXY, sq);
/* 35:   */             }
/* 36:   */           }
/* 37:   */         }
/* 38:   */         catch (Exception ex)
/* 39:   */         {
/* 40:30 */           Log.printException("", ex);
/* 41:   */         }
/* 42:33 */         index++;
/* 43:   */       }
/* 44:   */     }
/* 45:   */   }
/* 46:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.gamemap.DynamicGameMap
 * JD-Core Version:    0.7.0.1
 */