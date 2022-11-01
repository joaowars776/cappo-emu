/*  1:   */ package cappo.protocol.messages.events.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.collections.BaseItem;
/*  8:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.room.furniture.RequestSpamWallPostItComposer;
/* 13:   */ 
/* 14:   */ public class OpenPostItParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:20 */     Avatar avatar = Main.avatar;
/* 20:21 */     if (avatar == null) {
/* 21:22 */       return;
/* 22:   */     }
/* 23:25 */     GenericWallItem item = (GenericWallItem)avatar.room.getWallItem(Main.currentPacket.readInt());
/* 24:26 */     if ((item == null) || (item.baseItem.interactorType != Interactor.InteractorType.postit)) {
/* 25:27 */       return;
/* 26:   */     }
/* 27:30 */     QueueWriter.write(Main.socket, RequestSpamWallPostItComposer.compose(item));
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.furniture.OpenPostItParser
 * JD-Core Version:    0.7.0.1
 */