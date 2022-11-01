/*  1:   */ package cappo.protocol.messages.events.sound;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.game.sound.trax.TraxPlaylist;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.sound.NowPlayingComposer;
/* 10:   */ 
/* 11:   */ public class GetNowPlayingParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection Main)
/* 15:   */   {
/* 16:20 */     Avatar avatar = Main.avatar;
/* 17:21 */     if (avatar == null) {
/* 18:22 */       return;
/* 19:   */     }
/* 20:25 */     RoomTask room = avatar.room;
/* 21:26 */     if (room.traxPlaylist.Playing) {
/* 22:27 */       QueueWriter.write(Main.socket, NowPlayingComposer.compose(room.traxPlaylist, (int)(room.traxPlaylist.nextSongTime - System.currentTimeMillis())));
/* 23:   */     }
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.sound.GetNowPlayingParser
 * JD-Core Version:    0.7.0.1
 */