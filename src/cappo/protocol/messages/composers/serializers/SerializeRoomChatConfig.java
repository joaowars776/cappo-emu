/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.settings.ChatSettings;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class SerializeRoomChatConfig
/*  8:   */ {
/*  9:   */   public static void parse(ChatSettings settings, MessageWriter writer)
/* 10:   */   {
/* 11:15 */     Composer.writeInt32(settings.chatMode, writer);
/* 12:16 */     Composer.writeInt32(settings.chatBubbleWidth, writer);
/* 13:17 */     Composer.writeInt32(settings.chatScrollSpeed, writer);
/* 14:18 */     Composer.writeInt32(settings.chatHearingDistance, writer);
/* 15:19 */     Composer.writeInt32(settings.chatFloodSensitivity, writer);
/* 16:   */   }
/* 17:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeRoomChatConfig
 * JD-Core Version:    0.7.0.1
 */