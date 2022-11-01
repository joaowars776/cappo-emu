/*  1:   */ package cappo.protocol.messages.events.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.OutFitItem;
/*  7:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ 
/* 10:   */ public class SetOutfitNameParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection Main)
/* 14:   */   {
/* 15:18 */     Avatar avatar = Main.avatar;
/* 16:19 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 17:20 */       return;
/* 18:   */     }
/* 19:23 */     OutFitItem floorItem = (OutFitItem)avatar.room.getFloorItem(Main.currentPacket.readInt());
/* 20:24 */     if (floorItem == null) {
/* 21:25 */       return;
/* 22:   */     }
/* 23:28 */     floorItem.setName(Main.currentPacket.readString());
/* 24:29 */     avatar.room.floorItemUpdateNeeded(floorItem);
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.furniture.SetOutfitNameParser
 * JD-Core Version:    0.7.0.1
 */