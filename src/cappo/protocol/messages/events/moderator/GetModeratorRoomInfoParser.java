/*  1:   */ package cappo.protocol.messages.events.moderator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.player.PlayerData;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.moderation.ModeratorRoomInfoComposer;
/*  9:   */ 
/* 10:   */ public class GetModeratorRoomInfoParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection cn)
/* 14:   */   {
/* 15:18 */     if (!cn.playerData.allowModTools()) {
/* 16:19 */       return;
/* 17:   */     }
/* 18:22 */     QueueWriter.write(cn.socket, ModeratorRoomInfoComposer.compose(cn.currentPacket.readInt()));
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.moderator.GetModeratorRoomInfoParser
 * JD-Core Version:    0.7.0.1
 */