/*  1:   */ package cappo.game.roomengine.roomevents;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  5:   */ import cappo.game.roomengine.entity.item.floor.SongItem;
/*  6:   */ import cappo.game.sound.trax.TraxDisc;
/*  7:   */ import cappo.game.sound.trax.TraxPlaylist;
/*  8:   */ import cappo.protocol.messages.composers.sound.NowPlayingComposer;
/*  9:   */ 
/* 10:   */ public class Trax_NEXTSONG
/* 11:   */   extends Event
/* 12:   */ {
/* 13:   */   FloorItem Item;
/* 14:   */   
/* 15:   */   public Trax_NEXTSONG(FloorItem item)
/* 16:   */   {
/* 17:17 */     this.Item = item;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void run(RoomTask room)
/* 21:   */   {
/* 22:22 */     if (room.traxPlaylist.Playing)
/* 23:   */     {
/* 24:23 */       room.traxPlaylist.NextSong();
/* 25:24 */       if (room.traxPlaylist.CurrentSong != null)
/* 26:   */       {
/* 27:25 */         this.Ticks += room.traxPlaylist.CurrentSong.Disc.Length / 500;
/* 28:26 */         room.traxPlaylist.nextSongTime = (System.currentTimeMillis() + room.traxPlaylist.CurrentSong.Disc.Length);
/* 29:27 */         room.sendMessage(NowPlayingComposer.compose(room.traxPlaylist, 0));
/* 30:   */       }
/* 31:   */       else
/* 32:   */       {
/* 33:29 */         room.traxPlaylist.Playing = false;
/* 34:   */       }
/* 35:   */     }
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomevents.Trax_NEXTSONG
 * JD-Core Version:    0.7.0.1
 */