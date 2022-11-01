/*  1:   */ package cappo.protocol.messages.composers.sound;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.SongItem;
/*  5:   */ import cappo.game.sound.trax.TraxDisc;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import java.util.List;
/*  8:   */ 
/*  9:   */ public class JukeboxSongDisksComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(List<SongItem> PlaylistByIndex)
/* 14:   */   {
/* 15:19 */     MessageWriter ClientMessage = new MessageWriter();
/* 16:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 18:22 */     Composer.add(Integer.valueOf(PlaylistByIndex.size()), ClientMessage);
/* 19:23 */     int count = 0;
/* 20:24 */     for (SongItem Song : PlaylistByIndex)
/* 21:   */     {
/* 22:25 */       Composer.add(Integer.valueOf(count++), ClientMessage);
/* 23:26 */       Composer.add(Integer.valueOf(Song.Disc.Id), ClientMessage);
/* 24:   */     }
/* 25:28 */     Composer.endPacket(ClientMessage);
/* 26:29 */     return ClientMessage;
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.sound.JukeboxSongDisksComposer
 * JD-Core Version:    0.7.0.1
 */