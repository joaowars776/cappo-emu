/*   1:    */ package cappo.game.games.snowwar;
/*   2:    */ 
/*   3:    */ import cappo.engine.network.QueueWriter;
/*   4:    */ import cappo.engine.player.Connection;
/*   5:    */ import cappo.engine.threadpools.RoomTask;
/*   6:    */ import cappo.engine.threadpools.SnowWarTask;
/*   7:    */ import cappo.game.games.snowwar.gameevents.PlayerLeft;
/*   8:    */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*   9:    */ import cappo.game.player.PlayerData;
/*  10:    */ import cappo.game.player.SnowWarPlayerData;
/*  11:    */ import cappo.game.roomengine.entity.live.Avatar;
/*  12:    */ import cappo.protocol.messages.composers.games.snowwar.ArenaEnteredComposer;
/*  13:    */ import cappo.protocol.messages.composers.games.snowwar.EnterArenaComposer;
/*  14:    */ import cappo.protocol.messages.composers.games.snowwar.GameCreatedComposer;
/*  15:    */ import cappo.protocol.messages.composers.games.snowwar.GameLongDataComposer;
/*  16:    */ import cappo.protocol.messages.composers.games.snowwar.GameStartedComposer;
/*  17:    */ import cappo.protocol.messages.composers.games.snowwar.InArenaQueueComposer;
/*  18:    */ import cappo.protocol.messages.composers.games.snowwar.StageLoadComposer;
/*  19:    */ import cappo.protocol.messages.composers.games.snowwar.StartCounterComposer;
/*  20:    */ import cappo.protocol.messages.composers.games.snowwar.UserJoinedGameComposer;
/*  21:    */ import cappo.protocol.messages.composers.games.snowwar.UserLeftGameComposer;
/*  22:    */ import cappo.protocol.messages.composers.room.session.YouArePlayingGameComposer;
/*  23:    */ import java.util.List;
/*  24:    */ import java.util.Map;
/*  25:    */ import java.util.concurrent.ConcurrentHashMap;
/*  26:    */ 
/*  27:    */ public class SnowPlayerQueue
/*  28:    */ {
/*  29:    */   private static int roomCounter;
/*  30: 32 */   public static final Map<Integer, RoomQueue> roomQueue = new ConcurrentHashMap(100);
/*  31:    */   
/*  32:    */   public static void addPlayerInQueue(Connection cn)
/*  33:    */   {
/*  34: 35 */     PlayerData playerData = cn.playerData;
/*  35:    */     
/*  36: 37 */     RoomQueue pickRoom = null;
/*  37: 38 */     if (roomQueue.isEmpty())
/*  38:    */     {
/*  39: 39 */       pickRoom = new RoomQueue(new SnowWarRoom(++roomCounter));
/*  40: 40 */       roomQueue.put(Integer.valueOf(pickRoom.room.roomId), pickRoom);
/*  41:    */     }
/*  42:    */     else
/*  43:    */     {
/*  44: 42 */       for (RoomQueue room : roomQueue.values()) {
/*  45: 43 */         if (room.players.size() < 10)
/*  46:    */         {
/*  47: 44 */           pickRoom = room;
/*  48: 45 */           break;
/*  49:    */         }
/*  50:    */       }
/*  51: 50 */       if (pickRoom == null)
/*  52:    */       {
/*  53: 51 */         pickRoom = new RoomQueue(new SnowWarRoom(++roomCounter));
/*  54: 52 */         roomQueue.put(Integer.valueOf(pickRoom.room.roomId), pickRoom);
/*  55:    */       }
/*  56:    */     }
/*  57: 56 */     if (pickRoom.players.isEmpty()) {
/*  58: 57 */       pickRoom.room.Owner = playerData.userName;
/*  59:    */     }
/*  60: 60 */     cn.snowWarPlayerData.setHumanObject(new HumanGameObject(pickRoom.room, 0));
/*  61: 61 */     cn.snowWarPlayerData.humanObject.status = 0;
/*  62: 62 */     cn.snowWarPlayerData.setRoom(pickRoom.room);
/*  63:    */     
/*  64: 64 */     pickRoom.broadcast(UserJoinedGameComposer.compose(cn));
/*  65:    */     
/*  66: 66 */     pickRoom.players.put(Integer.valueOf(playerData.userId), cn);
/*  67: 68 */     if (pickRoom.room.Owner.equals(playerData.userName)) {
/*  68: 69 */       QueueWriter.writeAndFlush(cn.socket, GameCreatedComposer.compose(pickRoom));
/*  69:    */     } else {
/*  70: 71 */       QueueWriter.writeAndFlush(cn.socket, GameLongDataComposer.compose(pickRoom));
/*  71:    */     }
/*  72: 74 */     if (pickRoom.players.size() >= 4) {
/*  73: 75 */       startLoading(pickRoom);
/*  74:    */     }
/*  75:    */   }
/*  76:    */   
/*  77:    */   public static void playerExit(SnowWarRoom room, HumanGameObject playerObject)
/*  78:    */   {
/*  79: 80 */     RoomQueue queue = (RoomQueue)roomQueue.get(Integer.valueOf(room.roomId));
/*  80: 81 */     if (queue == null)
/*  81:    */     {
/*  82: 82 */       room.players.remove(Integer.valueOf(playerObject.userId));
/*  83: 83 */       ((Map)room.TeamPlayers.get(Integer.valueOf(playerObject.team))).remove(Integer.valueOf(playerObject.userId));
/*  84: 85 */       if (room.STATUS == 5)
/*  85:    */       {
/*  86: 86 */         synchronized (room.gameEvents)
/*  87:    */         {
/*  88: 87 */           room.gameEvents.add(new PlayerLeft(playerObject));
/*  89:    */         }
/*  90: 89 */         return;
/*  91:    */       }
/*  92: 92 */       room.broadcast(UserLeftGameComposer.compose(playerObject.userId));
/*  93:    */     }
/*  94:    */     else
/*  95:    */     {
/*  96: 94 */       queue.broadcast(UserLeftGameComposer.compose(playerObject.userId));
/*  97: 95 */       queue.players.remove(Integer.valueOf(playerObject.userId));
/*  98:    */     }
/*  99: 98 */     playerObject.cleanData();
/* 100:    */   }
/* 101:    */   
/* 102:    */   public static void roomLoaded(SnowWarRoom room)
/* 103:    */   {
/* 104:102 */     RoomQueue queue = (RoomQueue)roomQueue.remove(Integer.valueOf(room.roomId));
/* 105:103 */     if (queue == null) {
/* 106:104 */       return;
/* 107:    */     }
/* 108:107 */     int pickTeam = 0;
/* 109:108 */     for (Connection cn : queue.players.values())
/* 110:    */     {
/* 111:109 */       room.players.put(Integer.valueOf(cn.playerData.userId), cn.snowWarPlayerData.humanObject);
/* 112:110 */       int team = 1 + ++pickTeam % SnowWar.TEAMS.length;
/* 113:111 */       cn.snowWarPlayerData.humanObject.team = team;
/* 114:112 */       ((Map)room.TeamPlayers.get(Integer.valueOf(team))).put(Integer.valueOf(cn.playerData.userId), cn.snowWarPlayerData.humanObject);
/* 115:    */     }
/* 116:115 */     queue.broadcast(GameStartedComposer.compose(queue));
/* 117:116 */     queue.broadcast(InArenaQueueComposer.compose(1));
/* 118:117 */     queue.broadcast(YouArePlayingGameComposer.compose(true));
/* 119:118 */     room.broadcast(EnterArenaComposer.compose(room));
/* 120:120 */     for (HumanGameObject player : room.players.values())
/* 121:    */     {
/* 122:121 */       player.status = 1;
/* 123:122 */       if (player.cn.avatar != null) {
/* 124:123 */         player.cn.avatar.room.removeUserFromRoom(player.cn, false, false);
/* 125:    */       }
/* 126:125 */       room.broadcast(ArenaEnteredComposer.compose(player));
/* 127:    */     }
/* 128:128 */     room.broadcast(StageLoadComposer.compose());
/* 129:    */   }
/* 130:    */   
/* 131:    */   private static void startLoading(RoomQueue queue)
/* 132:    */   {
/* 133:132 */     SnowWarRoom room = queue.room;
/* 134:133 */     if (room.STATUS == 1) {
/* 135:134 */       return;
/* 136:    */     }
/* 137:137 */     room.TimeToStart = 15;
/* 138:138 */     room.STATUS = 1;
/* 139:    */     
/* 140:140 */     queue.broadcast(StartCounterComposer.compose(room.TimeToStart));
/* 141:    */     
/* 142:142 */     SnowWarTask.addTask(new SnowWarTask(room), 0, 1000);
/* 143:    */   }
/* 144:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.SnowPlayerQueue
 * JD-Core Version:    0.7.0.1
 */