/*  1:   */ package cappo.game.roomengine.entity.item.extradata;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class StringStuffData
/*  7:   */   extends ExtraDataBase
/*  8:   */ {
/*  9:   */   public static final int TYPE_ID = 0;
/* 10:   */   public String extraData;
/* 11:   */   
/* 12:   */   public int getType()
/* 13:   */   {
/* 14:11 */     return 0;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public StringStuffData(StuffDataReader data)
/* 18:   */   {
/* 19:17 */     if (data == null) {
/* 20:18 */       this.extraData = "";
/* 21:   */     } else {
/* 22:20 */       this.extraData = data.readString();
/* 23:   */     }
/* 24:   */   }
/* 25:   */   
/* 26:   */   public byte[] data()
/* 27:   */   {
/* 28:26 */     if (this.extraData.isEmpty()) {
/* 29:28 */       return null;
/* 30:   */     }
/* 31:31 */     StuffDataWriter data = new StuffDataWriter(0);
/* 32:32 */     data.writeString(this.extraData);
/* 33:33 */     return data.getData();
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void setExtraData(Object data)
/* 37:   */   {
/* 38:38 */     if ((data instanceof Integer)) {
/* 39:39 */       this.extraData = Integer.toString(((Integer)data).intValue());
/* 40:   */     } else {
/* 41:41 */       this.extraData = ((String)data);
/* 42:   */     }
/* 43:   */   }
/* 44:   */   
/* 45:   */   public String getWallLegacyString()
/* 46:   */   {
/* 47:47 */     return this.extraData;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void serializeComposer(MessageWriter writer)
/* 51:   */   {
/* 52:52 */     Composer.add(this.extraData, writer);
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.extradata.StringStuffData
 * JD-Core Version:    0.7.0.1
 */