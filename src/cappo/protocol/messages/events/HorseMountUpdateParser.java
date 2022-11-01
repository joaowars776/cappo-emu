/*  1:   */ package cappo.protocol.messages.events;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.games.snowwar.Direction8;
/*  7:   */ import cappo.game.pets.Pet;
/*  8:   */ import cappo.game.roomeffects.special.RidingEffect;
/*  9:   */ import cappo.game.roomeffects.special.UserSpecialEffect;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.game.roomengine.entity.live.PetEntity;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ 
/* 14:   */ public class HorseMountUpdateParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:20 */     Avatar avatar = Main.avatar;
/* 20:21 */     if (avatar == null) {
/* 21:22 */       return;
/* 22:   */     }
/* 23:25 */     PetEntity petEntity = avatar.room.getRoomPetById(Main.currentPacket.readInt());
/* 24:26 */     if ((petEntity == null) || (petEntity.petData.petType != 15)) {
/* 25:27 */       return;
/* 26:   */     }
/* 27:30 */     boolean mount = Main.currentPacket.readBoolean();
/* 28:32 */     if (mount)
/* 29:   */     {
/* 30:33 */       if ((!petEntity.petData.ridingAll) && (avatar.controllerLevel < 4)) {
/* 31:34 */         return;
/* 32:   */       }
/* 33:37 */       if ((petEntity.ridingEntity != null) || (avatar.ridingEntity != null)) {
/* 34:38 */         return;
/* 35:   */       }
/* 36:41 */       int frontX = petEntity.x + petEntity.RotBody.getDiffX();
/* 37:42 */       int frontY = petEntity.y + petEntity.RotBody.getDiffY();
/* 38:44 */       if (((avatar.x == frontX) && (avatar.y == frontY)) || ((avatar.x == petEntity.x) && (avatar.y == petEntity.y)))
/* 39:   */       {
/* 40:45 */         avatar.allowOverride = true;
/* 41:46 */         avatar.moveTo(petEntity.x, petEntity.y);
/* 42:   */         
/* 43:48 */         petEntity.ridingEntity = avatar;
/* 44:49 */         avatar.ridingEntity = petEntity;
/* 45:51 */         if ((avatar.userSpecialEffect == null) || (avatar.userSpecialEffect.effectId != 77)) {
/* 46:52 */           avatar.userSpecialEffect = new RidingEffect(avatar, (short)77);
/* 47:   */         }
/* 48:   */       }
/* 49:   */       else
/* 50:   */       {
/* 51:55 */         avatar.moveTo(frontX, frontY);
/* 52:   */       }
/* 53:   */     }
/* 54:   */     else
/* 55:   */     {
/* 56:58 */       if ((petEntity.ridingEntity == null) || (avatar.ridingEntity == null)) {
/* 57:59 */         return;
/* 58:   */       }
/* 59:62 */       petEntity.ridingEntity = null;
/* 60:63 */       avatar.ridingEntity = null;
/* 61:   */     }
/* 62:66 */     avatar.room.userUpdateNeeded(petEntity);
/* 63:   */   }
/* 64:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.HorseMountUpdateParser
 * JD-Core Version:    0.7.0.1
 */