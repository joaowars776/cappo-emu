/*  1:   */ package cappo.game.roomengine.entity.item.extradata;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import java.util.Iterator;
/*  7:   */ import java.util.List;
/*  8:   */ 
/*  9:   */ public class IntArrayStuffData
/* 10:   */   extends ExtraDataBase
/* 11:   */ {
/* 12:   */   public static final int TYPE_ID = 5;
/* 13:   */   public List<Integer> extraData;
/* 14:   */   
/* 15:   */   public int getType()
/* 16:   */   {
/* 17:14 */     return 5;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public IntArrayStuffData(StuffDataReader data)
/* 21:   */   {
/* 22:21 */     int len = data.readInt8();
/* 23:   */     
/* 24:23 */     this.extraData = new ArrayList(1 + (int)(len * 1.2D));
/* 25:24 */     for (int i = 0; i < len; i++) {
/* 26:25 */       this.extraData.add(Integer.valueOf(data.readInt32()));
/* 27:   */     }
/* 28:   */   }
/* 29:   */   
/* 30:   */   public byte[] data()
/* 31:   */   {
/* 32:31 */     StuffDataWriter data = new StuffDataWriter(5);
/* 33:32 */     data.writeInt8(this.extraData.size());
/* 34:33 */     for (Iterator localIterator = this.extraData.iterator(); localIterator.hasNext();)
/* 35:   */     {
/* 36:33 */       int value = ((Integer)localIterator.next()).intValue();
/* 37:34 */       data.writeInt32(value);
/* 38:   */     }
/* 39:36 */     return data.getData();
/* 40:   */   }
/* 41:   */   
/* 42:   */   public String getWallLegacyString()
/* 43:   */   {
/* 44:41 */     if ((this.extraData == null) || (this.extraData.isEmpty())) {
/* 45:42 */       return "";
/* 46:   */     }
/* 47:44 */     return Integer.toString(((Integer)this.extraData.get(0)).intValue());
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void serializeComposer(MessageWriter writer)
/* 51:   */   {
/* 52:49 */     Composer.add(Integer.valueOf(this.extraData.size()), writer);
/* 53:50 */     for (Iterator localIterator = this.extraData.iterator(); localIterator.hasNext();)
/* 54:   */     {
/* 55:50 */       int value = ((Integer)localIterator.next()).intValue();
/* 56:51 */       Composer.add(Integer.valueOf(value), writer);
/* 57:   */     }
/* 58:   */   }
/* 59:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.extradata.IntArrayStuffData
 * JD-Core Version:    0.7.0.1
 */