/*   1:    */ package cappo.game.roomengine.roomevents;
/*   2:    */ 
/*   3:    */ import cappo.engine.threadpools.RoomTask;
/*   4:    */ import cappo.game.collections.BaseItem;
/*   5:    */ import cappo.game.games.snowwar.Direction8;
/*   6:    */ import cappo.game.rollers.RollerMoveDataEntity;
/*   7:    */ import cappo.game.rollers.RollerMoveDataObject;
/*   8:    */ import cappo.game.roomengine.SquareFlagManager;
/*   9:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  10:    */ import cappo.game.roomengine.entity.item.floor.RollerItem;
/*  11:    */ import cappo.game.roomengine.entity.item.floor.RoomFloorItemData.AffectedTile;
/*  12:    */ import cappo.game.roomengine.entity.live.LiveEntity;
/*  13:    */ import cappo.game.roomengine.gamemap.GameMapBase;
/*  14:    */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/*  15:    */ import cappo.protocol.messages.composers.room.engine.SlideObjectBundleComposer;
/*  16:    */ import java.util.ArrayList;
/*  17:    */ import java.util.List;
/*  18:    */ import java.util.Map;
/*  19:    */ 
/*  20:    */ public class RollerEvent
/*  21:    */ {
/*  22:    */   public static List<FloorItem> cleanRollerTile(RoomTask room, int xy, float z)
/*  23:    */   {
/*  24: 21 */     List<FloorItem> modified = new ArrayList();
/*  25: 22 */     Map<Integer, FloorItem> squareItems = (Map)room.mapFloorItems.get(Integer.valueOf(xy));
/*  26: 23 */     if (squareItems != null) {
/*  27: 24 */       for (FloorItem floorItem : squareItems.values()) {
/*  28: 25 */         if (floorItem.getZ() > z)
/*  29:    */         {
/*  30: 26 */           modified.add(floorItem);
/*  31: 29 */           for (RoomFloorItemData.AffectedTile Tile : floorItem.getAffectedTiles()) {
/*  32: 30 */             if (room.validTile(Tile.xy)) {
/*  33: 31 */               room.generateSquare(Tile.xy, floorItem, false, false);
/*  34:    */             }
/*  35:    */           }
/*  36:    */         }
/*  37:    */       }
/*  38:    */     }
/*  39: 37 */     return modified;
/*  40:    */   }
/*  41:    */   
/*  42:    */   private static void execRoller(RoomTask room, RollerItem roller, int mainId)
/*  43:    */   {
/*  44: 41 */     if (roller.getRoomId() != room.roomId) {
/*  45: 42 */       return;
/*  46:    */     }
/*  47: 45 */     int fromX = roller.getX();
/*  48: 46 */     int fromY = roller.getY();
/*  49: 47 */     int nextX = fromX + roller.getDir().getDiffX();
/*  50: 48 */     int nextY = fromY + roller.getDir().getDiffY();
/*  51: 49 */     int nextXY = nextX + nextY * room.model.widthX;
/*  52: 51 */     if (!room.validTile(nextXY)) {
/*  53: 52 */       return;
/*  54:    */     }
/*  55: 55 */     roller.status = 1;
/*  56:    */     
/*  57:    */ 
/*  58:    */ 
/*  59: 59 */     List<FloorItem> modified = cleanRollerTile(room, roller.getXy(), roller.getZ());
/*  60:    */     RollerItem rl;
/*  61: 61 */     if (room.squareFlag.eventHave(nextXY, 2))
/*  62:    */     {
/*  63: 62 */       Map<Integer, FloorItem> squareItems = (Map)room.mapFloorItems.get(Integer.valueOf(nextXY));
/*  64: 63 */       for (FloorItem r : squareItems.values()) {
/*  65: 64 */         if (r.baseItem.interactorType == Interactor.InteractorType.roller)
/*  66:    */         {
/*  67: 65 */           rl = (RollerItem)r;
/*  68: 66 */           if (rl.status == 0)
/*  69:    */           {
/*  70: 67 */             execRoller(room, rl, mainId);
/*  71: 68 */             break;
/*  72:    */           }
/*  73: 68 */           if (rl.status != 1) {
/*  74:    */             break;
/*  75:    */           }
/*  76: 72 */           break;
/*  77:    */         }
/*  78:    */       }
/*  79:    */     }
/*  80: 77 */     float delta = ((Float)room.squareAbsoluteHeight.get(Integer.valueOf(nextXY))).floatValue() - (roller.getZ() + roller.baseItem.Height);
/*  81:    */     
/*  82: 79 */     List<RollerMoveDataObject> stackedItems = new ArrayList();
/*  83: 81 */     for (FloorItem stacked : modified)
/*  84:    */     {
/*  85: 82 */       List<RoomFloorItemData.AffectedTile> Points = stacked.getAffectedTiles(nextXY, stacked.getDir());
/*  86: 83 */       if (!room.canPlace(stacked, Points, nextXY, false)) {
/*  87: 86 */         for (RoomFloorItemData.AffectedTile Tile : stacked.getAffectedTiles()) {
/*  88: 87 */           if (room.validTile(Tile.xy)) {
/*  89: 88 */             room.generateSquare(Tile.xy, stacked, true, true);
/*  90:    */           }
/*  91:    */         }
/*  92:    */       } else {
/*  93: 95 */         stackedItems.add(new RollerMoveDataObject(stacked));
/*  94:    */       }
/*  95:    */     }
/*  96: 98 */     for (RollerMoveDataObject roolerMove : stackedItems)
/*  97:    */     {
/*  98: 99 */       FloorItem stacked = roolerMove.item;
/*  99:100 */       List<RoomFloorItemData.AffectedTile> Points = stacked.getAffectedTiles(nextXY, stacked.getDir());
/* 100:    */       
/* 101:102 */       room.moveObject2(stacked, Points, nextXY, stacked.getDir());
/* 102:    */       
/* 103:104 */       stacked.setMysqlState(1);
/* 104:105 */       stacked.finishPlace(null, Points, false);
/* 105:    */     }
/* 106:109 */     RollerMoveDataEntity moveDataEntity = null;
/* 107:110 */     LiveEntity ent = null;
/* 108:112 */     if (((room.squareFlag.have(nextXY, 4)) || (room.squareFlag.have(nextXY, 8))) && 
/* 109:113 */       (!room.squareHasUsers(nextXY)))
/* 110:    */     {
/* 111:114 */       Map<Short, LiveEntity> users = (Map)room.usersMatrix.get(Integer.valueOf(roller.getXy()));
/* 112:115 */       if (users != null) {
/* 113:116 */         for (LiveEntity entity : users.values()) {
/* 114:117 */           if (!entity.evtWalk.isWalking)
/* 115:    */           {
/* 116:118 */             ent = entity;
/* 117:119 */             break;
/* 118:    */           }
/* 119:    */         }
/* 120:    */       }
/* 121:124 */       if (ent != null)
/* 122:    */       {
/* 123:125 */         moveDataEntity = new RollerMoveDataEntity(ent, 2);
/* 124:    */         
/* 125:127 */         room.entityWalk(ent.xy, ent, false);
/* 126:    */         
/* 127:    */ 
/* 128:    */ 
/* 129:    */ 
/* 130:    */ 
/* 131:    */ 
/* 132:    */ 
/* 133:135 */         ent.SetPos(nextX, nextY, ent.z + delta);
/* 134:    */         
/* 135:137 */         room.entityWalk(ent.xy, ent, true);
/* 136:    */       }
/* 137:    */     }
/* 138:142 */     roller.status = 2;
/* 139:144 */     if ((stackedItems.isEmpty()) && (ent == null)) {
/* 140:145 */       return;
/* 141:    */     }
/* 142:148 */     room.sendMessage(SlideObjectBundleComposer.compose(roller, nextX, nextY, stackedItems, moveDataEntity));
/* 143:    */   }
/* 144:    */   
/* 145:    */   public static void run(RoomTask room, Map<Integer, RollerItem> rollers)
/* 146:    */   {
/* 147:152 */     for (RollerItem roller : rollers.values()) {
/* 148:153 */       if (roller.status == 0) {
/* 149:154 */         execRoller(room, roller, roller.itemId);
/* 150:    */       }
/* 151:    */     }
/* 152:158 */     for (RollerItem roller : rollers.values()) {
/* 153:159 */       roller.status = 0;
/* 154:    */     }
/* 155:    */   }
/* 156:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomevents.RollerEvent
 * JD-Core Version:    0.7.0.1
 */