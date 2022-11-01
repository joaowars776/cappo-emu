/*  1:   */ package cappo.game.player;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.moderation.StaffManager;
/*  6:   */ import cappo.protocol.messages.composers.moderation.ModeratorInitComposer;
/*  7:   */ 
/*  8:   */ public class PlayerStaff
/*  9:   */   extends PlayerData
/* 10:   */ {
/* 11:   */   public static boolean allowRoomAlert;
/* 12:   */   public static boolean allowPickFurni;
/* 13:   */   public static boolean allowEjectFurni;
/* 14:   */   public static boolean allowRoomControl;
/* 15:   */   public static boolean allowModTools;
/* 16:   */   public static boolean allowBan;
/* 17:   */   public static boolean allowGiveBadge;
/* 18:   */   public static boolean allowHotelAlert;
/* 19:   */   public static boolean allowGiveMoney;
/* 20:   */   
/* 21:   */   public boolean canKickMe(int rank)
/* 22:   */   {
/* 23:11 */     if (rank > this.staffLevel) {
/* 24:12 */       return true;
/* 25:   */     }
/* 26:14 */     return false;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setupLevelStuff()
/* 30:   */   {
/* 31:19 */     super.setupLevelStuff();
/* 32:   */     
/* 33:21 */     this.connection.giveBadge("HBA");
/* 34:23 */     if (this.connection.playerData.allowModTools())
/* 35:   */     {
/* 36:24 */       QueueWriter.writeAndFlush(this.connection.socket, ModeratorInitComposer.compose());
/* 37:25 */       StaffManager.addStaff(this.userId, this.connection);
/* 38:   */     }
/* 39:   */   }
/* 40:   */   
/* 41:   */   public boolean allowRoomAlert()
/* 42:   */   {
/* 43:41 */     return allowRoomAlert;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public boolean allowPickFurni()
/* 47:   */   {
/* 48:46 */     return allowPickFurni;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public boolean allowEjectFurni()
/* 52:   */   {
/* 53:51 */     return allowEjectFurni;
/* 54:   */   }
/* 55:   */   
/* 56:   */   public boolean allowRoomControl()
/* 57:   */   {
/* 58:56 */     return allowRoomControl;
/* 59:   */   }
/* 60:   */   
/* 61:   */   public boolean allowModTools()
/* 62:   */   {
/* 63:61 */     return allowModTools;
/* 64:   */   }
/* 65:   */   
/* 66:   */   public boolean allowBan()
/* 67:   */   {
/* 68:66 */     return allowBan;
/* 69:   */   }
/* 70:   */   
/* 71:   */   public boolean allowGiveBadge()
/* 72:   */   {
/* 73:71 */     return allowGiveBadge;
/* 74:   */   }
/* 75:   */   
/* 76:   */   public boolean allowHotelAlert()
/* 77:   */   {
/* 78:76 */     return allowHotelAlert;
/* 79:   */   }
/* 80:   */   
/* 81:   */   public boolean allowGiveMoney()
/* 82:   */   {
/* 83:81 */     return allowGiveMoney;
/* 84:   */   }
/* 85:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.PlayerStaff
 * JD-Core Version:    0.7.0.1
 */