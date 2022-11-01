/*  1:   */ package cappo.protocol.messages.events.sound;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.game.sound.trax.TraxPlaylist;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.sound.JukeboxSongDisksComposer;
/* 10:   */ 
/* 11:   */ public class GetJukeboxPlayListParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection Main)
/* 15:   */   {
/* 16:18 */     Avatar avatar = Main.avatar;
/* 17:19 */     if (avatar == null) {
/* 18:20 */       return;
/* 19:   */     }
/* 20:23 */     QueueWriter.write(Main.socket, JukeboxSongDisksComposer.compose(avatar.room.traxPlaylist.PlaylistByIndex));
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.sound.GetJukeboxPlayListParser
 * JD-Core Version:    0.7.0.1
 */