/*   1:    */ package cappo.game.roomgames.football.utils;
/*   2:    */ 
/*   3:    */ import cappo.engine.logging.Log;
/*   4:    */ import cappo.engine.threadpools.ItemTask;
/*   5:    */ import cappo.engine.threadpools.RoomTask;
/*   6:    */ import cappo.game.collections.BaseItem;
/*   7:    */ import cappo.game.games.snowwar.Direction8;
/*   8:    */ import cappo.game.roomengine.SquareFlagManager;
/*   9:    */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  10:    */ import cappo.game.roomengine.entity.live.Avatar;
/*  11:    */ import cappo.game.roomengine.gamemap.GameMapBase;
/*  12:    */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/*  13:    */ import cappo.protocol.messages.composers.room.action.AvatarExpressionComposer;
/*  14:    */ import cappo.protocol.messages.composers.room.engine.ObjectUpdateComposer;
/*  15:    */ 
/*  16:    */ public class BallonFootBall
/*  17:    */   extends ItemTask
/*  18:    */ {
/*  19:    */   private Direction8 rot;
/*  20:    */   private final Avatar avatar;
/*  21:    */   private final GenericFloorItem ballItem;
/*  22:    */   
/*  23:    */   public BallonFootBall(GenericFloorItem item, Avatar avt, boolean lastSteep)
/*  24:    */   {
/*  25: 20 */     super(item);
/*  26:    */     
/*  27: 22 */     this.rot = Direction8.getRot(avt.x, avt.y, item.getX(), item.getY());
/*  28: 23 */     this.ballItem = item;
/*  29: 24 */     this.avatar = avt;
/*  30: 25 */     this.ballItem.setIntData(lastSteep ? 55 : 0);
/*  31:    */   }
/*  32:    */   
/*  33:    */   private boolean isTileBocked(int xy)
/*  34:    */   {
/*  35: 29 */     RoomTask room = this.item.getRoom();
/*  36: 31 */     if (!room.validTile(xy)) {
/*  37: 32 */       return true;
/*  38:    */     }
/*  39: 35 */     if (!room.squareFlag.have(xy, 4)) {
/*  40: 36 */       return true;
/*  41:    */     }
/*  42: 39 */     return room.squareHasUsers(xy);
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void run()
/*  46:    */   {
/*  47:    */     try
/*  48:    */     {
/*  49: 45 */       boolean goal = false;
/*  50:    */       
/*  51: 47 */       RoomTask room = this.item.getRoom();
/*  52:    */       
/*  53: 49 */       int nextX = this.ballItem.getX() + this.rot.getDiffX();
/*  54: 50 */       int nextY = this.ballItem.getY() + this.rot.getDiffY();
/*  55:    */       
/*  56: 52 */       int nextXY = nextX + nextY * room.model.widthX;
/*  57: 54 */       if (isTileBocked(nextXY))
/*  58:    */       {
/*  59: 55 */         this.rot = this.rot.rotateDirection180Degrees();
/*  60: 56 */         nextX = this.ballItem.getX() + this.rot.getDiffX();
/*  61: 57 */         nextY = this.ballItem.getY() + this.rot.getDiffY();
/*  62:    */         
/*  63: 59 */         nextXY = nextX + nextY * room.model.widthX;
/*  64:    */       }
/*  65: 62 */       room.generateSquare(this.ballItem.getXy(), this.ballItem, false, false);
/*  66: 63 */       if (room.squareFlag.eventHave(nextXY, 64))
/*  67:    */       {
/*  68: 64 */         goal = true;
/*  69: 66 */         if (this.item.baseItem.interactorType == Interactor.InteractorType.banzaiscoreblue)
/*  70:    */         {
/*  71: 67 */           room.ScorePoints_B += 1;
/*  72: 68 */           for (GenericFloorItem item : room.roomGamesScorersBLUE)
/*  73:    */           {
/*  74: 69 */             item.setIntData(room.ScorePoints_B);
/*  75: 70 */             room.floorItemUpdateNeeded(item);
/*  76:    */           }
/*  77:    */         }
/*  78: 72 */         else if (this.item.baseItem.interactorType == Interactor.InteractorType.banzaiscoregreen)
/*  79:    */         {
/*  80: 73 */           room.ScorePoints_G += 1;
/*  81: 74 */           for (GenericFloorItem item : room.roomGamesScorersGREEN)
/*  82:    */           {
/*  83: 75 */             item.setIntData(room.ScorePoints_G);
/*  84: 76 */             room.floorItemUpdateNeeded(item);
/*  85:    */           }
/*  86:    */         }
/*  87: 78 */         else if (this.item.baseItem.interactorType == Interactor.InteractorType.banzaiscorered)
/*  88:    */         {
/*  89: 79 */           room.ScorePoints_R += 1;
/*  90: 80 */           for (GenericFloorItem item : room.roomGamesScorersRED)
/*  91:    */           {
/*  92: 81 */             item.setIntData(room.ScorePoints_R);
/*  93: 82 */             room.floorItemUpdateNeeded(item);
/*  94:    */           }
/*  95:    */         }
/*  96: 84 */         else if (this.item.baseItem.interactorType == Interactor.InteractorType.banzaiscoreyellow)
/*  97:    */         {
/*  98: 85 */           room.ScorePoints_Y += 1;
/*  99: 86 */           for (GenericFloorItem item : room.roomGamesScorersYELLOW)
/* 100:    */           {
/* 101: 87 */             item.setIntData(room.ScorePoints_Y);
/* 102: 88 */             room.floorItemUpdateNeeded(item);
/* 103:    */           }
/* 104:    */         }
/* 105: 92 */         room.sendMessage(AvatarExpressionComposer.compose(this.avatar.virtualId, 7));
/* 106:    */       }
/* 107: 95 */       this.ballItem.setPosition(nextX + nextY * room.model.widthX);
/* 108: 96 */       room.generateSquare(this.ballItem.getXy(), this.ballItem, true, false);
/* 109: 98 */       if (goal)
/* 110:    */       {
/* 111: 99 */         this.ballItem.setIntData(11);
/* 112:100 */         room.sendMessage(ObjectUpdateComposer.compose(this.ballItem));
/* 113:101 */         return;
/* 114:    */       }
/* 115:104 */       if (this.ballItem.getIntData() == 55) {
/* 116:105 */         ItemTask.addTask(this, 100, 0);
/* 117:107 */       } else if (this.ballItem.getIntData() == 44) {
/* 118:108 */         ItemTask.addTask(this, 100, 0);
/* 119:109 */       } else if (this.ballItem.getIntData() == 33) {
/* 120:110 */         ItemTask.addTask(this, 200, 0);
/* 121:111 */       } else if (this.ballItem.getIntData() == 22) {
/* 122:112 */         ItemTask.addTask(this, 250, 0);
/* 123:113 */       } else if (this.ballItem.getIntData() == 11) {
/* 124:114 */         ItemTask.addTask(this, 500, 0);
/* 125:    */       } else {
/* 126:116 */         this.ballItem.setIntData(11);
/* 127:    */       }
/* 128:120 */       room.sendMessage(ObjectUpdateComposer.compose(this.ballItem));
/* 129:    */       
/* 130:    */ 
/* 131:123 */       this.ballItem.decIntData(11);
/* 132:    */     }
/* 133:    */     catch (Exception ex)
/* 134:    */     {
/* 135:126 */       Log.printException("", ex);
/* 136:    */     }
/* 137:    */   }
/* 138:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomgames.football.utils.BallonFootBall
 * JD-Core Version:    0.7.0.1
 */