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
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ 
/* 13:   */ public class AddSpamWallPostItParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection Main)
/* 17:   */   {
/* 18:20 */     Avatar avatar = Main.avatar;
/* 19:21 */     if ((avatar == null) || (avatar.controllerLevel < 4)) {
/* 20:22 */       return;
/* 21:   */     }
/* 22:25 */     RoomTask room = avatar.room;
/* 23:   */     
/* 24:27 */     GenericWallItem item = (GenericWallItem)room.getWallItem(Main.currentPacket.readInt());
/* 25:28 */     if ((item == null) || (item.baseItem.interactorType != Interactor.InteractorType.postit)) {
/* 26:29 */       return;
/* 27:   */     }
/* 28:32 */     String color = Main.currentPacket.readString();
/* 29:   */     String str1;
/* 30:33 */     switch ((str1 = color).hashCode())
/* 31:   */     {
/* 32:   */     case 1695802060: 
/* 33:33 */       if (str1.equals("9CCEFF")) {
/* 34:   */         break label170;
/* 35:   */       }
/* 36:   */       break;
/* 37:   */     case 1695891988: 
/* 38:33 */       if (str1.equals("9CFF9C")) {
/* 39:   */         break label170;
/* 40:   */       }
/* 41:   */       break;
/* 42:   */     case 2070451754: 
/* 43:   */     case 2070841312: 
/* 44:33 */       if ((str1.equals("FF9CFF")) || ((goto 169) && (str1.equals("FFFF33")))) {
/* 45:   */         break label170;
/* 46:   */       }
/* 47:   */     }
/* 48:41 */     return;
/* 49:   */     label170:
/* 50:44 */     item.extraData.setExtraData(color.concat(" ").concat(Main.currentPacket.readString()));
/* 51:45 */     room.wallItemUpdateNeeded(item);
/* 52:   */   }
/* 53:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.furniture.AddSpamWallPostItParser
 * JD-Core Version:    0.7.0.1
 */