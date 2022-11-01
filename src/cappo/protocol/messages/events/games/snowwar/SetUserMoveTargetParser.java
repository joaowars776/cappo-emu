/*  1:   */ package cappo.protocol.messages.events.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  6:   */ import cappo.game.player.SnowWarPlayerData;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ 
/*  9:   */ public class SetUserMoveTargetParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:15 */     if (Main.snowWarPlayerData.currentSnowWar == null) {
/* 15:16 */       return;
/* 16:   */     }
/* 17:19 */     int x = Main.currentPacket.readInt();
/* 18:20 */     int y = Main.currentPacket.readInt();
/* 19:   */     
/* 20:22 */     Main.currentPacket.readInt();
/* 21:23 */     Main.currentPacket.readInt();
/* 22:25 */     if (Main.snowWarPlayerData.humanObject.canWalkTo(x, y)) {
/* 23:26 */       Main.snowWarPlayerData.playerMove(x, y);
/* 24:   */     }
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.games.snowwar.SetUserMoveTargetParser
 * JD-Core Version:    0.7.0.1
 */