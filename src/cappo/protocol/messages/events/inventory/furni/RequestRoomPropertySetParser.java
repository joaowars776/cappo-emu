/*  1:   */ package cappo.protocol.messages.events.inventory.furni;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.collections.BaseItem;
/*  7:   */ import cappo.game.player.inventory.PlayerInventory;
/*  8:   */ import cappo.game.roomengine.RoomData;
/*  9:   */ import cappo.game.roomengine.entity.item.extradata.ExtraData1;
/* 10:   */ import cappo.game.roomengine.entity.item.extradata.MapStuffData;
/* 11:   */ import cappo.game.roomengine.entity.item.extradata.StringArrayStuffData;
/* 12:   */ import cappo.game.roomengine.entity.item.wall.WallItem;
/* 13:   */ import cappo.game.roomengine.entity.live.Avatar;
import cappo.game.roomengine.itemInteractor.Interactor;
/* 14:   */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/* 15:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 16:   */ import cappo.protocol.messages.composers.room.engine.RoomPropertyComposer;

/* 17:   */ import java.util.List;
/* 18:   */ import java.util.Map;
/* 19:   */ 
/* 20:   */ public class RequestRoomPropertySetParser
/* 21:   */   extends IncomingMessageEvent
/* 22:   */ {
/* 23:   */   public void messageReceived(Connection Main)
/* 24:   */   {
/* 25:25 */     Avatar avatar = Main.avatar;
/* 26:26 */     if ((avatar == null) || ((avatar.controllerLevel != 1) && 
/* 27:27 */       (avatar.controllerLevel < 4))) {
/* 28:28 */       return;
/* 29:   */     }
/* 30:31 */     WallItem wallItem = Main.inventory.getItem(Main.currentPacket.readInt());
/* 31:32 */     if (wallItem == null) {
/* 32:33 */       return;
/* 33:   */     }
/* 34:36 */     if (wallItem.baseItem.interactorType != Interactor.InteractorType.roomeffect) {
/* 35:37 */       return;
/* 36:   */     }
/* 37:40 */     Main.inventoryRemoveItem(wallItem.itemId, true);
/* 38:   */     
/* 39:42 */     RoomTask room = avatar.room;
/* 40:44 */     if (wallItem.baseItem.itemCategory == 2)
/* 41:   */     {
/* 42:45 */       MapStuffData data = (MapStuffData)wallItem.extraData;
/* 43:46 */       room.roomData.Wallpaper = ((String)data.extraData.get("state"));
/* 44:47 */       if (room.roomData.Wallpaper == null) {
/* 45:48 */         room.roomData.Wallpaper = "0.0";
/* 46:   */       }
/* 47:50 */       room.sendMessage(RoomPropertyComposer.compose("wallpaper", room.roomData.Wallpaper));
/* 48:   */     }
/* 49:51 */     else if (wallItem.baseItem.itemCategory == 4)
/* 50:   */     {
/* 51:52 */       ExtraData1 data = (ExtraData1)wallItem.extraData;
/* 52:53 */       room.roomData.Landscape = data.value;
/* 53:54 */       room.sendMessage(RoomPropertyComposer.compose("landscape", room.roomData.Landscape));
/* 54:   */     }
/* 55:55 */     else if (wallItem.baseItem.itemCategory == 3)
/* 56:   */     {
/* 57:56 */       StringArrayStuffData data = (StringArrayStuffData)wallItem.extraData;
/* 58:57 */       room.roomData.Floor = ((String)data.extraData.get(0));
/* 59:58 */       room.sendMessage(RoomPropertyComposer.compose("floor", room.roomData.Floor));
/* 60:   */     }
/* 61:   */   }
/* 62:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.furni.RequestRoomPropertySetParser
 * JD-Core Version:    0.7.0.1
 */