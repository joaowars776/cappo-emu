/*  1:   */ package cappo.protocol.messages.events.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.collections.BaseItem;
/*  7:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  8:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  9:   */ import cappo.game.roomengine.itemInteractor.Interactor;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ 
/* 12:   */ public class UseWallItemParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:18 */     Avatar avatar = Main.avatar;
/* 18:19 */     if (avatar == null) {
/* 19:20 */       return;
/* 20:   */     }
/* 21:23 */     GenericWallItem Item = (GenericWallItem)avatar.room.getWallItem(Main.currentPacket.readInt());
/* 22:24 */     if (Item == null) {
/* 23:25 */       return;
/* 24:   */     }
/* 25:28 */     Item.baseItem.interactor.OnTriggerWall(avatar.room, Main, Item, Main.currentPacket.readInt(), (avatar.controllerLevel == 1) || (avatar.controllerLevel >= 4));
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.UseWallItemParser
 * JD-Core Version:    0.7.0.1
 */