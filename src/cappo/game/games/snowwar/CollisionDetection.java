/*  1:   */ package cappo.game.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.gameobjects.GameItemObject;
/*  4:   */ 
/*  5:   */ public class CollisionDetection
/*  6:   */ {
/*  7:   */   public static boolean _pU(GameItemObject _arg1, GameItemObject _arg2)
/*  8:   */   {
/*  9:13 */     if (_arg2 == _arg1) {
/* 10:14 */       return false;
/* 11:   */     }
/* 12:16 */     return _s(_arg1, _arg2);
/* 13:   */   }
/* 14:   */   
/* 15:   */   private static boolean _s(GameItemObject _arg1, GameItemObject _arg2)
/* 16:   */   {
/* 17:20 */     return _arg1.location3D()._0Dk(_arg2.location3D(), _arg1.boundingData()[0] + _arg2.boundingData()[0]);
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.CollisionDetection
 * JD-Core Version:    0.7.0.1
 */