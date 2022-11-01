/*  1:   */ package cappo.game.roomengine.itemInteractor;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.collections.BaseItem;
/*  6:   */ import cappo.game.collections.BaseItem.ItemType;
/*  7:   */ import cappo.game.roomengine.RoomData;
/*  8:   */ import cappo.game.roomengine.SquareFlagManager;
/*  9:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/* 10:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/* 11:   */ import cappo.game.roomengine.entity.item.floor.wired.trigger.WiredTriggerBase;
/* 12:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/* 13:   */ import cappo.game.roomengine.roomevents.Item_TIMER;
/* 14:   */ import cappo.game.roomengine.wired.WiredManager;
/* 15:   */ import java.util.Map;
/* 16:   */ 
/* 17:   */ public class InteractorTimer
/* 18:   */   extends Interactor
/* 19:   */ {
/* 20:   */   public void OnPlace(RoomTask room, Connection User, GenericFloorItem item)
/* 21:   */   {
/* 22:23 */     room.ScorePoints_R = 0;
/* 23:24 */     room.ScorePoints_G = 0;
/* 24:25 */     room.ScorePoints_B = 0;
/* 25:26 */     room.ScorePoints_Y = 0;
/* 26:27 */     item.setIntData(60);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void OnTriggerFloor(RoomTask room, Connection User, FloorItem fitem, int Request, boolean UserHasRights)
/* 30:   */   {
/* 31:32 */     if (!UserHasRights) {
/* 32:33 */       return;
/* 33:   */     }
/* 34:36 */     GenericFloorItem Item = (GenericFloorItem)fitem;
/* 35:38 */     if (Request == 1)
/* 36:   */     {
/* 37:39 */       if (room.roomData.haveFlag(64))
/* 38:   */       {
/* 39:41 */         room.roomData.setFlag(64, false);
/* 40:   */       }
/* 41:   */       else
/* 42:   */       {
/* 43:43 */         if (room.ScorePoints_R == -1)
/* 44:   */         {
/* 45:46 */           for (FloorItem Item_ : room.FloorItems.values()) {
/* 46:47 */             if (Item_.baseItem.interactorType == Interactor.InteractorType.banzaifloor)
/* 47:   */             {
/* 48:48 */               GenericFloorItem item_ = (GenericFloorItem)Item_;
/* 49:49 */               item_.setIntData(1);
/* 50:50 */               room.floorItemUpdateNeeded(item_);
/* 51:   */             }
/* 52:51 */             else if (Item_.baseItem.itemType == BaseItem.ItemType.ROOMGAME_GATE)
/* 53:   */             {
/* 54:52 */               room.squareFlag.SetFlag(Item_.getXy(), 4, false);
/* 55:   */             }
/* 56:   */           }
/* 57:56 */           WiredTriggerBase.launchTriggers(room.wiredManager.triggersGameStarts, null, null);
/* 58:   */         }
/* 59:60 */         room.roomData.setFlag(64, true);
/* 60:61 */         room.addItemEvent(new Item_TIMER(Item), 0);
/* 61:   */       }
/* 62:   */     }
/* 63:   */     else
/* 64:   */     {
/* 65:64 */       if (room.roomData.haveFlag(64)) {
/* 66:65 */         return;
/* 67:   */       }
/* 68:72 */       for (FloorItem Item_ : room.FloorItems.values()) {
/* 69:73 */         if (Item_.baseItem.interactorType == Interactor.InteractorType.banzaifloor)
/* 70:   */         {
/* 71:74 */           GenericFloorItem item_ = (GenericFloorItem)Item_;
/* 72:75 */           item_.setIntData(0);
/* 73:76 */           room.floorItemUpdateNeeded(item_);
/* 74:   */         }
/* 75:77 */         else if (Item_.baseItem.itemType == BaseItem.ItemType.ROOMGAME_GATE)
/* 76:   */         {
/* 77:78 */           room.squareFlag.SetFlag(Item_.getXy(), 4, true);
/* 78:   */         }
/* 79:   */       }
/* 80:82 */       room.ScorePoints_R = -1;
/* 81:83 */       room.ScorePoints_G = -1;
/* 82:84 */       room.ScorePoints_B = -1;
/* 83:85 */       room.ScorePoints_Y = -1;
/* 84:87 */       if ((Item.getIntData() >= 600) || (Item.getIntData() < 1))
/* 85:   */       {
/* 86:88 */         Item.setIntData(60);
/* 87:   */       }
/* 88:   */       else
/* 89:   */       {
/* 90:90 */         Item.decIntData(Item.getIntData() % 60);
/* 91:91 */         Item.incIntData(60);
/* 92:   */       }
/* 93:94 */       room.floorItemUpdateNeeded(Item);
/* 94:   */     }
/* 95:   */   }
/* 96:   */   
/* 97:   */   public void OnTriggerWall(RoomTask room, Connection User, GenericWallItem Item, int Request, boolean UserHasRights) {}
/* 98:   */   
/* 99:   */   public void OnPickUp(RoomTask room, Connection User, GenericFloorItem Item) {}
/* :0:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.itemInteractor.InteractorTimer
 * JD-Core Version:    0.7.0.1
 */