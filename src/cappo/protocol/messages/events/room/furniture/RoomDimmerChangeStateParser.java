/*  1:   */ package cappo.protocol.messages.events.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.collections.MoodlightData;
/*  6:   */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*  7:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  8:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ 
/* 11:   */ public class RoomDimmerChangeStateParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection Main)
/* 15:   */   {
/* 16:19 */     Avatar avatar = Main.avatar;
/* 17:20 */     if (avatar == null) {
/* 18:21 */       return;
/* 19:   */     }
/* 20:24 */     if (avatar.controllerLevel < 4) {
/* 21:25 */       return;
/* 22:   */     }
/* 23:28 */     RoomTask room = avatar.room;
/* 24:29 */     if (room.MoodlightData == null) {
/* 25:30 */       return;
/* 26:   */     }
/* 27:33 */     room.MoodlightData.Enabled = (!room.MoodlightData.Enabled);
/* 28:   */     
/* 29:35 */     GenericWallItem Item = (GenericWallItem)room.getWallItem(room.MoodlightData.ItemId);
/* 30:36 */     if (Item == null) {
/* 31:37 */       return;
/* 32:   */     }
/* 33:40 */     Item.extraData.setExtraData(room.MoodlightData.GenerateExtraData());
/* 34:41 */     room.wallItemUpdateNeeded(Item);
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.furniture.RoomDimmerChangeStateParser
 * JD-Core Version:    0.7.0.1
 */