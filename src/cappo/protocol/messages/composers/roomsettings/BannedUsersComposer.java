/*  1:   */ package cappo.protocol.messages.composers.roomsettings;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.player.PlayerData;
/*  5:   */ import cappo.game.roomengine.settings.PlayerBan;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import cappo.protocol.messages.composers.serializers.SerializeRoomBan;
/*  8:   */ import java.util.Collection;
/*  9:   */ 
/* 10:   */ public class BannedUsersComposer
/* 11:   */ {
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static MessageWriter compose(int roomId, Collection<PlayerBan> bans)
/* 15:   */   {
/* 16:20 */     MessageWriter ClientMessage = new MessageWriter();
/* 17:21 */     Composer.initPacket(HEADER, ClientMessage);
/* 18:22 */     Composer.add(Integer.valueOf(roomId), ClientMessage);
/* 19:23 */     Composer.add(Integer.valueOf(bans.size()), ClientMessage);
/* 20:24 */     for (PlayerBan ban : bans) {
/* 21:25 */       SerializeRoomBan.parse(ClientMessage, ban.player.userId, ban.player.userName);
/* 22:   */     }
/* 23:27 */     Composer.endPacket(ClientMessage);
/* 24:28 */     return ClientMessage;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.roomsettings.BannedUsersComposer
 * JD-Core Version:    0.7.0.1
 */