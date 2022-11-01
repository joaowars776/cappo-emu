/*  1:   */ package cappo.game.roomengine.entity.item.extradata;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import java.util.Map;
/*  6:   */ import java.util.concurrent.ConcurrentHashMap;
/*  7:   */ 
/*  8:   */ public class MapStuffData
/*  9:   */   extends ExtraDataBase
/* 10:   */ {
/* 11:   */   public static final int TYPE_ID = 1;
/* 12:   */   public Map<String, String> extraData;
/* 13:   */   public static final String STATE = "state";
/* 14:   */   public static final String RARITY = "rarity";
/* 15:   */   
/* 16:   */   public int getType()
/* 17:   */   {
/* 18:15 */     return 1;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public MapStuffData(StuffDataReader data)
/* 22:   */   {
/* 23:24 */     int len = data.readInt8();
/* 24:   */     
/* 25:26 */     this.extraData = new ConcurrentHashMap(1 + (int)(len * 1.2D));
/* 26:27 */     for (int i = 0; i < len; i++) {
/* 27:28 */       this.extraData.put(data.readString(), data.readString());
/* 28:   */     }
/* 29:   */   }
/* 30:   */   
/* 31:   */   public MapStuffData(String data)
/* 32:   */   {
/* 33:34 */     this.extraData = new ConcurrentHashMap();
/* 34:35 */     setExtraData(data);
/* 35:   */   }
/* 36:   */   
/* 37:   */   public byte[] data()
/* 38:   */   {
/* 39:41 */     StuffDataWriter data = new StuffDataWriter(1);
/* 40:42 */     data.writeInt8(this.extraData.size());
/* 41:43 */     for (String key : this.extraData.keySet())
/* 42:   */     {
/* 43:44 */       data.writeString(key);
/* 44:45 */       data.writeString((String)this.extraData.get(key));
/* 45:   */     }
/* 46:47 */     return data.getData();
/* 47:   */   }
/* 48:   */   
/* 49:   */   public void serializeComposer(MessageWriter writer)
/* 50:   */   {
/* 51:52 */     Composer.add(Integer.valueOf(this.extraData.size()), writer);
/* 52:53 */     for (String key : this.extraData.keySet())
/* 53:   */     {
/* 54:54 */       Composer.add(key, writer);
/* 55:55 */       Composer.add(this.extraData.get(key), writer);
/* 56:   */     }
/* 57:   */   }
/* 58:   */   
/* 59:   */   public void setExtraData(Object data)
/* 60:   */   {
/* 61:62 */     String sData = (String)data;
/* 62:   */     
/* 63:64 */     String[] values = sData.split("\t");
/* 64:65 */     for (String part : values) {
/* 65:66 */       if ((!part.isEmpty()) && (!part.equals("=")))
/* 66:   */       {
/* 67:70 */         String[] a = part.split("=");
/* 68:71 */         if (a.length == 2) {
/* 69:75 */           this.extraData.put(a[0], a[1]);
/* 70:   */         }
/* 71:   */       }
/* 72:   */     }
/* 73:   */   }
/* 74:   */   
/* 75:   */   public String getWallLegacyString()
/* 76:   */   {
/* 77:81 */     if (this.extraData == null) {
/* 78:82 */       return "";
/* 79:   */     }
/* 80:85 */     String data = (String)this.extraData.get("state");
/* 81:86 */     if (data == null) {
/* 82:87 */       return "";
/* 83:   */     }
/* 84:90 */     return data;
/* 85:   */   }
/* 86:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.extradata.MapStuffData
 * JD-Core Version:    0.7.0.1
 */