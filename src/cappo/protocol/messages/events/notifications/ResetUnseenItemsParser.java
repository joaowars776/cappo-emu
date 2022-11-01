/*  1:   */ package cappo.protocol.messages.events.notifications;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.collections.UnseenItems;
/*  6:   */ import cappo.game.player.data.AvatarData;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ 
/*  9:   */ public class ResetUnseenItemsParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:15 */     Main.avatarData.UnseenItems.ResetItems(Main.currentPacket.readInt());
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.notifications.ResetUnseenItemsParser
 * JD-Core Version:    0.7.0.1
 */