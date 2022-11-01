/*  1:   */ package cappo.game.roomengine.entity.item.extradata;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ 
/*  5:   */ public class HighScoreStuffData
/*  6:   */   extends ExtraDataBase
/*  7:   */ {
/*  8:   */   public static final int TYPE_ID = 6;
/*  9:   */   
/* 10:   */   public int getType()
/* 11:   */   {
/* 12:10 */     return 6;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public byte[] data()
/* 16:   */   {
/* 17:15 */     return null;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void serializeComposer(MessageWriter writer) {}
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.extradata.HighScoreStuffData
 * JD-Core Version:    0.7.0.1
 */