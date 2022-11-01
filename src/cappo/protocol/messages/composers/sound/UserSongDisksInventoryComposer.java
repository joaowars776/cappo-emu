/*  1:   */ package cappo.protocol.messages.composers.sound;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.entity.item.floor.SongItem;
/*  5:   */ import cappo.game.sound.trax.TraxDisc;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import java.util.Collection;
/*  8:   */ 
/*  9:   */ public class UserSongDisksInventoryComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(Collection<SongItem> SongInInventory)
/* 14:   */   {
/* 15:19 */     MessageWriter ClientMessage = new MessageWriter();
/* 16:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(SongInInventory.size()), ClientMessage);
/* 18:22 */     for (SongItem disc : SongInInventory)
/* 19:   */     {
/* 20:23 */       Composer.add(Integer.valueOf(disc.itemId), ClientMessage);
/* 21:24 */       Composer.add(Integer.valueOf(disc.Disc.Id), ClientMessage);
/* 22:   */     }
/* 23:26 */     Composer.endPacket(ClientMessage);
/* 24:27 */     return ClientMessage;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.sound.UserSongDisksInventoryComposer
 * JD-Core Version:    0.7.0.1
 */