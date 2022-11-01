/*  1:   */ package cappo.game.roomengine.entity.item.floor;
/*  2:   */ 
/*  3:   */ import cappo.engine.logging.Log;
/*  4:   */ import cappo.game.sound.trax.Trax;
/*  5:   */ import cappo.game.sound.trax.TraxDisc;
/*  6:   */ import java.util.Map;
/*  7:   */ 
/*  8:   */ public class SongItem
/*  9:   */   extends GenericFloorItem
/* 10:   */ {
/* 11:   */   public TraxDisc Disc;
/* 12:   */   
/* 13:   */   public void setExtraParam(int extraparam)
/* 14:   */   {
/* 15:18 */     if (extraparam == 0) {
/* 16:19 */       extraparam = 1;
/* 17:   */     }
/* 18:22 */     super.setExtraParam(extraparam);
/* 19:   */     
/* 20:24 */     this.Disc = ((TraxDisc)Trax.songDiscs.get(Integer.valueOf(extraparam)));
/* 21:25 */     if (this.Disc == null) {
/* 22:26 */       Log.printLog("UnRegistered SongId: " + extraparam);
/* 23:   */     }
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.SongItem
 * JD-Core Version:    0.7.0.1
 */