/*  1:   */ package cappo.game.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.game.roomengine.entity.item.Item;
/*  4:   */ import cappo.game.roomengine.entity.item.RoomItemData;
/*  5:   */ import cappo.game.roomengine.entity.item.extradata.StringStuffData;
/*  6:   */ 
/*  7:   */ public class GamefuseObject
/*  8:   */   extends Item
/*  9:   */ {
/* 10:   */   public int X;
/* 11:   */   public int Y;
/* 12:   */   public int Rot;
/* 13:   */   public int Z;
/* 14:   */   
/* 15:   */   public GamefuseObject()
/* 16:   */   {
/* 17:20 */     this.extraData = new StringStuffData(null);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void setRoomData(RoomItemData data) {}
/* 21:   */   
/* 22:   */   public void cleanRoomData() {}
/* 23:   */   
/* 24:   */   public void roomDataSave(boolean moved) {}
/* 25:   */   
/* 26:   */   public int getRoomId()
/* 27:   */   {
/* 28:40 */     return 0;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void insertItem()
/* 32:   */     throws Exception
/* 33:   */   {}
/* 34:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.GamefuseObject
 * JD-Core Version:    0.7.0.1
 */