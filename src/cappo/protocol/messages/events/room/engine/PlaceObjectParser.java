/*   1:    */ package cappo.protocol.messages.events.room.engine;
/*   2:    */ 
/*   3:    */ import cappo.engine.network.MessageReader;
/*   4:    */ import cappo.engine.network.QueueWriter;
/*   5:    */ import cappo.engine.player.Connection;
/*   6:    */ import cappo.engine.threadpools.RoomTask;
/*   7:    */ import cappo.game.collections.BaseItem;
/*   8:    */ import cappo.game.collections.BaseItem.FurniLogic;
/*   9:    */ import cappo.game.collections.Teleports;
/*  10:    */ import cappo.game.games.snowwar.Direction8;
/*  11:    */ import cappo.game.player.inventory.PlayerInventory;
/*  12:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  13:    */ import cappo.game.roomengine.entity.item.floor.RoomFloorItemData;
/*  14:    */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  15:    */ import cappo.game.roomengine.entity.item.wall.RoomWallItemData;
/*  16:    */ import cappo.game.roomengine.entity.item.wall.WallItem;
/*  17:    */ import cappo.game.roomengine.entity.live.Avatar;
/*  18:    */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/*  19:    */ import cappo.protocol.messages.IncomingMessageEvent;
/*  20:    */ import cappo.protocol.messages.composers.handshake.GenericErrorComposer;
/*  21:    */ import cappo.protocol.messages.composers.room.engine.PlaceObjectErrorComposer;
/*  22:    */ 
/*  23:    */ public class PlaceObjectParser
/*  24:    */   extends IncomingMessageEvent
/*  25:    */ {
/*  26:    */   public void messageReceived(Connection Main)
/*  27:    */   {
/*  28: 29 */     Avatar avatar = Main.avatar;
/*  29: 30 */     if (avatar == null) {
/*  30: 31 */       return;
/*  31:    */     }
/*  32: 34 */     if ((avatar.controllerLevel != 1) && 
/*  33: 35 */       (avatar.controllerLevel < 4))
/*  34:    */     {
/*  35: 36 */       QueueWriter.write(Main.socket, GenericErrorComposer.compose(-32000));
/*  36: 37 */       return;
/*  37:    */     }
/*  38: 41 */     String PlacementData = Main.currentPacket.readString();
/*  39: 42 */     String[] DataBits = PlacementData.split(" ");
/*  40: 43 */     if (DataBits.length < 3) {
/*  41: 44 */       return;
/*  42:    */     }
/*  43: 47 */     int ItemId = Integer.parseInt(DataBits[0]);
/*  44: 49 */     if (DataBits[1].startsWith(":"))
/*  45:    */     {
/*  46: 50 */       WallItem item = Main.inventory.getItem(ItemId);
/*  47: 51 */       if (item == null) {
/*  48: 52 */         return;
/*  49:    */       }
/*  50: 55 */       if (!(item instanceof GenericWallItem)) {
/*  51: 56 */         return;
/*  52:    */       }
/*  53: 59 */       if ((item.baseItem.logic == BaseItem.FurniLogic.ROOMDIMMER) && 
/*  54: 60 */         (avatar.room.MoodlightData != null)) {
/*  55: 61 */         return;
/*  56:    */       }
/*  57: 65 */       String[] widD = DataBits[1].substring(3).split(",");
/*  58: 66 */       int widthX = Integer.parseInt(widD[0]);
/*  59: 67 */       int widthY = Integer.parseInt(widD[1]);
/*  60: 68 */       if ((widthX < 0) || (widthY < 0) || (widthX > 200) || (widthY > 200))
/*  61:    */       {
/*  62: 69 */         QueueWriter.write(Main.socket, PlaceObjectErrorComposer.compose(11));
/*  63: 70 */         return;
/*  64:    */       }
/*  65: 73 */       String[] lenD = DataBits[2].substring(2).split(",");
/*  66: 74 */       int lengthX = Integer.parseInt(lenD[0]);
/*  67: 75 */       int lengthY = Integer.parseInt(lenD[1]);
/*  68: 76 */       if ((lengthX < 0) || (lengthY < 0) || (lengthX > 200) || (lengthY > 200))
/*  69:    */       {
/*  70: 77 */         QueueWriter.write(Main.socket, PlaceObjectErrorComposer.compose(11)); return;
/*  71:    */       }
/*  72:    */       char side;
/*  73:    */       char side;
/*  74: 82 */       if (DataBits[3].equals("r")) {
/*  75: 83 */         side = 'r';
/*  76:    */       } else {
/*  77: 85 */         side = 'l';
/*  78:    */       }
/*  79: 88 */       item.setRoomData(new RoomWallItemData(avatar.room, item, side, widthX, widthY, lengthX, lengthY));
/*  80: 90 */       if ((item.baseItem.logic == BaseItem.FurniLogic.ROOMDIMMER) && 
/*  81: 91 */         (avatar.room.MoodlightData != null))
/*  82:    */       {
/*  83: 93 */         QueueWriter.write(Main.socket, PlaceObjectErrorComposer.compose(11));
/*  84: 94 */         return;
/*  85:    */       }
/*  86: 98 */       if (avatar.room.setWallItem(Main, (GenericWallItem)item, true)) {
/*  87: 99 */         Main.inventoryRemoveItem(ItemId, true);
/*  88:    */       } else {
/*  89:101 */         item.cleanRoomData();
/*  90:    */       }
/*  91:    */     }
/*  92:    */     else
/*  93:    */     {
/*  94:104 */       FloorItem item = Main.inventory.getObject(ItemId);
/*  95:105 */       if (item == null) {
/*  96:106 */         return;
/*  97:    */       }
/*  98:109 */       item.setRoomData(new RoomFloorItemData(avatar.room, item));
/*  99:    */       
/* 100:111 */       int X = Short.parseShort(DataBits[1]);
/* 101:112 */       int Y = Short.parseShort(DataBits[2]);
/* 102:113 */       Direction8 Rot = Direction8.getDirection(Integer.parseInt(DataBits[3]));
/* 103:115 */       if (avatar.room.setFloorItem(Main, item, X, Y, Rot, true))
/* 104:    */       {
/* 105:116 */         if (item.baseItem.interactorType == Interactor.InteractorType.teleport) {
/* 106:117 */           Teleports.setRoom(item.itemId, avatar.room.roomId);
/* 107:    */         }
/* 108:120 */         Main.inventoryRemoveItem(ItemId, false);
/* 109:    */       }
/* 110:    */       else
/* 111:    */       {
/* 112:122 */         item.cleanRoomData();
/* 113:    */         
/* 114:124 */         QueueWriter.write(Main.socket, PlaceObjectErrorComposer.compose(11));
/* 115:    */       }
/* 116:    */     }
/* 117:    */   }
/* 118:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.PlaceObjectParser
 * JD-Core Version:    0.7.0.1
 */