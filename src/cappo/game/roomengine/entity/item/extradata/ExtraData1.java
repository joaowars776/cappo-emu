/*  1:   */ package cappo.game.roomengine.entity.item.extradata;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class ExtraData1
/*  7:   */   extends ExtraDataBase
/*  8:   */ {
/*  9:   */   public static final int TYPE_ID = 3;
/* 10:   */   public String value;
/* 11:   */   public int result;
/* 12:   */   
/* 13:   */   public int getType()
/* 14:   */   {
/* 15:11 */     return 3;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public ExtraData1(StuffDataReader reader)
/* 19:   */   {
/* 20:18 */     this.value = reader.readString();
/* 21:19 */     this.result = reader.readInt16();
/* 22:   */   }
/* 23:   */   
/* 24:   */   public byte[] data()
/* 25:   */   {
/* 26:24 */     StuffDataWriter data = new StuffDataWriter(3);
/* 27:25 */     data.writeString(this.value);
/* 28:26 */     data.writeInt16(this.result);
/* 29:27 */     return data.getData();
/* 30:   */   }
/* 31:   */   
/* 32:   */   public String getWallLegacyString()
/* 33:   */   {
/* 34:32 */     if (this.value == null) {
/* 35:33 */       return "";
/* 36:   */     }
/* 37:35 */     return this.value;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void serializeComposer(MessageWriter writer)
/* 41:   */   {
/* 42:41 */     Composer.add(this.value, writer);
/* 43:42 */     Composer.add(Integer.valueOf(this.result), writer);
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.extradata.ExtraData1
 * JD-Core Version:    0.7.0.1
 */