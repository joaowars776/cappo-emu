/*  1:   */ package cappo.protocol.messages.events.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ import cappo.protocol.messages.composers.inventory.purse.CreditBalanceComposer;
/* 12:   */ 
/* 13:   */ public class CreditFurniRedeemParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection Main)
/* 17:   */   {
/* 18:22 */     Avatar avatar = Main.avatar;
/* 19:23 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 20:24 */       return;
/* 21:   */     }
/* 22:27 */     RoomTask room = avatar.room;
/* 23:   */     
/* 24:29 */     GenericFloorItem floorItem = (GenericFloorItem)room.getFloorItem(Main.currentPacket.readInt());
/* 25:30 */     if (floorItem == null) {
/* 26:31 */       return;
/* 27:   */     }
/* 28:34 */     room.removeFloorItem(floorItem, Main.playerData.userId);
/* 29:35 */     floorItem.setMysqlState(4);
/* 30:   */     
/* 31:37 */     Main.credits += floorItem.getExtraParam();
/* 32:38 */     QueueWriter.write(Main.socket, CreditBalanceComposer.compose(Main.credits));
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.furniture.CreditFurniRedeemParser
 * JD-Core Version:    0.7.0.1
 */