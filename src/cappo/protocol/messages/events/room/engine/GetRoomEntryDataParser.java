/*   1:    */ package cappo.protocol.messages.events.room.engine;
/*   2:    */ 
/*   3:    */ import cappo.engine.logging.Log;
/*   4:    */ import cappo.engine.network.QueueWriter;
/*   5:    */ import cappo.engine.player.Connection;
/*   6:    */ import cappo.engine.threadpools.RoomTask;
/*   7:    */ import cappo.game.bots.RentalBot;
/*   8:    */ import cappo.game.player.PlayerData;
/*   9:    */ import cappo.game.player.data.AvatarData;
/*  10:    */ import cappo.game.polls.Poll;
/*  11:    */ import cappo.game.polls.PollManager;
/*  12:    */ import cappo.game.roomeffects.UserEffect;
/*  13:    */ import cappo.game.roomeffects.special.UserSpecialEffect;
/*  14:    */ import cappo.game.roomengine.RoomData;
/*  15:    */ import cappo.game.roomengine.RoomEvent;
/*  16:    */ import cappo.game.roomengine.RoomManager;
/*  17:    */ import cappo.game.roomengine.entity.item.floor.wired.trigger.WiredTriggerBase;
/*  18:    */ import cappo.game.roomengine.entity.live.Avatar;
/*  19:    */ import cappo.game.roomengine.entity.live.RentalBotEntity;
/*  20:    */ import cappo.game.roomengine.settings.ControllerLevels;
/*  21:    */ import cappo.game.roomengine.wired.WiredManager;
/*  22:    */ import cappo.protocol.messages.IncomingMessageEvent;
/*  23:    */ import cappo.protocol.messages.composers.navigator.EventComposer;
/*  24:    */ import cappo.protocol.messages.composers.navigator.FlatAccessDeniedComposer;
/*  25:    */ import cappo.protocol.messages.composers.poll.PollOfferMessageComposer;
/*  26:    */ import cappo.protocol.messages.composers.room.action.UserAsleepComposer;
/*  27:    */ import cappo.protocol.messages.composers.room.action.UserDanceComposer;
/*  28:    */ import cappo.protocol.messages.composers.room.engine.FloorHeightMapComposer;
/*  29:    */ import cappo.protocol.messages.composers.room.engine.HeightMapComposer;
/*  30:    */ import cappo.protocol.messages.composers.room.engine.ItemsComposer;
/*  31:    */ import cappo.protocol.messages.composers.room.engine.ObjectsComposer;
/*  32:    */ import cappo.protocol.messages.composers.room.engine.PublicRoomObjectsMessageParser;
/*  33:    */ import cappo.protocol.messages.composers.room.engine.RoomEntryInfoComposer;
/*  34:    */ import cappo.protocol.messages.composers.room.engine.RoomVisualizationSettingsComposer;
/*  35:    */ import cappo.protocol.messages.composers.room.engine.UserUpdateComposer;
/*  36:    */ import cappo.protocol.messages.composers.room.engine.UsersComposer;
/*  37:    */ import cappo.protocol.messages.composers.room.session.CloseConnectionComposer;
/*  38:    */ import java.util.Map;
/*  39:    */ 
/*  40:    */ public class GetRoomEntryDataParser
/*  41:    */   extends IncomingMessageEvent
/*  42:    */ {
/*  43:    */   public void messageReceived(Connection cn)
/*  44:    */   {
/*  45: 43 */     if (cn.avatarData.LoadingRoom == 0) {
/*  46: 44 */       return;
/*  47:    */     }
/*  48: 47 */     RoomData roomData = RoomManager.getRoom(cn.avatarData.LoadingRoom);
/*  49:    */     
/*  50: 49 */     cn.avatarData.LoadingRoom = 0;
/*  51: 51 */     if (roomData == null) {
/*  52: 52 */       return;
/*  53:    */     }
/*  54: 55 */     RoomTask room = roomData.room;
/*  55: 57 */     if (room.model == null)
/*  56:    */     {
/*  57: 58 */       QueueWriter.write(cn.socket, FlatAccessDeniedComposer.compose(2, ""));
/*  58: 59 */       QueueWriter.write(cn.socket, CloseConnectionComposer.compose());
/*  59: 60 */       return;
/*  60:    */     }
/*  61: 63 */     PlayerData playerData = cn.getPlayerData();
/*  62:    */     
/*  63: 65 */     int controllerLevel = ControllerLevels.getLevel(playerData, roomData, room);
/*  64: 66 */     if (controllerLevel < 4)
/*  65:    */     {
/*  66: 67 */       if (room.userCount >= roomData.usersMax)
/*  67:    */       {
/*  68: 68 */         QueueWriter.write(cn.socket, FlatAccessDeniedComposer.compose(1, ""));
/*  69: 69 */         QueueWriter.write(cn.socket, CloseConnectionComposer.compose());
/*  70: 70 */         return;
/*  71:    */       }
/*  72: 73 */       if ((room.userIsBanned(playerData.userId)) && 
/*  73: 74 */         (!room.hasBanExpired(playerData.userId)))
/*  74:    */       {
/*  75: 75 */         QueueWriter.write(cn.socket, FlatAccessDeniedComposer.compose(4, ""));
/*  76: 76 */         QueueWriter.write(cn.socket, CloseConnectionComposer.compose());
/*  77: 77 */         return;
/*  78:    */       }
/*  79:    */     }
/*  80: 83 */     QueueWriter.write(cn.socket, HeightMapComposer.compose(room));
/*  81: 84 */     QueueWriter.write(cn.socket, FloorHeightMapComposer.compose(room.model));
/*  82:    */     
/*  83:    */ 
/*  84: 87 */     boolean clean = false;
/*  85: 88 */     while (!clean)
/*  86:    */     {
/*  87: 89 */       clean = true;
/*  88: 90 */       for (Avatar User : room.userList.values()) {
/*  89: 91 */         if ((User.cn == null) || (User.cn.getPlayerData() == null))
/*  90:    */         {
/*  91: 92 */           Log.printLog("PROBLEM: User List is bugged, userid=" + User.id);
/*  92: 93 */           room.userList.remove(Integer.valueOf(User.id));
/*  93: 94 */           clean = false;
/*  94: 95 */           break;
/*  95:    */         }
/*  96:    */       }
/*  97:    */     }
/*  98:100 */     QueueWriter.write(cn.socket, UsersComposer.compose(room.userList.values(), room.petList.values(), room.rentalBotList.values()));
/*  99:101 */     QueueWriter.write(cn.socket, PublicRoomObjectsMessageParser.compose());
/* 100:102 */     QueueWriter.write(cn.socket, ObjectsComposer.compose(room.FloorItems.values()));
/* 101:103 */     QueueWriter.write(cn.socket, ItemsComposer.compose(room.WallItems.values()));
/* 102:    */     
/* 103:105 */     room.addUserToRoom(cn);
/* 104:    */     
/* 105:107 */     QueueWriter.write(cn.socket, RoomVisualizationSettingsComposer.compose(Boolean.valueOf(roomData.haveFlag(16)), roomData.wallAnchor, roomData.floorAnchor));
/* 106:108 */     QueueWriter.write(cn.socket, RoomEntryInfoComposer.compose(Boolean.valueOf(true), roomData.roomId, Boolean.valueOf(cn.avatar.controllerLevel >= 4)));
/* 107:109 */     if (roomData.event != null) {
/* 108:110 */       QueueWriter.write(cn.socket, EventComposer.compose(cn.playerData.userId, cn.playerData.userName, roomData.roomId, roomData.event.category, roomData.event.name, roomData.event.description, roomData.event.startTime));
/* 109:    */     } else {
/* 110:112 */       QueueWriter.write(cn.socket, EventComposer.compose());
/* 111:    */     }
/* 112:114 */     QueueWriter.write(cn.socket, UserUpdateComposer.compose(room.userList.values(), room.petList.values()));
/* 113:116 */     for (Avatar User : room.userList.values())
/* 114:    */     {
/* 115:117 */       if (User.IsDancing) {
/* 116:118 */         QueueWriter.write(cn.socket, UserDanceComposer.compose(User.virtualId, User.DanceId));
/* 117:    */       }
/* 118:121 */       if (User.IsAsleep) {
/* 119:122 */         QueueWriter.write(cn.socket, UserAsleepComposer.compose(User.virtualId, Boolean.valueOf(User.IsAsleep)));
/* 120:    */       }
/* 121:125 */       if (User.userSpecialEffect != null) {
/* 122:126 */         User.userSpecialEffect.startEffect(cn.socket);
/* 123:127 */       } else if (User.userEffect != null) {
/* 124:128 */         User.userEffect.startEffect(cn.socket);
/* 125:    */       }
/* 126:    */     }
/* 127:133 */     for (RentalBotEntity bot : room.rentalBotList.values()) {
/* 128:134 */       if (bot.botData.danceEnabled) {
/* 129:135 */         QueueWriter.write(cn.socket, UserDanceComposer.compose(bot.virtualId, 1));
/* 130:    */       }
/* 131:    */     }
/* 132:139 */     Poll poll = (Poll)PollManager.roomPolls.get(Integer.valueOf(room.roomId));
/* 133:140 */     if (poll != null) {
/* 134:141 */       QueueWriter.write(cn.socket, PollOfferMessageComposer.compose(poll));
/* 135:    */     }
/* 136:144 */     WiredTriggerBase.launchTriggers(room.wiredManager.triggersEntersRoom, cn, playerData.userName);
/* 137:145 */     WiredTriggerBase.launchTriggers(room.wiredManager.triggersTimers, null, null);
/* 138:    */   }
/* 139:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.GetRoomEntryDataParser
 * JD-Core Version:    0.7.0.1
 */