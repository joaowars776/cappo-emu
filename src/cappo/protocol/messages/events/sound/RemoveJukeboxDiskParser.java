/*  1:   */ package cappo.protocol.messages.events.sound;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.player.inventory.PlayerInventory;
/*  9:   */ import cappo.game.roomengine.entity.item.floor.SongItem;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.game.sound.trax.TraxPlaylist;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ import cappo.protocol.messages.composers.sound.JukeboxSongDisksComposer;
/* 14:   */ import cappo.protocol.messages.composers.sound.UserSongDisksInventoryComposer;
/* 15:   */ import java.util.List;
/* 16:   */ 
/* 17:   */ public class RemoveJukeboxDiskParser
/* 18:   */   extends IncomingMessageEvent
/* 19:   */ {
/* 20:   */   public void messageReceived(Connection Main)
/* 21:   */   {
/* 22:23 */     Avatar avatar = Main.avatar;
/* 23:24 */     if (avatar == null) {
/* 24:25 */       return;
/* 25:   */     }
/* 26:28 */     RoomTask room = avatar.room;
/* 27:   */     
/* 28:30 */     int index = Main.currentPacket.readInt();
/* 29:   */     
/* 30:32 */     SongItem item = (SongItem)room.traxPlaylist.PlaylistByIndex.get(index);
/* 31:33 */     if (item.owner.userId != Main.playerData.userId) {
/* 32:34 */       return;
/* 33:   */     }
/* 34:37 */     Main.inventoryAddFloorItem(item);
/* 35:38 */     item.setMysqlState(2);
/* 36:   */     
/* 37:40 */     room.traxPlaylist.PlaylistByIndex.remove(index);
/* 38:   */     
/* 39:42 */     QueueWriter.write(Main.socket, UserSongDisksInventoryComposer.compose(Main.inventory.getSongs()));
/* 40:43 */     QueueWriter.write(Main.socket, JukeboxSongDisksComposer.compose(room.traxPlaylist.PlaylistByIndex));
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.sound.RemoveJukeboxDiskParser
 * JD-Core Version:    0.7.0.1
 */