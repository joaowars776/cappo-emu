/*  1:   */ package cappo.game.roomengine.entity.item.extradata;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class CrackableExtraData
/*  7:   */   extends ExtraDataBase
/*  8:   */ {
/*  9:   */   public static final int TYPE_ID = 7;
/* 10:   */   public String value;
/* 11:   */   public int hits;
/* 12:   */   public int target;
/* 13:   */   
/* 14:   */   public int getType()
/* 15:   */   {
/* 16:11 */     return 7;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public CrackableExtraData(StuffDataReader reader)
/* 20:   */   {
/* 21:19 */     this.value = reader.readString();
/* 22:20 */     this.hits = reader.readInt16();
/* 23:21 */     this.target = reader.readInt16();
/* 24:   */   }
/* 25:   */   
/* 26:   */   public byte[] data()
/* 27:   */   {
/* 28:26 */     StuffDataWriter data = new StuffDataWriter(7);
/* 29:27 */     data.writeString(this.value);
/* 30:28 */     data.writeInt16(this.hits);
/* 31:29 */     data.writeInt16(this.target);
/* 32:30 */     return data.getData();
/* 33:   */   }
/* 34:   */   
/* 35:   */   public String getWallLegacyString()
/* 36:   */   {
/* 37:35 */     if (this.value == null) {
/* 38:36 */       return "";
/* 39:   */     }
/* 40:38 */     return this.value;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void serializeComposer(MessageWriter writer)
/* 44:   */   {
/* 45:44 */     Composer.add(this.value, writer);
/* 46:45 */     Composer.add(Integer.valueOf(this.hits), writer);
/* 47:46 */     Composer.add(Integer.valueOf(this.target), writer);
/* 48:   */   }
/* 49:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.extradata.CrackableExtraData
 * JD-Core Version:    0.7.0.1
 */