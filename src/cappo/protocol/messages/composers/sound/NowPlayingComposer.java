/*  1:   */ package cappo.protocol.messages.composers.sound;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.SongItem;
/*  5:   */ import cappo.game.sound.trax.TraxDisc;
/*  6:   */ import cappo.game.sound.trax.TraxPlaylist;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ 
/*  9:   */ public class NowPlayingComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(TraxPlaylist list, int timeForNextSong)
/* 14:   */   {
/* 15:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 16:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:19 */     Composer.add(Integer.valueOf(list.CurrentSong.Disc.Id), ClientMessage);
/* 18:20 */     Composer.add(Integer.valueOf(list.SongIndex), ClientMessage);
/* 19:21 */     Composer.add(Integer.valueOf(list.CurrentSong.Disc.Id), ClientMessage);
/* 20:22 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 21:23 */     Composer.add(Integer.valueOf(timeForNextSong), ClientMessage);
/* 22:24 */     Composer.endPacket(ClientMessage);
/* 23:25 */     return ClientMessage;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public static final MessageWriter compose()
/* 27:   */   {
/* 28:29 */     MessageWriter ClientMessage = new MessageWriter();
/* 29:30 */     Composer.initPacket(HEADER, ClientMessage);
/* 30:31 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 31:32 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 32:33 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 33:34 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 34:35 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 35:36 */     Composer.endPacket(ClientMessage);
/* 36:37 */     return ClientMessage;
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.sound.NowPlayingComposer
 * JD-Core Version:    0.7.0.1
 */