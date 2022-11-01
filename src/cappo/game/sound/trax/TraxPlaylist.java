/*  1:   */ package cappo.game.sound.trax;
/*  2:   */ 
/*  3:   */ import cappo.game.roomengine.entity.item.floor.SongItem;
/*  4:   */ import java.util.ArrayList;
/*  5:   */ import java.util.List;
/*  6:   */ 
/*  7:   */ public class TraxPlaylist
/*  8:   */ {
/*  9:   */   public SongItem CurrentSong;
/* 10:   */   public boolean Playing;
/* 11:17 */   public List<SongItem> PlaylistByIndex = new ArrayList();
/* 12:   */   public int SongIndex;
/* 13:   */   public long nextSongTime;
/* 14:   */   
/* 15:   */   public void NextSong()
/* 16:   */   {
/* 17:23 */     this.SongIndex = (++this.SongIndex % this.PlaylistByIndex.size());
/* 18:24 */     this.CurrentSong = ((SongItem)this.PlaylistByIndex.get(this.SongIndex));
/* 19:26 */     if (this.CurrentSong == null) {
/* 20:27 */       this.Playing = false;
/* 21:   */     }
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void StartPlaying()
/* 25:   */   {
/* 26:32 */     if (this.PlaylistByIndex.isEmpty()) {
/* 27:33 */       this.CurrentSong = null;
/* 28:   */     } else {
/* 29:35 */       this.CurrentSong = ((SongItem)this.PlaylistByIndex.get(this.SongIndex = 0));
/* 30:   */     }
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.sound.trax.TraxPlaylist
 * JD-Core Version:    0.7.0.1
 */