/*  1:   */ package cappo.game.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.games.snowwar.gameevents.Event;
/*  7:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  8:   */ import io.netty.channel.Channel;
/*  9:   */ import java.util.ArrayList;
/* 10:   */ import java.util.Collection;
/* 11:   */ import java.util.List;
/* 12:   */ import java.util.Map;
/* 13:   */ import java.util.concurrent.ConcurrentHashMap;
/* 14:   */ 
/* 15:   */ public class SnowWarRoom
/* 16:   */   extends SynchronizedGameStage
/* 17:   */ {
/* 18:24 */   public int[] TeamScore = new int[SnowWar.TEAMS.length];
/* 19:   */   public int checksum;
/* 20:   */   public int roomId;
/* 21:   */   public int Result;
/* 22:   */   public int STATUS;
/* 23:   */   public int TimeToStart;
/* 24:   */   public int Turn;
/* 25:   */   public int Winner;
/* 26:   */   public String Name;
/* 27:   */   public String Owner;
/* 28:   */   public boolean LobbyFull;
/* 29:   */   public SnowWarArenaBase ArenaType;
/* 30:   */   public HumanGameObject MostHits;
/* 31:   */   public HumanGameObject MostKills;
/* 32:   */   public SnowWarGameStage map;
/* 33:40 */   public final Map<Integer, Map<Integer, HumanGameObject>> TeamPlayers = new ConcurrentHashMap(SnowWar.TEAMS.length);
/* 34:41 */   public final Map<Integer, HumanGameObject> players = new ConcurrentHashMap(10);
/* 35:43 */   public final List<Event> gameEvents = new ArrayList(50);
/* 36:   */   public List<Channel> fullGameStatusQueue;
/* 37:   */   private Map<Integer, HumanGameObject> stageLoadedPlayers;
/* 38:   */   
/* 39:   */   public SnowWarRoom(int id)
/* 40:   */   {
/* 41:50 */     this.ArenaType = SnowWar.ArenaTypes[cappo.game.collections.Utils.GetRandomNumber(0, SnowWar.ArenaTypes.length - 1)];
/* 42:51 */     this.roomId = id;
/* 43:52 */     this.Name = ("SnowStorm level " + this.ArenaType.ArenaType);
/* 44:53 */     this.map = new SnowWarGameStage();
/* 45:54 */     this.map.initialize(this.ArenaType);
/* 46:55 */     for (int TeamId : SnowWar.TEAMS) {
/* 47:56 */       this.TeamPlayers.put(Integer.valueOf(TeamId), new ConcurrentHashMap());
/* 48:   */     }
/* 49:   */   }
/* 50:   */   
/* 51:   */   public void broadcast(MessageWriter Message)
/* 52:   */   {
/* 53:61 */     for (HumanGameObject player : this.players.values()) {
/* 54:62 */       if (player.currentSnowWar != null) {
/* 55:63 */         QueueWriter.writeAndFlush(player.cn.socket, Message);
/* 56:   */       }
/* 57:   */     }
/* 58:   */   }
/* 59:   */   
/* 60:   */   public Collection<HumanGameObject> getStageLoadedPlayers()
/* 61:   */   {
/* 62:71 */     if (this.stageLoadedPlayers == null) {
/* 63:72 */       return null;
/* 64:   */     }
/* 65:75 */     Collection<HumanGameObject> result = this.stageLoadedPlayers.values();
/* 66:76 */     this.stageLoadedPlayers = null;
/* 67:   */     
/* 68:78 */     return result;
/* 69:   */   }
/* 70:   */   
/* 71:   */   public void stageLoaded(HumanGameObject humanObject)
/* 72:   */   {
/* 73:82 */     if (this.stageLoadedPlayers == null) {
/* 74:83 */       this.stageLoadedPlayers = new ConcurrentHashMap();
/* 75:   */     }
/* 76:86 */     this.stageLoadedPlayers.put(Integer.valueOf(humanObject.objectId), humanObject);
/* 77:87 */     humanObject.stageLoaded = true;
/* 78:   */   }
/* 79:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.SnowWarRoom
 * JD-Core Version:    0.7.0.1
 */