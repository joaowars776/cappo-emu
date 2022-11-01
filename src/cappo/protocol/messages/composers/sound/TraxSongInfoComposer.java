/*  1:   */ package cappo.protocol.messages.composers.sound;
/*  2:   */ 
/*  3:   */ import cappo.engine.logging.Log;
/*  4:   */ import cappo.engine.network.MessageWriter;
/*  5:   */ import cappo.game.sound.trax.Trax;
/*  6:   */ import cappo.game.sound.trax.TraxDisc;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ import java.util.Iterator;
/*  9:   */ import java.util.List;
/* 10:   */ import java.util.Map;
/* 11:   */ 
/* 12:   */ public class TraxSongInfoComposer
/* 13:   */ {
/* 14:   */   public static int HEADER;
/* 15:   */   
/* 16:   */   public static final MessageWriter compose(List<Integer> Discs)
/* 17:   */   {
/* 18:21 */     int i = 0;
/* 19:22 */     MessageWriter ClientMessage = new MessageWriter(50 + Discs.size() * 500);
/* 20:23 */     Composer.initPacket(HEADER, ClientMessage);
/* 21:24 */     Composer.add(ClientMessage.setSaved(Integer.valueOf(0)), ClientMessage);
/* 22:25 */     for (Iterator localIterator = Discs.iterator(); localIterator.hasNext();)
/* 23:   */     {
/* 24:25 */       int DiscId = ((Integer)localIterator.next()).intValue();
/* 25:26 */       TraxDisc Disc = (TraxDisc)Trax.songDiscs.get(Integer.valueOf(DiscId));
/* 26:27 */       if (Disc == null)
/* 27:   */       {
/* 28:28 */         Log.printLog("DiscId[" + DiscId + "] is Invalid..");
/* 29:   */       }
/* 30:   */       else
/* 31:   */       {
/* 32:31 */         Composer.add(Integer.valueOf(Disc.Id), ClientMessage);
/* 33:32 */         Composer.add("", ClientMessage);
/* 34:33 */         Composer.add(Disc.Name, ClientMessage);
/* 35:34 */         Composer.add(Disc.SongData, ClientMessage);
/* 36:35 */         Composer.add(Integer.valueOf(Disc.Length), ClientMessage);
/* 37:36 */         Composer.add(Disc.Author, ClientMessage);
/* 38:37 */         i++;
/* 39:   */       }
/* 40:   */     }
/* 41:39 */     ClientMessage.writeSaved(Integer.valueOf(i));
/* 42:40 */     Composer.endPacket(ClientMessage);
/* 43:41 */     return ClientMessage;
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.sound.TraxSongInfoComposer
 * JD-Core Version:    0.7.0.1
 */