/*  1:   */ package cappo.protocol.messages.events.handshake;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.player.PlayerData;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.availability.AvailabilityStatusComposer;
/*  8:   */ import cappo.protocol.messages.composers.handshake.PerkAllowancesComposer;
/*  9:   */ import cappo.protocol.messages.composers.handshake.UserInfoComposer;
/* 10:   */ import cappo.protocol.messages.composers.handshake.UserLevelsComposer;
/* 11:   */ import cappo.protocol.messages.composers.inventory.avatareffect.EffectsComposer;
/* 12:   */ import cappo.protocol.messages.composers.notifications.ActivityPointsComposer;
/* 13:   */ 
/* 14:   */ public class InfoRetrieveParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection cn)
/* 18:   */   {
/* 19:22 */     QueueWriter.write(cn.socket, EffectsComposer.compose(cn.avatarEffects));
/* 20:23 */     QueueWriter.write(cn.socket, UserLevelsComposer.compose(2, cn.playerData.staffLevel));
/* 21:24 */     QueueWriter.write(cn.socket, AvailabilityStatusComposer.compose());
/* 22:25 */     QueueWriter.write(cn.socket, ActivityPointsComposer.compose(cn.pixelAmmount, cn.diamondAmmount));
/* 23:26 */     QueueWriter.write(cn.socket, UserInfoComposer.compose(cn, Boolean.valueOf(false)));
/* 24:27 */     QueueWriter.write(cn.socket, PerkAllowancesComposer.compose(cn.avatarData));
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.handshake.InfoRetrieveParser
 * JD-Core Version:    0.7.0.1
 */