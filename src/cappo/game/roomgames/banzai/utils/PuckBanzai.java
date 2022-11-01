/*  1:   */ package cappo.game.roomgames.banzai.utils;
/*  2:   */ 
/*  3:   */ import cappo.engine.logging.Log;
/*  4:   */ import cappo.engine.threadpools.ItemTask;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.games.snowwar.Direction8;
/*  7:   */ import cappo.game.roomengine.RoomData;
/*  8:   */ import cappo.game.roomengine.SquareFlagManager;
/*  9:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.game.roomengine.gamemap.GameMapBase;
/* 12:   */ import cappo.game.roomgames.RoomGamePlayer;
/* 13:   */ import cappo.protocol.messages.composers.room.engine.ObjectUpdateComposer;
/* 14:   */ import java.util.Map;
/* 15:   */ 
/* 16:   */ public class PuckBanzai
/* 17:   */   extends ItemTask
/* 18:   */ {
/* 19:   */   private Direction8 rot;
/* 20:   */   private int type;
/* 21:   */   private final GenericFloorItem puckItem;
/* 22:   */   
/* 23:   */   public PuckBanzai(GenericFloorItem item, Avatar avatar, boolean lastSteep)
/* 24:   */   {
/* 25:19 */     super(item);
/* 26:   */     
/* 27:21 */     this.rot = Direction8.getRot(avatar.x, avatar.y, item.getX(), item.getY());
/* 28:22 */     this.puckItem = item;
/* 29:23 */     if (avatar.roomGamePlayer != null) {
/* 30:24 */       this.type = avatar.roomGamePlayer.team;
/* 31:   */     }
/* 32:26 */     this.puckItem.setIntData(this.type + (lastSteep ? 50 : 0));
/* 33:   */   }
/* 34:   */   
/* 35:   */   private boolean isTileBocked(int xy)
/* 36:   */   {
/* 37:30 */     RoomTask room = this.item.getRoom();
/* 38:32 */     if (!room.validTile(xy)) {
/* 39:33 */       return true;
/* 40:   */     }
/* 41:36 */     if (!room.squareFlag.have(xy, 4)) {
/* 42:37 */       return true;
/* 43:   */     }
/* 44:40 */     return room.squareHasUsers(xy);
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void run()
/* 48:   */   {
/* 49:   */     try
/* 50:   */     {
/* 51:46 */       int nextX = this.puckItem.getX() + this.rot.getDiffX();
/* 52:47 */       int nextY = this.puckItem.getY() + this.rot.getDiffY();
/* 53:   */       
/* 54:49 */       int nextXY = nextX + nextY * this.item.getRoom().model.widthX;
/* 55:51 */       if (isTileBocked(nextXY))
/* 56:   */       {
/* 57:52 */         this.rot = this.rot.rotateDirection180Degrees();
/* 58:53 */         nextX = this.puckItem.getX() + this.rot.getDiffX();
/* 59:54 */         nextY = this.puckItem.getY() + this.rot.getDiffY();
/* 60:   */         
/* 61:56 */         nextXY = nextX + nextY * this.item.getRoom().model.widthX;
/* 62:   */       }
/* 63:60 */       this.item.getRoom().generateSquare(this.puckItem.getXy(), this.puckItem, false, false);
/* 64:61 */       if ((this.type > 0) && 
/* 65:62 */         (this.item.getRoom().roomData.haveFlag(64)) && 
/* 66:63 */         (this.item.getRoom().squareFlag.eventHave(nextXY, 8)))
/* 67:   */       {
/* 68:64 */         GenericFloorItem top = (GenericFloorItem)this.item.getRoom().topFloorItems.get(Integer.valueOf(nextXY));
/* 69:65 */         if (top.getIntData() != 3 * this.type + 2) {
/* 70:66 */           TileBanzaiWork.doWork(top, this.type, this.item.getRoom());
/* 71:   */         }
/* 72:   */       }
/* 73:72 */       this.puckItem.setPosition(nextX, nextY, nextXY);
/* 74:73 */       this.item.getRoom().generateSquare(this.puckItem.getXy(), this.puckItem, true, false);
/* 75:75 */       if (this.puckItem.getIntData() > 49) {
/* 76:76 */         ItemTask.addTask(this, 100, 0);
/* 77:78 */       } else if (this.puckItem.getIntData() > 39) {
/* 78:79 */         ItemTask.addTask(this, 120, 0);
/* 79:80 */       } else if (this.puckItem.getIntData() > 29) {
/* 80:81 */         ItemTask.addTask(this, 160, 0);
/* 81:82 */       } else if (this.puckItem.getIntData() > 19) {
/* 82:83 */         ItemTask.addTask(this, 250, 0);
/* 83:84 */       } else if (this.puckItem.getIntData() > 9) {
/* 84:85 */         ItemTask.addTask(this, 500, 0);
/* 85:   */       } else {
/* 86:87 */         this.puckItem.incIntData(10);
/* 87:   */       }
/* 88:90 */       this.item.getRoom().sendMessage(ObjectUpdateComposer.compose(this.puckItem));
/* 89:91 */       this.puckItem.decIntData(10);
/* 90:   */     }
/* 91:   */     catch (Exception ex)
/* 92:   */     {
/* 93:94 */       Log.printException("", ex);
/* 94:   */     }
/* 95:   */   }
/* 96:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomgames.banzai.utils.PuckBanzai
 * JD-Core Version:    0.7.0.1
 */