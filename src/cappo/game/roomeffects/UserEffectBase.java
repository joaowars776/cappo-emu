/*  1:   */ package cappo.game.roomeffects;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.roomeffects.special.UserSpecialEffect;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.protocol.messages.composers.room.action.UserEffectComposer;
/*  8:   */ import io.netty.channel.Channel;
/*  9:   */ 
/* 10:   */ public abstract class UserEffectBase
/* 11:   */ {
/* 12:   */   public Avatar user;
/* 13:   */   public short effectId;
/* 14:   */   
/* 15:   */   public UserEffectBase(Avatar avatar, short effect)
/* 16:   */   {
/* 17:15 */     this.user = avatar;
/* 18:16 */     this.effectId = effect;
/* 19:17 */     startEffect();
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void startEffect()
/* 23:   */   {
/* 24:21 */     if (((this instanceof UserSpecialEffect)) || (this.user.userSpecialEffect == null)) {
/* 25:22 */       this.user.room.sendMessage(UserEffectComposer.compose(this.user.virtualId, this.effectId));
/* 26:   */     }
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void startEffect(Channel socket)
/* 30:   */   {
/* 31:27 */     if (((this instanceof UserSpecialEffect)) || (this.user.userSpecialEffect == null)) {
/* 32:28 */       QueueWriter.writeAndFlush(socket, UserEffectComposer.compose(this.user.virtualId, this.effectId));
/* 33:   */     }
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void stopEffect()
/* 37:   */   {
/* 38:33 */     this.user.userEffect = null;
/* 39:34 */     if (this.user.userSpecialEffect == null) {
/* 40:35 */       this.user.room.sendMessage(UserEffectComposer.compose(this.user.virtualId, -1));
/* 41:   */     }
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomeffects.UserEffectBase
 * JD-Core Version:    0.7.0.1
 */