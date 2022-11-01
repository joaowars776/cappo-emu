/*  1:   */ package cappo.protocol.messages.composers.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.MoodlightData;
/*  5:   */ import cappo.game.collections.MoodlightData.MoodlightPreset;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import java.util.List;
/*  8:   */ 
/*  9:   */ public class RoomDimmerPresetsComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(MoodlightData Moodlightdata)
/* 14:   */   {
/* 15:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 16:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:19 */     Composer.add(Integer.valueOf(Moodlightdata.Presets.size()), ClientMessage);
/* 18:20 */     Composer.add(Integer.valueOf(Moodlightdata.CurrentPreset), ClientMessage);
/* 19:21 */     int i = 0;
/* 20:22 */     for (MoodlightData.MoodlightPreset Preset : Moodlightdata.Presets)
/* 21:   */     {
/* 22:23 */       i++;Composer.add(Integer.valueOf(i), ClientMessage);
/* 23:24 */       Composer.add(Integer.valueOf(Preset.BackgroundOnly ? 2 : 1), ClientMessage);
/* 24:25 */       Composer.add(Preset.ColorCode, ClientMessage);
/* 25:26 */       Composer.add(Integer.valueOf(Preset.ColorIntensity), ClientMessage);
/* 26:   */     }
/* 27:28 */     Composer.endPacket(ClientMessage);
/* 28:29 */     return ClientMessage;
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.furniture.RoomDimmerPresetsComposer
 * JD-Core Version:    0.7.0.1
 */