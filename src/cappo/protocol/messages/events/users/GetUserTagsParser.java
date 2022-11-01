/*  1:   */ package cappo.protocol.messages.events.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.users.UserTagsComposer;
/* 10:   */ 
/* 11:   */ public class GetUserTagsParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection Main)
/* 15:   */   {
/* 16:19 */     PlayerData pClient = Clients.getPlayerData(Main.currentPacket.readInt());
/* 17:20 */     if (pClient == null) {
/* 18:21 */       return;
/* 19:   */     }
/* 20:24 */     QueueWriter.write(Main.socket, UserTagsComposer.compose(pClient));
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.users.GetUserTagsParser
 * JD-Core Version:    0.7.0.1
 */