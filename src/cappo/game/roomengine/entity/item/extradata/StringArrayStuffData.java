/*  1:   */ package cappo.game.roomengine.entity.item.extradata;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import java.util.List;
/*  7:   */ 
/*  8:   */ public class StringArrayStuffData
/*  9:   */   extends ExtraDataBase
/* 10:   */ {
/* 11:   */   public static final int TYPE_ID = 2;
/* 12:   */   public List<String> extraData;
/* 13:   */   
/* 14:   */   public int getType()
/* 15:   */   {
/* 16:14 */     return 2;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public StringArrayStuffData(StuffDataReader data)
/* 20:   */   {
/* 21:20 */     int len = data.readInt8();
/* 22:   */     
/* 23:22 */     this.extraData = new ArrayList(1 + (int)(len * 1.2D));
/* 24:23 */     for (int i = 0; i < len; i++) {
/* 25:24 */       this.extraData.add(data.readString());
/* 26:   */     }
/* 27:   */   }
/* 28:   */   
/* 29:   */   public byte[] data()
/* 30:   */   {
/* 31:30 */     StuffDataWriter data = new StuffDataWriter(2);
/* 32:31 */     data.writeInt8(this.extraData.size());
/* 33:32 */     for (String value : this.extraData) {
/* 34:33 */       data.writeString(value);
/* 35:   */     }
/* 36:35 */     return data.getData();
/* 37:   */   }
/* 38:   */   
/* 39:   */   public String getWallLegacyString()
/* 40:   */   {
/* 41:40 */     if ((this.extraData == null) || (this.extraData.isEmpty())) {
/* 42:41 */       return "";
/* 43:   */     }
/* 44:43 */     return (String)this.extraData.get(0);
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void serializeComposer(MessageWriter writer)
/* 48:   */   {
/* 49:48 */     Composer.add(Integer.valueOf(this.extraData.size()), writer);
/* 50:49 */     for (String value : this.extraData) {
/* 51:50 */       Composer.add(value, writer);
/* 52:   */     }
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.extradata.StringArrayStuffData
 * JD-Core Version:    0.7.0.1
 */