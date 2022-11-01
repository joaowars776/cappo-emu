/*  1:   */ package cappo.game.roomengine.itemInteractor;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.player.AvatarLook;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  9:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/* 10:   */ import cappo.game.roomengine.entity.item.floor.OutFitItem;
/* 11:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/* 12:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 13:   */ import cappo.protocol.messages.composers.room.engine.UserChangeComposer;
/* 14:   */ 
/* 15:   */ public class InteractorOutfit
/* 16:   */   extends Interactor
/* 17:   */ {
/* 18:   */   public void OnPlace(RoomTask room, Connection User, GenericFloorItem Item) {}
/* 19:   */   
/* 20:   */   public void OnTriggerFloor(RoomTask room, Connection User, FloorItem item, int Request, boolean UserHasRights)
/* 21:   */   {
/* 22:26 */     if (User == null) {
/* 23:27 */       return;
/* 24:   */     }
/* 25:30 */     PlayerData playerData = User.getPlayerData();
/* 26:   */     
/* 27:32 */     ((OutFitItem)item).generateLook(playerData);
/* 28:   */     
/* 29:34 */     QueueWriter.writeAndFlush(User.socket, UserChangeComposer.compose(-1, playerData.avatarLook.toString(), playerData.sex, playerData.motto, playerData.AchievementsScore));
/* 30:35 */     room.sendMessage(UserChangeComposer.compose(User.avatar.virtualId, playerData.avatarLook.toString(), playerData.sex, playerData.motto, playerData.AchievementsScore));
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void OnTriggerWall(RoomTask room, Connection User, GenericWallItem Item, int Request, boolean UserHasRights) {}
/* 34:   */   
/* 35:   */   public void OnPickUp(RoomTask room, Connection User, GenericFloorItem Item) {}
/* 36:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.itemInteractor.InteractorOutfit
 * JD-Core Version:    0.7.0.1
 */