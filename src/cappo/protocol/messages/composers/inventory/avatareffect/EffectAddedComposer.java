/*  1:   */ package cappo.protocol.messages.composers.inventory.avatareffect;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.AvatarEffect;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class EffectAddedComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(AvatarEffect effect)
/* 12:   */   {
/* 13:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 14:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(effect.effectType), ClientMessage);
/* 16:20 */     Composer.add(Integer.valueOf(effect.noNamed), ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(effect.TotalDuration), ClientMessage);
/* 18:22 */     Composer.endPacket(ClientMessage);
/* 19:23 */     return ClientMessage;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.avatareffect.EffectAddedComposer
 * JD-Core Version:    0.7.0.1
 */