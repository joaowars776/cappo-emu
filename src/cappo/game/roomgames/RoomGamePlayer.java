/*  1:   */ package cappo.game.roomgames;
/*  2:   */ 
/*  3:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  4:   */ 
/*  5:   */ public class RoomGamePlayer
/*  6:   */ {
/*  7:   */   public static final short TEAM_1 = 1;
/*  8:   */   public static final short TEAM_2 = 2;
/*  9:   */   public static final short TEAM_3 = 3;
/* 10:   */   public static final short TEAM_4 = 4;
/* 11:   */   public short team;
/* 12:   */   public short score;
/* 13:   */   public Avatar avatarEntity;
/* 14:   */   
/* 15:   */   public RoomGamePlayer(short playerTeam, Avatar player)
/* 16:   */   {
/* 17:16 */     this.team = playerTeam;
/* 18:17 */     this.avatarEntity = player;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void removePlayer()
/* 22:   */   {
/* 23:21 */     this.avatarEntity.roomGamePlayer = null;
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomgames.RoomGamePlayer
 * JD-Core Version:    0.7.0.1
 */