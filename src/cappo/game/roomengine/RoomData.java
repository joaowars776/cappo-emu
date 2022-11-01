/*   1:    */ package cappo.game.roomengine;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.Database;
/*   4:    */ import cappo.engine.logging.Log;
/*   5:    */ import cappo.engine.threadpools.RoomTask;
/*   6:    */ import cappo.game.navigator.NavigatorCategories;
/*   7:    */ import cappo.game.player.PlayerData;
/*   8:    */ import cappo.game.roomengine.roomlisting.RoomListing.ListingRoomState;
/*   9:    */ import cappo.game.roomengine.settings.ChatSettings;
/*  10:    */ import cappo.game.roomengine.settings.ModerationPermissions;
/*  11:    */ import cappo.game.roomengine.settings.TradingSettings;
/*  12:    */ 
/*  13:    */ public final class RoomData
/*  14:    */ {
/*  15:    */   public String model;
/*  16:    */   public long lastUsedThis;
/*  17:    */   public String[] tags;
/*  18:    */   public boolean muteAllOn;
/*  19:    */   public int roomId;
/*  20:    */   public int BotCounter;
/*  21:    */   public int category;
/*  22:    */   public int rating;
/*  23:    */   public int state;
/*  24:    */   public int usersMax;
/*  25:    */   public int roomOwnerId;
/*  26:    */   public int ranking;
/*  27:    */   public int floorAnchor;
/*  28:    */   public int wallAnchor;
/*  29:    */   public String roomOwnerName;
/*  30:    */   public String description;
/*  31:    */   public String Floor;
/*  32:    */   public String Wallpaper;
/*  33:    */   public String Landscape;
/*  34:    */   public String name;
/*  35:    */   public String password;
/*  36:    */   public RoomTask room;
/*  37:    */   public PlayerData roomOwner;
/*  38:    */   public RoomEvent event;
/*  39:    */   public RoomIcon icon;
/*  40:    */   public ModerationPermissions modPermissions;
/*  41:    */   public TradingSettings tradingSettings;
/*  42:    */   public ChatSettings chatSettings;
/*  43:    */   private int flags;
/*  44:    */   public final RoomListing.ListingRoomState[] roomListingState;
/*  45:    */   
/*  46:    */   public RoomData(int id, int maxUsers)
/*  47:    */   {
/*  48: 62 */     this.roomId = id;
/*  49: 63 */     this.usersMax = maxUsers;
/*  50: 64 */     this.roomListingState = new RoomListing.ListingRoomState[2 + (1 + NavigatorCategories.MAX_ID)];
/*  51:    */   }
/*  52:    */   
/*  53:    */   public boolean haveFlag(int bit)
/*  54:    */   {
/*  55: 68 */     return (this.flags & bit) != 0;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public final void setFlag(int flag, boolean add)
/*  59:    */   {
/*  60: 72 */     if (add) {
/*  61: 73 */       this.flags |= flag;
/*  62:    */     } else {
/*  63: 75 */       this.flags &= (flag ^ 0xFFFFFFFF);
/*  64:    */     }
/*  65:    */   }
/*  66:    */   
/*  67:    */   public final void xorFlag(int flag)
/*  68:    */   {
/*  69: 80 */     this.flags ^= flag;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void updateMaxUsers(int Max)
/*  73:    */   {
/*  74: 84 */     this.usersMax = Max;
/*  75: 85 */     if (this.room != null) {
/*  76: 86 */       this.room.updateMaxUsers(Max);
/*  77:    */     }
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void delete()
/*  81:    */   {
/*  82:    */     try
/*  83:    */     {
/*  84: 92 */       Database.exec("DELETE FROM rooms WHERE `id`='" + this.roomId + "';", new Object[0]);
/*  85:    */     }
/*  86:    */     catch (Exception ex)
/*  87:    */     {
/*  88: 95 */       Log.printException("saveItems", ex);
/*  89:    */     }
/*  90: 99 */     removeRoomPets();
/*  91:100 */     removeRoomBots();
/*  92:101 */     deleteRoomDiscs();
/*  93:    */   }
/*  94:    */   
/*  95:    */   private void removeRoomPets()
/*  96:    */   {
/*  97:    */     try
/*  98:    */     {
/*  99:106 */       Database.exec("UPDATE user_pets SET `room_id`='0' WHERE `room_id`='" + this.roomId + "';", new Object[0]);
/* 100:    */     }
/* 101:    */     catch (Exception ex)
/* 102:    */     {
/* 103:109 */       Log.printException("saveItems", ex);
/* 104:    */     }
/* 105:    */   }
/* 106:    */   
/* 107:    */   private void removeRoomBots()
/* 108:    */   {
/* 109:    */     try
/* 110:    */     {
/* 111:115 */       Database.exec("UPDATE user_bots SET `room_id`='0' WHERE `room_id`='" + this.roomId + "';", new Object[0]);
/* 112:    */     }
/* 113:    */     catch (Exception ex)
/* 114:    */     {
/* 115:118 */       Log.printException("saveItems", ex);
/* 116:    */     }
/* 117:    */   }
/* 118:    */   
/* 119:    */   private void deleteRoomDiscs()
/* 120:    */   {
/* 121:    */     try
/* 122:    */     {
/* 123:124 */       Database.exec("DELETE FROM room_discs WHERE `roomid`='" + this.roomId + "';", new Object[0]);
/* 124:    */     }
/* 125:    */     catch (Exception ex)
/* 126:    */     {
/* 127:127 */       Log.printException("saveItems", ex);
/* 128:    */     }
/* 129:    */   }
/* 130:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.RoomData
 * JD-Core Version:    0.7.0.1
 */