/*  1:   */ package cappo.protocol.messages.composers.avatar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.Wardrobe;
/*  5:   */ import cappo.game.player.AvatarLook;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import java.util.Collection;
/*  8:   */ 
/*  9:   */ public class WardrobeComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(int clubLevel, Collection<Wardrobe> Wardrobes)
/* 14:   */   {
/* 15:19 */     MessageWriter ClientMessage = new MessageWriter(100 + Wardrobes.size() * 400);
/* 16:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:21 */     Composer.add(Integer.valueOf(clubLevel), ClientMessage);
/* 18:22 */     Composer.add(Integer.valueOf(clubLevel > 0 ? Wardrobes.size() : 0), ClientMessage);
/* 19:23 */     if (clubLevel > 0) {
/* 20:24 */       for (Wardrobe wrb : Wardrobes)
/* 21:   */       {
/* 22:25 */         Composer.add(Short.valueOf(wrb.slotId), ClientMessage);
/* 23:26 */         if (!AvatarLook.validateLook(wrb.look))
/* 24:   */         {
/* 25:27 */           wrb.look = "hr-115-42.hd-190-1.ch-215-62.lg-285-91.sh-290-62";
/* 26:28 */           wrb.gender = 1;
/* 27:29 */           wrb.needInsert = true;
/* 28:   */         }
/* 29:31 */         Composer.add(wrb.look, ClientMessage);
/* 30:32 */         Composer.add(wrb.gender == 1 ? "M" : "F", ClientMessage);
/* 31:   */       }
/* 32:   */     }
/* 33:35 */     Composer.endPacket(ClientMessage);
/* 34:36 */     return ClientMessage;
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.avatar.WardrobeComposer
 * JD-Core Version:    0.7.0.1
 */