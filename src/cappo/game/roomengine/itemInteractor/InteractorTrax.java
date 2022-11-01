/*  1:   */ package cappo.game.roomengine.itemInteractor;
/*  2:   */ 
/*  3:   */ import cappo.engine.player.Connection;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  7:   */ import cappo.game.roomengine.entity.item.floor.SongItem;
/*  8:   */ import cappo.game.roomengine.entity.item.wall.GenericWallItem;
/*  9:   */ import cappo.game.roomengine.roomevents.Trax_NEXTSONG;
/* 10:   */ import cappo.game.sound.trax.TraxDisc;
/* 11:   */ import cappo.game.sound.trax.TraxPlaylist;
/* 12:   */ import cappo.protocol.messages.composers.sound.NowPlayingComposer;
/* 13:   */ 
/* 14:   */ public class InteractorTrax
/* 15:   */   extends Interactor
/* 16:   */ {
/* 17:   */   public void OnPlace(RoomTask room, Connection User, GenericFloorItem Item) {}
/* 18:   */   
/* 19:   */   public void OnTriggerFloor(RoomTask room, Connection User, FloorItem fitem, int Request, boolean UserHasRights)
/* 20:   */   {
/* 21:24 */     if (!UserHasRights) {
/* 22:25 */       return;
/* 23:   */     }
/* 24:28 */     if (Request == -1) {
/* 25:29 */       return;
/* 26:   */     }
/* 27:32 */     GenericFloorItem Item = (GenericFloorItem)fitem;
/* 28:34 */     if (!room.traxPlaylist.Playing)
/* 29:   */     {
/* 30:35 */       room.traxPlaylist.StartPlaying();
/* 31:36 */       if (room.traxPlaylist.CurrentSong != null)
/* 32:   */       {
/* 33:37 */         room.traxPlaylist.Playing = true;
/* 34:   */         
/* 35:39 */         Item.setIntData(1);
/* 36:40 */         room.floorItemUpdateNeeded(Item);
/* 37:   */         
/* 38:42 */         room.sendMessage(NowPlayingComposer.compose(room.traxPlaylist, 0));
/* 39:   */         
/* 40:44 */         room.addItemEvent(new Trax_NEXTSONG(Item), room.traxPlaylist.CurrentSong.Disc.Length / 500);
/* 41:   */       }
/* 42:   */     }
/* 43:   */     else
/* 44:   */     {
/* 45:47 */       Item.setIntData(0);
/* 46:48 */       room.floorItemUpdateNeeded(Item);
/* 47:49 */       room.traxPlaylist.Playing = false;
/* 48:50 */       room.sendMessage(NowPlayingComposer.compose());
/* 49:   */     }
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void OnTriggerWall(RoomTask room, Connection User, GenericWallItem Item, int Request, boolean UserHasRights) {}
/* 53:   */   
/* 54:   */   public void OnPickUp(RoomTask room, Connection User, GenericFloorItem Item) {}
/* 55:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.itemInteractor.InteractorTrax
 * JD-Core Version:    0.7.0.1
 */