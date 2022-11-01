/*  1:   */ package cappo.protocol.messages.events.sound;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.sound.TraxSongInfoComposer;
/*  8:   */ import java.util.ArrayList;
/*  9:   */ import java.util.List;
/* 10:   */ 
/* 11:   */ public class GetSongInfoParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection Main)
/* 15:   */   {
/* 16:20 */     int Count = Main.currentPacket.readInt();
/* 17:21 */     if (Count > 20) {
/* 18:23 */       return;
/* 19:   */     }
/* 20:26 */     List<Integer> Discs = new ArrayList(Count);
/* 21:27 */     for (int i = 0; i < Count; i++) {
/* 22:28 */       Discs.add(Integer.valueOf(Main.currentPacket.readInt()));
/* 23:   */     }
/* 24:31 */     QueueWriter.write(Main.socket, TraxSongInfoComposer.compose(Discs));
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.sound.GetSongInfoParser
 * JD-Core Version:    0.7.0.1
 */