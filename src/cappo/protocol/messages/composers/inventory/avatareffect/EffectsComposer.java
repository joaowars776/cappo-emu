/*  1:   */ package cappo.protocol.messages.composers.inventory.avatareffect;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.AvatarEffect;
/*  5:   */ import cappo.game.collections.Utils;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import java.util.List;
/*  8:   */ 
/*  9:   */ public class EffectsComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(List<AvatarEffect> Effects)
/* 14:   */   {
/* 15:20 */     MessageWriter ClientMessage = new MessageWriter(100 + Effects.size() * 50);
/* 16:21 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:22 */     Composer.add(Integer.valueOf(Effects.size()), ClientMessage);
/* 18:23 */     for (AvatarEffect Effect : Effects)
/* 19:   */     {
/* 20:24 */       Composer.add(Integer.valueOf(Effect.effectType), ClientMessage);
/* 21:25 */       Composer.add(Integer.valueOf(Effect.noNamed), ClientMessage);
/* 22:26 */       Composer.add(Integer.valueOf(Effect.TotalDuration), ClientMessage);
/* 23:27 */       Composer.add(Integer.valueOf(Effect.Activated ? 1 : 0), ClientMessage);
/* 24:28 */       int timeLeft = (int)(Effect.TotalDuration - (Utils.getTimestamp() - Effect.ActivateTimestamp));
/* 25:29 */       Composer.add(Integer.valueOf(timeLeft), ClientMessage);
/* 26:   */     }
/* 27:31 */     Composer.endPacket(ClientMessage);
/* 28:32 */     return ClientMessage;
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.avatareffect.EffectsComposer
 * JD-Core Version:    0.7.0.1
 */