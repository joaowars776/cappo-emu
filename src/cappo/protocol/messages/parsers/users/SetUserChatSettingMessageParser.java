/*  1:   */ package cappo.protocol.messages.parsers.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.game.player.data.AvatarData;
/*  5:   */ 
/*  6:   */ public class SetUserChatSettingMessageParser
/*  7:   */ {
/*  8:   */   public boolean isValid;
/*  9:   */   private boolean oldChatStyle;
/* 10:   */   private AvatarData avatarData;
/* 11:   */   
/* 12:   */   public SetUserChatSettingMessageParser(MessageReader reader, AvatarData player)
/* 13:   */   {
/* 14:14 */     if (player == null) {
/* 15:15 */       return;
/* 16:   */     }
/* 17:18 */     this.avatarData = player;
/* 18:19 */     this.oldChatStyle = reader.readBoolean();
/* 19:20 */     this.isValid = true;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void setChatStyle()
/* 23:   */   {
/* 24:24 */     this.avatarData.oldChatStyle = this.oldChatStyle;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.parsers.users.SetUserChatSettingMessageParser
 * JD-Core Version:    0.7.0.1
 */