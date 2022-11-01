/*  1:   */ package cappo.protocol.messages.events.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.collections.BaseItem;
/*  7:   */ import cappo.game.collections.BaseItem.FurniLogic;
/*  8:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  9:   */ import cappo.game.roomengine.entity.item.floor.OutFitItem;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ 
/* 13:   */ public class UpdateOutfitParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection Main)
/* 17:   */   {
/* 18:21 */     Avatar avatar = Main.avatar;
/* 19:22 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 20:23 */       return;
/* 21:   */     }
/* 22:26 */     FloorItem floorItem = avatar.room.getFloorItem(Main.currentPacket.readInt());
/* 23:27 */     if ((floorItem == null) || (floorItem.baseItem.logic != BaseItem.FurniLogic.MANNEQUIN)) {
/* 24:28 */       return;
/* 25:   */     }
/* 26:31 */     ((OutFitItem)floorItem).setLook(Main.getPlayerData());
/* 27:32 */     avatar.room.floorItemUpdateNeeded(floorItem);
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.furniture.UpdateOutfitParser
 * JD-Core Version:    0.7.0.1
 */