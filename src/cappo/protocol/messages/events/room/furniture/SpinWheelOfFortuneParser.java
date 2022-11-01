/*  1:   */ package cappo.protocol.messages.events.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.collections.BaseItem;
/*  7:   */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*  8:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/* 11:   */ import cappo.game.roomengine.roomevents.HabboWheel_RUN;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ 
/* 14:   */ public class SpinWheelOfFortuneParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:21 */     Avatar avatar = Main.avatar;
/* 20:22 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 21:23 */       return;
/* 22:   */     }
/* 23:26 */     RoomTask room = avatar.room;
/* 24:   */     
/* 25:28 */     GenericWallItem item = (GenericWallItem)room.getWallItem(Main.currentPacket.readInt());
/* 26:29 */     if ((item == null) || (item.baseItem.interactorType != Interactor.InteractorType.habbowheel)) {
/* 27:30 */       return;
/* 28:   */     }
/* 29:33 */     if (item.extraData.equals("-1")) {
/* 30:34 */       return;
/* 31:   */     }
/* 32:37 */     item.extraData.setExtraData("-1");
/* 33:38 */     room.wallItemUpdateNeeded(item);
/* 34:39 */     room.addItemEvent(new HabboWheel_RUN(item), 5);
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.furniture.SpinWheelOfFortuneParser
 * JD-Core Version:    0.7.0.1
 */