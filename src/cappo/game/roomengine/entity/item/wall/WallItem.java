/*   1:    */ package cappo.game.roomengine.entity.item.wall;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.Database;
/*   4:    */ import cappo.engine.logging.Log;
/*   5:    */ import cappo.game.collections.BaseItem;
/*   6:    */ import cappo.game.player.PlayerData;
/*   7:    */ import cappo.game.roomengine.entity.item.Item;
/*   8:    */ import cappo.game.roomengine.entity.item.RoomItemData;
/*   9:    */ import cappo.game.roomengine.entity.item.extradata.CrackableExtraData;
/*  10:    */ import cappo.game.roomengine.entity.item.extradata.ExtraData1;
/*  11:    */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*  12:    */ import cappo.game.roomengine.entity.item.extradata.IntArrayStuffData;
/*  13:    */ import cappo.game.roomengine.entity.item.extradata.MapStuffData;
/*  14:    */ import cappo.game.roomengine.entity.item.extradata.StringArrayStuffData;
/*  15:    */ import cappo.game.roomengine.entity.item.extradata.StringStuffData;
/*  16:    */ import cappo.game.roomengine.entity.item.extradata.StuffDataReader;
/*  17:    */ 
/*  18:    */ public abstract class WallItem
/*  19:    */   extends Item
/*  20:    */ {
/*  21:    */   private RoomWallItemData data;
/*  22:    */   
/*  23:    */   public static WallItem createItem(PlayerData owner, int ref, int Id, StuffDataReader reader, BaseItem baseItem)
/*  24:    */   {
/*  25: 24 */     WallItem userItem = new GenericWallItem();
/*  26:    */     
/*  27: 26 */     userItem.refId = ref;
/*  28: 27 */     userItem.itemId = Id;
/*  29: 28 */     userItem.baseItem = baseItem;
/*  30: 29 */     userItem.owner = owner;
/*  31: 31 */     if (reader.type == 0)
/*  32:    */     {
/*  33: 32 */       StringStuffData stuffdata = new StringStuffData(reader);
/*  34: 33 */       userItem.extraData = stuffdata;
/*  35:    */       try
/*  36:    */       {
/*  37: 37 */         GenericWallItem wall = (GenericWallItem)userItem;
/*  38: 38 */         wall.setIntData(Integer.parseInt(stuffdata.extraData));
/*  39:    */       }
/*  40:    */       catch (Exception localException) {}
/*  41:    */     }
/*  42: 43 */     else if (reader.type == 1)
/*  43:    */     {
/*  44: 44 */       userItem.extraData = new MapStuffData(reader);
/*  45:    */     }
/*  46: 45 */     else if (reader.type == 2)
/*  47:    */     {
/*  48: 46 */       userItem.extraData = new StringArrayStuffData(reader);
/*  49:    */     }
/*  50: 47 */     else if (reader.type == 3)
/*  51:    */     {
/*  52: 48 */       userItem.extraData = new ExtraData1(reader);
/*  53:    */     }
/*  54: 51 */     else if (reader.type == 5)
/*  55:    */     {
/*  56: 52 */       userItem.extraData = new IntArrayStuffData(reader);
/*  57:    */     }
/*  58: 55 */     else if (reader.type == 7)
/*  59:    */     {
/*  60: 56 */       userItem.extraData = new CrackableExtraData(reader);
/*  61:    */     }
/*  62:    */     else
/*  63:    */     {
/*  64: 58 */       Log.printLog("BAD EXTRATYPE = " + reader.type + " - BASEID = " + baseItem.Id);
/*  65:    */     }
/*  66: 61 */     return userItem;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public String roomDataString()
/*  70:    */   {
/*  71: 65 */     return this.data.toString();
/*  72:    */   }
/*  73:    */   
/*  74:    */   public int getRoomId()
/*  75:    */   {
/*  76: 70 */     if (this.data == null) {
/*  77: 71 */       return 0;
/*  78:    */     }
/*  79: 73 */     return this.data.getRoomId();
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void insertItem()
/*  83:    */     throws Exception
/*  84:    */   {
/*  85: 78 */     int roomId = getRoomId();
/*  86:    */     
/*  87: 80 */     byte[] dat = this.extraData.data();
/*  88: 81 */     if (dat == null) {
/*  89: 82 */       Database.exec("INSERT IGNORE INTO furnis(id,userid,baseid,roomid,data)VALUES(" + this.itemId + "," + this.owner.userId + "," + this.baseItem.Id + "," + roomId + ",NULL);", new Object[0]);
/*  90:    */     } else {
/*  91: 84 */       Database.exec("INSERT IGNORE INTO furnis(id,userid,baseid,roomid,data)VALUES(" + this.itemId + "," + this.owner.userId + "," + this.baseItem.Id + "," + roomId + ",?);", new Object[] { dat });
/*  92:    */     }
/*  93: 87 */     if (roomId > 0) {
/*  94: 88 */       Database.exec("INSERT IGNORE INTO furnis_roomdata(id,a,b,r)VALUES(" + this.itemId + "," + this.data.GetXValue() + "," + this.data.GetYValue() + "," + this.data.n() + ");", new Object[0]);
/*  95:    */     }
/*  96:    */   }
/*  97:    */   
/*  98:    */   public void roomDataSave(boolean moved)
/*  99:    */     throws Exception
/* 100:    */   {
/* 101: 94 */     if (moved)
/* 102:    */     {
/* 103: 95 */       float a = this.data.GetXValue();
/* 104: 96 */       float b = this.data.GetYValue();
/* 105: 97 */       int r = this.data.n();
/* 106: 98 */       Database.exec("INSERT INTO furnis_roomdata(id,a,b,r)VALUES(" + this.itemId + "," + a + "," + b + "," + r + ") on DUPLICATE KEY UPDATE `a`='" + a + "',`b`='" + b + "',`r`='" + r + "';", new Object[0]);
/* 107:    */     }
/* 108:    */     else
/* 109:    */     {
/* 110:100 */       this.data.save();
/* 111:    */     }
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void setRoomData(RoomItemData dat)
/* 115:    */   {
/* 116:106 */     this.data = ((RoomWallItemData)dat);
/* 117:    */   }
/* 118:    */   
/* 119:    */   public void cleanRoomData()
/* 120:    */   {
/* 121:111 */     this.data = null;
/* 122:    */   }
/* 123:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.wall.WallItem
 * JD-Core Version:    0.7.0.1
 */