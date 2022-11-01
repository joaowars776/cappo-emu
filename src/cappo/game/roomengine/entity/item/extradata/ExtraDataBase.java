/*  1:   */ package cappo.game.roomengine.entity.item.extradata;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ 
/*  5:   */ public abstract class ExtraDataBase
/*  6:   */ {
/*  7:   */   public void setExtraData(Object extraData) {}
/*  8:   */   
/*  9:   */   public String getWallLegacyString()
/* 10:   */   {
/* 11:10 */     return "";
/* 12:   */   }
/* 13:   */   
/* 14:   */   public abstract byte[] data();
/* 15:   */   
/* 16:   */   public abstract int getType();
/* 17:   */   
/* 18:   */   public abstract void serializeComposer(MessageWriter paramMessageWriter);
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.extradata.ExtraDataBase
 * JD-Core Version:    0.7.0.1
 */