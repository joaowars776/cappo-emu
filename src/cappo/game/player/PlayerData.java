/*   1:    */ package cappo.game.player;
/*   2:    */ 
/*   3:    */ import cappo.engine.player.Connection;
/*   4:    */ import cappo.game.player.messenger.PlayerMessenger;
/*   5:    */ 
/*   6:    */ public abstract class PlayerData
/*   7:    */ {
/*   8:    */   public static final int SECURITY_NONE = 0;
/*   9:    */   public static final int SECURITY_PANCHO = 1;
/*  10:    */   public static final int SECURITY_LINCE = 2;
/*  11:    */   public static final int SECURITY_BOT = 3;
/*  12:    */   public static final int SECURITY_STAFF = 4;
/*  13:    */   public static final int SECURITY_MOD = 5;
/*  14:    */   public static final int SECURITY_SECURITYMANAGER = 6;
/*  15:    */   public static final int SECURITY_COMUNITYMANAGER = 7;
/*  16:    */   public static final int SECURITY_MANAGER = 8;
/*  17:    */   public static final int SECURITY_DEVELOPER = 9;
/*  18: 24 */   public static Class<?>[] securityLevelPlr = new Class[10];
/*  19:    */   public int userId;
/*  20:    */   public int staffLevel;
/*  21:    */   public int sex;
/*  22:    */   public String email;
/*  23:    */   public String userName;
/*  24:    */   public String motto;
/*  25:    */   public AvatarLook avatarLook;
/*  26:    */   public Connection connection;
/*  27:    */   
/*  28:    */   static
/*  29:    */   {
/*  30: 26 */     securityLevelPlr[0] = PlayerNormal.class;
/*  31: 27 */     securityLevelPlr[1] = PanchoPantera.class;
/*  32: 28 */     securityLevelPlr[2] = PlayerLince.class;
/*  33: 29 */     securityLevelPlr[3] = PlayerBot.class;
/*  34: 30 */     securityLevelPlr[4] = PlayerStaff.class;
/*  35: 31 */     securityLevelPlr[5] = PlayerModerator.class;
/*  36: 32 */     securityLevelPlr[6] = PlayerSecurity.class;
/*  37: 33 */     securityLevelPlr[7] = PlayerCommunity.class;
/*  38: 34 */     securityLevelPlr[8] = PlayerManager.class;
/*  39: 35 */     securityLevelPlr[9] = PlayerDeveloper.class;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public static PlayerData getPlayer(int level)
/*  43:    */     throws Exception
/*  44:    */   {
/*  45: 39 */     PlayerData player = (PlayerData)securityLevelPlr[level].newInstance();
/*  46: 40 */     player.staffLevel = level;
/*  47: 41 */     return player;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void setupLevelStuff()
/*  51:    */   {
/*  52: 47 */     this.connection.giveBadge("Z63");
/*  53: 48 */     this.connection.giveBadge("Z64");
/*  54:    */   }
/*  55:    */   
/*  56:    */   public abstract boolean allowRoomAlert();
/*  57:    */   
/*  58:    */   public abstract boolean allowPickFurni();
/*  59:    */   
/*  60:    */   public abstract boolean allowEjectFurni();
/*  61:    */   
/*  62:    */   public abstract boolean allowRoomControl();
/*  63:    */   
/*  64:    */   public abstract boolean allowModTools();
/*  65:    */   
/*  66:    */   public abstract boolean allowBan();
/*  67:    */   
/*  68:    */   public abstract boolean allowGiveBadge();
/*  69:    */   
/*  70:    */   public abstract boolean allowHotelAlert();
/*  71:    */   
/*  72:    */   public abstract boolean allowGiveMoney();
/*  73:    */   
/*  74:    */   public boolean allowSuperAds()
/*  75:    */   {
/*  76: 62 */     return false;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public boolean allowHotelImageAlert()
/*  80:    */   {
/*  81: 66 */     return false;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public boolean allowDataReload()
/*  85:    */   {
/*  86: 70 */     return false;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public boolean canKickMe(int rank)
/*  90:    */   {
/*  91: 74 */     return true;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public boolean useChatBot()
/*  95:    */   {
/*  96: 78 */     return false;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public String getRealName()
/* 100:    */   {
/* 101: 94 */     return "";
/* 102:    */   }
/* 103:    */   
/* 104: 98 */   public PlayerMessenger messenger = new PlayerMessenger(this);
/* 105:    */   public long LastUsedThis;
/* 106:    */   public int AchievementsScore;
/* 107:    */   public long registerDate;
/* 108:    */   public long lastVisit;
/* 109:    */   public int bans;
/* 110:    */   public int cautions;
/* 111:    */   public int cfhs;
/* 112:    */   public int cfhs_abusive;
/* 113:    */   
/* 114:    */   public boolean equals(Object arg0)
/* 115:    */   {
/* 116:114 */     return ((PlayerData)arg0).userId == this.userId;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public int hashCode()
/* 120:    */   {
/* 121:119 */     return this.userId;
/* 122:    */   }
/* 123:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.PlayerData
 * JD-Core Version:    0.7.0.1
 */