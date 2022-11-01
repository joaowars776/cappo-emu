/*  1:   */ package cappo.protocol.messages.composers.handshake;
/*  2:   */ 
/*  3:   */ import cappo.engine.Server;
/*  4:   */ import cappo.engine.network.MessageWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.collections.Utils;
/*  7:   */ import cappo.game.player.AvatarLook;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.protocol.messages.Composer;
/* 10:   */ import java.text.SimpleDateFormat;
/* 11:   */ 
/* 12:   */ public class UserInfoComposer
/* 13:   */ {
/* 14:   */   public static int HEADER;
/* 15:   */   
/* 16:   */   public static final MessageWriter compose(Connection cn, Boolean ClientStreamEventsAllow)
/* 17:   */   {
/* 18:21 */     PlayerData playerData = cn.getPlayerData();
/* 19:   */     
/* 20:23 */     MessageWriter ClientMessage = new MessageWriter();
/* 21:24 */     Composer.initPacket(HEADER, ClientMessage);
/* 22:25 */     Composer.add(Integer.valueOf(playerData.userId), ClientMessage);
/* 23:26 */     Composer.add(playerData.userName, ClientMessage);
/* 24:27 */     Composer.add(playerData.avatarLook.toString(), ClientMessage);
/* 25:28 */     Composer.add(playerData.sex == 1 ? "M" : "F", ClientMessage);
/* 26:29 */     Composer.add(playerData.motto, ClientMessage);
/* 27:30 */     Composer.add(playerData.getRealName(), ClientMessage);
/* 28:31 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 29:32 */     Composer.add(Integer.valueOf(cn.respects), ClientMessage);
/* 30:33 */     Composer.add(Integer.valueOf(cn.dailyRespectPoints), ClientMessage);
/* 31:34 */     Composer.add(Integer.valueOf(cn.dailyPetRespectPoints), ClientMessage);
/* 32:35 */     Composer.add(ClientStreamEventsAllow, ClientMessage);
/* 33:36 */     Composer.add(Server.date.format(Utils.GetDate(playerData.lastVisit * 1000L)), ClientMessage);
/* 34:37 */     Composer.add(Boolean.valueOf(cn.haveFlag(4)), ClientMessage);
/* 35:38 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 36:39 */     Composer.endPacket(ClientMessage);
/* 37:40 */     return ClientMessage;
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.handshake.UserInfoComposer
 * JD-Core Version:    0.7.0.1
 */