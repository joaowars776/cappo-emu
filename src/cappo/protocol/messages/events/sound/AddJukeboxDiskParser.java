/*  1:   */ package cappo.protocol.messages.events.sound;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.player.inventory.PlayerInventory;
/*  8:   */ import cappo.game.roomengine.entity.item.floor.SongItem;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.game.sound.trax.TraxPlaylist;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.sound.JukeboxSongDisksComposer;
/* 13:   */ import java.util.List;
/* 14:   */ 
/* 15:   */ public class AddJukeboxDiskParser
/* 16:   */   extends IncomingMessageEvent
/* 17:   */ {
/* 18:   */   public void messageReceived(Connection Main)
/* 19:   */   {
/* 20:21 */     Avatar avatar = Main.avatar;
/* 21:22 */     if (avatar == null) {
/* 22:23 */       return;
/* 23:   */     }
/* 24:26 */     int itemid = Main.currentPacket.readInt();
/* 25:   */     
/* 26:28 */     SongItem item = Main.inventory.getSong(itemid);
/* 27:29 */     if (item == null) {
/* 28:30 */       return;
/* 29:   */     }
/* 30:33 */     Main.inventoryRemoveItem(itemid, false);
/* 31:34 */     item.setMysqlState(2);
/* 32:35 */     avatar.room.traxPlaylist.PlaylistByIndex.add(item);
/* 33:36 */     QueueWriter.write(Main.socket, JukeboxSongDisksComposer.compose(avatar.room.traxPlaylist.PlaylistByIndex));
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.sound.AddJukeboxDiskParser
 * JD-Core Version:    0.7.0.1
 */