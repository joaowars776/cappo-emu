/*  1:   */ package cappo.protocol.messages.events.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.users.UserBadgesComposer;
/* 10:   */ import java.util.Map;
/* 11:   */ 
/* 12:   */ public class GetSelectedBadgesParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:19 */     PlayerData pClient = Clients.getPlayerDataLoaded(Main.currentPacket.readInt());
/* 18:20 */     if ((pClient == null) || (pClient.connection == null)) {
/* 19:21 */       return;
/* 20:   */     }
/* 21:24 */     QueueWriter.write(Main.socket, UserBadgesComposer.compose(pClient.userId, pClient.connection.badgesSelected.values()));
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.users.GetSelectedBadgesParser
 * JD-Core Version:    0.7.0.1
 */