/*  1:   */ package cappo.protocol.messages.events.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Clients;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.users.RelationshipStatusComposer;
/* 10:   */ 
/* 11:   */ public class GetRelationshipStatusParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection Main)
/* 15:   */   {
/* 16:19 */     PlayerData player = Clients.getPlayerData(Main.currentPacket.readInt());
/* 17:20 */     if (player != null) {
/* 18:21 */       QueueWriter.write(Main.socket, RelationshipStatusComposer.compose(player));
/* 19:   */     }
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.users.GetRelationshipStatusParser
 * JD-Core Version:    0.7.0.1
 */