/*  1:   */ package cappo.protocol.messages.composers.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class YoutubeDisplayPlaylistsMessageComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(GenericFloorItem item)
/* 12:   */   {
/* 13:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 14:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 15:   */     
/* 16:20 */     Composer.endPacket(ClientMessage);
/* 17:21 */     return ClientMessage;
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.furniture.YoutubeDisplayPlaylistsMessageComposer
 * JD-Core Version:    0.7.0.1
 */