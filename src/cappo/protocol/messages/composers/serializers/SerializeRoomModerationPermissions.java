/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.settings.ModerationPermissions;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class SerializeRoomModerationPermissions
/*  8:   */ {
/*  9:   */   public static void parse(ModerationPermissions perms, MessageWriter writer)
/* 10:   */   {
/* 11:15 */     Composer.writeInt32(perms.permissionsMute, writer);
/* 12:16 */     Composer.writeInt32(perms.permissionsKick, writer);
/* 13:17 */     Composer.writeInt32(perms.permissionsBan, writer);
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeRoomModerationPermissions
 * JD-Core Version:    0.7.0.1
 */