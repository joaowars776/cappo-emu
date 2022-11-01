/*  1:   */ package cappo.protocol.messages.events.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  6:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.SnowWarPlayerData;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.games.snowwar.GameChatFromPlayerComposer;
/* 11:   */ 
/* 12:   */ public class GameChatParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:19 */     SnowWarPlayerData snowPlayer = Main.snowWarPlayerData;
/* 18:20 */     if (snowPlayer == null) {
/* 19:21 */       return;
/* 20:   */     }
/* 21:24 */     SnowWarRoom room = snowPlayer.currentSnowWar;
/* 22:25 */     if (room == null) {
/* 23:26 */       return;
/* 24:   */     }
/* 25:29 */     String say = Main.currentPacket.readString();
/* 26:30 */     if ((say.startsWith(":")) && 
/* 27:31 */       (Main.playerData.allowDataReload()) && 
/* 28:32 */       (say.startsWith(":info")))
/* 29:   */     {
/* 30:33 */       HumanGameObject human = Main.snowWarPlayerData.humanObject;
/* 31:34 */       String local5 = "";
/* 32:35 */       local5 = local5 + human.getVariable(2) + ",";
/* 33:36 */       local5 = local5 + human.getVariable(3) + ",";
/* 34:37 */       local5 = local5 + human.getVariable(6) + ",";
/* 35:   */       
/* 36:39 */       room.broadcast(GameChatFromPlayerComposer.compose(snowPlayer.player.userId, local5));
/* 37:   */     }
/* 38:43 */     room.broadcast(GameChatFromPlayerComposer.compose(snowPlayer.player.userId, say));
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.games.snowwar.GameChatParser
 * JD-Core Version:    0.7.0.1
 */