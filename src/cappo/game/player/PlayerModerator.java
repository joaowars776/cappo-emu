/*  1:   */ package cappo.game.player;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ 
/*  5:   */ public class PlayerModerator
/*  6:   */   extends PlayerStaff
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
/* 20:19 */     super.setupLevelStuff();
/* 21:   */     
/* 22:21 */     this.connection.giveBadge("MOD");
/* 23:   */   }
/* 24:   */   
/* 25:   */   public boolean allowRoomAlert()
/* 26:   */   {
/* 27:37 */     return allowRoomAlert;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public boolean allowPickFurni()
/* 31:   */   {
/* 32:42 */     return allowPickFurni;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public boolean allowEjectFurni()
/* 36:   */   {
/* 37:47 */     return allowEjectFurni;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public boolean allowRoomControl()
/* 41:   */   {
/* 42:52 */     return allowRoomControl;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public boolean allowModTools()
/* 46:   */   {
/* 47:57 */     return allowModTools;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public boolean allowBan()
/* 51:   */   {
/* 52:62 */     return allowBan;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public boolean allowGiveBadge()
/* 56:   */   {
/* 57:67 */     return allowGiveBadge;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public boolean allowHotelAlert()
/* 61:   */   {
/* 62:72 */     return allowHotelAlert;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public boolean allowGiveMoney()
/* 66:   */   {
/* 67:77 */     return allowGiveMoney;
/* 68:   */   }
/* 69:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.PlayerModerator
 * JD-Core Version:    0.7.0.1
 */