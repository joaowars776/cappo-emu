/*  1:   */ package cappo.game.player;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ 
/*  5:   */ public class PlayerDeveloper
/*  6:   */   extends PlayerManager
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
/* 18:   */   public boolean allowHotelImageAlert()
/* 19:   */   {
/* 20:11 */     return true;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean allowDataReload()
/* 24:   */   {
/* 25:16 */     return true;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void setupLevelStuff()
/* 29:   */   {
/* 30:21 */     super.setupLevelStuff();
/* 31:   */     
/* 32:23 */     this.connection.giveBadge("PROG");
/* 33:   */   }
/* 34:   */   
/* 35:   */   public boolean allowRoomAlert()
/* 36:   */   {
/* 37:39 */     return allowRoomAlert;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public boolean allowPickFurni()
/* 41:   */   {
/* 42:44 */     return allowPickFurni;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public boolean allowEjectFurni()
/* 46:   */   {
/* 47:49 */     return allowEjectFurni;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public boolean allowRoomControl()
/* 51:   */   {
/* 52:54 */     return allowRoomControl;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public boolean allowModTools()
/* 56:   */   {
/* 57:59 */     return allowModTools;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public boolean allowBan()
/* 61:   */   {
/* 62:64 */     return allowBan;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public boolean allowGiveBadge()
/* 66:   */   {
/* 67:69 */     return allowGiveBadge;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public boolean allowHotelAlert()
/* 71:   */   {
/* 72:74 */     return allowHotelAlert;
/* 73:   */   }
/* 74:   */   
/* 75:   */   public boolean allowGiveMoney()
/* 76:   */   {
/* 77:79 */     return allowGiveMoney;
/* 78:   */   }
/* 79:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.PlayerDeveloper
 * JD-Core Version:    0.7.0.1
 */