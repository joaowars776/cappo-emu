/*  1:   */ package cappo.protocol.messages.events.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  5:   */ import cappo.protocol.messages.parsers.users.SetUserChatSettingMessageParser;
/*  6:   */ 
/*  7:   */ public class SetUserChatSettingMessageEvent
/*  8:   */   extends IncomingMessageEvent
/*  9:   */ {
/* 10:   */   public void messageReceived(Connection cn)
/* 11:   */     throws Exception
/* 12:   */   {
/* 13:16 */     SetUserChatSettingMessageParser parser = new SetUserChatSettingMessageParser(
/* 14:17 */       cn.currentPacket, 
/* 15:18 */       cn.avatarData);
/* 16:21 */     if (!parser.isValid) {
/* 17:22 */       return;
/* 18:   */     }
/* 19:25 */     parser.setChatStyle();
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.users.SetUserChatSettingMessageEvent
 * JD-Core Version:    0.7.0.1
 */