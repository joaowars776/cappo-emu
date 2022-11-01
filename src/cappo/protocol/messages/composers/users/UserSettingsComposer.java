/*  1:   */ package cappo.protocol.messages.composers.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.player.data.AvatarData;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class UserSettingsComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(AvatarData avatarData)
/* 12:   */   {
/* 13:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 14:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 15:19 */     Composer.writeInt32(avatarData.volume1, ClientMessage);
/* 16:20 */     Composer.writeInt32(avatarData.volume2, ClientMessage);
/* 17:21 */     Composer.writeInt32(avatarData.volume3, ClientMessage);
/* 18:22 */     Composer.writeBoolean(avatarData.oldChatStyle, ClientMessage);
/* 19:23 */     Composer.endPacket(ClientMessage);
/* 20:24 */     return ClientMessage;
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.users.UserSettingsComposer
 * JD-Core Version:    0.7.0.1
 */