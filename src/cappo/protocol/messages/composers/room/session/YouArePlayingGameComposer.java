/*  1:   */ package cappo.protocol.messages.composers.room.session;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class YouArePlayingGameComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(boolean isPlaying)
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(Boolean.valueOf(isPlaying), ClientMessage);
/* 15:19 */     Composer.endPacket(ClientMessage);
/* 16:20 */     return ClientMessage;
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.session.YouArePlayingGameComposer
 * JD-Core Version:    0.7.0.1
 */