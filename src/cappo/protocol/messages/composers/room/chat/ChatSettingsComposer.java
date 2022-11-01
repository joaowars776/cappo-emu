/*  1:   */ package cappo.protocol.messages.composers.room.chat;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.settings.ChatSettings;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializeRoomChatConfig;
/*  7:   */ 
/*  8:   */ public class ChatSettingsComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(ChatSettings settings)
/* 13:   */   {
/* 14:18 */     MessageWriter writer = new MessageWriter();
/* 15:19 */     Composer.initPacket(HEADER, writer);
/* 16:20 */     SerializeRoomChatConfig.parse(settings, writer);
/* 17:21 */     Composer.endPacket(writer);
/* 18:22 */     return writer;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.chat.ChatSettingsComposer
 * JD-Core Version:    0.7.0.1
 */