/*  1:   */ package cappo.game.player;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ 
/*  5:   */ public class PlayerSecurity
/*  6:   */   extends PlayerModerator
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
/* 18:   */   public void setupLevelStuff()
/* 19:   */   {
/* 20:11 */     super.setupLevelStuff();
/* 21:   */     
/* 22:13 */     this.connection.giveBadge("ADM");
/* 23:   */   }
/* 24:   */   
/* 25:   */   public boolean allowRoomAlert()
/* 26:   */   {
/* 27:28 */     return allowRoomAlert;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public boolean allowPickFurni()
/* 31:   */   {
/* 32:33 */     return allowPickFurni;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public boolean allowEjectFurni()
/* 36:   */   {
/* 37:38 */     return allowEjectFurni;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public boolean allowRoomControl()
/* 41:   */   {
/* 42:43 */     return allowRoomControl;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public boolean allowModTools()
/* 46:   */   {
/* 47:48 */     return allowModTools;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public boolean allowBan()
/* 51:   */   {
/* 52:53 */     return allowBan;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public boolean allowGiveBadge()
/* 56:   */   {
/* 57:58 */     return allowGiveBadge;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public boolean allowHotelAlert()
/* 61:   */   {
/* 62:63 */     return allowHotelAlert;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public boolean allowGiveMoney()
/* 66:   */   {
/* 67:68 */     return allowGiveMoney;
/* 68:   */   }
/* 69:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.PlayerSecurity
 * JD-Core Version:    0.7.0.1
 */