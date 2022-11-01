/*  1:   */ package cappo.protocol.messages.composers.moderation;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.Utils;
/*  5:   */ import cappo.game.player.AvatarLook;
/*  6:   */ import cappo.game.player.PlayerData;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ 
/*  9:   */ public class ModeratorUserInfoComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(PlayerData playerData)
/* 14:   */   {
/* 15:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 16:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */     Composer.add(Integer.valueOf(playerData.userId), ClientMessage);
/* 18:21 */     Composer.add(playerData.userName, ClientMessage);
/* 19:22 */     Composer.add(playerData.avatarLook.toString(), ClientMessage);
/* 20:23 */     Composer.add(Long.valueOf((Utils.getTimestamp() - playerData.registerDate) / 60L), ClientMessage);
/* 21:24 */     Composer.add(Long.valueOf((Utils.getTimestamp() - playerData.lastVisit) / 60L), ClientMessage);
/* 22:25 */     Composer.add(Boolean.valueOf(playerData.connection != null), ClientMessage);
/* 23:26 */     Composer.add(Integer.valueOf(playerData.cfhs), ClientMessage);
/* 24:27 */     Composer.add(Integer.valueOf(playerData.cfhs_abusive), ClientMessage);
/* 25:28 */     Composer.add(Integer.valueOf(playerData.cautions), ClientMessage);
/* 26:29 */     Composer.add(Integer.valueOf(playerData.bans), ClientMessage);
/* 27:30 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 28:31 */     Composer.add("lastPurchase", ClientMessage);
/* 29:32 */     Composer.add(Integer.valueOf(playerData.userId), ClientMessage);
/* 30:33 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 31:34 */     Composer.add(playerData.email, ClientMessage);
/* 32:35 */     Composer.endPacket(ClientMessage);
/* 33:36 */     return ClientMessage;
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.moderation.ModeratorUserInfoComposer
 * JD-Core Version:    0.7.0.1
 */