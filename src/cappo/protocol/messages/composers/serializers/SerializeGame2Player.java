/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  6:   */ import cappo.game.player.AvatarLook;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.SnowWarPlayerData;
/*  9:   */ import cappo.protocol.messages.Composer;
/* 10:   */ 
/* 11:   */ public class SerializeGame2Player
/* 12:   */ {
/* 13:   */   public static void parse(MessageWriter ClientMessage, Connection cn)
/* 14:   */   {
/* 15:15 */     Composer.add(Integer.valueOf(cn.playerData.userId), ClientMessage);
/* 16:16 */     Composer.add(cn.playerData.userName, ClientMessage);
/* 17:17 */     Composer.add(cn.playerData.avatarLook.toString(), ClientMessage);
/* 18:18 */     Composer.add(cn.playerData.sex == 1 ? "M" : "F", ClientMessage);
/* 19:19 */     Composer.add(Integer.valueOf(cn.snowWarPlayerData.humanObject.team), ClientMessage);
/* 20:20 */     Composer.add(Integer.valueOf(cn.snowWarPlayerData.snowLevel), ClientMessage);
/* 21:21 */     Composer.add(Integer.valueOf(cn.snowWarPlayerData.score), ClientMessage);
/* 22:22 */     Composer.add(Integer.valueOf(cn.snowWarPlayerData.PointsNeed), ClientMessage);
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2Player
 * JD-Core Version:    0.7.0.1
 */