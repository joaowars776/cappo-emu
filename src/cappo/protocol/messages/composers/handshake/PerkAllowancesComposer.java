/*  1:   */ package cappo.protocol.messages.composers.handshake;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.settings.PerkAllowance;
/*  5:   */ import cappo.game.player.data.AvatarData;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import java.util.List;
/*  8:   */ 
/*  9:   */ public class PerkAllowancesComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(AvatarData avatarData)
/* 14:   */   {
/* 15:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 16:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */     Composer.add(Integer.valueOf(avatarData.perksAllowances.size()), ClientMessage);
/* 18:21 */     for (PerkAllowance perk : avatarData.perksAllowances)
/* 19:   */     {
/* 20:22 */       Composer.add(perk.codeName, ClientMessage);
/* 21:23 */       Composer.add(perk.errorText, ClientMessage);
/* 22:24 */       Composer.add(Boolean.valueOf(perk.active), ClientMessage);
/* 23:   */     }
/* 24:26 */     Composer.endPacket(ClientMessage);
/* 25:27 */     return ClientMessage;
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.handshake.PerkAllowancesComposer
 * JD-Core Version:    0.7.0.1
 */