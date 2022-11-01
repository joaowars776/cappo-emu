/*  1:   */ package cappo.game.roomengine.settings;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ import cappo.game.player.PlayerData;
/*  5:   */ import cappo.game.roomengine.RoomData;
/*  6:   */ import java.util.Map;
/*  7:   */ 
/*  8:   */ public class ControllerLevels
/*  9:   */ {
/* 10:   */   public static final int LEVEL_NONE = 0;
/* 11:   */   public static final int LEVEL_RIGHTS = 1;
/* 12:   */   public static final int LEVEL_GROUP_MEMBER = 2;
/* 13:   */   public static final int LEVEL_GROUP_ADMIN = 3;
/* 14:   */   public static final int LEVEL_ROOM_OWNER = 4;
/* 15:   */   public static final int LEVEL_STAFF = 5;
/* 16:   */   
/* 17:   */   public static int getLevel(PlayerData User, RoomData roomData, RoomTask room)
/* 18:   */   {
/* 19:22 */     if (User.allowRoomControl()) {
/* 20:23 */       return 5;
/* 21:   */     }
/* 22:26 */     if (roomData.roomOwnerId > 0)
/* 23:   */     {
/* 24:27 */       if (User.userId == roomData.roomOwnerId) {
/* 25:28 */         return 4;
/* 26:   */       }
/* 27:   */     }
/* 28:32 */     else if (User.userName.equals(roomData.roomOwnerName)) {
/* 29:33 */       return 4;
/* 30:   */     }
/* 31:37 */     if ((room != null) && 
/* 32:38 */       (room.usersWithRights.containsKey(Integer.valueOf(User.userId)))) {
/* 33:39 */       return 1;
/* 34:   */     }
/* 35:45 */     return 0;
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.settings.ControllerLevels
 * JD-Core Version:    0.7.0.1
 */