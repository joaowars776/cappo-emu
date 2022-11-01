/*  1:   */ package cappo.game.player;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ 
/*  5:   */ public class PlayerBot
/*  6:   */   extends PlayerLince
/*  7:   */ {
/*  8:   */   public static boolean allowRoomAlert;
/*  9:   */   public static boolean allowPickFurni;
/* 10:   */   public static boolean allowEjectFurni;
/* 11:   */   public static boolean allowRoomControl;
/* 12:   */   public static boolean allowModTools;
/* 13:   */   public static boolean allowBan;
/* 14:   */   public static boolean allowGiveBadge;
/* 15:   */   public static boolean allowHotelAlert;
/* 16:   */   public static boolean allowGiveMoney;
/* 17:   */   
/* 18:   */   public boolean useChatBot()
/* 19:   */   {
/* 20: 6 */     return true;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean canKickMe(int rank)
/* 24:   */   {
/* 25:11 */     if (rank > this.staffLevel) {
/* 26:12 */       return true;
/* 27:   */     }
/* 28:14 */     return false;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void setupLevelStuff()
/* 32:   */   {
/* 33:19 */     super.setupLevelStuff();
/* 34:   */     
/* 35:21 */     this.connection.giveBadge("BOT");
/* 36:   */   }
/* 37:   */   
/* 38:   */   public boolean allowRoomAlert()
/* 39:   */   {
/* 40:36 */     return allowRoomAlert;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public boolean allowPickFurni()
/* 44:   */   {
/* 45:41 */     return allowPickFurni;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public boolean allowEjectFurni()
/* 49:   */   {
/* 50:46 */     return allowEjectFurni;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public boolean allowRoomControl()
/* 54:   */   {
/* 55:51 */     return allowRoomControl;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public boolean allowModTools()
/* 59:   */   {
/* 60:56 */     return allowModTools;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public boolean allowBan()
/* 64:   */   {
/* 65:61 */     return allowBan;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public boolean allowGiveBadge()
/* 69:   */   {
/* 70:66 */     return allowGiveBadge;
/* 71:   */   }
/* 72:   */   
/* 73:   */   public boolean allowHotelAlert()
/* 74:   */   {
/* 75:71 */     return allowHotelAlert;
/* 76:   */   }
/* 77:   */   
/* 78:   */   public boolean allowGiveMoney()
/* 79:   */   {
/* 80:76 */     return allowGiveMoney;
/* 81:   */   }
/* 82:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.PlayerBot
 * JD-Core Version:    0.7.0.1
 */