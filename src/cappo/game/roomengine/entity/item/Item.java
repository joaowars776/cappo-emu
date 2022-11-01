/*   1:    */ package cappo.game.roomengine.entity.item;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.Database;
/*   4:    */ import cappo.engine.logging.Log;
/*   5:    */ import cappo.game.collections.BaseItem;
/*   6:    */ import cappo.game.player.PlayerData;
/*   7:    */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*   8:    */ 
/*   9:    */ public abstract class Item
/*  10:    */ {
/*  11:    */   public static final int UPDATE = 1;
/*  12:    */   public static final int MOVE = 2;
/*  13:    */   public static final int INSERT = 3;
/*  14:    */   public static final int DELETE = 4;
/*  15:    */   public int refId;
/*  16:    */   private int mysqlAction;
/*  17:    */   public int itemId;
/*  18:    */   public BaseItem baseItem;
/*  19:    */   public PlayerData owner;
/*  20:    */   public ExtraDataBase extraData;
/*  21:    */   
/*  22:    */   public abstract int getRoomId();
/*  23:    */   
/*  24:    */   public abstract void insertItem()
/*  25:    */     throws Exception;
/*  26:    */   
/*  27:    */   public abstract void roomDataSave(boolean paramBoolean)
/*  28:    */     throws Exception;
/*  29:    */   
/*  30:    */   public abstract void setRoomData(RoomItemData paramRoomItemData);
/*  31:    */   
/*  32:    */   public abstract void cleanRoomData();
/*  33:    */   
/*  34:    */   public void setMysqlState(int newState)
/*  35:    */   {
/*  36: 38 */     if (this.mysqlAction == 0)
/*  37:    */     {
/*  38: 40 */       this.mysqlAction = newState;
/*  39: 41 */       return;
/*  40:    */     }
/*  41: 47 */     if (newState == 4)
/*  42:    */     {
/*  43: 50 */       this.mysqlAction = 4;
/*  44:    */       try
/*  45:    */       {
/*  46: 54 */         Database.exec(
/*  47:    */         
/*  48:    */ 
/*  49:    */ 
/*  50: 58 */           "DELETE da,db,dc FROM furnis AS da LEFT JOIN furnis_roomdata AS db ON db.id=da.id LEFT JOIN furnis_floorextra AS dc ON dc.id=da.id WHERE da.id = " + this.itemId + ";", new Object[0]);
/*  51:    */       }
/*  52:    */       catch (Exception ex)
/*  53:    */       {
/*  54: 60 */         Log.printException("Item-Delete", ex);
/*  55:    */       }
/*  56: 63 */       return;
/*  57:    */     }
/*  58: 66 */     if (this.mysqlAction == 4) {
/*  59: 68 */       return;
/*  60:    */     }
/*  61: 71 */     if (this.mysqlAction == 3) {
/*  62: 73 */       return;
/*  63:    */     }
/*  64: 76 */     if (newState == 2)
/*  65:    */     {
/*  66: 77 */       if (this.mysqlAction == 2)
/*  67:    */       {
/*  68: 82 */         this.mysqlAction = 1;
/*  69: 83 */         return;
/*  70:    */       }
/*  71: 86 */       this.mysqlAction = 2;
/*  72:    */     }
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void mysqlSave()
/*  76:    */     throws Exception
/*  77:    */   {
/*  78: 93 */     if (this.mysqlAction == 0) {
/*  79: 94 */       return;
/*  80:    */     }
/*  81: 97 */     if (this.mysqlAction == 3)
/*  82:    */     {
/*  83:    */       try
/*  84:    */       {
/*  85: 99 */         insertItem();
/*  86:    */       }
/*  87:    */       catch (Exception ex)
/*  88:    */       {
/*  89:101 */         Log.printException("mysqlSave", ex);
/*  90:    */       }
/*  91:104 */       this.mysqlAction = 0;
/*  92:105 */       return;
/*  93:    */     }
/*  94:108 */     if (this.mysqlAction != 4)
/*  95:    */     {
/*  96:111 */       int roomId = getRoomId();
/*  97:113 */       if (this.extraData != null)
/*  98:    */       {
/*  99:114 */         byte[] data = this.extraData.data();
/* 100:115 */         if (data == null) {
/* 101:116 */           Database.exec("UPDATE furnis SET roomid=" + roomId + ",userid=" + this.owner.userId + ",data=NULL WHERE id=" + this.itemId + ";", new Object[0]);
/* 102:    */         } else {
/* 103:118 */           Database.exec("UPDATE furnis SET roomid=" + roomId + ",userid=" + this.owner.userId + ",data=? WHERE id=" + this.itemId + ";", new Object[] { data });
/* 104:    */         }
/* 105:    */       }
/* 106:    */       else
/* 107:    */       {
/* 108:121 */         Database.exec("UPDATE furnis SET roomid=" + roomId + ",userid=" + this.owner.userId + ",data=NULL WHERE id=" + this.itemId + ";", new Object[0]);
/* 109:    */       }
/* 110:124 */       if (roomId < 1)
/* 111:    */       {
/* 112:125 */         if (this.mysqlAction == 2) {
/* 113:126 */           Database.exec("DELETE FROM furnis_roomdata WHERE id=" + this.itemId + ";", new Object[0]);
/* 114:    */         }
/* 115:    */       }
/* 116:    */       else {
/* 117:129 */         roomDataSave(this.mysqlAction == 2);
/* 118:    */       }
/* 119:    */     }
/* 120:133 */     this.mysqlAction = 0;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public int hashCode()
/* 124:    */   {
/* 125:138 */     return this.itemId;
/* 126:    */   }
/* 127:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.Item
 * JD-Core Version:    0.7.0.1
 */