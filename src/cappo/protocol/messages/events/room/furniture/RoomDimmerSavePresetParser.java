/*  1:   */ package cappo.protocol.messages.events.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.collections.MoodlightData;
/*  7:   */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*  8:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 11:   */ 
/* 12:   */ public class RoomDimmerSavePresetParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:19 */     Avatar avatar = Main.avatar;
/* 18:20 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 19:21 */       return;
/* 20:   */     }
/* 21:24 */     RoomTask room = avatar.room;
/* 22:25 */     if (room.MoodlightData == null) {
/* 23:26 */       return;
/* 24:   */     }
/* 25:29 */     int Preset = Main.currentPacket.readInt();
/* 26:30 */     int BackgroundMode = Main.currentPacket.readInt();
/* 27:31 */     String ColorCode = Main.currentPacket.readString();
/* 28:32 */     int Intensity = Main.currentPacket.readInt();
/* 29:   */     
/* 30:34 */     room.MoodlightData.Enabled = true;
/* 31:35 */     room.MoodlightData.CurrentPreset = Preset;
/* 32:36 */     room.MoodlightData.UpdatePreset(ColorCode, Intensity, BackgroundMode > 1);
/* 33:   */     
/* 34:38 */     GenericWallItem Item = (GenericWallItem)room.getWallItem(room.MoodlightData.ItemId);
/* 35:   */     
/* 36:40 */     Item.extraData.setExtraData(room.MoodlightData.GenerateExtraData());
/* 37:41 */     room.wallItemUpdateNeeded(Item);
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.furniture.RoomDimmerSavePresetParser
 * JD-Core Version:    0.7.0.1
 */