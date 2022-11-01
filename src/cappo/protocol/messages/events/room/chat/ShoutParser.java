/*  1:   */ package cappo.protocol.messages.events.room.chat;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ 
/*  8:   */ public class ShoutParser
/*  9:   */   extends IncomingMessageEvent
/* 10:   */ {
/* 11:   */   public void messageReceived(Connection Main)
/* 12:   */   {
/* 13:16 */     Avatar avatar = Main.avatar;
/* 14:17 */     if (avatar == null) {
/* 15:18 */       return;
/* 16:   */     }
/* 17:21 */     avatar.idleTime = 0;
/* 18:22 */     avatar.say(Main.currentPacket.readString(), Main.currentPacket.readInt(), 0, true);
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.chat.ShoutParser
 * JD-Core Version:    0.7.0.1
 */